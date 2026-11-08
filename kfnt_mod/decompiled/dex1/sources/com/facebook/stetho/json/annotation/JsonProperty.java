package com.facebook.stetho.json.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public @interface JsonProperty {
    boolean required() default false;
}
