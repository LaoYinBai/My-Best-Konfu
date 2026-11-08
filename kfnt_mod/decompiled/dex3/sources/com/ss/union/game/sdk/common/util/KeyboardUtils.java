package com.ss.union.game.sdk.common.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class KeyboardUtils {

    public interface OnKeyboardChangeListener {
        void keyboardHide(int i);

        void keyboardShow(int i);
    }

    private KeyboardUtils() {
    }

    public static void hideKeyboard(Context context) {
        InputMethodManager inputMethodManager;
        Activity activity = (Activity) context;
        if (activity == null || (inputMethodManager = (InputMethodManager) activity.getSystemService("input_method")) == null || !inputMethodManager.isActive() || activity.getCurrentFocus() == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager;
        Activity activity = (Activity) context;
        if (activity == null || view == null || (inputMethodManager = (InputMethodManager) activity.getSystemService("input_method")) == null || !inputMethodManager.isActive()) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyboard(Context context) {
        InputMethodManager inputMethodManager;
        Activity activity = (Activity) context;
        if (activity == null || (inputMethodManager = (InputMethodManager) activity.getSystemService("input_method")) == null) {
            return;
        }
        inputMethodManager.toggleSoftInput(2, 0);
    }

    public static boolean isSoftShowing(Activity activity) {
        int height = activity.getWindow().getDecorView().getHeight();
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return height - rect.bottom >= 400;
    }

    public static KeyboardObserver observeKeyboard(Activity activity, OnKeyboardChangeListener onKeyboardChangeListener) {
        return new KeyboardObserver(activity, activity.getWindow(), onKeyboardChangeListener);
    }

    public static KeyboardObserver observeKeyboard(Context context, Window window, OnKeyboardChangeListener onKeyboardChangeListener) {
        return new KeyboardObserver(context, window, onKeyboardChangeListener);
    }

    public static class KeyboardObserver implements ViewTreeObserver.OnGlobalLayoutListener {
        private Context a;
        private View b;
        private boolean c;
        private int d;
        private int e;
        private Rect f;
        private OnKeyboardChangeListener g;

        private KeyboardObserver(Context context, Window window, OnKeyboardChangeListener onKeyboardChangeListener) {
            this.f = new Rect();
            this.a = context;
            this.c = false;
            this.g = onKeyboardChangeListener;
            this.b = window.getDecorView();
            this.b.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        public void release() {
            if (this.b != null) {
                this.b.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        }

        public boolean isSoftKeyBoardShow() {
            return this.c;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            this.b.getWindowVisibleDisplayFrame(this.f);
            int i = this.a.getResources().getConfiguration().orientation;
            int iHeight = this.f.height();
            if (this.e == 0 || this.d == 0) {
                this.e = iHeight;
                this.d = i;
                return;
            }
            if (this.e == iHeight) {
                return;
            }
            if (this.d != i) {
                this.e = iHeight;
                this.d = i;
                return;
            }
            if (this.e - iHeight > 200) {
                this.c = true;
                if (this.g != null) {
                    this.g.keyboardShow(this.e - iHeight);
                }
                this.e = iHeight;
                return;
            }
            if (iHeight - this.e > 200) {
                this.c = false;
                if (this.g != null) {
                    this.g.keyboardHide(iHeight - this.e);
                }
                this.e = iHeight;
                return;
            }
            this.e = iHeight;
        }
    }
}
