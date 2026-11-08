package com.kwad.sdk.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.kwad.sdk.KsAdSDKImpl;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class NetworkMonitor {
    private static volatile boolean a = false;
    private List<WeakReference<a>> b;
    private boolean c;
    private final BroadcastReceiver d;

    private enum Holder {
        INSTANCE;

        private NetworkMonitor mInstance = new NetworkMonitor();

        Holder() {
        }

        NetworkMonitor getInstance() {
            return this.mInstance;
        }
    }

    public enum NetworkState {
        NETWORK_NONE,
        NETWORK_MOBILE,
        NETWORK_WIFI
    }

    public interface a {
        void a(NetworkState networkState);
    }

    private NetworkMonitor() {
        this.b = Collections.synchronizedList(new LinkedList());
        this.c = false;
        this.d = new BroadcastReceiver() { // from class: com.kwad.sdk.core.NetworkMonitor.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(@NonNull Context context, Intent intent) {
                ConnectivityManager connectivityManager;
                NetworkMonitor networkMonitor;
                NetworkState networkState;
                try {
                    if ((ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_NETWORK_STATE") == 0) && (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) != null) {
                        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                            networkMonitor = NetworkMonitor.this;
                            networkState = NetworkState.NETWORK_NONE;
                        } else if (1 == activeNetworkInfo.getType()) {
                            networkMonitor = NetworkMonitor.this;
                            networkState = NetworkState.NETWORK_WIFI;
                        } else if (activeNetworkInfo.getType() == 0) {
                            networkMonitor = NetworkMonitor.this;
                            networkState = NetworkState.NETWORK_MOBILE;
                        } else {
                            networkMonitor = NetworkMonitor.this;
                            networkState = NetworkState.NETWORK_NONE;
                        }
                        networkMonitor.a(networkState);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        };
    }

    public static NetworkMonitor a() {
        return Holder.INSTANCE.getInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(NetworkState networkState) {
        a aVar;
        Iterator<WeakReference<a>> it = this.b.iterator();
        while (it.hasNext()) {
            WeakReference<a> next = it.next();
            if (next == null || (aVar = next.get()) == null) {
                it.remove();
            } else {
                aVar.a(networkState);
            }
        }
    }

    private synchronized void b() {
        if (a) {
            return;
        }
        Context context = KsAdSDKImpl.get().getContext();
        if (context == null) {
            return;
        }
        context.getApplicationContext().registerReceiver(this.d, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        a = true;
    }

    public void a(@NonNull a aVar) {
        b();
        this.b.add(new WeakReference<>(aVar));
    }
}
