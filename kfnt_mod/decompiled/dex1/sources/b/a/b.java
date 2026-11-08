package b.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class b implements Runnable {

    /* renamed from: b, reason: collision with root package name */
    protected final String f780b;

    protected abstract void d();

    public b(String str, Object... objArr) {
        this.f780b = c.a(str, objArr);
    }

    @Override // java.lang.Runnable
    public final void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.f780b);
        try {
            d();
        } finally {
            Thread.currentThread().setName(name);
        }
    }
}
