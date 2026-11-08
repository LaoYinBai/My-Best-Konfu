package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.bytedance.msdk.adapter.util.Logger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d0 {

    /* renamed from: a, reason: collision with root package name */
    private static String f1990a;

    public static String a(String str) {
        try {
            if (!b()) {
                return str;
            }
            String strB = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().b();
            if (TextUtils.isEmpty(strB)) {
                return str;
            }
            Log.d("TestHelperUtils", "AnyDoorId=" + strB);
            return Uri.parse(str).buildUpon().appendQueryParameter(a(), strB).appendQueryParameter("aid", "5001121").toString();
        } catch (Throwable unused) {
            return str;
        }
    }

    public static boolean b() {
        return Logger.isDebug() && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().u() && bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b.D().v();
    }

    public static String a() {
        if (TextUtils.isEmpty(f1990a)) {
            f1990a = new String(Base64.decode("ZGV2aWNlX2lk", 0));
        }
        return f1990a;
    }
}
