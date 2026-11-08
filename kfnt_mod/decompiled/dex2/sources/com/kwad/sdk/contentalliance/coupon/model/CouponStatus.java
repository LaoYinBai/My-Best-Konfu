package com.kwad.sdk.contentalliance.coupon.model;

import com.google.android.gms.common.api.Api;
import com.kwad.sdk.core.b;
import com.kwad.sdk.core.response.kwai.a;
import com.kwad.sdk.core.response.model.SdkConfigData;
import java.io.Serializable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class CouponStatus extends a implements b, Serializable {
    private static final int DEF_COUPON_VIDEO_SECONDS = 5;
    private static final long serialVersionUID = 8301131859564901468L;
    public volatile CouponStatusInfo couponStatusInfo;
    public volatile SdkConfigData.CouponActiveConfig couponActiveConfig = null;
    public int currentWatchVideoCount = 0;
    public int winningTimes = 0;

    public int getCouponVideoSeconds() {
        if (this.couponActiveConfig != null) {
            return this.couponActiveConfig.videoSeconds;
        }
        return 5;
    }

    public int getCouponVideoThreshold() {
        return this.couponActiveConfig != null ? this.couponActiveConfig.videoThreshold : Api.BaseClientBuilder.API_PRIORITY_OTHER;
    }

    public double getCurrTotalAmount() {
        if (this.couponStatusInfo != null) {
            return this.couponStatusInfo.currTotalAmount;
        }
        return 0.0d;
    }

    public int getStatusCode() {
        if (this.couponStatusInfo != null) {
            return this.couponStatusInfo.statusCode;
        }
        return -1;
    }

    public boolean isAccumulating() {
        return !isNewUser() && getStatusCode() == 0 && this.currentWatchVideoCount < this.couponActiveConfig.videoThreshold;
    }

    public boolean isEnable() {
        return this.couponActiveConfig != null && this.couponActiveConfig.videoThreshold > 0 && this.couponActiveConfig.videoSeconds > 0;
    }

    public boolean isNewUser() {
        return this.couponStatusInfo != null && this.couponStatusInfo.isNewUser;
    }

    public boolean isWaitingOpen() {
        return isNewUser() || (getStatusCode() == 0 && this.currentWatchVideoCount == this.couponActiveConfig.videoThreshold);
    }

    public void setStatusCode(int i) {
        if (this.couponStatusInfo != null) {
            this.couponStatusInfo.statusCode = i;
        }
    }
}
