package com.appsflyer;

import android.os.Bundle;
import com.appsflyer.b;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.iid.InstanceIDListenerService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GcmInstanceIdListener extends InstanceIDListenerService {
    /* JADX WARN: Multi-variable type inference failed */
    public void onTokenRefresh() {
        String token;
        super.onTokenRefresh();
        String string = AppsFlyerProperties.getInstance().getString("gcmProjectNumber");
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            token = InstanceID.getInstance(getApplicationContext()).getToken(string, "GCM", (Bundle) null);
        } catch (Throwable th) {
            AFLogger.afErrorLog("Error registering for uninstall tracking", th);
            token = null;
        }
        if (token != null) {
            AFLogger.afInfoLog("GCM Refreshed Token = ".concat(String.valueOf(token)));
            b.e.C0088b c0088bM141 = b.e.C0088b.m141(AppsFlyerProperties.getInstance().getString("afUninstallToken"));
            b.e.C0088b c0088b = new b.e.C0088b(jCurrentTimeMillis, token);
            if (c0088bM141.m143(c0088b)) {
                y.m248(getApplicationContext(), c0088b);
            }
        }
    }
}
