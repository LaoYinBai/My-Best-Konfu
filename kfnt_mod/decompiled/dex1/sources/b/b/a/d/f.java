package b.b.a.d;

import android.os.Bundle;
import android.text.TextUtils;
import b.b.a.f.g;
import b.b.a.j.h;
import b.b.a.j.j;
import b.b.a.j.m;
import b.b.a.k.v;
import com.bytedance.common.utility.NetworkUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    public static long f1057a;

    /* renamed from: b, reason: collision with root package name */
    public static long f1058b;

    /* renamed from: c, reason: collision with root package name */
    public static b f1059c;
    public final b.b.a.d.b d;
    public final com.apm.applog.a e;
    public j f;
    public j g;
    public String h;
    public volatile long i;
    public int j;
    public long k = -1;
    public volatile boolean l;
    public long m;
    public int n;
    public String o;
    public volatile String p;

    public static class b extends m {
        public /* synthetic */ b(a aVar) {
        }
    }

    public f(b.b.a.d.b bVar) {
        this.d = bVar;
        this.e = com.apm.applog.a.a(bVar.g.f());
    }

    public static long a() {
        long j = f1058b + 1;
        f1058b = j;
        return j;
    }

    public String b() {
        return this.h;
    }

    public boolean c() {
        return this.l && this.m == 0;
    }

    public synchronized Bundle a(long j, long j2) {
        Bundle bundle;
        bundle = null;
        long j3 = this.i;
        if (this.d.d.f1070b.g() && c() && j3 > 0) {
            long j4 = j - j3;
            if (j4 > j2) {
                bundle = new Bundle();
                bundle.putInt("session_no", this.n);
                int i = this.j + 1;
                this.j = i;
                bundle.putInt("send_times", i);
                bundle.putLong("current_duration", j4 / 1000);
                bundle.putString("session_start_time", b.b.a.j.b.f1108a.format(new Date(this.k)));
                this.i = j;
            }
        }
        return bundle;
    }

    public static boolean a(b.b.a.j.b bVar) {
        if (bVar instanceof j) {
            return ((j) bVar).i();
        }
        return false;
    }

    public synchronized h a(b.b.a.j.b bVar, ArrayList<b.b.a.j.b> arrayList, boolean z) {
        h hVar;
        long j = bVar instanceof b ? -1L : bVar.f1110c;
        this.h = UUID.randomUUID().toString();
        if (z && !this.d.s && TextUtils.isEmpty(this.p)) {
            this.p = this.h;
        }
        f1058b = 10000L;
        this.k = j;
        this.l = z;
        this.m = 0L;
        this.i = 0L;
        if (z) {
            Calendar calendar = Calendar.getInstance();
            StringBuilder sbA = b.a.a.a.a.a("");
            sbA.append(calendar.get(1));
            sbA.append(calendar.get(2));
            sbA.append(calendar.get(5));
            String string = sbA.toString();
            g gVar = this.d.d;
            if (TextUtils.isEmpty(this.o)) {
                this.o = gVar.d.getString("session_last_day", "");
                this.n = gVar.d.getInt("session_order", 0);
            }
            if (!string.equals(this.o)) {
                this.o = string;
                this.n = 1;
            } else {
                this.n++;
            }
            gVar.d.edit().putString("session_last_day", string).putInt("session_order", this.n).apply();
            this.j = 0;
            this.i = bVar.f1110c;
        }
        hVar = null;
        if (j != -1) {
            hVar = new h();
            hVar.e = this.h;
            hVar.n = !this.l;
            hVar.d = a();
            hVar.a(this.k);
            hVar.m = this.d.g.c();
            hVar.l = this.d.g.b();
            hVar.f = f1057a;
            hVar.g = this.e.y();
            hVar.h = this.e.x();
            hVar.i = this.e.q();
            hVar.p = z ? this.d.d.e.getInt("is_first_time_launch", 1) : 0;
            if (z && hVar.p == 1) {
                this.d.d.e.edit().putInt("is_first_time_launch", 0).apply();
            }
            arrayList.add(hVar);
        }
        if (com.apm.applog.a.f2739b <= 0) {
            com.apm.applog.a.f2739b = 6;
        }
        StringBuilder sbA2 = b.a.a.a.a.a("startSession, ");
        sbA2.append(this.l ? "fg" : "bg");
        sbA2.append(", ");
        sbA2.append(this.h);
        v.a(sbA2.toString());
        return hVar;
    }

    public boolean a(b.b.a.j.b bVar, ArrayList<b.b.a.j.b> arrayList) {
        boolean z = bVar instanceof j;
        boolean zA = a(bVar);
        boolean z2 = true;
        if (this.k == -1) {
            a(bVar, arrayList, a(bVar));
        } else if (!this.l && zA) {
            a(bVar, arrayList, true);
        } else {
            long j = this.m;
            if ((j == 0 || bVar.f1110c <= this.d.d.e.getLong("session_interval", com.bytedance.b.a.a.c.g.e) + j) && this.k <= bVar.f1110c + 7200000) {
                z2 = false;
            } else {
                a(bVar, arrayList, zA);
            }
        }
        if (z) {
            j jVar = (j) bVar;
            if (jVar.i()) {
                this.m = 0L;
                arrayList.add(bVar);
                if (TextUtils.isEmpty(jVar.m)) {
                    j jVar2 = this.g;
                    if (jVar2 != null && (jVar.f1110c - jVar2.f1110c) - jVar2.l < 500) {
                        jVar.m = jVar2.n;
                    } else {
                        j jVar3 = this.f;
                        if (jVar3 != null && (jVar.f1110c - jVar3.f1110c) - jVar3.l < 500) {
                            jVar.m = jVar3.n;
                        }
                    }
                }
            } else {
                Bundle bundleA = a(bVar.f1110c, 0L);
                if (bundleA != null) {
                    this.e.a("play_session", bundleA);
                }
                this.m = jVar.f1110c;
                arrayList.add(bVar);
                if (jVar.n.contains(":")) {
                    this.f = jVar;
                } else {
                    this.g = jVar;
                    this.f = null;
                }
            }
        } else if (!(bVar instanceof b)) {
            arrayList.add(bVar);
        }
        b(bVar);
        return z2;
    }

    public void b(b.b.a.j.b bVar) {
        if (bVar != null) {
            bVar.f = f1057a;
            bVar.g = this.e.y();
            bVar.h = this.e.x();
            bVar.e = this.h;
            bVar.d = a();
            bVar.i = this.e.q();
            bVar.j = NetworkUtils.getNetworkTypeFast(this.d.f1054c).getValue();
        }
    }
}
