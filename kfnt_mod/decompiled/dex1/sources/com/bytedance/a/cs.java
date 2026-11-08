package com.bytedance.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import com.bytedance.sdk.account.platform.api.IGoogleService;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class cs extends af {

    /* renamed from: a, reason: collision with root package name */
    public final Context f3477a;
    public final az f;

    public cs(Context context, az azVar) {
        super(false, false);
        this.f3477a = context;
        this.f = azVar;
    }

    @Override // com.bytedance.a.af
    public boolean a(JSONObject jSONObject) throws JSONException, PackageManager.NameNotFoundException {
        String str;
        Object version;
        String str2;
        Object versionMinor;
        int i;
        String packageName = this.f3477a.getPackageName();
        if (TextUtils.isEmpty(this.f.f3386b.getZiJieCloudPkg())) {
            jSONObject.put("package", packageName);
        } else {
            de.a("has zijie pkg", null);
            jSONObject.put("package", this.f.f3386b.getZiJieCloudPkg());
            jSONObject.put("real_package_name", packageName);
        }
        try {
            PackageInfo packageInfo = this.f3477a.getPackageManager().getPackageInfo(packageName, 0);
            int i2 = packageInfo.versionCode;
            if (TextUtils.isEmpty(this.f.f3386b.getVersion())) {
                str = "app_version";
                version = packageInfo.versionName;
            } else {
                str = "app_version";
                version = this.f.f3386b.getVersion();
            }
            jSONObject.put(str, version);
            if (TextUtils.isEmpty(this.f.f3386b.getVersionMinor())) {
                str2 = "app_version_minor";
                versionMinor = "";
            } else {
                str2 = "app_version_minor";
                versionMinor = this.f.f3386b.getVersionMinor();
            }
            jSONObject.put(str2, versionMinor);
            if (this.f.f3386b.getVersionCode() != 0) {
                jSONObject.put("version_code", this.f.f3386b.getVersionCode());
            } else {
                jSONObject.put("version_code", i2);
            }
            if (this.f.f3386b.getUpdateVersionCode() != 0) {
                jSONObject.put("update_version_code", this.f.f3386b.getUpdateVersionCode());
            } else {
                jSONObject.put("update_version_code", i2);
            }
            if (this.f.f3386b.getManifestVersionCode() != 0) {
                jSONObject.put("manifest_version_code", this.f.f3386b.getManifestVersionCode());
            } else {
                jSONObject.put("manifest_version_code", i2);
            }
            if (!TextUtils.isEmpty(this.f.f3386b.getAppName())) {
                jSONObject.put(TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY, this.f.f3386b.getAppName());
            }
            if (!TextUtils.isEmpty(this.f.f3386b.getTweakedChannel())) {
                jSONObject.put("tweaked_channel", this.f.f3386b.getTweakedChannel());
            }
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (applicationInfo == null || (i = applicationInfo.labelRes) <= 0) {
                return true;
            }
            jSONObject.put(IGoogleService.ResponseKey.DISPLAY_NAME, this.f3477a.getString(i));
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            de.a("U SHALL NOT PASS!", e);
            return false;
        }
    }
}
