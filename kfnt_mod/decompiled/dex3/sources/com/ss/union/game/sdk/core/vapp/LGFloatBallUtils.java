package com.ss.union.game.sdk.core.vapp;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.Keep;
import com.ss.union.game.sdk.common.ui.floatview.FloatIntent;
import com.ss.union.game.sdk.common.ui.floatview.FloatViewManager;
import com.ss.union.game.sdk.common.ui.floatview.IFloatView;
import com.ss.union.game.sdk.core.vapp.callback.LGAdVolumeRewardCallBack;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGFloatBallUtils {
    private static com.ss.union.game.sdk.core.vapp.buoy.a adVolumeProxy = com.ss.union.game.sdk.core.vapp.buoy.a.a();

    public static IFloatView createBall(Activity activity) {
        return createBall(activity, 0, 0);
    }

    public static IFloatView createBall(Activity activity, int i, int i2) {
        VLog.d("悬浮球开启状态： " + c.b());
        if (c.b()) {
            return createBallInner(activity, i, i2);
        }
        return new a();
    }

    private static IFloatView createBallInner(Activity activity, int i, int i2) {
        return FloatViewManager.getInstance().create(new FloatIntent(activity, com.ss.union.game.sdk.core.vapp.buoy.d.class).setX(i).setY(i2));
    }

    public static void tryShowAdVolumeInRewardVideoScene(Context context, LGAdVolumeRewardCallBack lGAdVolumeRewardCallBack) {
        VLog.d("悬浮球开启状态： " + c.b() + ",cp调用悬浮球进入激励视频广告模式");
        if (c.b()) {
            adVolumeProxy.a(context, lGAdVolumeRewardCallBack);
        }
    }

    public static void exitAdVolumeMode() {
        VLog.d("悬浮球开启状态： " + c.b() + ",cp调用悬浮球退出激励视频广告模式");
        if (c.b()) {
            adVolumeProxy.c();
        }
    }

    private static class a implements IFloatView {
        @Override // com.ss.union.game.sdk.common.ui.floatview.IFloatView
        public boolean isDestroyed() {
            return false;
        }

        @Override // com.ss.union.game.sdk.common.ui.floatview.IFloatView
        public void onDestroyed() {
        }

        @Override // com.ss.union.game.sdk.common.ui.floatview.IFloatView
        public void onPaused() {
        }

        @Override // com.ss.union.game.sdk.common.ui.floatview.IFloatView
        public void onResumed() {
        }

        private a() {
        }
    }
}
