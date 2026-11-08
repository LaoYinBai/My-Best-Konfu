package com.alipay.android.phone.mrpc.core;

import android.os.Looper;
import com.alipay.mobile.framework.service.annotation.OperationType;
import com.alipay.mobile.framework.service.annotation.ResetCookie;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class z {

    /* renamed from: a, reason: collision with root package name */
    private static final ThreadLocal<Object> f2483a = new ThreadLocal<>();

    /* renamed from: b, reason: collision with root package name */
    private static final ThreadLocal<Map<String, Object>> f2484b = new ThreadLocal<>();

    /* renamed from: c, reason: collision with root package name */
    private byte f2485c = 0;
    private AtomicInteger d = new AtomicInteger();
    private x e;

    public z(x xVar) {
        this.e = xVar;
    }

    public final Object a(Method method, Object[] objArr) {
        if (Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalThreadStateException("can't in main thread call rpc .");
        }
        OperationType operationType = (OperationType) method.getAnnotation(OperationType.class);
        boolean z = method.getAnnotation(ResetCookie.class) != null;
        Type genericReturnType = method.getGenericReturnType();
        method.getAnnotations();
        f2483a.set(null);
        f2484b.set(null);
        if (operationType == null) {
            throw new IllegalStateException("OperationType must be set.");
        }
        String strValue = operationType.value();
        int iIncrementAndGet = this.d.incrementAndGet();
        try {
            if (this.f2485c == 0) {
                com.alipay.android.phone.mrpc.core.a.e eVar = new com.alipay.android.phone.mrpc.core.a.e(iIncrementAndGet, strValue, objArr);
                if (f2484b.get() != null) {
                    eVar.a(f2484b.get());
                }
                byte[] bArr = (byte[]) new j(this.e.a(), method, iIncrementAndGet, strValue, eVar.a(), z).a();
                f2484b.set(null);
                Object objA = new com.alipay.android.phone.mrpc.core.a.d(genericReturnType, bArr).a();
                if (genericReturnType != Void.TYPE) {
                    f2483a.set(objA);
                }
            }
            return f2483a.get();
        } catch (RpcException e) {
            e.setOperationType(strValue);
            throw e;
        }
    }
}
