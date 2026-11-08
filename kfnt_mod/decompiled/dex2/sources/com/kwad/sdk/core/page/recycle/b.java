package com.kwad.sdk.core.page.recycle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends g {
    private Rect a;
    private int b;
    private int c;
    private boolean d;
    private int e;
    private a f;
    private boolean g;
    private boolean h;

    public interface a {
        boolean a(MotionEvent motionEvent);
    }

    public b(Context context) {
        this(context, null);
    }

    public b(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public b(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = Integer.MIN_VALUE;
        this.h = false;
    }

    private void a(int i, int i2, int i3) {
        if (this.e == Integer.MIN_VALUE) {
            int[] iArr = new int[2];
            getLocationOnScreen(iArr);
            this.e = iArr[1];
        }
        int iA = f.a(this).a();
        int iB = f.a(this).b();
        if (iA == -1 || iB == -1) {
            return;
        }
        if (i < iA || i > iB) {
            if (i > iB) {
                scrollBy(0, i2);
            } else {
                scrollBy(0, -i2);
            }
            a(i, i2, i3);
            return;
        }
        int i4 = i - iA;
        if (getChildCount() > i4) {
            int[] iArr2 = new int[2];
            getChildAt(i4).getLocationOnScreen(iArr2);
            scrollBy(0, (iArr2[1] - this.e) - i3);
        }
    }

    private void b() {
        if (this.a == null) {
            this.a = new Rect();
        } else {
            this.a.setEmpty();
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() == 0) {
                this.a.union(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom());
            }
        }
    }

    protected void a() {
        RecyclerView.Adapter adapter = getAdapter();
        if (adapter instanceof d) {
            ((d) adapter).a();
        }
    }

    public void a(int i, int i2) {
        a(i, getHeight(), i2);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        a();
    }

    public void onDraw(Canvas canvas) {
        if (this.b != 0) {
            b();
            if (this.a != null && !this.a.isEmpty()) {
                canvas.save();
                canvas.clipRect(this.a);
                canvas.drawColor(this.b);
                canvas.restore();
            }
        }
        super.onDraw(canvas);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.g) {
            return false;
        }
        if (motionEvent.getAction() == 0 && this.h) {
            stopScroll();
        }
        if (this.f == null || !this.f.a(motionEvent)) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i2);
        if (this.c > 0 && this.c < size) {
            i2 = View.MeasureSpec.makeMeasureSpec(this.c, View.MeasureSpec.getMode(i2));
        }
        super.onMeasure(i, i2);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.g) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void scrollToPosition(int i) {
        if (this.d) {
            a(i, 0);
        } else {
            super.scrollToPosition(i);
        }
    }

    public void setDisableScroll(boolean z) {
        this.g = z;
    }

    public void setDownStop(boolean z) {
        this.h = z;
    }

    public void setIgnoreTouchSwipeHandler(a aVar) {
        this.f = aVar;
    }

    public void setUnderneathColor(int i) {
        this.b = i;
        b();
        invalidate();
    }

    public void setUseCustomScrollToPosition(boolean z) {
        this.d = z;
    }
}
