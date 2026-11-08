package com.tachikoma.core.component.listview.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RecyclerViewPager extends RecyclerView {
    final String TAG;
    private int mCurrentItem;
    private List<OnPageChangeListener> mPageListeners;
    private int mPrevItem;
    private ScrollEventAdapter mScrollEventAdapter;
    protected PagerSnapHelper mSnapHelper;

    public static abstract class OnPageChangeCallback {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, @Px int i2) {
        }

        public void onPageSelected(int i) {
        }
    }

    public interface OnPageChangeListener {
        void onPageScrollStateChanged(int i);

        void onPageScrolled(int i, float f, @Px int i2);

        void onPageSelected(int i, int i2);
    }

    private static class SmoothScrollToPosition implements Runnable {
        private final int mPosition;
        private final RecyclerView mRecyclerView;

        SmoothScrollToPosition(int i, RecyclerView recyclerView) {
            this.mPosition = i;
            this.mRecyclerView = recyclerView;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mRecyclerView.smoothScrollToPosition(this.mPosition);
        }
    }

    public RecyclerViewPager(Context context) {
        this(context, null);
    }

    public RecyclerViewPager(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RecyclerViewPager(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = getClass().getSimpleName();
        this.mPageListeners = new ArrayList();
        this.mSnapHelper = new PagerSnapHelper();
        this.mSnapHelper.attachToRecyclerView(this);
    }

    public void addOnPageListener(OnPageChangeListener onPageChangeListener) {
        this.mPageListeners.add(onPageChangeListener);
    }

    public boolean fling(int i, int i2) {
        return super.fling(i, i2);
    }

    public int getCurrentItem() {
        return this.mCurrentItem;
    }

    void notifyPageScrolled(int i, float f, @Px int i2) {
        Iterator<OnPageChangeListener> it = this.mPageListeners.iterator();
        while (it.hasNext()) {
            it.next().onPageScrolled(i, f, i2);
        }
    }

    void notifyPageSelected(int i, int i2) {
        Log.i(this.TAG, "position: " + i + " mPrevPosition: " + i2);
        Iterator<OnPageChangeListener> it = this.mPageListeners.iterator();
        while (it.hasNext()) {
            it.next().onPageSelected(i, i2);
        }
    }

    void notifyScrollStateChanged(int i) {
        Iterator<OnPageChangeListener> it = this.mPageListeners.iterator();
        while (it.hasNext()) {
            it.next().onPageScrollStateChanged(i);
        }
    }

    public void removeOnPageListener(OnPageChangeListener onPageChangeListener) {
        this.mPageListeners.remove(onPageChangeListener);
    }

    public void setCurrent(int i) {
        setCurrentItem(i, false);
    }

    public final void setCurrentItem(int i, boolean z) {
        RecyclerView.Adapter adapter = getAdapter();
        if (adapter == null || adapter.getItemCount() <= 0) {
            return;
        }
        int iMin = Math.min(Math.max(i, 0), adapter.getItemCount() - 1);
        if (iMin == this.mCurrentItem && this.mScrollEventAdapter.isIdle()) {
            return;
        }
        if (iMin == this.mCurrentItem && z) {
            return;
        }
        float relativeScrollPosition = this.mCurrentItem;
        this.mCurrentItem = iMin;
        if (!this.mScrollEventAdapter.isIdle()) {
            relativeScrollPosition = this.mScrollEventAdapter.getRelativeScrollPosition();
        }
        this.mScrollEventAdapter.notifyProgrammaticScroll(iMin, z);
        if (!z) {
            scrollToPosition(iMin);
            return;
        }
        float f = iMin;
        if (Math.abs(f - relativeScrollPosition) <= 3.0f) {
            smoothScrollToPosition(iMin);
        } else {
            scrollToPosition(f > relativeScrollPosition ? iMin - 3 : iMin + 3);
            post(new SmoothScrollToPosition(iMin, this));
        }
    }

    public void setDirection(int i) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), i, false);
        super.setLayoutManager(linearLayoutManager);
        this.mScrollEventAdapter = new ScrollEventAdapter(linearLayoutManager);
        addOnScrollListener(this.mScrollEventAdapter);
        this.mScrollEventAdapter.setOnPageChangeCallback(new OnPageChangeCallback() { // from class: com.tachikoma.core.component.listview.viewpager.RecyclerViewPager.2
            @Override // com.tachikoma.core.component.listview.viewpager.RecyclerViewPager.OnPageChangeCallback
            public void onPageScrollStateChanged(int i2) {
                super.onPageScrollStateChanged(i2);
                RecyclerViewPager.this.notifyScrollStateChanged(i2);
            }

            @Override // com.tachikoma.core.component.listview.viewpager.RecyclerViewPager.OnPageChangeCallback
            public void onPageScrolled(int i2, float f, int i3) {
                super.onPageScrolled(i2, f, i3);
                RecyclerViewPager.this.notifyPageScrolled(i2, f, i3);
            }

            @Override // com.tachikoma.core.component.listview.viewpager.RecyclerViewPager.OnPageChangeCallback
            public void onPageSelected(int i2) {
                super.onPageSelected(i2);
                RecyclerViewPager.this.mCurrentItem = i2;
                RecyclerViewPager.this.notifyPageSelected(RecyclerViewPager.this.mCurrentItem, RecyclerViewPager.this.mPrevItem);
                RecyclerViewPager.this.mPrevItem = RecyclerViewPager.this.mCurrentItem;
            }
        });
    }

    @Deprecated
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
    }

    public void smoothScrollToPosition(int i) {
        super.smoothScrollToPosition(i);
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(getContext()) { // from class: com.tachikoma.core.component.listview.viewpager.RecyclerViewPager.1
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return super.calculateSpeedPerPixel(displayMetrics) * 3.0f;
            }
        };
        linearSmoothScroller.setTargetPosition(i);
        getLayoutManager().startSmoothScroll(linearSmoothScroller);
    }
}
