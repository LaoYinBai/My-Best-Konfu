package com.mbridge.msdk.video.js.bridge;

import android.os.Handler;
import android.os.Looper;
import com.mbridge.msdk.foundation.tools.w;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class RewardJs extends BaseRewardJs implements IRewardBridge {
    private Handler b = new Handler(Looper.getMainLooper());

    @Override // com.mbridge.msdk.video.js.bridge.BaseRewardJs, com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void getEndScreenInfo(final Object obj, final String str) {
        if (w.b()) {
            super.getEndScreenInfo(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.RewardJs.1
                @Override // java.lang.Runnable
                public final void run() {
                    RewardJs.super.getEndScreenInfo(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseRewardJs, com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void install(final Object obj, final String str) {
        if (w.b()) {
            super.install(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.RewardJs.3
                @Override // java.lang.Runnable
                public final void run() {
                    RewardJs.super.install(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseRewardJs, com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void notifyCloseBtn(final Object obj, final String str) {
        if (w.b()) {
            super.notifyCloseBtn(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.RewardJs.4
                @Override // java.lang.Runnable
                public final void run() {
                    RewardJs.super.notifyCloseBtn(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseRewardJs, com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void toggleCloseBtn(final Object obj, final String str) {
        if (w.b()) {
            super.toggleCloseBtn(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.RewardJs.5
                @Override // java.lang.Runnable
                public final void run() {
                    RewardJs.super.toggleCloseBtn(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseRewardJs, com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void triggerCloseBtn(final Object obj, final String str) {
        if (w.b()) {
            super.triggerCloseBtn(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.RewardJs.6
                @Override // java.lang.Runnable
                public final void run() {
                    RewardJs.super.getEndScreenInfo(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseRewardJs, com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void setOrientation(final Object obj, final String str) {
        if (w.b()) {
            super.setOrientation(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.RewardJs.7
                @Override // java.lang.Runnable
                public final void run() {
                    RewardJs.super.setOrientation(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseRewardJs, com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void handlerPlayableException(final Object obj, final String str) {
        if (w.b()) {
            super.handlerPlayableException(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.RewardJs.8
                @Override // java.lang.Runnable
                public final void run() {
                    RewardJs.super.handlerPlayableException(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseRewardJs, com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void openURL(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.openURL(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.RewardJs.9
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    RewardJs.super.openURL(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseRewardJs, com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void cai(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.cai(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.RewardJs.10
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    RewardJs.super.cai(obj, str);
                }
            });
        }
    }

    @Override // com.mbridge.msdk.video.js.bridge.BaseRewardJs, com.mbridge.msdk.video.js.bridge.IRewardBridge
    public void gial(final Object obj, final String str) throws JSONException {
        if (w.b()) {
            super.gial(obj, str);
        } else {
            this.b.post(new Runnable() { // from class: com.mbridge.msdk.video.js.bridge.RewardJs.2
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    RewardJs.super.gial(obj, str);
                }
            });
        }
    }
}
