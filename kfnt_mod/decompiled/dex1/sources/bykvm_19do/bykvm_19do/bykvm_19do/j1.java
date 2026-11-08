package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class j1 {
    static void a(Context context) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Class<?> cls = Class.forName("com.bun.miitmdid.core.JLibrary");
        if (cls.getDeclaredField("context").get(cls) == null) {
            cls.getDeclaredMethod("InitEntry", Context.class).invoke(cls, context);
        }
    }
}
