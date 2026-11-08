package com.baidu.mobads.sdk.api;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import com.baidu.mobads.sdk.internal.a;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface NativeResponse extends AbstractData {
    public static final int ACTION_TYPE_APP_DOWNLOAD = 2;
    public static final int ACTION_TYPE_DEEP_LINK = 3;
    public static final int ACTION_TYPE_LANDING_PAGE = 1;
    public static final int INFO_FLOW_GROUP_PIC = 35;
    public static final int INFO_FLOW_GROUP_PIC_LOGO = 36;
    public static final int INFO_FLOW_LEFT_PIC = 33;
    public static final int INFO_FLOW_PIC_BOTTOM_TITLE = 28;
    public static final int INFO_FLOW_PIC_LOGO = 30;
    public static final int INFO_FLOW_PIC_TOP_TITLE = 29;
    public static final int INFO_FLOW_RIGHT_PIC = 34;
    public static final int INFO_FLOW_VIDEO_TOP_TITLE = 37;

    public interface AdDislikeListener {
        void onDislikeClick();
    }

    public interface AdDownloadWindowListener extends AdPrivacyListener {
        void adDownloadWindowClose();

        void adDownloadWindowShow();
    }

    public interface AdInteractionListener {
        void onADExposed();

        void onADExposureFailed(int i);

        void onADStatusChanged();

        void onAdClick();

        void onAdUnionClick();
    }

    public interface AdPrivacyListener {
        void onADPermissionClose();

        void onADPermissionShow();

        void onADPrivacyClick();
    }

    void biddingFail(String str);

    void biddingSuccess(String str);

    void clearImpressionTaskWhenBack();

    String getActButtonString();

    int getAdActionType();

    String getAdLogoUrl();

    String getAdMaterialType();

    String getAppPackage();

    String getAppPermissionLink();

    String getAppPrivacyLink();

    long getAppSize();

    String getAppVersion();

    String getBaiduLogoUrl();

    String getBrandName();

    int getContainerHeight();

    int getContainerSizeType();

    int getContainerWidth();

    String getDesc();

    int getDownloadStatus();

    int getDuration();

    String getECPMLevel();

    Map<String, String> getExtras();

    String getHtmlSnippet();

    String getIconUrl();

    String getImageUrl();

    int getMainPicHeight();

    int getMainPicWidth();

    String getMarketingPendant();

    MaterialType getMaterialType();

    List<String> getMultiPicUrls();

    String getPublisher();

    int getStyleType();

    String getTitle();

    String getVideoUrl();

    WebView getWebView();

    void handleClick(View view);

    void handleClick(View view, int i);

    void handleClick(View view, int i, boolean z);

    void handleClick(View view, boolean z);

    boolean isAdAvailable(Context context);

    boolean isAutoPlay();

    @Deprecated
    boolean isNeedDownloadApp();

    boolean isNonWifiAutoPlay();

    void pauseAppDownload();

    void permissionClick();

    void privacyClick();

    void recordImpression(View view);

    void registerViewForInteraction(View view, AdInteractionListener adInteractionListener);

    void registerViewForInteraction(View view, List<View> list, List<View> list2, AdInteractionListener adInteractionListener);

    void resumeAppDownload();

    void setAdPrivacyListener(AdPrivacyListener adPrivacyListener);

    void unionLogoClick();

    public enum MaterialType {
        NORMAL("normal"),
        VIDEO("video"),
        HTML(a.f);

        private final String value;

        MaterialType(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }

        public static MaterialType parse(String str) {
            for (MaterialType materialType : values()) {
                if (materialType.value.equalsIgnoreCase(str)) {
                    return materialType;
                }
            }
            return null;
        }
    }
}
