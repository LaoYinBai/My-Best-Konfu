package com.kwad.sdk.contentalliance.refreshview;

import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class RefreshLayout extends ViewGroup implements NestedScrollingChild, NestedScrollingParent {
    private float A;
    private float B;
    private View C;
    private boolean D;
    private boolean E;
    private boolean F;
    private RefreshStyle G;
    private View H;
    private com.kwad.sdk.contentalliance.refreshview.a I;
    private a J;
    private final Animation.AnimationListener K;
    private c L;
    private b M;
    private List<b> N;
    private Interpolator O;
    private Interpolator P;
    private boolean Q;
    private boolean R;
    private final Animation S;
    private final Animation T;
    private boolean U;
    protected float a;
    protected float b;
    protected View c;
    protected com.kwad.sdk.contentalliance.refreshview.b d;
    protected final Animation.AnimationListener e;
    private final NestedScrollingChildHelper f;
    private final NestedScrollingParentHelper g;
    private float h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private float v;
    private float w;
    private float x;
    private float y;
    private float z;

    public enum RefreshStyle {
        NORMAL,
        PINNED,
        FLOAT
    }

    public interface a {
    }

    public interface b {
        void a();

        void a(float f, float f2, boolean z);

        void b();
    }

    public interface c {
        boolean a(float f, boolean z);
    }

    private float a(MotionEvent motionEvent, int i) {
        int iFindPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, i);
        if (iFindPointerIndex < 0) {
            return -1.0f;
        }
        return MotionEventCompat.getY(motionEvent, iFindPointerIndex);
    }

    private void a(float f) {
        float f2 = f - this.v;
        if (this.k && (f2 > this.u || this.a > 0.0f)) {
            this.m = true;
            this.x = this.v + this.u;
        } else {
            if (this.m || f2 <= this.u) {
                return;
            }
            this.x = this.v + this.u;
            this.m = true;
        }
    }

    private void a(float f, boolean z) {
        float fA;
        this.y = f;
        int i = 0;
        if (this.L == null || !this.L.a(f, false)) {
            if (this.k) {
                fA = f > this.b ? this.b : f;
                if (fA < 0.0f) {
                    fA = 0.0f;
                }
            } else {
                fA = AnonymousClass2.a[this.G.ordinal()] != 1 ? this.I.a(f, this.b) : this.z + this.I.a(f, this.b);
            }
            float f2 = this.b;
            if (!this.k) {
                if (fA > f2 && !this.l) {
                    this.l = true;
                    this.d.c();
                    if (this.M != null) {
                        this.M.a();
                    }
                    if (this.N != null) {
                        while (i < this.N.size()) {
                            this.N.get(i).a();
                            i++;
                        }
                    }
                } else if (fA <= f2 && this.l) {
                    this.l = false;
                    this.d.d();
                    if (this.M != null) {
                        this.M.b();
                    }
                    if (this.N != null) {
                        while (i < this.N.size()) {
                            this.N.get(i).b();
                            i++;
                        }
                    }
                }
            }
            com.kwad.sdk.core.d.a.c("RefreshLayout", f + " -- " + f2 + " -- " + fA + " -- " + this.a + " -- " + this.b);
            a((int) (fA - this.a), z);
        }
    }

    private void a(int i, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.c.getLayoutParams();
        this.c.measure(marginLayoutParams.width == -1 ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) - marginLayoutParams.leftMargin) - marginLayoutParams.rightMargin), 1073741824) : getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin, marginLayoutParams.width), marginLayoutParams.height == -1 ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - marginLayoutParams.topMargin) - marginLayoutParams.bottomMargin), 1073741824) : getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, marginLayoutParams.height));
    }

    private void a(int i, boolean z) {
        View view;
        if (this.H == null) {
            return;
        }
        switch (this.G) {
            case FLOAT:
                this.c.offsetTopAndBottom(i);
                view = this.c;
                this.a = view.getTop();
                break;
            case PINNED:
                this.H.offsetTopAndBottom(i);
                if (this.C != null) {
                    this.C.offsetTopAndBottom(i);
                }
                view = this.H;
                this.a = view.getTop();
                break;
            default:
                this.H.offsetTopAndBottom(i);
                if (this.C != null) {
                    this.C.offsetTopAndBottom(i);
                } else {
                    float f = (i / this.A) + this.B;
                    int i2 = (int) f;
                    this.B = f - i2;
                    this.c.offsetTopAndBottom(i2);
                }
                this.a = this.H.getTop();
                com.kwad.sdk.core.d.a.c("RefreshLayout", "refresh style" + this.a);
                break;
        }
        com.kwad.sdk.core.d.a.c("RefreshLayout", "current offset" + this.a);
        if (AnonymousClass2.a[this.G.ordinal()] != 1) {
            this.d.a(this.a, this.a / this.b);
            if (this.M != null) {
                this.M.a(this.a, this.a / this.b, z);
            }
            if (this.N != null) {
                for (int i3 = 0; i3 < this.N.size(); i3++) {
                    this.N.get(i3).a(this.a, this.a / this.b, z);
                }
            }
        } else {
            this.d.a(this.a, (this.a - this.z) / this.b);
            if (this.M != null) {
                this.M.a(this.a, (this.a - this.z) / this.b, z);
            }
            if (this.N != null) {
                for (int i4 = 0; i4 < this.N.size(); i4++) {
                    this.N.get(i4).a(this.a, (this.a - this.z) / this.b, z);
                }
            }
        }
        if (this.R && this.c.getVisibility() != 0) {
            this.c.setVisibility(0);
        }
        invalidate();
    }

    private void a(MotionEvent motionEvent) {
        this.q = MotionEventCompat.getPointerId(motionEvent, MotionEventCompat.getActionIndex(motionEvent));
        this.x = a(motionEvent, this.q) - this.y;
        com.kwad.sdk.core.d.a.c("RefreshLayout", " onDown " + this.x);
    }

    private void a(boolean z, boolean z2) {
        if (this.k != z) {
            this.n = z2;
            this.k = z;
            if (z) {
                b((int) this.a, this.K);
            } else {
                this.d.b();
                postDelayed(new Runnable() { // from class: com.kwad.sdk.contentalliance.refreshview.RefreshLayout.1
                    @Override // java.lang.Runnable
                    public void run() {
                        RefreshLayout.this.a((int) RefreshLayout.this.a, RefreshLayout.this.e);
                    }
                }, this.d.e());
            }
        }
    }

    private boolean a(View view) {
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
                if (a(viewGroup.getChildAt(i))) {
                    return true;
                }
            }
        }
        return ViewCompat.canScrollVertically(view, -1);
    }

    private int b(float f) {
        com.kwad.sdk.core.d.a.c("RefreshLayout", "from -- refreshing " + f);
        if (AnonymousClass2.a[this.G.ordinal()] == 1) {
            f -= this.z;
        }
        return (int) (Math.max(0.0f, Math.min(1.0f, Math.abs(f - this.b) / this.b)) * this.s);
    }

    private int b(int i) {
        switch (this.G) {
            case FLOAT:
                return i;
            case PINNED:
            default:
                return i + ((int) this.a);
        }
    }

    private void b() {
        c();
        this.y = 0.0f;
        this.B = 0.0f;
        this.d.a();
        this.c.setVisibility(8);
        this.k = false;
        this.j = false;
        com.kwad.sdk.core.d.a.c("RefreshLayout", "reset");
    }

    private void b(int i, Animation.AnimationListener animationListener) {
        clearAnimation();
        if (b(i) <= 0) {
            animationListener.onAnimationStart(null);
            animationListener.onAnimationEnd(null);
            return;
        }
        this.t = i;
        this.S.reset();
        this.S.setDuration(b(r0));
        this.S.setInterpolator(this.P);
        if (animationListener != null) {
            this.S.setAnimationListener(animationListener);
        }
        startAnimation(this.S);
    }

    private void b(MotionEvent motionEvent) {
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (MotionEventCompat.getPointerId(motionEvent, actionIndex) == this.q) {
            this.q = MotionEventCompat.getPointerId(motionEvent, actionIndex == 0 ? 1 : 0);
        }
        this.x = a(motionEvent, this.q) - this.y;
        com.kwad.sdk.core.d.a.c("RefreshLayout", " onUp " + this.x);
    }

    private int c(float f) {
        com.kwad.sdk.core.d.a.c("RefreshLayout", "from -- start " + f);
        if (f < this.z) {
            return 0;
        }
        if (AnonymousClass2.a[this.G.ordinal()] == 1) {
            f -= this.z;
        }
        return (int) (Math.max(0.0f, Math.min(1.0f, Math.abs(f) / this.b)) * this.r);
    }

    private void c() {
        setTargetOrRefreshViewOffsetY((int) ((AnonymousClass2.a[this.G.ordinal()] != 1 ? 0.0f : this.z) - this.a));
    }

    private void d() {
        this.H.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
    }

    private void e() {
        this.w = 0.0f;
        this.m = false;
        this.o = false;
        this.q = -1;
    }

    private void f() {
        if (this.k || this.j) {
            return;
        }
        if (this.L == null || !this.L.a(this.y, true)) {
            if (a()) {
                a(true, true);
            } else {
                this.k = false;
                a((int) this.a, this.e);
            }
        }
    }

    private void g() {
        if (this.C != null) {
            this.C.measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
        }
    }

    private int getTargetOrRefreshViewTop() {
        return (AnonymousClass2.a[this.G.ordinal()] != 1 ? this.H : this.c).getTop();
    }

    private void h() {
        if (i()) {
            return;
        }
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (!childAt.equals(this.c) && !childAt.equals(this.C)) {
                this.H = childAt;
                return;
            }
        }
    }

    private boolean i() {
        for (int i = 0; i < getChildCount(); i++) {
            if (this.H == getChildAt(i)) {
                return true;
            }
        }
        return false;
    }

    protected int a(int i) {
        switch (this.G) {
            case FLOAT:
            default:
                return i + ((int) this.a);
            case PINNED:
                return i;
        }
    }

    protected void a(int i, Animation.AnimationListener animationListener) {
        clearAnimation();
        if (c(i) <= 0) {
            animationListener.onAnimationStart(null);
            animationListener.onAnimationEnd(null);
            return;
        }
        this.t = i;
        this.T.reset();
        this.T.setDuration(c(r0));
        this.T.setInterpolator(this.O);
        if (animationListener != null) {
            this.T.setAnimationListener(animationListener);
        }
        startAnimation(this.T);
    }

    protected boolean a() {
        return !this.Q && ((float) getTargetOrRefreshViewOffset()) > this.b;
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.f.dispatchNestedFling(f, f2, z);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.f.dispatchNestedPreFling(f, f2);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.f.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.f.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int actionMasked;
        if (this.U && ((actionMasked = MotionEventCompat.getActionMasked(motionEvent)) == 1 || actionMasked == 3)) {
            onStopNestedScroll(this);
        }
        com.kwad.sdk.core.d.a.c("RefreshLayout", "dispatch " + this.o + " isRefreshing" + this.k);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.g.getNestedScrollAxes();
    }

    public float getRefreshTargetOffset() {
        return this.b;
    }

    @Nullable
    public View getStateView() {
        return this.C;
    }

    public int getTargetOrRefreshViewOffset() {
        if (AnonymousClass2.a[this.G.ordinal()] == 1) {
            return (int) (this.c.getTop() - this.z);
        }
        if (this.H == null) {
            return 0;
        }
        return this.H.getTop();
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return this.f.hasNestedScrollingParent();
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return this.f.isNestedScrollingEnabled();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        b();
        this.J = null;
        clearAnimation();
        super.onDetachedFromWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (getChildCount() == 0) {
            return;
        }
        h();
        if (this.H == null) {
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (this.H.getVisibility() != 8 || (this.C != null && this.C.getVisibility() != 8)) {
            int iB = b(getPaddingTop());
            int paddingLeft = getPaddingLeft();
            int paddingLeft2 = ((paddingLeft + measuredWidth) - getPaddingLeft()) - getPaddingRight();
            int paddingTop = ((measuredHeight + iB) - getPaddingTop()) - getPaddingBottom();
            if (this.H.getVisibility() != 8) {
                this.H.layout(paddingLeft, iB, paddingLeft2, paddingTop);
            }
            if (this.C != null && this.C.getVisibility() != 8) {
                this.C.layout(paddingLeft, iB, paddingLeft2, paddingTop);
            }
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.c.getLayoutParams();
        int measuredWidth2 = (measuredWidth - this.c.getMeasuredWidth()) / 2;
        int iA = (a((int) this.z) + marginLayoutParams.topMargin) - marginLayoutParams.bottomMargin;
        this.c.layout(measuredWidth2, iA, (measuredWidth + this.c.getMeasuredWidth()) / 2, this.c.getMeasuredHeight() + iA);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        h();
        if (this.H == null) {
            return;
        }
        d();
        g();
        a(i, i2);
        if (!this.F && !this.E) {
            switch (this.G) {
                case FLOAT:
                    float f = -this.c.getMeasuredHeight();
                    this.z = f;
                    this.a = f;
                    break;
                case PINNED:
                    this.z = 0.0f;
                    this.a = 0.0f;
                    break;
                default:
                    this.a = 0.0f;
                    this.z = -this.c.getMeasuredHeight();
                    break;
            }
        }
        if (!this.F && !this.D && this.b < this.c.getMeasuredHeight()) {
            this.b = this.c.getMeasuredHeight();
        }
        this.F = true;
        this.p = -1;
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            if (getChildAt(i3) == this.c) {
                this.p = i3;
                return;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        this.g.onStopNestedScroll(view);
        this.i = false;
        if (this.h > 0.0f) {
            f();
            this.h = 0.0f;
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
        h();
        if (this.H == null) {
            return false;
        }
        if (AnonymousClass2.a[this.G.ordinal()] != 1) {
            if (!isEnabled() || (a(this.H) && !this.o)) {
                return false;
            }
        } else if (!isEnabled() || a(this.H) || this.i) {
            return false;
        }
        if (this.G == RefreshStyle.FLOAT && (a(this.H) || this.i)) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.q = MotionEventCompat.getPointerId(motionEvent, 0);
                this.m = false;
                return true;
            case 1:
            case 3:
                if (this.q == -1 || a(motionEvent, this.q) == -1.0f) {
                    e();
                    return false;
                }
                if (!this.k && !this.j) {
                    e();
                    f();
                    return false;
                }
                if (this.o) {
                    this.H.dispatchTouchEvent(motionEvent);
                }
                e();
                return false;
            case 2:
                if (this.q == -1) {
                    return false;
                }
                float fA = a(motionEvent, this.q);
                if (fA == -1.0f) {
                    return false;
                }
                if (this.j) {
                    targetOrRefreshViewTop = getTargetOrRefreshViewTop();
                    this.x = fA;
                    this.w = targetOrRefreshViewTop;
                    str = "RefreshLayout";
                    sb = new StringBuilder();
                    sb.append("animatetostart overscrolly ");
                    sb.append(targetOrRefreshViewTop);
                    sb.append(" -- ");
                    f = this.x;
                } else {
                    targetOrRefreshViewTop = (fA - this.x) + this.w;
                    str = "RefreshLayout";
                    sb = new StringBuilder();
                    sb.append("overscrolly ");
                    sb.append(targetOrRefreshViewTop);
                    sb.append(" --");
                    sb.append(this.x);
                    sb.append(" -- ");
                    f = this.w;
                }
                sb.append(f);
                com.kwad.sdk.core.d.a.c(str, sb.toString());
                if (this.k) {
                    if (targetOrRefreshViewTop > 0.0f) {
                        if (targetOrRefreshViewTop > 0.0f && targetOrRefreshViewTop < this.b && this.o) {
                            motionEvent = MotionEvent.obtain(motionEvent);
                            motionEvent.setAction(3);
                            this.o = false;
                        }
                        com.kwad.sdk.core.d.a.c("RefreshLayout", "moveSpinner refreshing -- " + this.w + " -- " + (fA - this.x));
                        a(targetOrRefreshViewTop, true);
                    } else if (!this.o) {
                        motionEvent = MotionEvent.obtain(motionEvent);
                        motionEvent.setAction(0);
                        this.o = true;
                    }
                    this.H.dispatchTouchEvent(motionEvent);
                    com.kwad.sdk.core.d.a.c("RefreshLayout", "moveSpinner refreshing -- " + this.w + " -- " + (fA - this.x));
                    a(targetOrRefreshViewTop, true);
                } else if (!this.m) {
                    com.kwad.sdk.core.d.a.c("RefreshLayout", "is not Being Dragged, init drag status");
                    a(fA);
                } else {
                    if (targetOrRefreshViewTop <= 0.0f) {
                        com.kwad.sdk.core.d.a.c("RefreshLayout", "is Being Dragged, but over scroll Y < 0");
                        return false;
                    }
                    a(targetOrRefreshViewTop, true);
                    com.kwad.sdk.core.d.a.c("RefreshLayout", "moveSpinner not refreshing -- " + this.w + " -- " + (fA - this.x));
                }
                return true;
            case 4:
            default:
                return true;
            case 5:
                a(motionEvent);
                return true;
            case 6:
                b(motionEvent);
                return true;
        }
    }

    public void setAnimateToRefreshDuration(int i) {
        this.s = i;
    }

    public void setAnimateToRefreshInterpolator(Interpolator interpolator) {
        this.P = interpolator;
    }

    public void setAnimateToStartDuration(int i) {
        this.r = i;
    }

    public void setAnimateToStartInterpolator(Interpolator interpolator) {
        this.O = interpolator;
    }

    public void setDragDistanceConverter(@NonNull com.kwad.sdk.contentalliance.refreshview.a aVar) {
        if (aVar == null) {
            throw new NullPointerException("the dragDistanceConverter can't be null");
        }
        this.I = aVar;
    }

    public void setIsStopNestScrollWhenUpOrCancel(boolean z) {
        this.U = z;
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        this.f.setNestedScrollingEnabled(z);
    }

    public void setOnRefreshListener(a aVar) {
        this.J = aVar;
    }

    public void setOnRefreshStatusListener(b bVar) {
        this.M = bVar;
    }

    public void setOnScrollInterceptor(c cVar) {
        this.L = cVar;
    }

    public void setOnlySupportPull(boolean z) {
        this.Q = z;
    }

    public void setRefreshInitialOffset(float f) {
        this.z = f;
        this.E = true;
        requestLayout();
    }

    public void setRefreshStyle(@NonNull RefreshStyle refreshStyle) {
        this.G = refreshStyle;
    }

    public void setRefreshTargetOffset(float f) {
        this.b = f;
        this.D = true;
        requestLayout();
    }

    public void setRefreshing(boolean z) {
        if (this.k == z) {
            return;
        }
        if (!z) {
            a(z, false);
            return;
        }
        if (getAnimation() != null && !getAnimation().hasEnded()) {
            getAnimation().setAnimationListener(null);
            clearAnimation();
            b();
        }
        this.k = z;
        this.n = false;
        b((int) this.a, this.K);
    }

    public void setShowRefreshView(boolean z) {
        setOnlySupportPull(!z);
        this.R = z;
    }

    public void setTargetOrRefreshViewOffsetY(int i) {
        a(i, false);
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i) {
        return this.f.startNestedScroll(i);
    }

    @Override // android.view.View
    public void stopNestedScroll() {
        this.f.stopNestedScroll();
    }
}
