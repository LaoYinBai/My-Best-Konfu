package b.b.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class b<T> {

    /* renamed from: a, reason: collision with root package name */
    public volatile T f1157a;

    public abstract T a(Object... objArr);

    public final T b(Object... objArr) {
        if (this.f1157a == null) {
            synchronized (this) {
                if (this.f1157a == null) {
                    this.f1157a = a(objArr);
                }
            }
        }
        return this.f1157a;
    }
}
