package com.bytedance.pangle.log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    private String f4650a;

    /* renamed from: b, reason: collision with root package name */
    private String f4651b;

    /* renamed from: c, reason: collision with root package name */
    private String f4652c;
    private long d;
    private long e;

    public static c a(String str, String str2, String str3) {
        return new c(str, str2, str3);
    }

    private c(String str, String str2, String str3) {
        this.f4650a = str;
        this.f4651b = str2;
        this.f4652c = str3;
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.e = jCurrentTimeMillis;
        this.d = jCurrentTimeMillis;
        ZeusLogger.i(this.f4650a, this.f4651b + String.format(" watcher[%s]-start", str3));
    }

    public final long a(String str) {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.e;
        ZeusLogger.i(this.f4650a, this.f4651b + String.format(" watcher[%s]-%s cost=%s", this.f4652c, str, Long.valueOf(jCurrentTimeMillis)));
        this.e = System.currentTimeMillis();
        return jCurrentTimeMillis;
    }

    public final long b(String str) {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.e;
        long jCurrentTimeMillis2 = System.currentTimeMillis() - this.d;
        ZeusLogger.i(this.f4650a, this.f4651b + String.format(" watcher[%s]-%s cost=%s, total=%s", this.f4652c, str, Long.valueOf(jCurrentTimeMillis), Long.valueOf(jCurrentTimeMillis2)));
        return jCurrentTimeMillis2;
    }

    public final long a() {
        return System.currentTimeMillis() - this.d;
    }
}
