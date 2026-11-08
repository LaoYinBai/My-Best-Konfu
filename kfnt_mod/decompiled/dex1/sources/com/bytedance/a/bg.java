package com.bytedance.a;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bg {

    /* renamed from: a, reason: collision with root package name */
    public static volatile String f3403a;

    public static String a(Context context, az azVar) {
        AdvertisingIdClient.Info advertisingIdInfo;
        if (TextUtils.isEmpty(f3403a)) {
            synchronized (bg.class) {
                if (!TextUtils.isEmpty(f3403a)) {
                    return f3403a;
                }
                try {
                    advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                } catch (Throwable th) {
                    if (!(th instanceof ClassNotFoundException) && !(th instanceof NoClassDefFoundError)) {
                        de.a("", th);
                    }
                }
                String id = advertisingIdInfo != null ? advertisingIdInfo.getId() : null;
                if (TextUtils.isEmpty(id)) {
                    id = azVar.e.getString("google_aid", null);
                } else if (!TextUtils.equals(azVar.e.getString("google_aid", null), id) && !TextUtils.isEmpty(id)) {
                    azVar.e.edit().putString("google_aid", id).apply();
                }
                f3403a = id;
            }
        }
        return f3403a;
    }
}
