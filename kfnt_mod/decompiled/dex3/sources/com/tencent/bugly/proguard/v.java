package com.tencent.bugly.proguard;

import android.content.Context;
import java.util.Map;
import java.util.UUID;

/* compiled from: BUGLY */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class v implements Runnable {
    private int a;
    private int b;
    private final Context c;
    private final int d;
    private final byte[] e;
    private final com.tencent.bugly.crashreport.common.info.a f;
    private final com.tencent.bugly.crashreport.common.strategy.a g;
    private final s h;
    private final u i;
    private final int j;
    private final t k;
    private final t l;
    private String m;
    private final String n;
    private final Map<String, String> o;
    private int p;
    private long q;
    private long r;
    private boolean s;
    private boolean t;

    public v(Context context, int i, int i2, byte[] bArr, String str, String str2, t tVar, boolean z, boolean z2) {
        this(context, i, i2, bArr, str, str2, tVar, z, 2, 30000, z2, null);
    }

    public v(Context context, int i, int i2, byte[] bArr, String str, String str2, t tVar, boolean z, int i3, int i4, boolean z2, Map<String, String> map) {
        this.a = 2;
        this.b = 30000;
        this.m = null;
        this.p = 0;
        this.q = 0L;
        this.r = 0L;
        this.s = true;
        this.t = false;
        this.c = context;
        this.f = com.tencent.bugly.crashreport.common.info.a.a(context);
        this.e = bArr;
        this.g = com.tencent.bugly.crashreport.common.strategy.a.a();
        this.h = s.a(context);
        this.i = u.a();
        this.j = i;
        this.m = str;
        this.n = str2;
        this.k = tVar;
        u uVar = this.i;
        this.l = null;
        this.s = z;
        this.d = i2;
        if (i3 > 0) {
            this.a = i3;
        }
        if (i4 > 0) {
            this.b = i4;
        }
        this.t = z2;
        this.o = map;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(com.tencent.bugly.proguard.an r5, boolean r6, int r7, java.lang.String r8, int r9) {
        /*
            r4 = this;
            int r5 = r4.d
            r0 = 630(0x276, float:8.83E-43)
            if (r5 == r0) goto L1c
            r0 = 640(0x280, float:8.97E-43)
            if (r5 == r0) goto L19
            r0 = 830(0x33e, float:1.163E-42)
            if (r5 == r0) goto L1c
            r0 = 840(0x348, float:1.177E-42)
            if (r5 == r0) goto L19
            int r5 = r4.d
            java.lang.String r5 = java.lang.String.valueOf(r5)
            goto L1e
        L19:
            java.lang.String r5 = "userinfo"
            goto L1e
        L1c:
            java.lang.String r5 = "crash"
        L1e:
            r0 = 0
            r1 = 1
            if (r6 == 0) goto L2c
            java.lang.String r7 = "[Upload] Success: %s"
            java.lang.Object[] r8 = new java.lang.Object[r1]
            r8[r0] = r5
            com.tencent.bugly.proguard.x.a(r7, r8)
            goto L49
        L2c:
            java.lang.String r2 = "[Upload] Failed to upload(%d) %s: %s"
            r3 = 3
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r3[r0] = r7
            r3[r1] = r5
            r5 = 2
            r3[r5] = r8
            com.tencent.bugly.proguard.x.e(r2, r3)
            boolean r5 = r4.s
            if (r5 == 0) goto L49
            com.tencent.bugly.proguard.u r5 = r4.i
            r7 = 0
            r5.a(r9, r7)
        L49:
            long r7 = r4.q
            long r0 = r4.r
            long r7 = r7 + r0
            r0 = 0
            int r5 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r5 <= 0) goto L69
            com.tencent.bugly.proguard.u r5 = r4.i
            boolean r7 = r4.t
            long r7 = r5.a(r7)
            long r0 = r4.q
            long r7 = r7 + r0
            long r0 = r4.r
            long r7 = r7 + r0
            com.tencent.bugly.proguard.u r5 = r4.i
            boolean r9 = r4.t
            r5.a(r7, r9)
        L69:
            com.tencent.bugly.proguard.t r5 = r4.k
            if (r5 == 0) goto L78
            com.tencent.bugly.proguard.t r5 = r4.k
            int r7 = r4.d
            long r7 = r4.q
            long r7 = r4.r
            r5.a(r6)
        L78:
            com.tencent.bugly.proguard.t r5 = r4.l
            if (r5 == 0) goto L87
            com.tencent.bugly.proguard.t r5 = r4.l
            int r7 = r4.d
            long r7 = r4.q
            long r7 = r4.r
            r5.a(r6)
        L87:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.v.a(com.tencent.bugly.proguard.an, boolean, int, java.lang.String, int):void");
    }

    private static boolean a(an anVar, com.tencent.bugly.crashreport.common.info.a aVar, com.tencent.bugly.crashreport.common.strategy.a aVar2) throws NumberFormatException {
        if (anVar == null) {
            x.d("resp == null!", new Object[0]);
            return false;
        }
        if (anVar.a != 0) {
            x.e("resp result error %d", Byte.valueOf(anVar.a));
            return false;
        }
        try {
            if (!z.a(anVar.d) && !com.tencent.bugly.crashreport.common.info.a.b().i().equals(anVar.d)) {
                p.a().a(com.tencent.bugly.crashreport.common.strategy.a.a, "key_ip", anVar.d.getBytes("UTF-8"), (o) null, true);
                aVar.d(anVar.d);
            }
            if (!z.a(anVar.f) && !com.tencent.bugly.crashreport.common.info.a.b().j().equals(anVar.f)) {
                p.a().a(com.tencent.bugly.crashreport.common.strategy.a.a, "key_imei", anVar.f.getBytes("UTF-8"), (o) null, true);
                aVar.e(anVar.f);
            }
        } catch (Throwable th) {
            x.a(th);
        }
        aVar.i = anVar.e;
        if (anVar.b == 510) {
            if (anVar.c == null) {
                x.e("[Upload] Strategy data is null. Response cmd: %d", Integer.valueOf(anVar.b));
                return false;
            }
            ap apVar = (ap) a.a(anVar.c, ap.class);
            if (apVar == null) {
                x.e("[Upload] Failed to decode strategy from server. Response cmd: %d", Integer.valueOf(anVar.b));
                return false;
            }
            aVar2.a(apVar);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:156:0x02ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0245 A[Catch: Throwable -> 0x0477, TryCatch #2 {Throwable -> 0x0477, blocks: (B:3:0x0003, B:5:0x0015, B:8:0x0023, B:11:0x0028, B:13:0x003c, B:15:0x006e, B:17:0x0081, B:19:0x0085, B:21:0x0089, B:24:0x008f, B:26:0x0097, B:28:0x00a3, B:30:0x00c9, B:31:0x00ce, B:33:0x00d2, B:35:0x0105, B:37:0x0111, B:39:0x0117, B:41:0x0123, B:43:0x012b, B:45:0x0137, B:47:0x0146, B:48:0x014a, B:50:0x014e, B:51:0x0152, B:52:0x0159, B:55:0x0161, B:57:0x0178, B:58:0x0185, B:60:0x0197, B:61:0x019c, B:63:0x01cb, B:65:0x01e0, B:68:0x01ea, B:71:0x01f1, B:73:0x01f9, B:74:0x0201, B:85:0x0245, B:87:0x0273, B:88:0x027b, B:90:0x0281, B:91:0x02a1, B:97:0x02de, B:99:0x02ea, B:100:0x0301, B:102:0x0344, B:107:0x0364, B:75:0x0205, B:77:0x020d, B:78:0x0216, B:80:0x0226, B:81:0x0230, B:82:0x023b, B:108:0x0389, B:110:0x039b, B:112:0x039e, B:113:0x03a6, B:115:0x03ac, B:116:0x03c6, B:118:0x03d2, B:120:0x03da, B:122:0x03e6, B:124:0x03ec, B:126:0x03f8, B:128:0x0400, B:130:0x040c, B:132:0x0410, B:133:0x0415, B:137:0x0429, B:139:0x043c, B:141:0x0447, B:136:0x0426, B:143:0x0452, B:145:0x045f, B:147:0x046b), top: B:158:0x0003 }] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            Method dump skipped, instructions count: 1154
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.v.run():void");
    }

    public final void a(long j) {
        this.p++;
        this.q += j;
    }

    public final void b(long j) {
        this.r += j;
    }

    private static String a(String str) {
        if (z.a(str)) {
            return str;
        }
        try {
            return String.format("%s?aid=%s", str, UUID.randomUUID().toString());
        } catch (Throwable th) {
            x.a(th);
            return str;
        }
    }
}
