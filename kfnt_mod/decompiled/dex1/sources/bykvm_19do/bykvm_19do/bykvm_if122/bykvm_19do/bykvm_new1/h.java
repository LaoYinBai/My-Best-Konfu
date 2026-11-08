package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1;

import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h extends d {
    private long d;
    private String e;
    private long f;

    public h(String str, String str2, int i, String str3, String str4) {
        super(str, str2, i);
        this.f = 0L;
        this.d = 0L;
        try {
            this.d = Long.parseLong(str3);
        } catch (Exception unused) {
        }
        this.e = str4;
    }

    public void a(long j) {
        this.f = j;
    }

    public long e() {
        return this.d;
    }

    public String f() {
        return this.e;
    }

    public long g() {
        return this.f;
    }

    public boolean h() {
        return this.f != 0;
    }

    public String toString() {
        return "BaseIntervalBean{waterfallId='" + this.f1892a + "', showRulesVersion='" + this.f1893b + "', timingMode=" + this.f1894c + "}IntervalPacingBean{pacing=" + this.d + ", pacingRuleId='" + this.e + "', effectiveTime=" + this.f + '}';
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1.d
    public boolean a() {
        return (TextUtils.isEmpty(this.e) || this.d == 0) ? false : true;
    }

    public h(String str, String str2, int i, String str3, String str4, String str5) {
        this(str, str2, i, str3, str4);
        this.f = 0L;
        try {
            this.f = Long.parseLong(str5);
        } catch (Exception unused) {
        }
    }
}
