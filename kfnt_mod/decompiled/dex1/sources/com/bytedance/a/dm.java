package com.bytedance.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import java.security.NoSuchAlgorithmException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class dm extends af {

    /* renamed from: a, reason: collision with root package name */
    public final Context f3511a;

    public dm(Context context) {
        super(true, false);
        this.f3511a = context;
    }

    @Override // com.bytedance.a.af
    public boolean a(JSONObject jSONObject) throws JSONException, PackageManager.NameNotFoundException, NoSuchAlgorithmException {
        PackageInfo packageInfo;
        Signature[] signatureArr;
        Signature signature;
        String strB = null;
        try {
            packageInfo = this.f3511a.getPackageManager().getPackageInfo(this.f3511a.getPackageName(), 64);
        } catch (PackageManager.NameNotFoundException e) {
            de.a("U SHALL NOT PASS!", e);
            packageInfo = null;
        }
        if (packageInfo != null && (signatureArr = packageInfo.signatures) != null && signatureArr.length > 0 && (signature = signatureArr[0]) != null) {
            strB = am.b(signature.toByteArray());
        }
        if (strB == null) {
            return true;
        }
        jSONObject.put("sig_hash", strB);
        return true;
    }
}
