package com.unity3d.splash.services.ads.adunit;

import android.os.ConditionVariable;
import com.sigmob.sdk.common.mta.PointCategory;
import com.unity3d.splash.services.ads.properties.AdsProperties;
import com.unity3d.splash.services.core.webview.WebViewApp;
import com.unity3d.splash.services.core.webview.bridge.CallbackStatus;
import java.lang.reflect.Method;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AdUnitOpen {
    private static ConditionVariable _waitShowStatus;

    public static synchronized boolean open(String str, JSONObject jSONObject) {
        boolean zBlock;
        Method method = AdUnitOpen.class.getMethod("showCallback", CallbackStatus.class);
        _waitShowStatus = new ConditionVariable();
        WebViewApp.getCurrentApp().invokeMethod("webview", PointCategory.SHOW, method, str, jSONObject);
        zBlock = _waitShowStatus.block(AdsProperties.getShowTimeout());
        _waitShowStatus = null;
        return zBlock;
    }

    public static void showCallback(CallbackStatus callbackStatus) {
        if (_waitShowStatus == null || !callbackStatus.equals(CallbackStatus.OK)) {
            return;
        }
        _waitShowStatus.open();
    }
}
