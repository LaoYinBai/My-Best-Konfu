package com.cmic.sso.sdk.auth;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import com.cmic.sso.sdk.d.e;
import com.cmic.sso.sdk.d.g;
import com.cmic.sso.sdk.d.h;
import com.cmic.sso.sdk.d.j;
import com.cmic.sso.sdk.d.k;
import com.cmic.sso.sdk.d.n;
import com.cmic.sso.sdk.d.o;
import com.cmic.sso.sdk.d.p;
import com.cmic.sso.sdk.d.q;
import com.cmic.sso.sdk.d.s;
import com.cmic.sso.sdk.d.t;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AuthnHelper {
    private static final int ConcurrentBundle_DEFAULT_SIZE = 64;
    public static final String SDK_VERSION = "quick_login_android_9.2.0.2";
    private static final String TAG = "AuthnHelper";

    @SuppressLint({"StaticFieldLeak"})
    private static AuthnHelper mInstance;
    private com.cmic.sso.sdk.auth.a authnBusiness;
    private Context mContext;
    private Handler mHandler;
    private final Object mObject;
    private long overTime;
    private String use2048PublicKey;

    private AuthnHelper(Context context) {
        this.overTime = 8000L;
        this.mObject = new Object();
        this.mContext = context.getApplicationContext();
        this.mHandler = new Handler(this.mContext.getMainLooper());
        this.authnBusiness = com.cmic.sso.sdk.auth.a.a(this.mContext);
        t.a(this.mContext);
        k.a(this.mContext);
        j.a(this.mContext);
        o.a(new o.a() { // from class: com.cmic.sso.sdk.auth.AuthnHelper.1
            @Override // com.cmic.sso.sdk.d.o.a
            protected void a() {
                String strB = k.b("AID", "");
                com.cmic.sso.sdk.d.c.b(AuthnHelper.TAG, "aid = " + strB);
                if (TextUtils.isEmpty(strB)) {
                    AuthnHelper.this.generateAID();
                }
                if (com.cmic.sso.sdk.d.b.a(AuthnHelper.this.mContext, true)) {
                    com.cmic.sso.sdk.d.c.b(AuthnHelper.TAG, "生成androidkeystore成功");
                } else {
                    com.cmic.sso.sdk.d.c.b(AuthnHelper.TAG, "生成androidkeystore失败");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void generateAID() {
        String str = "%" + s.a();
        com.cmic.sso.sdk.d.c.b(TAG, "generate aid = " + str);
        k.a aVarA = k.a();
        aVarA.a("AID", str);
        aVarA.b();
    }

    private AuthnHelper(Context context, String str) {
        this(context);
        this.use2048PublicKey = str;
    }

    public static AuthnHelper getInstance(Context context) {
        if (mInstance == null) {
            synchronized (AuthnHelper.class) {
                if (mInstance == null) {
                    mInstance = new AuthnHelper(context);
                }
            }
        }
        return mInstance;
    }

    public static AuthnHelper getInstance(Context context, String str) {
        if (mInstance == null) {
            synchronized (AuthnHelper.class) {
                if (mInstance == null) {
                    mInstance = new AuthnHelper(context, str);
                }
            }
        }
        return mInstance;
    }

    public void loginAuth(final String str, final String str2, final TokenListener tokenListener) {
        final com.cmic.sso.sdk.a bundle = getBundle(tokenListener);
        o.a(new o.a(this.mContext, bundle) { // from class: com.cmic.sso.sdk.auth.AuthnHelper.2
            @Override // com.cmic.sso.sdk.d.o.a
            protected void a() {
                if (AuthnHelper.this.commonInit(bundle, str, str2, "loginAuth", 1, tokenListener)) {
                    AuthnHelper.this.startGetPrePhonescript(bundle);
                }
            }
        });
    }

    public void mobileAuth(final String str, final String str2, final TokenListener tokenListener) {
        final com.cmic.sso.sdk.a bundle = getBundle(tokenListener);
        o.a(new o.a(this.mContext, bundle) { // from class: com.cmic.sso.sdk.auth.AuthnHelper.3
            @Override // com.cmic.sso.sdk.d.o.a
            protected void a() {
                if (AuthnHelper.this.commonInit(bundle, str, str2, "mobileAuth", 0, tokenListener)) {
                    AuthnHelper.this.startGetPrePhonescript(bundle);
                }
            }
        });
    }

    public void getPhoneInfo(final String str, final String str2, final TokenListener tokenListener) {
        final com.cmic.sso.sdk.a bundle = getBundle(tokenListener);
        o.a(new o.a(this.mContext, bundle) { // from class: com.cmic.sso.sdk.auth.AuthnHelper.4
            @Override // com.cmic.sso.sdk.d.o.a
            protected void a() {
                if (AuthnHelper.this.commonInit(bundle, str, str2, "preGetMobile", 3, tokenListener)) {
                    AuthnHelper.this.startGetPrePhonescript(bundle);
                }
            }
        });
    }

    private com.cmic.sso.sdk.a getBundle(TokenListener tokenListener) {
        com.cmic.sso.sdk.a aVar = new com.cmic.sso.sdk.a(64);
        String strB = s.b();
        aVar.a("traceId", strB);
        if (tokenListener != null) {
            e.a(strB, tokenListener);
        }
        return aVar;
    }

    private class a implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        private com.cmic.sso.sdk.a f5372b;

        a(com.cmic.sso.sdk.a aVar) {
            this.f5372b = aVar;
        }

        @Override // java.lang.Runnable
        public void run() throws JSONException {
            JSONObject jSONObjectA;
            if (!t.a(AuthnHelper.this.mContext).a() && this.f5372b.b("doNetworkSwitch", false)) {
                jSONObjectA = c.a("102508", "数据网络切换失败");
            } else {
                jSONObjectA = c.a("200023", "登录超时");
            }
            AuthnHelper.this.callBackResult(jSONObjectA.optString("resultCode", "200023"), jSONObjectA.optString("resultString", "登录超时"), this.f5372b, jSONObjectA);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startGetPrePhonescript(com.cmic.sso.sdk.a aVar) {
        final a aVar2 = new a(aVar);
        this.mHandler.postDelayed(aVar2, this.overTime);
        this.authnBusiness.a(aVar, new b() { // from class: com.cmic.sso.sdk.auth.AuthnHelper.5
            @Override // com.cmic.sso.sdk.auth.b
            public void a(String str, String str2, com.cmic.sso.sdk.a aVar3, JSONObject jSONObject) {
                AuthnHelper.this.mHandler.removeCallbacks(aVar2);
                AuthnHelper.this.callBackResult(str, str2, aVar3, jSONObject);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean commonInit(com.cmic.sso.sdk.a aVar, String str, String str2, String str3, int i, TokenListener tokenListener) throws Throwable {
        boolean zA;
        aVar.a("CLOSE_CERT_VERIFY", q.h());
        aVar.a("use2048PublicKey", "rsa2048".equals(this.use2048PublicKey));
        aVar.a("systemStartTime", SystemClock.elapsedRealtime());
        aVar.a("starttime", p.a());
        aVar.a("loginMethod", str3);
        aVar.a("appkey", str2);
        aVar.a("appid", str);
        aVar.a("timeOut", String.valueOf(this.overTime));
        boolean zA2 = g.a(this.mContext, "android.permission.READ_PHONE_STATE");
        com.cmic.sso.sdk.d.c.a(TAG, "有READ_PHONE_STATE权限？" + zA2);
        aVar.a("hsaReadPhoneStatePermission", zA2);
        com.cmic.sso.sdk.a.b.a().a(this.mContext, zA2);
        aVar.a("networkClass", com.cmic.sso.sdk.a.b.a().a(this.mContext));
        aVar.a("simCardNum", String.valueOf(com.cmic.sso.sdk.a.b.a().b().i()));
        String strB = j.a().b();
        String strB2 = j.a().b(zA2);
        String strE = j.e();
        String strA = j.a().a(false);
        aVar.a("imsi", strB);
        aVar.a("imei", strB2);
        aVar.a(OnekeyLoginConstants.OPERATOR_TYPE, strA);
        aVar.a("iccid", strE);
        aVar.a("logintype", i);
        com.cmic.sso.sdk.d.c.b(TAG, "iccid=" + strE);
        com.cmic.sso.sdk.d.c.b(TAG, "imsi=" + strB);
        if (TextUtils.isEmpty(strB)) {
            com.cmic.sso.sdk.d.c.a(TAG, "使用iccid作为缓存key");
            aVar.a("keyIsSimKeyICCID", true);
        }
        int iA = n.a(this.mContext, strA);
        aVar.a("networktype", iA);
        synchronized (this.mObject) {
            zA = h.a(aVar);
            if (zA) {
                aVar.a(OnekeyLoginConstants.CM_KEY_PRE_PHONE_SCRIP, k.b(OnekeyLoginConstants.CM_KEY_PRE_PHONE_SCRIP, ""));
                if (3 != i && iA != 0) {
                    String strA2 = h.a(this.mContext);
                    if (TextUtils.isEmpty(strA2)) {
                        zA = false;
                    } else {
                        aVar.a("sourceid", k.b("sourceid", ""));
                        aVar.a("phonescrip", strA2);
                    }
                    h.a(true, false);
                }
            }
            aVar.a("isCacheScrip", zA);
            com.cmic.sso.sdk.d.c.b(TAG, "isCachePhoneScrip = " + zA);
        }
        if (tokenListener == null) {
            callBackResult("102203", "listener不能为空", aVar, null);
            return false;
        }
        if (q.d()) {
            callBackResult("200082", "服务器繁忙，请稍后重试", aVar, null);
            return false;
        }
        if (TextUtils.isEmpty(str == null ? "" : str.trim())) {
            callBackResult("102203", "appId 不能为空", aVar, null);
            return false;
        }
        if (TextUtils.isEmpty(str2 == null ? "" : str2.trim())) {
            callBackResult("102203", "appkey不能为空", aVar, null);
            return false;
        }
        if (iA == 0) {
            callBackResult("102101", "未检测到网络", aVar, null);
            return false;
        }
        if (TextUtils.isEmpty(strA)) {
            aVar.a("authtype", String.valueOf(0));
            callBackResult("200010", "无法识别sim卡或没有sim卡", aVar, null);
            return false;
        }
        if (iA == 2 && !zA) {
            callBackResult("102103", "无数据网络", aVar, null);
            return false;
        }
        if (OnekeyLoginConstants.UNICOM_TYPE.equals(strA) && q.i()) {
            callBackResult("200082", "服务器繁忙，请稍后重试", aVar, null);
            return false;
        }
        if (!OnekeyLoginConstants.TELECOM_TYPE.equals(strA) || !q.j()) {
            return true;
        }
        callBackResult("200082", "服务器繁忙，请稍后重试", aVar, null);
        return false;
    }

    public static void setDebugMode(boolean z) {
        com.cmic.sso.sdk.d.c.a(z);
    }

    public void callBackResult(String str, String str2, final com.cmic.sso.sdk.a aVar, JSONObject jSONObject) {
        final JSONObject jSONObjectA;
        try {
            String strB = aVar.b("traceId");
            if (!e.a(strB)) {
                synchronized (this) {
                    final TokenListener tokenListenerC = e.c(strB);
                    e.b(strB);
                    if (tokenListenerC == null) {
                        return;
                    }
                    aVar.a("systemEndTime", SystemClock.elapsedRealtime());
                    aVar.a("endtime", p.a());
                    int iB = aVar.b("logintype", -1);
                    if (jSONObject == null) {
                        jSONObject = c.a(str, str2);
                    }
                    if (iB == 3) {
                        jSONObjectA = c.a(str, aVar, jSONObject);
                    } else {
                        jSONObjectA = c.a(str, str2, aVar, jSONObject);
                    }
                    this.mHandler.post(new Runnable() { // from class: com.cmic.sso.sdk.auth.AuthnHelper.6
                        @Override // java.lang.Runnable
                        public void run() {
                            tokenListenerC.onGetTokenComplete(jSONObjectA);
                        }
                    });
                    sendLog(this.mContext, str, aVar);
                    if (aVar.b("isNeedToGetCert", false) || q.a()) {
                        o.a(new o.a() { // from class: com.cmic.sso.sdk.auth.AuthnHelper.7
                            @Override // com.cmic.sso.sdk.d.o.a
                            protected void a() {
                                q.a(aVar);
                            }
                        });
                    }
                }
            }
            if (e.a()) {
                t.a(this.mContext).b();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendLog(Context context, String str, com.cmic.sso.sdk.a aVar) {
        if (q.k()) {
            return;
        }
        new com.cmic.sso.sdk.c.b().a(context, str, aVar);
    }

    public void setOverTime(long j) {
        this.overTime = j;
    }

    public JSONObject getNetworkType(Context context) throws Throwable {
        JSONObject jSONObject = new JSONObject();
        try {
            try {
                com.cmic.sso.sdk.a.b.a().a(context, g.a(context, "android.permission.READ_PHONE_STATE"));
                String strA = j.a().a(true);
                int iA = n.a(context, strA);
                jSONObject.put(OnekeyLoginConstants.OPERATOR_TYPE, strA);
                jSONObject.put("networktype", iA + "");
                com.cmic.sso.sdk.d.c.b(TAG, "网络类型: " + iA);
                com.cmic.sso.sdk.d.c.b(TAG, "运营商类型: " + strA);
                return jSONObject;
            } catch (Exception unused) {
                jSONObject.put("errorDes", "发生未知错误");
                return jSONObject;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return jSONObject;
        }
    }

    public void delScrip() {
        try {
            h.a(true, true);
            com.cmic.sso.sdk.d.c.b(TAG, "删除scrip");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
