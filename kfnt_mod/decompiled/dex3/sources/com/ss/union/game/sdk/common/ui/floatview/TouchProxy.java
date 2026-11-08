package com.ss.union.game.sdk.common.ui.floatview;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TouchProxy {
    private static final int a = 4;
    private static final int b = 1000;
    private OnTouchEventListener c;
    private int d;
    private int e;
    private int f;
    private int g;
    private a h = a.STATE_STOP;

    public interface OnTouchEventListener {
        void onDown(int i, int i2);

        void onMove(int i, int i2, int i3, int i4);

        void onUp(int i, int i2);
    }

    private enum a {
        STATE_MOVE,
        STATE_STOP
    }

    public TouchProxy(OnTouchEventListener onTouchEventListener) {
        this.c = onTouchEventListener;
    }

    public boolean onTouchEvent(View view, MotionEvent motionEvent) {
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        switch (motionEvent.getAction()) {
            case 0:
                this.f = rawX;
                this.g = rawY;
                this.e = rawY;
                this.d = rawX;
                if (this.c != null) {
                    this.c.onDown(rawX, rawY);
                    break;
                }
                break;
            case 1:
                if (this.c != null) {
                    this.c.onUp(rawX, rawY);
                }
                if (this.h != a.STATE_MOVE && motionEvent.getEventTime() - motionEvent.getDownTime() < 1000) {
                    view.performClick();
                }
                this.h = a.STATE_STOP;
                break;
            case 2:
                int scaledTouchSlop = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
                if (Math.abs(rawX - this.f) < scaledTouchSlop && Math.abs(rawY - this.g) < scaledTouchSlop) {
                    if (this.h == a.STATE_STOP) {
                    }
                } else if (this.h != a.STATE_MOVE) {
                    this.h = a.STATE_MOVE;
                }
                if (this.c != null && this.h == a.STATE_MOVE) {
                    this.c.onMove(rawX, rawY, rawX - this.d, rawY - this.e);
                }
                this.e = rawY;
                this.d = rawX;
                break;
        }
        return true;
    }
}
