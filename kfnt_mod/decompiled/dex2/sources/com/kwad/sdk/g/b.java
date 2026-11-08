package com.kwad.sdk.g;

import android.content.Context;
import android.util.Log;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.miitmdid.interfaces.IIdentifierListener;
import com.bun.miitmdid.interfaces.IdSupplier;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private static boolean a = false;
    private static boolean b = false;

    public interface a {
    }

    public static void a(Context context, final a aVar) {
        if (context == null || b) {
            return;
        }
        if (!a()) {
            b = true;
            return;
        }
        if (a) {
            return;
        }
        a = true;
        try {
            final long jCurrentTimeMillis = System.currentTimeMillis();
            int iInitSdk = MdidSdkHelper.InitSdk(context.getApplicationContext(), true, new IIdentifierListener() { // from class: com.kwad.sdk.g.b.1
            });
            Log.d("KSAdSDK", "[OADIDSDKHelper26]:sdk init time=" + (System.currentTimeMillis() - jCurrentTimeMillis) + "--result=" + iInitSdk);
        } catch (Throwable th) {
            Log.d("KSAdSDK", "[OADIDSDKHelper26]:oaid sdk not find " + th.getMessage());
            a = false;
            b = true;
        }
    }

    public static boolean a() {
        try {
            new IIdentifierListener() { // from class: com.kwad.sdk.g.b.2
            }.onSupport((IdSupplier) null);
            return true;
        } catch (Throwable th) {
            Log.d("KSAdSDK", "[OADIDSDKHelper26]:oaid sdk not find " + th.getMessage());
            return false;
        }
    }
}
