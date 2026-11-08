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
import androidx.annotation.CallSuper;
import com.ss.union.game.sdk.core.glide.request.Request;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class ViewTarget<T extends View, Z> extends BaseTarget<Z> {
    private static final String a = "ViewTarget";
    private static boolean b;
    private static Integer c;
    private final a d;
    private View.OnAttachStateChangeListener e;
    private boolean f;
    private boolean g;
    protected final T view;

    public ViewTarget(T t) {
        this.view = (T) Preconditions.checkNotNull(t);
        this.d = new a(t);
    }

    @Deprecated
    public ViewTarget(T t, boolean z) {
        this(t);
        if (z) {
            waitForLayout();
        }
    }

    public final ViewTarget<T, Z> clearOnDetach() {
        if (this.e != null) {
            return this;
        }
        this.e = new View.OnAttachStateChangeListener() { // from class: com.ss.union.game.sdk.core.glide.request.target.ViewTarget.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view) {
                ViewTarget.this.a();
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view) {
                ViewTarget.this.b();
            }
        };
        c();
        return this;
    }

    void a() {
        Request request = getRequest();
        if (request == null || !request.isCleared()) {
            return;
        }
        request.begin();
    }

    void b() {
        Request request = getRequest();
        if (request != null) {
            this.f = true;
            request.clear();
            this.f = false;
        }
    }

    public final ViewTarget<T, Z> waitForLayout() {
        this.d.b = true;
        return this;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.BaseTarget, com.ss.union.game.sdk.core.glide.request.target.Target
    @CallSuper
    public void onLoadStarted(Drawable drawable) {
        super.onLoadStarted(drawable);
        c();
    }

    private void c() {
        if (this.e == null || this.g) {
            return;
        }
        this.view.addOnAttachStateChangeListener(this.e);
        this.g = true;
    }

    private void d() {
        if (this.e == null || !this.g) {
            return;
        }
        this.view.removeOnAttachStateChangeListener(this.e);
        this.g = false;
    }

    public T getView() {
        return this.view;
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    @CallSuper
    public void getSize(SizeReadyCallback sizeReadyCallback) {
        this.d.a(sizeReadyCallback);
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.Target
    @CallSuper
    public void removeCallback(SizeReadyCallback sizeReadyCallback) {
        this.d.b(sizeReadyCallback);
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.BaseTarget, com.ss.union.game.sdk.core.glide.request.target.Target
    @CallSuper
    public void onLoadCleared(Drawable drawable) {
        super.onLoadCleared(drawable);
        this.d.b();
        if (this.f) {
            return;
        }
        d();
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.BaseTarget, com.ss.union.game.sdk.core.glide.request.target.Target
    public void setRequest(Request request) {
        a(request);
    }

    @Override // com.ss.union.game.sdk.core.glide.request.target.BaseTarget, com.ss.union.game.sdk.core.glide.request.target.Target
    public Request getRequest() {
        Object objE = e();
        if (objE == null) {
            return null;
        }
        if (objE instanceof Request) {
            return (Request) objE;
        }
        throw new IllegalArgumentException("You must not call setTag() on a view Glide is targeting");
    }

    public String toString() {
        return "Target for: " + this.view;
    }

    private void a(Object obj) {
        if (c == null) {
            b = true;
            this.view.setTag(obj);
        } else {
            this.view.setTag(c.intValue(), obj);
        }
    }

    private Object e() {
        if (c == null) {
            return this.view.getTag();
        }
        return this.view.getTag(c.intValue());
    }

    public static void setTagId(int i) {
        if (c != null || b) {
            throw new IllegalArgumentException("You cannot set the tag id more than once or change the tag id after the first request has been made");
        }
        c = Integer.valueOf(i);
    }

    static final class a {
        static Integer a;
        private static final int c = 0;
        boolean b;
        private final View d;
        private final List<SizeReadyCallback> e = new ArrayList();
        private ViewTreeObserverOnPreDrawListenerC0086a f;

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
                this.f = new ViewTreeObserverOnPreDrawListenerC0086a(this);
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
            if (Log.isLoggable(ViewTarget.a, 4)) {
                Log.i(ViewTarget.a, "Glide treats LayoutParams.WRAP_CONTENT as a request for an image the size of this device's screen dimensions. If you want to load the original image and are ok with the corresponding memory cost and OOMs (depending on the input size), use .override(Target.SIZE_ORIGINAL). Otherwise, use LayoutParams.MATCH_PARENT, set layout_width and layout_height to fixed dimension, or use .override() with fixed dimensions.");
            }
            return a(this.d.getContext());
        }

        /* renamed from: com.ss.union.game.sdk.core.glide.request.target.ViewTarget$a$a, reason: collision with other inner class name */
        private static final class ViewTreeObserverOnPreDrawListenerC0086a implements ViewTreeObserver.OnPreDrawListener {
            private final WeakReference<a> a;

            ViewTreeObserverOnPreDrawListenerC0086a(a aVar) {
                this.a = new WeakReference<>(aVar);
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (Log.isLoggable(ViewTarget.a, 2)) {
                    Log.v(ViewTarget.a, "OnGlobalLayoutListener called attachStateListener=" + this);
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
