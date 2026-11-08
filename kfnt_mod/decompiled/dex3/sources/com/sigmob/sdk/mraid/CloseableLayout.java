package com.sigmob.sdk.mraid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import com.sigmob.sdk.common.utils.m;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CloseableLayout extends FrameLayout {
    private final int a;
    private b b;
    private final StateListDrawable c;
    private a d;
    private final int e;
    private final int f;
    private final int g;
    private boolean h;
    private final Rect i;
    private final Rect j;
    private final Rect k;
    private final Rect l;
    private boolean m;
    private c n;

    public enum a {
        TOP_LEFT(51),
        TOP_CENTER(49),
        TOP_RIGHT(53),
        CENTER(17),
        BOTTOM_LEFT(83),
        BOTTOM_CENTER(81),
        BOTTOM_RIGHT(85);

        private final int h;

        a(int i2) {
            this.h = i2;
        }

        int a() {
            return this.h;
        }
    }

    public interface b {
        void a();
    }

    private final class c implements Runnable {
        private c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CloseableLayout.this.setClosePressed(false);
        }
    }

    public CloseableLayout(Context context) {
        this(context, null, 0);
    }

    public CloseableLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CloseableLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i = new Rect();
        this.j = new Rect();
        this.k = new Rect();
        this.l = new Rect();
        this.c = new StateListDrawable();
        this.d = a.TOP_RIGHT;
        this.c.setState(EMPTY_STATE_SET);
        this.c.setCallback(this);
        this.a = ViewConfiguration.get(context).getScaledTouchSlop();
        this.e = com.sigmob.sdk.common.utils.c.c(50.0f, context);
        this.f = com.sigmob.sdk.common.utils.c.c(30.0f, context);
        this.g = com.sigmob.sdk.common.utils.c.c(8.0f, context);
        setWillNotDraw(false);
        this.m = true;
    }

    private void a(a aVar, int i, Rect rect, Rect rect2) {
        Gravity.apply(aVar.a(), i, i, rect, rect2);
    }

    private void b(a aVar, Rect rect, Rect rect2) {
        a(aVar, this.f, rect, rect2);
    }

    private void c() {
        playSoundEffect(0);
        if (this.b != null) {
            this.b.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setClosePressed(boolean z) {
        if (z == b()) {
            return;
        }
        this.c.setState(z ? SELECTED_STATE_SET : EMPTY_STATE_SET);
        invalidate(this.j);
    }

    public void a(a aVar, Rect rect, Rect rect2) {
        a(aVar, this.e, rect, rect2);
    }

    boolean a() {
        return this.m || this.c.isVisible();
    }

    boolean a(int i, int i2, int i3) {
        return i >= this.j.left - i3 && i2 >= this.j.top - i3 && i < this.j.right + i3 && i2 < this.j.bottom + i3;
    }

    boolean b() {
        return this.c.getState() == SELECTED_STATE_SET;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.h) {
            this.h = false;
            this.i.set(0, 0, getWidth(), getHeight());
            a(this.d, this.i, this.j);
            this.l.set(this.j);
            this.l.inset(this.g, this.g);
            b(this.d, this.l, this.k);
            this.c.setBounds(this.k);
        }
        if (this.c.isVisible()) {
            this.c.draw(canvas);
        }
    }

    Rect getCloseBounds() {
        return this.j;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        return a((int) motionEvent.getX(), (int) motionEvent.getY(), 0);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.h = true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!a((int) motionEvent.getX(), (int) motionEvent.getY(), this.a) || !a()) {
            setClosePressed(false);
            super.onTouchEvent(motionEvent);
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 3) {
            switch (action) {
                case 0:
                    setClosePressed(true);
                    break;
                case 1:
                    if (b()) {
                        if (this.n == null) {
                            this.n = new c();
                        }
                        postDelayed(this.n, ViewConfiguration.getPressedStateDuration());
                        c();
                        break;
                    }
                    break;
            }
        } else {
            setClosePressed(false);
        }
        return true;
    }

    public void setCloseAlwaysInteractable(boolean z) {
        this.m = z;
    }

    void setCloseBoundChanged(boolean z) {
        this.h = z;
    }

    void setCloseBounds(Rect rect) {
        this.j.set(rect);
    }

    public void setClosePosition(a aVar) {
        m.a(aVar);
        this.d = aVar;
        this.h = true;
        invalidate();
    }

    public void setCloseVisible(boolean z) {
        if (this.c.setVisible(z, false)) {
            invalidate(this.j);
        }
    }

    public void setOnCloseListener(b bVar) {
        this.b = bVar;
    }
}
