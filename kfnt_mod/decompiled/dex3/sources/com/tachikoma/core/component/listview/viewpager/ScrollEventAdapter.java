package com.tachikoma.core.component.listview.viewpager;

import android.util.Log;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tachikoma.core.component.listview.viewpager.RecyclerViewPager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class ScrollEventAdapter extends RecyclerView.OnScrollListener {
    private static final int NO_POSITION = -1;
    public static final int ORIENTATION_HORIZONTAL = 0;
    public static final int ORIENTATION_VERTICAL = 1;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final int STATE_IDLE = 0;
    private static final int STATE_IN_PROGRESS_IMMEDIATE_SCROLL = 3;
    private static final int STATE_IN_PROGRESS_MANUAL_DRAG = 1;
    private static final int STATE_IN_PROGRESS_SMOOTH_SCROLL = 2;
    private static final ViewGroup.MarginLayoutParams ZERO_MARGIN_LAYOUT_PARAMS = new ViewGroup.MarginLayoutParams(-1, -1);
    private int mAdapterState;
    private RecyclerViewPager.OnPageChangeCallback mCallback;
    private boolean mDispatchSelected;
    private int mDragStartPosition;

    @NonNull
    private final LinearLayoutManager mLayoutManager;
    private boolean mScrollHappened;
    private int mScrollState;
    private int mTarget;
    private final String TAG = getClass().getSimpleName();
    private ScrollEventValues mScrollValues = new ScrollEventValues();

    public @interface Orientation {
    }

    private static final class ScrollEventValues {
        float mOffset;
        int mOffsetPx;
        int mPosition;

        ScrollEventValues() {
        }

        ScrollEventValues reset() {
            this.mPosition = -1;
            this.mOffset = 0.0f;
            this.mOffsetPx = 0;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ScrollState {
    }

    static {
        ZERO_MARGIN_LAYOUT_PARAMS.setMargins(0, 0, 0, 0);
    }

    public ScrollEventAdapter(@NonNull LinearLayoutManager linearLayoutManager) {
        this.mLayoutManager = linearLayoutManager;
        resetState();
    }

    private void dispatchScrolled(int i, float f, int i2) {
        if (this.mCallback != null) {
            this.mCallback.onPageScrolled(i, f, i2);
        }
    }

    private void dispatchSelected(int i) {
        if (this.mCallback != null) {
            this.mCallback.onPageSelected(i);
        }
    }

    private void dispatchStateChanged(int i) {
        if ((this.mAdapterState == 3 && this.mScrollState == 0) || this.mScrollState == i) {
            return;
        }
        this.mScrollState = i;
        if (this.mCallback != null) {
            this.mCallback.onPageScrollStateChanged(i);
        }
    }

    private int getPosition() {
        return this.mLayoutManager.findFirstVisibleItemPosition();
    }

    private boolean isLayoutRTL() {
        return this.mLayoutManager.getLayoutDirection() == 1;
    }

    private void resetState() {
        this.mAdapterState = 0;
        this.mScrollState = 0;
        this.mScrollValues.reset();
        this.mDragStartPosition = -1;
        this.mTarget = -1;
        this.mDispatchSelected = false;
        this.mScrollHappened = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.tachikoma.core.component.listview.viewpager.ScrollEventAdapter.ScrollEventValues updateScrollEventValues() {
        /*
            r7 = this;
            com.tachikoma.core.component.listview.viewpager.ScrollEventAdapter$ScrollEventValues r0 = r7.mScrollValues
            androidx.recyclerview.widget.LinearLayoutManager r1 = r7.mLayoutManager
            int r1 = r1.findFirstVisibleItemPosition()
            r0.mPosition = r1
            int r1 = r0.mPosition
            r2 = -1
            if (r1 != r2) goto L14
            com.tachikoma.core.component.listview.viewpager.ScrollEventAdapter$ScrollEventValues r0 = r0.reset()
            return r0
        L14:
            androidx.recyclerview.widget.LinearLayoutManager r1 = r7.mLayoutManager
            int r2 = r0.mPosition
            android.view.View r1 = r1.findViewByPosition(r2)
            if (r1 != 0) goto L23
            com.tachikoma.core.component.listview.viewpager.ScrollEventAdapter$ScrollEventValues r0 = r0.reset()
            return r0
        L23:
            android.view.ViewGroup$LayoutParams r2 = r1.getLayoutParams()
            boolean r2 = r2 instanceof android.view.ViewGroup.MarginLayoutParams
            if (r2 == 0) goto L32
            android.view.ViewGroup$LayoutParams r2 = r1.getLayoutParams()
            android.view.ViewGroup$MarginLayoutParams r2 = (android.view.ViewGroup.MarginLayoutParams) r2
            goto L34
        L32:
            android.view.ViewGroup$MarginLayoutParams r2 = com.tachikoma.core.component.listview.viewpager.ScrollEventAdapter.ZERO_MARGIN_LAYOUT_PARAMS
        L34:
            androidx.recyclerview.widget.LinearLayoutManager r3 = r7.mLayoutManager
            int r3 = r3.getOrientation()
            r4 = 0
            r5 = 1
            if (r3 != 0) goto L40
            r3 = 1
            goto L41
        L40:
            r3 = 0
        L41:
            if (r3 == 0) goto L5e
            int r3 = r1.getWidth()
            boolean r6 = r7.isLayoutRTL()
            if (r6 != 0) goto L54
            int r1 = r1.getLeft()
            int r2 = r2.leftMargin
            goto L68
        L54:
            int r1 = r1.getRight()
            int r1 = r3 - r1
            int r2 = r2.rightMargin
            int r1 = r1 + r2
            goto L69
        L5e:
            int r3 = r1.getHeight()
            int r1 = r1.getTop()
            int r2 = r2.topMargin
        L68:
            int r1 = r1 - r2
        L69:
            int r1 = -r1
            r0.mOffsetPx = r1
            int r1 = r0.mOffsetPx
            if (r1 < 0) goto L7c
            if (r3 != 0) goto L74
            r1 = 0
            goto L79
        L74:
            int r1 = r0.mOffsetPx
            float r1 = (float) r1
            float r2 = (float) r3
            float r1 = r1 / r2
        L79:
            r0.mOffset = r1
            return r0
        L7c:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.util.Locale r2 = java.util.Locale.US
            java.lang.Object[] r3 = new java.lang.Object[r5]
            int r0 = r0.mOffsetPx
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r3[r4] = r0
            java.lang.String r0 = "Page can only be offset by a positive amount, not by %d"
            java.lang.String r0 = java.lang.String.format(r2, r0, r3)
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.component.listview.viewpager.ScrollEventAdapter.updateScrollEventValues():com.tachikoma.core.component.listview.viewpager.ScrollEventAdapter$ScrollEventValues");
    }

    public float getRelativeScrollPosition() {
        updateScrollEventValues();
        return this.mScrollValues.mPosition + this.mScrollValues.mOffset;
    }

    public boolean isIdle() {
        return this.mAdapterState == 0;
    }

    public void notifyProgrammaticScroll(int i, boolean z) {
        this.mAdapterState = z ? 2 : 3;
        boolean z2 = this.mTarget != i;
        this.mTarget = i;
        dispatchStateChanged(2);
        if (z2) {
            dispatchSelected(i);
        }
    }

    public void notifyRestoreCurrentItem(int i) {
        if (i != 0) {
            dispatchSelected(i);
        }
    }

    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
        if (this.mAdapterState != 1 && i == 1) {
            this.mAdapterState = 1;
            if (this.mTarget != -1) {
                this.mDragStartPosition = this.mTarget;
                this.mTarget = -1;
            } else {
                this.mDragStartPosition = getPosition();
            }
            dispatchStateChanged(1);
            return;
        }
        if (this.mAdapterState == 1 && i == 2) {
            if (!this.mScrollHappened) {
                dispatchScrolled(getPosition(), 0.0f, 0);
                return;
            }
            dispatchStateChanged(2);
            this.mDispatchSelected = true;
            this.mScrollHappened = false;
            return;
        }
        if (this.mAdapterState == 1 && i == 0) {
            if (this.mScrollState == 1 && this.mScrollValues.mOffsetPx == 0) {
                if (this.mScrollHappened) {
                    this.mDispatchSelected = true;
                    this.mScrollHappened = false;
                } else {
                    dispatchScrolled(getPosition(), 0.0f, 0);
                }
            } else if (this.mScrollState == 2 && !this.mScrollHappened) {
                Log.e(this.TAG, "RecyclerView sent SCROLL_STATE_SETTLING event without scrolling any further before going to SCROLL_STATE_IDLE");
            }
            if (this.mScrollHappened) {
                return;
            }
            if (this.mDispatchSelected) {
                updateScrollEventValues();
                if (this.mDragStartPosition != this.mScrollValues.mPosition) {
                    dispatchSelected(this.mScrollValues.mPosition);
                }
            }
            dispatchStateChanged(0);
            resetState();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onScrolled(@androidx.annotation.NonNull androidx.recyclerview.widget.RecyclerView r4, int r5, int r6) {
        /*
            r3 = this;
            r4 = 1
            r3.mScrollHappened = r4
            com.tachikoma.core.component.listview.viewpager.ScrollEventAdapter$ScrollEventValues r0 = r3.updateScrollEventValues()
            boolean r1 = r3.mDispatchSelected
            r2 = 0
            if (r1 == 0) goto L3a
            r3.mDispatchSelected = r2
            if (r6 > 0) goto L20
            if (r6 != 0) goto L1e
            if (r5 >= 0) goto L16
            r5 = 1
            goto L17
        L16:
            r5 = 0
        L17:
            boolean r6 = r3.isLayoutRTL()
            if (r5 != r6) goto L1e
            goto L20
        L1e:
            r5 = 0
            goto L21
        L20:
            r5 = 1
        L21:
            if (r5 == 0) goto L2b
            int r5 = r0.mOffsetPx
            if (r5 == 0) goto L2b
            int r5 = r0.mPosition
            int r5 = r5 + r4
            goto L2d
        L2b:
            int r5 = r0.mPosition
        L2d:
            r3.mTarget = r5
            int r5 = r3.mDragStartPosition
            int r6 = r3.mTarget
            if (r5 == r6) goto L3a
            int r5 = r3.mTarget
            r3.dispatchSelected(r5)
        L3a:
            int r5 = r0.mPosition
            float r6 = r0.mOffset
            int r1 = r0.mOffsetPx
            r3.dispatchScrolled(r5, r6, r1)
            int r5 = r0.mPosition
            int r6 = r3.mTarget
            if (r5 == r6) goto L4e
            int r5 = r3.mTarget
            r6 = -1
            if (r5 != r6) goto L5c
        L4e:
            int r5 = r0.mOffsetPx
            if (r5 != 0) goto L5c
            int r5 = r3.mScrollState
            if (r5 == r4) goto L5c
            r3.dispatchStateChanged(r2)
            r3.resetState()
        L5c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tachikoma.core.component.listview.viewpager.ScrollEventAdapter.onScrolled(androidx.recyclerview.widget.RecyclerView, int, int):void");
    }

    public void setOnPageChangeCallback(RecyclerViewPager.OnPageChangeCallback onPageChangeCallback) {
        this.mCallback = onPageChangeCallback;
    }
}
