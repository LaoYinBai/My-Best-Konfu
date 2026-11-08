package com.ss.union.game.sdk.core.glide.manager;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.ss.union.game.sdk.core.glide.manager.ConnectivityMonitor;
import com.ss.union.game.sdk.core.glide.util.Preconditions;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class c implements ConnectivityMonitor {
    private static final String c = "ConnectivityMonitor";
    final ConnectivityMonitor.ConnectivityListener a;
    boolean b;
    private final Context d;
    private boolean e;
    private final BroadcastReceiver f = new BroadcastReceiver() { // from class: com.ss.union.game.sdk.core.glide.manager.c.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean z = c.this.b;
            c.this.b = c.this.a(context);
            if (z != c.this.b) {
                if (Log.isLoggable(c.c, 3)) {
                    Log.d(c.c, "connectivity changed, isConnected: " + c.this.b);
                }
                c.this.a.onConnectivityChanged(c.this.b);
            }
        }
    };

    @Override // com.ss.union.game.sdk.core.glide.manager.LifecycleListener
    public void onDestroy() {
    }

    c(Context context, ConnectivityMonitor.ConnectivityListener connectivityListener) {
        this.d = context.getApplicationContext();
        this.a = connectivityListener;
    }

    private void a() {
        if (this.e) {
            return;
        }
        this.b = a(this.d);
        try {
            this.d.registerReceiver(this.f, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.e = true;
        } catch (SecurityException e) {
            if (Log.isLoggable(c, 5)) {
                Log.w(c, "Failed to register", e);
            }
        }
    }

    private void b() {
        if (this.e) {
            this.d.unregisterReceiver(this.f);
            this.e = false;
        }
    }

    @SuppressLint({"MissingPermission"})
    boolean a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) Preconditions.checkNotNull((ConnectivityManager) context.getSystemService("connectivity"))).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (RuntimeException e) {
            if (Log.isLoggable(c, 5)) {
                Log.w(c, "Failed to determine connectivity status when connectivity changed", e);
            }
            return true;
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.manager.LifecycleListener
    public void onStart() {
        a();
    }

    @Override // com.ss.union.game.sdk.core.glide.manager.LifecycleListener
    public void onStop() {
        b();
    }
}
