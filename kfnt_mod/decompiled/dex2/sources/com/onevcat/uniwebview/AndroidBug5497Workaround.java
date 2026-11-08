package com.onevcat.uniwebview;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class AndroidBug5497Workaround {
    private FrameLayout.LayoutParams frameLayoutParams;
    private int lastOrientation;
    private Activity mActivity;
    private View mChildOfContent;
    private FrameLayout mContent;
    private float mTargetHeight;
    private int usableHeightPrevious;

    static AndroidBug5497Workaround assistFrameLayout(FrameLayout frameLayout, Activity activity) {
        return new AndroidBug5497Workaround(frameLayout, activity);
    }

    private AndroidBug5497Workaround(FrameLayout frameLayout, Activity activity) {
        this.mActivity = activity;
        this.mContent = frameLayout;
        this.mChildOfContent = frameLayout.getChildAt(0);
        this.mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.onevcat.uniwebview.AndroidBug5497Workaround.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                AndroidBug5497Workaround.this.possiblyResizeChildOfContent();
            }
        });
        this.frameLayoutParams = (FrameLayout.LayoutParams) frameLayout.getLayoutParams();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void possiblyResizeChildOfContent() {
        int i = this.mActivity.getResources().getConfiguration().orientation;
        int iComputeUsableHeight = computeUsableHeight();
        if (i != this.lastOrientation) {
            this.lastOrientation = i;
            this.usableHeightPrevious = iComputeUsableHeight;
        } else if (iComputeUsableHeight != this.usableHeightPrevious) {
            this.frameLayoutParams.height = (int) (this.mTargetHeight - overlapHeight());
            this.mContent.requestLayout();
            this.usableHeightPrevious = iComputeUsableHeight;
        }
    }

    private int computeUsableHeight() {
        Rect rect = new Rect();
        this.mChildOfContent.getWindowVisibleDisplayFrame(rect);
        return rect.bottom - rect.top;
    }

    private float contentMaxY() {
        return this.mTargetHeight + this.mChildOfContent.getY();
    }

    private float keyboardHeight() {
        return this.mChildOfContent.getRootView().getHeight() - computeUsableHeight();
    }

    private float screenHeight() {
        return this.mChildOfContent.getRootView().getHeight();
    }

    private float overlapHeight() {
        return Math.max(0.0f, (contentMaxY() + keyboardHeight()) - screenHeight());
    }

    void setTargetHeight(float f) {
        this.mTargetHeight = f;
    }
}
