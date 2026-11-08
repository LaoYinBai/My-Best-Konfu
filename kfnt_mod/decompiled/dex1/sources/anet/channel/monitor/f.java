package anet.channel.monitor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {

    /* renamed from: b, reason: collision with root package name */
    protected long f473b;

    /* renamed from: c, reason: collision with root package name */
    private final double f474c = 40.0d;

    /* renamed from: a, reason: collision with root package name */
    boolean f472a = false;
    private boolean d = true;

    public int a() {
        return 0;
    }

    public boolean a(double d) {
        return d < 40.0d;
    }

    protected final boolean b() {
        if (!this.d) {
            return false;
        }
        if (System.currentTimeMillis() - this.f473b <= a() * 1000) {
            return true;
        }
        this.d = false;
        return false;
    }
}
