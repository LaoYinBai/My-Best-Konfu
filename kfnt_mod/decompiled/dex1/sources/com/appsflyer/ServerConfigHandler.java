package com.appsflyer;

import androidx.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ServerConfigHandler {
    @Nullable
    /* renamed from: ॱ, reason: contains not printable characters */
    static JSONObject m127(String str) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
            try {
                if (jSONObject.optBoolean("monitor", false)) {
                    r.m212().m218();
                } else {
                    r.m212().m226();
                    r.m212().m223();
                }
            } catch (JSONException unused) {
                r.m212().m226();
                r.m212().m223();
                return jSONObject;
            } catch (Throwable th) {
                th = th;
                AFLogger.afErrorLog(th.getMessage(), th);
                r.m212().m226();
                r.m212().m223();
                return jSONObject;
            }
        } catch (JSONException unused2) {
            jSONObject = null;
        } catch (Throwable th2) {
            th = th2;
            jSONObject = null;
        }
        return jSONObject;
    }

    public static String getUrl(String str) {
        return String.format(str, AppsFlyerLib.getInstance().getHost());
    }
}
