package com.ss.union.game.sdk.common.ui.floatview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Application;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.annotation.Nullable;
import com.ss.union.game.sdk.common.ui.floatview.TouchProxy;
import com.ss.union.game.sdk.common.util.UIUtils;
import com.ss.union.game.sdk.common.util.logger.LogUtils;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class BaseFloatView implements IFloatView, TouchProxy.OnTouchEventListener {
    public static final int SCREEN_MARGIN_LEFT_RIGHT_DP = 8;
    public static final int SCREEN_MARGIN_TOP_BOTTOM_DP = 8;
    private static final int a = 100;
    private static final int b = 2000;
    private WindowManager c;
    private Animator e;
    private boolean i;
    protected Activity mAct;
    protected FloatIntent mFloatIntent;

    @Nullable
    protected IFloatViewState mFloatViewState;
    protected NoScrollFrameLayout mRoot;
    protected int mScreenHeight;
    protected int mScreenWidth;
    protected WindowManager.LayoutParams wlp = new WindowManager.LayoutParams();
    protected Handler mUiHandler = new Handler(Looper.getMainLooper());
    protected TouchProxy mTouchProxy = new TouchProxy(this);
    private Interpolator d = new DecelerateInterpolator();
    private boolean f = true;
    private boolean g = false;
    private boolean h = true;

    @Nullable
    protected abstract IFloatViewState createFloatViewStateManager();

    protected int getDefaultHideDelayTime() {
        return 2000;
    }

    protected long getMoveAnimationTime() {
        return 100L;
    }

    protected String getTag() {
        return "BaseFloatView";
    }

    public void onAfterViewMoveUp(int i, int i2) {
    }

    public void onPreViewMoveUp(int i, int i2) {
    }

    protected void onRestoreInstanceState() {
    }

    protected void onSaveInstanceState() {
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.TouchProxy.OnTouchEventListener
    public void onUp(int i, int i2) {
    }

    protected void onWindowLayoutParamsCreated() {
    }

    protected abstract void setContent();

    public static int getScreenWidth(Activity activity) {
        WindowManager windowManager = (WindowManager) activity.getSystemService("window");
        if (windowManager == null) {
            return activity.getResources().getDisplayMetrics().widthPixels;
        }
        Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
        return point.x;
    }

    public static int getScreenHeight(Activity activity) {
        WindowManager windowManager = (WindowManager) activity.getSystemService("window");
        if (windowManager == null) {
            return activity.getResources().getDisplayMetrics().heightPixels;
        }
        Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
        return point.y;
    }

    protected void attachIntent(FloatIntent floatIntent) {
        this.mFloatViewState = createFloatViewStateManager();
        parseFloatIntent(floatIntent);
        this.mRoot = new NoScrollFrameLayout(this.mAct);
        this.mRoot.setTouchProxy(this.mTouchProxy);
        this.c = (WindowManager) this.mAct.getSystemService("window");
    }

    protected void onCreateView() {
        setContent();
        initWindowLayoutParams();
        onWindowLayoutParamsCreated();
    }

    protected void onViewCreated() {
        try {
            this.c.addView(this.mRoot, this.wlp);
        } catch (Throwable th) {
            th.printStackTrace();
            LogUtils.log(getTag(), "add  " + getTag());
        }
        this.g = true;
        if (this.h) {
            this.mAct.getApplication().registerActivityLifecycleCallbacks(new a(this.mAct, this));
        }
    }

    protected void parseFloatIntent(FloatIntent floatIntent) {
        this.mFloatIntent = floatIntent;
        this.mAct = floatIntent.activity;
    }

    public void setSlideEnabled(boolean z) {
        this.f = z;
        this.mRoot.setSlideEnabled(z);
    }

    public View getRootView() {
        return this.mRoot;
    }

    protected void initWindowLayoutParams() {
        this.wlp.width = -2;
        this.wlp.height = -2;
        this.wlp.type = 2;
        this.wlp.format = 1;
        this.wlp.flags = 520;
        this.wlp.gravity = 51;
        this.mScreenWidth = getScreenWidth(this.mAct);
        this.mScreenHeight = getScreenHeight(this.mAct);
        this.mRoot.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        this.wlp.x = (this.mScreenWidth - this.mRoot.getMeasuredWidth()) - UIUtils.dip2Px(8.0f);
        this.wlp.y = (this.mScreenHeight / 3) - (this.mRoot.getMeasuredHeight() / 2);
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.IFloatView
    public void onResumed() {
        try {
            this.mRoot.setVisibility(0);
        } catch (Throwable th) {
            th.printStackTrace();
            LogUtils.log(6, getTag(), "show fail " + getTag(), th);
        }
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.IFloatView
    public void onPaused() {
        try {
            this.mRoot.setVisibility(8);
        } catch (Throwable th) {
            th.printStackTrace();
            LogUtils.log(6, getTag(), "hide fail" + getTag(), th);
        }
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.IFloatView
    public boolean isDestroyed() {
        return this.i;
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.IFloatView
    public void onDestroyed() {
        try {
            this.mUiHandler.removeCallbacksAndMessages(null);
            if (this.g) {
                this.c.removeViewImmediate(this.mRoot);
            }
            releaseAnimator();
            this.mAct = null;
            this.mFloatIntent = null;
            this.i = true;
        } catch (Throwable th) {
            th.printStackTrace();
            LogUtils.log(6, getTag(), "destroy fail" + getTag(), th);
        }
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.TouchProxy.OnTouchEventListener
    public void onMove(int i, int i2, int i3, int i4) {
        this.wlp.x += i3;
        this.wlp.y += i4;
        onPreViewMoveUp(this.wlp.x, this.wlp.y);
        this.c.updateViewLayout(this.mRoot, this.wlp);
        onAfterViewMoveUp(this.wlp.x, this.wlp.y);
    }

    protected void detachView() {
        try {
            try {
                this.c.removeViewImmediate(this.mRoot);
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            this.c.removeView(this.mRoot);
        }
    }

    protected void attachView() {
        try {
            this.c.addView(this.mRoot, this.wlp);
        } catch (Throwable unused) {
        }
    }

    protected void updateView() {
        try {
            this.c.updateViewLayout(this.mRoot, this.wlp);
        } catch (Throwable th) {
            LogUtils.log(6, getTag(), "updateView fail: " + th.getMessage(), th);
        }
    }

    protected Animator animateX(int i) {
        return animateX(i, 0L);
    }

    protected void hideHalfAfterDelayTime() {
        this.mUiHandler.postDelayed(new Runnable() { // from class: com.ss.union.game.sdk.common.ui.floatview.BaseFloatView.1
            @Override // java.lang.Runnable
            public void run() {
                BaseFloatView.this.hideHalf(0);
            }
        }, getDefaultHideDelayTime());
    }

    protected void hideHalf(int i) {
        if (this.wlp.x < this.mScreenWidth / 2) {
            a(true, i);
        } else {
            a(false, i);
        }
    }

    private void a(boolean z, int i) {
        if (this.mFloatViewState != null) {
            this.mFloatViewState.saveX(this.wlp.x);
            this.mFloatViewState.saveY(this.wlp.y);
        }
        this.wlp.flags |= 512;
        animateX(z ? (-this.mRoot.getWidth()) / 2 : this.mScreenWidth - (this.mRoot.getWidth() / 2), i);
    }

    protected Animator animateX(final int i, long j) {
        releaseAnimator();
        final int i2 = this.wlp.x;
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(i2, i);
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ss.union.game.sdk.common.ui.floatview.BaseFloatView.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BaseFloatView.this.wlp.x = (int) (i2 + (valueAnimator.getAnimatedFraction() * (i - i2)));
                BaseFloatView.this.updateView();
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(valueAnimatorOfInt);
        animatorSet.setInterpolator(this.d);
        animatorSet.setDuration(getMoveAnimationTime());
        animatorSet.setStartDelay(j);
        animatorSet.start();
        this.e = animatorSet;
        return this.e;
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.TouchProxy.OnTouchEventListener
    public void onDown(int i, int i2) {
        this.wlp.flags &= -513;
        releaseAnimator();
        releaseDelayRunnable();
    }

    protected void releaseAnimator() {
        if (this.e != null) {
            this.e.removeAllListeners();
            this.e.cancel();
            this.e = null;
        }
    }

    protected void releaseDelayRunnable() {
        if (this.mUiHandler != null) {
            this.mUiHandler.removeCallbacksAndMessages(null);
        }
    }

    protected final int dp2px(int i) {
        return UIUtils.dip2Px(i);
    }

    private static class a implements Application.ActivityLifecycleCallbacks {
        private WeakReference<IFloatView> a;
        private WeakReference<Activity> b;

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }

        public a(Activity activity, IFloatView iFloatView) {
            this.a = new WeakReference<>(iFloatView);
            this.b = new WeakReference<>(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            IFloatView iFloatView;
            if (this.b == null || this.b.get() != activity || (iFloatView = this.a.get()) == null) {
                return;
            }
            iFloatView.onResumed();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            IFloatView iFloatView;
            if (this.b == null || this.b.get() != activity || (iFloatView = this.a.get()) == null) {
                return;
            }
            iFloatView.onPaused();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (this.b == null || this.b.get() != activity) {
                return;
            }
            IFloatView iFloatView = this.a.get();
            if (iFloatView != null) {
                try {
                    iFloatView.onDestroyed();
                } catch (Exception e) {
                    LogUtils.log("float View destroy fail:" + e.getMessage());
                }
            }
            if (activity != null) {
                activity.getApplication().unregisterActivityLifecycleCallbacks(this);
            }
        }
    }
}
