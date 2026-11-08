package com.kwad.sdk.splashscreen.kwai;

import android.content.Context;
import androidx.annotation.Nullable;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    public static void a(Context context) {
        b bVarB = b(context);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (bVarB == null) {
            bVarB = new b(jCurrentTimeMillis, 1);
        } else if (bVarB.a(jCurrentTimeMillis)) {
            bVarB.b++;
        } else {
            bVarB.a = jCurrentTimeMillis;
            bVarB.b = 1;
        }
        a(context, bVarB);
    }

    public static void a(Context context, b bVar) {
        if (context == null || bVar == null) {
            com.kwad.sdk.core.d.a.a("SplashLocalHelper", "saveSplashLocalInfo illegal arguments.");
        } else {
            context.getSharedPreferences("ksadsdk_splash_local_ad_force_active", 0).edit().putString("key_local_info", bVar.toJson().toString()).apply();
        }
    }

    @Nullable
    public static b b(Context context) {
        if (context == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(context.getSharedPreferences("ksadsdk_splash_local_ad_force_active", 0).getString("key_local_info", null));
            b bVar = new b();
            bVar.parseJson(jSONObject);
            return bVar;
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.b(e);
            return null;
        }
    }
}
