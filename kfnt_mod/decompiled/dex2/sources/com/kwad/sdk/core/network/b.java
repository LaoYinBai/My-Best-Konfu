package com.kwad.sdk.core.network;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.internal.api.SceneImpl;
import com.kwad.sdk.plugin.DevelopMangerPlugin;
import com.kwad.sdk.utils.t;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class b implements g {
    private final Map<String, String> b = new HashMap();
    protected final JSONObject a = new JSONObject();

    protected b() {
        g();
        h();
        DevelopMangerPlugin.DevelopValue developValueA = ((DevelopMangerPlugin) com.kwad.sdk.plugin.f.a(DevelopMangerPlugin.class)).a("KEY_HOST_ENCRYPT_DISABLE");
        if (developValueA != null && ((Boolean) developValueA.getValue()).booleanValue()) {
            a("x-ksad-ignore-decrypt", String.valueOf(true));
        }
        String strB = e.a().b();
        if (!TextUtils.isEmpty(strB)) {
            a("cookie", strB);
        }
        com.kwad.sdk.core.a.d.a(c());
    }

    @Override // com.kwad.sdk.core.network.g
    public abstract String a();

    public void a(String str, double d) throws JSONException {
        t.a(this.a, str, d);
    }

    public void a(String str, int i) {
        t.a(this.a, str, i);
    }

    public void a(String str, long j) {
        t.a(this.a, str, j);
    }

    public void a(String str, com.kwad.sdk.core.b bVar) throws JSONException {
        t.a(this.a, str, bVar);
    }

    public void a(String str, String str2) {
        this.b.put(str, str2);
    }

    public void a(String str, List<? extends com.kwad.sdk.core.b> list) throws JSONException {
        t.a(this.a, str, list);
    }

    public void a(String str, JSONArray jSONArray) throws JSONException {
        t.a(this.a, str, jSONArray);
    }

    public void a(String str, JSONObject jSONObject) throws JSONException {
        t.a(this.a, str, jSONObject);
    }

    public void a(String str, boolean z) {
        t.a(this.a, str, z);
    }

    @Override // com.kwad.sdk.core.network.g
    @Nullable
    public SceneImpl b() {
        return null;
    }

    public void b(String str, String str2) {
        t.a(this.a, str, str2);
    }

    @Override // com.kwad.sdk.core.network.g
    public Map<String, String> c() {
        return this.b;
    }

    @Override // com.kwad.sdk.core.network.g
    public Map<String, String> d() {
        return null;
    }

    @Override // com.kwad.sdk.core.network.g
    public JSONObject e() {
        DevelopMangerPlugin.DevelopValue developValueA = ((DevelopMangerPlugin) com.kwad.sdk.plugin.f.a(DevelopMangerPlugin.class)).a("KEY_HOST_ENCRYPT_DISABLE");
        if (developValueA != null && ((Boolean) developValueA.getValue()).booleanValue()) {
            return this.a;
        }
        JSONObject jSONObject = new JSONObject();
        t.a(jSONObject, HiAnalyticsConstant.HaKey.BI_KEY_VERSION, KsAdSDKImpl.get().getSDKVersion());
        t.a(jSONObject, "appId", KsAdSDKImpl.get().getAppId());
        t.a(jSONObject, "message", com.kwad.sdk.core.a.d.a(this.a.toString()));
        com.kwad.sdk.core.a.d.a(a().replace(f(), ""), c(), jSONObject.toString());
        return jSONObject;
    }

    protected String f() {
        return com.kwad.sdk.d.a();
    }

    protected abstract void g();

    protected abstract void h();
}
