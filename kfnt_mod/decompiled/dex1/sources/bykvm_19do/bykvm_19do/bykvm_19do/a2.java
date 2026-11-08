package bykvm_19do.bykvm_19do.bykvm_19do;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a2 {
    private static long n;
    private static b o;

    /* renamed from: a, reason: collision with root package name */
    private final i2 f1314a;

    /* renamed from: b, reason: collision with root package name */
    private final j2 f1315b;

    /* renamed from: c, reason: collision with root package name */
    private b0 f1316c;
    private b0 d;
    String e;
    private long f;
    private int g;
    private long h = -1;
    private volatile boolean i;
    private long j;
    private int k;
    private String l;
    private z m;

    static class b extends d0 {
        private b() {
        }
    }

    a2(j2 j2Var, i2 i2Var) {
        this.f1315b = j2Var;
        this.f1314a = i2Var;
    }

    static b d() {
        if (o == null) {
            o = new b();
        }
        o.f1385a = System.currentTimeMillis();
        return o;
    }

    synchronized z a() {
        return this.m;
    }

    public boolean b() {
        return this.i;
    }

    boolean c() {
        return b() && this.j == 0;
    }

    public static boolean b(u uVar) {
        if (uVar instanceof b0) {
            return ((b0) uVar).j();
        }
        return false;
    }

    synchronized Bundle a(long j, long j2) {
        Bundle bundle;
        bundle = null;
        if (this.f1314a.u() && c() && j - this.f > j2) {
            bundle = new Bundle();
            bundle.putInt("session_no", this.k);
            int i = this.g + 1;
            this.g = i;
            bundle.putInt("send_times", i);
            bundle.putLong("current_duration", (j - this.f) / 1000);
            bundle.putString("session_start_time", u.a(this.h));
            this.f = j;
        }
        return bundle;
    }

    private synchronized void a(u uVar, ArrayList<u> arrayList, boolean z) {
        long j = uVar instanceof b ? -1L : uVar.f1385a;
        this.e = UUID.randomUUID().toString();
        n = this.f1314a.b();
        this.h = j;
        this.i = z;
        this.j = 0L;
        if (l0.f1356a) {
            l0.a("startSession, " + this.e + ", hadUi:" + z + " data:" + uVar, null);
        }
        if (z) {
            Calendar calendar = Calendar.getInstance();
            String str = "" + calendar.get(1) + calendar.get(2) + calendar.get(5);
            if (TextUtils.isEmpty(this.l)) {
                this.l = this.f1314a.r();
                this.k = this.f1314a.s();
            }
            if (!str.equals(this.l)) {
                this.l = str;
                this.k = 1;
            } else {
                this.k++;
            }
            this.f1314a.a(str, this.k);
            this.g = 0;
        }
        if (j != -1) {
            z zVar = new z();
            zVar.f1387c = this.e;
            zVar.f1386b = a(this.f1314a);
            zVar.f1385a = this.h;
            zVar.j = this.f1315b.d();
            zVar.i = this.f1315b.c();
            if (this.f1314a.K()) {
                zVar.e = bykvm_19do.bykvm_19do.bykvm_19do.a.c();
                zVar.f = bykvm_19do.bykvm_19do.bykvm_19do.a.d();
            }
            arrayList.add(zVar);
            this.m = zVar;
            if (l0.f1356a) {
                l0.a("gen launch, " + zVar.f1387c + ", hadUi:" + z, null);
            }
        }
    }

    boolean a(u uVar, ArrayList<u> arrayList) {
        boolean z = uVar instanceof b0;
        boolean zB = b(uVar);
        boolean z2 = true;
        if (this.h == -1) {
            a(uVar, arrayList, b(uVar));
        } else if (!this.i && zB) {
            a(uVar, arrayList, true);
        } else {
            long j = this.j;
            if ((j == 0 || uVar.f1385a <= j + this.f1314a.M()) && this.h <= uVar.f1385a + 7200000) {
                z2 = false;
            } else {
                a(uVar, arrayList, zB);
            }
        }
        if (z) {
            b0 b0Var = (b0) uVar;
            if (b0Var.j()) {
                this.f = uVar.f1385a;
                this.j = 0L;
                arrayList.add(uVar);
                if (TextUtils.isEmpty(b0Var.j)) {
                    b0 b0Var2 = this.d;
                    if (b0Var2 != null && (b0Var.f1385a - b0Var2.f1385a) - b0Var2.i < 500) {
                        b0Var.j = b0Var2.k;
                    } else {
                        b0 b0Var3 = this.f1316c;
                        if (b0Var3 != null && (b0Var.f1385a - b0Var3.f1385a) - b0Var3.i < 500) {
                            b0Var.j = b0Var3.k;
                        }
                    }
                }
            } else {
                Bundle bundleA = a(uVar.f1385a, 0L);
                if (bundleA != null) {
                    bykvm_19do.bykvm_19do.bykvm_19do.a.a("play_session", bundleA);
                }
                this.f = 0L;
                this.j = b0Var.f1385a;
                arrayList.add(uVar);
                if (b0Var.k()) {
                    this.f1316c = b0Var;
                } else {
                    this.d = b0Var;
                    this.f1316c = null;
                }
            }
        } else if (!(uVar instanceof b)) {
            arrayList.add(uVar);
        }
        a(uVar);
        return z2;
    }

    public void a(u uVar) {
        if (uVar != null) {
            uVar.d = this.f1315b.f();
            uVar.f1387c = this.e;
            uVar.f1386b = a(this.f1314a);
            if (this.f1314a.K()) {
                uVar.e = bykvm_19do.bykvm_19do.bykvm_19do.a.c();
                uVar.f = bykvm_19do.bykvm_19do.bykvm_19do.a.d();
            }
        }
    }

    public static long a(i2 i2Var) {
        n++;
        long j = n;
        if (j % 1000 == 0) {
            i2Var.a(j + 1000);
        }
        return n;
    }
}
