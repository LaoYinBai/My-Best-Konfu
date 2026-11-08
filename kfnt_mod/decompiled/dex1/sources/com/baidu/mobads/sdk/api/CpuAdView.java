package com.baidu.mobads.sdk.api;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.baidu.mobads.sdk.internal.bz;
import com.baidu.mobads.sdk.internal.cb;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class CpuAdView extends RelativeLayout {
    private cb mAdProd;

    public interface CpuAdViewInternalStatusListener {
        void loadDataError(String str);

        void onAdClick();

        void onAdImpression(String str);

        void onContentClick();

        void onContentImpression(String str);

        void onExitLp();

        void onLpContentStatus(Map<String, Object> map);
    }

    public CpuAdView(Context context) {
        super(context);
    }

    public CpuAdView(Context context, String str, int i, CPUWebAdRequestParam cPUWebAdRequestParam) {
        super(context);
        bz bzVar = new bz(context);
        this.mAdProd = new cb(context, bzVar, str, i, cPUWebAdRequestParam);
        addView(bzVar, new ViewGroup.LayoutParams(-1, -1));
    }

    public CpuAdView(Context context, String str, int i, CPUWebAdRequestParam cPUWebAdRequestParam, CpuAdViewInternalStatusListener cpuAdViewInternalStatusListener) {
        super(context);
        bz bzVar = new bz(context);
        this.mAdProd = new cb(context, bzVar, str, i, cPUWebAdRequestParam);
        this.mAdProd.a(cpuAdViewInternalStatusListener);
        addView(bzVar, new ViewGroup.LayoutParams(-1, -1));
    }

    public void onResume() {
        View viewV = this.mAdProd.v();
        if (viewV instanceof WebView) {
            ((WebView) viewV).onResume();
        }
    }

    public void onPause() {
        View viewV = this.mAdProd.v();
        if (viewV instanceof WebView) {
            ((WebView) viewV).onPause();
        }
    }

    public void onDestroy() {
        View viewV = this.mAdProd.v();
        if (viewV instanceof WebView) {
            ((WebView) viewV).destroy();
        }
    }

    public boolean onKeyBackDown(int i, KeyEvent keyEvent) {
        if (i != 4 || !canGoBack()) {
            return false;
        }
        goBack();
        return true;
    }

    protected boolean canGoBack() {
        try {
            return ((WebView) this.mAdProd.v()).canGoBack();
        } catch (Throwable unused) {
            return false;
        }
    }

    protected void goBack() {
        try {
            ((WebView) this.mAdProd.v()).goBack();
        } catch (Throwable unused) {
        }
    }

    public void requestData() {
        if (this.mAdProd != null) {
            this.mAdProd.b_();
        }
    }
}
