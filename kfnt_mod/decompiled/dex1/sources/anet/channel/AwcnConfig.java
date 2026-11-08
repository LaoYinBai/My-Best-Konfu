package anet.channel;

import android.text.TextUtils;
import anet.channel.strategy.ConnProtocol;
import anet.channel.strategy.StrategyTemplate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AwcnConfig {

    /* renamed from: a, reason: collision with root package name */
    private static volatile boolean f360a = false;

    /* renamed from: b, reason: collision with root package name */
    private static volatile boolean f361b = true;

    /* renamed from: c, reason: collision with root package name */
    private static volatile boolean f362c = true;
    private static volatile boolean d = true;
    private static volatile boolean e = false;
    private static volatile boolean f = false;
    private static volatile long g = 43200000;
    private static volatile boolean h = true;
    private static volatile boolean i = true;
    private static boolean j = true;
    private static boolean k = false;

    public static boolean isAccsSessionCreateForbiddenInBg() {
        return f360a;
    }

    public static void setAccsSessionCreateForbiddenInBg(boolean z) {
        f360a = z;
    }

    public static void setHttpsSniEnable(boolean z) {
        f361b = z;
    }

    public static boolean isHttpsSniEnable() {
        return f361b;
    }

    public static boolean isHorseRaceEnable() {
        return f362c;
    }

    public static void setHorseRaceEnable(boolean z) {
        f362c = z;
    }

    public static boolean isTnetHeaderCacheEnable() {
        return d;
    }

    public static void setTnetHeaderCacheEnable(boolean z) {
        d = z;
    }

    public static void setQuicEnable(boolean z) {
        e = z;
    }

    public static boolean isQuicEnable() {
        return e;
    }

    public static void setIdleSessionCloseEnable(boolean z) {
        f = z;
    }

    public static boolean isIdleSessionCloseEnable() {
        return f;
    }

    public static void registerPresetSessions(String str) throws JSONException {
        if (GlobalAppRuntimeInfo.isTargetProcess() && !TextUtils.isEmpty(str)) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                int length = jSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    String string = jSONObject.getString(com.alipay.sdk.cons.c.f);
                    if (!anet.channel.strategy.utils.c.c(string)) {
                        return;
                    }
                    StrategyTemplate.getInstance().registerConnProtocol(string, ConnProtocol.valueOf(jSONObject.getString("protocol"), jSONObject.getString("rtt"), jSONObject.getString("publicKey")));
                    if (jSONObject.getBoolean("isKeepAlive")) {
                        SessionCenter.getInstance().registerSessionInfo(SessionInfo.create(string, true, false, null, null, null));
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public static boolean isIpv6Enable() {
        return h;
    }

    public static void setIpv6Enable(boolean z) {
        h = z;
    }

    public static boolean isIpv6BlackListEnable() {
        return i;
    }

    public static void setIpv6BlackListEnable(boolean z) {
        i = z;
    }

    public static long getIpv6BlackListTtl() {
        return g;
    }

    public static void setIpv6BlackListTtl(long j2) {
        g = j2;
    }

    public static boolean isAppLifeCycleListenerEnable() {
        return j;
    }

    public static void setAppLifeCycleListenerEnable(boolean z) {
        j = z;
    }

    public static boolean isAsyncLoadStrategyEnable() {
        return k;
    }

    public static void setAsyncLoadStrategyEnable(boolean z) {
        k = z;
    }
}
