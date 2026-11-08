package com.mbridge.msdk.video.js.a;

import com.mbridge.msdk.video.module.MBridgeContainerView;

/* compiled from: JSContainerModule.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class k extends d {
    private MBridgeContainerView a;

    public k(MBridgeContainerView mBridgeContainerView) {
        this.a = mBridgeContainerView;
    }

    @Override // com.mbridge.msdk.video.js.a.d, com.mbridge.msdk.video.js.e
    public final boolean showAlertWebView() {
        super.showAlertWebView();
        if (this.a != null) {
            return this.a.showAlertWebView();
        }
        return false;
    }

    @Override // com.mbridge.msdk.video.js.a.d, com.mbridge.msdk.video.js.e
    public final void showVideoClickView(int i) {
        super.showVideoClickView(i);
        if (this.a != null) {
            this.a.showVideoClickView(i);
        }
    }

    @Override // com.mbridge.msdk.video.js.a.d, com.mbridge.msdk.video.js.e
    public final void showEndcard(int i) {
        super.showEndcard(i);
        try {
            if (this.a != null) {
                this.a.showEndcard(i);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.mbridge.msdk.video.js.a.d, com.mbridge.msdk.video.js.g
    public final void toggleCloseBtn(int i) {
        super.toggleCloseBtn(i);
        try {
            if (this.a != null) {
                this.a.toggleCloseBtn(i);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.mbridge.msdk.video.js.a.d, com.mbridge.msdk.video.js.e
    public final boolean endCardShowing() {
        try {
            if (this.a != null) {
                return this.a.endCardShowing();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return super.endCardShowing();
    }

    @Override // com.mbridge.msdk.video.js.a.d, com.mbridge.msdk.video.js.e
    public final boolean miniCardShowing() {
        try {
            if (this.a != null) {
                return this.a.miniCardShowing();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return super.miniCardShowing();
    }

    @Override // com.mbridge.msdk.video.js.a.d, com.mbridge.msdk.video.js.e
    public final void readyStatus(int i) {
        try {
            if (this.a != null) {
                this.a.readyStatus(i);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        super.readyStatus(i);
    }

    @Override // com.mbridge.msdk.video.js.a.d, com.mbridge.msdk.video.js.e
    public final void showMiniCard(int i, int i2, int i3, int i4, int i5) {
        super.showMiniCard(i, i2, i3, i4, i5);
        try {
            if (this.a != null) {
                this.a.showMiniCard(i, i2, i3, i4, i5);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.mbridge.msdk.video.js.a.d, com.mbridge.msdk.video.js.e
    public final void resizeMiniCard(int i, int i2, int i3) {
        super.resizeMiniCard(i, i2, i3);
        try {
            if (this.a != null) {
                this.a.resizeMiniCard(i, i2, i3);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.mbridge.msdk.video.js.a.d, com.mbridge.msdk.video.js.e
    public final void configurationChanged(int i, int i2, int i3) {
        super.configurationChanged(i, i2, i3);
        try {
            if (this.a != null) {
                this.a.configurationChanged(i, i2, i3);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.mbridge.msdk.video.js.a.d, com.mbridge.msdk.video.js.e
    public final void hideAlertWebview() {
        super.hideAlertWebview();
        if (this.a != null) {
            this.a.hideAlertWebview();
        }
    }

    @Override // com.mbridge.msdk.video.js.a.d, com.mbridge.msdk.video.js.e
    public final void ivRewardAdsWithoutVideo(String str) {
        super.ivRewardAdsWithoutVideo(str);
        if (this.a != null) {
            this.a.ivRewardAdsWithoutVideo(str);
        }
    }
}
