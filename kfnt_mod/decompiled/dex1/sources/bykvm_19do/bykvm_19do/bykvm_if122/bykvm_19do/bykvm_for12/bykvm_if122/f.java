package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_for12.bykvm_if122;

import com.bytedance.msdk.api.AdSlot;
import com.bytedance.msdk.base.TTBaseAd;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f implements Comparable<f> {

    /* renamed from: a, reason: collision with root package name */
    public TTBaseAd f1866a;

    /* renamed from: b, reason: collision with root package name */
    public bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d f1867b;

    /* renamed from: c, reason: collision with root package name */
    private long f1868c;
    private long d;
    private boolean e = false;
    private AdSlot f;

    public f(TTBaseAd tTBaseAd, bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.d dVar, long j, long j2, AdSlot adSlot) {
        this.f1866a = tTBaseAd;
        this.f1867b = dVar;
        this.f1868c = j;
        this.d = j2;
        this.f = adSlot;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(f fVar) {
        return this.f1866a.compareTo(fVar.f1866a);
    }

    public long b() {
        return this.d;
    }

    public long c() {
        return this.f1868c;
    }

    public boolean d() {
        return this.e;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public AdSlot a() {
        return this.f;
    }
}
