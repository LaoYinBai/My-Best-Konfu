package com.mbridge.msdk.video.js.a;

import com.mbridge.msdk.foundation.tools.s;

/* compiled from: DefaultJSContainerModule.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d implements com.mbridge.msdk.video.js.e, com.mbridge.msdk.video.js.g {
    @Override // com.mbridge.msdk.video.js.e
    public void configurationChanged(int i, int i2, int i3) {
    }

    @Override // com.mbridge.msdk.video.js.e
    public void showVideoClickView(int i) {
        s.a("DefaultJSContainerModule", "showVideoClickView:" + i);
    }

    @Override // com.mbridge.msdk.video.js.e
    public void showEndcard(int i) {
        s.a("DefaultJSContainerModule", "showEndcard,type=" + i);
    }

    @Override // com.mbridge.msdk.video.js.e
    public boolean endCardShowing() {
        s.a("DefaultJSContainerModule", "endCardShowing");
        return true;
    }

    @Override // com.mbridge.msdk.video.js.e
    public boolean miniCardShowing() {
        s.a("DefaultJSContainerModule", "miniCardShowing");
        return false;
    }

    @Override // com.mbridge.msdk.video.js.g
    public void notifyCloseBtn(int i) {
        s.a("DefaultJSContainerModule", "notifyCloseBtn:state = " + i);
    }

    @Override // com.mbridge.msdk.video.js.g
    public void toggleCloseBtn(int i) {
        s.a("DefaultJSContainerModule", "toggleCloseBtn:state=" + i);
    }

    @Override // com.mbridge.msdk.video.js.e
    public void readyStatus(int i) {
        s.a("DefaultJSContainerModule", "readyStatus:isReady=" + i);
    }

    @Override // com.mbridge.msdk.video.js.e
    public void showMiniCard(int i, int i2, int i3, int i4, int i5) {
        s.a("DefaultJSContainerModule", "showMiniCard top = " + i + " left = " + i2 + " width = " + i3 + " height = " + i4 + " radius = " + i5);
    }

    @Override // com.mbridge.msdk.video.js.e
    public void resizeMiniCard(int i, int i2, int i3) {
        s.a("DefaultJSContainerModule", "showMiniCard width = " + i + " height = " + i2 + " radius = " + i3);
    }

    @Override // com.mbridge.msdk.video.js.e
    public boolean showAlertWebView() {
        s.a("DefaultJSContainerModule", "showAlertWebView ,msg=");
        return false;
    }

    @Override // com.mbridge.msdk.video.js.e
    public void hideAlertWebview() {
        s.a("DefaultJSContainerModule", "hideAlertWebview ,msg=");
    }

    @Override // com.mbridge.msdk.video.js.e
    public void ivRewardAdsWithoutVideo(String str) {
        s.a("DefaultJSContainerModule", "ivRewardAdsWithoutVideo,params=");
    }
}
