package com.bytedance.msdk.api.v2.ad.custom.nativeAd;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.v2.ad.custom.GMCustomAdError;
import com.bytedance.msdk.api.v2.ad.custom.GMCustomTTBaseAd;
import com.bytedance.msdk.api.v2.ad.custom.base.GMCustomAd;
import com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMNativeExpressAdListener;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class GMCustomNativeAd extends GMCustomBaseNativeAd implements IGMCustomNativeEvent {

    /* renamed from: b, reason: collision with root package name */
    private int f4406b = 0;

    /* renamed from: c, reason: collision with root package name */
    private int f4407c = 0;
    private boolean d = false;

    @Override // com.bytedance.msdk.api.v2.ad.custom.nativeAd.IGMCustomNativeEvent
    public void callNativeAdClick() {
        checkClick(new GMCustomBaseAdapter.CheckCallback() { // from class: com.bytedance.msdk.api.v2.ad.custom.nativeAd.GMCustomNativeAd.4
            @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter.CheckCallback
            public void callback() {
                if (((GMCustomAd) GMCustomNativeAd.this).f4370a == null || ((GMCustomAd) GMCustomNativeAd.this).f4370a.getGMNativeAdListener() == null) {
                    return;
                }
                ((GMCustomAd) GMCustomNativeAd.this).f4370a.getGMNativeAdListener().onAdClick();
            }
        });
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.nativeAd.IGMCustomNativeEvent
    public void callNativeAdShow() {
        checkShow(new GMCustomBaseAdapter.CheckCallback() { // from class: com.bytedance.msdk.api.v2.ad.custom.nativeAd.GMCustomNativeAd.5
            @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter.CheckCallback
            public void callback() {
                if (((GMCustomAd) GMCustomNativeAd.this).f4370a == null || ((GMCustomAd) GMCustomNativeAd.this).f4370a.getGMNativeAdListener() == null) {
                    return;
                }
                ((GMCustomAd) GMCustomNativeAd.this).f4370a.getGMNativeAdListener().onAdShow();
            }
        });
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.nativeAd.IGMCustomNativeEvent
    public void callNativeRenderFail(View view, String str, int i) {
        GMCustomTTBaseAd gMCustomTTBaseAd = this.f4370a;
        if (gMCustomTTBaseAd == null || gMCustomTTBaseAd.getGMNativeAdListener() == null) {
            return;
        }
        try {
            ((GMNativeExpressAdListener) this.f4370a.getGMNativeAdListener()).onRenderFail(view, str, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.nativeAd.IGMCustomNativeEvent
    public void callNativeRenderSuccess(float f, float f2) {
        GMCustomTTBaseAd gMCustomTTBaseAd = this.f4370a;
        if (gMCustomTTBaseAd == null || gMCustomTTBaseAd.getGMNativeAdListener() == null) {
            return;
        }
        try {
            ((GMNativeExpressAdListener) this.f4370a.getGMNativeAdListener()).onRenderSuccess(f, f2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.nativeAd.IGMCustomNativeEvent
    public void callNativeVideoCompleted() {
        GMCustomTTBaseAd gMCustomTTBaseAd = this.f4370a;
        if (gMCustomTTBaseAd == null || gMCustomTTBaseAd.getGMVideoListener() == null) {
            return;
        }
        this.f4370a.getGMVideoListener().onVideoCompleted();
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.nativeAd.IGMCustomNativeEvent
    public void callNativeVideoError(GMCustomAdError gMCustomAdError) {
        GMCustomTTBaseAd gMCustomTTBaseAd = this.f4370a;
        if (gMCustomTTBaseAd == null || gMCustomTTBaseAd.getGMVideoListener() == null) {
            return;
        }
        this.f4370a.getGMVideoListener().onVideoError(new AdError(AdError.ERROR_CODE_CUSTOM_NATIVE_VIDEO_SHOW_ERROR, AdError.getMessage(AdError.ERROR_CODE_CUSTOM_NATIVE_VIDEO_SHOW_ERROR), gMCustomAdError == null ? -1 : gMCustomAdError.getCode(), gMCustomAdError == null ? "" : gMCustomAdError.getMessage()));
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.nativeAd.IGMCustomNativeEvent
    public void callNativeVideoPause() {
        GMCustomTTBaseAd gMCustomTTBaseAd = this.f4370a;
        if (gMCustomTTBaseAd == null || gMCustomTTBaseAd.getGMVideoListener() == null) {
            return;
        }
        this.f4370a.getGMVideoListener().onVideoPause();
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.nativeAd.IGMCustomNativeEvent
    public void callNativeVideoResume() {
        GMCustomTTBaseAd gMCustomTTBaseAd = this.f4370a;
        if (gMCustomTTBaseAd == null || gMCustomTTBaseAd.getGMVideoListener() == null) {
            return;
        }
        this.f4370a.getGMVideoListener().onVideoResume();
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.nativeAd.IGMCustomNativeEvent
    public void callNativeVideoStart() {
        GMCustomTTBaseAd gMCustomTTBaseAd = this.f4370a;
        if (gMCustomTTBaseAd == null || gMCustomTTBaseAd.getGMVideoListener() == null) {
            return;
        }
        this.f4370a.getGMVideoListener().onVideoStart();
    }

    public final void checkClick(GMCustomBaseAdapter.CheckCallback checkCallback) {
        int i = this.f4407c;
        if (i >= 60) {
            Logger.e("TTMediationSDK", "自定义Adapter click方法回调次数需要小于60次");
            return;
        }
        this.f4407c = i + 1;
        if (checkCallback != null) {
            checkCallback.callback();
        }
    }

    public final void checkShow(GMCustomBaseAdapter.CheckCallback checkCallback) {
        if (!this.d) {
            Logger.e("TTMediationSDK", "自定义Adapter show方法回调必须由GroMore触发show时才会生效");
            return;
        }
        int i = this.f4406b;
        if (i >= 2) {
            Logger.e("TTMediationSDK", "自定义Adapter show方法回调次数需要小于2次");
            return;
        }
        this.f4406b = i + 1;
        if (checkCallback != null) {
            checkCallback.callback();
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.nativeAd.GMCustomBaseNativeAd
    public View getExpressView() {
        return null;
    }

    public final View getExpressViewInner() {
        try {
            return getExpressView();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomAd
    public GMCustomTTBaseAd getTTBaseAd() {
        this.f4370a.setTitle(getTitle());
        this.f4370a.setAdDescription(getDescription());
        this.f4370a.setIconUrl(getIconUrl());
        this.f4370a.setImageUrl(getImageUrl());
        this.f4370a.setImageWidth(getImageWidth());
        this.f4370a.setImageHeight(getImageHeight());
        this.f4370a.setVideoWidth(getVideoWidth());
        this.f4370a.setVideoHeight(getVideoHeight());
        this.f4370a.setActionText(getActionText());
        this.f4370a.setPackageName(getPackageName());
        this.f4370a.setRating(getStarRating());
        this.f4370a.setImages(getImageList());
        this.f4370a.setSource(getSource());
        this.f4370a.setImageMode(getAdImageMode());
        this.f4370a.setInteractionType(getInteractionType());
        if (getNativeAdAppInfo() != null) {
            this.f4370a.setAppName(getNativeAdAppInfo().getAppName());
            this.f4370a.setAuthorName(getNativeAdAppInfo().getAuthorName());
            this.f4370a.setPackageSizeBytes(getNativeAdAppInfo().getPackageSizeBytes());
            this.f4370a.setPermissionsUrl(getNativeAdAppInfo().getPermissionsUrl());
            this.f4370a.setPrivacyAgreement(getNativeAdAppInfo().getPrivacyAgreement());
            this.f4370a.setVersionName(getNativeAdAppInfo().getVersionName());
        }
        this.f4370a.setMediaExtraInfo(getMediaExtraInfo());
        this.f4370a.setCustomNativeConvert(new IGMCustomNativeConvert() { // from class: com.bytedance.msdk.api.v2.ad.custom.nativeAd.GMCustomNativeAd.1
            @Override // com.bytedance.msdk.api.v2.ad.custom.nativeAd.IGMCustomNativeConvert
            public void registerViewForInteractionInner(@NonNull ViewGroup viewGroup, List<View> list, List<View> list2, GMViewBinder gMViewBinder) {
                GMCustomNativeAd.this.registerViewForInteractionInner(viewGroup, list, list2, gMViewBinder);
            }

            @Override // com.bytedance.msdk.api.v2.ad.custom.nativeAd.IGMCustomNativeConvert
            public void unregisterViewInner() {
                GMCustomNativeAd.this.unregisterViewInner();
            }
        });
        this.f4370a.setCustomNativeExpressConvert(new IGMCustomNativeExpressConvert() { // from class: com.bytedance.msdk.api.v2.ad.custom.nativeAd.GMCustomNativeAd.2
            @Override // com.bytedance.msdk.api.v2.ad.custom.nativeAd.IGMCustomNativeExpressConvert
            public View getExpressViewInner() {
                return GMCustomNativeAd.this.getExpressViewInner();
            }

            @Override // com.bytedance.msdk.api.v2.ad.custom.nativeAd.IGMCustomNativeExpressConvert
            public void renderInner() {
                GMCustomNativeAd.this.renderInner();
            }
        });
        this.f4370a.setCustomNativeLifecycleConvert(new IGMCustomNativeLifecycleConvert() { // from class: com.bytedance.msdk.api.v2.ad.custom.nativeAd.GMCustomNativeAd.3
            @Override // com.bytedance.msdk.api.v2.ad.custom.nativeAd.IGMCustomNativeLifecycleConvert
            public void onDestroyInner() {
                GMCustomNativeAd.this.onDestroyInner();
            }

            @Override // com.bytedance.msdk.api.v2.ad.custom.nativeAd.IGMCustomNativeLifecycleConvert
            public void onPauseInner() {
                GMCustomNativeAd.this.onPauseInner();
            }

            @Override // com.bytedance.msdk.api.v2.ad.custom.nativeAd.IGMCustomNativeLifecycleConvert
            public void onResumeInner() {
                GMCustomNativeAd.this.onResumeInner();
            }
        });
        return this.f4370a;
    }

    public void onDestroyInner() {
        try {
            if (this.f4370a != null) {
                this.f4370a.onDestroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onPauseInner() {
        try {
            if (this.f4370a != null) {
                this.f4370a.onPause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onResumeInner() {
        try {
            if (this.f4370a != null) {
                this.f4370a.onResume();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerViewForInteraction(ViewGroup viewGroup, List<View> list, List<View> list2, GMViewBinder gMViewBinder) {
    }

    public final void registerViewForInteractionInner(ViewGroup viewGroup, List<View> list, List<View> list2, GMViewBinder gMViewBinder) {
        try {
            this.d = true;
            registerViewForInteraction(viewGroup, list, list2, gMViewBinder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void render() {
    }

    public final void renderInner() {
        try {
            this.d = true;
            render();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unregisterView() {
    }

    public final void unregisterViewInner() {
        try {
            unregisterView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
