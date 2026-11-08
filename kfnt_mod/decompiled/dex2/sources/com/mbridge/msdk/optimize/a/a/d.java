package com.mbridge.msdk.optimize.a.a;

import android.content.ContentProviderClient;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.heytap.mcssdk.mode.CommandMessage;

/* compiled from: NubiaDeviceHelper.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class d {
    private Context a;

    public d(Context context) {
        this.a = context;
    }

    public final String a() {
        Bundle bundleCall;
        try {
            Uri uri = Uri.parse("content://cn.nubia.identity/identity");
            if (Build.VERSION.SDK_INT > 17) {
                ContentProviderClient contentProviderClientAcquireContentProviderClient = this.a.getContentResolver().acquireContentProviderClient(uri);
                bundleCall = contentProviderClientAcquireContentProviderClient.call("getOAID", null, null);
                if (contentProviderClientAcquireContentProviderClient != null) {
                    if (Build.VERSION.SDK_INT >= 24) {
                        contentProviderClientAcquireContentProviderClient.close();
                    } else {
                        contentProviderClientAcquireContentProviderClient.release();
                    }
                }
            } else {
                bundleCall = this.a.getContentResolver().call(uri, "getOAID", (String) null, (Bundle) null);
            }
            if ((bundleCall != null ? bundleCall.getInt(CommandMessage.CODE, -1) : -1) == 0) {
                return bundleCall.getString("id");
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
