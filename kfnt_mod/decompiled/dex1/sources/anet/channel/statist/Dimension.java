package anet.channel.statist;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public @interface Dimension {
    String name() default "";
}
