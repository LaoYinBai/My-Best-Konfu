package com.taobao.agoo.a.a;

import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.p;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c extends b {
    public static final String JSON_CMD_REGISTER = "register";
    public String a;
    public String b;
    public String c;
    public String d = String.valueOf(Constants.SDK_VERSION_CODE);
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p;

    public byte[] a() {
        try {
            String string = new p.a().a(b.JSON_CMD, this.e).a(Constants.KEY_APP_KEY, this.a).a("utdid", this.b).a(Constants.KEY_APP_VERSION, this.c).a(Constants.KEY_SDK_VERSION, this.d).a(Constants.KEY_TTID, this.f).a("packageName", this.g).a("notifyEnable", this.h).a("romInfo", this.i).a("c0", this.j).a("c1", this.k).a("c2", this.l).a("c3", this.m).a("c4", this.n).a("c5", this.o).a("c6", this.p).a().toString();
            ALog.i("RegisterDO", "buildData", "data", string);
            return string.getBytes("utf-8");
        } catch (Throwable th) {
            ALog.e("RegisterDO", "buildData", th, new Object[0]);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00b9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] a(android.content.Context r8, java.lang.String r9, java.lang.String r10) throws java.lang.Throwable {
        /*
            r0 = 0
            r1 = 1
            r2 = 0
            java.lang.String r3 = com.taobao.accs.utl.UtilityImpl.l(r8)     // Catch: java.lang.Throwable -> L9b java.lang.Throwable -> L9e
            java.lang.String r4 = r8.getPackageName()     // Catch: java.lang.Throwable -> L9b java.lang.Throwable -> L9e
            com.taobao.accs.client.GlobalClientInfo r5 = com.taobao.accs.client.GlobalClientInfo.getInstance(r8)     // Catch: java.lang.Throwable -> L9b java.lang.Throwable -> L9e
            android.content.pm.PackageInfo r5 = r5.getPackageInfo()     // Catch: java.lang.Throwable -> L9b java.lang.Throwable -> L9e
            java.lang.String r5 = r5.versionName     // Catch: java.lang.Throwable -> L9b java.lang.Throwable -> L9e
            boolean r6 = android.text.TextUtils.isEmpty(r9)     // Catch: java.lang.Throwable -> L9b java.lang.Throwable -> L9e
            if (r6 != 0) goto L7a
            boolean r6 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L9b java.lang.Throwable -> L9e
            if (r6 != 0) goto L7a
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> L9b java.lang.Throwable -> L9e
            if (r6 == 0) goto L28
            goto L7a
        L28:
            com.taobao.agoo.a.a.c r6 = new com.taobao.agoo.a.a.c     // Catch: java.lang.Throwable -> L9b java.lang.Throwable -> L9e
            r6.<init>()     // Catch: java.lang.Throwable -> L9b java.lang.Throwable -> L9e
            java.lang.String r7 = "register"
            r6.e = r7     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            r6.a = r9     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            r6.b = r3     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            r6.c = r5     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            r6.f = r10     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            r6.g = r4     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            java.lang.String r9 = android.os.Build.BRAND     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            r6.j = r9     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            java.lang.String r9 = android.os.Build.MODEL     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            r6.k = r9     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            java.lang.String r9 = com.taobao.accs.utl.j.d(r8)     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            r6.h = r9     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            java.lang.String r9 = "ACCS_SDK_CHANNEL"
            java.lang.String r10 = r6.h     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            com.taobao.accs.utl.UtilityImpl.a(r8, r9, r10)     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            com.taobao.accs.utl.o r9 = new com.taobao.accs.utl.o     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            r9.<init>()     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            java.lang.String r9 = r9.a()     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            r6.i = r9     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            java.lang.String r9 = "phone"
            java.lang.Object r8 = r8.getSystemService(r9)     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            android.telephony.TelephonyManager r8 = (android.telephony.TelephonyManager) r8     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            if (r8 == 0) goto L6a
            java.lang.String r9 = r8.getDeviceId()     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            goto L6b
        L6a:
            r9 = r2
        L6b:
            r6.l = r9     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            if (r8 == 0) goto L74
            java.lang.String r8 = r8.getSubscriberId()     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            goto L75
        L74:
            r8 = r2
        L75:
            r6.m = r8     // Catch: java.lang.Throwable -> L78 java.lang.Throwable -> Lb6
            goto Lb1
        L78:
            r8 = move-exception
            goto La0
        L7a:
            java.lang.String r8 = "RegisterDO"
            java.lang.String r10 = "buildRegister param null"
            r4 = 6
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L9b java.lang.Throwable -> L9e
            java.lang.String r6 = "appKey"
            r4[r0] = r6     // Catch: java.lang.Throwable -> L9b java.lang.Throwable -> L9e
            r4[r1] = r9     // Catch: java.lang.Throwable -> L9b java.lang.Throwable -> L9e
            r9 = 2
            java.lang.String r6 = "utdid"
            r4[r9] = r6     // Catch: java.lang.Throwable -> L9b java.lang.Throwable -> L9e
            r9 = 3
            r4[r9] = r3     // Catch: java.lang.Throwable -> L9b java.lang.Throwable -> L9e
            r9 = 4
            java.lang.String r3 = "appVersion"
            r4[r9] = r3     // Catch: java.lang.Throwable -> L9b java.lang.Throwable -> L9e
            r9 = 5
            r4[r9] = r5     // Catch: java.lang.Throwable -> L9b java.lang.Throwable -> L9e
            com.taobao.accs.utl.ALog.e(r8, r10, r4)     // Catch: java.lang.Throwable -> L9b java.lang.Throwable -> L9e
            return r2
        L9b:
            r8 = move-exception
            r6 = r2
            goto Lb7
        L9e:
            r8 = move-exception
            r6 = r2
        La0:
            java.lang.String r9 = "RegisterDO"
            java.lang.String r10 = "buildRegister"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> Lb6
            java.lang.String r8 = r8.getMessage()     // Catch: java.lang.Throwable -> Lb6
            r1[r0] = r8     // Catch: java.lang.Throwable -> Lb6
            com.taobao.accs.utl.ALog.w(r9, r10, r1)     // Catch: java.lang.Throwable -> Lb6
            if (r6 == 0) goto Lb5
        Lb1:
            byte[] r2 = r6.a()
        Lb5:
            return r2
        Lb6:
            r8 = move-exception
        Lb7:
            if (r6 == 0) goto Lbc
            r6.a()
        Lbc:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.agoo.a.a.c.a(android.content.Context, java.lang.String, java.lang.String):byte[]");
    }
}
