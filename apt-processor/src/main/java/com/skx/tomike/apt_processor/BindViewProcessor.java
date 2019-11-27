package com.skx.tomike.apt_processor;

import com.skx.tomike.apt_annotation.BindView;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public class BindViewProcessor extends AbstractProcessor {

    private Filer mFilerUtils;// 文件管理工具类
    private Types mTypesUtils;// 类型处理工具类，包含用于操作TypeMirror的工具方法
    private Elements mElementsUtils;// Element处理工具类，(类、函数、属性都是Element)

    // 初始化。可以得到ProcessingEnvironment，ProcessingEnvironment提供很多有用的工具类Elements, Types 和 Filer
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFilerUtils = processingEnv.getFiler();
        mTypesUtils = processingEnv.getTypeUtils();
        mElementsUtils = processingEnv.getElementUtils();
    }

    // 指定此注解处理器的目标注解对象，需要自己添加。
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> supportTypes = new LinkedHashSet<>();
        supportTypes.add(BindView.class.getCanonicalName());
        return supportTypes; //将要支持的注解放入其中
    }

    // 指定支持的Java版本，通常这里返回SourceVersion.latestSupported()
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();// 表示支持最新的Java版本
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elementSet = roundEnvironment.getElementsAnnotatedWith(BindView.class);

        Map<TypeElement, Map<Integer, VariableElement>> typeElementMapHashMap = new HashMap<>();
        for (Element element : elementSet) {
            // 被@BindView标注的应当是变量，注解的是FIELD，因此可以直接转换
            VariableElement variableElement = (VariableElement) element;
            // 获取最里层的元素，此处就是Activity
            TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();
            // 获取对应Activity中的Map viewId View
            Map<Integer, VariableElement> variableElementMap = typeElementMapHashMap.get(typeElement);
            if (variableElementMap == null) {
                variableElementMap = new HashMap<>();
                typeElementMapHashMap.put(typeElement, variableElementMap);
            }
            // 获取注解对象
            BindView bindView = variableElement.getAnnotation(BindView.class);
            // 获取注解值
            int id = bindView.value();
            variableElementMap.put(id, variableElement);
        }

        for (TypeElement key : typeElementMapHashMap.keySet()) {
            Map<Integer, VariableElement> elementMap = typeElementMapHashMap.get(key);
            String packageName = mElementsUtils.getPackageOf(key).getQualifiedName().toString();

            JavaFile javaFile = JavaFile.builder(packageName, generateCodeByPoet(key, elementMap)).build();
            try {
                javaFile.writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    private TypeSpec generateCodeByPoet(TypeElement typeElement, Map<Integer, VariableElement> variableElementMap) {
        //自动生成的文件以 Activity名 + ViewBinding 进行命名
        return TypeSpec.classBuilder(typeElement.getSimpleName().toString() + "ViewBinding")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(generateMethodByPoet(typeElement, variableElementMap))
                .build();
    }

    /**
     * @param typeElement        注解对象的根元素，即Activity
     * @param variableElementMap Activity包含的注解对象以及注解的目标对象
     * @return MethodSpec
     */
    private MethodSpec generateMethodByPoet(TypeElement typeElement, Map<Integer, VariableElement> variableElementMap) {
        ClassName className = ClassName.bestGuess(typeElement.getQualifiedName().toString());
        //  _mainActivity.btn_serializeSingle = (android.widget.Button) (_mainActivity.findViewById(2131165221));
        // 第一个转小写+下划线
        String parameter = "_" + toLowerCaseFirstChar(className.simpleName());
        MethodSpec.Builder builder = MethodSpec.methodBuilder("bind") // 方法名
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)             // public static
                .returns(void.class)// 返回类型
                .addParameter(className, parameter);
        for (int viewId : variableElementMap.keySet()) {
            VariableElement variableElement = variableElementMap.get(viewId);
            // 变量名
            String fieldName = variableElement.getSimpleName().toString();
            // 变量父类的全称
            String fieldType = variableElement.asType().toString();
            String text = "{0}.{1} = ({2})({3}.findViewById({4}));";
            builder.addCode(MessageFormat.format(text, parameter, fieldName, fieldType, parameter, String.valueOf(viewId)));
        }
        return builder.build();
    }

    private String toLowerCaseFirstChar(String str) {
        String first = str.charAt(0) + "";
        return first.toLowerCase() + str.substring(1);
    }

}
