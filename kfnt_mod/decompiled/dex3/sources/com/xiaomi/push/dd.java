package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.push.al;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class dd {
    private static volatile dd a;

    /* renamed from: a, reason: collision with other field name */
    private Context f223a;

    /* renamed from: a, reason: collision with other field name */
    private final ConcurrentLinkedQueue<b> f224a = new ConcurrentLinkedQueue<>();

    class a extends b {
        a() {
            super();
        }

        @Override // com.xiaomi.push.dd.b, com.xiaomi.push.al.b
        public void b() {
            dd.this.b();
        }
    }

    class b extends al.b {
        long a = System.currentTimeMillis();

        b() {
        }

        public boolean a() {
            return true;
        }

        @Override // com.xiaomi.push.al.b
        public void b() {
        }

        /* renamed from: b, reason: collision with other method in class */
        final boolean m196b() {
            return System.currentTimeMillis() - this.a > 172800000;
        }
    }

    class c extends b {
        int a;

        /* renamed from: a, reason: collision with other field name */
        File f226a;

        /* renamed from: a, reason: collision with other field name */
        String f227a;

        /* renamed from: a, reason: collision with other field name */
        boolean f228a;
        String b;

        /* renamed from: b, reason: collision with other field name */
        boolean f229b;

        c(String str, String str2, File file, boolean z) {
            super();
            this.f227a = str;
            this.b = str2;
            this.f226a = file;
            this.f229b = z;
        }

        /* JADX WARN: Can't wrap try/catch for region: R(11:0|2|(2:23|3)|(2:25|4)|8|(2:10|(1:12))(1:13)|14|21|15|19|(1:(0))) */
        /* JADX WARN: Code restructure failed: missing block: B:17:0x0066, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x0067, code lost:
        
            com.xiaomi.channel.commonutils.logger.b.c("JSONException on put " + r0.getMessage());
         */
        /* JADX WARN: Removed duplicated region for block: B:10:0x0039  */
        /* JADX WARN: Removed duplicated region for block: B:13:0x003e  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private boolean c() throws org.json.JSONException {
            /*
                r10 = this;
                com.xiaomi.push.dd r0 = com.xiaomi.push.dd.this
                android.content.Context r0 = com.xiaomi.push.dd.a(r0)
                java.lang.String r1 = "log.timestamp"
                r2 = 0
                android.content.SharedPreferences r0 = r0.getSharedPreferences(r1, r2)
                java.lang.String r1 = "log.requst"
                java.lang.String r3 = ""
                java.lang.String r1 = r0.getString(r1, r3)
                long r3 = java.lang.System.currentTimeMillis()
                org.json.JSONObject r5 = new org.json.JSONObject     // Catch: org.json.JSONException -> L2b
                r5.<init>(r1)     // Catch: org.json.JSONException -> L2b
                java.lang.String r1 = "time"
                long r6 = r5.getLong(r1)     // Catch: org.json.JSONException -> L2b
                java.lang.String r1 = "times"
                int r1 = r5.getInt(r1)     // Catch: org.json.JSONException -> L2c
                goto L2d
            L2b:
                r6 = r3
            L2c:
                r1 = 0
            L2d:
                long r3 = java.lang.System.currentTimeMillis()
                long r3 = r3 - r6
                r8 = 86400000(0x5265c00, double:4.2687272E-316)
                int r3 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
                if (r3 >= 0) goto L3e
                r3 = 10
                if (r1 <= r3) goto L43
                return r2
            L3e:
                long r6 = java.lang.System.currentTimeMillis()
                r1 = 0
            L43:
                org.json.JSONObject r2 = new org.json.JSONObject
                r2.<init>()
                r3 = 1
                java.lang.String r4 = "time"
                r2.put(r4, r6)     // Catch: org.json.JSONException -> L66
                java.lang.String r4 = "times"
                int r1 = r1 + r3
                r2.put(r4, r1)     // Catch: org.json.JSONException -> L66
                android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch: org.json.JSONException -> L66
                java.lang.String r1 = "log.requst"
                java.lang.String r2 = r2.toString()     // Catch: org.json.JSONException -> L66
                android.content.SharedPreferences$Editor r0 = r0.putString(r1, r2)     // Catch: org.json.JSONException -> L66
                r0.commit()     // Catch: org.json.JSONException -> L66
                goto L7f
            L66:
                r0 = move-exception
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "JSONException on put "
                r1.append(r2)
                java.lang.String r0 = r0.getMessage()
                r1.append(r0)
                java.lang.String r0 = r1.toString()
                com.xiaomi.channel.commonutils.logger.b.c(r0)
            L7f:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.dd.c.c():boolean");
        }

        @Override // com.xiaomi.push.dd.b
        public boolean a() {
            return bg.e(dd.this.f223a) || (this.f229b && bg.b(dd.this.f223a));
        }

        @Override // com.xiaomi.push.dd.b, com.xiaomi.push.al.b
        public void b() throws Throwable {
            try {
                if (c()) {
                    HashMap map = new HashMap();
                    map.put("uid", com.xiaomi.push.service.bk.m619a());
                    map.put("token", this.b);
                    map.put("net", bg.m124a(dd.this.f223a));
                    bg.a(this.f227a, map, this.f226a, "file");
                }
                this.f228a = true;
            } catch (IOException unused) {
            }
        }

        @Override // com.xiaomi.push.al.b
        /* renamed from: c, reason: collision with other method in class */
        public void mo197c() {
            if (!this.f228a) {
                this.a++;
                if (this.a < 3) {
                    dd.this.f224a.add(this);
                }
            }
            if (this.f228a || this.a >= 3) {
                this.f226a.delete();
            }
            dd.this.a((1 << this.a) * 1000);
        }
    }

    private dd(Context context) {
        this.f223a = context;
        this.f224a.add(new a());
        b(0L);
    }

    public static dd a(Context context) {
        if (a == null) {
            synchronized (dd.class) {
                if (a == null) {
                    a = new dd(context);
                }
            }
        }
        a.f223a = context;
        return a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j) {
        b bVarPeek = this.f224a.peek();
        if (bVarPeek == null || !bVarPeek.a()) {
            return;
        }
        b(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (aa.b() || aa.m89a()) {
            return;
        }
        try {
            File file = new File(this.f223a.getExternalFilesDir(null) + "/.logcache");
            if (file.exists() && file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    file2.delete();
                }
            }
        } catch (NullPointerException unused) {
        }
    }

    private void b(long j) {
        if (this.f224a.isEmpty()) {
            return;
        }
        gs.a(new df(this), j);
    }

    private void c() {
        while (!this.f224a.isEmpty()) {
            b bVarPeek = this.f224a.peek();
            if (bVarPeek != null) {
                if (!bVarPeek.m196b() && this.f224a.size() <= 6) {
                    return;
                }
                com.xiaomi.channel.commonutils.logger.b.c("remove Expired task");
                this.f224a.remove(bVarPeek);
            }
        }
    }

    public void a() {
        c();
        a(0L);
    }

    public void a(String str, String str2, Date date, Date date2, int i, boolean z) {
        this.f224a.add(new de(this, i, date, date2, str, str2, z));
        b(0L);
    }
}
