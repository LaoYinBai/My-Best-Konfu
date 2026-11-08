package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import java.security.NoSuchAlgorithmException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class p extends d2 {
    private final Context e;

    p(Context context) {
        super(true, false);
        this.e = context;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.d2
    protected boolean a(JSONObject jSONObject) throws JSONException, NoSuchAlgorithmException {
        PackageInfo packageInfo;
        Signature[] signatureArr;
        Signature signature;
        String strB = null;
        try {
            packageInfo = this.e.getPackageManager().getPackageInfo(this.e.getPackageName(), 64);
        } catch (Throwable th) {
            l0.a(th);
            packageInfo = null;
        }
        if (packageInfo != null && (signatureArr = packageInfo.signatures) != null && signatureArr.length > 0 && (signature = signatureArr[0]) != null) {
            strB = f0.b(signature.toByteArray());
        }
        if (strB == null) {
            return true;
        }
        jSONObject.put("sig_hash", strB);
        return true;
    }
}
