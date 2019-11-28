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
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

public class BindViewProcessor extends AbstractProcessor {

    private Elements mElementsUtils;// Element处理工具类，(类、函数、属性都是Element)

    /**
     * 此处的TypeElement就是Activity。Integer 对应绑定的id。VariableElement为绑定的view
     */
    private final Map<TypeElement, Map<Integer, VariableElement>> mTypeElementMapHashMap = new HashMap<>();

    // 初始化。可以得到ProcessingEnvironment，ProcessingEnvironment提供很多有用的工具类Elements, Types 和 Filer
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mElementsUtils = processingEnv.getElementUtils();
    }

    // 指定此注解处理器的目标注解对象，需要自己添加。
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> supportTypes = new LinkedHashSet<>();
        supportTypes.add(BindView.class.getCanonicalName());
        return supportTypes; //将要支持的注解放入其中
    }

    // 用于指示注释处理器支持的最新源版本的注释，通常这里返回SourceVersion.latestSupported()
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();// 表示支持最新的Java版本
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // 获取所有包含BindView注解的元素
        Set<? extends Element> elementSet = roundEnvironment.getElementsAnnotatedWith(BindView.class);
        createTypeElement(elementSet);
        createFile();

        return true;
    }

    /**
     * 构建类型元素（Activity） map。
     *
     * @param elementSet 带注解的元素集合
     */
    private void createTypeElement(Set<? extends Element> elementSet) {
        if (elementSet == null) return;
        for (Element element : elementSet) {
            // 因为注解是ElementType.FIELD，在java 元素中对应的是VariableElement ，因此这里可以直接转换
            VariableElement variableElement = (VariableElement) element;

            // 取注解元素（VariableElement）的上层元素，也就是取到类层级元素（TypeElement）
            TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();

            Map<Integer, VariableElement> variableElementMap = mTypeElementMapHashMap.get(typeElement);
            // 如果当下注解map 中没有此类型元素对应的value，新建并添加
            if (variableElementMap == null) {
                variableElementMap = new HashMap<>();
                mTypeElementMapHashMap.put(typeElement, variableElementMap);
            }

            // 获取属性字段上的 BindView注解
            BindView bindView = variableElement.getAnnotation(BindView.class);
            // 获取注解值
            int id = bindView.value();
            variableElementMap.put(id, variableElement);
        }
    }

    private void createFile() {
        // 遍历类型元素（Activity） map，为每个类型元素创建一个文件
        for (TypeElement key : mTypeElementMapHashMap.keySet()) {

            // 获取类型元素所在的包名
            String packageName = mElementsUtils.getPackageOf(key).getQualifiedName().toString();
            TypeSpec typeSpec = generateFileCodeByPoet(key, mTypeElementMapHashMap.get(key));
            JavaFile javaFile = JavaFile.builder(packageName, typeSpec).build();
            try {
                javaFile.writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 生成文件代码
     *
     * @param typeElement        类型元素
     * @param variableElementMap
     * @return
     */
    private TypeSpec generateFileCodeByPoet(TypeElement typeElement, Map<Integer, VariableElement> variableElementMap) {
        //自动生成的文件以 Activity名 + ViewBinding 进行命名
        return TypeSpec.classBuilder(typeElement.getSimpleName().toString() + "ViewBinding")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(generateMethodByPoet(typeElement, variableElementMap))
                .build();
    }

    /**
     * 生成方法代码
     *
     * @param typeElement        注解对象的根元素，即Activity
     * @param variableElementMap Activity包含的注解对象以及注解的目标对象
     * @return MethodSpec
     */
    private MethodSpec generateMethodByPoet(TypeElement typeElement, Map<Integer, VariableElement> variableElementMap) {
        ClassName className = ClassName.bestGuess(typeElement.getQualifiedName().toString());
        //  _homeActivity.fl_content_container = (android.widget.FrameLayout)(_homeActivity.findViewById(2131296467));}
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
            String text = "{0}.{1} = ({2})({3}.findViewById({4}));\n";
            builder.addCode(MessageFormat.format(text, parameter, fieldName, fieldType, parameter, String.valueOf(viewId)));
//            builder.addCode(MessageFormat.format(text, parameter, fieldName, fieldType, parameter, String.valueOf(viewId)));
//            builder.addCode(MessageFormat.format(text, parameter, fieldName, fieldType, parameter, String.valueOf(viewId)));
//            builder.addCode(MessageFormat.format(text, parameter, fieldName, fieldType, parameter, String.valueOf(viewId)));
//            builder.addCode(MessageFormat.format(text, parameter, fieldName, fieldType, parameter, String.valueOf(viewId)));
        }
        return builder.build();
    }

    private String toLowerCaseFirstChar(String str) {
        String first = str.charAt(0) + "";
        return first.toLowerCase() + str.substring(1);
    }

}
