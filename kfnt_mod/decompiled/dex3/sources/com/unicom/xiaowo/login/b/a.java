package com.unicom.xiaowo.login.b;

import android.content.Context;
import android.text.TextUtils;
import com.sigmob.sdk.common.Constants;
import com.tachikoma.core.component.input.InputType;
import com.umeng.message.util.HttpRequest;
import com.unicom.xiaowo.login.c.e;
import com.unicom.xiaowo.login.d.f;
import com.unicom.xiaowo.login.d.g;
import com.unicom.xiaowo.login.d.h;
import java.util.HashMap;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static volatile a a;
    private String b = "";

    private a(Context context) {
        try {
            f.a();
            f.a("init complete");
        } catch (Exception e) {
            f.b("init error:" + e.getMessage());
        }
    }

    public static a a(Context context) {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a(context);
                }
            }
        }
        return a;
    }

    private static String a() {
        try {
            String strA = h.a(UUID.randomUUID().toString());
            try {
                if (TextUtils.isEmpty(strA)) {
                    return Constants.FAIL;
                }
            } catch (Exception unused) {
            }
            return strA;
        } catch (Exception unused2) {
            return "";
        }
    }

    private void a(Context context, int i) {
        e.a().a(context, new b(this, i, context));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, String str, String str2, int i) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("grant_type", InputType.PASSWORD);
            jSONObject.put("username", str);
            jSONObject.put(InputType.PASSWORD, a());
            jSONObject.put("scope", "read,write");
            jSONObject.put("loginmode", Constants.FAIL);
            f.a(jSONObject.toString());
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            this.b = sb.toString();
            e eVarA = e.a();
            String strA = h.a(jSONObject.toString(), "&");
            String str3 = this.b;
            StringBuilder sb2 = new StringBuilder("basic ");
            StringBuilder sb3 = new StringBuilder();
            sb3.append(g.b());
            sb3.append(com.xiaomi.mipush.sdk.Constants.COLON_SEPARATOR);
            sb3.append(h.a(this.b + g.c()).toUpperCase());
            sb2.append(com.unicom.xiaowo.login.a.b.a(sb3.toString().getBytes()));
            String string = sb2.toString();
            HashMap map = new HashMap();
            map.put("accept", "*/*");
            map.put(HttpRequest.PARAM_CHARSET, "UTF-8");
            map.put("version", "3.1.0AL02B0307");
            map.put(HttpRequest.HEADER_AUTHORIZATION, string);
            map.put("packname", context.getPackageName());
            map.put("packsign", h.a(context, context.getPackageName()));
            f.a("header:" + map.toString());
            eVarA.a(strA, str3, map, new c(this, i, str2, context));
        } catch (Exception unused) {
            d.a().a(context, "数据解析异常");
        }
    }

    private static void a(String str, String str2) {
        g.b(str);
        g.c(str2);
    }

    public final void a(Context context, String str, String str2) {
        a(str, str2);
        a(context, 1);
    }

    public final void a(Context context, String str, String str2, String str3) throws JSONException {
        a(str, str2);
        a(context, str3, "", 3);
    }

    public final void b(Context context, String str, String str2) {
        a(str, str2);
        a(context, 2);
    }
}
