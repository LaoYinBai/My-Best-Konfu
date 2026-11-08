package com.unity3d.services.ads.api;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.ads.mediation.IUnityAdsExtendedListener;
import com.unity3d.ads.properties.AdsProperties;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Listener {
    @WebViewExposed
    public static void sendReadyEvent(final String str, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Utilities.runOnUiThread(new Runnable() { // from class: com.unity3d.services.ads.api.Listener.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IUnityAdsListener> it = AdsProperties.getListeners().iterator();
                while (it.hasNext()) {
                    it.next().onUnityAdsReady(str);
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendStartEvent(final String str, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Utilities.runOnUiThread(new Runnable() { // from class: com.unity3d.services.ads.api.Listener.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IUnityAdsListener> it = AdsProperties.getListeners().iterator();
                while (it.hasNext()) {
                    it.next().onUnityAdsStart(str);
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendFinishEvent(final String str, final String str2, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Utilities.runOnUiThread(new Runnable() { // from class: com.unity3d.services.ads.api.Listener.3
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IUnityAdsListener> it = AdsProperties.getListeners().iterator();
                while (it.hasNext()) {
                    it.next().onUnityAdsFinish(str, UnityAds.FinishState.valueOf(str2));
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendClickEvent(final String str, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Utilities.runOnUiThread(new Runnable() { // from class: com.unity3d.services.ads.api.Listener.4
            @Override // java.lang.Runnable
            public void run() {
                for (IUnityAdsListener iUnityAdsListener : AdsProperties.getListeners()) {
                    if (iUnityAdsListener instanceof IUnityAdsExtendedListener) {
                        ((IUnityAdsExtendedListener) iUnityAdsListener).onUnityAdsClick(str);
                    }
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendPlacementStateChangedEvent(final String str, final String str2, final String str3, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Utilities.runOnUiThread(new Runnable() { // from class: com.unity3d.services.ads.api.Listener.5
            @Override // java.lang.Runnable
            public void run() {
                for (IUnityAdsListener iUnityAdsListener : AdsProperties.getListeners()) {
                    if (iUnityAdsListener instanceof IUnityAdsExtendedListener) {
                        ((IUnityAdsExtendedListener) iUnityAdsListener).onUnityAdsPlacementStateChanged(str, UnityAds.PlacementState.valueOf(str2), UnityAds.PlacementState.valueOf(str3));
                    }
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void sendErrorEvent(final String str, final String str2, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Utilities.runOnUiThread(new Runnable() { // from class: com.unity3d.services.ads.api.Listener.6
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IUnityAdsListener> it = AdsProperties.getListeners().iterator();
                while (it.hasNext()) {
                    it.next().onUnityAdsError(UnityAds.UnityAdsError.valueOf(str), str2);
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }
}
