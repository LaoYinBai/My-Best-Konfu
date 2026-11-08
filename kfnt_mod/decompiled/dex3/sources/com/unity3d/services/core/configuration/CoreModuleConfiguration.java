package com.unity3d.services.core.configuration;

import com.unity3d.services.core.api.Broadcast;
import com.unity3d.services.core.api.Cache;
import com.unity3d.services.core.api.ClassDetection;
import com.unity3d.services.core.api.Connectivity;
import com.unity3d.services.core.api.DeviceInfo;
import com.unity3d.services.core.api.Intent;
import com.unity3d.services.core.api.Lifecycle;
import com.unity3d.services.core.api.Permissions;
import com.unity3d.services.core.api.Preferences;
import com.unity3d.services.core.api.Request;
import com.unity3d.services.core.api.Resolve;
import com.unity3d.services.core.api.Sdk;
import com.unity3d.services.core.api.SensorInfo;
import com.unity3d.services.core.api.Storage;
import com.unity3d.services.core.broadcast.BroadcastMonitor;
import com.unity3d.services.core.cache.CacheThread;
import com.unity3d.services.core.connectivity.ConnectivityMonitor;
import com.unity3d.services.core.device.AdvertisingId;
import com.unity3d.services.core.device.OpenAdvertisingId;
import com.unity3d.services.core.device.StorageManager;
import com.unity3d.services.core.device.VolumeChange;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.properties.SdkProperties;
import com.unity3d.services.core.request.SDKMetrics;
import com.unity3d.services.core.request.WebRequestThread;
import java.lang.reflect.InvocationTargetException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CoreModuleConfiguration implements IModuleConfiguration {
    @Override // com.unity3d.services.core.configuration.IModuleConfiguration
    public Class[] getWebAppApiClassList() {
        return new Class[]{Broadcast.class, Cache.class, Connectivity.class, DeviceInfo.class, ClassDetection.class, Storage.class, Sdk.class, Request.class, Resolve.class, Intent.class, Lifecycle.class, Preferences.class, SensorInfo.class, Permissions.class};
    }

    @Override // com.unity3d.services.core.configuration.IModuleConfiguration
    public boolean resetState(Configuration configuration) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        SDKMetrics.setConfiguration(configuration);
        BroadcastMonitor.removeAllBroadcastListeners();
        CacheThread.cancel();
        WebRequestThread.cancel();
        ConnectivityMonitor.stopAll();
        StorageManager.init(ClientProperties.getApplicationContext());
        AdvertisingId.init(ClientProperties.getApplicationContext());
        OpenAdvertisingId.init(ClientProperties.getApplicationContext());
        VolumeChange.clearAllListeners();
        return true;
    }

    @Override // com.unity3d.services.core.configuration.IModuleConfiguration
    public boolean initModuleState(Configuration configuration) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        SDKMetrics.setConfiguration(configuration);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0028  */
    @Override // com.unity3d.services.core.configuration.IModuleConfiguration
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean initErrorState(com.unity3d.services.core.configuration.Configuration r4, java.lang.String r5, final java.lang.String r6) throws java.lang.IllegalAccessException, java.lang.NoSuchMethodException, java.lang.SecurityException, java.lang.IllegalArgumentException, java.lang.reflect.InvocationTargetException {
        /*
            r3 = this;
            com.unity3d.services.core.request.SDKMetrics.setConfiguration(r4)
            int r4 = r5.hashCode()
            r0 = -1874595343(0xffffffff9043f1f1, float:-3.8643354E-29)
            r1 = 1
            r2 = 0
            if (r4 == r0) goto L1e
            r0 = -929949577(0xffffffffc8921877, float:-299203.72)
            if (r4 == r0) goto L14
            goto L28
        L14:
            java.lang.String r4 = "init modules"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L28
            r4 = 1
            goto L29
        L1e:
            java.lang.String r4 = "create webapp"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L28
            r4 = 0
            goto L29
        L28:
            r4 = -1
        L29:
            switch(r4) {
                case 0: goto L34;
                case 1: goto L31;
                default: goto L2c;
            }
        L2c:
            java.lang.String r6 = "Unity Ads failed to initialize due to internal error"
            com.unity3d.ads.UnityAds$UnityAdsInitializationError r4 = com.unity3d.ads.UnityAds.UnityAdsInitializationError.INTERNAL_ERROR
            goto L36
        L31:
            com.unity3d.ads.UnityAds$UnityAdsInitializationError r4 = com.unity3d.ads.UnityAds.UnityAdsInitializationError.AD_BLOCKER_DETECTED
            goto L36
        L34:
            com.unity3d.ads.UnityAds$UnityAdsInitializationError r4 = com.unity3d.ads.UnityAds.UnityAdsInitializationError.INTERNAL_ERROR
        L36:
            com.unity3d.services.core.configuration.InitializationNotificationCenter r5 = com.unity3d.services.core.configuration.InitializationNotificationCenter.getInstance()
            r5.triggerOnSdkInitializationFailed(r6, r2)
            com.unity3d.services.core.configuration.CoreModuleConfiguration$1 r5 = new com.unity3d.services.core.configuration.CoreModuleConfiguration$1
            r5.<init>()
            com.unity3d.services.core.misc.Utilities.runOnUiThread(r5)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unity3d.services.core.configuration.CoreModuleConfiguration.initErrorState(com.unity3d.services.core.configuration.Configuration, java.lang.String, java.lang.String):boolean");
    }

    @Override // com.unity3d.services.core.configuration.IModuleConfiguration
    public boolean initCompleteState(Configuration configuration) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        SDKMetrics.setConfiguration(configuration);
        InitializationNotificationCenter.getInstance().triggerOnSdkInitialized();
        Utilities.runOnUiThread(new Runnable() { // from class: com.unity3d.services.core.configuration.CoreModuleConfiguration.2
            @Override // java.lang.Runnable
            public void run() {
                SdkProperties.notifyInitializationComplete();
            }
        });
        return true;
    }
}
