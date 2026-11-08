package com.bytedance.a;

import android.content.ContentProviderClient;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.bytedance.a.bm;
import com.bytedance.common.utility.Logger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class bc implements bm {
    @Override // com.bytedance.a.bm
    public bm.a a(Context context) throws RemoteException {
        Bundle bundleCall;
        Uri uri = Uri.parse("content://cn.nubia.identity/identity");
        try {
            if (Build.VERSION.SDK_INT > 17) {
                ContentProviderClient contentProviderClientAcquireContentProviderClient = context.getContentResolver().acquireContentProviderClient(uri);
                if (contentProviderClientAcquireContentProviderClient == null) {
                    return null;
                }
                bundleCall = contentProviderClientAcquireContentProviderClient.call("getOAID", null, null);
                if (Build.VERSION.SDK_INT >= 24) {
                    contentProviderClientAcquireContentProviderClient.close();
                } else {
                    contentProviderClientAcquireContentProviderClient.release();
                }
            } else {
                bundleCall = context.getContentResolver().call(uri, "getOAID", (String) null, (Bundle) null);
            }
            if (bundleCall == null) {
                return null;
            }
            if (bundleCall.getInt("code", -1) == 0) {
                bm.a aVar = new bm.a();
                aVar.f3415b = bundleCall.getString("id");
                return aVar;
            }
            String string = bundleCall.getString("message");
            if (!TextUtils.isEmpty(string)) {
                Logger.e(string);
            }
            return null;
        } catch (Exception e) {
            de.a("", e);
            return null;
        }
    }

    @Override // com.bytedance.a.bm
    public boolean c(Context context) {
        return Build.VERSION.SDK_INT > 28;
    }
}
