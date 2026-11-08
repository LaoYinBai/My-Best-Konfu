package com.ss.sys.ck;

import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class SCCheckListener {
    public static final int CLOSE_BY_CLICK_BUTTON = 0;
    public static final int CLOSE_BY_CLICK_SCREEN = 1;
    public static final int CLOSE_BY_ESCAPE = 2;

    public void dialogOnClose(int i) {
    }

    public void dialogOnError(String str) {
    }

    public void dialogOnReady() {
    }

    public void dialogOnResult(boolean z, String str) {
    }
}
