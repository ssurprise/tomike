package com.skx.tomike.apt_processor;

import com.skx.tomike.apt_annotation.Coder;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;


@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({
        "com.skx.tomike.apt_annotation.Coder"
})
public class CoderProcessor extends AbstractProcessor {

    private final String SUFFIX = "$WrmRequestInfo";

    private Messager mMessager;
    private Filer mFiler;
    private Types mTypeUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mMessager = processingEnvironment.getMessager();
        mFiler = processingEnvironment.getFiler();
        mTypeUtils = processingEnvironment.getTypeUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> elementSet = roundEnvironment.getElementsAnnotatedWith(Coder.class);
        for (Element e : elementSet) {
            Coder coder = e.getAnnotation(Coder.class);

            TypeElement clazz = (TypeElement) e.getEnclosingElement();
            try {
                generateCode(e, coder, clazz);
            } catch (IOException x) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, x.toString());
                return false;
            }
        }
        return false;
    }

    private void generateCode(Element e, Coder ca, TypeElement clazz) throws IOException {
        JavaFileObject f = mFiler.createSourceFile(clazz.getQualifiedName() + SUFFIX);
        mMessager.printMessage(Diagnostic.Kind.NOTE, "Creating " + f.toUri());
        Writer w = f.openWriter();
        try {
            String pack = clazz.getQualifiedName().toString();
            PrintWriter pw = new PrintWriter(w);
            pw.println("package " + pack.substring(0, pack.lastIndexOf('.')) + ";"); //create package element
            pw.println("\n class " + clazz.getSimpleName() + "Autogenerate {");//create class element
            pw.println("\n    protected " + clazz.getSimpleName() + "Autogenerate() {}");//create class construction
            pw.println("    protected final void message() {");//create method
            pw.println("\n//" + e);
            pw.println("//" + ca);
            pw.println("\n        System.out.println(\"author:" + ca.author() + "\");");
            pw.println("\n        System.out.println(\"date:" + ca.date() + "\");");
            pw.println("    }");
            pw.println("}");
            pw.flush();
        } finally {
            w.close();
        }
    }
}
