package com.unity3d.services.ads.operation.load;

import com.unity3d.ads.UnityAds;
import com.unity3d.services.core.configuration.IInitializationListener;
import com.unity3d.services.core.configuration.IInitializationNotificationCenter;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.properties.SdkProperties;
import com.unity3d.services.core.webview.bridge.IWebViewBridgeInvoker;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LoadModuleDecoratorInitializationBuffer extends LoadModuleDecorator implements IInitializationListener {
    private static String errorMsgInitializationFailed = "[UnityAds] SDK Initialization Failed";
    private static String errorMsgInitializationFailure = "[UnityAds] SDK Initialization Failure";
    private ConcurrentHashMap<LoadOperationState, IWebViewBridgeInvoker> _queuedLoadEvents;

    public LoadModuleDecoratorInitializationBuffer(ILoadModule iLoadModule, IInitializationNotificationCenter iInitializationNotificationCenter) {
        super(iLoadModule);
        iInitializationNotificationCenter.addListener(this);
        this._queuedLoadEvents = new ConcurrentHashMap<>();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.unity3d.services.ads.operation.load.LoadModuleDecorator, com.unity3d.services.ads.operation.IAdModule
    public void executeAdOperation(IWebViewBridgeInvoker iWebViewBridgeInvoker, LoadOperationState loadOperationState) {
        if (loadOperationState == null) {
        }
        switch (SdkProperties.getCurrentInitializationState()) {
            case INITIALIZED_SUCCESSFULLY:
                super.executeAdOperation(iWebViewBridgeInvoker, loadOperationState);
                break;
            case INITIALIZED_FAILED:
                sendOnUnityAdsFailedToLoad(loadOperationState, UnityAds.UnityAdsLoadError.INITIALIZE_FAILED, errorMsgInitializationFailed);
                break;
            default:
                this._queuedLoadEvents.put(loadOperationState, iWebViewBridgeInvoker);
                break;
        }
    }

    @Override // com.unity3d.services.core.configuration.IInitializationListener
    public synchronized void onSdkInitialized() {
        for (Map.Entry<LoadOperationState, IWebViewBridgeInvoker> entry : this._queuedLoadEvents.entrySet()) {
            super.executeAdOperation(entry.getValue(), entry.getKey());
        }
        this._queuedLoadEvents.clear();
    }

    @Override // com.unity3d.services.core.configuration.IInitializationListener
    public synchronized void onSdkInitializationFailed(String str, int i) {
        Iterator<LoadOperationState> it = this._queuedLoadEvents.keySet().iterator();
        while (it.hasNext()) {
            sendOnUnityAdsFailedToLoad(it.next(), UnityAds.UnityAdsLoadError.INITIALIZE_FAILED, errorMsgInitializationFailure);
        }
        this._queuedLoadEvents.clear();
    }

    private void sendOnUnityAdsFailedToLoad(final LoadOperationState loadOperationState, final UnityAds.UnityAdsLoadError unityAdsLoadError, final String str) {
        Utilities.runOnUiThread(new Runnable() { // from class: com.unity3d.services.ads.operation.load.LoadModuleDecoratorInitializationBuffer.1
            @Override // java.lang.Runnable
            public void run() {
                loadOperationState.listener.onUnityAdsFailedToLoad(loadOperationState.placementId, unityAdsLoadError, str);
            }
        });
    }
}
