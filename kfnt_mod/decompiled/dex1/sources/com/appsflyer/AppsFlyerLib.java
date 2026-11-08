package com.appsflyer;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerProperties;
import com.appsflyer.OneLinkHttpTask;
import com.appsflyer.b;
import com.appsflyer.cache.CacheManager;
import com.appsflyer.cache.RequestCacheData;
import com.appsflyer.i;
import com.appsflyer.j;
import com.appsflyer.o;
import com.appsflyer.s;
import com.appsflyer.share.Constants;
import com.appsflyer.y;
import com.baidu.mobads.sdk.internal.ae;
import com.baidu.mobads.sdk.internal.av;
import com.baidu.mobads.sdk.internal.bh;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import com.bytedance.sdk.adnet.game.http.cb.NetCallback;
import com.google.android.gms.common.GoogleApiAvailability;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.NetworkInterface;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AppsFlyerLib implements com.appsflyer.a {
    public static final String AF_PRE_INSTALL_PATH = "AF_PRE_INSTALL_PATH";
    public static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    public static final String ATTRIBUTION_ID_CONTENT_URI = "content://com.facebook.katana.provider.AttributionIdProvider";
    public static final String IS_STOP_TRACKING_USED = "is_stop_tracking_used";
    public static final String LOG_TAG = "AppsFlyer_4.8.18";
    public static final String PRE_INSTALL_SYSTEM_DEFAULT = "/data/local/tmp/pre_install.appsflyer";
    public static final String PRE_INSTALL_SYSTEM_DEFAULT_ETC = "/etc/pre_install.appsflyer";
    public static final String PRE_INSTALL_SYSTEM_RO_PROP = "ro.appsflyer.preinstall.path";

    /* renamed from: ʼ, reason: contains not printable characters */
    private static final String f76;

    /* renamed from: ʼॱ, reason: contains not printable characters */
    private static AppsFlyerLib f77;

    /* renamed from: ʽ, reason: contains not printable characters */
    private static String f78;

    /* renamed from: ˊ, reason: contains not printable characters */
    static AppsFlyerInAppPurchaseValidatorListener f79;

    /* renamed from: ˋ, reason: contains not printable characters */
    static final String f80 = BuildConfig.VERSION_NAME.substring(0, BuildConfig.VERSION_NAME.indexOf("."));

    /* renamed from: ˎ, reason: contains not printable characters */
    static final String f81;

    /* renamed from: ˏॱ, reason: contains not printable characters */
    private static AppsFlyerConversionListener f82;

    /* renamed from: ͺ, reason: contains not printable characters */
    private static final List<String> f83;

    /* renamed from: ॱˊ, reason: contains not printable characters */
    private static final List<String> f84;

    /* renamed from: ॱॱ, reason: contains not printable characters */
    private static String f85;

    /* renamed from: ᐝ, reason: contains not printable characters */
    private static String f86;

    /* renamed from: ᐝॱ, reason: contains not printable characters */
    private static AppsFlyerTrackingRequestListener f87;

    /* renamed from: ʽॱ, reason: contains not printable characters */
    private long f90;

    /* renamed from: ʾ, reason: contains not printable characters */
    private long f91;

    /* renamed from: ˈ, reason: contains not printable characters */
    private o.d f93;

    /* renamed from: ˊˊ, reason: contains not printable characters */
    private Map<Long, String> f95;

    /* renamed from: ˊˋ, reason: contains not printable characters */
    private String f96;

    /* renamed from: ˊᐝ, reason: contains not printable characters */
    private long f98;

    /* renamed from: ˋᐝ, reason: contains not printable characters */
    private boolean f102;

    /* renamed from: ˌ, reason: contains not printable characters */
    private boolean f103;

    /* renamed from: ˏ, reason: contains not printable characters */
    String f106;

    /* renamed from: ॱ, reason: contains not printable characters */
    String f107;

    /* renamed from: ॱˋ, reason: contains not printable characters */
    private Map<String, String> f108;

    /* renamed from: ॱᐝ, reason: contains not printable characters */
    private long f110;

    /* renamed from: ʻ, reason: contains not printable characters */
    private long f88 = -1;

    /* renamed from: ˊॱ, reason: contains not printable characters */
    private long f97 = -1;

    /* renamed from: ˋॱ, reason: contains not printable characters */
    private long f101 = TimeUnit.SECONDS.toMillis(5);

    /* renamed from: ॱˎ, reason: contains not printable characters */
    private com.appsflyer.d f109 = null;

    /* renamed from: ʻॱ, reason: contains not printable characters */
    private boolean f89 = false;

    /* renamed from: ʿ, reason: contains not printable characters */
    private ScheduledExecutorService f92 = null;
    protected Uri latestDeepLink = null;

    /* renamed from: ˉ, reason: contains not printable characters */
    private boolean f94 = false;

    /* renamed from: ˋˊ, reason: contains not printable characters */
    private boolean f99 = false;

    /* renamed from: ˋˋ, reason: contains not printable characters */
    private q f100 = new q();

    /* renamed from: ˍ, reason: contains not printable characters */
    private boolean f104 = false;

    /* renamed from: ˎˎ, reason: contains not printable characters */
    private boolean f105 = false;

    /* renamed from: ॱ, reason: contains not printable characters */
    static /* synthetic */ String m96(String str) {
        return AppsFlyerProperties.getInstance().getString(str);
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    static /* synthetic */ void m102(Map map) {
        if (f82 != null) {
            try {
                f82.onAppOpenAttribution(map);
            } catch (Throwable th) {
                AFLogger.afErrorLog(th.getLocalizedMessage(), th);
            }
        }
    }

    /* renamed from: ˋ, reason: contains not printable characters */
    static /* synthetic */ boolean m75(AppsFlyerLib appsFlyerLib) {
        return appsFlyerLib.f108 != null && appsFlyerLib.f108.size() > 0;
    }

    /* renamed from: ˎ, reason: contains not printable characters */
    static /* synthetic */ void m82(AppsFlyerLib appsFlyerLib, Context context, String str, String str2, String str3, String str4, boolean z, boolean z2, Intent intent) throws NoSuchAlgorithmException {
        if (context == null) {
            AFLogger.afDebugLog("sendTrackingWithEvent - got null context. skipping event/launch.");
            return;
        }
        boolean z3 = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("appsflyer-data", 0);
        AppsFlyerProperties.getInstance().saveProperties(sharedPreferences);
        if (!appsFlyerLib.isTrackingStopped()) {
            StringBuilder sb = new StringBuilder("sendTrackingWithEvent from activity: ");
            sb.append(context.getClass().getName());
            AFLogger.afInfoLog(sb.toString());
        }
        boolean z4 = str2 == null;
        Map<String, Object> mapM109 = appsFlyerLib.m109(context, str, str2, str3, str4, z, sharedPreferences, z4, intent);
        String str5 = (String) mapM109.get("appsflyerKey");
        if (str5 == null || str5.length() == 0) {
            AFLogger.afDebugLog("Not sending data yet, waiting for dev key");
            return;
        }
        if (!appsFlyerLib.isTrackingStopped()) {
            AFLogger.afInfoLog("AppsFlyerLib.sendTrackingWithEvent");
        }
        String url = ServerConfigHandler.getUrl(z4 ? z2 ? f86 : f85 : f78);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(url);
        sb2.append(context.getPackageName());
        String string = sb2.toString();
        int iM85 = m85(sharedPreferences, "appsFlyerCount", false);
        if (!(AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_ANDROID_ID_FORCE_BY_USER, false) || AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_IMEI_FORCE_BY_USER, false)) && mapM109.get(ServerParameters.ADVERTISING_ID_PARAM) != null) {
            try {
                if (TextUtils.isEmpty(appsFlyerLib.f107) && mapM109.remove(av.f) != null) {
                    AFLogger.afInfoLog("validateGaidAndIMEI :: removing: android_id");
                }
                if (TextUtils.isEmpty(appsFlyerLib.f106) && mapM109.remove("imei") != null) {
                    AFLogger.afInfoLog("validateGaidAndIMEI :: removing: imei");
                }
            } catch (Exception e2) {
                AFLogger.afErrorLog("failed to remove IMEI or AndroidID key from params; ", e2);
            }
        }
        e eVar = new e(appsFlyerLib, string, mapM109, context.getApplicationContext(), z4, iM85, (byte) 0);
        if (z4 && m93(context)) {
            if (appsFlyerLib.f108 != null && appsFlyerLib.f108.size() > 0) {
                z3 = true;
            }
            if (!z3) {
                AFLogger.afDebugLog("Failed to get new referrer, wait ...");
                m103(AFExecutor.getInstance().m39(), eVar, 500L, TimeUnit.MILLISECONDS);
                return;
            }
        }
        eVar.run();
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    static /* synthetic */ ScheduledExecutorService m87(AppsFlyerLib appsFlyerLib) {
        appsFlyerLib.f92 = null;
        return null;
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    static /* synthetic */ void m91(AppsFlyerLib appsFlyerLib, String str, String str2, String str3, WeakReference weakReference, String str4, boolean z) throws Throwable {
        URL url = new URL(str);
        StringBuilder sb = new StringBuilder("url: ");
        sb.append(url.toString());
        AFLogger.afInfoLog(sb.toString());
        j.AnonymousClass2.m182("data: ".concat(String.valueOf(str2)));
        m101((Context) weakReference.get(), LOG_TAG, "EVENT_DATA", str2);
        try {
            appsFlyerLib.m63(url, str2, str3, weakReference, str4, z);
        } catch (IOException e2) {
            AFLogger.afErrorLog("Exception in sendRequestToServer. ", e2);
            if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.USE_HTTP_FALLBACK, false)) {
                appsFlyerLib.m63(new URL(str.replace("https:", "http:")), str2, str3, weakReference, str4, z);
                return;
            }
            StringBuilder sb2 = new StringBuilder("failed to send requeset to server. ");
            sb2.append(e2.getLocalizedMessage());
            AFLogger.afInfoLog(sb2.toString());
            m101((Context) weakReference.get(), LOG_TAG, bh.l, e2.getLocalizedMessage());
            throw e2;
        }
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(f80);
        sb.append("/androidevent?buildnumber=4.8.18&app_id=");
        f76 = sb.toString();
        StringBuilder sb2 = new StringBuilder("https://attr.%s/api/v");
        sb2.append(f76);
        f86 = sb2.toString();
        StringBuilder sb3 = new StringBuilder("https://t.%s/api/v");
        sb3.append(f76);
        f85 = sb3.toString();
        StringBuilder sb4 = new StringBuilder("https://events.%s/api/v");
        sb4.append(f76);
        f78 = sb4.toString();
        StringBuilder sb5 = new StringBuilder("https://register.%s/api/v");
        sb5.append(f76);
        f81 = sb5.toString();
        f84 = Arrays.asList("is_cache");
        f83 = Arrays.asList("googleplay", "playstore", "googleplaystore");
        f82 = null;
        f79 = null;
        f87 = null;
        f77 = new AppsFlyerLib();
    }

    /* renamed from: ˎ, reason: contains not printable characters */
    final void m110() {
        this.f91 = System.currentTimeMillis();
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    final void m107() {
        this.f90 = System.currentTimeMillis();
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    final void m111(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(AppsFlyerProperties.IS_MONITOR);
        if (stringExtra != null) {
            AFLogger.afInfoLog("Turning on monitoring.");
            AppsFlyerProperties.getInstance().set(AppsFlyerProperties.IS_MONITOR, stringExtra.equals("true"));
            m101(context, (String) null, "START_TRACKING", context.getPackageName());
            return;
        }
        AFLogger.afInfoLog("****** onReceive called *******");
        AppsFlyerProperties.getInstance().setOnReceiveCalled();
        String stringExtra2 = intent.getStringExtra("referrer");
        AFLogger.afInfoLog("Play store referrer: ".concat(String.valueOf(stringExtra2)));
        if (stringExtra2 != null) {
            if ("AppsFlyer_Test".equals(intent.getStringExtra("TestIntegrationMode"))) {
                SharedPreferences.Editor editorEdit = context.getSharedPreferences("appsflyer-data", 0).edit();
                editorEdit.clear();
                if (Build.VERSION.SDK_INT >= 9) {
                    editorEdit.apply();
                } else {
                    editorEdit.commit();
                }
                AppsFlyerProperties.getInstance().setFirstLaunchCalled(false);
                AFLogger.afInfoLog("Test mode started..");
                this.f98 = System.currentTimeMillis();
            }
            SharedPreferences.Editor editorEdit2 = context.getSharedPreferences("appsflyer-data", 0).edit();
            editorEdit2.putString("referrer", stringExtra2);
            if (Build.VERSION.SDK_INT >= 9) {
                editorEdit2.apply();
            } else {
                editorEdit2.commit();
            }
            AppsFlyerProperties.getInstance().setReferrer(stringExtra2);
            if (AppsFlyerProperties.getInstance().isFirstLaunchCalled()) {
                AFLogger.afInfoLog("onReceive: isLaunchCalled");
                if (stringExtra2 == null || stringExtra2.length() <= 5) {
                    return;
                }
                ScheduledThreadPoolExecutor scheduledThreadPoolExecutorM39 = AFExecutor.getInstance().m39();
                m103(scheduledThreadPoolExecutorM39, new c(this, new WeakReference(context.getApplicationContext()), null, null, null, stringExtra2, scheduledThreadPoolExecutorM39, true, intent, (byte) 0), 5L, TimeUnit.MILLISECONDS);
            }
        }
    }

    /* renamed from: ˎ, reason: contains not printable characters */
    private static void m83(JSONObject jSONObject) {
        String str;
        ArrayList arrayList = new ArrayList();
        Iterator<String> itKeys = jSONObject.keys();
        while (true) {
            if (!itKeys.hasNext()) {
                break;
            }
            try {
                JSONArray jSONArray = new JSONArray((String) jSONObject.get(itKeys.next()));
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(Long.valueOf(jSONArray.getLong(i)));
                }
            } catch (JSONException unused) {
            }
        }
        Collections.sort(arrayList);
        Iterator<String> itKeys2 = jSONObject.keys();
        loop2: while (true) {
            str = null;
            while (itKeys2.hasNext() && str == null) {
                String next = itKeys2.next();
                try {
                    JSONArray jSONArray2 = new JSONArray((String) jSONObject.get(next));
                    String str2 = str;
                    int i2 = 0;
                    while (i2 < jSONArray2.length()) {
                        try {
                            if (jSONArray2.getLong(i2) == ((Long) arrayList.get(0)).longValue() || jSONArray2.getLong(i2) == ((Long) arrayList.get(1)).longValue() || jSONArray2.getLong(i2) == ((Long) arrayList.get(arrayList.size() - 1)).longValue()) {
                                break;
                            }
                            i2++;
                            str2 = next;
                        } catch (JSONException unused2) {
                        }
                    }
                    str = str2;
                } catch (JSONException unused3) {
                }
            }
        }
        if (str != null) {
            jSONObject.remove(str);
        }
    }

    /* renamed from: ˋ, reason: contains not printable characters */
    static void m72(Context context, String str) {
        JSONObject jSONObject;
        JSONArray jSONArray;
        AFLogger.afDebugLog("received a new (extra) referrer: ".concat(String.valueOf(str)));
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String string = context.getSharedPreferences("appsflyer-data", 0).getString("extraReferrers", null);
            if (string == null) {
                jSONObject = new JSONObject();
                jSONArray = new JSONArray();
            } else {
                jSONObject = new JSONObject(string);
                if (jSONObject.has(str)) {
                    jSONArray = new JSONArray((String) jSONObject.get(str));
                } else {
                    jSONArray = new JSONArray();
                }
            }
            if (jSONArray.length() < 5) {
                jSONArray.put(jCurrentTimeMillis);
            }
            if (jSONObject.length() >= 4) {
                m83(jSONObject);
            }
            jSONObject.put(str, jSONArray.toString());
            String string2 = jSONObject.toString();
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("appsflyer-data", 0).edit();
            editorEdit.putString("extraReferrers", string2);
            if (Build.VERSION.SDK_INT >= 9) {
                editorEdit.apply();
            } else {
                editorEdit.commit();
            }
        } catch (JSONException unused) {
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder("Couldn't save referrer - ");
            sb.append(str);
            sb.append(": ");
            AFLogger.afErrorLog(sb.toString(), th);
        }
    }

    private AppsFlyerLib() {
        AFVersionDeclaration.init();
    }

    public static AppsFlyerLib getInstance() {
        return f77;
    }

    public void stopTracking(boolean z, Context context) {
        this.f105 = z;
        CacheManager.getInstance().clearCache(context);
        if (this.f105) {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("appsflyer-data", 0).edit();
            editorEdit.putBoolean(IS_STOP_TRACKING_USED, true);
            if (Build.VERSION.SDK_INT >= 9) {
                editorEdit.apply();
            } else {
                editorEdit.commit();
            }
        }
    }

    public String getSdkVersion() {
        r.m212().m220("getSdkVersion", new String[0]);
        return "version: 4.8.18 (build 413)";
    }

    public void onPause(Context context) {
        i.b.m172(context);
        j jVarM177 = j.m177(context);
        jVarM177.f218.post(jVarM177.f213);
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    private void m60(Application application) {
        AppsFlyerProperties.getInstance().loadProperties(application.getApplicationContext());
        if (Build.VERSION.SDK_INT >= 14) {
            if (Build.VERSION.SDK_INT < 14 || this.f93 != null) {
                return;
            }
            o.m193();
            this.f93 = new o.d() { // from class: com.appsflyer.AppsFlyerLib.3
                @Override // com.appsflyer.o.d
                /* renamed from: ˊ, reason: contains not printable characters */
                public final void mo117(Activity activity) {
                    if (2 > AppsFlyerLib.m66(AppsFlyerLib.m94(activity))) {
                        j jVarM177 = j.m177(activity);
                        jVarM177.f218.post(jVarM177.f213);
                        jVarM177.f218.post(jVarM177.f221);
                    }
                    AFLogger.afInfoLog("onBecameForeground");
                    AppsFlyerLib.getInstance().m110();
                    AppsFlyerLib.getInstance().m113(activity, (String) null, (Map<String, Object>) null);
                    AFLogger.resetDeltaTime();
                }

                @Override // com.appsflyer.o.d
                /* renamed from: ˊ, reason: contains not printable characters */
                public final void mo118(WeakReference<Context> weakReference) {
                    i.b.m172(weakReference.get());
                    j jVarM177 = j.m177(weakReference.get());
                    jVarM177.f218.post(jVarM177.f213);
                }
            };
            o.m196().m199(application, this.f93);
            return;
        }
        AFLogger.afInfoLog("SDK<14 call trackEvent manually");
        AFLogger.afInfoLog("onBecameForeground");
        getInstance().f91 = System.currentTimeMillis();
        getInstance().m113(application, (String) null, (Map<String, Object>) null);
        AFLogger.resetDeltaTime();
    }

    @Deprecated
    public void setGCMProjectID(String str) {
        r.m212().m220("setGCMProjectID", str);
        AFLogger.afWarnLog("Method 'setGCMProjectNumber' is deprecated. Please follow the documentation.");
        enableUninstallTracking(str);
    }

    @Deprecated
    public void setGCMProjectNumber(String str) {
        r.m212().m220("setGCMProjectNumber", str);
        AFLogger.afWarnLog("Method 'setGCMProjectNumber' is deprecated. Please follow the documentation.");
        enableUninstallTracking(str);
    }

    @Deprecated
    public void setGCMProjectNumber(Context context, String str) {
        r.m212().m220("setGCMProjectNumber", str);
        AFLogger.afWarnLog("Method 'setGCMProjectNumber' is deprecated. Please use 'enableUninstallTracking'.");
        enableUninstallTracking(str);
    }

    public void enableUninstallTracking(String str) {
        r.m212().m220("enableUninstallTracking", str);
        AppsFlyerProperties.getInstance().set("gcmProjectNumber", str);
    }

    public void updateServerUninstallToken(Context context, String str) {
        if (str != null) {
            y.m248(context, new b.e.C0088b(str));
        }
    }

    public void setDebugLog(boolean z) {
        r.m212().m220("setDebugLog", String.valueOf(z));
        AppsFlyerProperties.getInstance().set("shouldLog", z);
        AppsFlyerProperties.getInstance().set("logLevel", (z ? AFLogger.LogLevel.DEBUG : AFLogger.LogLevel.NONE).getLevel());
    }

    public void setImeiData(String str) {
        r.m212().m220("setImeiData", str);
        this.f106 = str;
    }

    public void setAndroidIdData(String str) {
        r.m212().m220("setAndroidIdData", str);
        this.f107 = str;
    }

    public AppsFlyerLib enableLocationCollection(boolean z) {
        this.f94 = z;
        return this;
    }

    @Deprecated
    public void setAppUserId(String str) {
        r.m212().m220("setAppUserId", str);
        setCustomerUserId(str);
    }

    public void setCustomerUserId(String str) {
        r.m212().m220("setCustomerUserId", str);
        AFLogger.afInfoLog("setCustomerUserId = ".concat(String.valueOf(str)));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.APP_USER_ID, str);
    }

    public void waitForCustomerUserId(boolean z) {
        AFLogger.afInfoLog("initAfterCustomerUserID: ".concat(String.valueOf(z)), true);
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, z);
    }

    public void setCustomerIdAndTrack(String str, @NonNull Context context) {
        if (context != null) {
            boolean z = false;
            if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false) && AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID) == null) {
                z = true;
            }
            if (z) {
                setCustomerUserId(str);
                StringBuilder sb = new StringBuilder("CustomerUserId set: ");
                sb.append(str);
                sb.append(" - Initializing AppsFlyer Tacking");
                AFLogger.afInfoLog(sb.toString(), true);
                String referrer = AppsFlyerProperties.getInstance().getReferrer(context);
                String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
                if (referrer == null) {
                    referrer = "";
                }
                m74(context, string, null, null, referrer, context instanceof Activity ? ((Activity) context).getIntent() : null);
                if (AppsFlyerProperties.getInstance().getString("afUninstallToken") != null) {
                    m112(context, AppsFlyerProperties.getInstance().getString("afUninstallToken"));
                    return;
                }
                return;
            }
            setCustomerUserId(str);
            AFLogger.afInfoLog("waitForCustomerUserId is false; setting CustomerUserID: ".concat(String.valueOf(str)), true);
        }
    }

    public String getOutOfStore(Context context) {
        String string = AppsFlyerProperties.getInstance().getString("api_store_value");
        if (string != null) {
            return string;
        }
        String strM98 = m98((WeakReference<Context>) new WeakReference(context), "AF_STORE");
        if (strM98 != null) {
            return strM98;
        }
        AFLogger.afInfoLog("No out-of-store value set");
        return null;
    }

    public void setOutOfStore(String str) {
        if (str != null) {
            String lowerCase = str.toLowerCase();
            AppsFlyerProperties.getInstance().set("api_store_value", lowerCase);
            AFLogger.afInfoLog("Store API set with value: ".concat(String.valueOf(lowerCase)), true);
            return;
        }
        AFLogger.m48("Cannot set setOutOfStore with null");
    }

    public void setAppInviteOneLink(String str) {
        r.m212().m220("setAppInviteOneLink", str);
        AFLogger.afInfoLog("setAppInviteOneLink = ".concat(String.valueOf(str)));
        if (str == null || !str.equals(AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.ONELINK_ID))) {
            AppsFlyerProperties.getInstance().remove(AppsFlyerProperties.ONELINK_DOMAIN);
            AppsFlyerProperties.getInstance().remove("onelinkVersion");
            AppsFlyerProperties.getInstance().remove(AppsFlyerProperties.ONELINK_SCHEME);
        }
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.ONELINK_ID, str);
    }

    public void setAdditionalData(HashMap<String, Object> map) {
        if (map != null) {
            r.m212().m220("setAdditionalData", map.toString());
            AppsFlyerProperties.getInstance().setCustomData(new JSONObject(map).toString());
        }
    }

    public void sendDeepLinkData(Activity activity) {
        if (activity != null && activity.getIntent() != null) {
            r rVarM212 = r.m212();
            StringBuilder sb = new StringBuilder("activity_intent_");
            sb.append(activity.getIntent().toString());
            rVarM212.m220("sendDeepLinkData", activity.getLocalClassName(), sb.toString());
        } else if (activity != null) {
            r.m212().m220("sendDeepLinkData", activity.getLocalClassName(), "activity_intent_null");
        } else {
            r.m212().m220("sendDeepLinkData", "activity_null");
        }
        try {
            m60(activity.getApplication());
            StringBuilder sb2 = new StringBuilder("getDeepLinkData with activity ");
            sb2.append(activity.getIntent().getDataString());
            AFLogger.afInfoLog(sb2.toString());
        } catch (Exception e2) {
            AFLogger.afInfoLog("getDeepLinkData Exception: ".concat(String.valueOf(e2)));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0168  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void sendPushNotificationData(android.app.Activity r15) {
        /*
            Method dump skipped, instructions count: 410
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AppsFlyerLib.sendPushNotificationData(android.app.Activity):void");
    }

    @Deprecated
    public void setUserEmail(String str) {
        r.m212().m220("setUserEmail", str);
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.USER_EMAIL, str);
    }

    public void setUserEmails(String... strArr) {
        r.m212().m220("setUserEmails", strArr);
        setUserEmails(AppsFlyerProperties.EmailsCryptType.NONE, strArr);
    }

    public void setUserEmails(AppsFlyerProperties.EmailsCryptType emailsCryptType, String... strArr) {
        String str;
        ArrayList arrayList = new ArrayList(strArr.length + 1);
        arrayList.add(emailsCryptType.toString());
        arrayList.addAll(Arrays.asList(strArr));
        r.m212().m220("setUserEmails", (String[]) arrayList.toArray(new String[strArr.length + 1]));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.EMAIL_CRYPT_TYPE, emailsCryptType.getValue());
        HashMap map = new HashMap();
        ArrayList arrayList2 = new ArrayList();
        String str2 = null;
        for (String str3 : strArr) {
            switch (AnonymousClass4.f115[emailsCryptType.ordinal()]) {
                case 2:
                    str = "md5_el_arr";
                    arrayList2.add(t.m241(str3));
                    break;
                case 3:
                    str = "sha256_el_arr";
                    arrayList2.add(t.m240(str3));
                    break;
                case 4:
                    str = "plain_el_arr";
                    arrayList2.add(str3);
                    break;
                default:
                    str = "sha1_el_arr";
                    arrayList2.add(t.m242(str3));
                    break;
            }
            str2 = str;
        }
        map.put(str2, arrayList2);
        AppsFlyerProperties.getInstance().setUserEmails(new JSONObject(map).toString());
    }

    /* renamed from: com.appsflyer.AppsFlyerLib$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {

        /* renamed from: ˎ, reason: contains not printable characters */
        static final /* synthetic */ int[] f115 = new int[AppsFlyerProperties.EmailsCryptType.values().length];

        static {
            try {
                f115[AppsFlyerProperties.EmailsCryptType.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f115[AppsFlyerProperties.EmailsCryptType.MD5.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f115[AppsFlyerProperties.EmailsCryptType.SHA256.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f115[AppsFlyerProperties.EmailsCryptType.NONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public void setCollectAndroidID(boolean z) {
        r.m212().m220("setCollectAndroidID", String.valueOf(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.COLLECT_ANDROID_ID, Boolean.toString(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.COLLECT_ANDROID_ID_FORCE_BY_USER, Boolean.toString(z));
    }

    public void setCollectIMEI(boolean z) {
        r.m212().m220("setCollectIMEI", String.valueOf(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.COLLECT_IMEI, Boolean.toString(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.COLLECT_IMEI_FORCE_BY_USER, Boolean.toString(z));
    }

    @Deprecated
    public void setCollectFingerPrint(boolean z) {
        r.m212().m220("setCollectFingerPrint", String.valueOf(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.COLLECT_FINGER_PRINT, Boolean.toString(z));
    }

    public AppsFlyerLib init(String str, AppsFlyerConversionListener appsFlyerConversionListener) {
        r rVarM212 = r.m212();
        String[] strArr = new String[2];
        strArr[0] = str;
        strArr[1] = appsFlyerConversionListener == null ? "null" : "conversionDataListener";
        rVarM212.m220("init", strArr);
        AFLogger.m51(String.format("Initializing AppsFlyer SDK: (v%s.%s)", BuildConfig.VERSION_NAME, "413"));
        this.f102 = true;
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.AF_KEY, str);
        j.AnonymousClass2.m183(str);
        f82 = appsFlyerConversionListener;
        return this;
    }

    public AppsFlyerLib init(String str, AppsFlyerConversionListener appsFlyerConversionListener, Context context) {
        if (context != null && m93(context)) {
            if (this.f109 == null) {
                this.f109 = new com.appsflyer.d();
                this.f109.m147(context, this);
            } else {
                AFLogger.afWarnLog("AFInstallReferrer instance already created");
            }
        }
        return init(str, appsFlyerConversionListener);
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    private static boolean m93(@NonNull Context context) {
        if (m85(context.getSharedPreferences("appsflyer-data", 0), "appsFlyerCount", false) > 2) {
            AFLogger.afRDLog("Install referrer will not load, the counter > 2, ");
            return false;
        }
        try {
            Class.forName("com.android.installreferrer.api.InstallReferrerClient");
            if (j.AnonymousClass1.m181(context, "com.google.android.finsky.permission.BIND_GET_INSTALL_REFERRER_SERVICE")) {
                AFLogger.afDebugLog("Install referrer is allowed");
                return true;
            }
            AFLogger.afDebugLog("Install referrer is not allowed");
            return false;
        } catch (ClassNotFoundException unused) {
            AFLogger.afRDLog("Class com.android.installreferrer.api.InstallReferrerClient not found");
            return false;
        } catch (Throwable th) {
            AFLogger.afErrorLog("An error occurred while trying to verify manifest : com.android.installreferrer.api.InstallReferrerClient", th);
            return false;
        }
    }

    public void startTracking(Application application) {
        if (!this.f102) {
            AFLogger.afWarnLog("ERROR: AppsFlyer SDK is not initialized! The API call 'startTracking(Application)' must be called after the 'init(String, AppsFlyerConversionListener)' API method, which should be called on the Application's onCreate.");
        } else {
            startTracking(application, null);
        }
    }

    public void startTracking(Application application, String str) {
        startTracking(application, str, null);
    }

    public void startTracking(Application application, String str, AppsFlyerTrackingRequestListener appsFlyerTrackingRequestListener) {
        r.m212().m220("startTracking", str);
        AFLogger.afInfoLog(String.format("Starting AppsFlyer Tracking: (v%s.%s)", BuildConfig.VERSION_NAME, "413"));
        AFLogger.afInfoLog("Build Number: 413");
        AppsFlyerProperties.getInstance().loadProperties(application.getApplicationContext());
        if (!TextUtils.isEmpty(str)) {
            AppsFlyerProperties.getInstance().set(AppsFlyerProperties.AF_KEY, str);
            j.AnonymousClass2.m183(str);
        } else if (TextUtils.isEmpty(AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY))) {
            AFLogger.afWarnLog("ERROR: AppsFlyer SDK is not initialized! You must provide AppsFlyer Dev-Key either in the 'init' API method (should be called on Application's onCreate),or in the startTracking API method (should be called on Activity's onCreate).");
            return;
        }
        if (appsFlyerTrackingRequestListener != null) {
            f87 = appsFlyerTrackingRequestListener;
        }
        m60(application);
    }

    public void setAppId(String str) {
        r.m212().m220("setAppId", str);
        AppsFlyerProperties.getInstance().set("appid", str);
    }

    public void setExtension(String str) {
        r.m212().m220("setExtension", str);
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.EXTENSION, str);
    }

    public void setIsUpdate(boolean z) {
        r.m212().m220("setIsUpdate", String.valueOf(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.IS_UPDATE, z);
    }

    public void setCurrencyCode(String str) {
        r.m212().m220("setCurrencyCode", str);
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.CURRENCY_CODE, str);
    }

    public void trackLocation(Context context, double d2, double d3) {
        r.m212().m220("trackLocation", String.valueOf(d2), String.valueOf(d3));
        HashMap map = new HashMap();
        map.put(AFInAppEventParameterName.LONGTITUDE, Double.toString(d3));
        map.put(AFInAppEventParameterName.LATITUDE, Double.toString(d2));
        m113(context, AFInAppEventType.LOCATION_COORDINATES, map);
    }

    /* renamed from: ˋ, reason: contains not printable characters */
    final void m108(WeakReference<Context> weakReference) {
        if (weakReference.get() == null) {
            return;
        }
        AFLogger.afInfoLog("app went to background");
        SharedPreferences sharedPreferences = weakReference.get().getSharedPreferences("appsflyer-data", 0);
        AppsFlyerProperties.getInstance().saveProperties(sharedPreferences);
        long j = this.f90 - this.f91;
        HashMap map = new HashMap();
        String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
        if (string == null) {
            AFLogger.afWarnLog("[callStats] AppsFlyer's SDK cannot send any event without providing DevKey.");
            return;
        }
        String string2 = AppsFlyerProperties.getInstance().getString("KSAppsFlyerId");
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, false)) {
            map.put(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, "true");
        }
        k kVarM192 = n.m192(weakReference.get().getContentResolver());
        if (kVarM192 != null) {
            map.put("amazon_aid", kVarM192.m185());
            map.put("amazon_aid_limit", String.valueOf(kVarM192.m184()));
        }
        String string3 = AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM);
        if (string3 != null) {
            map.put(ServerParameters.ADVERTISING_ID_PARAM, string3);
        }
        map.put("app_id", weakReference.get().getPackageName());
        map.put("devkey", string);
        map.put(IWeiboService.ResponseConstants.UID, p.m202(weakReference));
        map.put("time_in_app", String.valueOf(j / 1000));
        map.put("statType", "user_closed_app");
        map.put("platform", "Android");
        map.put("launch_counter", Integer.toString(m85(sharedPreferences, "appsFlyerCount", false)));
        map.put("gcd_conversion_data_timing", Long.toString(sharedPreferences.getLong("appsflyerGetConversionDataTiming", 0L)));
        map.put("channel", m77(weakReference));
        if (string2 == null) {
            string2 = "";
        }
        map.put("originalAppsflyerId", string2);
        if (this.f104) {
            try {
                m mVar = new m(null, isTrackingStopped());
                mVar.f241 = map;
                if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                    AFLogger.afDebugLog("Main thread detected. Running callStats task in a new thread.");
                    mVar.execute(ServerConfigHandler.getUrl("https://stats.%s/stats"));
                    return;
                }
                StringBuilder sb = new StringBuilder("Running callStats task (on current thread: ");
                sb.append(Thread.currentThread().toString());
                sb.append(" )");
                AFLogger.afDebugLog(sb.toString());
                mVar.onPreExecute();
                mVar.onPostExecute(mVar.doInBackground(ServerConfigHandler.getUrl("https://stats.%s/stats")));
                return;
            } catch (Throwable th) {
                AFLogger.afErrorLog("Could not send callStats request", th);
                return;
            }
        }
        AFLogger.afDebugLog("Stats call is disabled, ignore ...");
    }

    public void trackAppLaunch(Context context, String str) {
        if (m93(context)) {
            if (this.f109 == null) {
                this.f109 = new com.appsflyer.d();
                this.f109.m147(context, this);
            } else {
                AFLogger.afWarnLog("AFInstallReferrer instance already created");
            }
        }
        m74(context, str, null, null, "", null);
    }

    protected void setDeepLinkData(Intent intent) {
        if (intent != null) {
            try {
                if ("android.intent.action.VIEW".equals(intent.getAction())) {
                    this.latestDeepLink = intent.getData();
                    StringBuilder sb = new StringBuilder("Unity setDeepLinkData = ");
                    sb.append(this.latestDeepLink);
                    AFLogger.afDebugLog(sb.toString());
                }
            } catch (Throwable th) {
                AFLogger.afErrorLog("Exception while setting deeplink data (unity). ", th);
            }
        }
    }

    public void reportTrackSession(Context context) {
        r.m212().m220("reportTrackSession", new String[0]);
        r.m212().m217();
        m113(context, (String) null, (Map<String, Object>) null);
    }

    public void trackEvent(Context context, String str, Map<String, Object> map) {
        r.m212().m220("trackEvent", str, new JSONObject(map == null ? new HashMap<>() : map).toString());
        m113(context, str, map);
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    final void m113(Context context, String str, Map<String, Object> map) {
        Intent intent;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            intent = activity.getIntent();
            AFDeepLinkManager.getInstance().currentActivityHash = System.identityHashCode(activity);
        } else {
            intent = null;
        }
        Intent intent2 = intent;
        if (AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY) == null) {
            AFLogger.afWarnLog("[TrackEvent/Launch] AppsFlyer's SDK cannot send any event without providing DevKey.");
            return;
        }
        if (map == null) {
            map = new HashMap<>();
        }
        JSONObject jSONObject = new JSONObject(map);
        String referrer = AppsFlyerProperties.getInstance().getReferrer(context);
        String string = jSONObject.toString();
        if (referrer == null) {
            referrer = "";
        }
        m74(context, null, str, string, referrer, intent2);
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    private static void m101(Context context, String str, String str2, String str3) {
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.IS_MONITOR, false)) {
            Intent intent = new Intent("com.appsflyer.MonitorBroadcast");
            intent.setPackage("com.appsflyer.nightvision");
            intent.putExtra("message", str2);
            intent.putExtra(com.bytedance.b.a.a.a.n, str3);
            intent.putExtra("packageName", "true");
            intent.putExtra(Constants.URL_MEDIA_SOURCE, new Integer(Process.myPid()));
            intent.putExtra("eventIdentifier", str);
            intent.putExtra("sdk", BuildConfig.VERSION_NAME);
            context.sendBroadcast(intent);
        }
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    final void m112(Context context, String str) {
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false) && AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID) == null) {
            AFLogger.afInfoLog("CustomerUserId not set, Tracking is disabled", true);
            return;
        }
        HashMap map = new HashMap();
        String string = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY);
        if (string == null) {
            AFLogger.afWarnLog("[registerUninstall] AppsFlyer's SDK cannot send any event without providing DevKey.");
            return;
        }
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            map.put("app_version_code", Integer.toString(packageInfo.versionCode));
            map.put("app_version_name", packageInfo.versionName);
            map.put(TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY, packageManager.getApplicationLabel(packageInfo.applicationInfo).toString());
            long j = packageInfo.firstInstallTime;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmssZ", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            map.put("installDate", simpleDateFormat.format(new Date(j)));
        } catch (Throwable th) {
            AFLogger.afErrorLog("Exception while collecting application version info.", th);
        }
        m62(context, map);
        String string2 = AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID);
        if (string2 != null) {
            map.put("appUserId", string2);
        }
        try {
            map.put(av.j, Build.MODEL);
            map.put(av.k, Build.BRAND);
        } catch (Throwable th2) {
            AFLogger.afErrorLog("Exception while collecting device brand and model.", th2);
        }
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, false)) {
            map.put(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, "true");
        }
        k kVarM192 = n.m192(context.getContentResolver());
        if (kVarM192 != null) {
            map.put("amazon_aid", kVarM192.m185());
            map.put("amazon_aid_limit", String.valueOf(kVarM192.m184()));
        }
        String string3 = AppsFlyerProperties.getInstance().getString(ServerParameters.ADVERTISING_ID_PARAM);
        if (string3 != null) {
            map.put(ServerParameters.ADVERTISING_ID_PARAM, string3);
        }
        map.put("devkey", string);
        map.put(IWeiboService.ResponseConstants.UID, p.m202((WeakReference<Context>) new WeakReference(context)));
        map.put("af_gcm_token", str);
        map.put("launch_counter", Integer.toString(m85(context.getSharedPreferences("appsflyer-data", 0), "appsFlyerCount", false)));
        map.put("sdk", Integer.toString(Build.VERSION.SDK_INT));
        String strM77 = m77((WeakReference<Context>) new WeakReference(context));
        if (strM77 != null) {
            map.put("channel", strM77);
        }
        try {
            m mVar = new m(context, isTrackingStopped());
            mVar.f241 = map;
            StringBuilder sb = new StringBuilder();
            sb.append(ServerConfigHandler.getUrl(f81));
            sb.append(packageName);
            mVar.execute(sb.toString());
        } catch (Throwable th3) {
            AFLogger.afErrorLog(th3.getMessage(), th3);
        }
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    private static void m88(Context context, String str) {
        Intent intent = new Intent("com.appsflyer.testIntgrationBroadcast");
        intent.putExtra("params", str);
        if (Build.VERSION.SDK_INT >= 26) {
            if (context.getPackageManager().queryBroadcastReceivers(intent, 0).toString().contains("com.appsflyer.referrerSender")) {
                Intent intent2 = new Intent(intent);
                intent2.setComponent(new ComponentName("com.appsflyer.referrerSender", "com.appsflyer.referrerSender.Receiver"));
                context.sendBroadcast(intent2);
                return;
            }
            return;
        }
        context.sendBroadcast(intent);
    }

    public void setDeviceTrackingDisabled(boolean z) {
        r.m212().m220("setDeviceTrackingDisabled", String.valueOf(z));
        AppsFlyerProperties.getInstance().set(AppsFlyerProperties.DEVICE_TRACKING_DISABLED, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ˋ, reason: contains not printable characters */
    public static Map<String, String> m70(Context context) throws l {
        String string = context.getSharedPreferences("appsflyer-data", 0).getString("attributionId", null);
        if (string != null && string.length() > 0) {
            return m71(string);
        }
        throw new l();
    }

    public void registerConversionListener(Context context, AppsFlyerConversionListener appsFlyerConversionListener) {
        r.m212().m220("registerConversionListener", new String[0]);
        if (appsFlyerConversionListener != null) {
            f82 = appsFlyerConversionListener;
        }
    }

    public void unregisterConversionListener() {
        r.m212().m220("unregisterConversionListener", new String[0]);
        f82 = null;
    }

    public void registerValidatorListener(Context context, AppsFlyerInAppPurchaseValidatorListener appsFlyerInAppPurchaseValidatorListener) {
        r.m212().m220("registerValidatorListener", new String[0]);
        AFLogger.afDebugLog("registerValidatorListener called");
        if (appsFlyerInAppPurchaseValidatorListener == null) {
            AFLogger.afDebugLog("registerValidatorListener null listener");
        } else {
            f79 = appsFlyerInAppPurchaseValidatorListener;
        }
    }

    protected void getConversionData(Context context, final ConversionDataListener conversionDataListener) {
        f82 = new AppsFlyerConversionListener() { // from class: com.appsflyer.AppsFlyerLib.5
            @Override // com.appsflyer.AppsFlyerConversionListener
            public final void onAppOpenAttribution(Map<String, String> map) {
            }

            @Override // com.appsflyer.AppsFlyerConversionListener
            public final void onAttributionFailure(String str) {
            }

            @Override // com.appsflyer.AppsFlyerConversionListener
            public final void onInstallConversionDataLoaded(Map<String, String> map) {
                conversionDataListener.onConversionDataLoaded(map);
            }

            @Override // com.appsflyer.AppsFlyerConversionListener
            public final void onInstallConversionFailure(String str) {
                conversionDataListener.onConversionFailure(str);
            }
        };
    }

    /* renamed from: ˎ, reason: contains not printable characters */
    private static Map<String, String> m79(Context context, String str) throws PackageManager.NameNotFoundException {
        int i;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        boolean z = false;
        for (String str2 : str.split("&")) {
            int iIndexOf = str2.indexOf("=");
            String strSubstring = iIndexOf > 0 ? str2.substring(0, iIndexOf) : str2;
            if (!linkedHashMap.containsKey(strSubstring)) {
                if (strSubstring.equals(Constants.URL_CAMPAIGN)) {
                    strSubstring = "campaign";
                } else if (strSubstring.equals(Constants.URL_MEDIA_SOURCE)) {
                    strSubstring = "media_source";
                } else if (strSubstring.equals("af_prt")) {
                    strSubstring = "agency";
                    z = true;
                }
                linkedHashMap.put(strSubstring, "");
            }
            linkedHashMap.put(strSubstring, (iIndexOf <= 0 || str2.length() <= (i = iIndexOf + 1)) ? null : str2.substring(i));
        }
        try {
            if (!linkedHashMap.containsKey("install_time")) {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
                long j = packageInfo.firstInstallTime;
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                linkedHashMap.put("install_time", simpleDateFormat.format(new Date(j)));
            }
        } catch (Exception e2) {
            AFLogger.afErrorLog("Could not fetch install time. ", e2);
        }
        if (!linkedHashMap.containsKey("af_status")) {
            linkedHashMap.put("af_status", "Non-organic");
        }
        if (z) {
            linkedHashMap.remove("media_source");
        }
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ˋ, reason: contains not printable characters */
    public static Map<String, String> m71(String str) throws JSONException {
        HashMap map = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (!f84.contains(next)) {
                    String string = jSONObject.getString(next);
                    if (!TextUtils.isEmpty(string) && !"null".equals(string)) {
                        map.put(next, string);
                    }
                }
            }
            return map;
        } catch (JSONException e2) {
            AFLogger.afErrorLog(e2.getMessage(), e2);
            return null;
        }
    }

    /* renamed from: ˋ, reason: contains not printable characters */
    private void m74(Context context, String str, String str2, String str3, String str4, Intent intent) {
        Context applicationContext = context.getApplicationContext();
        boolean z = false;
        boolean z2 = str2 == null;
        if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.AF_WAITFOR_CUSTOMERID, false) && AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.APP_USER_ID) == null) {
            z = true;
        }
        if (z) {
            AFLogger.afInfoLog("CustomerUserId not set, Tracking is disabled", true);
            return;
        }
        if (z2) {
            if (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.LAUNCH_PROTECT_ENABLED, true)) {
                if (m92()) {
                    return;
                }
            } else {
                AFLogger.afInfoLog("Allowing multiple launches within a 5 second time window.");
            }
            this.f88 = System.currentTimeMillis();
        }
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutorM39 = AFExecutor.getInstance().m39();
        m103(scheduledThreadPoolExecutorM39, new c(this, new WeakReference(applicationContext), str, str2, str3, str4, scheduledThreadPoolExecutorM39, false, intent, (byte) 0), 150L, TimeUnit.MILLISECONDS);
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    private boolean m92() {
        if (this.f88 > 0) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.f88;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS Z", Locale.US);
            long j = this.f88;
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String str = simpleDateFormat.format(new Date(j));
            long j2 = this.f97;
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String str2 = simpleDateFormat.format(new Date(j2));
            if (jCurrentTimeMillis < this.f101 && !isTrackingStopped()) {
                AFLogger.afInfoLog(String.format(Locale.US, "Last Launch attempt: %s;\nLast successful Launch event: %s;\nThis launch is blocked: %s ms < %s ms", str, str2, Long.valueOf(jCurrentTimeMillis), Long.valueOf(this.f101)));
                return true;
            }
            if (!isTrackingStopped()) {
                AFLogger.afInfoLog(String.format(Locale.US, "Last Launch attempt: %s;\nLast successful Launch event: %s;\nSending launch (+%s ms)", str, str2, Long.valueOf(jCurrentTimeMillis)));
            }
        } else if (!isTrackingStopped()) {
            AFLogger.afInfoLog("Sending first launch for this session!");
        }
        return false;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(110:0|2|(1:4)|405|5|(3:7|(1:9)(1:10)|11)(1:12)|13|(1:15)(1:16)|17|18|408|19|(1:21)|22|(1:24)|25|(1:27)|(1:33)|34|(6:36|(6:38|(1:40)|41|(1:43)(1:44)|45|(1:54)(3:49|(1:51)(1:52)|53))|55|(1:57)(1:58)|59|(1:61))(6:62|426|63|(1:65)|66|(1:68)(1:69))|74|(1:79)|80|(1:82)|418|83|(1:85)|90|(1:94)|95|(1:97)|(1:103)|104|(1:106)(3:107|(1:109)(1:110)|111)|(1:113)|114|(3:116|(1:118)(3:119|(10:121|(1:127)(1:126)|(1:129)|(1:135)(1:134)|(1:137)|(1:143)(1:142)|(1:145)|(1:151)(1:150)|(1:156)(1:155)|(1:158)(1:159))|(1:161))|(1:163))|(1:165)|166|(1:168)|(2:173|(2:400|401)(1:177))(1:172)|178|(1:180)|181|(1:183)(2:184|(1:186))|(2:188|(1:190))|191|(1:193)|194|(3:196|(1:198)|199)|200|(1:202)|203|(3:416|205|(1:213))|214|(1:216)(3:217|(5:239|(1:241)(1:242)|(1:244)(1:245)|246|(3:264|(1:266)(1:267)|(1:269)(1:270))(2:250|(4:414|252|(1:(2:256|257)(0))(1:254)|(0)(0))(0)))(2:221|(6:412|223|(1:(2:227|228)(0))(1:225)|(0)(0)|246|(4:248|264|(0)(0)|(0)(0))(0))(0))|404)|422|271|(1:273)|406|278|429|283|410|288|293|420|294|427|299|300|424|301|(1:303)|304|(3:306|(1:308)(1:309)|310)|311|319|(1:321)|322|(1:324)|325|(1:327)|328|(1:330)|331|(3:333|(1:335)|336)|337|(1:339)|340|(1:346)(1:345)|(1:348)|349|(2:351|(1:353)(1:354))|355|(1:357)|358|(1:364)|365|(1:367)(1:368)|369|(2:373|(1:375))|376|(6:378|(4:380|(1:382)|383|(1:385))|386|(1:388)|389|(2:391|(1:393)))|394|(1:396)|397|(1:399)|404|(1:(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(59:0|2|(1:4)|(52:405|5|(3:7|(1:9)(1:10)|11)(1:12)|13|(1:15)(1:16)|17|18|408|19|(1:21)|22|(1:24)|25|(1:27)|(1:33)|34|(6:36|(6:38|(1:40)|41|(1:43)(1:44)|45|(1:54)(3:49|(1:51)(1:52)|53))|55|(1:57)(1:58)|59|(1:61))(6:62|426|63|(1:65)|66|(1:68)(1:69))|74|(1:79)|80|(1:82)|418|83|(1:85)|90|(1:94)|95|(1:97)|(1:103)|104|(1:106)(3:107|(1:109)(1:110)|111)|(1:113)|114|(3:116|(1:118)(3:119|(10:121|(1:127)(1:126)|(1:129)|(1:135)(1:134)|(1:137)|(1:143)(1:142)|(1:145)|(1:151)(1:150)|(1:156)(1:155)|(1:158)(1:159))|(1:161))|(1:163))|(1:165)|166|(1:168)|(2:173|(2:400|401)(1:177))(1:172)|178|(1:180)|181|(1:183)(2:184|(1:186))|(2:188|(1:190))|191|(1:193)|194|(3:196|(1:198)|199)|200|(1:202)|203|(3:416|205|(1:213))|214)|(1:216)(3:217|(5:239|(1:241)(1:242)|(1:244)(1:245)|246|(3:264|(1:266)(1:267)|(1:269)(1:270))(2:250|(4:414|252|(1:(2:256|257)(0))(1:254)|(0)(0))(0)))(2:221|(6:412|223|(1:(2:227|228)(0))(1:225)|(0)(0)|246|(4:248|264|(0)(0)|(0)(0))(0))(0))|404)|422|271|(1:273)|406|278|429|283|410|288|293|420|294|427|299|300|424|301|(1:303)|304|(3:306|(1:308)(1:309)|310)|311|319|(1:321)|322|(1:324)|325|(1:327)|328|(1:330)|331|(3:333|(1:335)|336)|337|(1:339)|340|(1:346)(1:345)|(1:348)|349|(2:351|(1:353)(1:354))|355|(1:357)|358|(1:364)|365|(1:367)(1:368)|369|(2:373|(1:375))|376|(6:378|(4:380|(1:382)|383|(1:385))|386|(1:388)|389|(2:391|(1:393)))|394|(1:396)|397|(1:399)|404|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:275:0x0667, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:277:0x0669, code lost:
    
        r6 = new java.lang.StringBuilder("ERROR: could not get uid ");
        r6.append(r0.getMessage());
        com.appsflyer.AFLogger.afErrorLog(r6.toString(), r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:280:0x068c, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:282:0x068e, code lost:
    
        com.appsflyer.AFLogger.afErrorLog("Exception while collecting display language name. ", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:285:0x06a1, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:287:0x06a3, code lost:
    
        com.appsflyer.AFLogger.afErrorLog("Exception while collecting display language code. ", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:290:0x06b6, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:292:0x06b8, code lost:
    
        com.appsflyer.AFLogger.afErrorLog("Exception while collecting country name. ", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:296:0x06fb, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:298:0x06fd, code lost:
    
        com.appsflyer.AFLogger.afErrorLog("Exception while collecting install date. ", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:313:0x07b4, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:315:0x07b6, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:316:0x07b7, code lost:
    
        r10 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:318:0x07ba, code lost:
    
        com.appsflyer.AFLogger.afErrorLog("Exception while collecting app version data ", r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:213:0x051e A[Catch: Throwable -> 0x0b5d, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0530 A[Catch: Throwable -> 0x0b5d, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0539 A[Catch: Throwable -> 0x0b5d, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:242:0x05cd  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x05d0 A[Catch: Throwable -> 0x0b5d, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:245:0x05db A[Catch: Throwable -> 0x0b5d, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:264:0x063c A[Catch: Throwable -> 0x0b5d, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0640 A[Catch: Throwable -> 0x0b5d, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:267:0x0643  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0646 A[Catch: Throwable -> 0x0b5d, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0651 A[Catch: Throwable -> 0x0b5d, TRY_LEAVE, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0661 A[Catch: Exception -> 0x0667, Throwable -> 0x0b5d, TRY_LEAVE, TryCatch #10 {Exception -> 0x0667, blocks: (B:271:0x0656, B:273:0x0661), top: B:422:0x0656, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:303:0x071b A[Catch: Throwable -> 0x07b4, TryCatch #12 {Throwable -> 0x07b4, blocks: (B:301:0x0713, B:303:0x071b, B:304:0x0727, B:306:0x077b, B:308:0x078b, B:310:0x079c, B:311:0x07a1), top: B:424:0x0713 }] */
    /* JADX WARN: Removed duplicated region for block: B:306:0x077b A[Catch: Throwable -> 0x07b4, TryCatch #12 {Throwable -> 0x07b4, blocks: (B:301:0x0713, B:303:0x071b, B:304:0x0727, B:306:0x077b, B:308:0x078b, B:310:0x079c, B:311:0x07a1), top: B:424:0x0713 }] */
    /* JADX WARN: Removed duplicated region for block: B:321:0x07c5 A[Catch: Throwable -> 0x0b5d, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:324:0x07d5 A[Catch: Throwable -> 0x0b5d, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:327:0x07e6 A[Catch: Throwable -> 0x0b5d, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:330:0x0810 A[Catch: Throwable -> 0x0b5d, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:333:0x081b A[Catch: Throwable -> 0x0b5d, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0846 A[Catch: Throwable -> 0x0b5d, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:342:0x0874 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:346:0x0880  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x0883 A[Catch: Throwable -> 0x0b5d, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:351:0x08b0 A[Catch: Throwable -> 0x0b5d, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:357:0x08d8 A[Catch: Throwable -> 0x0b5d, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:367:0x093c  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x093e  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x0965 A[Catch: Throwable -> 0x0b5d, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:378:0x09ad A[Catch: Throwable -> 0x0b5d, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:396:0x0b19 A[Catch: Throwable -> 0x0b5d, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* JADX WARN: Removed duplicated region for block: B:399:0x0b35 A[Catch: Throwable -> 0x0b5d, TryCatch #0 {Throwable -> 0x0b5d, blocks: (B:5:0x0033, B:7:0x0039, B:11:0x0046, B:17:0x0060, B:19:0x006b, B:21:0x0087, B:22:0x0091, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:33:0x00b5, B:34:0x00bc, B:36:0x00f7, B:38:0x0106, B:40:0x0110, B:41:0x011d, B:43:0x0127, B:45:0x0131, B:47:0x0135, B:49:0x0141, B:51:0x0164, B:53:0x0174, B:52:0x0171, B:54:0x0193, B:55:0x01ab, B:57:0x01c8, B:59:0x01d0, B:61:0x01e3, B:74:0x026b, B:77:0x0283, B:79:0x028d, B:80:0x0297, B:82:0x02a3, B:83:0x02a8, B:85:0x02b6, B:90:0x02c3, B:92:0x02cf, B:94:0x02d5, B:95:0x02da, B:97:0x02e9, B:99:0x02f0, B:103:0x02fa, B:104:0x02ff, B:106:0x030e, B:113:0x0335, B:114:0x033e, B:116:0x0351, B:118:0x0359, B:163:0x03f8, B:119:0x0363, B:121:0x0374, B:123:0x0380, B:129:0x038c, B:131:0x03a0, B:137:0x03ac, B:139:0x03b4, B:145:0x03c0, B:147:0x03c8, B:153:0x03d4, B:159:0x03e4, B:161:0x03f1, B:165:0x0403, B:166:0x040c, B:168:0x0412, B:170:0x041d, B:172:0x0423, B:178:0x0440, B:180:0x044c, B:181:0x0451, B:183:0x045d, B:188:0x047a, B:190:0x0481, B:191:0x0486, B:193:0x0492, B:194:0x04a1, B:196:0x04ae, B:198:0x04b4, B:199:0x04ca, B:200:0x04cf, B:202:0x04db, B:203:0x04e0, B:213:0x051e, B:214:0x0523, B:216:0x0530, B:271:0x0656, B:273:0x0661, B:278:0x067e, B:283:0x0693, B:288:0x06a8, B:293:0x06bd, B:294:0x06d4, B:319:0x07bf, B:321:0x07c5, B:322:0x07cc, B:324:0x07d5, B:325:0x07da, B:327:0x07e6, B:328:0x07f3, B:330:0x0810, B:333:0x081b, B:335:0x0828, B:336:0x083f, B:337:0x0842, B:339:0x0846, B:340:0x085e, B:343:0x0876, B:348:0x0883, B:349:0x08a4, B:351:0x08b0, B:353:0x08bf, B:354:0x08c7, B:355:0x08ce, B:357:0x08d8, B:358:0x08ee, B:360:0x08f8, B:362:0x08fe, B:364:0x0906, B:365:0x090b, B:369:0x093f, B:373:0x0951, B:375:0x0965, B:376:0x096f, B:378:0x09ad, B:380:0x09b1, B:382:0x09be, B:383:0x09e5, B:385:0x09eb, B:386:0x09f0, B:388:0x0a09, B:391:0x0a15, B:393:0x0a23, B:394:0x0a28, B:396:0x0b19, B:397:0x0b29, B:399:0x0b35, B:318:0x07ba, B:298:0x06fd, B:292:0x06b8, B:287:0x06a3, B:282:0x068e, B:277:0x0669, B:217:0x0539, B:219:0x0554, B:221:0x055c, B:223:0x0562, B:244:0x05d0, B:246:0x05e0, B:248:0x05f4, B:250:0x05fc, B:252:0x0602, B:269:0x0646, B:270:0x0651, B:256:0x0611, B:261:0x0624, B:263:0x0634, B:264:0x063c, B:266:0x0640, B:245:0x05db, B:227:0x0585, B:232:0x0598, B:234:0x05a8, B:236:0x05b0, B:238:0x05c0, B:239:0x05c6, B:241:0x05ca, B:209:0x050f, B:211:0x0516, B:184:0x0463, B:186:0x046f, B:173:0x0429, B:175:0x0435, B:177:0x043b, B:400:0x0b4a, B:107:0x0316, B:109:0x0327, B:111:0x032e, B:89:0x02be, B:62:0x01f8, B:63:0x0202, B:65:0x020b, B:66:0x0243, B:68:0x025c, B:69:0x0260, B:73:0x0266, B:31:0x00ae, B:12:0x0051, B:205:0x04fa), top: B:405:0x0033, inners: #1, #2, #3, #4, #5, #6, #7, #8, #9, #10, #11, #13, #15 }] */
    /* renamed from: ˎ, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    final java.util.Map<java.lang.String, java.lang.Object> m109(android.content.Context r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, boolean r24, android.content.SharedPreferences r25, boolean r26, android.content.Intent r27) throws java.security.NoSuchAlgorithmException {
        /*
            Method dump skipped, instructions count: 2919
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AppsFlyerLib.m109(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, android.content.SharedPreferences, boolean, android.content.Intent):java.util.Map");
    }

    public void setConsumeAFDeepLinks(boolean z) {
        AppsFlyerProperties.getInstance().set("consumeAfDeepLink", z);
        r.m212().m220("setConsumeAFDeepLinks: ".concat(String.valueOf(z)), new String[0]);
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    private static void m62(Context context, Map<String, ? super String> map) {
        i iVar = i.c.f209;
        i.b bVarM171 = i.m171(context);
        map.put("network", bVarM171.m173());
        if (bVarM171.m174() != null) {
            map.put("operator", bVarM171.m174());
        }
        if (bVarM171.m175() != null) {
            map.put(DispatchConstants.CARRIER, bVarM171.m175());
        }
    }

    protected void handleDeepLinkCallback(Context context, Map<String, Object> map, Uri uri) throws PackageManager.NameNotFoundException {
        final Map<String, String> map2;
        map.put("af_deeplink", uri.toString());
        if (uri.getQueryParameter("af_deeplink") != null) {
            this.f99 = "AppsFlyer_Test".equals(uri.getQueryParameter("media_source")) && Boolean.parseBoolean(uri.getQueryParameter("is_retargeting"));
            map2 = m79(context, uri.getQuery());
            String path = uri.getPath();
            if (path != null) {
                map2.put("path", path);
            }
            String scheme = uri.getScheme();
            if (scheme != null) {
                map2.put("scheme", scheme);
            }
            String host = uri.getHost();
            if (host != null) {
                map2.put(com.alipay.sdk.cons.c.f, host);
            }
        } else {
            map2 = new HashMap<>();
            map2.put("link", uri.toString());
        }
        final WeakReference weakReference = new WeakReference(context);
        s sVar = new s(uri, this);
        sVar.setConnProvider(new OneLinkHttpTask.HttpsUrlConnectionProvider());
        if (sVar.m230()) {
            sVar.m229(new s.c() { // from class: com.appsflyer.AppsFlyerLib.1
                @Override // com.appsflyer.s.c
                /* renamed from: ˎ, reason: contains not printable characters */
                public final void mo116(String str) {
                    if (AppsFlyerLib.f82 != null) {
                        m114(map2);
                        AppsFlyerLib.f82.onAttributionFailure(str);
                    }
                }

                /* renamed from: ˋ, reason: contains not printable characters */
                private void m114(Map<String, String> map3) {
                    if (weakReference.get() != null) {
                        AppsFlyerLib.m90((Context) weakReference.get(), "deeplinkAttribution", new JSONObject(map3).toString());
                    }
                }

                @Override // com.appsflyer.s.c
                /* renamed from: ˊ, reason: contains not printable characters */
                public final void mo115(Map<String, String> map3) {
                    for (String str : map3.keySet()) {
                        map2.put(str, map3.get(str));
                    }
                    m114(map2);
                    AppsFlyerLib.m102(map2);
                }
            });
            AFExecutor.getInstance().getThreadPoolExecutor().execute(sVar);
        } else if (f82 != null) {
            try {
                f82.onAppOpenAttribution(map2);
            } catch (Throwable th) {
                AFLogger.afErrorLog(th.getLocalizedMessage(), th);
            }
        }
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    private static boolean m64(Context context) throws PackageManager.NameNotFoundException {
        try {
            if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == 0) {
                return true;
            }
        } catch (Throwable th) {
            AFLogger.afErrorLog("WARNING:  Google play services is unavailable. ", th);
        }
        try {
            context.getPackageManager().getPackageInfo("com.google.android.gms", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e2) {
            AFLogger.afErrorLog("WARNING:  Google Play Services is unavailable. ", e2);
            return false;
        }
    }

    /* renamed from: ॱॱ, reason: contains not printable characters */
    private static boolean m105(Context context) {
        return (AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_ANDROID_ID_FORCE_BY_USER, false) || AppsFlyerProperties.getInstance().getBoolean(AppsFlyerProperties.COLLECT_IMEI_FORCE_BY_USER, false)) || !m64(context);
    }

    /* renamed from: ʽ, reason: contains not printable characters */
    private static String m55(Context context) {
        String string = AppsFlyerProperties.getInstance().getString("api_store_value");
        if (string != null) {
            return string;
        }
        String strM98 = m98((WeakReference<Context>) new WeakReference(context), "AF_STORE");
        if (strM98 != null) {
            return strM98;
        }
        return null;
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    private static String m57(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod(ApiRequest.METHOD_GET, String.class).invoke(null, str);
        } catch (Throwable th) {
            AFLogger.afErrorLog(th.getMessage(), th);
            return null;
        }
    }

    @Nullable
    /* renamed from: ॱ, reason: contains not printable characters */
    private static String m98(WeakReference<Context> weakReference, String str) {
        if (weakReference.get() == null) {
            return null;
        }
        return m58(str, weakReference.get().getPackageManager(), weakReference.get().getPackageName());
    }

    @Nullable
    /* renamed from: ˊ, reason: contains not printable characters */
    private static String m58(String str, PackageManager packageManager, String str2) {
        Object obj;
        try {
            Bundle bundle = ((PackageItemInfo) packageManager.getApplicationInfo(str2, 128)).metaData;
            if (bundle == null || (obj = bundle.get(str)) == null) {
                return null;
            }
            return obj.toString();
        } catch (Throwable th) {
            StringBuilder sb = new StringBuilder("Could not find ");
            sb.append(str);
            sb.append(" value in the manifest");
            AFLogger.afErrorLog(sb.toString(), th);
            return null;
        }
    }

    public void setPreinstallAttribution(String str, String str2, String str3) throws JSONException {
        AFLogger.afDebugLog("setPreinstallAttribution API called");
        JSONObject jSONObject = new JSONObject();
        if (str != null) {
            try {
                jSONObject.put(Constants.URL_MEDIA_SOURCE, str);
            } catch (JSONException e2) {
                AFLogger.afErrorLog(e2.getMessage(), e2);
            }
        }
        if (str2 != null) {
            jSONObject.put(Constants.URL_CAMPAIGN, str2);
        }
        if (str3 != null) {
            jSONObject.put(Constants.URL_SITE_ID, str3);
        }
        if (jSONObject.has(Constants.URL_MEDIA_SOURCE)) {
            AppsFlyerProperties.getInstance().set("preInstallName", jSONObject.toString());
        } else {
            AFLogger.afWarnLog("Cannot set preinstall attribution data without a media source");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0060 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: ˎ, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String m76(java.io.File r4, java.lang.String r5) throws java.lang.Throwable {
        /*
            r0 = 0
            java.util.Properties r1 = new java.util.Properties     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L29 java.io.FileNotFoundException -> L41
            r1.<init>()     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L29 java.io.FileNotFoundException -> L41
            java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L29 java.io.FileNotFoundException -> L41
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L26 java.lang.Throwable -> L29 java.io.FileNotFoundException -> L41
            r1.load(r2)     // Catch: java.lang.Throwable -> L24 java.io.FileNotFoundException -> L42 java.lang.Throwable -> L5d
            java.lang.String r3 = "Found PreInstall property!"
            com.appsflyer.AFLogger.afInfoLog(r3)     // Catch: java.lang.Throwable -> L24 java.io.FileNotFoundException -> L42 java.lang.Throwable -> L5d
            java.lang.String r5 = r1.getProperty(r5)     // Catch: java.lang.Throwable -> L24 java.io.FileNotFoundException -> L42 java.lang.Throwable -> L5d
            r2.close()     // Catch: java.lang.Throwable -> L1b
            goto L23
        L1b:
            r4 = move-exception
            java.lang.String r0 = r4.getMessage()
            com.appsflyer.AFLogger.afErrorLog(r0, r4)
        L23:
            return r5
        L24:
            r4 = move-exception
            goto L2b
        L26:
            r4 = move-exception
            r2 = r0
            goto L5e
        L29:
            r4 = move-exception
            r2 = r0
        L2b:
            java.lang.String r5 = r4.getMessage()     // Catch: java.lang.Throwable -> L5d
            com.appsflyer.AFLogger.afErrorLog(r5, r4)     // Catch: java.lang.Throwable -> L5d
            if (r2 == 0) goto L5c
            r2.close()     // Catch: java.lang.Throwable -> L38
            goto L5c
        L38:
            r4 = move-exception
            java.lang.String r5 = r4.getMessage()
            com.appsflyer.AFLogger.afErrorLog(r5, r4)
            goto L5c
        L41:
            r2 = r0
        L42:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5d
            java.lang.String r1 = "PreInstall file wasn't found: "
            r5.<init>(r1)     // Catch: java.lang.Throwable -> L5d
            java.lang.String r4 = r4.getAbsolutePath()     // Catch: java.lang.Throwable -> L5d
            r5.append(r4)     // Catch: java.lang.Throwable -> L5d
            java.lang.String r4 = r5.toString()     // Catch: java.lang.Throwable -> L5d
            com.appsflyer.AFLogger.afDebugLog(r4)     // Catch: java.lang.Throwable -> L5d
            if (r2 == 0) goto L5c
            r2.close()     // Catch: java.lang.Throwable -> L38
        L5c:
            return r0
        L5d:
            r4 = move-exception
        L5e:
            if (r2 == 0) goto L6c
            r2.close()     // Catch: java.lang.Throwable -> L64
            goto L6c
        L64:
            r5 = move-exception
            java.lang.String r0 = r5.getMessage()
            com.appsflyer.AFLogger.afErrorLog(r0, r5)
        L6c:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.AppsFlyerLib.m76(java.io.File, java.lang.String):java.lang.String");
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    private static File m86(String str) {
        if (str == null) {
            return null;
        }
        try {
            if (str.trim().length() > 0) {
                return new File(str.trim());
            }
            return null;
        } catch (Throwable th) {
            AFLogger.afErrorLog(th.getMessage(), th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    /* renamed from: ˎ, reason: contains not printable characters */
    public static String m77(WeakReference<Context> weakReference) {
        String string = AppsFlyerProperties.getInstance().getString("channel");
        if (string == null) {
            string = m98(weakReference, "CHANNEL");
        }
        if (string == null || !string.equals("")) {
            return string;
        }
        return null;
    }

    public boolean isPreInstalledApp(Context context) {
        try {
        } catch (PackageManager.NameNotFoundException e2) {
            AFLogger.afErrorLog("Could not check if app is pre installed", e2);
        }
        return (context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 1) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ॱॱ, reason: contains not printable characters */
    public static String m104(Context context, String str) throws PackageManager.NameNotFoundException {
        SharedPreferences sharedPreferences = context.getSharedPreferences("appsflyer-data", 0);
        if (sharedPreferences.contains("CACHED_CHANNEL")) {
            return sharedPreferences.getString("CACHED_CHANNEL", null);
        }
        m90(context, "CACHED_CHANNEL", str);
        return str;
    }

    public String getAttributionId(ContentResolver contentResolver) {
        Cursor cursorQuery = contentResolver.query(Uri.parse(ATTRIBUTION_ID_CONTENT_URI), new String[]{"aid"}, null, null, null);
        if (cursorQuery != null) {
            try {
                try {
                    if (cursorQuery.moveToFirst()) {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndex("aid"));
                        if (cursorQuery != null) {
                            try {
                                cursorQuery.close();
                            } catch (Exception e2) {
                                AFLogger.afErrorLog(e2.getMessage(), e2);
                            }
                        }
                        return string;
                    }
                } catch (Exception e3) {
                    AFLogger.afErrorLog("Could not collect cursor attribution. ", e3);
                    if (cursorQuery == null) {
                        return null;
                    }
                    try {
                        cursorQuery.close();
                        return null;
                    } catch (Exception e4) {
                        AFLogger.afErrorLog(e4.getMessage(), e4);
                        return null;
                    }
                }
            } finally {
                if (cursorQuery != null) {
                    try {
                        cursorQuery.close();
                    } catch (Exception e5) {
                        AFLogger.afErrorLog(e5.getMessage(), e5);
                    }
                }
            }
        }
        return null;
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    static SharedPreferences m94(Context context) {
        return context.getSharedPreferences("appsflyer-data", 0);
    }

    /* renamed from: ˋ, reason: contains not printable characters */
    static int m66(SharedPreferences sharedPreferences) {
        return m85(sharedPreferences, "appsFlyerCount", false);
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    private static int m85(SharedPreferences sharedPreferences, String str, boolean z) {
        int i = sharedPreferences.getInt(str, 0);
        if (z) {
            i++;
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt(str, i);
            if (Build.VERSION.SDK_INT >= 9) {
                editorEdit.apply();
            } else {
                editorEdit.commit();
            }
        }
        if (r.m212().m228()) {
            r.m212().m224(String.valueOf(i));
        }
        return i;
    }

    public String getAppsFlyerUID(Context context) {
        r.m212().m220("getAppsFlyerUID", new String[0]);
        return p.m202((WeakReference<Context>) new WeakReference(context));
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    private void m63(URL url, String str, String str2, WeakReference<Context> weakReference, String str3, boolean z) throws Throwable {
        HttpURLConnection httpURLConnection;
        Context context = weakReference.get();
        boolean z2 = z && f82 != null;
        OutputStreamWriter outputStreamWriter = null;
        try {
            r.m212().m225(url.toString(), str);
            httpURLConnection = (HttpURLConnection) url.openConnection();
        } catch (Throwable th) {
            th = th;
            httpURLConnection = null;
        }
        try {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(str.getBytes().length));
            httpURLConnection.setRequestProperty("Content-Type", ae.d);
            httpURLConnection.setConnectTimeout(NetCallback.ERROR_CODE_REQUEST_SUCCESS);
            httpURLConnection.setDoOutput(true);
            try {
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8");
                try {
                    outputStreamWriter2.write(str);
                    outputStreamWriter2.close();
                    int responseCode = httpURLConnection.getResponseCode();
                    String strM68 = m68(httpURLConnection);
                    r.m212().m222(url.toString(), responseCode, strM68);
                    AFLogger.afInfoLog("response code: ".concat(String.valueOf(responseCode)));
                    m101(context, LOG_TAG, "SERVER_RESPONSE_CODE", Integer.toString(responseCode));
                    SharedPreferences sharedPreferences = context.getSharedPreferences("appsflyer-data", 0);
                    if (responseCode == 200) {
                        if (weakReference.get() != null && z) {
                            this.f97 = System.currentTimeMillis();
                            if (f87 != null) {
                                f87.onTrackingRequestSuccess();
                            }
                        }
                        String string = AppsFlyerProperties.getInstance().getString("afUninstallToken");
                        if (string != null) {
                            AFLogger.afDebugLog("Uninstall Token exists: ".concat(String.valueOf(string)));
                            if (!sharedPreferences.getBoolean("sentRegisterRequestToAF", false)) {
                                AFLogger.afDebugLog("Resending Uninstall token to AF servers: ".concat(String.valueOf(string)));
                                y.m248(context, new b.e.C0088b(string));
                            }
                        } else if (AppsFlyerProperties.getInstance().getString("gcmProjectNumber") != null) {
                            AFLogger.afDebugLog("GCM Project number exists. Fetching token and sending to AF servers");
                            new y.b(new WeakReference(context)).execute(new Void[0]);
                        }
                        if (this.latestDeepLink != null) {
                            this.latestDeepLink = null;
                        }
                        if (str3 != null) {
                            CacheManager.getInstance().deleteRequest(str3, context);
                        }
                        if (weakReference.get() != null && str3 == null) {
                            m90(context, "sentSuccessfully", "true");
                            if (!this.f89 && System.currentTimeMillis() - this.f110 >= 15000 && this.f92 == null) {
                                this.f92 = AFExecutor.getInstance().m39();
                                m103(this.f92, new d(context), 1L, TimeUnit.SECONDS);
                            }
                        }
                        this.f104 = ServerConfigHandler.m127(strM68).optBoolean("send_background", false);
                    } else if (f87 != null) {
                        f87.onTrackingRequestFailure("Failure: ".concat(String.valueOf(responseCode)));
                    }
                    int i = sharedPreferences.getInt("appsflyerConversionDataRequestRetries", 0);
                    long j = sharedPreferences.getLong("appsflyerConversionDataCacheExpiration", 0L);
                    if (j != 0 && System.currentTimeMillis() - j > 5184000000L) {
                        m90(context, "attributionId", (String) null);
                        m73(context, "appsflyerConversionDataCacheExpiration", 0L);
                    }
                    if (sharedPreferences.getString("attributionId", null) == null && str2 != null && z2 && f82 != null && i <= 5) {
                        ScheduledThreadPoolExecutor scheduledThreadPoolExecutorM39 = AFExecutor.getInstance().m39();
                        m103(scheduledThreadPoolExecutorM39, new b(context.getApplicationContext(), str2, scheduledThreadPoolExecutorM39), 10L, TimeUnit.MILLISECONDS);
                    } else if (str2 == null) {
                        AFLogger.afWarnLog("AppsFlyer dev key is missing.");
                    } else if (z2 && f82 != null && sharedPreferences.getString("attributionId", null) != null && m85(sharedPreferences, "appsFlyerCount", false) > 1) {
                        try {
                            Map<String, String> mapM70 = m70(context);
                            if (mapM70 != null) {
                                try {
                                    if (!mapM70.containsKey("is_first_launch")) {
                                        mapM70.put("is_first_launch", Boolean.toString(false));
                                    }
                                    f82.onInstallConversionDataLoaded(mapM70);
                                } catch (Throwable th2) {
                                    AFLogger.afErrorLog(th2.getLocalizedMessage(), th2);
                                }
                            }
                        } catch (l e2) {
                            AFLogger.afErrorLog(e2.getMessage(), e2);
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    outputStreamWriter = outputStreamWriter2;
                    if (outputStreamWriter != null) {
                        outputStreamWriter.close();
                    } else if (f87 != null) {
                        f87.onTrackingRequestFailure("No Connectivity");
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Throwable th5) {
            th = th5;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    public void validateAndTrackInAppPurchase(Context context, String str, String str2, String str3, String str4, String str5, Map<String, String> map) {
        r rVarM212 = r.m212();
        String[] strArr = new String[6];
        strArr[0] = str;
        strArr[1] = str2;
        strArr[2] = str3;
        strArr[3] = str4;
        strArr[4] = str5;
        strArr[5] = map == null ? "" : map.toString();
        rVarM212.m220("validateAndTrackInAppPurchase", strArr);
        if (!isTrackingStopped()) {
            StringBuilder sb = new StringBuilder("Validate in app called with parameters: ");
            sb.append(str3);
            sb.append(" ");
            sb.append(str4);
            sb.append(" ");
            sb.append(str5);
            AFLogger.afInfoLog(sb.toString());
        }
        if (str == null || str4 == null || str2 == null || str5 == null || str3 == null) {
            if (f79 != null) {
                f79.onValidateInAppFailure("Please provide purchase parameters");
            }
        } else {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutorM39 = AFExecutor.getInstance().m39();
            m103(scheduledThreadPoolExecutorM39, new h(context.getApplicationContext(), AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY), str, str2, str3, str4, str5, map, scheduledThreadPoolExecutorM39, context instanceof Activity ? ((Activity) context).getIntent() : null), 10L, TimeUnit.MILLISECONDS);
        }
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    private static void m103(ScheduledExecutorService scheduledExecutorService, Runnable runnable, long j, TimeUnit timeUnit) {
        if (scheduledExecutorService != null) {
            try {
                if (!scheduledExecutorService.isShutdown() && !scheduledExecutorService.isTerminated()) {
                    scheduledExecutorService.schedule(runnable, j, timeUnit);
                    return;
                }
            } catch (RejectedExecutionException e2) {
                AFLogger.afErrorLog("scheduleJob failed with RejectedExecutionException Exception", e2);
                return;
            } catch (Throwable th) {
                AFLogger.afErrorLog("scheduleJob failed with Exception", th);
                return;
            }
        }
        AFLogger.afWarnLog("scheduler is null, shut downed or terminated");
    }

    @Override // com.appsflyer.a
    public void onHandleReferrer(Map<String, String> map) {
        this.f108 = map;
    }

    public boolean isTrackingStopped() {
        return this.f105;
    }

    class c implements Runnable {

        /* renamed from: ʻ, reason: contains not printable characters */
        private boolean f124;

        /* renamed from: ʼ, reason: contains not printable characters */
        private boolean f125;

        /* renamed from: ʽ, reason: contains not printable characters */
        private ExecutorService f126;

        /* renamed from: ˊ, reason: contains not printable characters */
        private String f127;

        /* renamed from: ˋ, reason: contains not printable characters */
        private String f128;

        /* renamed from: ˎ, reason: contains not printable characters */
        private WeakReference<Context> f129;

        /* renamed from: ˏ, reason: contains not printable characters */
        private final Intent f130;

        /* renamed from: ॱ, reason: contains not printable characters */
        private String f131;

        /* renamed from: ॱॱ, reason: contains not printable characters */
        private String f132;

        /* synthetic */ c(AppsFlyerLib appsFlyerLib, WeakReference weakReference, String str, String str2, String str3, String str4, ExecutorService executorService, boolean z, Intent intent, byte b2) {
            this(weakReference, str, str2, str3, str4, executorService, z, intent);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private c(WeakReference<Context> weakReference, String str, String str2, String str3, String str4, boolean z, boolean z2, Intent intent) {
            this.f129 = weakReference;
            this.f128 = str;
            this.f127 = str2;
            this.f131 = str3;
            this.f132 = str4;
            this.f124 = true;
            this.f126 = z;
            this.f125 = z2;
            this.f130 = intent;
        }

        @Override // java.lang.Runnable
        public final void run() throws NoSuchAlgorithmException {
            AppsFlyerLib.m82(AppsFlyerLib.this, this.f129.get(), this.f128, this.f127, this.f131, this.f132, this.f124, this.f125, this.f130);
        }
    }

    class e implements Runnable {

        /* renamed from: ˊ, reason: contains not printable characters */
        private int f136;

        /* renamed from: ˋ, reason: contains not printable characters */
        private String f137;

        /* renamed from: ˎ, reason: contains not printable characters */
        private Map<String, Object> f138;

        /* renamed from: ˏ, reason: contains not printable characters */
        private WeakReference<Context> f139;

        /* renamed from: ॱ, reason: contains not printable characters */
        private boolean f140;

        /* synthetic */ e(AppsFlyerLib appsFlyerLib, String str, Map map, Context context, boolean z, int i, byte b2) {
            this(str, map, context, z, i);
        }

        private e(String str, Map<String, Object> map, Context context, boolean z, int i) {
            this.f139 = null;
            this.f137 = str;
            this.f138 = map;
            this.f139 = new WeakReference<>(context);
            this.f140 = z;
            this.f136 = i;
        }

        @Override // java.lang.Runnable
        public final void run() throws Throwable {
            if (AppsFlyerLib.this.isTrackingStopped()) {
                return;
            }
            String str = null;
            if (this.f140 && this.f136 <= 2 && AppsFlyerLib.m75(AppsFlyerLib.this)) {
                this.f138.put("rfr", AppsFlyerLib.this.f108);
            }
            this.f138.putAll(new b.e(this.f138, this.f139.get()));
            try {
                try {
                    String str2 = (String) this.f138.get("appsflyerKey");
                    String string = AFHelper.convertToJsonObject(this.f138).toString();
                    try {
                        AppsFlyerLib.m91(AppsFlyerLib.this, this.f137, string, str2, this.f139, null, this.f140);
                    } catch (IOException e) {
                        e = e;
                        str = string;
                        AFLogger.afErrorLog("Exception while sending request to server. ", e);
                        if (str == null || this.f139 == null || this.f137.contains("&isCachedRequest=true&timeincache=")) {
                            return;
                        }
                        CacheManager.getInstance().cacheRequest(new RequestCacheData(this.f137, str, BuildConfig.VERSION_NAME), this.f139.get());
                        AFLogger.afErrorLog(e.getMessage(), e);
                    }
                } catch (Throwable th) {
                    AFLogger.afErrorLog(th.getMessage(), th);
                }
            } catch (IOException e2) {
                e = e2;
            }
        }
    }

    class b extends a {
        public b(Context context, String str, ScheduledExecutorService scheduledExecutorService) {
            super(context, str, scheduledExecutorService);
        }

        @Override // com.appsflyer.AppsFlyerLib.a
        /* renamed from: ˊ */
        public final String mo119() {
            return ServerConfigHandler.getUrl("https://api.%s/install_data/v3/");
        }

        @Override // com.appsflyer.AppsFlyerLib.a
        /* renamed from: ˎ */
        protected final void mo121(Map<String, String> map) {
            map.put("is_first_launch", Boolean.toString(true));
            AppsFlyerLib.f82.onInstallConversionDataLoaded(map);
            AppsFlyerLib.m89(this.f121.get(), "appsflyerConversionDataRequestRetries", 0);
        }

        @Override // com.appsflyer.AppsFlyerLib.a
        /* renamed from: ˊ */
        protected final void mo120(String str, int i) {
            AppsFlyerLib.f82.onInstallConversionFailure(str);
            if (i < 400 || i >= 500) {
                return;
            }
            AppsFlyerLib.m89(this.f121.get(), "appsflyerConversionDataRequestRetries", AppsFlyerLib.m94(this.f121.get()).getInt("appsflyerConversionDataRequestRetries", 0) + 1);
        }
    }

    abstract class a implements Runnable {

        /* renamed from: ˊ, reason: contains not printable characters */
        private ScheduledExecutorService f118;

        /* renamed from: ˋ, reason: contains not printable characters */
        private String f119;

        /* renamed from: ˎ, reason: contains not printable characters */
        private AtomicInteger f120 = new AtomicInteger(0);

        /* renamed from: ˏ, reason: contains not printable characters */
        WeakReference<Context> f121;

        /* renamed from: ˊ, reason: contains not printable characters */
        public abstract String mo119();

        /* renamed from: ˊ, reason: contains not printable characters */
        protected abstract void mo120(String str, int i);

        /* renamed from: ˎ, reason: contains not printable characters */
        protected abstract void mo121(Map<String, String> map);

        a(Context context, String str, ScheduledExecutorService scheduledExecutorService) {
            this.f121 = null;
            this.f121 = new WeakReference<>(context);
            this.f119 = str;
            if (scheduledExecutorService == null) {
                this.f118 = AFExecutor.getInstance().m39();
            } else {
                this.f118 = scheduledExecutorService;
            }
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            HttpURLConnection httpURLConnection;
            Context context;
            if (this.f119 == null || this.f119.length() == 0 || AppsFlyerLib.this.isTrackingStopped()) {
                return;
            }
            this.f120.incrementAndGet();
            HttpURLConnection httpURLConnection2 = null;
            try {
                try {
                    context = this.f121.get();
                } catch (Throwable th) {
                    th = th;
                }
                if (context == null) {
                    this.f120.decrementAndGet();
                    return;
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                String strM104 = AppsFlyerLib.m104(context, AppsFlyerLib.m77((WeakReference<Context>) new WeakReference(context)));
                String strConcat = "";
                if (strM104 != null) {
                    if (AppsFlyerLib.f83.contains(strM104.toLowerCase())) {
                        AFLogger.afWarnLog(String.format("AF detected using redundant Google-Play channel for attribution - %s. Using without channel postfix.", strM104));
                    } else {
                        strConcat = "-".concat(String.valueOf(strM104));
                    }
                }
                StringBuilder sb = new StringBuilder();
                sb.append(mo119());
                sb.append(context.getPackageName());
                sb.append(strConcat);
                sb.append("?devkey=");
                sb.append(this.f119);
                sb.append("&device_id=");
                sb.append(p.m202((WeakReference<Context>) new WeakReference(context)));
                r.m212().m225(sb.toString(), "");
                StringBuilder sb2 = new StringBuilder("Calling server for attribution url: ");
                sb2.append(sb.toString());
                j.AnonymousClass2.m182(sb2.toString());
                httpURLConnection = (HttpURLConnection) new URL(sb.toString()).openConnection();
                try {
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(NetCallback.ERROR_CODE_REQUEST_SUCCESS);
                    httpURLConnection.setRequestProperty("Connection", "close");
                    httpURLConnection.connect();
                    int responseCode = httpURLConnection.getResponseCode();
                    String strM68 = AppsFlyerLib.m68(httpURLConnection);
                    r.m212().m222(sb.toString(), responseCode, strM68);
                    if (responseCode == 200) {
                        AppsFlyerLib.m73(context, "appsflyerGetConversionDataTiming", (System.currentTimeMillis() - jCurrentTimeMillis) / 1000);
                        j.AnonymousClass2.m182("Attribution data: ".concat(String.valueOf(strM68)));
                        if (strM68.length() > 0 && context != null) {
                            Map<String, String> mapM71 = AppsFlyerLib.m71(strM68);
                            String str = mapM71.get("iscache");
                            if (str != null && Boolean.toString(false).equals(str)) {
                                AppsFlyerLib.m73(context, "appsflyerConversionDataCacheExpiration", System.currentTimeMillis());
                            }
                            if (mapM71.containsKey(Constants.URL_SITE_ID)) {
                                if (mapM71.containsKey("af_channel")) {
                                    StringBuilder sb3 = new StringBuilder(Constants.LOG_INVITE_DETECTED_APP_INVITE_VIA_CHANNEL);
                                    sb3.append(mapM71.get("af_channel"));
                                    AFLogger.afDebugLog(sb3.toString());
                                } else {
                                    AFLogger.afDebugLog(String.format(Constants.LOG_CROSS_PROMOTION_APP_INSTALLED_FROM_CROSS_PROMOTION, mapM71.get(Constants.URL_SITE_ID)));
                                }
                            }
                            if (mapM71.containsKey(Constants.URL_SITE_ID)) {
                                StringBuilder sb4 = new StringBuilder(Constants.LOG_INVITE_DETECTED_APP_INVITE_VIA_CHANNEL);
                                sb4.append(mapM71.get("af_channel"));
                                AFLogger.afDebugLog(sb4.toString());
                            }
                            mapM71.put("is_first_launch", Boolean.toString(false));
                            String string = new JSONObject(mapM71).toString();
                            if (string != null) {
                                AppsFlyerLib.m90(context, "attributionId", string);
                            } else {
                                AppsFlyerLib.m90(context, "attributionId", strM68);
                            }
                            StringBuilder sb5 = new StringBuilder("iscache=");
                            sb5.append(str);
                            sb5.append(" caching conversion data");
                            AFLogger.afDebugLog(sb5.toString());
                            if (AppsFlyerLib.f82 != null && this.f120.intValue() <= 1) {
                                try {
                                    mapM71 = AppsFlyerLib.m70(context);
                                } catch (l e) {
                                    AFLogger.afErrorLog("Exception while trying to fetch attribution data. ", e);
                                }
                                mo121(mapM71);
                            }
                        }
                    } else {
                        if (AppsFlyerLib.f82 != null) {
                            mo120("Error connection to server: ".concat(String.valueOf(responseCode)), responseCode);
                        }
                        StringBuilder sb6 = new StringBuilder("AttributionIdFetcher response code: ");
                        sb6.append(responseCode);
                        sb6.append("  url: ");
                        sb6.append((Object) sb);
                        j.AnonymousClass2.m182(sb6.toString());
                    }
                    this.f120.decrementAndGet();
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    httpURLConnection2 = httpURLConnection;
                    if (AppsFlyerLib.f82 != null) {
                        mo120(th.getMessage(), 0);
                    }
                    AFLogger.afErrorLog(th.getMessage(), th);
                    this.f120.decrementAndGet();
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    this.f118.shutdown();
                }
                this.f118.shutdown();
            } catch (Throwable th3) {
                th = th3;
                httpURLConnection = httpURLConnection2;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.Reader] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.Reader] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    @NonNull
    /* renamed from: ˋ, reason: contains not printable characters */
    static String m68(HttpURLConnection httpURLConnection) throws Throwable {
        String string;
        InputStreamReader inputStreamReader;
        ?? sb = new StringBuilder();
        ?? line = 0;
        line = 0;
        line = 0;
        line = 0;
        try {
            try {
                try {
                    InputStream errorStream = httpURLConnection.getErrorStream();
                    if (errorStream == null) {
                        errorStream = httpURLConnection.getInputStream();
                    }
                    inputStreamReader = new InputStreamReader(errorStream);
                    try {
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        while (true) {
                            try {
                                line = bufferedReader.readLine();
                                if (line == 0) {
                                    break;
                                }
                                sb.append(line);
                                sb.append('\n');
                            } catch (Throwable th) {
                                th = th;
                                line = bufferedReader;
                                StringBuilder sb2 = new StringBuilder("Could not read connection response from: ");
                                sb2.append(httpURLConnection.getURL().toString());
                                AFLogger.afErrorLog(sb2.toString(), th);
                                if (line != 0) {
                                    line.close();
                                }
                                if (inputStreamReader != null) {
                                    inputStreamReader.close();
                                }
                                string = sb.toString();
                                new JSONObject(string);
                                return string;
                            }
                        }
                        bufferedReader.close();
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Throwable th4) {
                th = th4;
                inputStreamReader = null;
            }
            inputStreamReader.close();
        } catch (Throwable unused) {
        }
        string = sb.toString();
        try {
            new JSONObject(string);
            return string;
        } catch (JSONException unused2) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("string_response", string);
                return jSONObject.toString();
            } catch (JSONException unused3) {
                return new JSONObject().toString();
            }
        }
    }

    class d implements Runnable {

        /* renamed from: ˋ, reason: contains not printable characters */
        private WeakReference<Context> f134;

        public d(Context context) {
            this.f134 = null;
            this.f134 = new WeakReference<>(context);
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (AppsFlyerLib.this.f89) {
                return;
            }
            AppsFlyerLib.this.f110 = System.currentTimeMillis();
            if (this.f134 == null) {
                return;
            }
            AppsFlyerLib.this.f89 = true;
            try {
                try {
                    String strM96 = AppsFlyerLib.m96(AppsFlyerProperties.AF_KEY);
                    synchronized (this.f134) {
                        for (RequestCacheData requestCacheData : CacheManager.getInstance().getCachedRequests(this.f134.get())) {
                            StringBuilder sb = new StringBuilder("resending request: ");
                            sb.append(requestCacheData.getRequestURL());
                            AFLogger.afInfoLog(sb.toString());
                            try {
                                long jCurrentTimeMillis = System.currentTimeMillis();
                                long j = Long.parseLong(requestCacheData.getCacheKey(), 10);
                                AppsFlyerLib appsFlyerLib = AppsFlyerLib.this;
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append(requestCacheData.getRequestURL());
                                sb2.append("&isCachedRequest=true&timeincache=");
                                sb2.append(Long.toString((jCurrentTimeMillis - j) / 1000));
                                AppsFlyerLib.m91(appsFlyerLib, sb2.toString(), requestCacheData.getPostData(), strM96, this.f134, requestCacheData.getCacheKey(), false);
                            } catch (Exception e) {
                                AFLogger.afErrorLog("Failed to resend cached request", e);
                            }
                        }
                    }
                } catch (Exception e2) {
                    AFLogger.afErrorLog("failed to check cache. ", e2);
                }
                AppsFlyerLib.this.f89 = false;
                AppsFlyerLib.this.f92.shutdown();
                AppsFlyerLib.m87(AppsFlyerLib.this);
            } catch (Throwable th) {
                AppsFlyerLib.this.f89 = false;
                throw th;
            }
        }
    }

    /* renamed from: ʼ, reason: contains not printable characters */
    private static float m54(Context context) {
        try {
            Intent intentRegisterReceiver = context.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = intentRegisterReceiver.getIntExtra("level", -1);
            int intExtra2 = intentRegisterReceiver.getIntExtra("scale", -1);
            if (intExtra == -1 || intExtra2 == -1) {
                return 50.0f;
            }
            return (intExtra / intExtra2) * 100.0f;
        } catch (Throwable th) {
            AFLogger.afErrorLog(th.getMessage(), th);
            return 1.0f;
        }
    }

    /* renamed from: ᐝ, reason: contains not printable characters */
    private static boolean m106(Context context) {
        if (context != null) {
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                    for (Network network : connectivityManager.getAllNetworks()) {
                        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                        if (networkCapabilities.hasTransport(4) && !networkCapabilities.hasCapability(15)) {
                            return true;
                        }
                    }
                    return false;
                } catch (Exception e2) {
                    AFLogger.afErrorLog("Failed collecting ivc data", e2);
                }
            } else if (Build.VERSION.SDK_INT >= 16) {
                ArrayList arrayList = new ArrayList();
                try {
                    Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
                    while (it.hasNext()) {
                        NetworkInterface networkInterface = (NetworkInterface) it.next();
                        if (networkInterface.isUp()) {
                            arrayList.add(networkInterface.getName());
                        }
                    }
                    return arrayList.contains("tun0");
                } catch (Exception e3) {
                    AFLogger.afErrorLog("Failed collecting ivc data", e3);
                }
            }
        }
        return false;
    }

    public void setLogLevel(AFLogger.LogLevel logLevel) {
        AppsFlyerProperties.getInstance().set("logLevel", logLevel.getLevel());
    }

    public void setHostName(String str) {
        AppsFlyerProperties.getInstance().set("custom_host", str);
    }

    public String getHost() {
        String string = AppsFlyerProperties.getInstance().getString("custom_host");
        return string != null ? string : ServerParameters.DEFAULT_HOST;
    }

    public void setMinTimeBetweenSessions(int i) {
        this.f101 = TimeUnit.SECONDS.toMillis(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ˏ, reason: contains not printable characters */
    public static void m90(Context context, String str, String str2) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("appsflyer-data", 0).edit();
        editorEdit.putString(str, str2);
        if (Build.VERSION.SDK_INT >= 9) {
            editorEdit.apply();
        } else {
            editorEdit.commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ˏ, reason: contains not printable characters */
    public static void m89(Context context, String str, int i) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("appsflyer-data", 0).edit();
        editorEdit.putInt(str, i);
        if (Build.VERSION.SDK_INT >= 9) {
            editorEdit.apply();
        } else {
            editorEdit.commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ˋ, reason: contains not printable characters */
    public static void m73(Context context, String str, long j) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("appsflyer-data", 0).edit();
        editorEdit.putLong(str, j);
        if (Build.VERSION.SDK_INT >= 9) {
            editorEdit.apply();
        } else {
            editorEdit.commit();
        }
    }
}
