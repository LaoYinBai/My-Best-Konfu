package com.kwad.sdk.g;

import android.content.Context;
import android.util.Log;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.miitmdid.interfaces.IIdentifierListener;
import com.bun.miitmdid.interfaces.IdSupplier;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static boolean a = false;
    private static boolean b = false;

    /* renamed from: com.kwad.sdk.g.a$a, reason: collision with other inner class name */
    public interface InterfaceC0063a {
    }

    public static void a(Context context, final InterfaceC0063a interfaceC0063a) {
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
            int iInitSdk = MdidSdkHelper.InitSdk(context.getApplicationContext(), true, new IIdentifierListener() { // from class: com.kwad.sdk.g.a.1
            });
            Log.d("KSAdSDK", "[OADIDSDKHelper25]:sdk init time=" + (System.currentTimeMillis() - jCurrentTimeMillis) + "--result=" + iInitSdk);
        } catch (Throwable th) {
            Log.d("KSAdSDK", "[OADIDSDKHelper25]:oaid sdk not find " + th.getMessage());
            a = false;
            b = true;
        }
    }

    public static boolean a() {
        try {
            new IIdentifierListener() { // from class: com.kwad.sdk.g.a.2
            }.OnSupport(true, (IdSupplier) null);
            return true;
        } catch (Throwable th) {
            Log.d("KSAdSDK", "[OADIDSDKHelper25]:oaid sdk not find " + th.getMessage());
            return false;
        }
    }
}
