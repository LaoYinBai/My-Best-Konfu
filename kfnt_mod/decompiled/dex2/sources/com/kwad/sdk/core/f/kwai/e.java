package com.kwad.sdk.core.f.kwai;

import android.content.ContentProviderClient;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import com.heytap.mcssdk.mode.CommandMessage;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e {
    private Context a;

    public e(Context context) {
        this.a = context;
    }

    public String a() throws RemoteException {
        String string;
        Bundle bundleCall;
        string = "";
        try {
            Uri uri = Uri.parse("content://cn.nubia.identity/identity");
            if (Build.VERSION.SDK_INT > 17) {
                ContentProviderClient contentProviderClientAcquireContentProviderClient = this.a.getContentResolver().acquireContentProviderClient(uri);
                bundleCall = contentProviderClientAcquireContentProviderClient.call("getOAID", null, null);
                if (Build.VERSION.SDK_INT >= 24) {
                    contentProviderClientAcquireContentProviderClient.close();
                } else {
                    contentProviderClientAcquireContentProviderClient.release();
                }
            } else {
                bundleCall = this.a.getContentResolver().call(uri, "getOAID", (String) null, (Bundle) null);
            }
            if (bundleCall != null) {
                string = bundleCall.getInt(CommandMessage.CODE, -1) == 0 ? bundleCall.getString("id") : "";
                com.kwad.sdk.core.d.a.c("NubiaDeviceIDHelper", "getOAID oaid:" + string + "faledMsg:" + bundleCall.getString("message"));
            }
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.c("NubiaDeviceIDHelper", "getOAID fail");
            com.kwad.sdk.core.d.a.b(e);
        }
        return string;
    }
}
