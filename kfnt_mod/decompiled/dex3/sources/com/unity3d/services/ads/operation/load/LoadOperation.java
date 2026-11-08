package com.unity3d.services.ads.operation.load;

import com.sigmob.sdk.common.mta.PointCategory;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.ads.operation.AdOperation;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.webview.bridge.invocation.IWebViewBridgeInvocation;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LoadOperation extends AdOperation implements ILoadOperation {
    private LoadOperationState _loadOperationState;

    public LoadOperation(LoadOperationState loadOperationState, IWebViewBridgeInvocation iWebViewBridgeInvocation) {
        super(iWebViewBridgeInvocation, PointCategory.LOAD);
        this._loadOperationState = loadOperationState;
    }

    @Override // com.unity3d.services.ads.operation.load.ILoadOperation
    public LoadOperationState getLoadOperationState() {
        return this._loadOperationState;
    }

    @Override // com.unity3d.ads.IUnityAdsLoadListener
    public void onUnityAdsAdLoaded(final String str) {
        if (this._loadOperationState == null || this._loadOperationState.listener == null || str == null) {
            return;
        }
        Utilities.runOnUiThread(new Runnable() { // from class: com.unity3d.services.ads.operation.load.LoadOperation.1
            @Override // java.lang.Runnable
            public void run() {
                LoadOperation.this._loadOperationState.listener.onUnityAdsAdLoaded(str);
            }
        });
    }

    @Override // com.unity3d.ads.IUnityAdsLoadListener
    public void onUnityAdsFailedToLoad(final String str, final UnityAds.UnityAdsLoadError unityAdsLoadError, final String str2) {
        if (this._loadOperationState == null || this._loadOperationState.listener == null || str == null) {
            return;
        }
        Utilities.runOnUiThread(new Runnable() { // from class: com.unity3d.services.ads.operation.load.LoadOperation.2
            @Override // java.lang.Runnable
            public void run() {
                LoadOperation.this._loadOperationState.listener.onUnityAdsFailedToLoad(str, unityAdsLoadError, str2);
            }
        });
    }

    @Override // com.unity3d.services.core.webview.bridge.IWebViewSharedObject
    public String getId() {
        return this._loadOperationState.id;
    }
}
