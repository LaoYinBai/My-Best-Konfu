package org.a.a.a;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.CLASS)
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public @interface a {

    @org.b.a.c
    public static final String a = "The method argument (if parameter was annotated) or this container (if instance method was annotated)";

    @org.b.a.c
    public static final String b = "this";

    @org.b.a.c
    public static final String c = "This container (if the parameter was annotated) or the return value (if instance method was annotated)";

    @org.b.a.c
    public static final String d = "The return value of this method";

    @org.b.a.c
    public static final String e = "this";

    String a() default "The method argument (if parameter was annotated) or this container (if instance method was annotated)";

    boolean b() default false;

    String c() default "This container (if the parameter was annotated) or the return value (if instance method was annotated)";

    boolean d() default false;
}
