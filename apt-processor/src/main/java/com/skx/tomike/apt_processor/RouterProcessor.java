package com.skx.tomike.apt_processor;

import com.skx.tomike.apt_annotation.Route;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * 描述 : Route 注解处理器
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/1/19 6:01 PM
 */
//@AutoService(Processor.class)
@SupportedAnnotationTypes({"com.skx.tomike.apt_annotation.Route"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class RouterProcessor extends AbstractProcessor {

    private Elements mElementsUtils;// Element处理工具类，(类、函数、属性都是Element)

    /**
     * 此处的TypeElement就是Activity。Integer 对应绑定的id。VariableElement为绑定的view
     */
    private final Map<TypeElement, String> mTypeElementLabel = new HashMap<>();

    // 初始化。可以得到ProcessingEnvironment，ProcessingEnvironment提供很多有用的工具类Elements, Types 和 Filer
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mElementsUtils = processingEnv.getElementUtils();
        Filer filer = processingEnv.getFiler();
        Messager messager = processingEnv.getMessager();
    }

    // 指定此注解处理器的目标注解对象，需要自己添加。
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> supportTypes = new LinkedHashSet<>();
        supportTypes.add(Route.class.getCanonicalName());
        return supportTypes; //将要支持的注解放入其中
    }

    // 用于指示注释处理器支持的最新源版本的注释，通常这里返回SourceVersion.latestSupported()
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();// 表示支持最新的Java版本
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // 扫描整个工程，找出含有 Route 注解的元素(包括类)
        Set<? extends Element> elementSet = roundEnvironment.getElementsAnnotatedWith(Route.class);

        return false;
    }

}
