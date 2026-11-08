package com.bytedance.pangle.service.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ServiceInfo;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.Keep;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.d;
import com.bytedance.pangle.f;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.plugin.PluginManager;
import com.bytedance.pangle.servermanager.b;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ServiceManagerNative {
    private static volatile ServiceManagerNative sInstance;
    private final HashMap<ServiceConnection, f> serviceConn2ServiceConn = new HashMap<>();
    public HashMap<IBinder, HashMap<ServiceConnection, HashSet<ComponentName>>> process2ConnAndService = new HashMap<>();
    private HashMap<ServiceConnection, HashSet<ServiceInfo>> conn2Service = new HashMap<>();

    public static ServiceManagerNative getInstance() {
        if (sInstance == null) {
            synchronized (ServiceManagerNative.class) {
                if (sInstance == null) {
                    sInstance = new ServiceManagerNative();
                }
            }
        }
        return sInstance;
    }

    private ServiceManagerNative() {
    }

    public ServiceInfo queryServiceFromPlugin(Intent intent, String str) {
        Zeus.loadPlugin(str);
        return PluginManager.getInstance().getPlugin(str).pluginServices.get(intent.getComponent().getClassName());
    }

    public boolean bindServiceNative(Context context, Intent intent, final ServiceConnection serviceConnection, int i, String str) {
        ServiceInfo serviceInfoQueryServiceFromPlugin = queryServiceFromPlugin(intent, str);
        if (serviceInfoQueryServiceFromPlugin == null) {
            return context.bindService(intent, serviceConnection, i);
        }
        if (!this.serviceConn2ServiceConn.containsKey(serviceConnection)) {
            this.serviceConn2ServiceConn.put(serviceConnection, new f.a() { // from class: com.bytedance.pangle.service.client.ServiceManagerNative.1
                @Override // com.bytedance.pangle.f
                public final void a(ComponentName componentName, IBinder iBinder) {
                    serviceConnection.onServiceConnected(componentName, iBinder);
                }

                @Override // com.bytedance.pangle.f
                public final int a() {
                    return serviceConnection.hashCode();
                }
            });
        }
        if (this.conn2Service.get(serviceConnection) == null) {
            this.conn2Service.put(serviceConnection, new HashSet<>());
        }
        this.conn2Service.get(serviceConnection).add(serviceInfoQueryServiceFromPlugin);
        d dVarA = b.a(serviceInfoQueryServiceFromPlugin.processName);
        IBinder iBinderAsBinder = dVarA.asBinder();
        HashMap<ServiceConnection, HashSet<ComponentName>> map = this.process2ConnAndService.get(iBinderAsBinder);
        if (map == null) {
            map = new HashMap<>();
            this.process2ConnAndService.put(iBinderAsBinder, map);
        }
        HashSet<ComponentName> hashSet = map.get(serviceConnection);
        if (hashSet == null) {
            hashSet = new HashSet<>();
            map.put(serviceConnection, hashSet);
        }
        hashSet.add(intent.getComponent());
        try {
            return dVarA.a(intent, this.serviceConn2ServiceConn.get(serviceConnection), i, str);
        } catch (RemoteException e) {
            e.printStackTrace();
            ZeusLogger.e(ZeusLogger.TAG_SERVICE, "bindService failed!", e);
            return false;
        }
    }

    public void unbindServiceNative(ServiceConnection serviceConnection) {
        HashSet<ServiceInfo> hashSet = this.conn2Service.get(serviceConnection);
        if (hashSet != null) {
            Iterator<ServiceInfo> it = hashSet.iterator();
            while (it.hasNext()) {
                try {
                    b.a(it.next().processName).a(this.serviceConn2ServiceConn.get(serviceConnection));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean stopServiceNative(Context context, Intent intent, String str) {
        ServiceInfo serviceInfoQueryServiceFromPlugin = queryServiceFromPlugin(intent, str);
        if (serviceInfoQueryServiceFromPlugin == null) {
            return context.stopService(intent);
        }
        try {
            return b.a(serviceInfoQueryServiceFromPlugin.processName).b(intent, str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ComponentName startServiceNative(Context context, Intent intent, String str) {
        ServiceInfo serviceInfoQueryServiceFromPlugin = queryServiceFromPlugin(intent, str);
        if (serviceInfoQueryServiceFromPlugin == null) {
            return context.startService(intent);
        }
        try {
            return b.a(serviceInfoQueryServiceFromPlugin.processName).a(intent, str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
}
