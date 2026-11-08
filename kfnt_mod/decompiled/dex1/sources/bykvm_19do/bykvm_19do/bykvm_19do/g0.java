package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g0 {

    /* renamed from: a, reason: collision with root package name */
    private static volatile String f1336a;

    public static String a(Context context, i2 i2Var) {
        AdvertisingIdClient.Info advertisingIdInfo;
        if (TextUtils.isEmpty(f1336a)) {
            synchronized (g0.class) {
                if (!TextUtils.isEmpty(f1336a)) {
                    return f1336a;
                }
                try {
                    advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                } catch (Throwable th) {
                    if (!(th instanceof ClassNotFoundException) && !(th instanceof NoClassDefFoundError)) {
                        th.printStackTrace();
                    }
                }
                String id = advertisingIdInfo != null ? advertisingIdInfo.getId() : null;
                if (TextUtils.isEmpty(id)) {
                    id = i2Var.t().getString("google_aid", null);
                } else if (!TextUtils.equals(i2Var.t().getString("google_aid", null), id)) {
                    a(context, id, i2Var);
                }
                f1336a = id;
            }
        }
        return f1336a;
    }

    private static void a(Context context, String str, i2 i2Var) {
        if (TextUtils.isEmpty(str) || context == null) {
            return;
        }
        i2Var.t().edit().putString("google_aid", str).apply();
    }
}
