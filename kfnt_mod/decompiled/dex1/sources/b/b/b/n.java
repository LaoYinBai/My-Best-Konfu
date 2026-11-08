package b.b.b;

import android.content.ContentProviderClient;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import b.b.b.p;
import com.bytedance.common.utility.Logger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class n implements p {
    @Override // b.b.b.p
    public boolean b(Context context) {
        return Build.VERSION.SDK_INT > 28;
    }

    @Override // b.b.b.p
    public p.a c(Context context) throws RemoteException {
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
                p.a aVar = new p.a();
                aVar.f1176b = bundleCall.getString("id");
                return aVar;
            }
            String string = bundleCall.getString("message");
            if (!TextUtils.isEmpty(string)) {
                Logger.e(string);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
