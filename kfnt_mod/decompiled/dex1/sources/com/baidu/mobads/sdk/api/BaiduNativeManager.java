package com.baidu.mobads.sdk.api;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.mobads.sdk.api.IAdInterListener;
import com.baidu.mobads.sdk.api.RequestParameters;
import com.baidu.mobads.sdk.internal.ac;
import com.baidu.mobads.sdk.internal.co;
import com.baidu.mobads.sdk.internal.e;
import com.baidu.mobads.sdk.internal.x;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BaiduNativeManager {
    private static final int FEED_TIMEOUT = 8000;
    private static final String TAG = "BaiduNativeManager";
    private boolean isCacheVideo;
    private boolean isCacheVideoOnlyWifi;
    private final String mAdPlacementId;
    private String mAppSid;
    private final Context mContext;
    private int mTimeoutMillis;

    public interface FeedAdListener {
        void onLpClosed();

        void onNativeFail(int i, String str);

        void onNativeLoad(List<NativeResponse> list);

        void onNoAd(int i, String str);

        void onVideoDownloadFailed();

        void onVideoDownloadSuccess();
    }

    public interface PortraitVideoAdListener extends FeedAdListener {
        void onAdClick();
    }

    public BaiduNativeManager(Context context, String str) {
        this(context, str, FEED_TIMEOUT);
    }

    public BaiduNativeManager(Context context, String str, int i) {
        this(context, str, true, i);
    }

    public BaiduNativeManager(Context context, String str, boolean z) {
        this(context, str, z, FEED_TIMEOUT);
    }

    public BaiduNativeManager(Context context, String str, boolean z, int i) {
        this.isCacheVideo = true;
        this.mTimeoutMillis = FEED_TIMEOUT;
        this.isCacheVideoOnlyWifi = false;
        this.mContext = context;
        this.mAdPlacementId = str;
        this.isCacheVideo = z;
        this.mTimeoutMillis = i;
    }

    public void setCacheVideoOnlyWifi(boolean z) {
        this.isCacheVideoOnlyWifi = z;
    }

    public void loadFeedAd(RequestParameters requestParameters, FeedAdListener feedAdListener) {
        e eVar = new e(this.mContext, this.mAdPlacementId, new x(feedAdListener), this.isCacheVideo, this.mTimeoutMillis);
        if (!TextUtils.isEmpty(this.mAppSid)) {
            eVar.b(this.mAppSid);
        }
        eVar.a(this.isCacheVideoOnlyWifi);
        eVar.a(new ac());
        eVar.b(requestParameters);
    }

    public void loadContentAd(RequestParameters requestParameters, FeedAdListener feedAdListener) {
        e eVar = new e(this.mContext, new x(feedAdListener), new co(this.mContext, this.mAdPlacementId, "content", this.isCacheVideo, this.mTimeoutMillis));
        if (!TextUtils.isEmpty(this.mAppSid)) {
            eVar.b(this.mAppSid);
        }
        eVar.a(this.isCacheVideoOnlyWifi);
        eVar.a(new ac());
        eVar.b(requestParameters);
    }

    public void loadPortraitVideoAd(RequestParameters requestParameters, PortraitVideoAdListener portraitVideoAdListener) {
        e eVar = new e(this.mContext, new x(portraitVideoAdListener), new co(this.mContext, this.mAdPlacementId, IAdInterListener.AdProdType.PRODUCT_PORTRAITVIDEO, this.isCacheVideo, this.mTimeoutMillis));
        if (!TextUtils.isEmpty(this.mAppSid)) {
            eVar.b(this.mAppSid);
        }
        eVar.a(this.isCacheVideoOnlyWifi);
        eVar.a(new ac());
        eVar.b(requestParameters);
    }

    public void loadInsiteAd(RequestParameters requestParameters, FeedAdListener feedAdListener) {
        e eVar = new e(this.mContext, new x(feedAdListener), new co(this.mContext, this.mAdPlacementId, IAdInterListener.AdProdType.PRODUCT_INSITE, this.isCacheVideo, this.mTimeoutMillis));
        if (!TextUtils.isEmpty(this.mAppSid)) {
            eVar.b(this.mAppSid);
        }
        eVar.a(this.isCacheVideoOnlyWifi);
        eVar.a(new ac());
        eVar.b(requestParameters);
    }

    public void loadPrerollVideo(RequestParameters requestParameters, FeedAdListener feedAdListener) {
        e eVar = new e(this.mContext, this.mAdPlacementId, new x(feedAdListener), this.isCacheVideo, FEED_TIMEOUT, IAdInterListener.AdProdType.PRODUCT_PREROLL);
        if (!TextUtils.isEmpty(this.mAppSid)) {
            eVar.b(this.mAppSid);
        }
        eVar.b(requestParameters);
    }

    public String getFeedBiddingToken(RequestParameters requestParameters) {
        co coVar = new co(this.mContext, this.mAdPlacementId, IAdInterListener.AdProdType.PRODUCT_FEEDS, this.isCacheVideo, this.mTimeoutMillis);
        if (requestParameters == null) {
            requestParameters = new RequestParameters.Builder().build();
        }
        if (!TextUtils.isEmpty(this.mAppSid)) {
            coVar.f(this.mAppSid);
        }
        coVar.a(requestParameters);
        return coVar.m();
    }

    public void setFeedBiddingData(RequestParameters requestParameters, String str, FeedAdListener feedAdListener) {
        e eVar = new e(this.mContext, this.mAdPlacementId, new x(feedAdListener), this.isCacheVideo, this.mTimeoutMillis);
        if (!TextUtils.isEmpty(this.mAppSid)) {
            eVar.b(this.mAppSid);
        }
        eVar.a(this.isCacheVideoOnlyWifi);
        eVar.a(new ac());
        eVar.a(requestParameters);
        eVar.a(str);
    }

    public String getPortraitVideoBiddingToken(RequestParameters requestParameters) {
        co coVar = new co(this.mContext, this.mAdPlacementId, IAdInterListener.AdProdType.PRODUCT_PORTRAITVIDEO, this.isCacheVideo, this.mTimeoutMillis);
        if (requestParameters == null) {
            requestParameters = new RequestParameters.Builder().build();
        }
        if (!TextUtils.isEmpty(this.mAppSid)) {
            coVar.f(this.mAppSid);
        }
        coVar.a(requestParameters);
        return coVar.m();
    }

    public void setPortraitVideoBiddingData(RequestParameters requestParameters, String str, PortraitVideoAdListener portraitVideoAdListener) {
        e eVar = new e(this.mContext, new x(portraitVideoAdListener), new co(this.mContext, this.mAdPlacementId, IAdInterListener.AdProdType.PRODUCT_PORTRAITVIDEO, this.isCacheVideo, this.mTimeoutMillis));
        if (!TextUtils.isEmpty(this.mAppSid)) {
            eVar.b(this.mAppSid);
        }
        eVar.a(this.isCacheVideoOnlyWifi);
        eVar.a(new ac());
        eVar.a(requestParameters);
        eVar.a(str);
    }

    public void setAppSid(String str) {
        this.mAppSid = str;
    }
}
