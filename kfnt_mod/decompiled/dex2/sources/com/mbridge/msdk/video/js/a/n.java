package com.mbridge.msdk.video.js.a;

import com.mbridge.msdk.video.module.MBridgeVideoView;

/* compiled from: JSVideoModule.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class n extends g {
    private MBridgeVideoView a;

    public n(MBridgeVideoView mBridgeVideoView) {
        this.a = mBridgeVideoView;
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final void showVideoLocation(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        super.showVideoLocation(i, i2, i3, i4, i5, i6, i7, i8, i9);
        if (this.a != null) {
            this.a.showVideoLocation(i, i2, i3, i4, i5, i6, i7, i8, i9);
        }
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final void soundOperate(int i, int i2) {
        super.soundOperate(i, i2);
        if (this.a != null) {
            this.a.soundOperate(i, i2);
        }
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final void soundOperate(int i, int i2, String str) {
        super.soundOperate(i, i2, str);
        if (this.a != null) {
            this.a.soundOperate(i, i2, str);
        }
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final void videoOperate(int i) {
        super.videoOperate(i);
        if (this.a != null) {
            this.a.videoOperate(i);
        }
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final void closeVideoOperate(int i, int i2) {
        super.closeVideoOperate(i, i2);
        if (this.a != null) {
            this.a.closeVideoOperate(i, i2);
        }
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final void progressOperate(int i, int i2) {
        super.progressOperate(i, i2);
        if (this.a != null) {
            this.a.progressOperate(i, i2);
        }
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final String getCurrentProgress() {
        if (this.a != null) {
            return this.a.getCurrentProgress();
        }
        return super.getCurrentProgress();
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final void setVisible(int i) {
        if (this.a != null) {
            this.a.setVisible(i);
        } else {
            super.setVisible(i);
        }
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final void setCover(boolean z) {
        if (this.a != null) {
            this.a.setCover(z);
        } else {
            super.setCover(z);
        }
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final void setScaleFitXY(int i) {
        if (this.a != null) {
            this.a.setScaleFitXY(i);
        } else {
            super.setScaleFitXY(i);
        }
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final boolean isH5Canvas() {
        if (this.a != null) {
            return this.a.isH5Canvas();
        }
        return super.isH5Canvas();
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final int getBorderViewWidth() {
        if (this.a != null) {
            return this.a.getBorderViewWidth();
        }
        return super.getBorderViewWidth();
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final int getBorderViewHeight() {
        if (this.a != null) {
            return this.a.getBorderViewHeight();
        }
        return super.getBorderViewHeight();
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final int getBorderViewRadius() {
        if (this.a != null) {
            return this.a.getBorderViewRadius();
        }
        return super.getBorderViewRadius();
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final int getBorderViewTop() {
        if (this.a != null) {
            return this.a.getBorderViewTop();
        }
        return super.getBorderViewTop();
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final int getBorderViewLeft() {
        if (this.a != null) {
            return this.a.getBorderViewLeft();
        }
        return super.getBorderViewLeft();
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final void showIVRewardAlertView(String str) {
        if (this.a != null) {
            this.a.showIVRewardAlertView(str);
        } else {
            super.showIVRewardAlertView(str);
        }
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final void hideAlertView(int i) {
        if (this.a != null) {
            this.a.hideAlertView(i);
        } else {
            super.hideAlertView(i);
        }
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final void alertWebViewShowed() {
        if (this.a != null) {
            this.a.alertWebViewShowed();
        } else {
            super.alertWebViewShowed();
        }
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final void showAlertView() {
        if (this.a != null) {
            this.a.showAlertView();
        } else {
            super.showAlertView();
        }
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final void dismissAllAlert() {
        if (this.a != null) {
            this.a.dismissAllAlert();
        } else {
            super.dismissAllAlert();
        }
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final void setInstallDialogState(boolean z) {
        if (this.a != null) {
            this.a.setInstallDialogState(z);
        } else {
            super.setInstallDialogState(z);
        }
    }

    @Override // com.mbridge.msdk.video.js.a.g, com.mbridge.msdk.video.js.i
    public final void setMiniEndCardState(boolean z) {
        if (this.a != null) {
            this.a.setMiniEndCardState(z);
        } else {
            super.setMiniEndCardState(z);
        }
    }
}
