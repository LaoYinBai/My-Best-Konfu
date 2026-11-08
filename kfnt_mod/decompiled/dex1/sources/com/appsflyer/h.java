package com.appsflyer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import com.bytedance.sdk.open.aweme.common.constants.ParamKeyConstants;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class h implements Runnable {

    /* renamed from: ॱॱ, reason: contains not printable characters */
    private static String f193;

    /* renamed from: ʻ, reason: contains not printable characters */
    private String f194;

    /* renamed from: ʼ, reason: contains not printable characters */
    private Map<String, String> f195;

    /* renamed from: ʽ, reason: contains not printable characters */
    private ScheduledExecutorService f196;

    /* renamed from: ˊ, reason: contains not printable characters */
    private String f197;

    /* renamed from: ˋ, reason: contains not printable characters */
    private String f198;

    /* renamed from: ˎ, reason: contains not printable characters */
    private WeakReference<Context> f199;

    /* renamed from: ˏ, reason: contains not printable characters */
    private String f200;

    /* renamed from: ͺ, reason: contains not printable characters */
    private final Intent f201;

    /* renamed from: ॱ, reason: contains not printable characters */
    private String f202;

    /* renamed from: ᐝ, reason: contains not printable characters */
    private String f203;

    static {
        StringBuilder sb = new StringBuilder("https://validate.%s/api/v");
        sb.append(AppsFlyerLib.f80);
        sb.append("/androidevent?buildnumber=4.8.18&app_id=");
        f193 = sb.toString();
    }

    h(Context context, String str, String str2, String str3, String str4, String str5, String str6, Map<String, String> map, ScheduledExecutorService scheduledExecutorService, Intent intent) {
        this.f199 = null;
        this.f199 = new WeakReference<>(context);
        this.f200 = str;
        this.f197 = str2;
        this.f202 = str4;
        this.f203 = str5;
        this.f194 = str6;
        this.f195 = map;
        this.f198 = str3;
        this.f196 = scheduledExecutorService;
        this.f201 = intent;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        if (this.f200 == null || this.f200.length() == 0 || AppsFlyerLib.getInstance().isTrackingStopped()) {
            return;
        }
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                Context context = this.f199.get();
                if (context == null) {
                    return;
                }
                HashMap map = new HashMap();
                map.put("public-key", this.f197);
                map.put("sig-data", this.f202);
                map.put(ParamKeyConstants.WebViewConstants.QUERY_SIGNATURE, this.f198);
                final HashMap map2 = new HashMap();
                map2.putAll(map);
                Executors.newSingleThreadScheduledExecutor().schedule(new Runnable() { // from class: com.appsflyer.h.1
                    @Override // java.lang.Runnable
                    public final void run() throws Throwable {
                        h.m168(h.this, map2, h.this.f195, h.this.f199);
                    }
                }, 5L, TimeUnit.MILLISECONDS);
                map.put("dev_key", this.f200);
                map.put("app_id", context.getPackageName());
                map.put(IWeiboService.ResponseConstants.UID, AppsFlyerLib.getInstance().getAppsFlyerUID(context));
                map.put(ServerParameters.ADVERTISING_ID_PARAM, AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM));
                String string = new JSONObject(map).toString();
                String url = ServerConfigHandler.getUrl("https://sdk-services.%s/validate-android-signature");
                r.m212().m225(url, string);
                HttpURLConnection httpURLConnectionM170 = m170(string, url);
                int responseCode = -1;
                if (httpURLConnectionM170 != null) {
                    try {
                        responseCode = httpURLConnectionM170.getResponseCode();
                    } catch (Throwable th) {
                        th = th;
                        httpURLConnection = httpURLConnectionM170;
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        throw th;
                    }
                }
                AppsFlyerLib.getInstance();
                String strM68 = AppsFlyerLib.m68(httpURLConnectionM170);
                r.m212().m222(url, responseCode, strM68);
                JSONObject jSONObject = new JSONObject(strM68);
                jSONObject.put("code", responseCode);
                if (responseCode == 200) {
                    StringBuilder sb = new StringBuilder("Validate response 200 ok: ");
                    sb.append(jSONObject.toString());
                    AFLogger.afInfoLog(sb.toString());
                    m167(jSONObject.optBoolean("result") ? jSONObject.getBoolean("result") : false, this.f202, this.f203, this.f194, jSONObject.toString());
                } else {
                    AFLogger.afInfoLog("Failed Validate request");
                    m167(false, this.f202, this.f203, this.f194, jSONObject.toString());
                }
                if (httpURLConnectionM170 != null) {
                    httpURLConnectionM170.disconnect();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    private static HttpURLConnection m170(String str, String str2) throws IOException {
        try {
            m mVar = new m(null, AppsFlyerLib.getInstance().isTrackingStopped());
            mVar.f242 = str;
            mVar.m187();
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                StringBuilder sb = new StringBuilder("Main thread detected. Calling ");
                sb.append(str2);
                sb.append(" in a new thread.");
                AFLogger.afDebugLog(sb.toString());
                mVar.execute(str2);
            } else {
                StringBuilder sb2 = new StringBuilder("Calling ");
                sb2.append(str2);
                sb2.append(" (on current thread: ");
                sb2.append(Thread.currentThread().toString());
                sb2.append(" )");
                AFLogger.afDebugLog(sb2.toString());
                mVar.onPreExecute();
                mVar.onPostExecute(mVar.doInBackground(str2));
            }
            return mVar.m190();
        } catch (Throwable th) {
            AFLogger.afErrorLog("Could not send callStats request", th);
            return null;
        }
    }

    /* renamed from: ˎ, reason: contains not printable characters */
    private static void m167(boolean z, String str, String str2, String str3, String str4) {
        if (AppsFlyerLib.f79 != null) {
            StringBuilder sb = new StringBuilder("Validate callback parameters: ");
            sb.append(str);
            sb.append(" ");
            sb.append(str2);
            sb.append(" ");
            sb.append(str3);
            AFLogger.afDebugLog(sb.toString());
            if (z) {
                AFLogger.afDebugLog("Validate in app purchase success: ".concat(String.valueOf(str4)));
                AppsFlyerLib.f79.onValidateInApp();
                return;
            }
            AFLogger.afDebugLog("Validate in app purchase failed: ".concat(String.valueOf(str4)));
            AppsFlyerInAppPurchaseValidatorListener appsFlyerInAppPurchaseValidatorListener = AppsFlyerLib.f79;
            if (str4 == null) {
                str4 = "Failed validating";
            }
            appsFlyerInAppPurchaseValidatorListener.onValidateInAppFailure(str4);
        }
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    static /* synthetic */ void m168(h hVar, Map map, Map map2, WeakReference weakReference) throws Throwable {
        HttpURLConnection httpURLConnectionM170;
        if (weakReference.get() == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ServerConfigHandler.getUrl(f193));
        sb.append(((Context) weakReference.get()).getPackageName());
        String string = sb.toString();
        SharedPreferences sharedPreferences = ((Context) weakReference.get()).getSharedPreferences("appsflyer-data", 0);
        HttpURLConnection httpURLConnection = null;
        String string2 = sharedPreferences.getString("referrer", null);
        if (string2 == null) {
            string2 = "";
        }
        Map<String, Object> mapM109 = AppsFlyerLib.getInstance().m109((Context) weakReference.get(), hVar.f200, AFInAppEventType.PURCHASE, "", string2, true, sharedPreferences, false, hVar.f201);
        mapM109.put("price", hVar.f203);
        mapM109.put("currency", hVar.f194);
        JSONObject jSONObject = new JSONObject(mapM109);
        JSONObject jSONObject2 = new JSONObject();
        try {
            for (Map.Entry entry : map.entrySet()) {
                jSONObject2.put((String) entry.getKey(), entry.getValue());
            }
            jSONObject.put("receipt_data", jSONObject2);
        } catch (JSONException e) {
            AFLogger.afErrorLog("Failed to build 'receipt_data'", e);
        }
        if (map2 != null) {
            JSONObject jSONObject3 = new JSONObject();
            try {
                for (Map.Entry entry2 : map2.entrySet()) {
                    jSONObject3.put((String) entry2.getKey(), entry2.getValue());
                }
                jSONObject.put("extra_prms", jSONObject3);
            } catch (JSONException e2) {
                AFLogger.afErrorLog("Failed to build 'extra_prms'", e2);
            }
        }
        String string3 = jSONObject.toString();
        r.m212().m225(string, string3);
        try {
            try {
                httpURLConnectionM170 = m170(string3, string);
                int responseCode = -1;
                if (httpURLConnectionM170 != null) {
                    try {
                        responseCode = httpURLConnectionM170.getResponseCode();
                    } catch (Throwable th) {
                        th = th;
                        if (httpURLConnectionM170 != null) {
                            httpURLConnectionM170.disconnect();
                        }
                        throw th;
                    }
                }
                AppsFlyerLib.getInstance();
                String strM68 = AppsFlyerLib.m68(httpURLConnectionM170);
                r.m212().m222(string, responseCode, strM68);
                JSONObject jSONObject4 = new JSONObject(strM68);
                if (responseCode == 200) {
                    StringBuilder sb2 = new StringBuilder("Validate-WH response - 200: ");
                    sb2.append(jSONObject4.toString());
                    AFLogger.afInfoLog(sb2.toString());
                } else {
                    StringBuilder sb3 = new StringBuilder("Validate-WH response failed - ");
                    sb3.append(responseCode);
                    sb3.append(": ");
                    sb3.append(jSONObject4.toString());
                    AFLogger.afWarnLog(sb3.toString());
                }
                if (httpURLConnectionM170 != null) {
                    httpURLConnectionM170.disconnect();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            httpURLConnectionM170 = httpURLConnection;
        }
    }
}
