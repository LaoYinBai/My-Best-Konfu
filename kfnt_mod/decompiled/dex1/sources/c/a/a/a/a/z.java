package c.a.a.a.a;

import android.content.Intent;
import android.text.TextUtils;
import com.bytedance.JProtect;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@JProtect
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class z implements InvocationHandler {

    /* renamed from: a, reason: collision with root package name */
    private Object f2126a;

    public z(Object obj) {
        this.f2126a = obj;
    }

    public static Object a(Intent intent, Object obj) {
        try {
            String action = intent.getAction();
            if (!TextUtils.isEmpty(action) && action.contains(bo.f2093c)) {
                ClassLoader classLoader = obj.getClass().getClassLoader();
                return Proxy.newProxyInstance(classLoader, new Class[]{classLoader.loadClass("android.app.IServiceConnection")}, new q(obj));
            }
        } catch (Throwable unused) {
        }
        return obj;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        Intent intent;
        Intent intent2 = null;
        if ("startActivity".equals(method.getName()) || "startActivityForResult".equals(method.getName())) {
            int i = 0;
            while (true) {
                if (i >= objArr.length) {
                    intent = null;
                    break;
                }
                if (objArr[i] instanceof Intent) {
                    intent = (Intent) objArr[i];
                    break;
                }
                i++;
            }
            k.a(intent, ar.a());
        }
        if ("bindService".equals(method.getName()) || "bindIsolatedService".equals(method.getName())) {
            int i2 = 4;
            for (int i3 = 0; i3 < objArr.length; i3++) {
                Object obj2 = objArr[i3];
                if (obj2 != null) {
                    if (obj2 instanceof Intent) {
                        intent2 = (Intent) obj2;
                    }
                    if (obj2.getClass().getName().contains("ServiceConnection")) {
                        i2 = i3;
                    }
                }
            }
            Object objA = a(intent2, objArr[i2]);
            if (objA != null) {
                objArr[i2] = objA;
            }
        }
        return method.invoke(this.f2126a, objArr);
    }
}
