package com.sigmob.sdk.videoAd;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class VideoPlayerProgressBarWidget extends ImageView {
    private com.sigmob.sdk.base.views.f a;
    private final int b;

    public VideoPlayerProgressBarWidget(Context context) {
        super(context);
        this.a = new com.sigmob.sdk.base.views.f(context);
        setImageDrawable(this.a);
        this.b = com.sigmob.sdk.common.utils.c.b(2.0f, context);
    }

    @Deprecated
    com.sigmob.sdk.base.views.f getImageViewDrawable() {
        return this.a;
    }

    public void setAnchorId(int i) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, this.b);
        layoutParams.addRule(8, i);
        setLayoutParams(layoutParams);
    }

    @Deprecated
    void setImageViewDrawable(com.sigmob.sdk.base.views.f fVar) {
        this.a = fVar;
    }
}
