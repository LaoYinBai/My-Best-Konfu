package org.b.a;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.CLASS)
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public @interface a {
    String a() default "";

    boolean b() default false;
}
