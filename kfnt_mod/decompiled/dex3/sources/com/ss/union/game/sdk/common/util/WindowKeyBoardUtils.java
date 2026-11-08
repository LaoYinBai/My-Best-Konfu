package com.ss.union.game.sdk.common.util;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class WindowKeyBoardUtils implements ViewTreeObserver.OnGlobalLayoutListener {
    private View a;
    private int c;
    private int d;
    private OnSoftKeyBoardChangeListener f;
    private Rect e = new Rect();
    private boolean b = false;

    public interface OnSoftKeyBoardChangeListener {
        void keyBoardHide(int i);

        void keyBoardShow(int i);
    }

    private WindowKeyBoardUtils(Window window) {
        this.a = window.getDecorView();
        this.a.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    public static WindowKeyBoardUtils setListener(Window window, OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        WindowKeyBoardUtils windowKeyBoardUtils = new WindowKeyBoardUtils(window);
        windowKeyBoardUtils.a(onSoftKeyBoardChangeListener);
        return windowKeyBoardUtils;
    }

    private void a(OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        this.f = onSoftKeyBoardChangeListener;
    }

    public void release() {
        if (this.a != null) {
            this.a.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }

    public boolean isSoftKeyBoardShow() {
        return this.b;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        this.a.getWindowVisibleDisplayFrame(this.e);
        int iHeight = this.e.height();
        if (this.d == 0) {
            this.d = iHeight;
            return;
        }
        if (this.d == iHeight) {
            return;
        }
        if (this.d - iHeight > 200) {
            this.b = true;
            if (this.f != null) {
                this.f.keyBoardShow(this.d - iHeight);
            }
            this.d = iHeight;
            return;
        }
        if (iHeight - this.d > 200) {
            this.b = false;
            if (this.f != null) {
                this.f.keyBoardHide(iHeight - this.d);
            }
            this.d = iHeight;
            return;
        }
        this.d = iHeight;
    }
}
