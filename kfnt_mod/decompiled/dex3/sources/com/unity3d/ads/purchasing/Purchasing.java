package com.unity3d.ads.purchasing;

import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Purchasing {

    public enum UnityAdsPurchasingEvent {
        COMMAND,
        VERSION,
        CATALOG,
        INITIALIZATION,
        EVENT
    }

    public static void initialize(IPurchasing iPurchasing) {
        com.unity3d.services.ads.api.Purchasing.setPurchasingInterface(iPurchasing);
    }

    public static void dispatchReturnEvent(int i, String str) {
        WebViewApp currentApp = WebViewApp.getCurrentApp();
        if (currentApp == null || !currentApp.isWebAppLoaded()) {
            return;
        }
        currentApp.sendEvent(WebViewEventCategory.PURCHASING, UnityAdsPurchasingEvent.values()[i], str);
    }
}
