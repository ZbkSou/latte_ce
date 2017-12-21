package com.example.compiler;

import com.example.annotations.AppRegisterGenerator;
import com.example.annotations.EntryGenerator;
import com.example.annotations.PayEntryGenerator;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

/**
 * User: bkzhou
 * Date: 2017/12/19
 * Description:
 */
@AutoService(Processor.class)
public class LatteProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        generateEntryCode(roundEnv);
        generatePayEntryCode(roundEnv);
        generateAppRegisterEntryCode(roundEnv);
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportAnnotations = getSupportAnnotations();
        for (Class<? extends Annotation> annotation : supportAnnotations) {
            types.add(annotation.getCanonicalName());
        }
        return types;

    }

    private Set<Class<? extends Annotation>> getSupportAnnotations() {
        final Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(EntryGenerator.class);
        annotations.add(PayEntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        return annotations;
    }

    /**
     * 扫描注解
     * @param env
     * @param annotation
     * @param visitor
     */
    private void scan(RoundEnvironment env,
                      Class<? extends Annotation> annotation,
                      AnnotationValueVisitor visitor) {
        for (Element typeElement : env.getElementsAnnotatedWith(annotation)) {
            final List<? extends AnnotationMirror> annotationMirrors =
                typeElement.getAnnotationMirrors();
            for (AnnotationMirror annotationMirror :annotationMirrors) {
                final Map<? extends ExecutableElement,?extends AnnotationValue> elementValues
                     = annotationMirror.getElementValues();
                for(Map.Entry<? extends ExecutableElement,? extends AnnotationValue> entry:elementValues.entrySet()){
                    entry.getValue().accept(visitor,null);
                }
            }
        }
    }
    private void generateEntryCode(RoundEnvironment env){
        final EntryVisitor entryVisitor = new EntryVisitor();
        entryVisitor.setFiler(processingEnv.getFiler());
        scan(env,EntryGenerator.class,entryVisitor);
    }
    private void generatePayEntryCode(RoundEnvironment env){
        final PayEntryVisitor payEntryVisitor = new PayEntryVisitor();
        payEntryVisitor.setFiler(processingEnv.getFiler());
        scan(env,PayEntryGenerator.class,payEntryVisitor);
    }
    private void generateAppRegisterEntryCode(RoundEnvironment env){
        final AppRegisterVisitor appRegisterVisitor = new AppRegisterVisitor();
        appRegisterVisitor.setFiler(processingEnv.getFiler());
        scan(env,AppRegisterGenerator.class,appRegisterVisitor);
    }
//    private static void generateHelloworld() throws IOException {
//        MethodSpec main = MethodSpec.methodBuilder("main") //main代表方法名
//            .addModifiers(Modifier.PUBLIC,Modifier.STATIC)//Modifier 修饰的关键字
//            .addParameter(String[].class, "args") //添加string[]类型的名为args的参数
//            .addStatement("$T.out.println($S)", System.class,"Hello World")//添加代码，这里$T和$S后面会讲，这里其实就是添加了System,out.println("Hello World");
//            .build();
//        TypeSpec typeSpec = TypeSpec.classBuilder("HelloWorld")//HelloWorld是类名
//            .addModifiers(Modifier.FINAL,Modifier.PUBLIC)
//            .addMethod(main)  //在类中添加方法
//            .build();
//        JavaFile javaFile = JavaFile.builder("com.example.helloworld", typeSpec)
//            .build();
//        javaFile.writeTo(System.out);
//    }
//
//    public static void  main (String [] args){
//        try {
//            generateHelloworld();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
