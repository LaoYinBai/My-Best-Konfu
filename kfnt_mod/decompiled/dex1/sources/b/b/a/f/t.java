package b.b.a.f;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import b.b.a.k.v;
import java.security.NoSuchAlgorithmException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class t extends c {

    /* renamed from: a, reason: collision with root package name */
    public final Context f1086a;

    public t(Context context) {
        super(true, false);
        this.f1086a = context;
    }

    @Override // b.b.a.f.c
    public boolean a(JSONObject jSONObject) throws JSONException, PackageManager.NameNotFoundException, NoSuchAlgorithmException {
        PackageInfo packageInfo;
        Signature[] signatureArr;
        Signature signature;
        String strB = null;
        try {
            packageInfo = this.f1086a.getPackageManager().getPackageInfo(this.f1086a.getPackageName(), 64);
        } catch (PackageManager.NameNotFoundException e) {
            v.a(e);
            packageInfo = null;
        }
        if (packageInfo != null && (signatureArr = packageInfo.signatures) != null && signatureArr.length > 0 && (signature = signatureArr[0]) != null) {
            strB = b.b.a.k.h.b(signature.toByteArray());
        }
        if (strB == null) {
            return true;
        }
        jSONObject.put("sig_hash", strB);
        return true;
    }
}
