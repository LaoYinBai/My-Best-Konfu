package com.alibaba.sdk.android.httpdns;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class l {

    /* renamed from: a, reason: collision with root package name */
    private static Context f2378a = null;
    private static String d = null;

    /* renamed from: d, reason: collision with other field name */
    static boolean f24d = false;

    /* JADX INFO: Access modifiers changed from: private */
    public static String c() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) f2378a.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return "None_Network";
            }
            String typeName = activeNetworkInfo.getTypeName();
            i.d("[detectCurrentNetwork] - Network name:" + typeName + " subType name: " + activeNetworkInfo.getSubtypeName());
            return typeName == null ? "None_Network" : typeName;
        } catch (Exception e) {
            e.printStackTrace();
            return "None_Network";
        }
    }

    public static void setContext(Context context) {
        if (context == null) {
            throw new IllegalStateException("Context can't be null");
        }
        f2378a = context;
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.alibaba.sdk.android.httpdns.l.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                try {
                    if (!isInitialStickyBroadcast() && "android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                        com.alibaba.sdk.android.httpdns.b.b.b(context2);
                        String strC = l.c();
                        if (strC != "None_Network" && !strC.equalsIgnoreCase(l.d)) {
                            i.d("[BroadcastReceiver.onReceive] - Network state changed");
                            ArrayList<String> arrayListM17a = d.a().m17a();
                            d.a().clear();
                            d.a().m18a();
                            if (l.f24d && HttpDns.instance != null) {
                                i.d("[BroadcastReceiver.onReceive] - refresh host");
                                HttpDns.instance.setPreResolveHosts(arrayListM17a);
                            }
                        }
                        String unused = l.d = strC;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            f2378a.registerReceiver(broadcastReceiver, intentFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
