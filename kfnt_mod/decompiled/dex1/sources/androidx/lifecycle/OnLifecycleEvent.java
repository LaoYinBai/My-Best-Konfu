package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public @interface OnLifecycleEvent {
    Lifecycle.Event value();
}
