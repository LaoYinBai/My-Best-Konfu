package com.kwad.sdk.core.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import androidx.annotation.MainThread;
import com.kwad.sdk.utils.z;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KsAdContainer extends RelativeLayout {
    protected z.a a;

    public KsAdContainer(Context context) {
        super(context);
        this.a = new z.a();
    }

    public KsAdContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = new z.a();
    }

    public KsAdContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new z.a();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.a = new z.a(getWidth(), getHeight());
                this.a.a(motionEvent.getX(), motionEvent.getY());
                break;
            case 1:
                this.a.b(motionEvent.getX(), motionEvent.getY());
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @MainThread
    public z.a getTouchCoords() {
        return this.a;
    }
}
