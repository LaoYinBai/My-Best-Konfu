package com.mbridge.msdk.video.js.bridge;

import android.os.Handler;
import android.os.Looper;
import com.heytap.mcssdk.mode.Message;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.w;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class VideoBridge extends BaseVideoBridge {
    private Handler b = new Handler(Looper.getMainLooper());

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void init(final Object obj, final String str) {
        if (w.b()) {
            super.init(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.1
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.init(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void click(final Object obj, final String str) {
        if (w.b()) {
            super.click(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.12
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.click(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void statistics(final Object obj, final String str) {
        if (w.b()) {
            super.statistics(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.23
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.statistics(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void triggerCloseBtn(final Object obj, final String str) {
        if (w.b()) {
            super.triggerCloseBtn(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.34
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.triggerCloseBtn(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void showVideoLocation(final Object obj, final String str) {
        if (w.b()) {
            super.showVideoLocation(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.45
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.showVideoLocation(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void soundOperate(final Object obj, final String str) {
        if (w.b()) {
            super.soundOperate(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.56
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.soundOperate(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void videoOperate(final Object obj, final String str) {
        if (w.b()) {
            super.videoOperate(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.67
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.videoOperate(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void closeVideoOperte(final Object obj, final String str) {
        if (w.b()) {
            super.closeVideoOperte(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.78
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.closeVideoOperte(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void progressOperate(final Object obj, final String str) {
        if (w.b()) {
            super.progressOperate(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.79
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.progressOperate(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void getCurrentProgress(final Object obj, final String str) {
        if (w.b()) {
            super.getCurrentProgress(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.2
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.getCurrentProgress(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void showVideoClickView(final Object obj, final String str) {
        if (w.b()) {
            super.showVideoClickView(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.3
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.showVideoClickView(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void setScaleFitXY(final Object obj, final String str) {
        if (w.b()) {
            super.setScaleFitXY(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.4
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.setScaleFitXY(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void notifyCloseBtn(final Object obj, final String str) {
        if (w.b()) {
            super.notifyCloseBtn(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.5
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.notifyCloseBtn(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void toggleCloseBtn(final Object obj, final String str) {
        if (w.b()) {
            super.toggleCloseBtn(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.6
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.toggleCloseBtn(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void handlerH5Exception(final Object obj, final String str) {
        if (w.b()) {
            super.handlerH5Exception(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.7
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.handlerH5Exception(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void isSystemResume(final Object obj, final String str) {
        if (w.b()) {
            super.isSystemResume(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.8
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.isSystemResume(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void readyStatus(final Object obj, final String str) {
        s.b("JS-Video-Brigde", "VIDEOBridge readyStatus");
        if (w.b()) {
            super.readyStatus(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.9
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.readyStatus(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playVideoFinishOperate(final Object obj, final String str) {
        if (w.b()) {
            super.playVideoFinishOperate(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.10
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.playVideoFinishOperate(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void openURL(final Object obj, final String str) {
        if (w.b()) {
            super.openURL(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.11
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.openURL(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void cai(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.cai(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.13
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.cai(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void gial(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.gial(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.14
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.gial(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void showAlertView(final Object obj, final String str) {
        if (w.b()) {
            super.showAlertView(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.15
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.showAlertView(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void closeWeb(final Object obj, final String str) {
        if (w.b()) {
            super.closeWeb(obj, str);
            return;
        }
        this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.16
            @Override // java.lang.Runnable
            public final void run() {
                VideoBridge.super.closeWeb(obj, str);
            }
        });
        s.d("JS-Video-Brigde", Message.TYPE + str);
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void getSDKInfo(final Object obj, final String str) {
        if (w.b()) {
            super.getSDKInfo(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.17
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.getSDKInfo(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void getFileInfo(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.getFileInfo(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.18
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.getFileInfo(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void loadads(final Object obj, final String str) {
        if (w.b()) {
            super.loadads(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.19
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.loadads(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void reactDeveloper(final Object obj, final String str) {
        if (w.b()) {
            super.reactDeveloper(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.20
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.reactDeveloper(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void reportUrls(final Object obj, final String str) {
        if (w.b()) {
            super.reportUrls(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.21
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.reportUrls(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void createWebview(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.createWebview(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.22
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.createWebview(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void createPlayerView(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.createPlayerView(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.24
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.createPlayerView(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void createSubPlayTemplateView(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.createSubPlayTemplateView(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.25
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.createSubPlayTemplateView(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void destroyComponent(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.destroyComponent(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.26
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.destroyComponent(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void getComponentOptions(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.getComponentOptions(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.27
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.getComponentOptions(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void setViewRect(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.setViewRect(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.28
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.setViewRect(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void removeFromSuperView(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.removeFromSuperView(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.29
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.removeFromSuperView(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void appendSubView(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.appendSubView(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.30
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.appendSubView(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void appendViewTo(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.appendViewTo(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.31
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.appendViewTo(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void bringViewToFront(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.bringViewToFront(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.32
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.bringViewToFront(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void hideView(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.hideView(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.33
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.hideView(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void showView(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.showView(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.35
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.showView(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void setViewBgColor(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.setViewBgColor(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.36
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.setViewBgColor(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void setViewAlpha(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.setViewAlpha(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.37
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.setViewAlpha(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void setViewScale(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.setViewScale(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.38
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.setViewScale(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void insertViewAbove(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.insertViewAbove(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.39
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.insertViewAbove(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void insertViewBelow(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.insertViewBelow(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.40
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.insertViewBelow(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void webviewLoad(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.webviewLoad(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.41
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.webviewLoad(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void webviewReload(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.webviewReload(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.42
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.webviewReload(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void webviewGoBack(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.webviewGoBack(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.43
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.webviewGoBack(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void webviewGoForward(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.webviewGoForward(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.44
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.webviewGoForward(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playerPlay(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.playerPlay(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.46
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.playerPlay(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playerPause(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.playerPause(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.47
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.playerPause(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playerResume(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.playerResume(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.48
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.playerResume(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playerStop(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.playerStop(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.49
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.playerStop(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playerUpdateFrame(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.playerUpdateFrame(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.50
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.playerUpdateFrame(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playerMute(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.playerMute(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.51
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.playerMute(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playerUnmute(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.playerUnmute(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.52
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.playerUnmute(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playerGetMuteState(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.playerGetMuteState(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.53
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.playerGetMuteState(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playerSetSource(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.playerSetSource(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.54
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.playerSetSource(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void playerSetRenderType(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.playerSetRenderType(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.55
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.playerSetRenderType(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void preloadSubPlayTemplateView(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.preloadSubPlayTemplateView(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.57
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.preloadSubPlayTemplateView(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void closeAd(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.closeAd(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.58
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.closeAd(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void broadcast(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.broadcast(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.59
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.broadcast(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void ivRewardAdsWithoutVideo(final Object obj, final String str) {
        if (w.b()) {
            super.ivRewardAdsWithoutVideo(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.60
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.ivRewardAdsWithoutVideo(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void setSubPlayTemplateInfo(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.setSubPlayTemplateInfo(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.61
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.setSubPlayTemplateInfo(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void webviewFireEvent(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.webviewFireEvent(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.62
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.webviewFireEvent(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void increaseOfferFrequence(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.increaseOfferFrequence(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.63
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.increaseOfferFrequence(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void handleNativeObject(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.handleNativeObject(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.64
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.handleNativeObject(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void loadingResourceStatus(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.loadingResourceStatus(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.65
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.loadingResourceStatus(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void createNativeEC(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.createNativeEC(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.66
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VideoBridge.super.createNativeEC(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void setCacheItem(final Object obj, final String str) {
        if (w.b()) {
            super.setCacheItem(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.68
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.setCacheItem(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void removeCacheItem(final Object obj, final String str) {
        if (w.b()) {
            super.removeCacheItem(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.69
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.removeCacheItem(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void getAllCache(final Object obj, final String str) {
        if (w.b()) {
            super.getAllCache(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.70
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.getAllCache(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void clearAllCache(final Object obj, final String str) {
        if (w.b()) {
            super.clearAllCache(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.71
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.clearAllCache(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void getCutout(final Object obj, final String str) {
        if (w.b()) {
            super.getCutout(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.72
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.getCutout(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void getAppSetting(final Object obj, final String str) {
        if (w.b()) {
            super.getAppSetting(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.73
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.getAppSetting(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void getRewardSetting(final Object obj, final String str) {
        if (w.b()) {
            super.getRewardSetting(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.74
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.getRewardSetting(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void getRewardUnitSetting(final Object obj, final String str) {
        if (w.b()) {
            super.getRewardUnitSetting(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.75
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.getRewardUnitSetting(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void getUnitSetting(final Object obj, final String str) {
        if (w.b()) {
            super.getUnitSetting(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.76
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.getUnitSetting(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseVideoBridge, com.mbridge.msdk.video.js.bridge.IVideoBridge
    public void getEncryptPrice(final Object obj, final String str) {
        if (w.b()) {
            super.getEncryptPrice(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.VideoBridge.77
                @Override // java.lang.Runnable
                public final void run() {
                    VideoBridge.super.getEncryptPrice(obj, str);
                }
            });
        }
    }
}
