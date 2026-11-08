package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import com.bytedance.sdk.account.platform.api.IGoogleService;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class m extends d2 {
    private final Context e;
    private final i2 f;

    m(Context context, i2 i2Var) {
        super(false, false);
        this.e = context;
        this.f = i2Var;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.d2
    protected boolean a(JSONObject jSONObject) throws JSONException {
        PackageInfo packageInfo;
        int i;
        int i2;
        String packageName = this.e.getPackageName();
        if (TextUtils.isEmpty(this.f.p())) {
            jSONObject.put("package", packageName);
        } else {
            if (l0.f1356a) {
                l0.a("has zijie pkg", null);
            }
            jSONObject.put("package", this.f.p());
            jSONObject.put("real_package_name", packageName);
        }
        try {
            packageInfo = this.e.getPackageManager().getPackageInfo(packageName, 0);
        } catch (Throwable unused) {
            packageInfo = null;
        }
        if (packageInfo != null) {
            try {
                i = packageInfo.versionCode;
            } catch (Throwable th) {
                l0.a(th);
                return false;
            }
        } else {
            i = 0;
        }
        if (TextUtils.isEmpty(this.f.g())) {
            jSONObject.put("app_version", packageInfo != null ? packageInfo.versionName : "");
        } else {
            jSONObject.put("app_version", this.f.g());
        }
        if (TextUtils.isEmpty(this.f.l())) {
            jSONObject.put("app_version_minor", "");
        } else {
            jSONObject.put("app_version_minor", this.f.l());
        }
        if (this.f.d() != 0) {
            jSONObject.put("version_code", this.f.d());
        } else {
            jSONObject.put("version_code", i);
        }
        if (this.f.e() != 0) {
            jSONObject.put("update_version_code", this.f.e());
        } else {
            jSONObject.put("update_version_code", i);
        }
        if (this.f.f() != 0) {
            jSONObject.put("manifest_version_code", this.f.f());
        } else {
            jSONObject.put("manifest_version_code", i);
        }
        if (!TextUtils.isEmpty(this.f.c())) {
            jSONObject.put(TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY, this.f.c());
        }
        if (!TextUtils.isEmpty(this.f.h())) {
            jSONObject.put("tweaked_channel", this.f.h());
        }
        if (packageInfo == null || packageInfo.applicationInfo == null || (i2 = packageInfo.applicationInfo.labelRes) <= 0) {
            return true;
        }
        jSONObject.put(IGoogleService.ResponseKey.DISPLAY_NAME, this.e.getString(i2));
        return true;
    }
}
