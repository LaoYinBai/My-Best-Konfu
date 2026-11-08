package com.appsflyer;

import com.appsflyer.b;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class FirebaseInstanceIdListener extends FirebaseInstanceIdService {
    /* JADX WARN: Multi-variable type inference failed */
    public void onTokenRefresh() {
        String token;
        super.onTokenRefresh();
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            token = FirebaseInstanceId.getInstance().getToken();
        } catch (Throwable th) {
            AFLogger.afErrorLog("Error registering for uninstall tracking", th);
            token = null;
        }
        if (token != null) {
            AFLogger.afInfoLog("Firebase Refreshed Token = ".concat(String.valueOf(token)));
            b.e.C0088b c0088bM141 = b.e.C0088b.m141(AppsFlyerProperties.getInstance().getString("afUninstallToken"));
            b.e.C0088b c0088b = new b.e.C0088b(jCurrentTimeMillis, token);
            if (c0088bM141.m143(c0088b)) {
                y.m248(getApplicationContext(), c0088b);
            }
        }
    }
}
