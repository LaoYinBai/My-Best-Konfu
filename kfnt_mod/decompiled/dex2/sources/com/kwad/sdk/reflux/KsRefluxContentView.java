package com.kwad.sdk.reflux;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KsRefluxContentView extends FrameLayout implements GestureDetector.OnGestureListener {
    private GestureDetector a;
    private a b;

    public interface a {
        boolean a();

        boolean b();
    }

    public KsRefluxContentView(@NonNull Context context) {
        super(context);
        a(context, null, 0);
    }

    public KsRefluxContentView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public KsRefluxContentView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    @RequiresApi(api = 21)
    public KsRefluxContentView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context, attributeSet, i);
    }

    private void a(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        this.a = new GestureDetector(context, this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.b == null || this.b.a()) {
            com.kwad.sdk.core.d.a.a("KsRefluxContentView", "dispatchTouchEvent consumed");
            return super.dispatchTouchEvent(motionEvent);
        }
        com.kwad.sdk.core.d.a.a("KsRefluxContentView", "dispatchTouchEvent mGestureDetector onTouchEvent");
        this.a.onTouchEvent(motionEvent);
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        com.kwad.sdk.core.d.a.a("KsRefluxContentView", "onDown");
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        com.kwad.sdk.core.d.a.a("KsRefluxContentView", "onFling");
        if (this.b != null) {
            return this.b.b();
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
        com.kwad.sdk.core.d.a.a("KsRefluxContentView", "onLongPress");
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        com.kwad.sdk.core.d.a.a("KsRefluxContentView", "onScroll");
        if (this.b != null) {
            return this.b.b();
        }
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
        com.kwad.sdk.core.d.a.a("KsRefluxContentView", "onShowPress");
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        com.kwad.sdk.core.d.a.a("KsRefluxContentView", "onSingleTapUp");
        if (this.b != null) {
            return this.b.b();
        }
        return false;
    }

    public void setContentViewListener(a aVar) {
        this.b = aVar;
    }
}
