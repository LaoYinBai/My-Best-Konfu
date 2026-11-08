package b.a.a;

import b.ac;
import b.ae;
import b.u;
import com.bytedance.common.utility.date.DateDef;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    @Nullable
    public final ac f755a;

    /* renamed from: b, reason: collision with root package name */
    @Nullable
    public final ae f756b;

    c(ac acVar, ae aeVar) {
        this.f755a = acVar;
        this.f756b = aeVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002e, code lost:
    
        if (r3.o().e() == false) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(b.ae r3, b.ac r4) {
        /*
            int r0 = r3.c()
            r1 = 0
            switch(r0) {
                case 200: goto L30;
                case 203: goto L30;
                case 204: goto L30;
                case 300: goto L30;
                case 301: goto L30;
                case 302: goto L9;
                case 307: goto L9;
                case 308: goto L30;
                case 404: goto L30;
                case 405: goto L30;
                case 410: goto L30;
                case 414: goto L30;
                case 501: goto L30;
                default: goto L8;
            }
        L8:
            goto L46
        L9:
            java.lang.String r0 = "Expires"
            java.lang.String r0 = r3.b(r0)
            if (r0 != 0) goto L30
            b.d r0 = r3.o()
            int r0 = r0.c()
            r2 = -1
            if (r0 != r2) goto L30
            b.d r0 = r3.o()
            boolean r0 = r0.f()
            if (r0 != 0) goto L30
            b.d r0 = r3.o()
            boolean r0 = r0.e()
            if (r0 == 0) goto L46
        L30:
            b.d r3 = r3.o()
            boolean r3 = r3.b()
            if (r3 != 0) goto L45
            b.d r3 = r4.g()
            boolean r3 = r3.b()
            if (r3 != 0) goto L45
            r1 = 1
        L45:
            return r1
        L46:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.c.a(b.ae, b.ac):boolean");
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        final long f757a;

        /* renamed from: b, reason: collision with root package name */
        final ac f758b;

        /* renamed from: c, reason: collision with root package name */
        final ae f759c;
        private Date d;
        private String e;
        private Date f;
        private String g;
        private Date h;
        private long i;
        private long j;
        private String k;
        private int l;

        public a(long j, ac acVar, ae aeVar) {
            this.l = -1;
            this.f757a = j;
            this.f758b = acVar;
            this.f759c = aeVar;
            if (aeVar != null) {
                this.i = aeVar.p();
                this.j = aeVar.q();
                u uVarG = aeVar.g();
                int iA = uVarG.a();
                for (int i = 0; i < iA; i++) {
                    String strA = uVarG.a(i);
                    String strB = uVarG.b(i);
                    if ("Date".equalsIgnoreCase(strA)) {
                        this.d = b.a.d.d.a(strB);
                        this.e = strB;
                    } else if ("Expires".equalsIgnoreCase(strA)) {
                        this.h = b.a.d.d.a(strB);
                    } else if ("Last-Modified".equalsIgnoreCase(strA)) {
                        this.f = b.a.d.d.a(strB);
                        this.g = strB;
                    } else if ("ETag".equalsIgnoreCase(strA)) {
                        this.k = strB;
                    } else if ("Age".equalsIgnoreCase(strA)) {
                        this.l = b.a.d.e.b(strB, -1);
                    }
                }
            }
        }

        public c a() {
            c cVarB = b();
            return (cVarB.f755a == null || !this.f758b.g().j()) ? cVarB : new c(null, null);
        }

        private c b() {
            String str;
            String str2;
            if (this.f759c == null) {
                return new c(this.f758b, null);
            }
            if (this.f758b.h() && this.f759c.f() == null) {
                return new c(this.f758b, null);
            }
            if (!c.a(this.f759c, this.f758b)) {
                return new c(this.f758b, null);
            }
            b.d dVarG = this.f758b.g();
            if (dVarG.a() || a(this.f758b)) {
                return new c(this.f758b, null);
            }
            b.d dVarO = this.f759c.o();
            if (dVarO.l()) {
                return new c(null, this.f759c);
            }
            long jD = d();
            long jC = c();
            if (dVarG.c() != -1) {
                jC = Math.min(jC, TimeUnit.SECONDS.toMillis(dVarG.c()));
            }
            long millis = 0;
            long millis2 = dVarG.i() != -1 ? TimeUnit.SECONDS.toMillis(dVarG.i()) : 0L;
            if (!dVarO.g() && dVarG.h() != -1) {
                millis = TimeUnit.SECONDS.toMillis(dVarG.h());
            }
            if (!dVarO.a()) {
                long j = millis2 + jD;
                if (j < millis + jC) {
                    ae.a aVarI = this.f759c.i();
                    if (j >= jC) {
                        aVarI.b("Warning", "110 HttpURLConnection \"Response is stale\"");
                    }
                    if (jD > DateDef.DAY && e()) {
                        aVarI.b("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                    }
                    return new c(null, aVarI.a());
                }
            }
            if (this.k != null) {
                str = "If-None-Match";
                str2 = this.k;
            } else if (this.f != null) {
                str = "If-Modified-Since";
                str2 = this.g;
            } else if (this.d != null) {
                str = "If-Modified-Since";
                str2 = this.e;
            } else {
                return new c(this.f758b, null);
            }
            u.a aVarD = this.f758b.c().d();
            b.a.a.f750a.a(aVarD, str, str2);
            return new c(this.f758b.f().a(aVarD.a()).d(), this.f759c);
        }

        private long c() {
            long time;
            long time2;
            if (this.f759c.o().c() != -1) {
                return TimeUnit.SECONDS.toMillis(r0.c());
            }
            if (this.h != null) {
                if (this.d != null) {
                    time2 = this.d.getTime();
                } else {
                    time2 = this.j;
                }
                long time3 = this.h.getTime() - time2;
                if (time3 > 0) {
                    return time3;
                }
                return 0L;
            }
            if (this.f == null || this.f759c.a().a().p() != null) {
                return 0L;
            }
            if (this.d != null) {
                time = this.d.getTime();
            } else {
                time = this.i;
            }
            long time4 = time - this.f.getTime();
            if (time4 > 0) {
                return time4 / 10;
            }
            return 0L;
        }

        private long d() {
            long jMax = this.d != null ? Math.max(0L, this.j - this.d.getTime()) : 0L;
            if (this.l != -1) {
                jMax = Math.max(jMax, TimeUnit.SECONDS.toMillis(this.l));
            }
            return jMax + (this.j - this.i) + (this.f757a - this.j);
        }

        private boolean e() {
            return this.f759c.o().c() == -1 && this.h == null;
        }

        private static boolean a(ac acVar) {
            return (acVar.a("If-Modified-Since") == null && acVar.a("If-None-Match") == null) ? false : true;
        }
    }
}
