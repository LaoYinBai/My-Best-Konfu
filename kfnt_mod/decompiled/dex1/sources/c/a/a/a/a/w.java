package c.a.a.a.a;

import android.os.Build;
import com.bytedance.JProtect;
import com.bytedance.sdk.account.execute.ApiRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@JProtect
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class w {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f2125a = false;

    public static synchronized boolean a() {
        Class<?> cls;
        String str;
        boolean z = f2125a;
        if (z) {
            return z;
        }
        try {
            boolean z2 = false;
            if (Build.VERSION.SDK_INT > 28) {
                if (b() && c()) {
                    z2 = true;
                }
                f2125a = z2;
            } else {
                if (Build.VERSION.SDK_INT >= 26 || (Build.VERSION.SDK_INT == 25 && Build.VERSION.PREVIEW_SDK_INT > 0)) {
                    cls = Class.forName("android.app.ActivityManager");
                    str = "IActivityManagerSingleton";
                } else {
                    cls = Class.forName("android.app.ActivityManagerNative");
                    str = "gDefault";
                }
                Object objA = al.a(cls, null, str);
                Field fieldA = al.a(Class.forName("android.util.Singleton"), "mInstance");
                Object obj = fieldA.get(objA);
                Class<?> cls2 = Class.forName("android.app.IActivityManager");
                if (cls2 != null) {
                    fieldA.set(objA, Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{cls2}, new z(obj)));
                    f2125a = true;
                }
            }
        } catch (Throwable unused) {
        }
        return f2125a;
    }

    protected static boolean b() {
        try {
            Field declaredField = Class.forName("android.app.ActivityTaskManager").getDeclaredField("IActivityTaskManagerSingleton");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(null);
            if (obj != null) {
                Class<?> cls = Class.forName("android.util.Singleton");
                Field declaredField2 = cls.getDeclaredField("mInstance");
                declaredField2.setAccessible(true);
                Method declaredMethod = cls.getDeclaredMethod(ApiRequest.METHOD_GET, new Class[0]);
                declaredMethod.setAccessible(true);
                Object objInvoke = declaredMethod.invoke(obj, new Object[0]);
                if (objInvoke != null) {
                    Class<?> cls2 = Class.forName("android.app.IActivityTaskManager");
                    declaredField2.set(obj, Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{cls2}, new z(objInvoke)));
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    protected static boolean c() {
        try {
            Field declaredField = Class.forName("android.app.ActivityManager").getDeclaredField("IActivityManagerSingleton");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(null);
            if (obj != null) {
                Class<?> cls = Class.forName("android.util.Singleton");
                Field declaredField2 = cls.getDeclaredField("mInstance");
                declaredField2.setAccessible(true);
                Method declaredMethod = cls.getDeclaredMethod(ApiRequest.METHOD_GET, new Class[0]);
                declaredMethod.setAccessible(true);
                Object objInvoke = declaredMethod.invoke(obj, new Object[0]);
                if (objInvoke != null) {
                    Class<?> cls2 = Class.forName("android.app.IActivityManager");
                    declaredField2.set(obj, Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{cls2}, new z(objInvoke)));
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }
}
