package com.kwad.sdk.utils;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ba {
    private View d;
    public Point c = new Point();
    public Rect a = new Rect();
    public Rect b = new Rect();

    public ba(View view) {
        this.d = view;
    }

    public boolean a() {
        boolean globalVisibleRect = this.d.getGlobalVisibleRect(this.a, this.c);
        if (this.c.x == 0 && this.c.y == 0 && this.a.height() == this.d.getHeight() && this.b.height() != 0 && Math.abs(this.a.top - this.b.top) > this.d.getHeight() / 2) {
            this.a.set(this.b);
        }
        this.b.set(this.a);
        return globalVisibleRect;
    }
}
