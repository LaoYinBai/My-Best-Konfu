package com.bytedance.msdk.core.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.annotation.UiThread;
import com.bytedance.msdk.adapter.util.Logger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class RefreshableBannerView extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    private boolean f4501a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f4502b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f4503c;
    private final Rect d;
    private final ViewTreeObserver.OnScrollChangedListener e;
    private c f;

    class a implements ViewTreeObserver.OnScrollChangedListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            RefreshableBannerView refreshableBannerView = RefreshableBannerView.this;
            refreshableBannerView.f4502b = refreshableBannerView.getGlobalVisibleRect(refreshableBannerView.d);
            RefreshableBannerView refreshableBannerView2 = RefreshableBannerView.this;
            refreshableBannerView2.a(refreshableBannerView2.f4502b);
        }
    }

    class b extends AnimatorListenerAdapter {
        b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (RefreshableBannerView.this.getChildCount() > 1) {
                RefreshableBannerView.this.removeViewAt(0);
                Logger.d("TMe", "--==-- after remove, view count: " + RefreshableBannerView.this.getChildCount());
            }
        }
    }

    public interface c {
        void a(boolean z);
    }

    public RefreshableBannerView(Context context) {
        super(context);
        this.f4501a = true;
        this.f4502b = true;
        this.f4503c = true;
        this.d = new Rect();
        this.e = new a();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnScrollChangedListener(this.e);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnScrollChangedListener(this.e);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.f4501a = i == 0;
        a(this.f4501a);
    }

    public void setVisibilityChangeListener(c cVar) {
        this.f = cVar;
    }

    @UiThread
    public void a(View view) {
        view.setTranslationX(getWidth());
        addView(view);
        ObjectAnimator.ofFloat(view, "translationX", 0.0f).setDuration(250L).start();
        if (getChildCount() > 1) {
            ObjectAnimator duration = ObjectAnimator.ofFloat(getChildAt(0), "translationX", -getWidth()).setDuration(250L);
            duration.addListener(new b());
            duration.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        boolean z2 = this.f4501a && this.f4502b;
        if (z) {
            if (!z2 || this.f4503c) {
                return;
            }
            this.f4503c = true;
            c cVar = this.f;
            if (cVar != null) {
                cVar.a(true);
                return;
            }
            return;
        }
        if (z2 || !this.f4503c) {
            return;
        }
        this.f4503c = false;
        c cVar2 = this.f;
        if (cVar2 != null) {
            cVar2.a(false);
        }
    }
}
