package com.unity3d.services.ads.operation.show;

import com.unity3d.ads.UnityAds;
import com.unity3d.services.core.request.SDKMetricEvents;
import com.unity3d.services.core.webview.bridge.IWebViewBridgeInvoker;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ShowModuleDecoratorTimeout extends ShowModuleDecorator {
    private static String errorMsgTimeout = "[UnityAds] Timeout while trying to show ";
    private ExecutorService _executorService;

    public ShowModuleDecoratorTimeout(IShowModule iShowModule) {
        super(iShowModule);
        this._executorService = Executors.newSingleThreadExecutor();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.unity3d.services.ads.operation.show.ShowModuleDecorator, com.unity3d.services.ads.operation.IAdModule
    public void executeAdOperation(IWebViewBridgeInvoker iWebViewBridgeInvoker, ShowOperationState showOperationState) {
        startShowTimeout(showOperationState);
        super.executeAdOperation(iWebViewBridgeInvoker, showOperationState);
    }

    private void startShowTimeout(final ShowOperationState showOperationState) {
        this._executorService.submit(new Runnable() { // from class: com.unity3d.services.ads.operation.show.ShowModuleDecoratorTimeout.1
            @Override // java.lang.Runnable
            public void run() {
                if (showOperationState.timeoutCV.block(showOperationState.configuration.getShowTimeout())) {
                    return;
                }
                ShowModuleDecoratorTimeout.this.onOperationTimeout(showOperationState, UnityAds.UnityAdsShowError.INTERNAL_ERROR, ShowModuleDecoratorTimeout.errorMsgTimeout + showOperationState.placementId);
            }
        });
    }

    @Override // com.unity3d.services.ads.operation.show.ShowModuleDecorator, com.unity3d.services.ads.operation.show.IShowModule
    public void onUnityAdsShowConsent(String str) {
        releaseOperationTimeoutLock(str);
        super.onUnityAdsShowConsent(str);
    }

    @Override // com.unity3d.services.ads.operation.show.ShowModuleDecorator, com.unity3d.services.ads.operation.show.IShowModule
    public void onUnityAdsShowFailure(String str, UnityAds.UnityAdsShowError unityAdsShowError, String str2) {
        releaseOperationTimeoutLock(str);
        super.onUnityAdsShowFailure(str, unityAdsShowError, str2);
    }

    @Override // com.unity3d.services.ads.operation.show.ShowModuleDecorator, com.unity3d.services.ads.operation.show.IShowModule
    public void onUnityAdsShowStart(String str) {
        releaseOperationTimeoutLock(str);
        super.onUnityAdsShowStart(str);
    }

    private void releaseOperationTimeoutLock(String str) {
        IShowOperation iShowOperation = get(str);
        if (iShowOperation == null || iShowOperation.getShowOperationState() == null || iShowOperation.getShowOperationState().timeoutCV == null) {
            return;
        }
        iShowOperation.getShowOperationState().timeoutCV.open();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onOperationTimeout(ShowOperationState showOperationState, UnityAds.UnityAdsShowError unityAdsShowError, String str) {
        remove(showOperationState.id);
        showOperationState.listener.onUnityAdsShowFailure(showOperationState.placementId, unityAdsShowError, str);
        getMetricSender().SendSDKMetricEvent(SDKMetricEvents.native_show_timeout_error);
    }
}
