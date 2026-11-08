package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_19do;

import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.a0;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.e;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.r;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.y;
import com.bytedance.common.utility.date.DateDef;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.facebook.stetho.server.http.HttpStatus;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    public final y f1515a;

    /* renamed from: b, reason: collision with root package name */
    public final a0 f1516b;

    c(y yVar, a0 a0Var) {
        this.f1515a = yVar;
        this.f1516b = a0Var;
    }

    public static boolean a(a0 a0Var, y yVar) {
        switch (a0Var.k()) {
            case 200:
            case 203:
            case 204:
            case TTAdConstant.DEFAULT_LIVE_SHOW_TIME_MAX /* 300 */:
            case 301:
            case 308:
            case HttpStatus.HTTP_NOT_FOUND /* 404 */:
            case 405:
            case 410:
            case 414:
            case HttpStatus.HTTP_NOT_IMPLEMENTED /* 501 */:
                break;
            case 302:
            case 307:
                if (a0Var.b("Expires") == null && a0Var.j().d() == -1 && !a0Var.j().c() && !a0Var.j().b()) {
                    return false;
                }
                break;
            default:
                return false;
        }
        return (a0Var.j().i() || yVar.b().i()) ? false : true;
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        final long f1517a;

        /* renamed from: b, reason: collision with root package name */
        final y f1518b;

        /* renamed from: c, reason: collision with root package name */
        final a0 f1519c;
        private Date d;
        private String e;
        private Date f;
        private String g;
        private Date h;
        private long i;
        private long j;
        private String k;
        private int l;

        public a(long j, y yVar, a0 a0Var) {
            this.l = -1;
            this.f1517a = j;
            this.f1518b = yVar;
            this.f1519c = a0Var;
            if (a0Var != null) {
                this.i = a0Var.t();
                this.j = a0Var.r();
                r rVarM = a0Var.m();
                int iB = rVarM.b();
                for (int i = 0; i < iB; i++) {
                    String strA = rVarM.a(i);
                    String strB = rVarM.b(i);
                    if ("Date".equalsIgnoreCase(strA)) {
                        this.d = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.d.a(strB);
                        this.e = strB;
                    } else if ("Expires".equalsIgnoreCase(strA)) {
                        this.h = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.d.a(strB);
                    } else if ("Last-Modified".equalsIgnoreCase(strA)) {
                        this.f = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_for12.d.a(strB);
                        this.g = strB;
                    } else if ("ETag".equalsIgnoreCase(strA)) {
                        this.k = strB;
                    } else if ("Age".equalsIgnoreCase(strA)) {
                        this.l = e.a(strB, -1);
                    }
                }
            }
        }

        private long b() {
            Date date = this.d;
            long jMax = date != null ? Math.max(0L, this.j - date.getTime()) : 0L;
            int i = this.l;
            if (i != -1) {
                jMax = Math.max(jMax, TimeUnit.SECONDS.toMillis(i));
            }
            long j = this.j;
            return jMax + (j - this.i) + (this.f1517a - j);
        }

        private long c() {
            if (this.f1519c.j().d() != -1) {
                return TimeUnit.SECONDS.toMillis(r0.d());
            }
            if (this.h != null) {
                Date date = this.d;
                long time = this.h.getTime() - (date != null ? date.getTime() : this.j);
                if (time > 0) {
                    return time;
                }
                return 0L;
            }
            if (this.f == null || this.f1519c.s().g().k() != null) {
                return 0L;
            }
            Date date2 = this.d;
            long time2 = (date2 != null ? date2.getTime() : this.i) - this.f.getTime();
            if (time2 > 0) {
                return time2 / 10;
            }
            return 0L;
        }

        private c d() {
            String str;
            if (this.f1519c == null) {
                return new c(this.f1518b, null);
            }
            if (this.f1518b.d() && this.f1519c.l() == null) {
                return new c(this.f1518b, null);
            }
            if (!c.a(this.f1519c, this.f1518b)) {
                return new c(this.f1518b, null);
            }
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.d dVarB = this.f1518b.b();
            if (dVarB.h() || a(this.f1518b)) {
                return new c(this.f1518b, null);
            }
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.d dVarJ = this.f1519c.j();
            if (dVarJ.a()) {
                return new c(null, this.f1519c);
            }
            long jB = b();
            long jC = c();
            if (dVarB.d() != -1) {
                jC = Math.min(jC, TimeUnit.SECONDS.toMillis(dVarB.d()));
            }
            long millis = 0;
            long millis2 = dVarB.f() != -1 ? TimeUnit.SECONDS.toMillis(dVarB.f()) : 0L;
            if (!dVarJ.g() && dVarB.e() != -1) {
                millis = TimeUnit.SECONDS.toMillis(dVarB.e());
            }
            if (!dVarJ.h()) {
                long j = millis2 + jB;
                if (j < millis + jC) {
                    a0.a aVarP = this.f1519c.p();
                    if (j >= jC) {
                        aVarP.a("Warning", "110 HttpURLConnection \"Response is stale\"");
                    }
                    if (jB > DateDef.DAY && e()) {
                        aVarP.a("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                    }
                    return new c(null, aVarP.a());
                }
            }
            String str2 = this.k;
            if (str2 != null) {
                str = "If-None-Match";
            } else if (this.f != null) {
                str = "If-Modified-Since";
                str2 = this.g;
            } else {
                if (this.d == null) {
                    return new c(this.f1518b, null);
                }
                str = "If-Modified-Since";
                str2 = this.e;
            }
            r.a aVarA = this.f1518b.c().a();
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.a.f1509a.a(aVarA, str, str2);
            return new c(this.f1518b.f().a(aVarA.a()).a(), this.f1519c);
        }

        private boolean e() {
            return this.f1519c.j().d() == -1 && this.h == null;
        }

        public c a() {
            c cVarD = d();
            return (cVarD.f1515a == null || !this.f1518b.b().j()) ? cVarD : new c(null, null);
        }

        private static boolean a(y yVar) {
            return (yVar.a("If-Modified-Since") == null && yVar.a("If-None-Match") == null) ? false : true;
        }
    }
}
