package com.bytedance.a;

import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.applog.AppLog;
import com.bytedance.common.utility.NetworkUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    public static long f3388a;

    /* renamed from: b, reason: collision with root package name */
    public static long f3389b;

    /* renamed from: c, reason: collision with root package name */
    public static C0096b f3390c;
    public final dg d;
    public di e;
    public di f;
    public String g;
    public volatile long h;
    public int i;
    public long j = -1;
    public volatile boolean k;
    public long l;
    public int m;
    public String n;
    public volatile String o;

    /* renamed from: com.bytedance.a.b$b, reason: collision with other inner class name */
    public static class C0096b extends dx {
        public /* synthetic */ C0096b(a aVar) {
        }
    }

    public b(dg dgVar) {
        this.d = dgVar;
    }

    public static long a() {
        long j = f3389b + 1;
        f3389b = j;
        return j;
    }

    public static boolean a(bu buVar) {
        if (buVar instanceof di) {
            return ((di) buVar).j();
        }
        return false;
    }

    public String b() {
        return this.g;
    }

    public void b(bu buVar) {
        if (buVar != null) {
            buVar.f = f3388a;
            buVar.g = AppLog.getUserUniqueID();
            buVar.h = AppLog.getSsid();
            buVar.e = this.g;
            buVar.d = a();
            buVar.i = AppLog.getAbSdkVersion();
            buVar.j = NetworkUtils.getNetworkTypeFast(this.d.d).getValue();
        }
    }

    public boolean c() {
        return this.k && this.l == 0;
    }

    public synchronized Bundle a(long j, long j2) {
        Bundle bundle;
        bundle = null;
        long j3 = this.h;
        if (this.d.e.f3386b.isPlayEnable() && c() && j3 > 0) {
            long j4 = j - j3;
            if (j4 > j2) {
                bundle = new Bundle();
                bundle.putInt("session_no", this.m);
                int i = this.i + 1;
                this.i = i;
                bundle.putInt("send_times", i);
                bundle.putLong("current_duration", j4 / 1000);
                bundle.putString("session_start_time", bu.f3432a.format(new Date(this.j)));
                this.h = j;
            }
        }
        return bundle;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(com.bytedance.a.bu r14, java.util.ArrayList<com.bytedance.a.bu> r15) {
        /*
            r13 = this;
            boolean r0 = r14 instanceof com.bytedance.a.di
            boolean r1 = a(r14)
            long r2 = r13.j
            r4 = -1
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            r3 = 0
            r5 = 1
            if (r2 != 0) goto L19
            boolean r1 = a(r14)
            r13.a(r14, r15, r1)
            goto L4e
        L19:
            boolean r2 = r13.k
            if (r2 != 0) goto L23
            if (r1 == 0) goto L23
            r13.a(r14, r15, r5)
            goto L4e
        L23:
            long r6 = r13.l
            int r2 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r2 == 0) goto L3f
            long r8 = r14.f3434c
            com.bytedance.a.dg r2 = r13.d
            com.bytedance.a.az r2 = r2.e
            android.content.SharedPreferences r2 = r2.e
            java.lang.String r10 = "session_interval"
            r11 = 30000(0x7530, double:1.4822E-319)
            long r10 = r2.getLong(r10, r11)
            long r10 = r10 + r6
            int r2 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r2 <= 0) goto L3f
            goto L4b
        L3f:
            long r6 = r13.j
            long r8 = r14.f3434c
            r10 = 7200000(0x6ddd00, double:3.5572727E-317)
            long r8 = r8 + r10
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 <= 0) goto L50
        L4b:
            r13.a(r14, r15, r1)
        L4e:
            r1 = 1
            goto L51
        L50:
            r1 = 0
        L51:
            if (r0 == 0) goto Lb8
            r0 = r14
            com.bytedance.a.di r0 = (com.bytedance.a.di) r0
            boolean r2 = r0.j()
            if (r2 == 0) goto L91
            r13.l = r3
            r15.add(r14)
            java.lang.String r15 = r0.n
            boolean r15 = android.text.TextUtils.isEmpty(r15)
            if (r15 == 0) goto Lbf
            com.bytedance.a.di r15 = r13.f
            r2 = 500(0x1f4, double:2.47E-321)
            if (r15 == 0) goto L7c
            long r4 = r0.f3434c
            long r6 = r15.f3434c
            long r4 = r4 - r6
            long r6 = r15.m
            long r4 = r4 - r6
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 >= 0) goto L7c
            goto L8c
        L7c:
            com.bytedance.a.di r15 = r13.e
            if (r15 == 0) goto Lbf
            long r4 = r0.f3434c
            long r6 = r15.f3434c
            long r4 = r4 - r6
            long r6 = r15.m
            long r4 = r4 - r6
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 >= 0) goto Lbf
        L8c:
            java.lang.String r15 = r15.o
            r0.n = r15
            goto Lbf
        L91:
            long r6 = r14.f3434c
            android.os.Bundle r2 = r13.a(r6, r3)
            if (r2 == 0) goto L9e
            java.lang.String r3 = "play_session"
            com.bytedance.applog.AppLog.onEventV3(r3, r2, r5)
        L9e:
            long r2 = r0.f3434c
            r13.l = r2
            r15.add(r14)
            java.lang.String r15 = r0.o
            java.lang.String r2 = ":"
            boolean r15 = r15.contains(r2)
            if (r15 == 0) goto Lb2
            r13.e = r0
            goto Lbf
        Lb2:
            r13.f = r0
            r15 = 0
            r13.e = r15
            goto Lbf
        Lb8:
            boolean r0 = r14 instanceof com.bytedance.a.b.C0096b
            if (r0 != 0) goto Lbf
            r15.add(r14)
        Lbf:
            r13.b(r14)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.b.a(com.bytedance.a.bu, java.util.ArrayList):boolean");
    }

    public synchronized cy a(bu buVar, ArrayList<bu> arrayList, boolean z) {
        cy cyVar;
        long j = buVar instanceof C0096b ? -1L : buVar.f3434c;
        this.g = UUID.randomUUID().toString();
        if (z && !this.d.v && TextUtils.isEmpty(this.o)) {
            this.o = this.g;
        }
        f3389b = 10000L;
        this.j = j;
        this.k = z;
        this.l = 0L;
        this.h = 0L;
        if (z) {
            Calendar calendar = Calendar.getInstance();
            StringBuilder sbA = com.bytedance.a.a.a("");
            sbA.append(calendar.get(1));
            sbA.append(calendar.get(2));
            sbA.append(calendar.get(5));
            String string = sbA.toString();
            az azVar = this.d.e;
            if (TextUtils.isEmpty(this.n)) {
                this.n = azVar.d.getString("session_last_day", "");
                this.m = azVar.d.getInt("session_order", 0);
            }
            if (string.equals(this.n)) {
                this.m++;
            } else {
                this.n = string;
                this.m = 1;
            }
            azVar.d.edit().putString("session_last_day", string).putInt("session_order", this.m).apply();
            this.i = 0;
            this.h = buVar.f3434c;
        }
        if (j != -1) {
            cyVar = new cy();
            cyVar.e = this.g;
            cyVar.o = !this.k;
            cyVar.d = a();
            cyVar.a(this.j);
            cyVar.n = this.d.i.c();
            cyVar.m = this.d.i.b();
            cyVar.f = f3388a;
            cyVar.g = AppLog.getUserUniqueID();
            cyVar.h = AppLog.getSsid();
            cyVar.i = AppLog.getAbSdkVersion();
            cyVar.q = z ? this.d.e.e.getInt("is_first_time_launch", 1) : 0;
            if (z && cyVar.q == 1) {
                this.d.e.e.edit().putInt("is_first_time_launch", 0).apply();
            }
            arrayList.add(cyVar);
        } else {
            cyVar = null;
        }
        if (AppLog.sLaunchFrom <= 0) {
            AppLog.sLaunchFrom = 6;
        }
        StringBuilder sbA2 = com.bytedance.a.a.a("startSession, ");
        sbA2.append(this.k ? "fg" : "bg");
        sbA2.append(", ");
        sbA2.append(this.g);
        de.a(sbA2.toString(), null);
        return cyVar;
    }
}
