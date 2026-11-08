package com.tachikoma.core.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.tachikoma.core.yoga.layout.YogaLayout;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKYogaLayout extends YogaLayout {
    private DisPatchEventListener mListener;

    public interface DisPatchEventListener {
        boolean dispatchTouchEvent(MotionEvent motionEvent);
    }

    public TKYogaLayout(Context context) {
        super(context);
    }

    public TKYogaLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TKYogaLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.mListener != null ? this.mListener.dispatchTouchEvent(motionEvent) : super.dispatchTouchEvent(motionEvent);
    }

    public void registerDispatchListener(DisPatchEventListener disPatchEventListener) {
        this.mListener = disPatchEventListener;
    }

    public void unRegisterListener() {
        this.mListener = null;
    }
}
