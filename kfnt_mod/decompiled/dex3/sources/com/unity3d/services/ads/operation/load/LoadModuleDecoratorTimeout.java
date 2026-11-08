package com.unity3d.services.ads.operation.load;

import com.unity3d.ads.UnityAds;
import com.unity3d.services.core.request.SDKMetricEvents;
import com.unity3d.services.core.webview.bridge.IWebViewBridgeInvoker;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LoadModuleDecoratorTimeout extends LoadModuleDecorator {
    private static String errorMsgTimeoutLoading = "[UnityAds] Timeout while loading ";
    private ExecutorService _executorService;

    public LoadModuleDecoratorTimeout(ILoadModule iLoadModule) {
        super(iLoadModule);
        this._executorService = Executors.newCachedThreadPool();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.unity3d.services.ads.operation.load.LoadModuleDecorator, com.unity3d.services.ads.operation.IAdModule
    public void executeAdOperation(IWebViewBridgeInvoker iWebViewBridgeInvoker, LoadOperationState loadOperationState) {
        startLoadTimeout(loadOperationState);
        super.executeAdOperation(iWebViewBridgeInvoker, loadOperationState);
    }

    private void startLoadTimeout(final LoadOperationState loadOperationState) {
        this._executorService.submit(new Runnable() { // from class: com.unity3d.services.ads.operation.load.LoadModuleDecoratorTimeout.1
            @Override // java.lang.Runnable
            public void run() {
                if (loadOperationState.timeoutCV.block(loadOperationState.configuration.getLoadTimeout())) {
                    return;
                }
                LoadModuleDecoratorTimeout.this.onOperationTimeout(loadOperationState);
            }
        });
    }

    @Override // com.unity3d.services.ads.operation.load.LoadModuleDecorator, com.unity3d.services.ads.operation.load.ILoadModule
    public void onUnityAdsAdLoaded(String str) {
        releaseOperationTimeoutLock(str);
        super.onUnityAdsAdLoaded(str);
    }

    @Override // com.unity3d.services.ads.operation.load.LoadModuleDecorator, com.unity3d.services.ads.operation.load.ILoadModule
    public void onUnityAdsFailedToLoad(String str, UnityAds.UnityAdsLoadError unityAdsLoadError, String str2) {
        releaseOperationTimeoutLock(str);
        super.onUnityAdsFailedToLoad(str, unityAdsLoadError, str2);
    }

    private void releaseOperationTimeoutLock(String str) {
        ILoadOperation iLoadOperation = get(str);
        if (iLoadOperation == null || iLoadOperation.getLoadOperationState() == null || iLoadOperation.getLoadOperationState().timeoutCV == null) {
            return;
        }
        iLoadOperation.getLoadOperationState().timeoutCV.open();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onOperationTimeout(LoadOperationState loadOperationState) {
        remove(loadOperationState.id);
        loadOperationState.listener.onUnityAdsFailedToLoad(loadOperationState.placementId, UnityAds.UnityAdsLoadError.TIMEOUT, errorMsgTimeoutLoading + loadOperationState.placementId);
        getMetricSender().SendSDKMetricEvent(SDKMetricEvents.native_load_timeout_error);
    }
}
