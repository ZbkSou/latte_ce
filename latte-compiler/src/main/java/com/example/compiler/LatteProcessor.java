package com.example.compiler;

import com.example.annotations.AppRegisterGenerator;
import com.example.annotations.EntryGenerator;
import com.example.annotations.PayEntryGenerator;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
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
    public static void  main {

    }
}
