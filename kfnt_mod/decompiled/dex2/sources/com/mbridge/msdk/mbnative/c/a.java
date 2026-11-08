package com.mbridge.msdk.mbnative.c;

import android.graphics.Rect;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import com.mbridge.msdk.foundation.tools.s;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ImpressionTracker.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    private WeakReference<ViewTreeObserver> a;
    private List<View> b;
    private ViewTreeObserver.OnPreDrawListener c;
    private InterfaceC0122a d;
    private Handler e;
    private boolean f;

    /* compiled from: ImpressionTracker.java */
    /* renamed from: com.mbridge.msdk.mbnative.c.a$a, reason: collision with other inner class name */
    public interface InterfaceC0122a {
        void a(ArrayList<View> arrayList, ArrayList<View> arrayList2);
    }

    public a(List<View> list, InterfaceC0122a interfaceC0122a, Handler handler) {
        this.b = new ArrayList();
        this.c = null;
        this.d = interfaceC0122a;
        this.e = handler;
        if (list != null) {
            this.b = list;
        } else {
            this.b.clear();
        }
        try {
            b();
        } catch (Throwable th) {
            s.b("ImpressionTracker", th.getMessage(), th);
        }
        try {
            this.c = new ViewTreeObserver.OnPreDrawListener() { // from class: com.mbridge.msdk.mbnative.c.a.1
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public final boolean onPreDraw() {
                    a.this.b();
                    return true;
                }
            };
        } catch (Throwable th2) {
            s.b("ImpressionTracker", th2.getMessage(), th2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.f) {
            return;
        }
        if (this.e != null) {
            this.e.postDelayed(new Runnable() { // from class: com.mbridge.msdk.mbnative.c.a.2
                @Override // java.lang.Runnable
                public final void run() {
                    a.b(a.this);
                }
            }, 100L);
        }
        this.f = true;
    }

    public final void a(View view) {
        View view2;
        View viewA = null;
        if (view != null) {
            viewA = e.a(view.getContext(), view);
            this.b.add(view);
        } else if (this.b != null && this.b.size() > 0) {
            for (int i = 0; i < this.b.size() && ((view2 = this.b.get(i)) == null || (viewA = e.a(view2.getContext(), view2)) == null); i++) {
            }
        }
        if (viewA == null) {
            return;
        }
        ViewTreeObserver viewTreeObserver = viewA.getViewTreeObserver();
        if (viewTreeObserver == null || viewTreeObserver.isAlive()) {
            this.a = new WeakReference<>(viewTreeObserver);
            if (this.c != null) {
                viewTreeObserver.addOnPreDrawListener(this.c);
            }
        }
    }

    public final void a() {
        try {
            this.f = false;
            if (this.a != null && this.a.get() != null) {
                ViewTreeObserver viewTreeObserver = this.a.get();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnPreDrawListener(this.c);
                }
                this.a.clear();
            }
            this.d = null;
            this.c = null;
            if (this.b != null) {
                this.b.clear();
            }
            this.b = null;
        } catch (Throwable unused) {
        }
    }

    static /* synthetic */ void b(a aVar) {
        boolean z;
        try {
            aVar.f = false;
            if (aVar.b == null || aVar.b.size() <= 0) {
                return;
            }
            ArrayList<View> arrayList = new ArrayList<>();
            ArrayList<View> arrayList2 = new ArrayList<>();
            for (int i = 0; i < aVar.b.size(); i++) {
                View view = aVar.b.get(i);
                if (view != null && view.getVisibility() == 0) {
                    Rect rect = new Rect();
                    if (view.getGlobalVisibleRect(rect)) {
                        long jHeight = rect.height() * rect.width();
                        long height = view.getHeight() * view.getWidth();
                        z = height > 0 && ((double) jHeight) > ((double) height) * 0.4d;
                    }
                }
                if (z) {
                    arrayList.add(view);
                } else {
                    arrayList2.add(view);
                }
            }
            if (aVar.d != null) {
                aVar.d.a(arrayList, arrayList2);
            }
            if (arrayList.size() > 0) {
                aVar.a();
            }
            arrayList.clear();
            arrayList2.clear();
        } catch (Exception unused) {
        }
    }
}
