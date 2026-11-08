package c.a.a.a.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import c.a.a.a.a.ah;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ae {

    /* renamed from: a, reason: collision with root package name */
    private List<ah.b> f2050a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    private c f2051b = null;

    /* renamed from: c, reason: collision with root package name */
    private b f2052c = null;

    private class b extends BroadcastReceiver {
        private b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ae aeVar;
            boolean z;
            if (ak.a(context)) {
                aeVar = ae.this;
                z = true;
            } else {
                aeVar = ae.this;
                z = false;
            }
            aeVar.a(z);
        }
    }

    @RequiresApi(21)
    private class c extends ConnectivityManager.NetworkCallback {
        private c() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(@NonNull Network network) {
            ae.this.a(true);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(@NonNull Network network) {
            ae.this.a(false);
        }
    }

    public ae(Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            b(context);
        } else {
            a(context);
        }
    }

    private void a(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        b bVar = new b();
        this.f2052c = bVar;
        context.registerReceiver(bVar, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        com.bytedance.gameprotect.p.a(z ? 2L : 3L);
        for (ah.b bVar : this.f2050a) {
            if (z) {
                bVar.a();
            } else {
                bVar.b();
            }
        }
    }

    @RequiresApi(api = 21)
    private void b(Context context) {
        this.f2051b = new c();
        NetworkRequest networkRequestBuild = new NetworkRequest.Builder().build();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            try {
                connectivityManager.registerNetworkCallback(networkRequestBuild, this.f2051b);
            } catch (Exception unused) {
            }
        }
    }
}
