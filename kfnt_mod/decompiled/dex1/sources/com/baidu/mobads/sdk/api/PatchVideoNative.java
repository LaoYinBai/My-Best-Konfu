package com.baidu.mobads.sdk.api;

import android.content.Context;
import android.widget.RelativeLayout;
import com.baidu.mobads.sdk.api.BaiduNativeManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PatchVideoNative {
    private String mAdPlaceId;
    private PrerollVideoResponse mAdResponse;
    private String mAppSid;
    private BaiduNativeManager mBaiduNativeManager;
    private Context mContext;
    private boolean mIsMute;
    private IPatchVideoNativeListener mListener;
    private RelativeLayout mParentView;
    private PatchAdView mPatchView;

    public interface IPatchVideoNativeListener {
        void onAdClick();

        void onAdFailed(int i, String str);

        void onAdLoad(String str);

        void onAdShow();

        void playCompletion();

        void playError();
    }

    public PatchVideoNative(Context context, String str, RelativeLayout relativeLayout, IPatchVideoNativeListener iPatchVideoNativeListener) {
        this.mContext = context;
        this.mAdPlaceId = str;
        this.mParentView = relativeLayout;
        this.mListener = iPatchVideoNativeListener;
        this.mBaiduNativeManager = new BaiduNativeManager(this.mContext, this.mAdPlaceId);
    }

    public void requestAd(RequestParameters requestParameters) {
        if (this.mBaiduNativeManager != null) {
            this.mBaiduNativeManager.setAppSid(this.mAppSid);
            this.mBaiduNativeManager.loadPrerollVideo(requestParameters, new BaiduNativeManager.FeedAdListener() { // from class: com.baidu.mobads.sdk.api.PatchVideoNative.1
                @Override // com.baidu.mobads.sdk.api.BaiduNativeManager.FeedAdListener
                public void onLpClosed() {
                }

                @Override // com.baidu.mobads.sdk.api.BaiduNativeManager.FeedAdListener
                public void onVideoDownloadFailed() {
                }

                @Override // com.baidu.mobads.sdk.api.BaiduNativeManager.FeedAdListener
                public void onVideoDownloadSuccess() {
                }

                @Override // com.baidu.mobads.sdk.api.BaiduNativeManager.FeedAdListener
                public void onNativeLoad(List<NativeResponse> list) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < list.size(); i++) {
                        arrayList.add(new XAdVideoResponse(list.get(i)));
                    }
                    PatchVideoNative.this.mAdResponse = (PrerollVideoResponse) arrayList.get(0);
                    PatchVideoNative.this.callLoadSucc();
                }

                @Override // com.baidu.mobads.sdk.api.BaiduNativeManager.FeedAdListener
                public void onNativeFail(int i, String str) {
                    PatchVideoNative.this.callLoadFail(i, str);
                }

                @Override // com.baidu.mobads.sdk.api.BaiduNativeManager.FeedAdListener
                public void onNoAd(int i, String str) {
                    PatchVideoNative.this.callLoadFail(i, str);
                }
            });
        }
    }

    public void setVideoMute(boolean z) {
        this.mIsMute = z;
        if (this.mPatchView != null) {
            this.mPatchView.setVideoVolume(this.mIsMute);
        }
    }

    public long getCurrentPosition() {
        if (this.mPatchView != null) {
            return this.mPatchView.getCurrentPosition();
        }
        return 0L;
    }

    public long getDuration() {
        if (this.mPatchView != null) {
            return this.mPatchView.getDuration();
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callLoadSucc() {
        if (this.mListener != null) {
            this.mListener.onAdLoad(this.mAdResponse.getMaterialType());
        }
        this.mPatchView = new PatchAdView(this.mContext);
        this.mPatchView.setVideoVolume(this.mIsMute);
        this.mParentView.addView(this.mPatchView, new RelativeLayout.LayoutParams(-1, -1));
        this.mPatchView.setPatchAdListener(new IPatchAdListener() { // from class: com.baidu.mobads.sdk.api.PatchVideoNative.2
            @Override // com.baidu.mobads.sdk.api.IPatchAdListener
            public void playCompletion() {
                PatchVideoNative.this.callPlayCompletion();
            }

            @Override // com.baidu.mobads.sdk.api.IPatchAdListener
            public void playError() {
                PatchVideoNative.this.callPlayError();
            }

            @Override // com.baidu.mobads.sdk.api.IPatchAdListener
            public void onAdShow() {
                PatchVideoNative.this.callAdShow();
            }

            @Override // com.baidu.mobads.sdk.api.IPatchAdListener
            public void onAdClicked() {
                PatchVideoNative.this.callAdClick();
            }
        });
        this.mPatchView.setAdData((XAdVideoResponse) this.mAdResponse);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callLoadFail(int i, String str) {
        if (this.mListener != null) {
            this.mListener.onAdFailed(i, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callPlayCompletion() {
        if (this.mListener != null) {
            this.mListener.playCompletion();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callPlayError() {
        if (this.mListener != null) {
            this.mListener.playError();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callAdShow() {
        if (this.mListener != null) {
            this.mListener.onAdShow();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callAdClick() {
        if (this.mListener != null) {
            this.mListener.onAdClick();
        }
    }

    public void setAppSid(String str) {
        this.mAppSid = str;
    }
}
