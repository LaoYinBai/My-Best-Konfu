package com.tachikoma.core.component.recyclerview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.kwai.library.widget.refresh.d;
import com.ss.union.game.sdk.core.glide.request.target.Target;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CustomRecyclerView extends RecyclerView implements d {
    private boolean mCanPullToRefresh;
    private boolean mDisableScroll;
    private int mDistanceToScreenTop;
    private boolean mDistanceToScreenTopUseCache;
    private boolean mDownStop;
    private int mDownX;
    private int mDownY;

    @FixScrollDirection
    private int mFixScrollDirection;
    private TouchEventInterceptor mIgnoreTouchSwipeHandler;
    private int mMaxHeight;
    private int mTouchSlop;
    private int mUnderneathColor;
    private Rect mUnderneathRect;
    private boolean mUseCustomScrollToPosition;

    public @interface FixScrollDirection {
        public static final int HORIZONTAL = 1;
        public static final int NONE = 0;
        public static final int VERTICAL = 2;
    }

    public interface TouchEventInterceptor {
        boolean shouldInterceptTouchEvent(MotionEvent motionEvent);
    }

    public CustomRecyclerView(Context context) {
        this(context, null);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDistanceToScreenTop = Target.SIZE_ORIGINAL;
        this.mDistanceToScreenTopUseCache = true;
        this.mCanPullToRefresh = true;
        this.mFixScrollDirection = 0;
        this.mDownStop = false;
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    private void computeUnderneathRect() {
        if (this.mUnderneathRect == null) {
            this.mUnderneathRect = new Rect();
        } else {
            this.mUnderneathRect.setEmpty();
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0) {
                this.mUnderneathRect.union(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom());
            }
        }
    }

    private boolean fixInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mDownX = (int) motionEvent.getX();
            this.mDownY = (int) motionEvent.getY();
            return false;
        }
        if (actionMasked != 2) {
            return false;
        }
        int iAbs = (int) Math.abs(motionEvent.getX() - this.mDownX);
        int iAbs2 = (int) Math.abs(motionEvent.getY() - this.mDownY);
        if (iAbs <= this.mTouchSlop || iAbs2 <= this.mTouchSlop) {
            return false;
        }
        if (this.mFixScrollDirection != 1 || iAbs <= iAbs2) {
            return this.mFixScrollDirection == 2 && iAbs < iAbs2;
        }
        return true;
    }

    private void startScroll(int i, int i2, int i3) {
        RecyclerViewPositionHelper recyclerViewPositionHelperCreateHelper = RecyclerViewPositionHelper.createHelper(this);
        while (true) {
            int iFindFirstVisibleItemPosition = recyclerViewPositionHelperCreateHelper.findFirstVisibleItemPosition();
            int iFindLastVisibleItemPosition = recyclerViewPositionHelperCreateHelper.findLastVisibleItemPosition();
            if (iFindFirstVisibleItemPosition == -1 || iFindLastVisibleItemPosition == -1) {
                return;
            }
            if (this.mDistanceToScreenTop == Integer.MIN_VALUE || !this.mDistanceToScreenTopUseCache) {
                int[] iArr = new int[2];
                getLocationOnScreen(iArr);
                this.mDistanceToScreenTop = iArr[1];
            }
            if (i >= iFindFirstVisibleItemPosition && i <= iFindLastVisibleItemPosition) {
                int i4 = i - iFindFirstVisibleItemPosition;
                if (getChildCount() > i4) {
                    int[] iArr2 = new int[2];
                    getChildAt(i4).getLocationOnScreen(iArr2);
                    scrollBy(0, (iArr2[1] - this.mDistanceToScreenTop) - i3);
                    return;
                }
                return;
            }
            if (i > iFindLastVisibleItemPosition) {
                scrollBy(0, i2);
            } else {
                scrollBy(0, -i2);
            }
        }
    }

    protected void afterDetachedFromWindow() {
    }

    public boolean canPullToRefresh() {
        return this.mCanPullToRefresh;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        afterDetachedFromWindow();
    }

    public void onDraw(Canvas canvas) {
        if (this.mUnderneathColor != 0) {
            computeUnderneathRect();
            if (this.mUnderneathRect != null && !this.mUnderneathRect.isEmpty()) {
                canvas.save();
                canvas.clipRect(this.mUnderneathRect);
                canvas.drawColor(this.mUnderneathColor);
                canvas.restore();
            }
        }
        super.onDraw(canvas);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mDisableScroll) {
            return false;
        }
        if (motionEvent.getAction() == 0 && this.mDownStop) {
            stopScroll();
        }
        if ((this.mIgnoreTouchSwipeHandler == null || !this.mIgnoreTouchSwipeHandler.shouldInterceptTouchEvent(motionEvent)) && !fixInterceptTouchEvent(motionEvent)) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i2);
        if (this.mMaxHeight > 0 && this.mMaxHeight < size) {
            i2 = View.MeasureSpec.makeMeasureSpec(this.mMaxHeight, View.MeasureSpec.getMode(i2));
        }
        super.onMeasure(i, i2);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mDisableScroll) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void scrollToPosition(int i) {
        if (this.mUseCustomScrollToPosition) {
            startScroll(i, (getHeight() * 2) / 3, 0);
        } else {
            super.scrollToPosition(i);
        }
    }

    public void scrollToPositionWithOffset(int i, int i2) {
        startScroll(i, getHeight(), i2);
    }

    public void setCanPullToRefresh(boolean z) {
        this.mCanPullToRefresh = z;
    }

    public void setDisableScroll(boolean z) {
        this.mDisableScroll = z;
    }

    public void setDistanceToScreenTopUseCache(boolean z) {
        this.mDistanceToScreenTopUseCache = z;
    }

    public void setDownStop(boolean z) {
        this.mDownStop = z;
    }

    public void setFixScrollConflictDirection(@FixScrollDirection int i) {
        this.mFixScrollDirection = i;
    }

    public void setIgnoreTouchSwipeHandler(TouchEventInterceptor touchEventInterceptor) {
        this.mIgnoreTouchSwipeHandler = touchEventInterceptor;
    }

    public void setMaxHeight(int i) {
        this.mMaxHeight = i;
    }

    public void setUnderneathColor(int i) {
        this.mUnderneathColor = i;
        computeUnderneathRect();
        invalidate();
    }

    public void setUseCustomScrollToPosition(boolean z) {
        this.mUseCustomScrollToPosition = z;
    }
}
