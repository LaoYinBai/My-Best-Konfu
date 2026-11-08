package com.bytedance.applog;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.HandlerThread;
import android.text.TextUtils;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bytedance.a.a;
import com.bytedance.a.al;
import com.bytedance.a.aw;
import com.bytedance.a.az;
import com.bytedance.a.b;
import com.bytedance.a.be;
import com.bytedance.a.bk;
import com.bytedance.a.bo;
import com.bytedance.a.ch;
import com.bytedance.a.cj;
import com.bytedance.a.co;
import com.bytedance.a.ct;
import com.bytedance.a.cu;
import com.bytedance.a.cw;
import com.bytedance.a.de;
import com.bytedance.a.dg;
import com.bytedance.a.g;
import com.bytedance.a.k;
import com.bytedance.a.m;
import com.bytedance.a.n;
import com.bytedance.a.o;
import com.bytedance.a.u;
import com.bytedance.applog.alink.IALinkListener;
import com.bytedance.applog.network.INetworkClient;
import com.bytedance.applog.profile.UserProfileCallback;
import com.bytedance.b.a.c.h;
import com.bytedance.common.utility.Logger;
import com.bytedance.sdk.account.platform.base.AuthorizeMonitorUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class AppLog {
    public static final int BUSINESS_EVENT = 1;
    public static final int DEFAULT_EVENT = 0;
    public static final String EVENT_V1_CATEGORY = "event_v1";

    /* renamed from: a, reason: collision with root package name */
    @SuppressLint({"StaticFieldLeak"})
    public static volatile az f3553a = null;

    /* renamed from: b, reason: collision with root package name */
    @SuppressLint({"StaticFieldLeak"})
    public static volatile be f3554b = null;

    /* renamed from: c, reason: collision with root package name */
    public static boolean f3555c = true;
    public static volatile boolean d = false;
    public static volatile ch e;
    public static IHeaderCustomTimelyCallback f;
    public static Application g;
    public static volatile boolean h;
    public static dg i;
    public static Integer j;
    public static volatile g sEventFilterFromClient;
    public static int sLaunchFrom;

    public AppLog() {
        de.a("U SHALL NOT PASS!", null);
    }

    public static void activateALink(Uri uri) {
        dg dgVar = i;
        if (dgVar != null) {
            dgVar.a(uri);
        }
    }

    public static void addDataObserver(IDataObserver iDataObserver) {
        n.a().a(iDataObserver);
    }

    public static void addEventObserver(IEventObserver iEventObserver) {
        aw.a().a(iEventObserver);
    }

    public static String addNetCommonParams(Context context, String str, boolean z, Level level) {
        return bk.a(context, f3554b != null ? f3554b.a() : null, str, z, level);
    }

    public static void addSessionHook(ISessionObserver iSessionObserver) {
        cu.a().a(iSessionObserver);
    }

    public static void flush() {
        dg dgVar = i;
        if (dgVar != null) {
            dgVar.a((String[]) null, true);
        }
    }

    @Nullable
    public static <T> T getAbConfig(String str, T t) throws JSONException {
        if (f3554b == null) {
            return null;
        }
        be beVar = f3554b;
        JSONObject jSONObjectOptJSONObject = beVar.d.d().optJSONObject(str);
        if (jSONObjectOptJSONObject != null) {
            String strOptString = jSONObjectOptJSONObject.optString("vid");
            T t2 = (T) jSONObjectOptJSONObject.opt("val");
            beVar.g(strOptString);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ab_sdk_version", strOptString);
                onEventV3("abtest_exposure", jSONObject);
            } catch (JSONException e2) {
                de.a("", e2);
            }
            if (t2 == null) {
                t2 = null;
            }
            if (t2 != null) {
                return t2;
            }
        }
        return t;
    }

    public static String getAbSdkVersion() {
        if (f3554b == null) {
            return null;
        }
        be beVar = f3554b;
        if (beVar.f3396b) {
            return beVar.e.optString("ab_sdk_version", "");
        }
        az azVar = beVar.d;
        return azVar != null ? azVar.b() : "";
    }

    public static String getAid() {
        return f3554b != null ? f3554b.e.optString("aid", "") : "";
    }

    public static JSONObject getAllAbTestConfigs() {
        dg dgVar = i;
        return dgVar == null ? new JSONObject() : dgVar.e.d();
    }

    public static k getAppContext() {
        return null;
    }

    public static String getClientUdid() {
        return f3554b != null ? f3554b.e.optString("clientudid", "") : "";
    }

    public static Context getContext() {
        return g;
    }

    public static String getDid() {
        return f3554b != null ? f3554b.f() : "";
    }

    public static boolean getEncryptAndCompress() {
        return f3555c;
    }

    @Nullable
    public static JSONObject getHeader() {
        if (f3554b != null) {
            return f3554b.a();
        }
        de.a("U SHALL NOT PASS!", new RuntimeException("init come first"));
        return null;
    }

    public static IHeaderCustomTimelyCallback getHeaderCustomCallback() {
        return f;
    }

    public static <T> T getHeaderValue(String str, T t, Class<T> cls) {
        if (f3554b != null) {
            return (T) bk.a(f3554b.e, str, t, cls);
        }
        return null;
    }

    public static int getHttpMonitorPort() {
        Integer num = j;
        if (num != null) {
            return num.intValue();
        }
        if (f3553a != null) {
            return f3553a.e.getInt("http_monitor_port", 0);
        }
        return 0;
    }

    public static String getIid() {
        return f3554b != null ? f3554b.g() : "";
    }

    public static InitConfig getInitConfig() {
        if (f3553a != null) {
            return f3553a.f3386b;
        }
        return null;
    }

    public static INetworkClient getNetClient() {
        return f3553a.f3386b.getNetworkClient();
    }

    public static String getOpenUdid() {
        return f3554b != null ? f3554b.e.optString("openudid", "") : "";
    }

    public static Map<String, String> getRequestHeader() {
        if (f3553a == null) {
            return Collections.emptyMap();
        }
        String string = f3553a.e.getString("device_token", "");
        HashMap map = new HashMap();
        if (string == null) {
            string = "";
        }
        map.put("x-tt-dt", string);
        return map;
    }

    public static String getSdkVersion() {
        return "6.3.0";
    }

    public static String getSessionId() {
        b bVar;
        dg dgVar = dg.f3498a;
        if (dgVar == null || (bVar = dgVar.n) == null) {
            return null;
        }
        return bVar.b();
    }

    public static String getSsid() {
        return f3554b != null ? f3554b.h() : "";
    }

    public static void getSsidGroup(Map<String, String> map) {
        String did = getDid();
        if (!TextUtils.isEmpty(did)) {
            map.put(h.x, did);
        }
        String iid = getIid();
        if (!TextUtils.isEmpty(iid)) {
            map.put("install_id", iid);
        }
        String openUdid = getOpenUdid();
        if (!TextUtils.isEmpty(openUdid)) {
            map.put("openudid", openUdid);
        }
        String clientUdid = getClientUdid();
        if (TextUtils.isEmpty(clientUdid)) {
            return;
        }
        map.put("clientudid", clientUdid);
    }

    public static int getSuccRate() {
        if (f3553a != null) {
            return f3553a.e.getInt("bav_monitor_rate", 0);
        }
        return 0;
    }

    public static String getUdid() {
        return f3554b != null ? f3554b.e.optString("udid", "") : "";
    }

    public static String getUserID() {
        return String.valueOf(b.f3388a);
    }

    public static String getUserUniqueID() {
        return f3554b != null ? f3554b.e() : "";
    }

    public static boolean hasStarted() {
        return h;
    }

    public static void init(@NonNull Context context, @NonNull InitConfig initConfig) {
        synchronized (AppLog.class) {
            if (g == null) {
                de.a(context, initConfig.getLogger(), initConfig.isLogEnable());
                de.a("Inited Begin", null);
                g = (Application) context.getApplicationContext();
                f3553a = new az(g, initConfig);
                f3554b = new be(g, f3553a);
                i = new dg(g, f3553a, f3554b);
                e = new ch(initConfig.getPicker());
                if (initConfig.a()) {
                    g.registerActivityLifecycleCallbacks(e);
                }
                try {
                    Method declaredMethod = Class.forName("com.bytedance.applog.metasec.AppLogSecHelper").getDeclaredMethod("init", Context.class);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(null, context);
                } catch (Exception unused) {
                }
                sLaunchFrom = 1;
                h = initConfig.autoStart();
                de.a("Inited End", null);
            }
        }
    }

    public static void init(@NonNull Context context, @NonNull InitConfig initConfig, Activity activity) {
        init(context, initConfig);
        if (e == null || activity == null) {
            return;
        }
        e.onActivityCreated(activity, null);
        e.onActivityResumed(activity);
    }

    public static boolean isH5BridgeEnable() {
        return getInitConfig() != null && getInitConfig().isH5BridgeEnable();
    }

    public static boolean isH5CollectEnable() {
        return getInitConfig() != null && getInitConfig().isH5CollectEnable();
    }

    public static boolean isNewUser() {
        if (f3554b != null) {
            return f3554b.j;
        }
        return false;
    }

    public static boolean isNewUserMode(Context context) {
        return bo.a(context);
    }

    public static boolean isNewUserModeAvailable() {
        if (!hasStarted()) {
            return false;
        }
        try {
            if (!bo.a()) {
                return false;
            }
            Class.forName("com.bytedance.applog.manager.newuser.DeviceParamsProvider");
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isPrivacyMode() {
        return d;
    }

    public static boolean manualActivate() {
        dg dgVar = i;
        if (dgVar != null) {
            return dgVar.a(false);
        }
        return false;
    }

    public static void onActivityPause() {
        if (e != null) {
            e.onActivityPaused(null);
        }
    }

    public static void onActivityResumed(String str, int i2) {
        if (e != null) {
            e.a(str, i2);
        }
    }

    public static void onEvent(String str) {
        onEvent("event_v1", str, null, 0L, 0L, null);
    }

    public static void onEvent(String str, String str2) {
        onEvent("event_v1", str, str2, 0L, 0L, null);
    }

    public static void onEvent(String str, String str2, String str3, long j2, long j3) {
        onEvent(str, str2, str3, j2, j3, null);
    }

    public static void onEvent(@NonNull String str, @NonNull String str2, String str3, long j2, long j3, JSONObject jSONObject) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            de.a("category or tag is empty", null);
        } else {
            dg.a(new cj(str, str2, str3, j2, j3, jSONObject != null ? jSONObject.toString() : null));
        }
    }

    public static void onEventV3(@NonNull String str) {
        onEventV3(str, (JSONObject) null);
    }

    public static void onEventV3(@NonNull String str, @Nullable Bundle bundle) {
        onEventV3(str, bundle, 0);
    }

    public static void onEventV3(@NonNull String str, @Nullable Bundle bundle, int i2) {
        JSONObject jSONObject = null;
        if (bundle != null) {
            try {
                if (!bundle.isEmpty()) {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        for (String str2 : bundle.keySet()) {
                            jSONObject2.put(str2, bundle.get(str2));
                        }
                        jSONObject = jSONObject2;
                    } catch (Throwable th) {
                        th = th;
                        jSONObject = jSONObject2;
                        de.a("U SHALL NOT PASS!", th);
                        onEventV3(str, jSONObject, i2);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        onEventV3(str, jSONObject, i2);
    }

    public static void onEventV3(@NonNull String str, @Nullable JSONObject jSONObject) {
        onEventV3(str, jSONObject, 0);
    }

    public static void onInternalEventV3(@NonNull String str, @Nullable Bundle bundle, @Nullable String str2, @Nullable String str3, @NonNull String str4) {
        JSONObject jSONObject = null;
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            de.a("both second appid and second app name is empty, return", null);
            return;
        }
        String str5 = "second_app_" + str;
        if (bundle != null) {
            try {
                if (!bundle.isEmpty()) {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        for (String str6 : bundle.keySet()) {
                            jSONObject2.put(str6, bundle.get(str6));
                        }
                        jSONObject2.put(AuthorizeMonitorUtil.Param.PARAMS_FOR_SPECIAL, "second_app");
                        jSONObject2.put("second_appid", str2);
                        jSONObject2.put("second_appname", str3);
                        jSONObject2.put("product_type", str4);
                        jSONObject = jSONObject2;
                    } catch (Throwable th) {
                        th = th;
                        jSONObject = jSONObject2;
                        de.a("U SHALL NOT PASS!", th);
                        onEventV3(str5, jSONObject);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        onEventV3(str5, jSONObject);
    }

    public static void onMiscEvent(@NonNull String str, @Nullable JSONObject jSONObject) {
        if (TextUtils.isEmpty(str) || jSONObject == null || jSONObject.length() <= 0) {
            de.a("call onEventData with invalid params, return", null);
            return;
        }
        try {
            dg.a(new co(str, jSONObject));
        } catch (Exception e2) {
            de.a("call onEventData get exception: ", e2);
        }
    }

    public static void onPause(Context context) {
        if (context instanceof Activity) {
            onActivityPause();
        }
    }

    public static void onResume(Context context) {
        if (context instanceof Activity) {
            onActivityResumed(context.getClass().getName(), context.hashCode());
        }
    }

    public static void profileAppend(JSONObject jSONObject) {
        if (i == null || jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        try {
            if (!m.a(jSONObject, (Class<?>[]) new Class[]{String.class, Integer.class}, (Class<?>[]) new Class[]{String.class})) {
                de.a("only support String、Int、String Array！", new Exception());
                return;
            }
        } catch (JSONException e2) {
            de.a("", e2);
        }
        i.e(jSONObject);
    }

    public static void profileIncrement(JSONObject jSONObject) {
        if (i == null || jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        try {
            if (!m.a(jSONObject, (Class<?>[]) new Class[]{Integer.class}, (Class<?>[]) null)) {
                de.a("only support Int", new Exception());
                return;
            }
        } catch (JSONException e2) {
            de.a("", e2);
        }
        i.d(jSONObject);
    }

    public static void profileSet(JSONObject jSONObject) {
        if (i == null || jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        i.a(jSONObject);
    }

    public static void profileSetOnce(JSONObject jSONObject) {
        if (i == null || jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        i.b(jSONObject);
    }

    public static void profileUnset(String str) throws JSONException {
        if (i == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(str, "");
        } catch (JSONException e2) {
            de.a("", e2);
        }
        i.c(jSONObject);
    }

    public static void putCommonParams(Context context, Map<String, String> map, boolean z, Level level) {
        bk.a(context, f3554b != null ? f3554b.a() : null, z, map, level);
    }

    public static void registerHeaderCustomCallback(IHeaderCustomTimelyCallback iHeaderCustomTimelyCallback) {
        f = iHeaderCustomTimelyCallback;
    }

    public static void removeAllDataObserver() {
        n.a().f3533b.clear();
    }

    public static void removeDataObserver(IDataObserver iDataObserver) {
        n.a().b(iDataObserver);
    }

    public static void removeEventObserver(IEventObserver iEventObserver) {
        aw.a().b(iEventObserver);
    }

    public static void removeHeaderInfo(String str) {
        if (f3554b == null || TextUtils.isEmpty(str)) {
            return;
        }
        f3554b.c(str);
    }

    public static void removeOaidObserver(@Nullable IOaidObserver iOaidObserver) {
        o.b(iOaidObserver);
    }

    public static void removeSessionHook(ISessionObserver iSessionObserver) {
        cu.a().b(iSessionObserver);
    }

    public static boolean reportPhoneDetailInfo() {
        return f3554b.j();
    }

    public static void setALinkListener(IALinkListener iALinkListener) {
        u.g.a(iALinkListener);
    }

    public static void setAccount(Account account) {
        if (f3554b != null) {
            de.a("setAccount " + account, null);
            f3554b.a(account);
        }
    }

    public static void setAppContext(k kVar) {
    }

    public static void setAppLanguageAndRegion(String str, String str2) {
        boolean z;
        dg dgVar = i;
        if (dgVar != null) {
            be beVar = dgVar.i;
            boolean z2 = false;
            if (beVar.a("app_language", (Object) str)) {
                a.a(beVar.d.e, "app_language", str);
                z = true;
            } else {
                z = false;
            }
            be beVar2 = dgVar.i;
            if (beVar2.a("app_region", (Object) str2)) {
                a.a(beVar2.d.e, "app_region", str2);
                z2 = true;
            }
            if (z || z2) {
                dgVar.a(dgVar.k);
                dgVar.a(dgVar.f);
            }
        }
    }

    public static void setAppTrack(JSONObject jSONObject) {
        if (jSONObject == null || f3554b == null) {
            return;
        }
        be beVar = f3554b;
        if (beVar.a("app_track", jSONObject)) {
            az azVar = beVar.d;
            a.a(azVar.f3387c, "app_track", jSONObject.toString());
        }
    }

    public static void setClipboardEnabled(boolean z) {
        u.g.a(z);
    }

    public static void setEncryptAndCompress(boolean z) {
        f3555c = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void setEventFilterByClient(java.util.List<java.lang.String> r4, boolean r5) {
        /*
            r0 = 0
            if (r4 == 0) goto L3c
            boolean r1 = r4.isEmpty()
            if (r1 != 0) goto L3c
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            java.util.Iterator r4 = r4.iterator()
        L12:
            boolean r2 = r4.hasNext()
            if (r2 == 0) goto L28
            java.lang.Object r2 = r4.next()
            java.lang.String r2 = (java.lang.String) r2
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L12
            r1.add(r2)
            goto L12
        L28:
            boolean r4 = r1.isEmpty()
            if (r4 != 0) goto L3c
            if (r5 == 0) goto L36
            com.bytedance.a.q r4 = new com.bytedance.a.q
            r4.<init>(r1, r0)
            goto L3d
        L36:
            com.bytedance.a.l r4 = new com.bytedance.a.l
            r4.<init>(r1, r0)
            goto L3d
        L3c:
            r4 = r0
        L3d:
            com.bytedance.applog.AppLog.sEventFilterFromClient = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.AppLog.setEventFilterByClient(java.util.List, boolean):void");
    }

    public static void setEventSenderEnable(boolean z, Context context) {
        dg dgVar = i;
        if (dgVar != null) {
            dgVar.a(z, context);
        }
    }

    public static void setExternalAbVersion(String str) {
        if (f3554b != null) {
            f3554b.b(str);
        }
    }

    public static void setExtraParams(IExtraParams iExtraParams) {
        bk.f3410a = iExtraParams;
    }

    public static void setForbidReportPhoneDetailInfo(boolean z) {
        if (f3554b == null) {
            throw new IllegalStateException("Applog还未init()");
        }
        be beVar = f3554b;
        beVar.k = z;
        if (beVar.j()) {
            return;
        }
        beVar.a("sim_serial_number", (Object) null);
    }

    public static void setGoogleAid(String str) {
        if (f3554b != null) {
            be beVar = f3554b;
            if (beVar.a("google_aid", (Object) str)) {
                a.a(beVar.d.e, "google_aid", str);
            }
        }
    }

    public static void setHeaderInfo(String str, Object obj) {
        if (f3554b == null || TextUtils.isEmpty(str)) {
            return;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put(str, obj);
        f3554b.a(map);
    }

    public static void setHeaderInfo(HashMap<String, Object> map) {
        if (f3554b != null) {
            f3554b.a(map);
        }
    }

    public static void setHttpMonitorPort(int i2) {
        j = Integer.valueOf(i2);
    }

    public static void setNewUserMode(Context context, boolean z) {
        if (context == null || !bo.a()) {
            return;
        }
        com.bytedance.a.h hVarA = com.bytedance.a.h.a(context);
        hVarA.f3527b = z;
        if (hVarA.b()) {
            String strC = "";
            try {
                strC = hVarA.c();
            } catch (JSONException e2) {
                de.a("", e2);
            }
            Context context2 = hVarA.e.get();
            String str = "newUserModeUtil:" + strC;
            try {
                AccountManager accountManager = AccountManager.get(context2);
                Account accountA = m.a(context2);
                if (accountManager == null || accountA == null) {
                    Logger.d("NewUserModeUtil", "OnEncryptToAccount: failed");
                } else {
                    accountManager.setUserData(accountA, "new_user_mode_account", str);
                }
            } catch (Throwable th) {
                de.a("", th);
            }
        }
    }

    @AnyThread
    public static void setOaidObserver(@Nullable IOaidObserver iOaidObserver) {
        o.a(iOaidObserver);
    }

    public static void setPrivacyMode(boolean z) {
        d = z;
    }

    public static void setRangersEventVerifyEnable(boolean z, String str) {
        dg dgVar = i;
        if (dgVar != null) {
            dgVar.j.removeMessages(15);
            dgVar.j.obtainMessage(15, new Object[]{Boolean.valueOf(z), str}).sendToTarget();
        }
    }

    public static void setTouchPoint(String str) {
        setHeaderInfo("touch_point", str);
    }

    public static void setTracerData(JSONObject jSONObject) {
        if (f3554b != null) {
            f3554b.a("tracer_data", jSONObject);
        }
    }

    public static void setUriRuntime(UriConfig uriConfig) {
        if (i != null) {
            StringBuilder sbA = a.a("setUriRuntime ");
            sbA.append(uriConfig.getRegisterUri());
            de.a(sbA.toString(), null);
            dg dgVar = i;
            dgVar.o = uriConfig;
            dgVar.a(dgVar.k);
            if (dgVar.e.f3386b.isAutoActive()) {
                dgVar.a(true);
            }
        }
    }

    public static void setUserAgent(String str) {
        if (f3554b != null) {
            be beVar = f3554b;
            if (beVar.a(com.alipay.sdk.cons.b.f2578b, (Object) str)) {
                a.a(beVar.d.e, com.alipay.sdk.cons.b.f2578b, str);
            }
        }
    }

    public static void setUserID(long j2) {
        b.f3388a = j2;
    }

    public static void setUserUniqueID(String str) {
        dg dgVar = i;
        if (dgVar != null) {
            dgVar.a(str);
        }
    }

    public static void start() {
        if (h) {
            return;
        }
        h = true;
        dg dgVar = i;
        if (dgVar.r) {
            return;
        }
        dgVar.r = true;
        dgVar.p.sendEmptyMessage(1);
    }

    public static void startSimulator(String str) throws NoSuchMethodException, SecurityException {
        dg dgVar = i;
        if (dgVar != null) {
            cw cwVar = dgVar.s;
            if (cwVar != null) {
                cwVar.e = true;
            }
            try {
                Constructor<?> constructor = Class.forName("com.bytedance.applog.picker.DomSender").getConstructor(dg.class, String.class);
                new HandlerThread("bd_tracker_d").start();
                dgVar.s = (cw) constructor.newInstance(dg.f3498a, str);
                dgVar.j.sendMessage(dgVar.j.obtainMessage(9, dgVar.s));
            } catch (Exception e2) {
                de.a("U SHALL NOT PASS!", e2);
            }
        }
    }

    public static void userProfileSetOnce(JSONObject jSONObject, UserProfileCallback userProfileCallback) {
        dg dgVar = i;
        if (dgVar == null || dgVar.j == null) {
            return;
        }
        al.a(dgVar, 0, jSONObject, userProfileCallback, dgVar.j, false);
    }

    public static void userProfileSync(JSONObject jSONObject, UserProfileCallback userProfileCallback) {
        dg dgVar = i;
        if (dgVar == null || dgVar.j == null) {
            return;
        }
        al.a(dgVar, 1, jSONObject, userProfileCallback, dgVar.j, false);
    }

    public static void onEventV3(@NonNull String str, @Nullable JSONObject jSONObject, int i2) {
        if (TextUtils.isEmpty(str)) {
            de.a("event name is empty", null);
        } else {
            dg.a(new ct(str, false, jSONObject != null ? jSONObject.toString() : null, i2));
        }
    }

    public static void onInternalEventV3(@NonNull String str, @Nullable JSONObject jSONObject, @Nullable String str2, @Nullable String str3, @NonNull String str4) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            de.a("both second appid and second app name is empty, return", null);
            return;
        }
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String str5 = "second_app_" + str;
        try {
            jSONObject.put(AuthorizeMonitorUtil.Param.PARAMS_FOR_SPECIAL, "second_app");
            jSONObject.put("second_appid", str2);
            jSONObject.put("second_appname", str3);
            jSONObject.put("product_type", str4);
        } catch (Throwable th) {
            de.a("U SHALL NOT PASS!", th);
        }
        onEventV3(str5, jSONObject);
    }
}
