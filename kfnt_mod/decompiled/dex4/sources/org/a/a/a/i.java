package org.a.a.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE})
@d(a = "RegExp")
@Retention(RetentionPolicy.CLASS)
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public @interface i {
    String a() default "";

    String b() default "";
}
