package b.b.a.f;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a extends c {

    /* renamed from: a, reason: collision with root package name */
    public Context f1062a;

    public a(Context context) {
        super(true, false);
        this.f1062a = context;
    }

    @Override // b.b.a.f.c
    public boolean a(JSONObject jSONObject) throws JSONException {
        try {
            Bundle bundle = this.f1062a.getPackageManager().getApplicationInfo(this.f1062a.getPackageName(), 128).metaData;
            if (bundle == null || TextUtils.isEmpty("UMENG_APPKEY")) {
                return true;
            }
            jSONObject.put("appkey", bundle.getString("UMENG_APPKEY"));
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return true;
        }
    }
}
