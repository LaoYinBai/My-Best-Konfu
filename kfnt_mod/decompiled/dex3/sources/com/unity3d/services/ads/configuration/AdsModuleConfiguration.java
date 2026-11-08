package com.unity3d.services.ads.configuration;

import android.os.ConditionVariable;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.properties.AdsProperties;
import com.unity3d.services.ads.UnityAdsImplementation;
import com.unity3d.services.ads.adunit.AdUnitOpen;
import com.unity3d.services.ads.adunit.VideoPlayerHandler;
import com.unity3d.services.ads.adunit.WebPlayerHandler;
import com.unity3d.services.ads.adunit.WebViewHandler;
import com.unity3d.services.ads.api.AdUnit;
import com.unity3d.services.ads.api.GMAScar;
import com.unity3d.services.ads.api.Listener;
import com.unity3d.services.ads.api.Load;
import com.unity3d.services.ads.api.Placement;
import com.unity3d.services.ads.api.Purchasing;
import com.unity3d.services.ads.api.Show;
import com.unity3d.services.ads.api.Token;
import com.unity3d.services.ads.api.VideoPlayer;
import com.unity3d.services.ads.api.WebPlayer;
import com.unity3d.services.ads.token.TokenStorage;
import com.unity3d.services.core.configuration.Configuration;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AdsModuleConfiguration implements IAdsModuleConfiguration {
    private InetAddress _address;

    @Override // com.unity3d.services.core.configuration.IModuleConfiguration
    public Class[] getWebAppApiClassList() {
        return new Class[]{AdUnit.class, Listener.class, VideoPlayer.class, Placement.class, WebPlayer.class, Purchasing.class, Load.class, Show.class, Token.class, GMAScar.class};
    }

    @Override // com.unity3d.services.core.configuration.IModuleConfiguration
    public boolean resetState(Configuration configuration) {
        com.unity3d.services.ads.placement.Placement.reset();
        AdUnitOpen.setConfiguration(configuration);
        UnityAdsImplementation.setConfiguration(configuration);
        TokenStorage.deleteTokens();
        return true;
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [com.unity3d.services.ads.configuration.AdsModuleConfiguration$1] */
    @Override // com.unity3d.services.core.configuration.IModuleConfiguration
    public boolean initModuleState(Configuration configuration) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        DeviceLog.debug("Unity Ads init: checking for ad blockers");
        try {
            final String host = new URL(configuration.getConfigUrl()).getHost();
            final ConditionVariable conditionVariable = new ConditionVariable();
            new Thread() { // from class: com.unity3d.services.ads.configuration.AdsModuleConfiguration.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
                    try {
                        AdsModuleConfiguration.this._address = InetAddress.getByName(host);
                        conditionVariable.open();
                    } catch (Exception e) {
                        DeviceLog.exception("Couldn't get address. Host: " + host, e);
                        conditionVariable.open();
                    }
                }
            }.start();
            if (conditionVariable.block(2000L) && this._address != null && this._address.isLoopbackAddress()) {
                DeviceLog.error("Unity Ads init: halting init because Unity Ads config resolves to loopback address (due to ad blocker?)");
                return false;
            }
            AdUnitOpen.setConfiguration(configuration);
            UnityAdsImplementation.setConfiguration(configuration);
            return true;
        } catch (MalformedURLException unused) {
            return true;
        }
    }

    @Override // com.unity3d.services.core.configuration.IModuleConfiguration
    public boolean initErrorState(Configuration configuration, String str, String str2) {
        final String str3 = "Init failed in " + str;
        Utilities.runOnUiThread(new Runnable() { // from class: com.unity3d.services.ads.configuration.AdsModuleConfiguration.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IUnityAdsListener> it = AdsProperties.getListeners().iterator();
                while (it.hasNext()) {
                    it.next().onUnityAdsError(UnityAds.UnityAdsError.INITIALIZE_FAILED, str3);
                }
            }
        });
        return true;
    }

    @Override // com.unity3d.services.core.configuration.IModuleConfiguration
    public boolean initCompleteState(Configuration configuration) {
        AdUnitOpen.setConfiguration(configuration);
        UnityAdsImplementation.setConfiguration(configuration);
        return true;
    }

    @Override // com.unity3d.services.ads.configuration.IAdsModuleConfiguration
    public Map<String, Class> getAdUnitViewHandlers() {
        HashMap map = new HashMap();
        map.put("videoplayer", VideoPlayerHandler.class);
        map.put("webplayer", WebPlayerHandler.class);
        map.put("webview", WebViewHandler.class);
        return map;
    }
}
