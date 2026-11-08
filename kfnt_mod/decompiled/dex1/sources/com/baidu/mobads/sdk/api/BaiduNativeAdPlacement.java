package com.baidu.mobads.sdk.api;

import com.baidu.mobads.sdk.internal.a;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BaiduNativeAdPlacement {
    private String apid;
    private String mAppsid;
    private boolean mClicked;
    private boolean mWinSended;
    a mAdElementInfo = null;
    private boolean mRequestStarted = false;
    private WeakReference<BaiduNativeH5AdView> mWeakAdView = null;
    private int feedSessionId = 0;
    private int feedPositionId = 1;
    private int feedSequenceId = 1;

    public void setRequestStarted(boolean z) {
        this.mRequestStarted = z;
    }

    public boolean getRequestStarted() {
        return this.mRequestStarted;
    }

    public void setAdView(BaiduNativeH5AdView baiduNativeH5AdView) {
        this.mWeakAdView = new WeakReference<>(baiduNativeH5AdView);
    }

    public BaiduNativeH5AdView getAdView() {
        if (this.mWeakAdView != null) {
            return this.mWeakAdView.get();
        }
        return null;
    }

    public void setApId(String str) {
        this.apid = str;
    }

    public String getApId() {
        return this.apid;
    }

    public void setAppSid(String str) {
        this.mAppsid = str;
    }

    public boolean hasValidResponse() {
        return this.mAdElementInfo != null && isAdAvailable();
    }

    protected boolean isAdAvailable() {
        return (this.mAdElementInfo != null && ((System.currentTimeMillis() - this.mAdElementInfo.x()) > 1800000L ? 1 : ((System.currentTimeMillis() - this.mAdElementInfo.x()) == 1800000L ? 0 : -1)) <= 0) && !this.mClicked;
    }

    protected boolean isWinSended() {
        return this.mWinSended;
    }

    public void setClicked(boolean z) {
        this.mClicked = z;
    }

    public void setWinSended(boolean z) {
        this.mWinSended = z;
    }

    public boolean isAdDataLoaded() {
        BaiduNativeH5AdView adView = getAdView();
        if (adView != null) {
            return adView.isAdDataLoaded();
        }
        return false;
    }

    public void setSessionId(int i) {
        if (i < 1) {
            return;
        }
        this.feedSessionId = i;
        this.feedSequenceId = BaiduNativeFeedSession.getInstance().getSequenceId(i);
    }

    protected int getSessionId() {
        return this.feedSessionId;
    }

    public void setPositionId(int i) {
        if (i < 1) {
            return;
        }
        this.feedPositionId = i;
    }

    protected int getPosistionId() {
        return this.feedPositionId;
    }

    protected int getSequenceId() {
        return this.feedSequenceId;
    }

    public a getResponse() {
        return this.mAdElementInfo;
    }

    public void setAdResponse(a aVar) {
        this.mWinSended = false;
        this.mAdElementInfo = aVar;
    }
}
