package com.sigmob.sdk.base.views;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class VideoProgressBarWidget extends ImageView {
    private f a;
    private final int b;

    public VideoProgressBarWidget(Context context) {
        super(context);
        this.a = new f(context);
        setImageDrawable(this.a);
        this.b = com.sigmob.sdk.common.utils.c.b(2.0f, context);
    }

    public void a(int i) {
        this.a.a(i);
    }

    public void a(int i, int i2) {
        this.a.a(i, i2);
        setVisibility(0);
    }

    @Deprecated
    f getImageViewDrawable() {
        return this.a;
    }

    public void setAnchorId(int i) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, this.b);
        layoutParams.addRule(12, i);
        setLayoutParams(layoutParams);
    }

    @Deprecated
    void setImageViewDrawable(f fVar) {
        this.a = fVar;
    }
}
