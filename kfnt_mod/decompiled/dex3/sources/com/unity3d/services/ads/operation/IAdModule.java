package com.unity3d.services.ads.operation;

import com.unity3d.services.core.request.ISDKMetricSender;
import com.unity3d.services.core.webview.bridge.IWebViewBridgeInvoker;
import com.unity3d.services.core.webview.bridge.IWebViewBridgeSharedObjectStore;
import com.unity3d.services.core.webview.bridge.IWebViewSharedObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IAdModule<T extends IWebViewSharedObject, T2> extends IWebViewBridgeSharedObjectStore<T> {
    void executeAdOperation(IWebViewBridgeInvoker iWebViewBridgeInvoker, T2 t2);

    ISDKMetricSender getMetricSender();
}
