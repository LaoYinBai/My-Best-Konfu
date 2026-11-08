package com.umeng.commonsdk.internal.crash;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.sigmob.sdk.common.mta.PointCategory;
import com.umeng.commonsdk.framework.c;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.stateless.f;
import com.umeng.commonsdk.statistics.common.ULog;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UMCrashManager {
    private static boolean isReportCrash;
    private static Object mObject = new Object();

    public static void reportCrash(final Context context, final Throwable th) {
        if (isReportCrash) {
            return;
        }
        ULog.i("walle-crash", "report is " + isReportCrash);
        new Thread(new Runnable() { // from class: com.umeng.commonsdk.internal.crash.UMCrashManager.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    synchronized (UMCrashManager.mObject) {
                        if (context != null && th != null && !UMCrashManager.isReportCrash) {
                            boolean unused = UMCrashManager.isReportCrash = true;
                            ULog.i("walle-crash", "report thread is " + UMCrashManager.isReportCrash);
                            String strA = a.a(th);
                            if (!TextUtils.isEmpty(strA)) {
                                f.a(context, context.getFilesDir() + "/" + com.umeng.commonsdk.stateless.a.e + "/" + Base64.encodeToString(com.umeng.commonsdk.internal.a.a.getBytes(), 0), 10);
                                UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
                                JSONObject jSONObjectBuildSLBaseHeader = uMSLEnvelopeBuild.buildSLBaseHeader(context);
                                try {
                                    JSONObject jSONObject = new JSONObject();
                                    jSONObject.put(c.a, strA);
                                    jSONObject.put("ts", System.currentTimeMillis());
                                    JSONObject jSONObject2 = new JSONObject();
                                    jSONObject2.put(PointCategory.CRASH, jSONObject);
                                    JSONObject jSONObject3 = new JSONObject();
                                    jSONObject3.put("tp", jSONObject2);
                                    JSONObject jSONObjectBuildSLEnvelope = uMSLEnvelopeBuild.buildSLEnvelope(context, jSONObjectBuildSLBaseHeader, jSONObject3, com.umeng.commonsdk.internal.a.a);
                                    if (jSONObjectBuildSLEnvelope != null) {
                                        jSONObjectBuildSLEnvelope.has(c.c);
                                    }
                                } catch (JSONException unused2) {
                                }
                            }
                        }
                    }
                } catch (Throwable unused3) {
                }
            }
        }).start();
    }
}
