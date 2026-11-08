package b.b.a.k;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class a<T> {

    /* renamed from: a, reason: collision with root package name */
    public volatile T f1123a;

    public abstract T a(Object... objArr);

    public final T b(Object... objArr) {
        if (this.f1123a == null) {
            synchronized (this) {
                if (this.f1123a == null) {
                    this.f1123a = a(objArr);
                }
            }
        }
        return this.f1123a;
    }
}
