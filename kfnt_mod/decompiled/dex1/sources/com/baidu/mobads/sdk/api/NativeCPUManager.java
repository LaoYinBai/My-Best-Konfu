package com.baidu.mobads.sdk.api;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.mobads.sdk.internal.ay;
import com.baidu.mobads.sdk.internal.bb;
import com.baidu.mobads.sdk.internal.y;
import java.util.HashMap;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class NativeCPUManager {
    private static final String TAG = "NativeCPUManager";
    private CPUAdListener mCPUAdListener;
    private y mCPUAdProd;
    private Context mContext;
    private int mPageSize = 10;
    private HashMap<String, Object> mParams = new HashMap<>();

    public interface CPUAdListener {
        void onAdError(String str, int i);

        void onAdLoaded(List<IBasicCPUData> list);

        void onDisLikeAdClick(int i, String str);

        void onExitLp();

        void onLpCustomEventCallBack(HashMap<String, Object> map);

        void onVideoDownloadFailed();

        void onVideoDownloadSuccess();
    }

    public NativeCPUManager(Context context, String str, CPUAdListener cPUAdListener) {
        this.mCPUAdProd = null;
        if (context != null && !TextUtils.isEmpty(str)) {
            this.mCPUAdListener = cPUAdListener;
            this.mContext = context;
            this.mCPUAdProd = new y(context, str, this);
            this.mCPUAdProd.a(cPUAdListener);
            return;
        }
        bb.a().c(TAG, "Init params error!");
        if (cPUAdListener != null) {
            cPUAdListener.onAdError("Input params error.", ay.INTERFACE_USE_PROBLEM.b());
        }
    }

    public void loadAd(int i, int i2, boolean z) {
        loadAd(i, new int[]{i2}, z);
    }

    private void loadAd(int i, int[] iArr, boolean z) {
        if (i > 0 && iArr != null) {
            if (this.mCPUAdProd != null) {
                this.mCPUAdProd.a(i, this.mPageSize, iArr, z, this.mParams);
                this.mCPUAdProd.f();
                this.mCPUAdProd.b_();
                return;
            }
            return;
        }
        bb.a().c(TAG, "LoadAd with terrible params!");
    }

    public void setRequestTimeoutMillis(int i) {
        if (this.mCPUAdProd != null) {
            this.mCPUAdProd.a(i);
        }
    }

    public void setRequestParameter(CPUAdRequest cPUAdRequest) {
        if (cPUAdRequest == null || cPUAdRequest.getExtras() == null) {
            return;
        }
        this.mParams = cPUAdRequest.getExtras();
    }

    public void setPageSize(int i) {
        if (i > 0 && i <= 20) {
            this.mPageSize = i;
        } else {
            bb.a().c(TAG, "Input page size is wrong which should be in (0,20]!");
        }
    }
}
