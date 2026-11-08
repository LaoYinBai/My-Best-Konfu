package com.cmic.sso.sdk.d;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import com.cmic.sso.sdk.a.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class j {

    /* renamed from: b, reason: collision with root package name */
    @SuppressLint({"StaticFieldLeak"})
    private static j f5459b;

    /* renamed from: a, reason: collision with root package name */
    private Context f5460a;

    public static void a(Context context) {
        f5459b = new j(context);
    }

    private j(Context context) {
        this.f5460a = context;
    }

    public static j a() {
        return f5459b;
    }

    public String b() {
        try {
            b.C0161b c0161bB = com.cmic.sso.sdk.a.b.a().b();
            return c0161bB.g(c0161bB.f());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @SuppressLint({"MissingPermission"})
    public String a(boolean z) {
        TelephonyManager telephonyManager;
        String subscriberId;
        b.C0161b c0161bB = com.cmic.sso.sdk.a.b.a().b();
        String strI = c0161bB.i(c0161bB.f());
        if (TextUtils.isEmpty(strI) && n.a(this.f5460a) && (telephonyManager = (TelephonyManager) this.f5460a.getSystemService("phone")) != null) {
            strI = telephonyManager.getSimOperator();
            if (TextUtils.isEmpty(strI) && g.a(this.f5460a, "android.permission.READ_PHONE_STATE") && n.d()) {
                try {
                    subscriberId = telephonyManager.getSubscriberId();
                } catch (Exception unused) {
                    c.a("SIMUtils", "getOperator失败");
                    subscriberId = null;
                }
                if (!TextUtils.isEmpty(subscriberId) && subscriberId.length() >= 5) {
                    strI = subscriberId.substring(0, 5);
                }
            }
        }
        c.b("SIMUtils", "operator: " + strI);
        if (TextUtils.isEmpty(strI)) {
            return z ? OnekeyLoginConstants.CU_RESULT_SUCCESS : "";
        }
        return a(strI);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String a(java.lang.String r3) {
        /*
            r2 = this;
            int r0 = r3.hashCode()
            r1 = 49679479(0x2f60c77, float:3.6153606E-37)
            if (r0 == r1) goto L6f
            r1 = 49679502(0x2f60c8e, float:3.6153657E-37)
            if (r0 == r1) goto L64
            switch(r0) {
                case 49679470: goto L5a;
                case 49679471: goto L50;
                case 49679472: goto L46;
                case 49679473: goto L3c;
                case 49679474: goto L32;
                case 49679475: goto L27;
                case 49679476: goto L1d;
                case 49679477: goto L13;
                default: goto L11;
            }
        L11:
            goto L79
        L13:
            java.lang.String r0 = "46007"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L79
            r3 = 2
            goto L7a
        L1d:
            java.lang.String r0 = "46006"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L79
            r3 = 5
            goto L7a
        L27:
            java.lang.String r0 = "46005"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L79
            r3 = 8
            goto L7a
        L32:
            java.lang.String r0 = "46004"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L79
            r3 = 3
            goto L7a
        L3c:
            java.lang.String r0 = "46003"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L79
            r3 = 7
            goto L7a
        L46:
            java.lang.String r0 = "46002"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L79
            r3 = 1
            goto L7a
        L50:
            java.lang.String r0 = "46001"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L79
            r3 = 4
            goto L7a
        L5a:
            java.lang.String r0 = "46000"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L79
            r3 = 0
            goto L7a
        L64:
            java.lang.String r0 = "46011"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L79
            r3 = 9
            goto L7a
        L6f:
            java.lang.String r0 = "46009"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L79
            r3 = 6
            goto L7a
        L79:
            r3 = -1
        L7a:
            switch(r3) {
                case 0: goto L94;
                case 1: goto L94;
                case 2: goto L94;
                case 3: goto L94;
                case 4: goto L8a;
                case 5: goto L8a;
                case 6: goto L8a;
                case 7: goto L80;
                case 8: goto L80;
                case 9: goto L80;
                default: goto L7d;
            }
        L7d:
            java.lang.String r3 = "0"
            return r3
        L80:
            java.lang.String r3 = "SIMUtils"
            java.lang.String r0 = "中国电信"
            com.cmic.sso.sdk.d.c.a(r3, r0)
            java.lang.String r3 = "3"
            return r3
        L8a:
            java.lang.String r3 = "SIMUtils"
            java.lang.String r0 = "中国联通"
            com.cmic.sso.sdk.d.c.a(r3, r0)
            java.lang.String r3 = "2"
            return r3
        L94:
            java.lang.String r3 = "SIMUtils"
            java.lang.String r0 = "中国移动"
            com.cmic.sso.sdk.d.c.a(r3, r0)
            java.lang.String r3 = "1"
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cmic.sso.sdk.d.j.a(java.lang.String):java.lang.String");
    }

    @SuppressLint({"MissingPermission"})
    public String b(boolean z) {
        try {
            if (!n.d()) {
                return com.baidu.mobads.sdk.internal.a.f3089a;
            }
            b.C0161b c0161bB = com.cmic.sso.sdk.a.b.a().b();
            String strH = c0161bB.h(c0161bB.f());
            if (TextUtils.isEmpty(strH) && z) {
                strH = ((TelephonyManager) this.f5460a.getSystemService("phone")).getDeviceId();
            }
            c.b("SIMUtils", "imei is " + strH);
            return TextUtils.isEmpty(strH) ? com.baidu.mobads.sdk.internal.a.f3089a : strH;
        } catch (Exception e) {
            c.a("SIMUtils", e.getMessage());
            return com.baidu.mobads.sdk.internal.a.f3089a;
        }
    }

    public String c() {
        try {
            b.C0161b c0161bB = com.cmic.sso.sdk.a.b.a().b();
            String strG = c0161bB.g((c0161bB.f() + 1) % 2);
            return strG == null ? "" : strG;
        } catch (Exception unused) {
            return "";
        }
    }

    public String d() {
        try {
            b.C0161b c0161bB = com.cmic.sso.sdk.a.b.a().b();
            String strH = c0161bB.h((c0161bB.f() + 1) % 2);
            return strH == null ? "" : strH;
        } catch (Exception unused) {
            return "";
        }
    }

    @SuppressLint({"MissingPermission"})
    public static String e() {
        b.C0161b c0161bB = com.cmic.sso.sdk.a.b.a().b();
        return c0161bB.a(c0161bB.f());
    }

    public String f() {
        TelephonyManager telephonyManager = (TelephonyManager) this.f5460a.getSystemService("phone");
        if (telephonyManager == null) {
            return OnekeyLoginConstants.CU_RESULT_SUCCESS;
        }
        String simOperator = telephonyManager.getSimOperator();
        c.b("SIMUtils", "SysOperType = " + simOperator);
        return a(simOperator);
    }
}
