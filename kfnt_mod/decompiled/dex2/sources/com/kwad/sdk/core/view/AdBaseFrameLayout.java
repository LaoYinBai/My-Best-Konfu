package com.kwad.sdk.core.view;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.MainThread;
import com.kwad.sdk.core.d.a;
import com.kwad.sdk.utils.z;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class AdBaseFrameLayout extends FrameLayout {
    private static final z.a b = new z.a();
    private View.OnTouchListener a;

    public AdBaseFrameLayout(Context context) {
        super(context);
    }

    public AdBaseFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AdBaseFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        try {
            super.dispatchRestoreInstanceState(sparseArray);
        } catch (Throwable th) {
            a.a(th);
            com.kwad.sdk.core.kwai.a.a(th);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.a != null) {
            this.a.onTouch(this, motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 0:
                b.a(getWidth(), getHeight());
                b.a(motionEvent.getX(), motionEvent.getY());
                break;
            case 1:
                b.b(motionEvent.getX(), motionEvent.getY());
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @MainThread
    public z.a getTouchCoords() {
        return b;
    }

    @Override // android.view.View
    public void saveHierarchyState(SparseArray<Parcelable> sparseArray) {
    }

    public void setDispatchTouchListener(View.OnTouchListener onTouchListener) {
        this.a = onTouchListener;
    }
}
