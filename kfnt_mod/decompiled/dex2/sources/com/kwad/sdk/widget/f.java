package com.kwad.sdk.widget;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f implements GestureDetector.OnGestureListener, View.OnTouchListener {
    private View a;
    private GestureDetector b;
    private MotionEvent c;
    private d d;

    public f(Context context, @NonNull View view, d dVar) {
        this.a = view;
        this.a.setOnTouchListener(this);
        this.b = new GestureDetector(context, this);
        this.d = dVar;
    }

    public f(@NonNull View view, d dVar) {
        this.a = view;
        this.a.setOnTouchListener(this);
        this.b = new GestureDetector(view.getContext(), this);
        this.d = dVar;
    }

    private static boolean a(MotionEvent motionEvent, MotionEvent motionEvent2) {
        return ((Math.abs(motionEvent.getX() - motionEvent2.getX()) > 20.0f ? 1 : (Math.abs(motionEvent.getX() - motionEvent2.getX()) == 20.0f ? 0 : -1)) > 0) || ((Math.abs(motionEvent.getY() - motionEvent2.getY()) > 20.0f ? 1 : (Math.abs(motionEvent.getY() - motionEvent2.getY()) == 20.0f ? 0 : -1)) > 0);
    }

    private boolean a(View view, MotionEvent motionEvent) {
        MotionEvent motionEventObtain;
        int action = motionEvent.getAction();
        boolean z = false;
        if (action != 0) {
            if (action == 1) {
                if (this.c != null && a(this.c, motionEvent)) {
                    if (this.d != null) {
                        this.d.b(view);
                    }
                    z = true;
                }
                motionEventObtain = null;
            }
            return z;
        }
        motionEventObtain = MotionEvent.obtain(motionEvent);
        this.c = motionEventObtain;
        return z;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        com.kwad.sdk.core.d.a.a("ViewGestureHelper", "onFling");
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        com.kwad.sdk.core.d.a.a("ViewGestureHelper", "onSingleTapUp");
        if (this.d == null) {
            return false;
        }
        this.d.a_(this.a);
        return true;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean zOnTouchEvent = this.b.onTouchEvent(motionEvent);
        com.kwad.sdk.core.d.a.a("ViewGestureHelper", "onTouch, " + motionEvent.getAction() + "， handled： " + zOnTouchEvent);
        if (zOnTouchEvent) {
            return true;
        }
        return a(view, motionEvent);
    }
}
