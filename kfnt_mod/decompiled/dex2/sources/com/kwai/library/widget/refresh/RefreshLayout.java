package com.kwai.library.widget.refresh;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class RefreshLayout extends ViewGroup implements NestedScrollingChild, NestedScrollingParent {
    private static final int ANIMATE_TO_REFRESH_DURATION = 500;
    private static final int ANIMATE_TO_START_DURATION = 300;
    private static final float DECELERATE_INTERPOLATION_FACTOR = 2.0f;
    public static final int DEFAULT_ANIMATE_DURATION = 400;
    private static final int DEFAULT_REFRESH_SIZE_DP = 70;
    private static final int DEFAULT_REFRESH_TARGET_OFFSET_DP = 70;
    private static final int INVALID_INDEX = -1;
    private static final int INVALID_POINTER = -1;
    private static final Handler UI_HANDLER = new Handler(Looper.getMainLooper());
    private final String TAG;
    private int mActivePointerId;
    private int mAnimateToRefreshDuration;
    private Interpolator mAnimateToRefreshInterpolator;
    private final Animation mAnimateToRefreshingAnimation;
    private final Animation mAnimateToStartAnimation;
    private int mAnimateToStartDuration;
    private Interpolator mAnimateToStartInterpolator;
    private float mCurrentTouchOffsetY;
    private boolean mDispatchTargetTouchDown;
    private com.kwai.library.widget.refresh.c mDragDistanceConverter;
    private int mFrom;
    private float mInitialDownY;
    private float mInitialMotionY;
    private float mInitialScrollY;
    private boolean mIsAnimatingToStart;
    private boolean mIsBeingDragged;
    private boolean mIsFitRefresh;
    private boolean mIsRefreshing;
    private boolean mIsStopNestScrollWhenUpOrCancel;
    private boolean mNestedScrollInProgress;
    private final NestedScrollingChildHelper mNestedScrollingChildHelper;
    private final NestedScrollingParentHelper mNestedScrollingParentHelper;
    private boolean mNotifyListener;
    private b mOnRefreshListener;
    private c mOnRefreshStatusListener;
    private List<c> mOnRefreshStatusListeners;
    private d mOnScrollInterceptor;
    private boolean mOnlySupportPull;
    private final int[] mParentOffsetInWindow;
    private final int[] mParentScrollConsumed;
    private float mRefreshInitialOffset;
    private float mRefreshResistanceIncremental;
    private float mRefreshResistanceRate;
    protected f mRefreshStatus;
    private RefreshStyle mRefreshStyle;
    protected float mRefreshTargetOffset;
    protected View mRefreshView;
    private int mRefreshViewIndex;
    private boolean mRefreshViewMeasured;
    protected int mRefreshViewSize;
    private ValueAnimator mRefreshingAnimator;
    private final Animation.AnimationListener mRefreshingListener;
    protected final Animation.AnimationListener mResetListener;
    private boolean mShowRefreshView;
    private View mStateView;
    private View mTarget;
    protected float mTargetOrRefreshViewOffsetY;
    private float mTotalUnconsumed;
    private int mTouchSlop;
    private boolean mUsingCustomRefreshInitialOffset;
    private boolean mUsingCustomRefreshTargetOffset;

    public enum RefreshStyle {
        NORMAL,
        PINNED,
        FLOAT
    }

    public static class a extends ViewGroup.MarginLayoutParams {
        public a(int i, int i2) {
            super(i, i2);
        }

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public interface b {
        void onRefresh();
    }

    public interface c {
        void a();

        void a(float f, float f2, boolean z);

        void b();

        void c();
    }

    public interface d {
        boolean a(float f, boolean z);
    }

    public RefreshLayout(Context context) {
        this(context, null);
    }

    public RefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "RefreshLayout";
        this.mParentScrollConsumed = new int[2];
        this.mParentOffsetInWindow = new int[2];
        this.mRefreshViewIndex = -1;
        this.mActivePointerId = -1;
        this.mAnimateToStartDuration = ANIMATE_TO_START_DURATION;
        this.mAnimateToRefreshDuration = ANIMATE_TO_REFRESH_DURATION;
        this.mUsingCustomRefreshTargetOffset = false;
        this.mUsingCustomRefreshInitialOffset = false;
        this.mRefreshViewMeasured = false;
        this.mRefreshStyle = RefreshStyle.NORMAL;
        this.mRefreshingAnimator = null;
        this.mAnimateToStartInterpolator = new DecelerateInterpolator(DECELERATE_INTERPOLATION_FACTOR);
        this.mAnimateToRefreshInterpolator = new DecelerateInterpolator(DECELERATE_INTERPOLATION_FACTOR);
        this.mShowRefreshView = true;
        this.mIsStopNestScrollWhenUpOrCancel = true;
        this.mAnimateToRefreshingAnimation = new Animation() { // from class: com.kwai.library.widget.refresh.RefreshLayout.1
            @Override // android.view.animation.Animation
            protected void applyTransformation(float f, Transformation transformation) {
                if (RefreshLayout.this.mTarget == null) {
                    return;
                }
                if (AnonymousClass6.a[RefreshLayout.this.mRefreshStyle.ordinal()] != 1) {
                    RefreshLayout.this.animateToTargetOffset(RefreshLayout.this.mRefreshTargetOffset, RefreshLayout.this.mTarget.getTop(), f);
                } else {
                    RefreshLayout.this.animateToTargetOffset(RefreshLayout.this.mRefreshTargetOffset + RefreshLayout.this.mRefreshInitialOffset, RefreshLayout.this.mRefreshView.getTop(), f);
                }
            }
        };
        this.mAnimateToStartAnimation = new Animation() { // from class: com.kwai.library.widget.refresh.RefreshLayout.2
            @Override // android.view.animation.Animation
            protected void applyTransformation(float f, Transformation transformation) {
                RefreshLayout refreshLayout;
                float f2;
                View view;
                if (RefreshLayout.this.mTarget == null) {
                    return;
                }
                if (AnonymousClass6.a[RefreshLayout.this.mRefreshStyle.ordinal()] != 1) {
                    refreshLayout = RefreshLayout.this;
                    f2 = 0.0f;
                    view = RefreshLayout.this.mTarget;
                } else {
                    refreshLayout = RefreshLayout.this;
                    f2 = RefreshLayout.this.mRefreshInitialOffset;
                    view = RefreshLayout.this.mRefreshView;
                }
                refreshLayout.animateToTargetOffset(f2, view.getTop(), f);
            }
        };
        this.mRefreshingListener = new Animation.AnimationListener() { // from class: com.kwai.library.widget.refresh.RefreshLayout.3
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (RefreshLayout.this.mNotifyListener && ViewCompat.isAttachedToWindow(RefreshLayout.this) && RefreshLayout.this.mOnRefreshListener != null) {
                    RefreshLayout.this.mOnRefreshListener.onRefresh();
                }
                RefreshLayout.this.mIsAnimatingToStart = false;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                RefreshLayout.this.mIsAnimatingToStart = true;
                RefreshLayout.this.mRefreshStatus.b();
            }
        };
        this.mResetListener = new Animation.AnimationListener() { // from class: com.kwai.library.widget.refresh.RefreshLayout.4
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                RefreshLayout.this.reset();
                if (RefreshLayout.this.mOnRefreshStatusListener != null) {
                    RefreshLayout.this.mOnRefreshStatusListener.c();
                }
                if (RefreshLayout.this.mOnRefreshStatusListeners != null) {
                    for (int i = 0; i < RefreshLayout.this.mOnRefreshStatusListeners.size(); i++) {
                        ((c) RefreshLayout.this.mOnRefreshStatusListeners.get(i)).c();
                    }
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                RefreshLayout.this.mIsAnimatingToStart = true;
            }
        };
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.mRefreshViewSize = (int) (displayMetrics.density * 70.0f);
        this.mRefreshTargetOffset = displayMetrics.density * 70.0f;
        this.mTargetOrRefreshViewOffsetY = 0.0f;
        Log.i("RefreshLayout", "constructor: " + this.mTargetOrRefreshViewOffsetY);
        this.mRefreshInitialOffset = 0.0f;
        this.mRefreshResistanceRate = 1.0f;
        this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        this.mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
        createRefreshView(attributeSet);
        createDragDistanceConverter();
        setNestedScrollingEnabled(true);
        ViewCompat.setChildrenDrawingOrderEnabled(this, true);
    }

    private void animateToRefreshingPosition(int i, Animation.AnimationListener animationListener) {
        clearAnimation();
        if (computeAnimateToRefreshingDuration(i) <= 0) {
            animationListener.onAnimationStart(null);
            animationListener.onAnimationEnd(null);
            return;
        }
        this.mFrom = i;
        this.mAnimateToRefreshingAnimation.reset();
        this.mAnimateToRefreshingAnimation.setDuration(computeAnimateToRefreshingDuration(r0));
        this.mAnimateToRefreshingAnimation.setInterpolator(this.mAnimateToRefreshInterpolator);
        if (animationListener != null) {
            this.mAnimateToRefreshingAnimation.setAnimationListener(animationListener);
        }
        startAnimation(this.mAnimateToRefreshingAnimation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void animateToTargetOffset(float f, float f2, float f3) {
        setTargetOrRefreshViewOffsetY((int) (((int) (this.mFrom + ((f - this.mFrom) * f3))) - f2));
    }

    private boolean canChildScrollUp(View view) {
        if (view == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 14 && (view instanceof AbsListView)) {
            AbsListView absListView = (AbsListView) view;
            if (absListView.getChildCount() > 0) {
                return absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop();
            }
            return false;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (canChildScrollUp(viewGroup.getChildAt(i))) {
                    return true;
                }
            }
        }
        return ViewCompat.canScrollVertically(view, -1);
    }

    private int computeAnimateToRefreshingDuration(float f) {
        Log.i("RefreshLayout", "from -- refreshing " + f);
        if (AnonymousClass6.a[this.mRefreshStyle.ordinal()] == 1) {
            f -= this.mRefreshInitialOffset;
        }
        return (int) (Math.max(0.0f, Math.min(1.0f, Math.abs(f - this.mRefreshTargetOffset) / this.mRefreshTargetOffset)) * this.mAnimateToRefreshDuration);
    }

    private int computeAnimateToStartDuration(float f) {
        Log.i("RefreshLayout", "from -- start " + f);
        if (f < this.mRefreshInitialOffset) {
            return 0;
        }
        if (AnonymousClass6.a[this.mRefreshStyle.ordinal()] == 1) {
            f -= this.mRefreshInitialOffset;
        }
        return (int) (Math.max(0.0f, Math.min(1.0f, Math.abs(f) / this.mRefreshTargetOffset)) * this.mAnimateToStartDuration);
    }

    private void createDragDistanceConverter() {
        this.mDragDistanceConverter = onCreateDragDistanceConvert();
    }

    private void ensureTarget() {
        if (isTargetValid()) {
            return;
        }
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (!childAt.equals(this.mRefreshView) && !childAt.equals(this.mStateView)) {
                this.mTarget = childAt;
                return;
            }
        }
    }

    private void finishSpinner() {
        if (this.mIsRefreshing || this.mIsAnimatingToStart) {
            return;
        }
        if (this.mOnScrollInterceptor == null || !this.mOnScrollInterceptor.a(this.mCurrentTouchOffsetY, true)) {
            if (shouldRefresh()) {
                setRefreshing(true, true);
            } else {
                this.mIsRefreshing = false;
                animateOffsetToStartPosition((int) this.mTargetOrRefreshViewOffsetY, this.mResetListener);
            }
        }
    }

    private float getMotionEventY(MotionEvent motionEvent, int i) {
        int iFindPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, i);
        if (iFindPointerIndex < 0) {
            return -1.0f;
        }
        return MotionEventCompat.getY(motionEvent, iFindPointerIndex);
    }

    private int getTargetOrRefreshViewTop() {
        return (AnonymousClass6.a[this.mRefreshStyle.ordinal()] != 1 ? this.mTarget : this.mRefreshView).getTop();
    }

    private void initDragStatus(float f) {
        float f2 = f - this.mInitialDownY;
        if (this.mIsRefreshing && (f2 > this.mTouchSlop || this.mTargetOrRefreshViewOffsetY > 0.0f)) {
            this.mIsBeingDragged = true;
            this.mInitialMotionY = this.mInitialDownY + this.mTouchSlop;
        } else {
            if (this.mIsBeingDragged || f2 <= this.mTouchSlop) {
                return;
            }
            this.mInitialMotionY = this.mInitialDownY + this.mTouchSlop;
            this.mIsBeingDragged = true;
        }
    }

    private boolean isTargetValid() {
        for (int i = 0; i < getChildCount(); i++) {
            if (this.mTarget == getChildAt(i)) {
                return true;
            }
        }
        return false;
    }

    private void measureRefreshView(int i, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mRefreshView.getLayoutParams();
        this.mRefreshView.measure(marginLayoutParams.width == -1 ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) - marginLayoutParams.leftMargin) - marginLayoutParams.rightMargin), 1073741824) : getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin, marginLayoutParams.width), marginLayoutParams.height == -1 ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - marginLayoutParams.topMargin) - marginLayoutParams.bottomMargin), 1073741824) : getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, marginLayoutParams.height));
    }

    private void measureStateViewIfNecessary() {
        if (this.mStateView != null) {
            this.mStateView.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
        }
    }

    private void measureTarget() {
        this.mTarget.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
    }

    private void moveSpinner(float f, boolean z) {
        float fA;
        this.mCurrentTouchOffsetY = f;
        int i = 0;
        if (this.mOnScrollInterceptor == null || !this.mOnScrollInterceptor.a(f, false)) {
            if (this.mIsRefreshing) {
                fA = f > this.mRefreshTargetOffset ? this.mRefreshTargetOffset : f;
                if (fA < 0.0f) {
                    fA = 0.0f;
                }
            } else {
                fA = AnonymousClass6.a[this.mRefreshStyle.ordinal()] != 1 ? this.mDragDistanceConverter.a(f, this.mRefreshTargetOffset) : this.mRefreshInitialOffset + this.mDragDistanceConverter.a(f, this.mRefreshTargetOffset);
            }
            float f2 = this.mRefreshTargetOffset;
            if (!this.mIsRefreshing) {
                if (fA > f2 && !this.mIsFitRefresh) {
                    this.mIsFitRefresh = true;
                    this.mRefreshStatus.d();
                    if (this.mOnRefreshStatusListener != null) {
                        this.mOnRefreshStatusListener.a();
                    }
                    if (this.mOnRefreshStatusListeners != null) {
                        while (i < this.mOnRefreshStatusListeners.size()) {
                            this.mOnRefreshStatusListeners.get(i).a();
                            i++;
                        }
                    }
                } else if (fA <= f2 && this.mIsFitRefresh) {
                    this.mIsFitRefresh = false;
                    this.mRefreshStatus.e();
                    if (this.mOnRefreshStatusListener != null) {
                        this.mOnRefreshStatusListener.b();
                    }
                    if (this.mOnRefreshStatusListeners != null) {
                        while (i < this.mOnRefreshStatusListeners.size()) {
                            this.mOnRefreshStatusListeners.get(i).b();
                            i++;
                        }
                    }
                }
            }
            Log.i("RefreshLayout", f + " -- " + f2 + " -- " + fA + " -- " + this.mTargetOrRefreshViewOffsetY + " -- " + this.mRefreshTargetOffset);
            setTargetOrRefreshViewOffsetY((int) (fA - this.mTargetOrRefreshViewOffsetY), z);
        }
    }

    private void onNewerPointerDown(MotionEvent motionEvent) {
        this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, MotionEventCompat.getActionIndex(motionEvent));
        this.mInitialMotionY = getMotionEventY(motionEvent, this.mActivePointerId) - this.mCurrentTouchOffsetY;
        Log.i("RefreshLayout", " onDown " + this.mInitialMotionY);
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.mActivePointerId) {
            this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex == 0 ? 1 : 0);
        }
        this.mInitialMotionY = getMotionEventY(motionEvent, this.mActivePointerId) - this.mCurrentTouchOffsetY;
        Log.i("RefreshLayout", " onUp " + this.mInitialMotionY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reset() {
        setTargetOrRefreshViewToInitial();
        this.mCurrentTouchOffsetY = 0.0f;
        this.mRefreshResistanceIncremental = 0.0f;
        this.mRefreshStatus.a();
        this.mRefreshView.setVisibility(8);
        this.mIsRefreshing = false;
        this.mIsAnimatingToStart = false;
        Log.i("RefreshLayout", "reset");
    }

    private void resetTouchEvent() {
        this.mInitialScrollY = 0.0f;
        this.mIsBeingDragged = false;
        this.mDispatchTargetTouchDown = false;
        this.mActivePointerId = -1;
    }

    private int reviseRefreshViewLayoutTop(int i) {
        switch (this.mRefreshStyle) {
            case FLOAT:
            default:
                return i + ((int) this.mTargetOrRefreshViewOffsetY);
            case PINNED:
                return i;
        }
    }

    private int reviseTargetLayoutTop(int i) {
        switch (this.mRefreshStyle) {
            case FLOAT:
                return i;
            case PINNED:
            default:
                return i + ((int) this.mTargetOrRefreshViewOffsetY);
        }
    }

    private void setRefreshing(boolean z, boolean z2) {
        if (this.mIsRefreshing != z) {
            this.mNotifyListener = z2;
            this.mIsRefreshing = z;
            if (z) {
                animateToRefreshingPosition((int) this.mTargetOrRefreshViewOffsetY, this.mRefreshingListener);
            } else {
                this.mRefreshStatus.c();
                UI_HANDLER.postDelayed(new Runnable() { // from class: com.kwai.library.widget.refresh.RefreshLayout.5
                    @Override // java.lang.Runnable
                    public void run() {
                        RefreshLayout.this.animateOffsetToStartPosition((int) RefreshLayout.this.mTargetOrRefreshViewOffsetY, RefreshLayout.this.mResetListener);
                    }
                }, this.mRefreshStatus.f());
            }
        }
    }

    private void setTargetOrRefreshViewOffsetY(int i, boolean z) {
        View view;
        if (this.mTarget == null) {
            return;
        }
        switch (this.mRefreshStyle) {
            case FLOAT:
                this.mRefreshView.offsetTopAndBottom(i);
                view = this.mRefreshView;
                this.mTargetOrRefreshViewOffsetY = view.getTop();
                break;
            case PINNED:
                this.mTarget.offsetTopAndBottom(i);
                if (this.mStateView != null) {
                    this.mStateView.offsetTopAndBottom(i);
                }
                view = this.mTarget;
                this.mTargetOrRefreshViewOffsetY = view.getTop();
                break;
            default:
                this.mTarget.offsetTopAndBottom(i);
                if (this.mStateView != null) {
                    this.mStateView.offsetTopAndBottom(i);
                } else {
                    float f = (i / this.mRefreshResistanceRate) + this.mRefreshResistanceIncremental;
                    int i2 = (int) f;
                    this.mRefreshResistanceIncremental = f - i2;
                    this.mRefreshView.offsetTopAndBottom(i2);
                }
                this.mTargetOrRefreshViewOffsetY = this.mTarget.getTop();
                Log.i("RefreshLayout", "refresh style" + this.mTargetOrRefreshViewOffsetY);
                break;
        }
        Log.i("RefreshLayout", "current offset" + this.mTargetOrRefreshViewOffsetY);
        if (AnonymousClass6.a[this.mRefreshStyle.ordinal()] != 1) {
            this.mRefreshStatus.a(this.mTargetOrRefreshViewOffsetY, this.mTargetOrRefreshViewOffsetY / this.mRefreshTargetOffset);
            if (this.mOnRefreshStatusListener != null) {
                this.mOnRefreshStatusListener.a(this.mTargetOrRefreshViewOffsetY, this.mTargetOrRefreshViewOffsetY / this.mRefreshTargetOffset, z);
            }
            if (this.mOnRefreshStatusListeners != null) {
                for (int i3 = 0; i3 < this.mOnRefreshStatusListeners.size(); i3++) {
                    this.mOnRefreshStatusListeners.get(i3).a(this.mTargetOrRefreshViewOffsetY, this.mTargetOrRefreshViewOffsetY / this.mRefreshTargetOffset, z);
                }
            }
        } else {
            this.mRefreshStatus.a(this.mTargetOrRefreshViewOffsetY, (this.mTargetOrRefreshViewOffsetY - this.mRefreshInitialOffset) / this.mRefreshTargetOffset);
            if (this.mOnRefreshStatusListener != null) {
                this.mOnRefreshStatusListener.a(this.mTargetOrRefreshViewOffsetY, (this.mTargetOrRefreshViewOffsetY - this.mRefreshInitialOffset) / this.mRefreshTargetOffset, z);
            }
            if (this.mOnRefreshStatusListeners != null) {
                for (int i4 = 0; i4 < this.mOnRefreshStatusListeners.size(); i4++) {
                    this.mOnRefreshStatusListeners.get(i4).a(this.mTargetOrRefreshViewOffsetY, (this.mTargetOrRefreshViewOffsetY - this.mRefreshInitialOffset) / this.mRefreshTargetOffset, z);
                }
            }
        }
        if (this.mShowRefreshView && this.mRefreshView.getVisibility() != 0) {
            this.mRefreshView.setVisibility(0);
        }
        invalidate();
    }

    private void setTargetOrRefreshViewToInitial() {
        setTargetOrRefreshViewOffsetY((int) ((AnonymousClass6.a[this.mRefreshStyle.ordinal()] != 1 ? 0.0f : this.mRefreshInitialOffset) - this.mTargetOrRefreshViewOffsetY));
    }

    public void addOnRefreshStatusListener(c cVar) {
        if (this.mOnRefreshStatusListeners == null) {
            this.mOnRefreshStatusListeners = new ArrayList();
        }
        if (cVar == null || this.mOnRefreshStatusListeners.contains(cVar)) {
            return;
        }
        this.mOnRefreshStatusListeners.add(cVar);
    }

    protected void animateOffsetToStartPosition(int i, Animation.AnimationListener animationListener) {
        clearAnimation();
        if (computeAnimateToStartDuration(i) <= 0) {
            animationListener.onAnimationStart(null);
            animationListener.onAnimationEnd(null);
            return;
        }
        this.mFrom = i;
        this.mAnimateToStartAnimation.reset();
        this.mAnimateToStartAnimation.setDuration(computeAnimateToStartDuration(r0));
        this.mAnimateToStartAnimation.setInterpolator(this.mAnimateToStartInterpolator);
        if (animationListener != null) {
            this.mAnimateToStartAnimation.setAnimationListener(animationListener);
        }
        startAnimation(this.mAnimateToStartAnimation);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof a;
    }

    protected void createRefreshView(AttributeSet attributeSet) {
        this.mRefreshView = onCreateRefreshView(attributeSet);
        this.mRefreshView.setVisibility(8);
        if (!(this.mRefreshView instanceof f)) {
            throw new ClassCastException("the refreshView must implement the interface IRefreshStatus");
        }
        this.mRefreshStatus = (f) this.mRefreshView;
        a aVarOnCreateLayoutParams = onCreateLayoutParams(attributeSet);
        if (aVarOnCreateLayoutParams == null) {
            aVarOnCreateLayoutParams = new a(this.mRefreshViewSize, this.mRefreshViewSize);
        }
        addView(this.mRefreshView, aVarOnCreateLayoutParams);
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.mNestedScrollingChildHelper.dispatchNestedFling(f, f2, z);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.mNestedScrollingChildHelper.dispatchNestedPreFling(f, f2);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.mNestedScrollingChildHelper.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.mNestedScrollingChildHelper.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int actionMasked;
        if (this.mIsStopNestScrollWhenUpOrCancel && ((actionMasked = MotionEventCompat.getActionMasked(motionEvent)) == 1 || actionMasked == 3)) {
            onStopNestedScroll(this);
        }
        Log.i("RefreshLayout", "dispatch " + this.mDispatchTargetTouchDown + " isRefreshing" + this.mIsRefreshing);
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public a generateDefaultLayoutParams() {
        return new a(-2, -2);
    }

    @Override // android.view.ViewGroup
    public a generateLayoutParams(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public a generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new a(layoutParams);
    }

    @Override // android.view.ViewGroup
    protected int getChildDrawingOrder(int i, int i2) {
        return AnonymousClass6.a[this.mRefreshStyle.ordinal()] != 1 ? this.mRefreshViewIndex < 0 ? i2 : i2 == 0 ? this.mRefreshViewIndex : i2 <= this.mRefreshViewIndex ? i2 - 1 : i2 : this.mRefreshViewIndex < 0 ? i2 : i2 == i - 1 ? this.mRefreshViewIndex : i2 >= this.mRefreshViewIndex ? i2 + 1 : i2;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    public float getRefreshTargetOffset() {
        return this.mRefreshTargetOffset;
    }

    @Nullable
    public View getStateView() {
        return this.mStateView;
    }

    public int getTargetOrRefreshViewOffset() {
        if (AnonymousClass6.a[this.mRefreshStyle.ordinal()] == 1) {
            return (int) (this.mRefreshView.getTop() - this.mRefreshInitialOffset);
        }
        if (this.mTarget == null) {
            return 0;
        }
        return this.mTarget.getTop();
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return this.mNestedScrollingChildHelper.hasNestedScrollingParent();
    }

    public void hideStateView() {
        if (this.mStateView != null && this.mStateView.getVisibility() != 8) {
            this.mStateView.setVisibility(8);
        }
        this.mStateView = null;
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return this.mNestedScrollingChildHelper.isNestedScrollingEnabled();
    }

    public boolean isRefreshing() {
        return this.mIsRefreshing;
    }

    protected abstract com.kwai.library.widget.refresh.c onCreateDragDistanceConvert();

    protected a onCreateLayoutParams(AttributeSet attributeSet) {
        return null;
    }

    protected abstract View onCreateRefreshView(AttributeSet attributeSet);

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        reset();
        clearAnimation();
        super.onDetachedFromWindow();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        ensureTarget();
        if (this.mTarget == null) {
            return false;
        }
        if ((this.mTarget instanceof com.kwai.library.widget.refresh.d) && !((com.kwai.library.widget.refresh.d) this.mTarget).canPullToRefresh()) {
            return false;
        }
        if (AnonymousClass6.a[this.mRefreshStyle.ordinal()] != 1) {
            if (!isEnabled() || (canChildScrollUp(this.mTarget) && !this.mDispatchTargetTouchDown)) {
                return false;
            }
        } else if (!isEnabled() || canChildScrollUp(this.mTarget) || this.mIsRefreshing || this.mNestedScrollInProgress) {
            return false;
        }
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked != 6) {
            switch (actionMasked) {
                case 0:
                    this.mActivePointerId = motionEvent.getPointerId(0);
                    this.mIsBeingDragged = false;
                    float motionEventY = getMotionEventY(motionEvent, this.mActivePointerId);
                    if (motionEventY != -1.0f) {
                        if (this.mAnimateToRefreshingAnimation.hasEnded() && this.mAnimateToStartAnimation.hasEnded()) {
                            this.mIsAnimatingToStart = false;
                        }
                        this.mInitialDownY = motionEventY;
                        this.mInitialScrollY = this.mTargetOrRefreshViewOffsetY;
                        this.mDispatchTargetTouchDown = false;
                        break;
                    } else {
                        return false;
                    }
                    break;
                case 1:
                case 3:
                    this.mIsBeingDragged = false;
                    this.mActivePointerId = -1;
                    break;
                case 2:
                    if (this.mActivePointerId != -1) {
                        float motionEventY2 = getMotionEventY(motionEvent, this.mActivePointerId);
                        if (motionEventY2 != -1.0f) {
                            initDragStatus(motionEventY2);
                            if (this.mRefreshingAnimator != null && this.mRefreshingAnimator.isRunning()) {
                                this.mRefreshingAnimator.cancel();
                                this.mRefreshStatus.c();
                                animateOffsetToStartPosition((int) this.mTargetOrRefreshViewOffsetY, this.mResetListener);
                                break;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                    break;
            }
        } else {
            onSecondaryPointerUp(motionEvent);
        }
        return this.mIsBeingDragged;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (getChildCount() == 0) {
            return;
        }
        ensureTarget();
        if (this.mTarget == null) {
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (this.mTarget.getVisibility() != 8 || (this.mStateView != null && this.mStateView.getVisibility() != 8)) {
            int iReviseTargetLayoutTop = reviseTargetLayoutTop(getPaddingTop());
            int paddingLeft = getPaddingLeft();
            int paddingLeft2 = ((paddingLeft + measuredWidth) - getPaddingLeft()) - getPaddingRight();
            int paddingTop = ((measuredHeight + iReviseTargetLayoutTop) - getPaddingTop()) - getPaddingBottom();
            if (this.mTarget.getVisibility() != 8) {
                this.mTarget.layout(paddingLeft, iReviseTargetLayoutTop, paddingLeft2, paddingTop);
            }
            if (this.mStateView != null && this.mStateView.getVisibility() != 8) {
                this.mStateView.layout(paddingLeft, iReviseTargetLayoutTop, paddingLeft2, paddingTop);
            }
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mRefreshView.getLayoutParams();
        int measuredWidth2 = (measuredWidth - this.mRefreshView.getMeasuredWidth()) / 2;
        int iReviseRefreshViewLayoutTop = (reviseRefreshViewLayoutTop((int) this.mRefreshInitialOffset) + marginLayoutParams.topMargin) - marginLayoutParams.bottomMargin;
        this.mRefreshView.layout(measuredWidth2, iReviseRefreshViewLayoutTop, (measuredWidth + this.mRefreshView.getMeasuredWidth()) / 2, this.mRefreshView.getMeasuredHeight() + iReviseRefreshViewLayoutTop);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        ensureTarget();
        if (this.mTarget == null) {
            return;
        }
        measureTarget();
        measureStateViewIfNecessary();
        measureRefreshView(i, i2);
        if (!this.mRefreshViewMeasured && !this.mUsingCustomRefreshInitialOffset) {
            switch (this.mRefreshStyle) {
                case FLOAT:
                    float f = -this.mRefreshView.getMeasuredHeight();
                    this.mRefreshInitialOffset = f;
                    this.mTargetOrRefreshViewOffsetY = f;
                    break;
                case PINNED:
                    this.mRefreshInitialOffset = 0.0f;
                    this.mTargetOrRefreshViewOffsetY = 0.0f;
                    break;
                default:
                    this.mTargetOrRefreshViewOffsetY = 0.0f;
                    this.mRefreshInitialOffset = -this.mRefreshView.getMeasuredHeight();
                    break;
            }
        }
        if (!this.mRefreshViewMeasured && !this.mUsingCustomRefreshTargetOffset && this.mRefreshTargetOffset < this.mRefreshView.getMeasuredHeight()) {
            this.mRefreshTargetOffset = this.mRefreshView.getMeasuredHeight();
        }
        this.mRefreshViewMeasured = true;
        this.mRefreshViewIndex = -1;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            if (getChildAt(i3) == this.mRefreshView) {
                this.mRefreshViewIndex = i3;
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        return dispatchNestedFling(f, f2, z);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f, float f2) {
        return dispatchNestedPreFling(f, f2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        if (i2 > 0 && this.mTotalUnconsumed > 0.0f) {
            float f = i2;
            if (f > this.mTotalUnconsumed) {
                iArr[1] = i2 - ((int) this.mTotalUnconsumed);
                this.mTotalUnconsumed = 0.0f;
            } else {
                this.mTotalUnconsumed -= f;
                iArr[1] = i2;
            }
            Log.i("RefreshLayout", "pre scroll");
            moveSpinner(this.mTotalUnconsumed, false);
        }
        int[] iArr2 = this.mParentScrollConsumed;
        if (dispatchNestedPreScroll(i - iArr[0], i2 - iArr[1], iArr2, null)) {
            iArr[0] = iArr[0] + iArr2[0];
            iArr[1] = iArr[1] + iArr2[1];
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        dispatchNestedScroll(i, i2, i3, i4, this.mParentOffsetInWindow);
        if (i4 + this.mParentOffsetInWindow[1] < 0) {
            this.mTotalUnconsumed += Math.abs(r11);
            Log.i("RefreshLayout", "nested scroll");
            moveSpinner(this.mTotalUnconsumed, true);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.mNestedScrollingParentHelper.onNestedScrollAccepted(view, view2, i);
        startNestedScroll(i & 2);
        this.mTotalUnconsumed = 0.0f;
        this.mNestedScrollInProgress = true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i) {
        return AnonymousClass6.a[this.mRefreshStyle.ordinal()] != 1 ? isEnabled() && canChildScrollUp(this.mTarget) && (i & 2) != 0 : isEnabled() && canChildScrollUp(this.mTarget) && !this.mIsRefreshing && (i & 2) != 0;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        this.mNestedScrollingParentHelper.onStopNestedScroll(view);
        this.mNestedScrollInProgress = false;
        if (this.mTotalUnconsumed > 0.0f) {
            finishSpinner();
            this.mTotalUnconsumed = 0.0f;
        }
        stopNestedScroll();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float targetOrRefreshViewTop;
        String str;
        StringBuilder sb;
        float f;
        ensureTarget();
        if (this.mTarget == null) {
            return false;
        }
        if (AnonymousClass6.a[this.mRefreshStyle.ordinal()] != 1) {
            if (!isEnabled() || (canChildScrollUp(this.mTarget) && !this.mDispatchTargetTouchDown)) {
                return false;
            }
        } else if (!isEnabled() || canChildScrollUp(this.mTarget) || this.mNestedScrollInProgress) {
            return false;
        }
        if (this.mRefreshStyle == RefreshStyle.FLOAT && (canChildScrollUp(this.mTarget) || this.mNestedScrollInProgress)) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                this.mIsBeingDragged = false;
                return true;
            case 1:
            case 3:
                if (this.mActivePointerId == -1 || getMotionEventY(motionEvent, this.mActivePointerId) == -1.0f) {
                    resetTouchEvent();
                    return false;
                }
                if (!this.mIsRefreshing && !this.mIsAnimatingToStart) {
                    resetTouchEvent();
                    finishSpinner();
                    return false;
                }
                if (this.mDispatchTargetTouchDown) {
                    this.mTarget.dispatchTouchEvent(motionEvent);
                }
                resetTouchEvent();
                return false;
            case 2:
                if (this.mActivePointerId == -1) {
                    return false;
                }
                float motionEventY = getMotionEventY(motionEvent, this.mActivePointerId);
                if (motionEventY == -1.0f) {
                    return false;
                }
                if (this.mIsAnimatingToStart) {
                    targetOrRefreshViewTop = getTargetOrRefreshViewTop();
                    this.mInitialMotionY = motionEventY;
                    this.mInitialScrollY = targetOrRefreshViewTop;
                    str = "RefreshLayout";
                    sb = new StringBuilder();
                    sb.append("animatetostart overscrolly ");
                    sb.append(targetOrRefreshViewTop);
                    sb.append(" -- ");
                    f = this.mInitialMotionY;
                } else {
                    targetOrRefreshViewTop = (motionEventY - this.mInitialMotionY) + this.mInitialScrollY;
                    str = "RefreshLayout";
                    sb = new StringBuilder();
                    sb.append("overscrolly ");
                    sb.append(targetOrRefreshViewTop);
                    sb.append(" --");
                    sb.append(this.mInitialMotionY);
                    sb.append(" -- ");
                    f = this.mInitialScrollY;
                }
                sb.append(f);
                Log.i(str, sb.toString());
                if (this.mIsRefreshing) {
                    if (targetOrRefreshViewTop > 0.0f) {
                        if (targetOrRefreshViewTop > 0.0f && targetOrRefreshViewTop < this.mRefreshTargetOffset && this.mDispatchTargetTouchDown) {
                            motionEvent = MotionEvent.obtain(motionEvent);
                            motionEvent.setAction(3);
                            this.mDispatchTargetTouchDown = false;
                        }
                        Log.i("RefreshLayout", "moveSpinner refreshing -- " + this.mInitialScrollY + " -- " + (motionEventY - this.mInitialMotionY));
                        moveSpinner(targetOrRefreshViewTop, true);
                    } else if (!this.mDispatchTargetTouchDown) {
                        motionEvent = MotionEvent.obtain(motionEvent);
                        motionEvent.setAction(0);
                        this.mDispatchTargetTouchDown = true;
                    }
                    this.mTarget.dispatchTouchEvent(motionEvent);
                    Log.i("RefreshLayout", "moveSpinner refreshing -- " + this.mInitialScrollY + " -- " + (motionEventY - this.mInitialMotionY));
                    moveSpinner(targetOrRefreshViewTop, true);
                } else if (!this.mIsBeingDragged) {
                    Log.i("RefreshLayout", "is not Being Dragged, init drag status");
                    initDragStatus(motionEventY);
                } else {
                    if (targetOrRefreshViewTop <= 0.0f) {
                        Log.i("RefreshLayout", "is Being Dragged, but over scroll Y < 0");
                        return false;
                    }
                    moveSpinner(targetOrRefreshViewTop, true);
                    Log.i("RefreshLayout", "moveSpinner not refreshing -- " + this.mInitialScrollY + " -- " + (motionEventY - this.mInitialMotionY));
                }
                return true;
            case 4:
            default:
                return true;
            case 5:
                onNewerPointerDown(motionEvent);
                return true;
            case 6:
                onSecondaryPointerUp(motionEvent);
                return true;
        }
    }

    public void removeOnRefreshStatusListener(c cVar) {
        if (cVar == null || this.mOnRefreshStatusListeners == null || !this.mOnRefreshStatusListeners.contains(cVar)) {
            return;
        }
        this.mOnRefreshStatusListeners.remove(cVar);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (Build.VERSION.SDK_INT >= 21 || !(this.mTarget instanceof AbsListView)) {
            if (this.mTarget == null || ViewCompat.isNestedScrollingEnabled(this.mTarget)) {
                super.requestDisallowInterceptTouchEvent(z);
            }
        }
    }

    public void setAnimateToRefreshDuration(int i) {
        this.mAnimateToRefreshDuration = i;
    }

    public void setAnimateToRefreshInterpolator(Interpolator interpolator) {
        this.mAnimateToRefreshInterpolator = interpolator;
    }

    public void setAnimateToStartDuration(int i) {
        this.mAnimateToStartDuration = i;
    }

    public void setAnimateToStartInterpolator(Interpolator interpolator) {
        this.mAnimateToStartInterpolator = interpolator;
    }

    public void setDragDistanceConverter(@NonNull com.kwai.library.widget.refresh.c cVar) {
        if (cVar == null) {
            throw new NullPointerException("the dragDistanceConverter can't be null");
        }
        this.mDragDistanceConverter = cVar;
    }

    public void setIsStopNestScrollWhenUpOrCancel(boolean z) {
        this.mIsStopNestScrollWhenUpOrCancel = z;
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        this.mNestedScrollingChildHelper.setNestedScrollingEnabled(z);
    }

    public void setOnRefreshListener(b bVar) {
        this.mOnRefreshListener = bVar;
    }

    public void setOnRefreshStatusListener(c cVar) {
        this.mOnRefreshStatusListener = cVar;
    }

    public void setOnScrollInterceptor(d dVar) {
        this.mOnScrollInterceptor = dVar;
    }

    public void setOnlySupportPull(boolean z) {
        this.mOnlySupportPull = z;
    }

    public void setRefreshInitialOffset(float f) {
        this.mRefreshInitialOffset = f;
        this.mUsingCustomRefreshInitialOffset = true;
        requestLayout();
    }

    public void setRefreshStyle(@NonNull RefreshStyle refreshStyle) {
        this.mRefreshStyle = refreshStyle;
    }

    public void setRefreshTargetOffset(float f) {
        this.mRefreshTargetOffset = f;
        this.mUsingCustomRefreshTargetOffset = true;
        requestLayout();
    }

    public void setRefreshing(boolean z) {
        if (this.mIsRefreshing == z) {
            return;
        }
        if (!z) {
            setRefreshing(z, false);
            return;
        }
        if (getAnimation() != null && !getAnimation().hasEnded()) {
            getAnimation().setAnimationListener(null);
            clearAnimation();
            reset();
        }
        this.mIsRefreshing = z;
        this.mNotifyListener = false;
        animateToRefreshingPosition((int) this.mTargetOrRefreshViewOffsetY, this.mRefreshingListener);
    }

    public void setShowRefreshView(boolean z) {
        setOnlySupportPull(!z);
        this.mShowRefreshView = z;
    }

    public void setTargetOrRefreshViewOffsetY(int i) {
        setTargetOrRefreshViewOffsetY(i, false);
    }

    protected boolean shouldRefresh() {
        return !this.mOnlySupportPull && ((float) getTargetOrRefreshViewOffset()) > this.mRefreshTargetOffset;
    }

    public void showStateView(@Nullable View view) {
        if (view == this.mStateView) {
            return;
        }
        if (view != null && view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        if (view != null) {
            view.setVisibility(0);
            addView(view);
        }
        this.mStateView = view;
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i) {
        return this.mNestedScrollingChildHelper.startNestedScroll(i);
    }

    @Override // android.view.View
    public void stopNestedScroll() {
        this.mNestedScrollingChildHelper.stopNestedScroll();
    }
}
