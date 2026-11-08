package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import android.content.res.Configuration;
import com.bytedance.msdk.adapter.util.Logger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class v {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f2033a = false;

    /* renamed from: b, reason: collision with root package name */
    private static String f2034b = null;

    /* renamed from: c, reason: collision with root package name */
    private static String f2035c = null;
    private static String d = null;
    private static boolean e = true;

    public static String a() {
        if (!f2033a) {
            d();
        }
        return f2034b;
    }

    public static String b() {
        try {
            if (!f2033a) {
                d();
            }
            Configuration configuration = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d().getResources().getConfiguration();
            String strValueOf = configuration.mcc != 0 ? String.valueOf(configuration.mcc) : f2035c;
            Logger.e("MCC", "config=" + configuration.mcc + ",sMCC=" + f2035c);
            if (e) {
                return strValueOf;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("getMCC");
            sb.append(e ? "有SIM卡" : "无SIM卡,MCC返回null");
            Logger.e("MCC", sb.toString());
            return "";
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static String c() {
        if (!f2033a) {
            d();
        }
        return d;
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0074 A[Catch: all -> 0x0086, TryCatch #4 {all -> 0x0086, blocks: (B:8:0x000c, B:33:0x0049, B:39:0x0056, B:41:0x005c, B:43:0x0063, B:45:0x006e, B:47:0x0074, B:48:0x0076, B:50:0x007c, B:51:0x007e, B:53:0x0084, B:24:0x0037, B:10:0x0019, B:17:0x0029, B:21:0x0032, B:14:0x0022, B:15:0x0025), top: B:66:0x000c, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x007c A[Catch: all -> 0x0086, TryCatch #4 {all -> 0x0086, blocks: (B:8:0x000c, B:33:0x0049, B:39:0x0056, B:41:0x005c, B:43:0x0063, B:45:0x006e, B:47:0x0074, B:48:0x0076, B:50:0x007c, B:51:0x007e, B:53:0x0084, B:24:0x0037, B:10:0x0019, B:17:0x0029, B:21:0x0032, B:14:0x0022, B:15:0x0025), top: B:66:0x000c, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0084 A[Catch: all -> 0x0086, TRY_LEAVE, TryCatch #4 {all -> 0x0086, blocks: (B:8:0x000c, B:33:0x0049, B:39:0x0056, B:41:0x005c, B:43:0x0063, B:45:0x006e, B:47:0x0074, B:48:0x0076, B:50:0x007c, B:51:0x007e, B:53:0x0084, B:24:0x0037, B:10:0x0019, B:17:0x0029, B:21:0x0032, B:14:0x0022, B:15:0x0025), top: B:66:0x000c, inners: #3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void d() {
        /*
            android.content.Context r0 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()
            if (r0 != 0) goto L7
            return
        L7:
            boolean r0 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.v.f2033a
            if (r0 != 0) goto L88
            r0 = 1
            android.content.Context r1 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()     // Catch: java.lang.Throwable -> L86
            java.lang.String r2 = "phone"
            java.lang.Object r1 = r1.getSystemService(r2)     // Catch: java.lang.Throwable -> L86
            android.telephony.TelephonyManager r1 = (android.telephony.TelephonyManager) r1     // Catch: java.lang.Throwable -> L86
            r2 = 0
            int r3 = r1.getSimState()     // Catch: java.lang.Throwable -> L36
            if (r3 == 0) goto L25
            if (r3 == r0) goto L22
            goto L27
        L22:
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.v.e = r2     // Catch: java.lang.Throwable -> L36
            goto L27
        L25:
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.v.e = r2     // Catch: java.lang.Throwable -> L36
        L27:
            java.lang.String r3 = "MCC"
            boolean r4 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.v.e     // Catch: java.lang.Throwable -> L36
            if (r4 == 0) goto L30
            java.lang.String r4 = "有SIM卡"
            goto L32
        L30:
            java.lang.String r4 = "无SIM卡"
        L32:
            com.bytedance.msdk.adapter.util.Logger.e(r3, r4)     // Catch: java.lang.Throwable -> L36
            goto L3a
        L36:
            r3 = move-exception
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L86
        L3a:
            r3 = 0
            java.lang.String r4 = r1.getSimOperatorName()     // Catch: java.lang.Throwable -> L40
            goto L41
        L40:
            r4 = r3
        L41:
            java.lang.String r5 = r1.getNetworkOperator()     // Catch: java.lang.Throwable -> L46
            goto L47
        L46:
            r5 = r3
        L47:
            if (r5 == 0) goto L50
            int r6 = r5.length()     // Catch: java.lang.Throwable -> L86
            r7 = 5
            if (r6 >= r7) goto L55
        L50:
            java.lang.String r1 = r1.getSimOperator()     // Catch: java.lang.Throwable -> L55
            goto L56
        L55:
            r1 = r5
        L56:
            boolean r5 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L86
            if (r5 != 0) goto L6d
            int r5 = r1.length()     // Catch: java.lang.Throwable -> L86
            r6 = 4
            if (r5 <= r6) goto L6d
            r3 = 3
            java.lang.String r2 = r1.substring(r2, r3)     // Catch: java.lang.Throwable -> L86
            java.lang.String r3 = r1.substring(r3)     // Catch: java.lang.Throwable -> L86
            goto L6e
        L6d:
            r2 = r3
        L6e:
            boolean r1 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L86
            if (r1 != 0) goto L76
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.v.f2034b = r4     // Catch: java.lang.Throwable -> L86
        L76:
            boolean r1 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L86
            if (r1 != 0) goto L7e
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.v.f2035c = r2     // Catch: java.lang.Throwable -> L86
        L7e:
            boolean r1 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Throwable -> L86
            if (r1 != 0) goto L86
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.v.d = r3     // Catch: java.lang.Throwable -> L86
        L86:
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.v.f2033a = r0
        L88:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.v.d():void");
    }
}
