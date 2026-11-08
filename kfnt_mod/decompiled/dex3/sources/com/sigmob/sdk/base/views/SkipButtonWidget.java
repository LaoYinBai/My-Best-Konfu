package com.sigmob.sdk.base.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.union.game.sdk.common.ui.roundedimageview.RoundedDrawable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SkipButtonWidget extends RelativeLayout {
    int a;
    boolean b;
    private final TextView c;
    private final TextView d;
    private int e;

    public SkipButtonWidget(Context context) {
        super(context);
        this.a = 0;
        this.b = false;
        this.a = com.sigmob.sdk.common.utils.c.c(30.0f, context);
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.a, -2);
        this.c = new TextView(context);
        this.d = new TextView(context);
        setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(this.a, this.a);
        layoutParams2.addRule(9);
        if (Build.VERSION.SDK_INT >= 17) {
            layoutParams2.addRule(20);
        }
        this.d.setTextColor(RoundedDrawable.DEFAULT_BORDER_COLOR);
        this.d.setId(com.sigmob.sdk.common.a.r());
        this.d.setTextSize(1, 14.0f);
        this.d.setGravity(17);
        addView(this.d, layoutParams2);
        int i = this.a / 2;
        int color = Color.parseColor("#ffffff");
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(i);
        gradientDrawable.setAlpha(102);
        setBackground(gradientDrawable);
        setContentDescription(PointCategory.SKIP);
    }

    public void a(int i) {
        this.e = i;
        if (!this.b) {
            if (i > 0) {
                this.d.setText(String.valueOf(i));
            }
        } else if (i > 0) {
            this.c.setText(com.sigmob.sdk.base.b.c(Integer.valueOf(this.e)));
            if (this.c.getVisibility() != 0) {
                this.c.setVisibility(0);
            }
        }
    }

    public boolean a() {
        return this.b;
    }

    public void b() {
        if (this.b) {
            return;
        }
        this.b = true;
        if (this.b) {
            SigmobLog.d("show skip widget");
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, this.a);
        this.c.setTextColor(RoundedDrawable.DEFAULT_BORDER_COLOR);
        this.c.setTextSize(1, 14.0f);
        this.c.setGravity(17);
        this.c.setVisibility(0);
        this.d.setVisibility(8);
        this.c.setPadding((int) (this.a / 3.0f), 0, (int) (this.a / 3.0f), 0);
        if (this.e > 0) {
            this.c.setText(com.sigmob.sdk.base.b.c(Integer.valueOf(this.e)));
        } else {
            this.c.setText(com.sigmob.sdk.base.b.g());
        }
        addView(this.c, layoutParams);
    }

    public int getTime() {
        return this.e;
    }
}
