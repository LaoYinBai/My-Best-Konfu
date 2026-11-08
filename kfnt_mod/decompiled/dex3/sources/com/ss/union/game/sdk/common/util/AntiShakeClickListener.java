package com.ss.union.game.sdk.common.util;

import android.view.View;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AntiShakeClickListener implements View.OnClickListener {
    private long a;
    private long b;
    private View.OnClickListener c;

    public AntiShakeClickListener(View.OnClickListener onClickListener) {
        this(onClickListener, 600L);
    }

    public AntiShakeClickListener(View.OnClickListener onClickListener, long j) {
        this.b = 0L;
        this.a = j;
        this.c = onClickListener;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (System.currentTimeMillis() - this.b >= this.a) {
            this.b = System.currentTimeMillis();
            if (this.c != null) {
                this.c.onClick(view);
            }
        }
    }
}
