package com.mbridge.msdk.mbdownload;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.mbdownload.manager.ADownloadManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DownloadNetWorkChangeReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            s.d("DownloadNetWorkChangeReceiver", "=======reConn:" + intent.getBooleanExtra("FAILOVER_CONNECTION", false) + "--noConn:" + intent.getBooleanExtra("EXTRA_NO_CONNECTIVITY", false));
            if (com.mbridge.msdk.foundation.same.a.g) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                ADownloadManager aDownloadManager = ADownloadManager.getInstance();
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                        s.d("DownloadNetWorkChangeReceiver", "=======networkInfo:Connected");
                        aDownloadManager.reStartAllTask();
                        return;
                    } else {
                        s.d("DownloadNetWorkChangeReceiver", "=======networkInfo:not Connected");
                        aDownloadManager.cancelAllTask();
                        return;
                    }
                }
                aDownloadManager.cancelAllTask();
            }
        }
    }
}
