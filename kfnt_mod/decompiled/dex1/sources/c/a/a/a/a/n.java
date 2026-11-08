package c.a.a.a.a;

import android.os.IBinder;
import com.bytedance.JProtect;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@JProtect
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class n implements InvocationHandler {

    /* renamed from: a, reason: collision with root package name */
    private Object f2108a;

    public n(Object obj) {
        this.f2108a = obj;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        String name = method.getName();
        if (((name.hashCode() == 1903606739 && name.equals("onServiceConnected")) ? (char) 0 : (char) 65535) == 0) {
            try {
                IBinder iBinder = (IBinder) objArr[1];
                String interfaceDescriptor = iBinder.getInterfaceDescriptor();
                if (interfaceDescriptor != null && interfaceDescriptor.equalsIgnoreCase(bo.d)) {
                    objArr[1] = (IBinder) Proxy.newProxyInstance(iBinder.getClass().getClassLoader(), new Class[]{IBinder.class}, new t(iBinder));
                }
            } catch (Throwable unused) {
            }
        }
        return method.invoke(this.f2108a, objArr);
    }
}
