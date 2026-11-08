package com.sigmob.sdk.base.common;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.sigmob.sdk.base.models.BaseAdUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b extends GestureDetector.SimpleOnGestureListener {
    boolean a;
    private final BaseAdUnit b;
    private float c;
    private float d;
    private boolean e;
    private boolean f;
    private int g;
    private float h;
    private a i = a.UNSET;
    private View j;

    enum a {
        UNSET,
        GOING_RIGHT,
        GOING_LEFT,
        FINISHED,
        FAILED
    }

    b(View view, BaseAdUnit baseAdUnit) {
        this.c = 100.0f;
        if (view != null && view.getWidth() > 0) {
            this.c = Math.min(100.0f, view.getWidth() / 3.0f);
        }
        this.a = false;
        this.j = view;
        this.b = baseAdUnit;
    }

    private void a(float f) {
        if (f > this.h) {
            this.i = a.GOING_RIGHT;
        }
    }

    private boolean a(MotionEvent motionEvent, MotionEvent motionEvent2) {
        if (motionEvent == null || motionEvent2 == null) {
            return false;
        }
        return Math.abs(motionEvent2.getY() - motionEvent.getY()) > 100.0f;
    }

    private void b(float f) {
        if (d(f) && g(f)) {
            this.i = a.GOING_LEFT;
            this.h = f;
        }
    }

    private void c() {
        this.g++;
        if (this.g >= 4) {
            this.i = a.FINISHED;
        }
    }

    private void c(float f) {
        if (e(f) && f(f)) {
            this.i = a.GOING_RIGHT;
            this.h = f;
        }
    }

    private boolean d(float f) {
        if (this.f) {
            return true;
        }
        if (f < this.h + this.c) {
            return false;
        }
        this.e = false;
        this.f = true;
        return true;
    }

    private boolean e(float f) {
        if (this.e) {
            return true;
        }
        if (f > this.h - this.c) {
            return false;
        }
        this.f = false;
        this.e = true;
        c();
        return true;
    }

    private boolean f(float f) {
        return f > this.d;
    }

    private boolean g(float f) {
        return f < this.d;
    }

    void a() {
        a aVar = this.i;
        a aVar2 = this.i;
        a aVar3 = a.FINISHED;
        b();
    }

    void b() {
        this.g = 0;
        this.i = a.UNSET;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (this.i == a.FINISHED) {
            return super.onScroll(motionEvent, motionEvent2, f, f2);
        }
        if (a(motionEvent, motionEvent2)) {
            this.i = a.FAILED;
        } else {
            switch (this.i) {
                case UNSET:
                    this.h = motionEvent.getX();
                    a(motionEvent2.getX());
                    break;
                case GOING_RIGHT:
                    b(motionEvent2.getX());
                    break;
                case GOING_LEFT:
                    c(motionEvent2.getX());
                    break;
            }
            this.d = motionEvent2.getX();
        }
        return super.onScroll(motionEvent, motionEvent2, f, f2);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        this.a = true;
        return super.onSingleTapUp(motionEvent);
    }
}
