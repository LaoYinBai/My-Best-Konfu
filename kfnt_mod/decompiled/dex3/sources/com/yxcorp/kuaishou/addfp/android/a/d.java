package com.yxcorp.kuaishou.addfp.android.a;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.message.MsgConstant;
import com.yxcorp.kuaishou.addfp.KWEGIDDFP;
import com.yxcorp.kuaishou.addfp.android.Orange;
import com.yxcorp.kuaishou.addfp.android.b.f;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class d {
    private static String a = "";
    private static String b = "";

    public static long a() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return statFs.getBlockCount() * statFs.getBlockSize();
        } catch (Throwable unused) {
            return 0L;
        }
    }

    public static String a(Context context) {
        try {
            if (!com.yxcorp.kuaishou.addfp.android.b.e.a(context, new String[]{MsgConstant.PERMISSION_READ_PHONE_STATE})) {
                return "KWE_PN";
            }
            String simSerialNumber = "";
            if (Build.VERSION.SDK_INT < 29) {
                try {
                    simSerialNumber = f.a(context).a.b();
                } catch (Throwable unused) {
                }
                if (TextUtils.isEmpty(simSerialNumber) || simSerialNumber.startsWith("KWE")) {
                    simSerialNumber = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
                }
                return TextUtils.isEmpty(simSerialNumber) ? "KWE_N" : simSerialNumber;
            }
            String[] strArrA = com.yxcorp.kuaishou.addfp.android.b.b.a(context);
            Arrays.sort(strArrA);
            for (int i = 0; i < 2; i++) {
                String str = strArrA[i];
                if (!TextUtils.isEmpty(str) && !str.startsWith("KWE")) {
                    simSerialNumber = str;
                }
            }
            return simSerialNumber;
        } catch (Throwable unused2) {
            return "KWE_PE";
        }
    }

    public static String a(Context context, String str) {
        try {
            return Settings.System.getString(context.getContentResolver(), str);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static boolean a(Context context, String str, String str2) {
        try {
            return Settings.System.putString(context.getContentResolver(), str, str2);
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0040 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long b() throws java.lang.Throwable {
        /*
            java.lang.String r0 = "/proc/meminfo"
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L29 java.lang.Throwable -> L2e
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L29 java.lang.Throwable -> L2e
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L29 java.lang.Throwable -> L2e
            r3 = 8192(0x2000, float:1.148E-41)
            r0.<init>(r2, r3)     // Catch: java.lang.Throwable -> L29 java.lang.Throwable -> L2e
            java.lang.String r1 = r0.readLine()     // Catch: java.lang.Throwable -> L27 java.lang.Throwable -> L3d
            java.lang.String r2 = "\\s+"
            java.lang.String[] r1 = r1.split(r2)     // Catch: java.lang.Throwable -> L27 java.lang.Throwable -> L3d
            r2 = 1
            r1 = r1[r2]     // Catch: java.lang.Throwable -> L27 java.lang.Throwable -> L3d
            long r1 = java.lang.Long.parseLong(r1)     // Catch: java.lang.Throwable -> L27 java.lang.Throwable -> L3d
            r3 = 10
            long r1 = r1 << r3
            r0.close()     // Catch: java.io.IOException -> L26
        L26:
            return r1
        L27:
            r1 = move-exception
            goto L32
        L29:
            r0 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L3e
        L2e:
            r0 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L32:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L3d
            if (r0 == 0) goto L3a
            r0.close()     // Catch: java.io.IOException -> L3a
        L3a:
            r0 = 0
            return r0
        L3d:
            r1 = move-exception
        L3e:
            if (r0 == 0) goto L43
            r0.close()     // Catch: java.io.IOException -> L43
        L43:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yxcorp.kuaishou.addfp.android.a.d.b():long");
    }

    public static String b(Context context) {
        TelephonyManager telephonyManager;
        try {
            if (com.yxcorp.kuaishou.addfp.android.b.e.a(context, new String[]{MsgConstant.PERMISSION_READ_PHONE_STATE}) && Build.VERSION.SDK_INT < 29) {
                String strA = "";
                try {
                    strA = f.a(context).a.a();
                } catch (Throwable unused) {
                }
                if ((!TextUtils.isEmpty(strA) && !strA.startsWith("KWE")) || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
                    return strA;
                }
                String deviceId = telephonyManager.getDeviceId();
                return TextUtils.isEmpty(deviceId) ? "" : deviceId;
            }
            return "";
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static synchronized String c() {
        try {
            if (!TextUtils.isEmpty(a)) {
                return a;
            }
            String strGMas = Orange.getInstance().gMas();
            new StringBuilder("nammm :").append(strGMas);
            if (TextUtils.isEmpty(strGMas)) {
                return "KWE_OTHER";
            }
            a = strGMas;
            return strGMas;
        } catch (Throwable th) {
            throw th;
        }
    }

    public static String c(Context context) {
        try {
            return context.getPackageName().replace("=", "").replace("&", "");
        } catch (Throwable th) {
            th.printStackTrace();
            return "KWE_PE";
        }
    }

    public static synchronized String d() {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        String strGMas = Orange.getInstance().gMas();
        new StringBuilder("user dis :").append(strGMas);
        if (!TextUtils.isEmpty(strGMas)) {
            byte[] bytes = strGMas.getBytes();
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (bytes[i] ^ 165);
            }
            b = "fuels:" + Base64.encodeToString(bytes, 0).replace("\n", "").replace("\r", "");
        }
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        b = "KWE_N";
        return "KWE_N";
    }

    public static String d(Context context) {
        try {
            String eGidLocal = KWEGIDDFP.instance().getEGidLocal(context, b.a.b(), false);
            return !TextUtils.isEmpty(eGidLocal) ? eGidLocal : "KWE_N";
        } catch (Throwable th) {
            th.printStackTrace();
            return "KWE_PE";
        }
    }

    public static String e(Context context) {
        String str = "KWE_N";
        try {
            String strA = a(context, "k_w_o_d_out_dtt");
            String strB = "";
            if (TextUtils.isEmpty(strA)) {
                boolean zB = com.yxcorp.kuaishou.addfp.android.b.e.b(context, new String[]{"android.permission.READ_EXTERNAL_STORAGE", MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE});
                com.yxcorp.kuaishou.addfp.android.b.b.a();
                if (zB) {
                    strB = c.a(context).b("Lm91a2R0ZnQ=");
                    if (!TextUtils.isEmpty(strB)) {
                        a(context, "k_w_o_d_out_dtt", strB);
                    }
                } else {
                    str = "KWE_PN";
                }
            } else {
                boolean zB2 = com.yxcorp.kuaishou.addfp.android.b.e.b(context, new String[]{"android.permission.READ_EXTERNAL_STORAGE", MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE});
                com.yxcorp.kuaishou.addfp.android.b.b.a();
                if (zB2) {
                    strB = c.a(context).b("Lm91a2R0ZnQ=");
                    if (TextUtils.isEmpty(strB)) {
                        c.a(context, strA);
                    }
                }
            }
            if (TextUtils.isEmpty(strA) && TextUtils.isEmpty(strB)) {
                return "KWE_N";
            }
            LinkedHashMap linkedHashMapA = c.a(context).a(strA);
            if (linkedHashMapA == null || linkedHashMapA.size() == 0) {
                linkedHashMapA = c.a(context).a(strB);
            }
            if (linkedHashMapA == null || linkedHashMapA.size() <= 0) {
                return str;
            }
            int i = 0;
            ListIterator listIterator = new ArrayList(linkedHashMapA.entrySet()).listIterator(linkedHashMapA.size());
            JSONObject jSONObject = new JSONObject();
            while (listIterator.hasPrevious() && i < 10) {
                try {
                    Map.Entry entry = (Map.Entry) listIterator.previous();
                    if (entry != null) {
                        jSONObject.put((String) entry.getKey(), entry.getValue());
                    }
                    i++;
                } catch (Throwable unused) {
                }
            }
            return jSONObject.length() > 0 ? jSONObject.toString() : str;
        } catch (Throwable th) {
            th.printStackTrace();
            return "KWE_PE";
        }
    }
}
