package com.ss.union.game.sdk.core.glide.manager;

import android.content.Context;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.ss.union.game.sdk.core.glide.manager.ConnectivityMonitor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DefaultConnectivityMonitorFactory implements ConnectivityMonitorFactory {
    private static final String a = "ConnectivityMonitor";
    private static final String b = "android.permission.ACCESS_NETWORK_STATE";

    @Override // com.ss.union.game.sdk.core.glide.manager.ConnectivityMonitorFactory
    public ConnectivityMonitor build(Context context, ConnectivityMonitor.ConnectivityListener connectivityListener) {
        boolean z = ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_NETWORK_STATE") == 0;
        if (Log.isLoggable(a, 3)) {
            Log.d(a, z ? "ACCESS_NETWORK_STATE permission granted, registering connectivity monitor" : "ACCESS_NETWORK_STATE permission missing, cannot register connectivity monitor");
        }
        return z ? new c(context, connectivityListener) : new e();
    }
}
