package com.unicom.xiaowo.login.c;

import android.content.Context;
import android.net.Network;
import android.os.Handler;
import android.os.Looper;
import com.ss.android.downloadlib.constants.DownloadConstants;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class e {
    private static volatile e a;
    private Handler b = new Handler(Looper.getMainLooper());
    private a c = new a();

    private e() {
    }

    public static e a() {
        if (a == null) {
            synchronized (e.class) {
                if (a == null) {
                    a = new e();
                }
            }
        }
        return a;
    }

    private static String a(Context context) throws JSONException, UnsupportedEncodingException {
        try {
            String packageName = context.getPackageName();
            String strA = com.unicom.xiaowo.login.d.h.a(context, context.getPackageName());
            if (packageName == null) {
                packageName = "";
            }
            if (strA == null) {
                strA = "";
            }
            String strB = com.unicom.xiaowo.login.d.g.b();
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            String string = sb.toString();
            String strA2 = com.unicom.xiaowo.login.a.a.a();
            com.unicom.xiaowo.login.d.g.a(strA2);
            String strDecode = URLDecoder.decode(com.unicom.xiaowo.login.d.h.d(strA2), "utf-8");
            String str = "1" + strB + "30100jsonp" + strDecode + packageName + strA + string + "3.1.0AL02B0307" + com.unicom.xiaowo.login.d.g.c();
            com.unicom.xiaowo.login.d.f.a("unsign:" + str);
            String strA3 = com.unicom.xiaowo.login.d.h.a(str);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("client_id", strB);
            jSONObject.put("client_type", "30100");
            jSONObject.put("format", "jsonp");
            jSONObject.put("version", "3.1.0AL02B0307");
            jSONObject.put(DownloadConstants.KEY_BUSINESS_TYPE, "1");
            jSONObject.put("packname", packageName);
            jSONObject.put("packsign", URLEncoder.encode(strA, "utf-8"));
            jSONObject.put("timeStamp", string);
            jSONObject.put(com.ss.union.game.sdk.redemptionCode.a.p, strDecode);
            jSONObject.put("sign", strA3);
            return jSONObject.toString();
        } catch (Exception e) {
            com.unicom.xiaowo.login.d.f.b("getPreCheckParam error:" + e.getMessage());
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, Network network, j jVar) {
        try {
            this.c.a(str, (HashMap) null, network, new f(this, jVar));
        } catch (Exception unused) {
            jVar.a(2, "");
            com.unicom.xiaowo.login.d.a.a().b();
        }
    }

    public final void a(Context context, j jVar) {
        try {
            String str = "http://opencloud.wostore.cn/openapi/netauth/precheck/wp?" + com.unicom.xiaowo.login.d.h.a(a(context), "&");
            if (com.unicom.xiaowo.login.d.h.b(context.getApplicationContext()) == 1) {
                this.b.post(new g(this, context, str, jVar));
            } else if (com.unicom.xiaowo.login.d.h.b(context.getApplicationContext()) == 0) {
                a(str, null, jVar);
            } else {
                jVar.a(2, "获取鉴权信息失败");
                com.unicom.xiaowo.login.d.a.a().b();
            }
        } catch (Exception unused) {
            jVar.a(2, "");
            com.unicom.xiaowo.login.d.a.a().b();
        }
    }

    public final void a(String str, String str2, HashMap map, j jVar) {
        try {
            this.c.a("https://opencloud.wostore.cn/authz/oauth/token?timestamp=" + str2, str, map, new i(this, jVar));
        } catch (Exception unused) {
            jVar.a(2, "");
        }
    }
}
