package com.bytedance.b.a.b;

import android.content.Context;
import android.text.TextUtils;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class a {

    /* renamed from: a, reason: collision with root package name */
    private String f3649a;

    /* renamed from: b, reason: collision with root package name */
    private c f3650b;

    /* renamed from: c, reason: collision with root package name */
    protected String f3651c;
    protected b d;
    protected d e;
    private long f;
    private long g;

    /* renamed from: com.bytedance.b.a.b.a$a, reason: collision with other inner class name */
    public static abstract class AbstractC0104a implements b {
        @Override // com.bytedance.b.a.b.a.b
        public int c() {
            return 4;
        }

        @Override // com.bytedance.b.a.b.a.b
        public long d() {
            return 15000L;
        }

        @Override // com.bytedance.b.a.b.a.b
        public String e() {
            return null;
        }

        @Override // com.bytedance.b.a.b.a.b
        public long f() {
            return 604800000L;
        }
    }

    public interface b {
        String a();

        List<String> b();

        int c();

        long d();

        String e();

        long f();
    }

    public interface c {
        boolean a();

        int b();

        long c();

        boolean d();

        long e();
    }

    protected void a(byte[] bArr, boolean z) {
    }

    protected abstract boolean a(String str, byte[] bArr);

    public long a() {
        return this.f;
    }

    public void a(long j) {
        this.f = j;
    }

    public long b() {
        return this.g;
    }

    public void b(long j) {
        this.g = j;
    }

    public a(Context context, b bVar) {
        this.d = bVar;
        if (this.d == null) {
            throw new IllegalArgumentException("config is null.");
        }
        this.f3651c = bVar.a();
        if (TextUtils.isEmpty(this.f3651c)) {
            throw new IllegalArgumentException("type is empty.");
        }
        this.e = d.a(context);
        this.e.a(this.f3651c, this);
    }

    public a(Context context, b bVar, c cVar) {
        this.d = bVar;
        this.f3650b = cVar;
        if (this.d == null) {
            throw new IllegalArgumentException("config is null.");
        }
        if (this.f3650b == null) {
            throw new IllegalArgumentException("responseConfig is null");
        }
        this.f3651c = bVar.a();
        if (TextUtils.isEmpty(this.f3651c)) {
            throw new IllegalArgumentException("type is empty.");
        }
        this.e = d.a(context);
        this.e.a(this.f3651c, this);
    }

    public boolean a(String str) {
        return a(com.bytedance.b.a.b.c.a(str));
    }

    public boolean a(byte[] bArr) {
        return this.e.a(this.f3651c, bArr);
    }

    public void a(b bVar) {
        if (bVar == null) {
            return;
        }
        this.d = bVar;
    }

    String c() {
        return this.f3651c;
    }

    b d() {
        return this.d;
    }

    c e() {
        return this.f3650b;
    }

    String f() {
        return this.f3649a;
    }

    public void b(String str) {
        this.f3649a = str;
    }
}
