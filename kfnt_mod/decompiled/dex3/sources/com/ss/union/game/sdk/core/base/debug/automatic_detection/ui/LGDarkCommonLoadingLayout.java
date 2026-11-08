package com.ss.union.game.sdk.core.base.debug.automatic_detection.ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGDarkCommonLoadingLayout extends BaseCommonLayout {
    ObjectAnimator a;
    private ImageView b;
    private TextView c;
    private TextView d;

    @Override // com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.BaseCommonLayout
    public String getLayoutName() {
        return "lg_dark_common_loading_layout";
    }

    public LGDarkCommonLoadingLayout(Context context) {
        this(context, null);
    }

    public LGDarkCommonLoadingLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LGDarkCommonLoadingLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.ss.union.game.sdk.core.base.debug.automatic_detection.ui.BaseCommonLayout
    public void a() {
        super.a();
        this.b = (ImageView) a("lg_dark_common_loading_view");
        this.c = (TextView) a("lg_dark_common_loading_tv_title");
        this.d = (TextView) a("lg_dark_common_loading_tv_description");
    }

    public void i() {
        if (this.a == null) {
            this.a = ObjectAnimator.ofFloat(this.b, "rotation", 0.0f, 359.0f);
        }
        this.a.setInterpolator(new LinearInterpolator());
        this.a.setDuration(600L);
        this.a.setRepeatMode(1);
        this.a.setRepeatCount(-1);
        this.a.start();
    }

    public void j() {
        if (this.a == null || !this.a.isRunning()) {
            return;
        }
        this.a.cancel();
    }
}
