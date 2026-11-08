package com.ss.union.game.sdk.core.photoview;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class b {
    private static final int a = -1;
    private int b = -1;
    private int c = 0;
    private final ScaleGestureDetector d;
    private VelocityTracker e;
    private boolean f;
    private float g;
    private float h;
    private final float i;
    private final float j;
    private c k;

    b(Context context, c cVar) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.j = viewConfiguration.getScaledMinimumFlingVelocity();
        this.i = viewConfiguration.getScaledTouchSlop();
        this.k = cVar;
        this.d = new ScaleGestureDetector(context, new ScaleGestureDetector.OnScaleGestureListener() { // from class: com.ss.union.game.sdk.core.photoview.b.1
            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                return true;
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                float scaleFactor = scaleGestureDetector.getScaleFactor();
                if (Float.isNaN(scaleFactor) || Float.isInfinite(scaleFactor)) {
                    return false;
                }
                if (scaleFactor < 0.0f) {
                    return true;
                }
                b.this.k.a(scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
                return true;
            }
        });
    }

    private float b(MotionEvent motionEvent) {
        try {
            return motionEvent.getX(this.c);
        } catch (Exception unused) {
            return motionEvent.getX();
        }
    }

    private float c(MotionEvent motionEvent) {
        try {
            return motionEvent.getY(this.c);
        } catch (Exception unused) {
            return motionEvent.getY();
        }
    }

    public boolean a() {
        return this.d.isInProgress();
    }

    public boolean b() {
        return this.f;
    }

    public boolean a(MotionEvent motionEvent) {
        try {
            this.d.onTouchEvent(motionEvent);
            return d(motionEvent);
        } catch (IllegalArgumentException unused) {
            return true;
        }
    }

    private boolean d(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action != 6) {
            switch (action) {
                case 0:
                    this.b = motionEvent.getPointerId(0);
                    this.e = VelocityTracker.obtain();
                    if (this.e != null) {
                        this.e.addMovement(motionEvent);
                    }
                    this.g = b(motionEvent);
                    this.h = c(motionEvent);
                    this.f = false;
                    break;
                case 1:
                    this.b = -1;
                    if (this.f && this.e != null) {
                        this.g = b(motionEvent);
                        this.h = c(motionEvent);
                        this.e.addMovement(motionEvent);
                        this.e.computeCurrentVelocity(1000);
                        float xVelocity = this.e.getXVelocity();
                        float yVelocity = this.e.getYVelocity();
                        if (Math.max(Math.abs(xVelocity), Math.abs(yVelocity)) >= this.j) {
                            this.k.a(this.g, this.h, -xVelocity, -yVelocity);
                        }
                    }
                    if (this.e != null) {
                        this.e.recycle();
                        this.e = null;
                        break;
                    }
                    break;
                case 2:
                    float fB = b(motionEvent);
                    float fC = c(motionEvent);
                    float f = fB - this.g;
                    float f2 = fC - this.h;
                    if (!this.f) {
                        this.f = Math.sqrt((double) ((f * f) + (f2 * f2))) >= ((double) this.i);
                    }
                    if (this.f) {
                        this.k.a(f, f2);
                        this.g = fB;
                        this.h = fC;
                        if (this.e != null) {
                            this.e.addMovement(motionEvent);
                            break;
                        }
                    }
                    break;
                case 3:
                    this.b = -1;
                    if (this.e != null) {
                        this.e.recycle();
                        this.e = null;
                        break;
                    }
                    break;
            }
        } else {
            int iA = d.a(motionEvent.getAction());
            if (motionEvent.getPointerId(iA) == this.b) {
                int i = iA == 0 ? 1 : 0;
                this.b = motionEvent.getPointerId(i);
                this.g = motionEvent.getX(i);
                this.h = motionEvent.getY(i);
            }
        }
        this.c = motionEvent.findPointerIndex(this.b != -1 ? this.b : 0);
        return true;
    }
}
