package com.bytedance.a;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.api.plugin.PluginConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class au extends af {

    /* renamed from: a, reason: collision with root package name */
    public final Context f3377a;
    public final az f;

    public au(Context context, az azVar) {
        super(false, false);
        this.f3377a = context;
        this.f = azVar;
    }

    @Override // com.bytedance.a.af
    public boolean a(JSONObject jSONObject) throws JSONException {
        jSONObject.put(PluginConstants.KEY_SDK_VERSION, 6030090);
        jSONObject.put("sdk_version_code", de.d);
        jSONObject.put("sdk_version_name", "6.3.0");
        jSONObject.put("channel", this.f.a());
        jSONObject.put("not_request_sender", this.f.f3386b.getNotReuqestSender() ? 1 : 0);
        be.a(jSONObject, "aid", this.f.f3386b.getAid());
        be.a(jSONObject, "release_build", this.f.f3386b.getReleaseBuild());
        be.a(jSONObject, com.alipay.sdk.cons.b.f2578b, this.f.e.getString(com.alipay.sdk.cons.b.f2578b, null));
        be.a(jSONObject, "ab_sdk_version", this.f.f3387c.getString("ab_sdk_version", ""));
        String googleAid = this.f.f3386b.getGoogleAid();
        if (TextUtils.isEmpty(googleAid)) {
            googleAid = bg.a(this.f3377a, this.f);
        }
        be.a(jSONObject, "google_aid", googleAid);
        String language = this.f.f3386b.getLanguage();
        if (TextUtils.isEmpty(language)) {
            language = this.f.e.getString("app_language", null);
        }
        be.a(jSONObject, "app_language", language);
        String region = this.f.f3386b.getRegion();
        if (TextUtils.isEmpty(region)) {
            region = this.f.e.getString("app_region", null);
        }
        be.a(jSONObject, "app_region", region);
        String string = this.f.f3387c.getString("app_track", null);
        if (!TextUtils.isEmpty(string)) {
            try {
                jSONObject.put("app_track", new JSONObject(string));
            } catch (Throwable th) {
                de.a("U SHALL NOT PASS!", th);
            }
        }
        String string2 = this.f.f3387c.getString("header_custom_info", null);
        if (string2 != null && string2.length() > 0) {
            try {
                JSONObject jSONObject2 = new JSONObject(string2);
                jSONObject2.remove("_debug_flag");
                jSONObject.put("custom", jSONObject2);
            } catch (Throwable th2) {
                de.a("U SHALL NOT PASS!", th2);
            }
        }
        String string3 = this.f.f3387c.getString("user_unique_id", null);
        if (TextUtils.isEmpty(string3)) {
            return true;
        }
        be.a(jSONObject, "user_unique_id", string3);
        return true;
    }
}
