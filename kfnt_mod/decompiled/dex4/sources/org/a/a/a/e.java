package org.a.a.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public @interface e {
    long[] a() default {};

    String[] b() default {};

    long[] c() default {};

    Class d() default void.class;

    Class e() default void.class;
}
