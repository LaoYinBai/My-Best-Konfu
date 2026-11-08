package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private int f1895a;

    /* renamed from: b, reason: collision with root package name */
    private long f1896b;

    /* renamed from: c, reason: collision with root package name */
    private String f1897c;
    private long d;
    private int e;

    public void a(e eVar) {
        this.f1895a = eVar.c();
        this.f1896b = eVar.e();
        this.f1897c = eVar.d();
        this.d = eVar.b();
        this.e = eVar.a();
    }

    public void b(int i) {
        this.f1895a = i;
    }

    public int c() {
        return this.f1895a;
    }

    public String d() {
        return this.f1897c;
    }

    public long e() {
        return this.f1896b;
    }

    public String toString() {
        return "FreqctlBean{waterfallShowFreq=" + this.f1895a + ", waterfallShowSpan=" + this.f1896b + ", waterfallShowRuleId='" + this.f1897c + "', effectiveTime=" + this.d + ", count=" + this.e + '}';
    }

    public void b(long j) {
        this.f1896b = j;
    }

    public long b() {
        return this.d;
    }

    public void a(String str) {
        this.f1897c = str;
    }

    public int a() {
        return this.e;
    }

    public void a(long j) {
        this.d = j;
    }

    public void a(int i) {
        this.e = i;
    }
}
