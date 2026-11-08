package bykvm_19do.bykvm_19do.bykvm_19do;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class o0<T> {

    /* renamed from: a, reason: collision with root package name */
    private volatile T f1364a;

    protected abstract T a(Object... objArr);

    public final T b(Object... objArr) {
        if (this.f1364a == null) {
            synchronized (this) {
                if (this.f1364a == null) {
                    this.f1364a = a(objArr);
                }
            }
        }
        return this.f1364a;
    }
}
