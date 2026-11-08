package com.ss.union.game.sdk.core.glide.request.target;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import androidx.annotation.IdRes;
import com.ss.union.game.sdk.core.glide.request.Request;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class CustomViewTarget<T extends View, Z> implements Target<Z> {
    private static final String a = "CustomViewTarget";
    private static final int b = 12;
    private final a c;
    private View.OnAttachStateChangeListener d;
    private boolean e;
    private boolean f;

    @IdRes
    private int g;
    protected final T view;

    @Override // com.ss.union.game.sdk.core.glide.manager.LifecycleListener
    public void onDestroy() {
    }

    protected abstract void onResourceCleared(Drawable drawable);

    protected void onResourceLoading(Drawable drawable) {
    }

    @Override // com.ss.union.game.sdk.core.glide.manager.LifecycleListener
    public void onStart() {
    }

    @Override // com.ss.union.game.sdk.core.glide.manager.LifecycleListener
    public void onStop() {
    }

    public CustomViewTarget(T t) {
        this.view = (T) Preconditions.checkNotNull(t);
        this.c = new a(t);
    }

    public final CustomViewTarget<T, Z> waitForLayout() {
        this.c.b = true;
        return this;
    }

    public final CustomViewTarget<T, Z> clearOnDetach() {
        if (this.d != null) {
            return this;
        }
        this.d = new View.OnAttachStateChangeListener() { // from class: com.ss.union.game.sdk.core.glide.request.target.CustomViewTarget.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
                CustomViewTarget.this.a();
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
                CustomViewTarget.this.b();
            }
        };
        d();
        return this;
    }

    public final CustomViewTarget<T, Z> useTagId(@IdRes int i) {
        if (this.g != 0) {
            throw new IllegalArgumentException("You cannot change the tag id once it has been set.");
        }
        this.g = i;
        return this;
    }

    public final T getView() {
        return this.view;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public final void getSize(SizeReadyCallback sizeReadyCallback) {
        this.c.a(sizeReadyCallback);
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public final void removeCallback(SizeReadyCallback sizeReadyCallback) {
        this.c.b(sizeReadyCallback);
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public final void onLoadStarted(Drawable drawable) {
        d();
        onResourceLoading(drawable);
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public final void onLoadCleared(Drawable drawable) {
        this.c.b();
        onResourceCleared(drawable);
        if (this.e) {
            return;
        }
        e();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public final void setRequest(Request request) {
        a(request);
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    public final Request getRequest() {
        Object objC = c();
        if (objC == null) {
            return null;
        }
        if (objC instanceof Request) {
            return (Request) objC;
        }
        throw new IllegalArgumentException("You must not pass non-R.id ids to setTag(id)");
    }

    public String toString() {
        return "Target for: " + this.view;
    }

    final void a() {
        Request request = getRequest();
        if (request == null || !request.isCleared()) {
            return;
        }
        request.begin();
    }

    final void b() {
        Request request = getRequest();
        if (request != null) {
            this.e = true;
            request.clear();
            this.e = false;
        }
    }

    private void a(Object obj) {
        this.view.setTag(this.g == 0 ? 12 : this.g, obj);
    }

    private Object c() {
        return this.view.getTag(this.g == 0 ? 12 : this.g);
    }

    private void d() {
        if (this.d == null || this.f) {
            return;
        }
        this.view.addOnAttachStateChangeListener(this.d);
        this.f = true;
    }

    private void e() {
        if (this.d == null || !this.f) {
            return;
        }
        this.view.removeOnAttachStateChangeListener(this.d);
        this.f = false;
    }

    static final class a {
        static Integer a;
        private static final int c = 0;
        boolean b;
        private final View d;
        private final List<SizeReadyCallback> e = new ArrayList();
        private ViewTreeObserverOnPreDrawListenerC0085a f;

        private boolean a(int i) {
            return i > 0 || i == Integer.MIN_VALUE;
        }

        a(View view) {
            this.d = view;
        }

        private static int a(Context context) {
            if (a == null) {
                Display defaultDisplay = ((WindowManager) Preconditions.checkNotNull((WindowManager) context.getSystemService("window"))).getDefaultDisplay();
                Point point = new Point();
                defaultDisplay.getSize(point);
                a = Integer.valueOf(Math.max(point.x, point.y));
            }
            return a.intValue();
        }

        private void a(int i, int i2) {
            Iterator it = new ArrayList(this.e).iterator();
            while (it.hasNext()) {
                ((SizeReadyCallback) it.next()).onSizeReady(i, i2);
            }
        }

        void a() {
            if (this.e.isEmpty()) {
                return;
            }
            int iD = d();
            int iC = c();
            if (b(iD, iC)) {
                a(iD, iC);
                b();
            }
        }

        void a(SizeReadyCallback sizeReadyCallback) {
            int iD = d();
            int iC = c();
            if (b(iD, iC)) {
                sizeReadyCallback.onSizeReady(iD, iC);
                return;
            }
            if (!this.e.contains(sizeReadyCallback)) {
                this.e.add(sizeReadyCallback);
            }
            if (this.f == null) {
                ViewTreeObserver viewTreeObserver = this.d.getViewTreeObserver();
                this.f = new ViewTreeObserverOnPreDrawListenerC0085a(this);
                viewTreeObserver.addOnPreDrawListener(this.f);
            }
        }

        void b(SizeReadyCallback sizeReadyCallback) {
            this.e.remove(sizeReadyCallback);
        }

        void b() {
            ViewTreeObserver viewTreeObserver = this.d.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.f);
            }
            this.f = null;
            this.e.clear();
        }

        private boolean b(int i, int i2) {
            return a(i) && a(i2);
        }

        private int c() {
            int paddingTop = this.d.getPaddingTop() + this.d.getPaddingBottom();
            ViewGroup.LayoutParams layoutParams = this.d.getLayoutParams();
            return a(this.d.getHeight(), layoutParams != null ? layoutParams.height : 0, paddingTop);
        }

        private int d() {
            int paddingLeft = this.d.getPaddingLeft() + this.d.getPaddingRight();
            ViewGroup.LayoutParams layoutParams = this.d.getLayoutParams();
            return a(this.d.getWidth(), layoutParams != null ? layoutParams.width : 0, paddingLeft);
        }

        private int a(int i, int i2, int i3) {
            int i4 = i2 - i3;
            if (i4 > 0) {
                return i4;
            }
            if (this.b && this.d.isLayoutRequested()) {
                return 0;
            }
            int i5 = i - i3;
            if (i5 > 0) {
                return i5;
            }
            if (this.d.isLayoutRequested() || i2 != -2) {
                return 0;
            }
            if (Log.isLoggable(CustomViewTarget.a, 4)) {
                Log.i(CustomViewTarget.a, "Glide treats LayoutParams.WRAP_CONTENT as a request for an image the size of this device's screen dimensions. If you want to load the original image and are ok with the corresponding memory cost and OOMs (depending on the input size), use .override(Target.SIZE_ORIGINAL). Otherwise, use LayoutParams.MATCH_PARENT, set layout_width and layout_height to fixed dimension, or use .override() with fixed dimensions.");
            }
            return a(this.d.getContext());
        }

        /* renamed from: com.ss.union.game.sdk.core.glide.request.target.CustomViewTarget$a$a, reason: collision with other inner class name */
        private static final class ViewTreeObserverOnPreDrawListenerC0085a implements ViewTreeObserver.OnPreDrawListener {
            private final WeakReference<a> a;

            ViewTreeObserverOnPreDrawListenerC0085a(a aVar) {
                this.a = new WeakReference<>(aVar);
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (Log.isLoggable(CustomViewTarget.a, 2)) {
                    Log.v(CustomViewTarget.a, "OnGlobalLayoutListener called attachStateListener=" + this);
                }
                a aVar = this.a.get();
                if (aVar == null) {
                    return true;
                }
                aVar.a();
                return true;
            }
        }
    }
}
