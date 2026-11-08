package b.b.a.d;

import b.b.a.k.v;
import com.bytedance.common.utility.NetworkUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    public final b f1049a;

    /* renamed from: b, reason: collision with root package name */
    public int f1050b;

    /* renamed from: c, reason: collision with root package name */
    public volatile boolean f1051c;
    public long d;
    public boolean e;

    public a(b bVar) {
        this.f1049a = bVar;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public abstract boolean b();

    public abstract long c();

    public abstract long[] d();

    public abstract boolean e();

    public abstract String f();

    /* JADX WARN: Multi-variable type inference failed */
    public <T extends a> T g() {
        StringBuilder sbA = b.a.a.a.a.a("setImmediately, ");
        sbA.append(f());
        v.a(sbA.toString());
        this.f1051c = true;
        return this;
    }

    public final long h() {
        if (b() && !NetworkUtils.isNetworkAvailableFast(this.f1049a.f1054c)) {
            v.a("checkWorkTime, 0");
            return System.currentTimeMillis() + 5000;
        }
        long jC = 0;
        if (this.f1051c) {
            this.d = 0L;
            this.f1051c = false;
        } else {
            int i = this.f1050b;
            if (i > 0) {
                long[] jArrD = d();
                jC = jArrD[(i - 1) % jArrD.length];
            } else {
                jC = c();
            }
        }
        return this.d + jC;
    }

    public final long a() {
        String str;
        long jH = h();
        if (jH > System.currentTimeMillis()) {
            return jH;
        }
        try {
            try {
                boolean zE = e();
                this.d = System.currentTimeMillis();
                if (zE) {
                    this.f1050b = 0;
                } else {
                    this.f1050b++;
                }
                str = f() + " worked:" + zE;
            } catch (Exception e) {
                v.a(e);
                this.d = System.currentTimeMillis();
                this.f1050b++;
                str = f() + " worked:false";
            }
            v.a(str, (Throwable) null);
            return h();
        } catch (Throwable th) {
            this.d = System.currentTimeMillis();
            this.f1050b++;
            v.a(f() + " worked:false", (Throwable) null);
            throw th;
        }
    }

    public a(b bVar, long j) {
        this.f1049a = bVar;
        this.d = j;
    }
}
