package com.xiaomi.push.service;

import android.content.SharedPreferences;
import com.xiaomi.push.al;
import com.xiaomi.push.ea;
import com.xiaomi.push.eb;
import com.xiaomi.push.gs;
import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class bk {
    private static bk a = new bk();

    /* renamed from: a, reason: collision with other field name */
    private static String f927a;

    /* renamed from: a, reason: collision with other field name */
    private al.b f928a;

    /* renamed from: a, reason: collision with other field name */
    private ea.a f929a;

    /* renamed from: a, reason: collision with other field name */
    private List<a> f930a = new ArrayList();

    public static abstract class a {
        public void a(ea.a aVar) {
        }

        public void a(eb.b bVar) {
        }
    }

    private bk() {
    }

    public static bk a() {
        return a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static synchronized String m619a() {
        if (f927a == null) {
            SharedPreferences sharedPreferences = com.xiaomi.push.t.m643a().getSharedPreferences("XMPushServiceConfig", 0);
            f927a = sharedPreferences.getString("DeviceUUID", null);
            if (f927a == null) {
                f927a = com.xiaomi.push.i.a(com.xiaomi.push.t.m643a(), false);
                if (f927a != null) {
                    sharedPreferences.edit().putString("DeviceUUID", f927a).commit();
                }
            }
        }
        return f927a;
    }

    private void b() throws Throwable {
        if (this.f929a == null) {
            d();
        }
    }

    private void c() {
        if (this.f928a != null) {
            return;
        }
        this.f928a = new bl(this);
        gs.a(this.f928a);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d() throws java.lang.Throwable {
        /*
            r4 = this;
            r0 = 0
            android.content.Context r1 = com.xiaomi.push.t.m643a()     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2b
            java.lang.String r2 = "XMCloudCfg"
            java.io.FileInputStream r1 = r1.openFileInput(r2)     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2b
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2b
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2b
            com.xiaomi.push.b r0 = com.xiaomi.push.b.a(r2)     // Catch: java.lang.Throwable -> L21 java.lang.Exception -> L25
            com.xiaomi.push.ea$a r0 = com.xiaomi.push.ea.a.b(r0)     // Catch: java.lang.Throwable -> L21 java.lang.Exception -> L25
            r4.f929a = r0     // Catch: java.lang.Throwable -> L21 java.lang.Exception -> L25
            r2.close()     // Catch: java.lang.Throwable -> L21 java.lang.Exception -> L25
            com.xiaomi.push.y.a(r2)
            goto L47
        L21:
            r0 = move-exception
            r1 = r0
            r0 = r2
            goto L53
        L25:
            r0 = move-exception
            r1 = r0
            r0 = r2
            goto L2c
        L29:
            r1 = move-exception
            goto L53
        L2b:
            r1 = move-exception
        L2c:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L29
            r2.<init>()     // Catch: java.lang.Throwable -> L29
            java.lang.String r3 = "load config failure: "
            r2.append(r3)     // Catch: java.lang.Throwable -> L29
            java.lang.String r1 = r1.getMessage()     // Catch: java.lang.Throwable -> L29
            r2.append(r1)     // Catch: java.lang.Throwable -> L29
            java.lang.String r1 = r2.toString()     // Catch: java.lang.Throwable -> L29
            com.xiaomi.channel.commonutils.logger.b.m22a(r1)     // Catch: java.lang.Throwable -> L29
            com.xiaomi.push.y.a(r0)
        L47:
            com.xiaomi.push.ea$a r0 = r4.f929a
            if (r0 != 0) goto L52
            com.xiaomi.push.ea$a r0 = new com.xiaomi.push.ea$a
            r0.<init>()
            r4.f929a = r0
        L52:
            return
        L53:
            com.xiaomi.push.y.a(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.bk.d():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        try {
            if (this.f929a != null) {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(com.xiaomi.push.t.m643a().openFileOutput("XMCloudCfg", 0));
                com.xiaomi.push.c cVarA = com.xiaomi.push.c.a(bufferedOutputStream);
                this.f929a.a(cVarA);
                cVarA.m147a();
                bufferedOutputStream.close();
            }
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.m22a("save config failure: " + e.getMessage());
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    int m622a() throws Throwable {
        b();
        if (this.f929a != null) {
            return this.f929a.c();
        }
        return 0;
    }

    /* renamed from: a, reason: collision with other method in class */
    public ea.a m623a() throws Throwable {
        b();
        return this.f929a;
    }

    /* renamed from: a, reason: collision with other method in class */
    synchronized void m624a() {
        this.f930a.clear();
    }

    void a(eb.b bVar) {
        a[] aVarArr;
        if (bVar.m234d() && bVar.d() > m622a()) {
            c();
        }
        synchronized (this) {
            aVarArr = (a[]) this.f930a.toArray(new a[this.f930a.size()]);
        }
        for (a aVar : aVarArr) {
            aVar.a(bVar);
        }
    }

    public synchronized void a(a aVar) {
        this.f930a.add(aVar);
    }
}
