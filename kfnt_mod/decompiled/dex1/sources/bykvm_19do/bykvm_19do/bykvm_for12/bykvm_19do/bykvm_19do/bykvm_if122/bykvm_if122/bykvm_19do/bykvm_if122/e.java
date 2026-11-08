package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_if122;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class e extends RuntimeException {

    /* renamed from: b, reason: collision with root package name */
    private static final Method f1554b;

    /* renamed from: a, reason: collision with root package name */
    private IOException f1555a;

    static {
        Method declaredMethod;
        try {
            declaredMethod = Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class);
        } catch (Exception unused) {
            declaredMethod = null;
        }
        f1554b = declaredMethod;
    }

    public e(IOException iOException) {
        super(iOException);
        this.f1555a = iOException;
    }

    public IOException a() {
        return this.f1555a;
    }

    public void a(IOException iOException) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        a(iOException, this.f1555a);
        this.f1555a = iOException;
    }

    private void a(IOException iOException, IOException iOException2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = f1554b;
        if (method != null) {
            try {
                method.invoke(iOException, iOException2);
            } catch (IllegalAccessException | InvocationTargetException unused) {
            }
        }
    }
}
