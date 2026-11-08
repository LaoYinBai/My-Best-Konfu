package b.b.a.f;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import b.b.a.k.v;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import com.bytedance.sdk.account.platform.api.IGoogleService;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class p extends c {

    /* renamed from: a, reason: collision with root package name */
    public final Context f1084a;
    public final g f;

    public p(Context context, g gVar) {
        super(false, false);
        this.f1084a = context;
        this.f = gVar;
    }

    @Override // b.b.a.f.c
    public boolean a(JSONObject jSONObject) throws JSONException, PackageManager.NameNotFoundException {
        int i;
        String packageName = this.f1084a.getPackageName();
        if (TextUtils.isEmpty(this.f.f1070b.H())) {
            jSONObject.put("package", packageName);
        } else {
            v.a("has zijie pkg", (Throwable) null);
            jSONObject.put("package", this.f.f1070b.H());
            jSONObject.put("real_package_name", packageName);
        }
        try {
            PackageInfo packageInfo = this.f1084a.getPackageManager().getPackageInfo(packageName, 0);
            int i2 = packageInfo.versionCode;
            if (TextUtils.isEmpty(this.f.f1070b.z())) {
                jSONObject.put("app_version", packageInfo.versionName);
            } else {
                jSONObject.put("app_version", this.f.f1070b.z());
            }
            if (TextUtils.isEmpty(this.f.f1070b.G())) {
                jSONObject.put("app_version_minor", "");
            } else {
                jSONObject.put("app_version_minor", this.f.f1070b.G());
            }
            if (this.f.f1070b.B() != 0) {
                jSONObject.put("version_code", this.f.f1070b.B());
            } else {
                jSONObject.put("version_code", i2);
            }
            if (this.f.f1070b.D() != 0) {
                jSONObject.put("update_version_code", this.f.f1070b.D());
            } else {
                jSONObject.put("update_version_code", i2);
            }
            if (this.f.f1070b.E() != 0) {
                jSONObject.put("manifest_version_code", this.f.f1070b.E());
            } else {
                jSONObject.put("manifest_version_code", i2);
            }
            if (!TextUtils.isEmpty(this.f.f1070b.y())) {
                jSONObject.put(TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY, this.f.f1070b.y());
            }
            if (!TextUtils.isEmpty(this.f.i())) {
                jSONObject.put("tweaked_channel", this.f.i());
            }
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (applicationInfo == null || (i = applicationInfo.labelRes) <= 0) {
                return true;
            }
            jSONObject.put(IGoogleService.ResponseKey.DISPLAY_NAME, this.f1084a.getString(i));
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            v.a(e);
            return false;
        }
    }
}
