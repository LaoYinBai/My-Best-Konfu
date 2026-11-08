package b.b.a.f;

import android.content.Context;
import android.text.TextUtils;
import b.b.a.k.v;
import com.bytedance.sdk.openadsdk.api.plugin.PluginConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f extends c {

    /* renamed from: a, reason: collision with root package name */
    public final Context f1068a;
    public final g f;

    public f(Context context, g gVar) {
        super(false, false);
        this.f1068a = context;
        this.f = gVar;
    }

    @Override // b.b.a.f.c
    public boolean a(JSONObject jSONObject) throws JSONException {
        jSONObject.put(PluginConstants.KEY_SDK_VERSION, 590);
        jSONObject.put("sdk_version_code", v.f1151c);
        jSONObject.put("sdk_version_name", "0.0.5");
        jSONObject.put("channel", this.f.b());
        jSONObject.put("not_request_sender", this.f.f1070b.e() ? 1 : 0);
        h.a(jSONObject, "aid", this.f.a());
        h.a(jSONObject, "release_build", this.f.f1070b.d());
        h.a(jSONObject, com.alipay.sdk.cons.b.f2578b, this.f.e.getString(com.alipay.sdk.cons.b.f2578b, null));
        h.a(jSONObject, "ab_sdk_version", this.f.f1071c.getString("ab_sdk_version", ""));
        String strJ = this.f.f1070b.j();
        if (TextUtils.isEmpty(strJ)) {
            strJ = b.b.a.k.l.a(this.f1068a, this.f);
        }
        h.a(jSONObject, "google_aid", strJ);
        String strK = this.f.f1070b.k();
        if (TextUtils.isEmpty(strK)) {
            strK = this.f.e.getString("app_language", null);
        }
        h.a(jSONObject, "app_language", strK);
        String strL = this.f.f1070b.l();
        if (TextUtils.isEmpty(strL)) {
            strL = this.f.e.getString("app_region", null);
        }
        h.a(jSONObject, "app_region", strL);
        String string = this.f.f1071c.getString("app_track", null);
        if (!TextUtils.isEmpty(string)) {
            try {
                jSONObject.put("app_track", new JSONObject(string));
            } catch (Throwable th) {
                v.a(th);
            }
        }
        String string2 = this.f.f1071c.getString("header_custom_info", null);
        if (string2 != null && string2.length() > 0) {
            try {
                JSONObject jSONObject2 = new JSONObject(string2);
                jSONObject2.remove("_debug_flag");
                jSONObject.put("custom", jSONObject2);
            } catch (Throwable th2) {
                v.a(th2);
            }
        }
        String string3 = this.f.f1071c.getString("user_unique_id", null);
        if (TextUtils.isEmpty(string3)) {
            return true;
        }
        h.a(jSONObject, "user_unique_id", string3);
        return true;
    }
}
