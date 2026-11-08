package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.api.plugin.PluginConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class h2 extends d2 {
    private final Context e;
    private final i2 f;

    h2(Context context, i2 i2Var) {
        super(false, false);
        this.e = context;
        this.f = i2Var;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.d2
    protected boolean a(JSONObject jSONObject) throws JSONException {
        jSONObject.put(PluginConstants.KEY_SDK_VERSION, 336);
        jSONObject.put("sdk_version_name", "3.6.0-rc.10-embed");
        jSONObject.put("channel", this.f.z());
        j2.a(jSONObject, "aid", this.f.y());
        j2.a(jSONObject, "release_build", this.f.P());
        j2.a(jSONObject, "app_region", this.f.C());
        j2.a(jSONObject, "app_language", this.f.B());
        j2.a(jSONObject, com.alipay.sdk.cons.b.f2578b, this.f.a());
        j2.a(jSONObject, "ab_sdk_version", this.f.E());
        j2.a(jSONObject, "ab_version", this.f.I());
        j2.a(jSONObject, "aliyun_uuid", this.f.q());
        String strA = this.f.A();
        if (TextUtils.isEmpty(strA)) {
            strA = g0.a(this.e, this.f);
        }
        if (!TextUtils.isEmpty(strA)) {
            j2.a(jSONObject, "google_aid", strA);
        }
        String strO = this.f.O();
        if (!TextUtils.isEmpty(strO)) {
            try {
                jSONObject.put("app_track", new JSONObject(strO));
            } catch (Throwable th) {
                l0.a(th);
            }
        }
        String strD = this.f.D();
        if (strD != null && strD.length() > 0) {
            jSONObject.put("custom", new JSONObject(strD));
        }
        j2.a(jSONObject, "user_unique_id", this.f.F());
        return true;
    }
}
