package b.b.a.f;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d extends c {
    public d(Context context) {
        super(true, false);
    }

    @Override // b.b.a.f.c
    @SuppressLint({"MissingPermission"})
    public boolean a(JSONObject jSONObject) throws JSONException {
        jSONObject.put("os", "Android");
        jSONObject.put("platform", "Android");
        jSONObject.put("sdk_lib", "Android");
        jSONObject.put("os_version", Build.VERSION.RELEASE);
        jSONObject.put("os_api", Build.VERSION.SDK_INT);
        jSONObject.put("device_model", Build.MODEL);
        jSONObject.put("device_brand", Build.BRAND);
        jSONObject.put("device_manufacturer", Build.MANUFACTURER);
        jSONObject.put("cpu_abi", Build.CPU_ABI);
        jSONObject.put("sdk_target_version", 29);
        jSONObject.put("git_hash", "ebd831e7");
        return true;
    }
}
