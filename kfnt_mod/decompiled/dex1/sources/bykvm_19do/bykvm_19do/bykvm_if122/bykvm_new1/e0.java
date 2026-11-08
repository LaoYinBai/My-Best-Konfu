package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.bytedance.msdk.adapter.util.Logger;
import java.security.SecureRandom;
import java.util.Locale;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e0 {

    /* renamed from: a, reason: collision with root package name */
    private static String f2002a = null;

    /* renamed from: b, reason: collision with root package name */
    private static String f2003b = null;

    /* renamed from: c, reason: collision with root package name */
    private static String f2004c = null;
    private static String d = "ToolUtils";

    public static synchronized String a() {
        if (!TextUtils.isEmpty(f2002a) || bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d() == null) {
        } else {
            try {
                f2002a = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d().getPackageName();
            } catch (Throwable th) {
                Logger.e(d, "ToolUtils getPackageName throws exception :" + th);
            }
        }
        return f2002a;
    }

    private static String b(String str) {
        String strA = a.a(str);
        if (str != null) {
            return strA;
        }
        String strA2 = b.a();
        return strA2.concat(strA2).substring(8, 24);
    }

    public static String c() {
        String strConcat;
        try {
            strConcat = System.getProperty("http.agent");
        } catch (Exception unused) {
            strConcat = "unKnow";
        }
        StringBuilder sb = new StringBuilder();
        if (strConcat == null) {
            return "";
        }
        int iLastIndexOf = strConcat.lastIndexOf(com.alipay.sdk.util.f.f2657b);
        if (iLastIndexOf != -1 && strConcat.length() > iLastIndexOf) {
            int i = iLastIndexOf + 1;
            String strSubstring = strConcat.substring(0, i);
            strConcat = strSubstring.concat(" " + Locale.getDefault().getLanguage() + "-" + Locale.getDefault().getCountry() + com.alipay.sdk.util.f.f2657b).concat(strConcat.substring(i));
        }
        int length = strConcat.length();
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = strConcat.charAt(i2);
            if (cCharAt <= 31 || cCharAt >= 127) {
                sb.append(String.format("\\u%04x", Integer.valueOf(cCharAt)));
            } else {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    public static synchronized String d() {
        if (!TextUtils.isEmpty(f2003b) || bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d() == null) {
        } else {
            try {
                PackageInfo packageInfo = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d().getPackageManager().getPackageInfo(a(), 0);
                f2003b = String.valueOf(packageInfo.versionCode);
                f2004c = packageInfo.versionName;
            } catch (Throwable th) {
                Logger.e(d, "ToolUtils getVersionCode throws exception :" + th);
            }
        }
        return f2003b;
    }

    public static synchronized String e() {
        if (!TextUtils.isEmpty(f2004c) || bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d() == null) {
        } else {
            try {
                PackageInfo packageInfo = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d().getPackageManager().getPackageInfo(a(), 0);
                f2003b = String.valueOf(packageInfo.versionCode);
                f2004c = packageInfo.versionName;
            } catch (Throwable th) {
                Logger.e(d, "ToolUtils getVersionName throws exception :" + th);
            }
        }
        return f2004c;
    }

    public static String b() {
        try {
            byte[] bArr = new byte[8];
            new SecureRandom().nextBytes(bArr);
            return l.a(bArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str) || str.length() < 17) {
            return str;
        }
        return a.a(str.substring(17), b(str.substring(1, 17)));
    }

    public static JSONObject a(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        if (jSONObject == null) {
            return jSONObject2;
        }
        try {
            String strA = a.a();
            String str = 2 + strA + a.b(jSONObject.toString(), a.a(strA));
            if (!TextUtils.isEmpty(str)) {
                jSONObject2.put("message", str);
                jSONObject2.put("cypher", 2);
            } else {
                jSONObject2.put("message", jSONObject.toString());
                jSONObject2.put("cypher", 0);
            }
        } catch (Throwable unused) {
            try {
                jSONObject2.put("message", jSONObject.toString());
                jSONObject2.put("cypher", 0);
            } catch (Throwable unused2) {
            }
        }
        return jSONObject2;
    }
}
