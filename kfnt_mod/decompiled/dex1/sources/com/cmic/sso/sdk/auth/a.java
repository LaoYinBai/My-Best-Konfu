package com.cmic.sso.sdk.auth;

import android.annotation.SuppressLint;
import android.content.Context;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import com.cmic.sso.sdk.b;
import com.cmic.sso.sdk.d.d;
import com.cmic.sso.sdk.d.h;
import com.cmic.sso.sdk.d.l;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import javax.crypto.NoSuchPaddingException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: c, reason: collision with root package name */
    @SuppressLint({"StaticFieldLeak"})
    private static a f5373c;

    /* renamed from: a, reason: collision with root package name */
    private com.cmic.sso.sdk.b.c.a f5374a = com.cmic.sso.sdk.b.c.a.a();

    /* renamed from: b, reason: collision with root package name */
    private Context f5375b;

    private a(Context context) {
        this.f5375b = context.getApplicationContext();
    }

    public static a a(Context context) {
        if (f5373c == null) {
            synchronized (a.class) {
                if (f5373c == null) {
                    f5373c = new a(context);
                }
            }
        }
        return f5373c;
    }

    void a(com.cmic.sso.sdk.a aVar, b bVar) {
        com.cmic.sso.sdk.d.c.b("AuthnBusiness", "LoginCheck method start");
        int iB = aVar.b("logintype", 0);
        if (aVar.b("isCacheScrip", false)) {
            String strB = aVar.b(OnekeyLoginConstants.CM_KEY_PRE_PHONE_SCRIP, "");
            if (iB == 3) {
                bVar.a(OnekeyLoginConstants.CM_RESULT_SUCCESS, "true", aVar, c.a(strB));
                return;
            } else {
                c(aVar, bVar);
                return;
            }
        }
        b(aVar, bVar);
    }

    private void b(final com.cmic.sso.sdk.a aVar, final b bVar) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        com.cmic.sso.sdk.d.c.b("AuthnBusiness", "getPrePhonescrip start");
        String packageName = this.f5375b.getPackageName();
        String strA = d.a(l.a(this.f5375b, packageName));
        aVar.a("apppackage", packageName);
        aVar.a("appsign", strA);
        byte[] bytes = new byte[0];
        if (aVar.b("use2048PublicKey", false)) {
            com.cmic.sso.sdk.d.c.a("AuthnBusiness", "使用2048公钥对应的对称秘钥生成方式");
            bytes = com.cmic.sso.sdk.d.a.a();
        } else {
            com.cmic.sso.sdk.d.c.a("AuthnBusiness", "使用1024公钥对应的对称秘钥生成方式");
            try {
                bytes = UUID.randomUUID().toString().substring(0, 16).getBytes("utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        aVar.a(b.a.f5382a, bytes);
        aVar.a("authtype", OnekeyLoginConstants.TELECOM_TYPE);
        this.f5374a.a(aVar, new com.cmic.sso.sdk.b.c.d() { // from class: com.cmic.sso.sdk.auth.a.1
            /* JADX WARN: Removed duplicated region for block: B:43:0x00ef  */
            /* JADX WARN: Removed duplicated region for block: B:47:0x0117  */
            @Override // com.cmic.sso.sdk.b.c.d
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void a(java.lang.String r8, java.lang.String r9, org.json.JSONObject r10) throws org.json.JSONException, javax.crypto.NoSuchPaddingException, java.security.NoSuchAlgorithmException, java.security.InvalidKeyException, java.security.InvalidAlgorithmParameterException {
                /*
                    Method dump skipped, instructions count: 297
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.cmic.sso.sdk.auth.a.AnonymousClass1.a(java.lang.String, java.lang.String, org.json.JSONObject):void");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final com.cmic.sso.sdk.a aVar, final b bVar) {
        com.cmic.sso.sdk.d.c.b("AuthnBusiness", "获取平台token》》》》");
        if (aVar.c("logintype") == 1) {
            aVar.a("userCapaid", "200");
        } else if (aVar.c("logintype") == 0) {
            aVar.a("userCapaid", "50");
        }
        this.f5374a.b(aVar, new com.cmic.sso.sdk.b.c.d() { // from class: com.cmic.sso.sdk.auth.a.2
            @Override // com.cmic.sso.sdk.b.c.d
            public void a(String str, String str2, JSONObject jSONObject) {
                com.cmic.sso.sdk.d.c.c("AuthnBusiness", "获取平台token 》》》》" + jSONObject.toString());
                if (str.equals(OnekeyLoginConstants.CM_RESULT_SUCCESS)) {
                    long jOptLong = jSONObject.optLong("phonescripED");
                    String strOptString = jSONObject.optString("phonescrip");
                    if (aVar.b("keyIsSimKeyICCID", false)) {
                        h.a(a.this.f5375b, strOptString, jOptLong, aVar.b("iccid", ""));
                    } else {
                        h.a(a.this.f5375b, strOptString, jOptLong, aVar.b("imsi", ""));
                    }
                    aVar.a("openId", jSONObject.optString("openId"));
                }
                bVar.a(str, str2, aVar, jSONObject);
            }
        });
    }
}
