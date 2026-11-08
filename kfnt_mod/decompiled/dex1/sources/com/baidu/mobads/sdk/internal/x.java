package com.baidu.mobads.sdk.internal;

import com.baidu.mobads.sdk.api.BaiduNativeManager;
import com.baidu.mobads.sdk.api.NativeResponse;
import com.baidu.mobads.sdk.api.XAdNativeResponse;
import com.baidu.mobads.sdk.internal.e;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class x implements e.a {

    /* renamed from: a, reason: collision with root package name */
    private BaiduNativeManager.FeedAdListener f3256a;

    public x(BaiduNativeManager.FeedAdListener feedAdListener) {
        this.f3256a = feedAdListener;
    }

    @Override // com.baidu.mobads.sdk.internal.e.a
    public void a(List<NativeResponse> list) {
        if (this.f3256a != null) {
            this.f3256a.onNativeLoad(list);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.e.a
    public void a(int i, String str) {
        if (this.f3256a != null) {
            this.f3256a.onNoAd(i, str);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.e.a
    public void b(int i, String str) {
        if (this.f3256a != null) {
            this.f3256a.onNativeFail(i, str);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.e.a
    public void a(NativeResponse nativeResponse) {
        if (nativeResponse instanceof XAdNativeResponse) {
            ((XAdNativeResponse) nativeResponse).onADExposed();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.e.a
    public void a(NativeResponse nativeResponse, int i) {
        if (nativeResponse instanceof XAdNativeResponse) {
            ((XAdNativeResponse) nativeResponse).onADExposureFailed(i);
        }
    }

    @Override // com.baidu.mobads.sdk.internal.e.a
    public void b(NativeResponse nativeResponse) {
        if (this.f3256a != null && (this.f3256a instanceof BaiduNativeManager.PortraitVideoAdListener)) {
            ((BaiduNativeManager.PortraitVideoAdListener) this.f3256a).onAdClick();
        } else if (nativeResponse instanceof XAdNativeResponse) {
            ((XAdNativeResponse) nativeResponse).onAdClick();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.e.a
    public void a() {
        if (this.f3256a != null) {
            this.f3256a.onLpClosed();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.e.a
    public void b() {
        if (this.f3256a != null) {
            this.f3256a.onVideoDownloadSuccess();
        }
    }

    @Override // com.baidu.mobads.sdk.internal.e.a
    public void c() {
        if (this.f3256a != null) {
            this.f3256a.onVideoDownloadFailed();
        }
    }
}
