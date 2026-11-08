package com.baidu.mobads.sdk.api;

import android.view.View;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class XAdVideoResponse implements PrerollVideoResponse {
    NativeResponse adNativeResponse;

    public XAdVideoResponse(NativeResponse nativeResponse) {
        this.adNativeResponse = nativeResponse;
    }

    @Override // com.baidu.mobads.sdk.api.PrerollVideoResponse
    public void recordImpression(View view) {
        if (this.adNativeResponse != null) {
            this.adNativeResponse.recordImpression(view);
        }
    }

    @Override // com.baidu.mobads.sdk.api.PrerollVideoResponse
    public void handleClick(View view) {
        if (this.adNativeResponse != null) {
            this.adNativeResponse.handleClick(view);
        }
    }

    @Override // com.baidu.mobads.sdk.api.PrerollVideoResponse
    public void handleClick(View view, int i) {
        if (this.adNativeResponse != null) {
            this.adNativeResponse.handleClick(view, i);
        }
    }

    @Override // com.baidu.mobads.sdk.api.PrerollVideoResponse
    public String getMaterialType() {
        if (this.adNativeResponse == null) {
            return "normal";
        }
        switch (this.adNativeResponse.getMaterialType()) {
            case NORMAL:
                if (this.adNativeResponse.getImageUrl().contains(".gif")) {
                }
                break;
        }
        return "normal";
    }

    @Override // com.baidu.mobads.sdk.api.PrerollVideoResponse
    public String getAdLogoUrl() {
        if (this.adNativeResponse != null) {
            return this.adNativeResponse.getAdLogoUrl();
        }
        return null;
    }

    @Override // com.baidu.mobads.sdk.api.PrerollVideoResponse
    public String getBaiduLogoUrl() {
        if (this.adNativeResponse != null) {
            return this.adNativeResponse.getBaiduLogoUrl();
        }
        return null;
    }

    @Override // com.baidu.mobads.sdk.api.PrerollVideoResponse
    public String getImageUrl() {
        if (this.adNativeResponse != null) {
            return this.adNativeResponse.getImageUrl();
        }
        return null;
    }

    @Override // com.baidu.mobads.sdk.api.PrerollVideoResponse
    public String getIconUrl() {
        if (this.adNativeResponse != null) {
            return this.adNativeResponse.getIconUrl();
        }
        return null;
    }

    @Override // com.baidu.mobads.sdk.api.PrerollVideoResponse
    public String getTitle() {
        if (this.adNativeResponse != null) {
            return this.adNativeResponse.getTitle();
        }
        return null;
    }

    @Override // com.baidu.mobads.sdk.api.PrerollVideoResponse
    public String getDesc() {
        if (this.adNativeResponse != null) {
            return this.adNativeResponse.getDesc();
        }
        return null;
    }

    @Override // com.baidu.mobads.sdk.api.PrerollVideoResponse
    public String getVideoUrl() {
        if (this.adNativeResponse != null) {
            return this.adNativeResponse.getVideoUrl();
        }
        return null;
    }
}
