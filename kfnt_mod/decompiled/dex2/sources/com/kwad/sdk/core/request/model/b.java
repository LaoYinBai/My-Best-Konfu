package com.kwad.sdk.core.request.model;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.utils.av;
import com.kwad.sdk.utils.t;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b implements com.kwad.sdk.core.b {
    private static JSONObject a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;

    public static JSONObject a() {
        if (!a(a)) {
            a = b().toJson();
        }
        return a;
    }

    private static boolean a(@Nullable JSONObject jSONObject) {
        if (jSONObject == null) {
            return false;
        }
        String strOptString = jSONObject.optString("appId");
        String strOptString2 = jSONObject.optString("name");
        return !TextUtils.isEmpty(strOptString2) && !TextUtils.isEmpty(strOptString) && strOptString.equals(KsAdSDKImpl.get().getAppId()) && strOptString2.equals(KsAdSDKImpl.get().getAppName());
    }

    public static b b() {
        b bVar = new b();
        bVar.b = KsAdSDKImpl.get().getAppId();
        bVar.c = KsAdSDKImpl.get().getAppName();
        Context context = KsAdSDKImpl.get().getContext();
        if (context != null) {
            bVar.d = context.getPackageName();
            bVar.e = av.w(context);
        }
        bVar.f = com.kwad.sdk.utils.g.a(context);
        return bVar;
    }

    @Override // com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) {
    }

    @Override // com.kwad.sdk.core.b
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        t.a(jSONObject, "appId", this.b);
        t.a(jSONObject, "name", this.c);
        t.a(jSONObject, "packageName", this.d);
        t.a(jSONObject, HiAnalyticsConstant.HaKey.BI_KEY_VERSION, this.e);
        t.a(jSONObject, "sha1", this.f);
        return jSONObject;
    }
}
