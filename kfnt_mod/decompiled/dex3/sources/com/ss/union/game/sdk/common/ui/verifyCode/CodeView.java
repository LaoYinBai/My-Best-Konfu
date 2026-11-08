package com.ss.union.game.sdk.common.ui.verifyCode;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.union.game.sdk.common.util.ResourceUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CodeView extends RelativeLayout {
    private TextView a;
    private View b;
    private ValueAnimator c;
    private Parameters d;
    private RelativeLayout e;

    public CodeView(Context context) {
        this(context, null);
    }

    public CodeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CodeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        this.c = ValueAnimator.ofFloat(1.0f, 0.0f);
        this.c.setRepeatCount(-1);
        this.c.setRepeatMode(2);
        this.c.setDuration(600L);
        this.c.setInterpolator(new LinearInterpolator());
        this.c.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ss.union.game.sdk.common.ui.verifyCode.CodeView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (valueAnimator.getAnimatedFraction() < 0.5d) {
                    CodeView.this.b.setBackgroundColor(0);
                } else {
                    CodeView.this.b.setBackgroundColor(CodeView.this.d.c);
                }
            }
        });
    }

    public void initCodeView(Parameters parameters) {
        if (parameters == null) {
            return;
        }
        removeAllViews();
        this.d = parameters;
        this.e = new RelativeLayout(getContext());
        this.e.setLayoutParams(new RelativeLayout.LayoutParams(a(60.0f), a(60.0f)));
        this.e.setBackgroundResource(ResourceUtils.getDrawableIdByName("lg_code_view_gray"));
        addView(this.e);
        this.a = new TextView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams.addRule(14, -1);
        this.a.setTextColor(parameters.e);
        this.a.setTextSize(parameters.d);
        this.a.setGravity(17);
        this.a.setImeOptions(DownloadExpSwitchCode.BACK_CLEAR_DATA);
        this.a.setId(ResourceUtils.getIdByName("lg_cv_text_id"));
        this.a.setInputType(parameters.i);
        this.e.addView(this.a, layoutParams);
        this.b = new View(getContext());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(a(1.5f), -1);
        layoutParams2.addRule(1, ResourceUtils.getIdByName("lg_cv_text_id"));
        layoutParams2.bottomMargin = a(12.0f);
        layoutParams2.topMargin = a(12.0f);
        this.b.setBackgroundColor(parameters.c);
        this.e.addView(this.b, layoutParams2);
        reset();
    }

    public void setSelected() {
        if (this.d.a && this.b != null) {
            this.b.setVisibility(0);
            setCursorFlicker(this.d.b);
        }
        if (this.a != null) {
            this.a.setText("");
        }
        if (this.e != null) {
            this.e.setBackgroundResource(ResourceUtils.getDrawableIdByName("lg_code_view_yellow"));
        }
    }

    public void setError() {
        if (this.e != null) {
            this.e.setBackgroundResource(ResourceUtils.getDrawableIdByName("lg_code_view_error"));
        }
        if (this.b != null) {
            this.b.setVisibility(8);
            setCursorFlicker(false);
        }
    }

    public void showCursor() {
        if (!this.d.a || this.b == null) {
            return;
        }
        this.b.setVisibility(0);
        setCursorFlicker(this.d.b);
    }

    public String getText() {
        return this.a != null ? this.a.getText().toString() : "";
    }

    public void setText(String str) {
        if (this.a != null) {
            this.a.setText(str);
        }
        if (this.e != null) {
            this.e.setBackgroundResource(ResourceUtils.getDrawableIdByName("lg_code_view_yellow"));
        }
        if (this.b != null) {
            this.b.setVisibility(8);
            setCursorFlicker(false);
        }
    }

    public void deleteText() {
        reset();
    }

    public void reset() {
        if (this.a != null) {
            this.a.setText("");
        }
        if (this.e != null) {
            this.e.setBackgroundResource(ResourceUtils.getDrawableIdByName("lg_code_view_gray"));
        }
        if (this.b != null) {
            this.b.setVisibility(8);
            setCursorFlicker(false);
        }
    }

    private int a(float f) {
        return (int) ((f * getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    private void setCursorFlicker(boolean z) {
        if (z) {
            this.c.start();
        } else {
            this.c.cancel();
        }
    }

    public static class Parameters {
        private boolean b;
        private boolean a = true;
        private int c = Color.rgb(51, 119, 255);
        private int d = 17;
        private int e = Color.argb(0, 0, 0, 0);
        private int f = Color.rgb(237, 237, 240);
        private int g = Color.rgb(51, 119, 255);
        private int h = Color.rgb(255, 91, 76);
        private int i = 18;

        public Parameters setShowCursor(boolean z) {
            this.a = z;
            return this;
        }

        public Parameters setFlickerCursor(boolean z) {
            this.b = z;
            return this;
        }

        public Parameters setCursorColor(int i) {
            this.c = i;
            return this;
        }

        public Parameters setTextSize(int i) {
            this.d = i;
            return this;
        }

        public Parameters setTextColor(int i) {
            this.e = i;
            return this;
        }

        public Parameters setUnSelectedLineColor(int i) {
            this.f = i;
            return this;
        }

        public Parameters setSelectedLineColor(int i) {
            this.g = i;
            return this;
        }

        public Parameters setInputType(int i) {
            this.i = i;
            return this;
        }

        public Parameters setErrorColor(int i) {
            this.h = i;
            return this;
        }
    }
}
