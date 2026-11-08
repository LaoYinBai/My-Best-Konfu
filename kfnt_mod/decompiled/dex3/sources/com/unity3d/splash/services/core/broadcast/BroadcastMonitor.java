package com.unity3d.splash.services.core.broadcast;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.unity3d.splash.services.core.properties.ClientProperties;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BroadcastMonitor {
    private static Map _eventReceivers;

    public static void addBroadcastListener(String str, String str2, String[] strArr) {
        removeBroadcastListener(str);
        IntentFilter intentFilter = new IntentFilter();
        for (String str3 : strArr) {
            intentFilter.addAction(str3);
        }
        if (str2 != null) {
            intentFilter.addDataScheme(str2);
        }
        if (_eventReceivers == null) {
            _eventReceivers = new HashMap();
        }
        BroadcastEventReceiver broadcastEventReceiver = new BroadcastEventReceiver(str);
        _eventReceivers.put(str, broadcastEventReceiver);
        ClientProperties.getApplicationContext().registerReceiver(broadcastEventReceiver, intentFilter);
    }

    public static void removeAllBroadcastListeners() {
        if (_eventReceivers != null) {
            Iterator it = _eventReceivers.keySet().iterator();
            while (it.hasNext()) {
                ClientProperties.getApplicationContext().unregisterReceiver((BroadcastReceiver) _eventReceivers.get((String) it.next()));
            }
            _eventReceivers = null;
        }
    }

    public static void removeBroadcastListener(String str) {
        if (_eventReceivers == null || !_eventReceivers.containsKey(str)) {
            return;
        }
        ClientProperties.getApplicationContext().unregisterReceiver((BroadcastReceiver) _eventReceivers.get(str));
        _eventReceivers.remove(str);
    }
}
