package java_basic_classes;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * @Author: Rita
 */
public class AnnotationTest {
}
/**
 * @author J&C
 */
@Inherited
@Repeatable(MyAnnotations.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE,TYPE_PARAMETER,TYPE_USE})
@interface MyAnnotation {

    String value() default "hello";
}

/**
 * @author J&C
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@interface MyAnnotations {

    MyAnnotation[] value();
}