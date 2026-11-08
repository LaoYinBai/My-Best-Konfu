package com.cmic.sso.sdk.d;

import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import com.cmic.sso.sdk.d.k;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class q {

    /* renamed from: a, reason: collision with root package name */
    private static volatile boolean f5469a = false;

    public static void a(com.cmic.sso.sdk.a aVar) {
        if (f5469a) {
            c.a("UmcConfigUtil", "正在获取配置中...");
        } else {
            f5469a = true;
            com.cmic.sso.sdk.b.c.a.a().a(aVar.b("isNeedToGetCert", true), aVar, new com.cmic.sso.sdk.b.c.d() { // from class: com.cmic.sso.sdk.d.q.1
                @Override // com.cmic.sso.sdk.b.c.d
                public void a(String str, String str2, JSONObject jSONObject) {
                    try {
                        if (OnekeyLoginConstants.CM_RESULT_SUCCESS.equals(str)) {
                            q.b(jSONObject);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    boolean unused = q.f5469a = false;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b(org.json.JSONObject r13) {
        /*
            Method dump skipped, instructions count: 925
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cmic.sso.sdk.d.q.b(org.json.JSONObject):void");
    }

    private static void a(JSONObject jSONObject, String str, String str2, k.a aVar) {
        if (jSONObject.has(str)) {
            aVar.a(str, jSONObject.optString(str, str2));
        }
    }

    public static boolean a() {
        return System.currentTimeMillis() >= k.a("client_valid", 0L);
    }

    public static boolean b() {
        return "1".equals(k.b("CLOSE_IPV4_LIST", OnekeyLoginConstants.CU_RESULT_SUCCESS));
    }

    public static boolean c() {
        return "1".equals(k.b("CLOSE_IPV6_LIST", OnekeyLoginConstants.CU_RESULT_SUCCESS));
    }

    public static boolean d() {
        return "1".equals(k.b("CLOSE_M001_SDKVERSION_LIST", OnekeyLoginConstants.CU_RESULT_SUCCESS)) || "1".equals(k.b("CLOSE_M001_APPID_LIST", OnekeyLoginConstants.CU_RESULT_SUCCESS)) || "1".equals(k.b("CLOSE_M005_APPID_LIST", OnekeyLoginConstants.CU_RESULT_SUCCESS)) || "1".equals(k.b("CLOSE_M005_SDKVERSION_LIST", OnekeyLoginConstants.CU_RESULT_SUCCESS));
    }

    public static String e() {
        String strB = k.b("httpHost", null);
        if (TextUtils.isEmpty(strB)) {
            return "http://www.cmpassport.com/unisdk";
        }
        return BDAccountNetApi.Platform.PROJECT_MODE_SCHEME + strB + "/unisdk";
    }

    public static String f() {
        String strB = k.b("httpsHost", null);
        if (TextUtils.isEmpty(strB)) {
            return "https://onekey1.cmpassport.com/unisdk";
        }
        return "https://" + strB + "/unisdk";
    }

    public static String g() {
        String strB = k.b("logHost", "");
        if (TextUtils.isEmpty(strB)) {
            return "https://log1.cmpassport.com:9443/log/logReport";
        }
        return "https://" + strB + "/log/logReport";
    }

    public static boolean h() {
        return "1".equals(k.b("CLOSE_CERT_VERIFY", "1"));
    }

    public static boolean i() {
        return k.b("CLOSE_FRIEND_WAPKS", "").contains("CU");
    }

    public static boolean j() {
        return k.b("CLOSE_FRIEND_WAPKS", "").contains("CT");
    }

    public static boolean k() {
        return "1".equals(k.b("CLOSE_LOGS_VERSION", OnekeyLoginConstants.CU_RESULT_SUCCESS));
    }

    public static int l() {
        return k.a("maxFailedLogTimes", 0);
    }

    public static int m() {
        return k.a("pauseTime", 0) * 60 * 60 * 1000;
    }

    public static String a(String str) {
        return k.b(str, "");
    }
}
