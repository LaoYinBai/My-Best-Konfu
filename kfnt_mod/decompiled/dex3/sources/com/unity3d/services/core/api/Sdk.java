package com.unity3d.services.core.api;

import com.unity3d.services.core.configuration.InitializeThread;
import com.unity3d.services.core.device.Device;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.properties.SdkProperties;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import java.lang.reflect.InvocationTargetException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Sdk {
    @WebViewExposed
    public static void loadComplete(WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        DeviceLog.debug("Web Application loaded");
        WebViewApp.getCurrentApp().setWebAppLoaded(true);
        Object[] objArr = new Object[16];
        objArr[0] = ClientProperties.getGameId();
        objArr[1] = Boolean.valueOf(SdkProperties.isTestMode());
        objArr[2] = ClientProperties.getAppName();
        objArr[3] = ClientProperties.getAppVersion();
        objArr[4] = Integer.valueOf(SdkProperties.getVersionCode());
        objArr[5] = SdkProperties.getVersionName();
        objArr[6] = Boolean.valueOf(ClientProperties.isAppDebuggable());
        objArr[7] = SdkProperties.getConfigUrl();
        objArr[8] = WebViewApp.getCurrentApp().getConfiguration().getWebViewUrl();
        objArr[9] = WebViewApp.getCurrentApp().getConfiguration().getWebViewHash();
        objArr[10] = WebViewApp.getCurrentApp().getConfiguration().getWebViewVersion();
        objArr[11] = Long.valueOf(SdkProperties.getInitializationTime());
        objArr[12] = Boolean.valueOf(SdkProperties.isReinitialized());
        objArr[13] = Boolean.valueOf(SdkProperties.isPerPlacementLoadEnabled());
        objArr[14] = Boolean.valueOf(SdkProperties.getLatestConfiguration() != null);
        objArr[15] = Long.valueOf(Device.getElapsedRealtime());
        webViewCallback.invoke(objArr);
    }

    @WebViewExposed
    public static void initComplete(WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        DeviceLog.debug("Web Application initialized");
        SdkProperties.setInitialized(true);
        WebViewApp.getCurrentApp().setWebAppInitialized(true);
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void initError(String str, Integer num, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        WebViewApp.getCurrentApp().setWebAppFailureMessage(str);
        WebViewApp.getCurrentApp().setWebAppFailureCode(num.intValue());
        WebViewApp.getCurrentApp().setWebAppInitialized(false);
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void setDebugMode(Boolean bool, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        SdkProperties.setDebugMode(bool.booleanValue());
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void getDebugMode(WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        webViewCallback.invoke(Boolean.valueOf(SdkProperties.getDebugMode()));
    }

    @WebViewExposed
    public static void logError(String str, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        DeviceLog.error(str);
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void logWarning(String str, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        DeviceLog.warning(str);
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void logInfo(String str, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        DeviceLog.info(str);
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void logDebug(String str, WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        DeviceLog.debug(str);
        webViewCallback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void reinitialize(WebViewCallback webViewCallback) {
        SdkProperties.setReinitialized(true);
        InitializeThread.initialize(WebViewApp.getCurrentApp().getConfiguration());
    }

    @WebViewExposed
    public static void downloadLatestWebView(WebViewCallback webViewCallback) throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        DeviceLog.debug("Unity Ads init: WebView called download");
        webViewCallback.invoke(Integer.valueOf(InitializeThread.downloadLatestWebView().getValue()));
    }
}
