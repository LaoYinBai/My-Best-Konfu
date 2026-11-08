package com.bytedance.push.settings;

import android.content.Context;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class f {

    /* renamed from: a, reason: collision with root package name */
    private final ConcurrentHashMap<Class<? extends ILocalSettings>, ILocalSettings> f4933a = new ConcurrentHashMap<>();

    /* renamed from: b, reason: collision with root package name */
    private final i f4934b;

    f(i iVar) {
        this.f4934b = iVar;
    }

    <T extends ILocalSettings> T a(Context context, Class<T> cls) {
        T t = (T) this.f4933a.get(cls);
        if (t == null) {
            synchronized (this) {
                t = (T) this.f4933a.get(cls);
                if (t == null) {
                    ILocalSettings iLocalSettingsB = b(context, cls);
                    this.f4933a.put(cls, iLocalSettingsB);
                    t = (T) iLocalSettingsB;
                }
            }
        }
        return t;
    }

    private <T extends ILocalSettings> T b(Context context, Class<T> cls) throws NoSuchMethodException, SecurityException {
        i iVar = this.f4934b;
        T t = (T) h.a(cls, iVar);
        if (t != null) {
            return t;
        }
        try {
            Constructor<?> constructor = Class.forName(cls.getName() + "$$SettingImpl", true, cls.getClassLoader()).getConstructor(Context.class, com.bytedance.push.settings.storage.i.class);
            com.bytedance.push.settings.a.a aVar = (com.bytedance.push.settings.a.a) cls.getAnnotation(com.bytedance.push.settings.a.a.class);
            if (aVar != null) {
                return (T) constructor.newInstance(context, iVar.a(context, aVar.b(), aVar.a()));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InstantiationException e3) {
            e3.printStackTrace();
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
        }
        throw new IllegalStateException("获取settings实例失败，检查warning日志确认问题");
    }
}
