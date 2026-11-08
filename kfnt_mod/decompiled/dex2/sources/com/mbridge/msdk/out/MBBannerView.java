package com.mbridge.msdk.out;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.mbridge.msdk.foundation.tools.w;
import com.mbridge.msdk.mbbanner.a.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MBBannerView extends RelativeLayout {
    private String bidToken;
    private a controller;
    private boolean hadAttached;
    private boolean isVisible;
    private BannerAdListener mBannerAdListener;
    private MBridgeIds mBridgeIds;

    public MBBannerView(Context context) {
        this(context, null);
    }

    public MBBannerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MBBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.hadAttached = false;
        this.isVisible = false;
        com.mbridge.msdk.foundation.controller.a.f().a(context);
    }

    public void init(BannerSize bannerSize, String str, String str2) {
        this.mBridgeIds = new MBridgeIds(str, str2);
        String strD = w.d(str2);
        if (!TextUtils.isEmpty(strD)) {
            w.a(str2, strD);
        }
        this.controller = new a(this, bannerSize, str, str2);
        this.controller.c(this.isVisible);
        this.controller.b(this.hadAttached);
    }

    public void updateBannerSize(BannerSize bannerSize) {
        if (this.controller != null) {
            this.controller.a(bannerSize);
        }
    }

    public void setRefreshTime(int i) {
        if (this.controller == null || i < 0) {
            return;
        }
        this.controller.a(i);
    }

    public void setAllowShowCloseBtn(boolean z) {
        if (this.controller != null) {
            this.controller.a(z);
        }
    }

    public void load() {
        if (this.controller != null) {
            this.controller.b(this.hadAttached);
            this.controller.a("");
        } else if (this.mBannerAdListener != null) {
            this.mBannerAdListener.onLoadFailed(this.mBridgeIds, "banner controler init error，please check it");
        }
    }

    public String getRequestId() {
        return this.controller != null ? this.controller.a() : "";
    }

    public void loadFromBid(String str) {
        this.bidToken = str;
        if (this.controller != null) {
            if (!TextUtils.isEmpty(str)) {
                this.controller.b(this.hadAttached);
                this.controller.a(0);
                this.controller.a(str);
                return;
            } else {
                if (this.mBannerAdListener != null) {
                    this.mBannerAdListener.onLoadFailed(this.mBridgeIds, "banner token is null or empty，please check it");
                    return;
                }
                return;
            }
        }
        if (this.mBannerAdListener != null) {
            this.mBannerAdListener.onLoadFailed(this.mBridgeIds, "banner controler init error，please check it");
        }
    }

    public void setBannerAdListener(BannerAdListener bannerAdListener) {
        this.mBannerAdListener = bannerAdListener;
        if (this.controller != null) {
            this.controller.a(bannerAdListener);
        }
    }

    public void release() {
        if (this.mBannerAdListener != null) {
            this.mBannerAdListener = null;
        }
        if (this.controller != null) {
            this.controller.a((BannerAdListener) null);
            this.controller.b();
        }
        removeAllViews();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateAttachedState(true);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        updateAttachedState(false);
    }

    private void updateAttachedState(boolean z) {
        this.hadAttached = z;
        if (this.controller != null) {
            this.controller.b(this.hadAttached);
        }
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.isVisible = i == 0;
        if (this.controller != null) {
            if (i == 0) {
                delayedNotify();
            } else {
                this.controller.c(false);
            }
        }
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.isVisible = i == 0;
        if (this.controller != null) {
            if (i == 0) {
                delayedNotify();
            } else {
                this.controller.c(false);
            }
        }
    }

    private void delayedNotify() {
        postDelayed(new Runnable() { // from class: com.mbridge.msdk.out.MBBannerView.1
            @Override // java.lang.Runnable
            public void run() {
                if (MBBannerView.this.controller != null) {
                    MBBannerView.this.controller.c(true);
                }
            }
        }, 200L);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.controller != null) {
            this.controller.a(i, i2, i3, i4);
        }
    }

    public void onPause() {
        if (this.controller == null || !TextUtils.isEmpty(this.bidToken)) {
            return;
        }
        this.controller.d();
    }

    public void onResume() {
        if (this.controller == null || !TextUtils.isEmpty(this.bidToken)) {
            return;
        }
        this.controller.e();
    }
}
