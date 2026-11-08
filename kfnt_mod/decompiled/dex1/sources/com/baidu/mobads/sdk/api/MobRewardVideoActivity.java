package com.baidu.mobads.sdk.api;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MobRewardVideoActivity extends Activity {
    private static IActivityImpl mRewardVideo;

    public static void setActivityImp(IActivityImpl iActivityImpl) {
        mRewardVideo = iActivityImpl;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        if (mRewardVideo != null) {
            mRewardVideo.setActivity(this);
            mRewardVideo.onCreate(bundle);
        }
        super.onCreate(bundle);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        if (mRewardVideo != null) {
            mRewardVideo.onPause();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (mRewardVideo != null) {
            mRewardVideo.onResume();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        if (mRewardVideo != null) {
            mRewardVideo.onDestroy();
            mRewardVideo = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (mRewardVideo != null ? mRewardVideo.onBackPressed() : false) {
            return;
        }
        super.onBackPressed();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (mRewardVideo != null) {
            mRewardVideo.onWindowFocusChanged(z);
        }
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (mRewardVideo != null ? mRewardVideo.onTouchEvent(motionEvent) : false) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mRewardVideo != null) {
            mRewardVideo.onAttachedToWindow();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mRewardVideo != null) {
            mRewardVideo.onDetachedFromWindow();
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        if (mRewardVideo != null) {
            mRewardVideo.onStop();
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (mRewardVideo != null) {
            mRewardVideo.onNewIntent(intent);
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (mRewardVideo != null) {
            mRewardVideo.onConfigurationChanged(configuration);
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (mRewardVideo != null) {
            mRewardVideo.onSaveInstanceState(bundle);
        }
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(@NonNull Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (mRewardVideo != null) {
            mRewardVideo.onRestoreInstanceState(bundle);
        }
    }

    @Override // android.app.Activity
    public void overridePendingTransition(int i, int i2) {
        super.overridePendingTransition(i, i2);
        if (mRewardVideo != null) {
            mRewardVideo.overridePendingTransition(i, i2);
        }
    }
}
