package com.xiaomi.push;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class da {
    public static int a(Context context, int i) {
        int iA = gu.a(context);
        if (-1 == iA) {
            return -1;
        }
        return (i * (iA == 0 ? 13 : 11)) / 10;
    }

    public static int a(hj hjVar) {
        return er.a(hjVar.a());
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x004b, code lost:
    
        if (com.xiaomi.push.fb.a(r2) != (-1)) goto L13;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0033 A[PHI: r3
  0x0033: PHI (r3v5 int) = (r3v2 int), (r3v2 int), (r3v8 int), (r3v8 int) binds: [B:30:0x006b, B:32:0x0075, B:10:0x0026, B:12:0x002c] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int a(com.xiaomi.push.iu r2, com.xiaomi.push.hj r3) {
        /*
            int[] r0 = com.xiaomi.push.db.a
            int r1 = r3.ordinal()
            r0 = r0[r1]
            r1 = -1
            switch(r0) {
                case 1: goto Lb5;
                case 2: goto Lb5;
                case 3: goto Lb5;
                case 4: goto Lb5;
                case 5: goto Lb5;
                case 6: goto Lb5;
                case 7: goto Lb5;
                case 8: goto Lb5;
                case 9: goto Lb5;
                case 10: goto Lb5;
                case 11: goto L55;
                case 12: goto Le;
                default: goto Lc;
            }
        Lc:
            goto Lbd
        Le:
            int r3 = r3.a()
            int r3 = com.xiaomi.push.er.a(r3)
            if (r2 == 0) goto L53
            boolean r0 = r2 instanceof com.xiaomi.push.ie     // Catch: java.lang.Exception -> L4e
            if (r0 == 0) goto L37
            com.xiaomi.push.ie r2 = (com.xiaomi.push.ie) r2     // Catch: java.lang.Exception -> L4e
            java.lang.String r2 = r2.b()     // Catch: java.lang.Exception -> L4e
            boolean r0 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Exception -> L4e
            if (r0 != 0) goto L33
            int r0 = com.xiaomi.push.fb.a(r2)     // Catch: java.lang.Exception -> L4e
            if (r0 == r1) goto L33
        L2e:
            int r2 = com.xiaomi.push.fb.a(r2)     // Catch: java.lang.Exception -> L4e
            goto L34
        L33:
            r2 = r3
        L34:
            r1 = r2
            goto Lbd
        L37:
            boolean r0 = r2 instanceof com.xiaomi.push.id     // Catch: java.lang.Exception -> L4e
            if (r0 == 0) goto L53
            com.xiaomi.push.id r2 = (com.xiaomi.push.id) r2     // Catch: java.lang.Exception -> L4e
            java.lang.String r2 = r2.a()     // Catch: java.lang.Exception -> L4e
            boolean r0 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Exception -> L4e
            if (r0 != 0) goto L53
            int r0 = com.xiaomi.push.fb.a(r2)     // Catch: java.lang.Exception -> L4e
            if (r0 == r1) goto L53
            goto L2e
        L4e:
            java.lang.String r2 = "PERF_ERROR : parse Command type error"
            com.xiaomi.channel.commonutils.logger.b.d(r2)
        L53:
            r1 = r3
            goto Lbd
        L55:
            int r3 = r3.a()
            int r3 = com.xiaomi.push.er.a(r3)
            if (r2 == 0) goto L53
            boolean r0 = r2 instanceof com.xiaomi.push.ia     // Catch: java.lang.Exception -> Lae
            if (r0 == 0) goto L80
            com.xiaomi.push.ia r2 = (com.xiaomi.push.ia) r2     // Catch: java.lang.Exception -> Lae
            java.lang.String r2 = r2.f596d     // Catch: java.lang.Exception -> Lae
            boolean r0 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Exception -> Lae
            if (r0 != 0) goto L33
            com.xiaomi.push.ht r0 = com.xiaomi.push.er.m291a(r2)     // Catch: java.lang.Exception -> Lae
            int r0 = com.xiaomi.push.er.a(r0)     // Catch: java.lang.Exception -> Lae
            if (r0 == r1) goto L33
            com.xiaomi.push.ht r2 = com.xiaomi.push.er.m291a(r2)     // Catch: java.lang.Exception -> Lae
            int r2 = com.xiaomi.push.er.a(r2)     // Catch: java.lang.Exception -> Lae
            goto L34
        L80:
            boolean r0 = r2 instanceof com.xiaomi.push.ii     // Catch: java.lang.Exception -> Lae
            if (r0 == 0) goto L53
            com.xiaomi.push.ii r2 = (com.xiaomi.push.ii) r2     // Catch: java.lang.Exception -> Lae
            java.lang.String r2 = r2.f655d     // Catch: java.lang.Exception -> Lae
            boolean r0 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Exception -> Lae
            if (r0 != 0) goto L53
            com.xiaomi.push.ht r0 = com.xiaomi.push.er.m291a(r2)     // Catch: java.lang.Exception -> Lae
            int r0 = com.xiaomi.push.er.a(r0)     // Catch: java.lang.Exception -> Lae
            if (r0 == r1) goto La1
            com.xiaomi.push.ht r0 = com.xiaomi.push.er.m291a(r2)     // Catch: java.lang.Exception -> Lae
            int r0 = com.xiaomi.push.er.a(r0)     // Catch: java.lang.Exception -> Lae
            r3 = r0
        La1:
            com.xiaomi.push.ht r0 = com.xiaomi.push.ht.UploadTinyData     // Catch: java.lang.Exception -> Lae
            com.xiaomi.push.ht r2 = com.xiaomi.push.er.m291a(r2)     // Catch: java.lang.Exception -> Lae
            boolean r2 = r0.equals(r2)     // Catch: java.lang.Exception -> Lae
            if (r2 == 0) goto L53
            goto Lbd
        Lae:
            r1 = r3
            java.lang.String r2 = "PERF_ERROR : parse Notification type error"
            com.xiaomi.channel.commonutils.logger.b.d(r2)
            goto Lbd
        Lb5:
            int r2 = r3.a()
            int r1 = com.xiaomi.push.er.a(r2)
        Lbd:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.da.a(com.xiaomi.push.iu, com.xiaomi.push.hj):int");
    }

    public static void a(String str, Context context, int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return;
        }
        int iA = a(context, i2);
        if (i != er.a(ht.UploadTinyData)) {
            es.a(context.getApplicationContext()).a(str, i, 1L, iA);
        }
    }

    public static void a(String str, Context context, Cif cif, int i) {
        hj hjVarA;
        if (context == null || cif == null || (hjVarA = cif.a()) == null) {
            return;
        }
        int iA = a(hjVarA);
        if (i <= 0) {
            byte[] bArrA = it.a(cif);
            i = bArrA != null ? bArrA.length : 0;
        }
        a(str, context, iA, i);
    }

    public static void a(String str, Context context, iu iuVar, hj hjVar, int i) {
        a(str, context, a(iuVar, hjVar), i);
    }

    public static void a(String str, Context context, byte[] bArr) {
        if (context == null || bArr == null || bArr.length <= 0) {
            return;
        }
        Cif cif = new Cif();
        try {
            it.a(cif, bArr);
            a(str, context, cif, bArr.length);
        } catch (iz unused) {
            com.xiaomi.channel.commonutils.logger.b.m22a("fail to convert bytes to container");
        }
    }
}
