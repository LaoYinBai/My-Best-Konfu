package com.baidu.mobads.sdk.api;

import android.content.Context;
import com.baidu.mobads.sdk.internal.bb;
import com.baidu.mobads.sdk.internal.cx;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class RewardVideoAd {
    public static final int DOWNLOAD_APP_CONFIRM_ALWAYS = 2;
    public static final int DOWNLOAD_APP_CONFIRM_NEVER = 3;
    public static final int DOWNLOAD_APP_CONFIRM_ONLY_MOBILE = 1;
    private static final String TAG = "RewardVideoAd";
    private cx mAdProd;
    private final Context mContext;

    public interface RewardVideoAdListener extends ScreenVideoAdListener {
        @Override // com.baidu.mobads.sdk.api.ScreenVideoAdListener
        void onAdClick();

        @Override // com.baidu.mobads.sdk.api.ScreenVideoAdListener
        void onAdClose(float f);

        @Override // com.baidu.mobads.sdk.api.ScreenVideoAdListener
        void onAdFailed(String str);

        @Override // com.baidu.mobads.sdk.api.ScreenVideoAdListener
        void onAdLoaded();

        @Override // com.baidu.mobads.sdk.api.ScreenVideoAdListener
        void onAdShow();

        @Override // com.baidu.mobads.sdk.api.ScreenVideoAdListener
        void onAdSkip(float f);

        void onRewardVerify(boolean z);

        @Override // com.baidu.mobads.sdk.api.ScreenVideoAdListener
        void onVideoDownloadFailed();

        @Override // com.baidu.mobads.sdk.api.ScreenVideoAdListener
        void onVideoDownloadSuccess();

        @Override // com.baidu.mobads.sdk.api.ScreenVideoAdListener
        void playCompletion();
    }

    public RewardVideoAd(Context context, String str, RewardVideoAdListener rewardVideoAdListener) {
        this(context, str, rewardVideoAdListener, false);
    }

    public RewardVideoAd(Context context, String str, RewardVideoAdListener rewardVideoAdListener, boolean z) {
        this.mContext = context;
        this.mAdProd = new cx(this.mContext, str, z);
        this.mAdProd.a(rewardVideoAdListener);
    }

    public void setDownloadAppConfirmPolicy(int i) {
        if (this.mAdProd != null) {
            this.mAdProd.a(i);
        }
    }

    public void setUserId(String str) {
        if (this.mAdProd != null) {
            this.mAdProd.a(str);
        }
    }

    public void setExtraInfo(String str) {
        if (this.mAdProd != null) {
            this.mAdProd.h(str);
        }
    }

    public synchronized void load() {
        if (this.mAdProd != null) {
            this.mAdProd.b_();
        }
    }

    public String getBiddingToken() {
        if (this.mAdProd != null) {
            return this.mAdProd.m();
        }
        return null;
    }

    public void setBiddingData(String str) {
        if (this.mAdProd != null) {
            this.mAdProd.b(str);
        }
    }

    public synchronized void show() {
        if (this.mAdProd != null) {
            this.mAdProd.f();
        }
    }

    public boolean isReady() {
        if (this.mAdProd != null) {
            return this.mAdProd.g();
        }
        return false;
    }

    public void setAppSid(String str) {
        if (this.mAdProd != null) {
            this.mAdProd.f(str);
        }
    }

    public void setShowDialogOnSkip(boolean z) {
        if (this.mAdProd != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("showDialogOnSkip", z);
                this.mAdProd.a(jSONObject);
            } catch (Throwable th) {
                bb.a().c(th);
            }
        }
    }

    public void setUseRewardCountdown(boolean z) {
        if (this.mAdProd != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("useRewardCountdown", z);
                this.mAdProd.a(jSONObject);
            } catch (Throwable th) {
                bb.a().c(th);
            }
        }
    }

    public String getECPMLevel() {
        return this.mAdProd != null ? this.mAdProd.h() : "";
    }

    public void biddingSuccess(String str) {
        if (this.mAdProd != null) {
            this.mAdProd.a(true, str);
        }
    }

    public void biddingFail(String str) {
        if (this.mAdProd != null) {
            this.mAdProd.a(false, str);
        }
    }
}
