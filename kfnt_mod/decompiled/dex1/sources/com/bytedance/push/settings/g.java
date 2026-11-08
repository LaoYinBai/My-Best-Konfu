package com.bytedance.push.settings;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class g {

    /* renamed from: a, reason: collision with root package name */
    private final ConcurrentHashMap<Class<? extends ISettings>, ISettings> f4935a = new ConcurrentHashMap<>();

    /* renamed from: b, reason: collision with root package name */
    private final ConcurrentHashMap<Class<? extends ISettings>, Object> f4936b = new ConcurrentHashMap<>();

    g() {
    }

    public <T extends ISettings> T a(Context context, Class<T> cls) {
        Object obj;
        T t;
        T t2 = (T) this.f4935a.get(cls);
        if (t2 == null) {
            synchronized (this.f4936b) {
                obj = this.f4936b.containsKey(cls) ? this.f4936b.get(cls) : null;
                if (obj == null) {
                    obj = new Object();
                    this.f4936b.put(cls, obj);
                }
            }
            synchronized (obj) {
                ISettings iSettings = this.f4935a.get(cls);
                if (iSettings == null) {
                    t = (T) b(context, cls);
                    if (t != null) {
                        this.f4935a.put(cls, t);
                    }
                } else {
                    t = (T) iSettings;
                }
            }
            return t;
        }
        if (this.f4936b.containsKey(cls)) {
            this.f4936b.remove(cls);
        }
        return t2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T extends ISettings> T b(Context context, Class<T> cls) {
        i storageFactory = SettingsManager.getStorageFactory();
        T t = (T) h.a(cls, storageFactory);
        InvocationTargetException invocationTargetException = null;
        if (t != null) {
            t.updateSettings(context, null);
            return t;
        }
        try {
            com.bytedance.push.settings.a.a aVar = (com.bytedance.push.settings.a.a) cls.getAnnotation(com.bytedance.push.settings.a.a.class);
            if (aVar != null) {
                return (T) a(context, cls, storageFactory.a(context, aVar.b(), aVar.a()));
            }
        } catch (ClassNotFoundException e) {
            ClassNotFoundException classNotFoundException = e;
            classNotFoundException.printStackTrace();
            invocationTargetException = classNotFoundException;
        } catch (IllegalAccessException e2) {
            IllegalAccessException illegalAccessException = e2;
            illegalAccessException.printStackTrace();
            invocationTargetException = illegalAccessException;
        } catch (InstantiationException e3) {
            InstantiationException instantiationException = e3;
            instantiationException.printStackTrace();
            invocationTargetException = instantiationException;
        } catch (NoSuchMethodException e4) {
            NoSuchMethodException noSuchMethodException = e4;
            noSuchMethodException.printStackTrace();
            invocationTargetException = noSuchMethodException;
        } catch (InvocationTargetException e5) {
            InvocationTargetException invocationTargetException2 = e5;
            invocationTargetException2.printStackTrace();
            invocationTargetException = invocationTargetException2;
        }
        if (invocationTargetException != null) {
            throw new IllegalStateException("获取settings实例失败，检查warning日志确认问题", invocationTargetException);
        }
        throw new IllegalStateException("获取settings实例失败，检查warning日志确认问题");
    }

    private static <T extends ISettings> ISettings a(Context context, Class<T> cls, com.bytedance.push.settings.storage.i iVar) throws IllegalAccessException, NoSuchMethodException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        ISettings iSettings = (ISettings) Class.forName(cls.getName() + "$$SettingImpl", true, cls.getClassLoader()).getConstructor(com.bytedance.push.settings.storage.i.class).newInstance(iVar);
        iSettings.updateSettings(context, null);
        return iSettings;
    }
}
