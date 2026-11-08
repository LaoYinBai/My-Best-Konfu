package com.sigmob.sdk.base.common;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.sigmob.sdk.base.models.BaseAdUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ab extends GestureDetector {
    private final View a;
    private b b;

    private ab(Context context, View view, b bVar) {
        super(context, bVar);
        this.b = bVar;
        this.a = view;
        setIsLongpressEnabled(false);
    }

    public ab(Context context, View view, BaseAdUnit baseAdUnit) {
        this(context, view, new b(view, baseAdUnit));
    }

    private boolean a(MotionEvent motionEvent, View view) {
        if (motionEvent == null || view == null) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        return x >= 0.0f && x <= ((float) view.getWidth()) && y >= 0.0f && y <= ((float) view.getHeight());
    }

    void a() {
        this.b.b();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.GestureDetector
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
        switch (motionEvent.getAction()) {
            case 1:
                this.b.a();
                return zOnTouchEvent;
            case 2:
                if (!a(motionEvent, this.a)) {
                    a();
                }
                return zOnTouchEvent;
            default:
                return zOnTouchEvent;
        }
    }
}
