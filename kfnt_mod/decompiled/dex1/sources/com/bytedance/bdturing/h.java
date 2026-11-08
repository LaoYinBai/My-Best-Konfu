package com.bytedance.bdturing;

import android.view.MotionEvent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class h {

    /* renamed from: a, reason: collision with root package name */
    private int f3796a = 0;

    h() {
    }

    public void a(boolean z, MotionEvent motionEvent) {
        float pressure = motionEvent.getPressure();
        float touchMajor = motionEvent.getTouchMajor();
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        long eventTime = motionEvent.getEventTime();
        if (a(action)) {
            e.a(eventTime, pressure, touchMajor, action, x, y);
        }
        g.a("onTouch", "action = " + action);
    }

    private boolean a(int i) {
        if (i != 2) {
            this.f3796a = 0;
            return true;
        }
        switch (this.f3796a) {
            case 0:
                this.f3796a++;
                break;
            case 1:
            case 2:
            case 3:
            case 4:
                this.f3796a++;
                break;
            default:
                this.f3796a = 1;
                break;
        }
        return true;
    }
}
