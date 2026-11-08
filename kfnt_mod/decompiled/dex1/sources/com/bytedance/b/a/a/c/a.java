package com.bytedance.b.a.a.c;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.b.a.b.a;
import com.bytedance.b.a.c.h;
import com.bytedance.b.a.c.l;
import java.net.URL;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements b {

    /* renamed from: a, reason: collision with root package name */
    com.bytedance.b.a.b.a f3596a;

    /* renamed from: b, reason: collision with root package name */
    String f3597b;

    /* renamed from: c, reason: collision with root package name */
    int f3598c;
    volatile long d;
    boolean e;
    int f;
    Context g;
    String h;
    int i;
    volatile long j;
    volatile long k;
    String l;
    private boolean m = true;

    @Override // com.bytedance.b.a.a.c.b
    public boolean a() {
        return false;
    }

    public a(Context context, final String str) {
        this.h = str;
        this.g = context;
        this.f3596a = new com.bytedance.b.a.b.a(context.getApplicationContext(), new a.AbstractC0104a() { // from class: com.bytedance.b.a.a.c.a.1
            @Override // com.bytedance.b.a.b.a.b
            public String a() {
                return str + h.K;
            }

            @Override // com.bytedance.b.a.b.a.b
            public List<String> b() {
                return com.bytedance.b.a.a.a.c.a(str, h.K);
            }

            @Override // com.bytedance.b.a.b.a.AbstractC0104a, com.bytedance.b.a.b.a.b
            public int c() {
                return com.bytedance.b.a.a.a.c.d(str);
            }

            @Override // com.bytedance.b.a.b.a.AbstractC0104a, com.bytedance.b.a.b.a.b
            public long d() {
                return com.bytedance.b.a.a.a.c.e(str);
            }

            @Override // com.bytedance.b.a.b.a.AbstractC0104a, com.bytedance.b.a.b.a.b
            public String e() {
                List<String> listB;
                if (TextUtils.isEmpty(a.this.l) || (listB = b()) == null || listB.size() <= 0) {
                    return null;
                }
                try {
                    return "https://" + a.this.l + new URL(listB.get(0)).getPath();
                } catch (Throwable unused) {
                    return null;
                }
            }
        }, new a.c() { // from class: com.bytedance.b.a.a.c.a.2
            @Override // com.bytedance.b.a.b.a.c
            public boolean a() {
                return com.bytedance.b.a.a.a.c.g(str);
            }

            @Override // com.bytedance.b.a.b.a.c
            public int b() {
                return a.this.f3598c;
            }

            @Override // com.bytedance.b.a.b.a.c
            public long c() {
                return a.this.h();
            }

            @Override // com.bytedance.b.a.b.a.c
            public boolean d() {
                return a.this.e;
            }

            @Override // com.bytedance.b.a.b.a.c
            public long e() {
                return com.bytedance.b.a.a.a.c.f(str);
            }
        }) { // from class: com.bytedance.b.a.a.c.a.3
            @Override // com.bytedance.b.a.b.a
            protected boolean a(String str2, byte[] bArr) {
                if (d.a(str) != null) {
                    f fVarA = d.a(str).a(str2, bArr);
                    a.this.c((String) null);
                    if (fVarA == null || fVarA.f3611a <= 0) {
                        a.this.c();
                        a.this.e = true;
                    } else {
                        a.this.e = false;
                        if (fVarA.f3611a == 200 && fVarA.f3612b != null) {
                            if ("success".equals(fVarA.f3612b.opt("message"))) {
                                a.this.d();
                                String strOptString = fVarA.f3612b.optString(g.o);
                                long jOptLong = fVarA.f3612b.optLong(g.p);
                                if (!TextUtils.isEmpty(strOptString)) {
                                    a.this.c(strOptString);
                                }
                                if (jOptLong > 0) {
                                    a.this.a(jOptLong);
                                }
                                return true;
                            }
                            boolean zEquals = g.m.equals(fVarA.f3612b.opt("message"));
                            boolean zEquals2 = g.n.equals(fVarA.f3612b.opt("message"));
                            String strOptString2 = fVarA.f3612b.optString(g.o);
                            long jOptLong2 = fVarA.f3612b.optLong(g.p);
                            if (!TextUtils.isEmpty(strOptString2)) {
                                a.this.c(strOptString2);
                            }
                            if (jOptLong2 > 0) {
                                a.this.a(jOptLong2);
                            }
                            if (zEquals) {
                                a.this.e();
                            } else {
                                a.this.f();
                            }
                            if (zEquals2) {
                                a.this.g();
                            }
                            return false;
                        }
                        if (500 <= fVarA.f3611a && fVarA.f3611a <= 600) {
                            a.this.b();
                            return false;
                        }
                    }
                }
                return false;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.m) {
            if (this.f == 0) {
                this.d = 300000L;
                this.f++;
            } else if (this.f == 1) {
                this.d = 900000L;
                this.f++;
            } else if (this.f == 2) {
                this.d = 1800000L;
                this.f++;
            } else {
                this.d = 1800000L;
                this.f++;
            }
            l.a(this.h).b(this.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.m) {
            if (this.i == 0) {
                this.j = g.e;
                this.i++;
            } else if (this.i == 1) {
                this.j = 60000L;
                this.i++;
            } else if (this.i == 2) {
                this.j = g.g;
                this.i++;
            } else if (this.i == 3) {
                this.j = g.h;
                this.i++;
            } else {
                this.j = 300000L;
                this.i++;
            }
            l.a(this.h).b(this.j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.m) {
            l.a(this.h).m();
            l.a(this.h).b(false);
            this.f = 0;
            this.d = 0L;
            this.i = 0;
            this.j = 0L;
            this.k = 0L;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.m) {
            b();
            l.a(this.h).b(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.m) {
            l.a(this.h).b(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.m) {
            b();
            l.a(this.h).b(true);
            l.a(this.h).o();
            l.a(this.h).n();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        if (this.m) {
            this.l = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j) {
        if (this.m) {
            this.k = j * 1000;
            l.a(this.h).b(this.k);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long h() {
        if (!this.m) {
            return 0L;
        }
        long j = this.d > this.j ? this.d : this.j;
        return j > this.k ? j : this.k;
    }

    @Override // com.bytedance.b.a.a.c.b
    public boolean a(String str) {
        return this.f3596a.a(str);
    }

    public void b(String str) {
        this.f3596a.b(str);
    }
}
