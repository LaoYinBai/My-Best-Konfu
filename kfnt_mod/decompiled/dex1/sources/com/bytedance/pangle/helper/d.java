package com.bytedance.pangle.helper;

import android.content.Context;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import com.bytedance.b.a.c.h;
import com.bytedance.framwork.core.sdkmonitor.SDKMonitor;
import com.bytedance.framwork.core.sdkmonitor.SDKMonitorUtils;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.i;
import com.bytedance.sdk.openadsdk.api.plugin.PluginConstants;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    private static SDKMonitor f4644a;

    @NonNull
    public static SDKMonitor a() {
        if (f4644a == null) {
            f4644a = SDKMonitorUtils.getInstance("7520");
        }
        return f4644a;
    }

    public static synchronized void a(Context context, String str, String str2, String str3) {
        i.a().execute(new Runnable() { // from class: com.bytedance.pangle.helper.d.2
            @Override // java.lang.Runnable
            public final void run() {
                for (int i = 0; i < 5; i++) {
                    SystemClock.sleep(2000L);
                    d.a().flushBuffer(new com.bytedance.pangle.log.b("flush buffer result : "));
                }
                d.a().flushReport(new com.bytedance.pangle.log.b("flush report result : "));
            }
        });
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(h.x, str3);
            jSONObject.putOpt(h.u, str);
            jSONObject.putOpt("channel", str2);
            jSONObject.putOpt(PluginConstants.KEY_SDK_VERSION, "0.0.1-beta.53-bugfix.18-pangle");
            if (context != null) {
                jSONObject.putOpt(h.t, context.getPackageName());
                StringBuilder sb = new StringBuilder();
                sb.append(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
                jSONObject.putOpt("app_version", sb.toString());
            }
        } catch (JSONException | Exception unused) {
        }
        ZeusLogger.d("SDKMonitorHelper", "initSDKMonitor :" + jSONObject.toString());
        ArrayList arrayList = new ArrayList();
        arrayList.add("https://mon.snssdk.com/monitor/appmonitor/v2/settings");
        SDKMonitorUtils.setConfigUrl("7520", arrayList);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("https://mon.snssdk.com/monitor/collect/");
        SDKMonitorUtils.setDefaultReportUrl("7520", arrayList2);
        SDKMonitorUtils.initMonitor(context, "7520", jSONObject, new SDKMonitor.IGetExtendParams() { // from class: com.bytedance.pangle.helper.d.1
            public final Map<String, String> getCommonParams() {
                return null;
            }

            public final String getSessionId() {
                return null;
            }
        });
    }
}
