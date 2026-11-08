package com.appsflyer;

import android.content.pm.PackageManager;
import android.os.Build;
import com.baidu.mobads.sdk.internal.av;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import com.bytedance.sdk.openadsdk.api.plugin.PluginConstants;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class r {

    /* renamed from: ˋ, reason: contains not printable characters */
    private static r f262;

    /* renamed from: ˉ, reason: contains not printable characters */
    private int f272;

    /* renamed from: ˊᐝ, reason: contains not printable characters */
    private JSONObject f277;

    /* renamed from: ˏ, reason: contains not printable characters */
    private boolean f283 = true;

    /* renamed from: ॱ, reason: contains not printable characters */
    private boolean f286 = true;

    /* renamed from: ˎ, reason: contains not printable characters */
    private final String f281 = av.k;

    /* renamed from: ˊ, reason: contains not printable characters */
    private final String f273 = av.j;

    /* renamed from: ʽ, reason: contains not printable characters */
    private final String f267 = "platform";

    /* renamed from: ʻ, reason: contains not printable characters */
    private final String f263 = "platform_version";

    /* renamed from: ॱॱ, reason: contains not printable characters */
    private final String f290 = ServerParameters.ADVERTISING_ID_PARAM;

    /* renamed from: ᐝ, reason: contains not printable characters */
    private final String f292 = "imei";

    /* renamed from: ʼ, reason: contains not printable characters */
    private final String f265 = av.f;

    /* renamed from: ˊॱ, reason: contains not printable characters */
    private final String f276 = PluginConstants.KEY_SDK_VERSION;

    /* renamed from: ˋॱ, reason: contains not printable characters */
    private final String f280 = "devkey";

    /* renamed from: ͺ, reason: contains not printable characters */
    private final String f285 = "originalAppsFlyerId";

    /* renamed from: ॱˊ, reason: contains not printable characters */
    private final String f287 = IWeiboService.ResponseConstants.UID;

    /* renamed from: ˏॱ, reason: contains not printable characters */
    private final String f284 = "app_id";

    /* renamed from: ʻॱ, reason: contains not printable characters */
    private final String f264 = "app_version";

    /* renamed from: ॱᐝ, reason: contains not printable characters */
    private final String f291 = "channel";

    /* renamed from: ᐝॱ, reason: contains not printable characters */
    private final String f293 = "preInstall";

    /* renamed from: ॱˎ, reason: contains not printable characters */
    private final String f289 = "data";

    /* renamed from: ॱˋ, reason: contains not printable characters */
    private final String f288 = "r_debugging_off";

    /* renamed from: ʽॱ, reason: contains not printable characters */
    private final String f268 = "r_debugging_on";

    /* renamed from: ʾ, reason: contains not printable characters */
    private final String f269 = "public_api_call";

    /* renamed from: ʼॱ, reason: contains not printable characters */
    private final String f266 = "exception";

    /* renamed from: ˈ, reason: contains not printable characters */
    private final String f271 = "server_request";

    /* renamed from: ʿ, reason: contains not printable characters */
    private final String f270 = "server_response";

    /* renamed from: ˋˊ, reason: contains not printable characters */
    private final String f278 = "yyyy-MM-dd HH:mm:ssZ";

    /* renamed from: ˊˋ, reason: contains not printable characters */
    private final String f275 = "MM-dd HH:mm:ss.SSS";

    /* renamed from: ˎˎ, reason: contains not printable characters */
    private String f282 = "-1";

    /* renamed from: ˊˊ, reason: contains not printable characters */
    private JSONArray f274 = new JSONArray();

    /* renamed from: ˋˋ, reason: contains not printable characters */
    private boolean f279 = false;

    private r() {
        this.f272 = 0;
        this.f272 = 0;
    }

    /* renamed from: ˎ, reason: contains not printable characters */
    static r m212() {
        if (f262 == null) {
            f262 = new r();
        }
        return f262;
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    final synchronized void m224(String str) {
        this.f282 = str;
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    final synchronized void m218() {
        this.f279 = true;
        m215("r_debugging_on", new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.ENGLISH).format(Long.valueOf(System.currentTimeMillis())), new String[0]);
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    final synchronized void m223() {
        m215("r_debugging_off", new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.ENGLISH).format(Long.valueOf(System.currentTimeMillis())), new String[0]);
        this.f279 = false;
        this.f283 = false;
    }

    /* renamed from: ˋ, reason: contains not printable characters */
    final synchronized void m219() {
        this.f277 = null;
        this.f274 = null;
        f262 = null;
    }

    /* renamed from: ˋ, reason: contains not printable characters */
    private synchronized void m211(String str, String str2, String str3, String str4, String str5, String str6) {
        try {
            this.f277.put(av.k, str);
            this.f277.put(av.j, str2);
            this.f277.put("platform", "Android");
            this.f277.put("platform_version", str3);
            if (str4 != null && str4.length() > 0) {
                this.f277.put(ServerParameters.ADVERTISING_ID_PARAM, str4);
            }
            if (str5 != null && str5.length() > 0) {
                this.f277.put("imei", str5);
            }
            if (str6 != null && str6.length() > 0) {
                this.f277.put(av.f, str6);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    private synchronized void m209(String str, String str2, String str3, String str4) {
        try {
            this.f277.put(PluginConstants.KEY_SDK_VERSION, str);
            if (str2 != null && str2.length() > 0) {
                this.f277.put("devkey", str2);
            }
            if (str3 != null && str3.length() > 0) {
                this.f277.put("originalAppsFlyerId", str3);
            }
            if (str4 != null && str4.length() > 0) {
                this.f277.put(IWeiboService.ResponseConstants.UID, str4);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    private synchronized void m213(String str, String str2, String str3, String str4) {
        if (str != null) {
            try {
                if (str.length() > 0) {
                    this.f277.put("app_id", str);
                }
            } catch (Throwable unused) {
                return;
            }
        }
        if (str2 != null && str2.length() > 0) {
            this.f277.put("app_version", str2);
        }
        if (str3 != null && str3.length() > 0) {
            this.f277.put("channel", str3);
        }
        if (str4 != null && str4.length() > 0) {
            this.f277.put("preInstall", str4);
        }
    }

    /* renamed from: ˋ, reason: contains not printable characters */
    final void m220(String str, String... strArr) {
        m215("public_api_call", str, strArr);
    }

    /* renamed from: ˋ, reason: contains not printable characters */
    final void m221(Throwable th) {
        String[] strArr;
        Throwable cause = th.getCause();
        String simpleName = th.getClass().getSimpleName();
        String message = cause == null ? th.getMessage() : cause.getMessage();
        StackTraceElement[] stackTrace = cause == null ? th.getStackTrace() : cause.getStackTrace();
        if (stackTrace == null) {
            strArr = new String[]{message};
        } else {
            String[] strArr2 = new String[stackTrace.length + 1];
            strArr2[0] = message;
            for (int i = 1; i < stackTrace.length; i++) {
                strArr2[i] = stackTrace[i].toString();
            }
            strArr = strArr2;
        }
        m215("exception", simpleName, strArr);
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    final void m225(String str, String str2) {
        m215("server_request", str, str2);
    }

    /* renamed from: ˎ, reason: contains not printable characters */
    final void m222(String str, int i, String str2) {
        m215("server_response", str, String.valueOf(i), str2);
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    final void m227(String str, String str2) {
        m215(null, str, str2);
    }

    /* renamed from: ʼ, reason: contains not printable characters */
    private synchronized String m208() {
        String string;
        try {
            this.f277.put("data", this.f274);
            string = this.f277.toString();
            try {
                m216();
            } catch (JSONException unused) {
            }
        } catch (JSONException unused2) {
            string = null;
        }
        return string;
    }

    /* renamed from: ˋ, reason: contains not printable characters */
    private synchronized void m210(String str, PackageManager packageManager) {
        AppsFlyerProperties appsFlyerProperties = AppsFlyerProperties.getInstance();
        AppsFlyerLib appsFlyerLib = AppsFlyerLib.getInstance();
        String string = appsFlyerProperties.getString("remote_debug_static_data");
        if (string != null) {
            try {
                this.f277 = new JSONObject(string);
            } catch (Throwable unused) {
            }
        } else {
            this.f277 = new JSONObject();
            m211(Build.BRAND, Build.MODEL, Build.VERSION.RELEASE, appsFlyerProperties.getString(ServerParameters.ADVERTISING_ID_PARAM), appsFlyerLib.f106, appsFlyerLib.f107);
            m209("4.8.18.413", appsFlyerProperties.getString(AppsFlyerProperties.AF_KEY), appsFlyerProperties.getString("KSAppsFlyerId"), appsFlyerProperties.getString(IWeiboService.ResponseConstants.UID));
            try {
                int i = packageManager.getPackageInfo(str, 0).versionCode;
                m213(str, String.valueOf(i), appsFlyerProperties.getString("channel"), appsFlyerProperties.getString("preInstallName"));
            } catch (Throwable unused2) {
            }
            appsFlyerProperties.set("remote_debug_static_data", this.f277.toString());
        }
        try {
            this.f277.put("launch_counter", this.f282);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: ᐝ, reason: contains not printable characters */
    private synchronized void m216() {
        this.f274 = null;
        this.f274 = new JSONArray();
        this.f272 = 0;
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    final synchronized void m226() {
        this.f283 = false;
        m216();
    }

    /* renamed from: ʻ, reason: contains not printable characters */
    final void m217() {
        this.f286 = false;
    }

    /* renamed from: ॱॱ, reason: contains not printable characters */
    final boolean m228() {
        return this.f279;
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    static void m214(String str, PackageManager packageManager) {
        try {
            if (f262 == null) {
                f262 = new r();
            }
            f262.m210(str, packageManager);
            if (f262 == null) {
                f262 = new r();
            }
            String strM208 = f262.m208();
            m mVar = new m(null, AppsFlyerLib.getInstance().isTrackingStopped());
            mVar.f242 = strM208;
            mVar.m189();
            StringBuilder sb = new StringBuilder();
            sb.append(ServerConfigHandler.getUrl("https://monitorsdk.%s/remote-debug?app_id="));
            sb.append(str);
            mVar.execute(sb.toString());
        } catch (Throwable unused) {
        }
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    private synchronized void m215(String str, String str2, String... strArr) {
        String str3;
        if (!(this.f286 && (this.f283 || this.f279)) || this.f272 >= 98304) {
            return;
        }
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String string = "";
            if (strArr.length > 0) {
                StringBuilder sb = new StringBuilder();
                for (int length = strArr.length - 1; length > 0; length--) {
                    sb.append(strArr[length]);
                    sb.append(", ");
                }
                sb.append(strArr[0]);
                string = sb.toString();
            }
            String str4 = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.ENGLISH).format(Long.valueOf(jCurrentTimeMillis));
            if (str != null) {
                str3 = String.format("%18s %5s _/%s [%s] %s %s", str4, Long.valueOf(Thread.currentThread().getId()), AppsFlyerLib.LOG_TAG, str, str2, string);
            } else {
                str3 = String.format("%18s %5s %s/%s %s", str4, Long.valueOf(Thread.currentThread().getId()), str2, AppsFlyerLib.LOG_TAG, string);
            }
            this.f274.put(str3);
            this.f272 += str3.getBytes().length;
        } catch (Throwable unused) {
        }
    }
}
