package com.example.compiler;

import com.example.annotations.AppRegisterGenerator;
import com.example.annotations.EntryGenerator;
import com.example.annotations.PayEntryGenerator;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

/**
 * User: bkzhou
 * Date: 2017/12/19
 * Description:
 */
public class LatteProcessor extends AbstractProcessor{
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String>types = new LinkedHashSet<>();
        final Set<Class<?extends Annotation>> supportAnnotations = getSupportAnnotations();
        for(Class<?extends Annotation> annotation:supportAnnotations){
            types.add(annotation.getCanonicalName());
        }
        return types;

    }

    private Set<Class<? extends Annotation>> getSupportAnnotations(){
        final Set<Class<?extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(EntryGenerator.class);
        annotations.add(PayEntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        return annotations;
    }
    private static void generateHelloworld() throws IOException {
        MethodSpec main = MethodSpec.methodBuilder("main") //main代表方法名
            .addModifiers(Modifier.PUBLIC,Modifier.STATIC)//Modifier 修饰的关键字
            .addParameter(String[].class, "args") //添加string[]类型的名为args的参数
            .addStatement("$T.out.println($S)", System.class,"Hello World")//添加代码，这里$T和$S后面会讲，这里其实就是添加了System,out.println("Hello World");
            .build();
        TypeSpec typeSpec = TypeSpec.classBuilder("HelloWorld")//HelloWorld是类名
            .addModifiers(Modifier.FINAL,Modifier.PUBLIC)
            .addMethod(main)  //在类中添加方法
            .build();
        JavaFile javaFile = JavaFile.builder("com.example.helloworld", typeSpec)
            .build();
        javaFile.writeTo(System.out);
    }

    public static void  main (String [] args){
        try {
            generateHelloworld();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
