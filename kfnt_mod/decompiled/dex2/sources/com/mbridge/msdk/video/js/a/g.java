package com.mbridge.msdk.video.js.a;

import com.mbridge.msdk.foundation.tools.s;

/* compiled from: DefaultJSVideoModule.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g implements com.mbridge.msdk.video.js.i {
    @Override // com.mbridge.msdk.video.js.i
    public int getBorderViewHeight() {
        return 0;
    }

    @Override // com.mbridge.msdk.video.js.i
    public int getBorderViewLeft() {
        return 0;
    }

    @Override // com.mbridge.msdk.video.js.i
    public int getBorderViewRadius() {
        return 0;
    }

    @Override // com.mbridge.msdk.video.js.i
    public int getBorderViewTop() {
        return 0;
    }

    @Override // com.mbridge.msdk.video.js.i
    public int getBorderViewWidth() {
        return 0;
    }

    @Override // com.mbridge.msdk.video.js.i
    public boolean isH5Canvas() {
        return false;
    }

    @Override // com.mbridge.msdk.video.js.i
    public void showVideoLocation(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        s.a("DefaultJSVideoModule", "showVideoLocation:marginTop=" + i + ",marginLeft=" + i2 + ",width=" + i3 + ",height=" + i4 + ",radius=" + i5 + ",borderTop=" + i6 + ",borderTop=" + i6 + ",borderLeft=" + i7 + ",borderWidth=" + i8 + ",borderHeight=" + i9);
    }

    @Override // com.mbridge.msdk.video.js.i
    public void soundOperate(int i, int i2) {
        s.a("DefaultJSVideoModule", "soundOperate:mute=" + i + ",soundViewVisible=" + i2);
    }

    @Override // com.mbridge.msdk.video.js.i
    public void soundOperate(int i, int i2, String str) {
        s.a("DefaultJSVideoModule", "soundOperate:mute=" + i + ",soundViewVisible=" + i2 + ",pt=" + str);
    }

    @Override // com.mbridge.msdk.video.js.i
    public void videoOperate(int i) {
        s.a("DefaultJSVideoModule", "videoOperate:" + i);
    }

    @Override // com.mbridge.msdk.video.js.i
    public void closeVideoOperate(int i, int i2) {
        s.a("DefaultJSVideoModule", "closeOperte:close=" + i + "closeViewVisible=" + i2);
    }

    @Override // com.mbridge.msdk.video.js.i
    public void progressOperate(int i, int i2) {
        s.a("DefaultJSVideoModule", "progressOperate:progress=" + i + "progressViewVisible=" + i2);
    }

    @Override // com.mbridge.msdk.video.js.i
    public String getCurrentProgress() {
        s.a("DefaultJSVideoModule", "getCurrentProgress");
        return "{}";
    }

    @Override // com.mbridge.msdk.video.js.i
    public void setScaleFitXY(int i) {
        s.a("DefaultJSVideoModule", "setScaleFitXY:" + i);
    }

    @Override // com.mbridge.msdk.video.js.i
    public void setVisible(int i) {
        s.a("DefaultJSVideoModule", "setVisible:" + i);
    }

    @Override // com.mbridge.msdk.video.js.i
    public void setCover(boolean z) {
        s.a("DefaultJSVideoModule", "setCover:" + z);
    }

    @Override // com.mbridge.msdk.video.js.i
    public void notifyCloseBtn(int i) {
        s.a("DefaultJSVideoModule", "notifyCloseBtn:" + i);
    }

    @Override // com.mbridge.msdk.video.js.i
    public void showIVRewardAlertView(String str) {
        s.a("DefaultJSVideoModule", "showAlertView:");
    }

    @Override // com.mbridge.msdk.video.js.i
    public void showAlertView() {
        s.a("DefaultJSVideoModule", "showAlertView:");
    }

    @Override // com.mbridge.msdk.video.js.i
    public void hideAlertView(int i) {
        s.a("DefaultJSVideoModule", "hideAlertView:");
    }

    @Override // com.mbridge.msdk.video.js.i
    public void alertWebViewShowed() {
        s.a("DefaultJSVideoModule", "alertWebViewShowed:");
    }

    @Override // com.mbridge.msdk.video.js.i
    public void dismissAllAlert() {
        s.a("DefaultJSVideoModule", "dismissAllAlert");
    }

    @Override // com.mbridge.msdk.video.js.i
    public void setInstallDialogState(boolean z) {
        s.a("DefaultJSVideoModule", "setInstallDialogState");
    }

    @Override // com.mbridge.msdk.video.js.i
    public void setMiniEndCardState(boolean z) {
        s.a("DefaultJSVideoModule", "setMiniEndCardState");
    }
}
