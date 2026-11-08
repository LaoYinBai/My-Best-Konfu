package com.kwad.sdk.core.page.recycle;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.kwad.sdk.utils.bd;
import com.kwad.sdk.utils.s;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DetailWebRecycleView extends b {
    a a;
    private int b;
    private boolean c;
    private boolean d;
    private int e;
    private int f;
    private boolean g;
    private int h;
    private Runnable i;
    private bd j;

    public interface a {
        boolean a(MotionEvent motionEvent);
    }

    public DetailWebRecycleView(Context context) {
        this(context, null);
    }

    public DetailWebRecycleView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DetailWebRecycleView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = 1000;
        this.c = false;
        this.d = false;
        this.i = new Runnable() { // from class: com.kwad.sdk.core.page.recycle.DetailWebRecycleView.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Object objA = s.a(DetailWebRecycleView.this, "mGapWorker");
                    if (objA != null) {
                        s.a(objA, "postFromTraversal", DetailWebRecycleView.this, 0, Integer.valueOf(DetailWebRecycleView.this.b));
                    }
                } catch (RuntimeException e) {
                    com.kwad.sdk.core.d.a.b(e);
                }
            }
        };
        this.j = new bd(this.i);
        this.h = context instanceof Activity ? com.kwad.sdk.a.kwai.a.b((Activity) context) : com.kwad.sdk.a.kwai.a.b(context);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        postDelayed(this.j, 50L);
    }

    @Override // com.kwad.sdk.core.page.recycle.b
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.j);
    }

    @Override // com.kwad.sdk.core.page.recycle.b
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.a != null && this.a.a(motionEvent)) {
            return true;
        }
        this.f = computeVerticalScrollOffset();
        if (motionEvent.getY() <= this.e - this.f) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        this.f = computeVerticalScrollOffset();
        if (this.f >= this.e) {
            return false;
        }
        fling((int) f, (int) f2);
        return true;
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        this.f = computeVerticalScrollOffset();
        if ((i2 > 0 && this.f < this.e) && !this.g && this.f < this.h) {
            scrollBy(0, i2);
            iArr[1] = i2;
        }
        if (i2 < 0 && this.f > 0 && !ViewCompat.canScrollVertically(view, -1)) {
            scrollBy(0, i2);
            iArr[1] = i2;
        }
    }

    public void onScrollStateChanged(int i) {
        super.onScrollStateChanged(i);
        if (i == 0) {
            View childAt = getLayoutManager().getChildAt(getLayoutManager().getChildCount() - 1);
            if (childAt != null) {
                int bottom = childAt.getBottom();
                int bottom2 = getBottom() - getPaddingBottom();
                int position = getLayoutManager().getPosition(childAt);
                if (bottom == bottom2 && position == getLayoutManager().getItemCount() - 1) {
                    this.g = true;
                    return;
                }
            }
            this.g = false;
        }
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        return (i & 2) != 0;
    }

    public void requestChildFocus(View view, View view2) {
        if (this.d) {
            this.d = false;
        } else {
            if (this.c) {
                return;
            }
            super.requestChildFocus(view, view2);
        }
    }

    public void setInterceptRequestFocusForWeb(boolean z) {
        this.c = z;
    }

    public void setInterceptRequestFocusForWebFiredOnce(boolean z) {
        this.d = z;
    }

    public void setInterceptTouchListener(a aVar) {
        this.a = aVar;
    }

    public void setTopViewHeight(int i) {
        this.e = i;
    }
}
