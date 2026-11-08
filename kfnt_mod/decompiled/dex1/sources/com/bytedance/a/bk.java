package com.bytedance.a;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.bytedance.applog.AppLog;
import com.bytedance.applog.IExtraParams;
import com.bytedance.applog.Level;
import com.bytedance.common.utility.NetworkUtils;
import com.bytedance.common.utility.UIUtils;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bk {

    /* renamed from: a, reason: collision with root package name */
    public static volatile IExtraParams f3410a;

    /* renamed from: b, reason: collision with root package name */
    public static final a[] f3411b = {new a("aid", "aid", String.class), new a("google_aid", "google_aid", String.class), new a(DispatchConstants.CARRIER, DispatchConstants.CARRIER, String.class), new a("mcc_mnc", "mcc_mnc", String.class), new a("sim_region", "sim_region", String.class), new a(com.bytedance.b.a.c.h.x, com.bytedance.b.a.c.h.x, String.class), new a("bd_did", "bd_did", String.class), new a("install_id", "iid", String.class), new a("clientudid", "clientudid", String.class), new a(TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY, TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY, String.class), new a("app_version", "version_name", String.class), new a("version_code", "version_code", Integer.class), new a("manifest_version_code", "manifest_version_code", Integer.class), new a("update_version_code", "update_version_code", Integer.class), new a("sdk_version_code", "sdk_version_code", Integer.class)};

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public final String f3412a;

        /* renamed from: b, reason: collision with root package name */
        public final String f3413b;

        /* renamed from: c, reason: collision with root package name */
        public final Class f3414c;

        public a(String str, String str2, Class cls) {
            this.f3412a = str;
            this.f3413b = str2;
            this.f3414c = cls;
        }
    }

    public static String a(Context context, JSONObject jSONObject, String str, boolean z, Level level) {
        if (context == null || TextUtils.isEmpty(str)) {
            return str;
        }
        Uri uri = Uri.parse(str);
        Set<String> queryParameterNames = uri.getQueryParameterNames();
        Uri.Builder builderBuildUpon = uri.buildUpon();
        HashMap map = new HashMap();
        a(context, jSONObject, z, map, level);
        for (Map.Entry entry : map.entrySet()) {
            String str2 = (String) entry.getKey();
            String str3 = (String) entry.getValue();
            if (!queryParameterNames.contains(str2) && !TextUtils.isEmpty(str3)) {
                builderBuildUpon.appendQueryParameter(str2, (String) entry.getValue());
            }
        }
        return builderBuildUpon.build().toString();
    }

    public static void a(Context context, JSONObject jSONObject, boolean z, Map<String, String> map, Level level) {
        HashMap<String, String> extraParams;
        if (context == null || map == null || level == null) {
            return;
        }
        map.put("_rticket", String.valueOf(System.currentTimeMillis()));
        map.put("device_platform", DispatchConstants.ANDROID);
        if (z) {
            map.put("ssmix", "a");
        }
        String screenResolution = UIUtils.getScreenResolution(context);
        if (!TextUtils.isEmpty(screenResolution)) {
            map.put("resolution", screenResolution);
        }
        int dpi = UIUtils.getDpi(context);
        if (dpi > 0) {
            map.put("dpi", String.valueOf(dpi));
        }
        map.put("device_type", Build.MODEL);
        map.put("device_brand", Build.BRAND);
        map.put("language", context.getResources().getConfiguration().locale.getLanguage());
        map.put("os_api", String.valueOf(Build.VERSION.SDK_INT));
        String strSubstring = Build.VERSION.RELEASE;
        int i = 0;
        if (strSubstring != null && strSubstring.length() > 10) {
            strSubstring = strSubstring.substring(0, 10);
        }
        map.put("os_version", strSubstring);
        String networkAccessType = NetworkUtils.getNetworkAccessType(context);
        if (!TextUtils.isEmpty(networkAccessType)) {
            map.put("ac", networkAccessType);
        }
        while (true) {
            a[] aVarArr = f3411b;
            extraParams = null;
            if (i >= aVarArr.length) {
                break;
            }
            a aVar = aVarArr[i];
            Object objA = a(jSONObject, aVar.f3412a, (Object) null, (Class<Object>) aVar.f3414c);
            if (objA != null) {
                map.put(aVar.f3413b, objA.toString());
            }
            i++;
        }
        String str = (String) a(jSONObject, "tweaked_channel", "", (Class<String>) String.class);
        if (TextUtils.isEmpty(str)) {
            str = (String) a(jSONObject, "channel", "", (Class<String>) String.class);
        }
        if (!TextUtils.isEmpty(str)) {
            map.put("channel", str);
        }
        String str2 = (String) a(jSONObject, "cdid", (Object) null, (Class<Object>) String.class);
        if (!TextUtils.isEmpty(str2)) {
            map.put("cdid", str2);
        }
        boolean zA = ca.a(context);
        if (level == Level.L0) {
            if (zA) {
                String str3 = (String) a(jSONObject, "mc", (Object) null, (Class<Object>) String.class);
                String str4 = (String) a(jSONObject, "udid", (Object) null, (Class<Object>) String.class);
                if (!TextUtils.isEmpty(str3)) {
                    map.put("mac_address", str3);
                }
                if (dj.b(str4)) {
                    map.put("uuid", str4);
                }
            }
            String str5 = (String) a(jSONObject, "aliyun_uuid", (Object) null, (Class<Object>) String.class);
            if (!TextUtils.isEmpty(str5)) {
                map.put("aliyun_uuid", str5);
            }
        }
        String str6 = (String) a(jSONObject, "build_serial", (Object) null, (Class<Object>) String.class);
        if (!TextUtils.isEmpty(str6)) {
            map.put("build_serial", str6);
        }
        if (level == Level.L0) {
            String str7 = (String) a(jSONObject, "openudid", (Object) null, (Class<Object>) String.class);
            if (!TextUtils.isEmpty(str7)) {
                map.put("openudid", str7);
            }
        }
        k appContext = AppLog.getAppContext();
        if (appContext != null) {
            map.put("aid", String.valueOf(appContext.h()));
            String strD = appContext.d();
            if (TextUtils.isEmpty(strD)) {
                strD = appContext.c();
            }
            if (!TextUtils.isEmpty(strD)) {
                map.put("channel", strD);
            }
            String strA = appContext.a();
            if (!TextUtils.isEmpty(strA)) {
                map.put(TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY, strA);
            }
            map.put("version_code", String.valueOf(appContext.e()));
            String strB = appContext.b();
            if (!TextUtils.isEmpty(strB)) {
                map.put("version_name", strB);
            }
            map.put("manifest_version_code", String.valueOf(appContext.g()));
            map.put("update_version_code", String.valueOf(appContext.f()));
            String strK = appContext.k();
            if (!TextUtils.isEmpty(strK)) {
                map.put("ab_version", strK);
            }
            String strI = appContext.i();
            if (!TextUtils.isEmpty(strI)) {
                map.put("ab_client", strI);
            }
            String strL = appContext.l();
            if (!TextUtils.isEmpty(strL)) {
                map.put("ab_group", strL);
            }
            String strM = appContext.m();
            if (!TextUtils.isEmpty(strM)) {
                map.put("ab_feature", strM);
            }
            long j = appContext.j();
            if (j > 0) {
                map.put("abflag", String.valueOf(j));
            }
        }
        try {
            if (f3410a != null) {
                extraParams = f3410a.getExtraParams(level);
            }
            if (extraParams != null && !extraParams.isEmpty()) {
                for (Map.Entry<String, String> entry : extraParams.entrySet()) {
                    if (entry != null) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value) && !map.containsKey(key)) {
                            map.put(key, value);
                        }
                    }
                }
            }
        } catch (Exception e) {
            de.a("U SHALL NOT PASS!", e);
        }
        if (AppLog.isNewUserMode(context)) {
            map.putAll(h.a(context).d);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String[] a(com.bytedance.a.dg r7, org.json.JSONObject r8, boolean r9, int r10) {
        /*
            com.bytedance.applog.UriConfig r0 = r7.c()
            r1 = 0
            r2 = 1
            if (r9 == 0) goto Ld
            java.lang.String[] r9 = r0.getRealUris()
            goto L2b
        Ld:
            if (r10 == 0) goto L27
            if (r10 == r2) goto L14
            java.lang.String[] r9 = new java.lang.String[r1]
            goto L2b
        L14:
            java.lang.String r9 = r0.getBusinessUri()
            boolean r9 = android.text.TextUtils.isEmpty(r9)
            if (r9 != 0) goto L27
            java.lang.String[] r9 = new java.lang.String[r2]
            java.lang.String r10 = r0.getBusinessUri()
            r9[r1] = r10
            goto L2b
        L27:
            java.lang.String[] r9 = r0.getSendUris()
        L2b:
            int r10 = r9.length
            java.lang.String[] r0 = new java.lang.String[r10]
            boolean r3 = com.bytedance.applog.AppLog.getEncryptAndCompress()
        L32:
            if (r1 >= r10) goto L62
            r4 = r9[r1]
            r0[r1] = r4
            if (r3 == 0) goto L49
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r5 = r0[r1]
            java.lang.String r6 = "?tt_data=a"
            java.lang.String r4 = com.bytedance.a.a.a(r4, r5, r6)
            r0[r1] = r4
        L49:
            android.app.Application r4 = r7.d
            r5 = r0[r1]
            com.bytedance.applog.Level r6 = com.bytedance.applog.Level.L1
            java.lang.String r4 = a(r4, r8, r5, r2, r6)
            r0[r1] = r4
            r4 = r0[r1]
            java.lang.String[] r5 = com.bytedance.a.bf.d
            java.lang.String r4 = com.bytedance.a.bf.a(r4, r5)
            r0[r1] = r4
            int r1 = r1 + 1
            goto L32
        L62:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.a.bk.a(com.bytedance.a.dg, org.json.JSONObject, boolean, int):java.lang.String[]");
    }

    @Nullable
    public static <T> T a(JSONObject jSONObject, String str, T t, Class<T> cls) {
        T tCast;
        if (jSONObject == null) {
            return (T) AppLog.getHeaderValue(str, t, cls);
        }
        Object objOpt = jSONObject.opt(str);
        if (objOpt == null || cls == null) {
            tCast = null;
        } else {
            try {
                tCast = cls.cast(objOpt);
            } catch (Throwable th) {
                de.a("U SHALL NOT PASS!", th);
            }
        }
        return tCast == null ? t : tCast;
    }
}
