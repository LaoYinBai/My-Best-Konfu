package com.bytedance.msdk.api.v2.ad.nativeAd;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bytedance.msdk.api.v2.GMAdDislike;
import com.bytedance.msdk.api.v2.GMDislikeCallback;
import com.bytedance.msdk.base.TTBaseAd;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface GMNativeAd {
    @Deprecated
    boolean canAdReuse();

    void destroy();

    @Nullable
    String getActionText();

    int getAdImageMode();

    @Nullable
    @Deprecated
    View getAdLogoView();

    int getAdNetworkPlatformId();

    @NonNull
    String getAdNetworkRitId();

    @Nullable
    String getDescription();

    @Nullable
    GMAdDislike getDislikeDialog(Activity activity);

    @Nullable
    View getExpressView();

    @Nullable
    String getIconUrl();

    int getImageHeight();

    @Nullable
    List<String> getImageList();

    @Nullable
    String getImageUrl();

    int getImageWidth();

    int getInteractionType();

    Map<String, Object> getMediaExtraInfo();

    GMNativeAdAppInfo getNativeAdAppInfo();

    @Nullable
    String getPackageName();

    @NonNull
    String getPreEcpm();

    @Deprecated
    int getSdkNumType();

    @Nullable
    String getSource();

    double getStarRating();

    @Deprecated
    TTBaseAd getTTBaseAd();

    @Nullable
    String getTitle();

    int getVideoHeight();

    int getVideoWidth();

    boolean hasDislike();

    boolean isExpressAd();

    boolean isHasShown();

    boolean isServerBidding();

    void onPause();

    void registerView(@NonNull Activity activity, @NonNull ViewGroup viewGroup, @NonNull List<View> list, @Nullable List<View> list2, GMViewBinder gMViewBinder);

    void registerView(@NonNull ViewGroup viewGroup, @NonNull List<View> list, @Nullable List<View> list2, GMViewBinder gMViewBinder);

    void render();

    void resume();

    void setDislikeCallback(Activity activity, GMDislikeCallback gMDislikeCallback);

    void setNativeAdListener(GMNativeAdListener gMNativeAdListener);

    void setVideoListener(GMVideoListener gMVideoListener);

    void unregisterView();
}
