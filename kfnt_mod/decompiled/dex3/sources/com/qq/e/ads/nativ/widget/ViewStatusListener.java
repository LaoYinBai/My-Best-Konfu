package com.qq.e.ads.nativ.widget;

import android.view.MotionEvent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ViewStatusListener {
    void onAttachToWindow();

    void onDetachFromWindow();

    void onDispatchTouchEvent(MotionEvent motionEvent);

    void onWindowFocusChanged(boolean z);

    void onWindowVisibilityChanged(int i);
}
