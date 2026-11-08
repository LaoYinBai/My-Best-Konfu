package b.a.c;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class e extends RuntimeException {

    /* renamed from: a, reason: collision with root package name */
    private static final Method f803a;

    /* renamed from: b, reason: collision with root package name */
    private IOException f804b;

    static {
        Method declaredMethod;
        try {
            declaredMethod = Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class);
        } catch (Exception unused) {
            declaredMethod = null;
        }
        f803a = declaredMethod;
    }

    public e(IOException iOException) {
        super(iOException);
        this.f804b = iOException;
    }

    public IOException a() {
        return this.f804b;
    }

    public void a(IOException iOException) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        a(iOException, this.f804b);
        this.f804b = iOException;
    }

    private void a(IOException iOException, IOException iOException2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (f803a != null) {
            try {
                f803a.invoke(iOException, iOException2);
            } catch (IllegalAccessException | InvocationTargetException unused) {
            }
        }
    }
}
