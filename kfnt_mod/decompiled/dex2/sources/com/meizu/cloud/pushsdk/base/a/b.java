package com.meizu.cloud.pushsdk.base.a;

import com.meizu.cloud.pushinternal.DebugLogger;
import java.lang.reflect.Constructor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private final a a;
    private final Class<?>[] b;

    b(a aVar, Class<?>... clsArr) {
        this.a = aVar;
        this.b = clsArr;
    }

    public <T> d<T> a(Object... objArr) throws NoSuchMethodException, SecurityException {
        d<T> dVar = new d<>();
        try {
            Constructor<?> declaredConstructor = this.a.a().getDeclaredConstructor(this.b);
            declaredConstructor.setAccessible(true);
            dVar.b = (T) declaredConstructor.newInstance(objArr);
            dVar.a = true;
        } catch (Exception e) {
            DebugLogger.e("ReflectConstructor", "newInstance", e);
        }
        return dVar;
    }
}
