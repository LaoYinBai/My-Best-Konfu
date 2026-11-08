package com.alipay.apmobilesecuritysdk.a;

import android.content.Context;
import android.os.Environment;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.alipay.apmobilesecuritysdk.d.e;
import com.alipay.apmobilesecuritysdk.e.b;
import com.alipay.apmobilesecuritysdk.e.g;
import com.alipay.apmobilesecuritysdk.e.h;
import com.alipay.apmobilesecuritysdk.e.i;
import com.alipay.apmobilesecuritysdk.otherid.UmidSdkWrapper;
import com.alipay.security.mobile.module.http.model.c;
import com.alipay.security.mobile.module.http.model.d;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private Context f2486a;

    /* renamed from: b, reason: collision with root package name */
    private com.alipay.apmobilesecuritysdk.b.a f2487b = com.alipay.apmobilesecuritysdk.b.a.a();

    /* renamed from: c, reason: collision with root package name */
    private int f2488c = 4;

    public a(Context context) {
        this.f2486a = context;
    }

    public static String a(Context context) {
        String strB = b(context);
        return com.alipay.security.mobile.module.a.a.a(strB) ? h.f(context) : strB;
    }

    public static String a(Context context, String str) {
        try {
            b();
            String strA = i.a(str);
            if (!com.alipay.security.mobile.module.a.a.a(strA)) {
                return strA;
            }
            String strA2 = g.a(context, str);
            i.a(str, strA2);
            return !com.alipay.security.mobile.module.a.a.a(strA2) ? strA2 : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    private static boolean a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] strArr = {"2017-01-27 2017-01-28", "2017-11-10 2017-11-11", "2017-12-11 2017-12-12"};
        int iRandom = ((int) (Math.random() * 24.0d * 60.0d * 60.0d)) * 1;
        for (int i = 0; i < 3; i++) {
            try {
                String[] strArrSplit = strArr[i].split(" ");
                if (strArrSplit != null && strArrSplit.length == 2) {
                    Date date = new Date();
                    Date date2 = simpleDateFormat.parse(strArrSplit[0] + " 00:00:00");
                    Date date3 = simpleDateFormat.parse(strArrSplit[1] + " 23:59:59");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date3);
                    calendar.add(13, iRandom);
                    Date time = calendar.getTime();
                    if (date.after(date2) && date.before(time)) {
                        return true;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }

    private c b(Map<String, String> map) {
        b bVarB;
        b bVarC;
        try {
            Context context = this.f2486a;
            d dVar = new d();
            String strA = com.alipay.security.mobile.module.a.a.a(map, DispatchConstants.APP_NAME, "");
            String strA2 = com.alipay.security.mobile.module.a.a.a(map, "sessionId", "");
            String strA3 = com.alipay.security.mobile.module.a.a.a(map, "rpcVersion", "");
            String strA4 = a(context, strA);
            String securityToken = UmidSdkWrapper.getSecurityToken(context);
            String strD = h.d(context);
            if (com.alipay.security.mobile.module.a.a.b(strA2)) {
                dVar.f2735c = strA2;
            } else {
                dVar.f2735c = strA4;
            }
            dVar.d = securityToken;
            dVar.e = strD;
            dVar.f2733a = DispatchConstants.ANDROID;
            String str = "";
            String str2 = "";
            String str3 = "";
            String str4 = "";
            com.alipay.apmobilesecuritysdk.e.c cVarC = com.alipay.apmobilesecuritysdk.e.d.c(context);
            if (cVarC != null) {
                str2 = cVarC.f2496a;
                str3 = cVarC.f2498c;
            }
            if (com.alipay.security.mobile.module.a.a.a(str2) && (bVarC = com.alipay.apmobilesecuritysdk.e.a.c(context)) != null) {
                str2 = bVarC.f2493a;
                str3 = bVarC.f2495c;
            }
            com.alipay.apmobilesecuritysdk.e.c cVarB = com.alipay.apmobilesecuritysdk.e.d.b();
            if (cVarB != null) {
                str = cVarB.f2496a;
                str4 = cVarB.f2498c;
            }
            if (com.alipay.security.mobile.module.a.a.a(str) && (bVarB = com.alipay.apmobilesecuritysdk.e.a.b()) != null) {
                str = bVarB.f2493a;
                str4 = bVarB.f2495c;
            }
            dVar.h = str2;
            dVar.g = str;
            dVar.j = strA3;
            if (com.alipay.security.mobile.module.a.a.a(str2)) {
                dVar.f2734b = str;
                dVar.i = str4;
            } else {
                dVar.f2734b = str2;
                dVar.i = str3;
            }
            dVar.f = e.a(context, map);
            return com.alipay.security.mobile.module.http.d.b(this.f2486a, this.f2487b.c()).a(dVar);
        } catch (Throwable th) {
            th.printStackTrace();
            com.alipay.apmobilesecuritysdk.c.a.a(th);
            return null;
        }
    }

    private static String b(Context context) {
        try {
            String strB = i.b();
            if (!com.alipay.security.mobile.module.a.a.a(strB)) {
                return strB;
            }
            com.alipay.apmobilesecuritysdk.e.c cVarB = com.alipay.apmobilesecuritysdk.e.d.b(context);
            if (cVarB != null) {
                i.a(cVarB);
                String str = cVarB.f2496a;
                if (com.alipay.security.mobile.module.a.a.b(str)) {
                    return str;
                }
            }
            b bVarB = com.alipay.apmobilesecuritysdk.e.a.b(context);
            if (bVarB == null) {
                return "";
            }
            i.a(bVarB);
            String str2 = bVarB.f2493a;
            return com.alipay.security.mobile.module.a.a.b(str2) ? str2 : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    private static void b() {
        try {
            String[] strArr = {"device_feature_file_name", "wallet_times", "wxcasxx_v3", "wxcasxx_v4", "wxxzyy_v1"};
            for (int i = 0; i < 5; i++) {
                String str = strArr[i];
                File file = new File(Environment.getExternalStorageDirectory(), ".SystemConfig/" + str);
                if (file.exists() && file.canWrite()) {
                    file.delete();
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x0214 A[Catch: Exception -> 0x0251, TryCatch #0 {Exception -> 0x0251, blocks: (B:2:0x0000, B:4:0x003b, B:7:0x0045, B:36:0x00cb, B:67:0x01f9, B:69:0x0214, B:71:0x021a, B:73:0x0220, B:77:0x0229, B:79:0x022f, B:39:0x00e0, B:41:0x00f8, B:47:0x0105, B:48:0x0115, B:50:0x011c, B:54:0x012e, B:56:0x0182, B:58:0x018c, B:60:0x0194, B:62:0x01a5, B:64:0x01af, B:66:0x01b7, B:65:0x01b3, B:59:0x0190, B:10:0x005a, B:12:0x0070, B:15:0x007b, B:17:0x0081, B:20:0x008c, B:23:0x0095, B:26:0x00a2, B:29:0x00af, B:32:0x00bd), top: B:85:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x021a A[Catch: Exception -> 0x0251, TryCatch #0 {Exception -> 0x0251, blocks: (B:2:0x0000, B:4:0x003b, B:7:0x0045, B:36:0x00cb, B:67:0x01f9, B:69:0x0214, B:71:0x021a, B:73:0x0220, B:77:0x0229, B:79:0x022f, B:39:0x00e0, B:41:0x00f8, B:47:0x0105, B:48:0x0115, B:50:0x011c, B:54:0x012e, B:56:0x0182, B:58:0x018c, B:60:0x0194, B:62:0x01a5, B:64:0x01af, B:66:0x01b7, B:65:0x01b3, B:59:0x0190, B:10:0x005a, B:12:0x0070, B:15:0x007b, B:17:0x0081, B:20:0x008c, B:23:0x0095, B:26:0x00a2, B:29:0x00af, B:32:0x00bd), top: B:85:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0229 A[Catch: Exception -> 0x0251, TryCatch #0 {Exception -> 0x0251, blocks: (B:2:0x0000, B:4:0x003b, B:7:0x0045, B:36:0x00cb, B:67:0x01f9, B:69:0x0214, B:71:0x021a, B:73:0x0220, B:77:0x0229, B:79:0x022f, B:39:0x00e0, B:41:0x00f8, B:47:0x0105, B:48:0x0115, B:50:0x011c, B:54:0x012e, B:56:0x0182, B:58:0x018c, B:60:0x0194, B:62:0x01a5, B:64:0x01af, B:66:0x01b7, B:65:0x01b3, B:59:0x0190, B:10:0x005a, B:12:0x0070, B:15:0x007b, B:17:0x0081, B:20:0x008c, B:23:0x0095, B:26:0x00a2, B:29:0x00af, B:32:0x00bd), top: B:85:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int a(java.util.Map<java.lang.String, java.lang.String> r8) {
        /*
            Method dump skipped, instructions count: 600
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.apmobilesecuritysdk.a.a.a(java.util.Map):int");
    }
}
