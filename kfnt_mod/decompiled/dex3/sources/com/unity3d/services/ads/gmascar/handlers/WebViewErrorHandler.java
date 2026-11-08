package com.unity3d.services.ads.gmascar.handlers;

import com.unity3d.scar.adapter.common.IAdsErrorHandler;
import com.unity3d.scar.adapter.common.WebViewAdsError;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class WebViewErrorHandler implements IAdsErrorHandler<WebViewAdsError> {
    @Override // com.unity3d.scar.adapter.common.IAdsErrorHandler
    public void handleError(WebViewAdsError webViewAdsError) {
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.valueOf(webViewAdsError.getDomain()), webViewAdsError.getErrorCategory(), webViewAdsError.getErrorArguments());
    }
}
