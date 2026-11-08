package com.baidu.mobads.sdk.api;

import android.content.Context;
import android.view.View;
import com.baidu.mobads.sdk.internal.cg;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CPUNovelAd {
    private cg mCpuNovelProd;

    public interface CpuNovelListener {
        void onAdClick();

        void onAdImpression();

        void onReadTime(long j);
    }

    public CPUNovelAd(Context context, String str, CPUWebAdRequestParam cPUWebAdRequestParam, CpuNovelListener cpuNovelListener) {
        this.mCpuNovelProd = new cg(context, str, cPUWebAdRequestParam);
        this.mCpuNovelProd.a(cpuNovelListener);
        this.mCpuNovelProd.b_();
    }

    public View getNovelView() {
        return this.mCpuNovelProd.v();
    }
}
