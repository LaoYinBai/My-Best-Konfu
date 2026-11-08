package b.b.a.k;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class l {

    /* renamed from: a, reason: collision with root package name */
    public static volatile String f1139a;

    public static String a(Context context, b.b.a.f.g gVar) {
        AdvertisingIdClient.Info advertisingIdInfo;
        if (TextUtils.isEmpty(f1139a)) {
            synchronized (l.class) {
                if (!TextUtils.isEmpty(f1139a)) {
                    return f1139a;
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
                    id = gVar.e.getString("google_aid", null);
                } else if (!TextUtils.equals(gVar.e.getString("google_aid", null), id) && !TextUtils.isEmpty(id)) {
                    gVar.e.edit().putString("google_aid", id).apply();
                }
                f1139a = id;
            }
        }
        return f1139a;
    }
}
