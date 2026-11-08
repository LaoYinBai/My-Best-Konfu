package com.bytedance.push.settings.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public @interface a {
    String a();

    boolean b() default false;
}
