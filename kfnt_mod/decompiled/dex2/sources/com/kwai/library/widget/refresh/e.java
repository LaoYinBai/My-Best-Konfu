package com.kwai.library.widget.refresh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import com.kwad.sdk.R;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e extends FrameLayout {
    private boolean a;
    private AnimationDrawable b;

    @DrawableRes
    private int c;
    private View d;
    private TextView e;
    private TextView f;

    public e(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public e(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = R.drawable.ksad_kwai_loading_view_gradient;
        a(context, attributeSet, 0);
    }

    private void a(int i) {
        a(i == 0);
    }

    private void a(Context context, AttributeSet attributeSet, @StyleRes int i) {
        LayoutInflater.from(context).inflate(R.layout.ksad_kwai_default_loading_view, this);
        this.d = findViewById(R.id.kwai_default_loading_view);
        this.e = (TextView) findViewById(R.id.kwai_default_loading_text_view);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.KwaiLoadingView, 0, i);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.KwaiLoadingView_loading_anim, 0);
        CharSequence text = typedArrayObtainStyledAttributes.getText(R.styleable.KwaiLoadingView_loading_text);
        float dimension = typedArrayObtainStyledAttributes.getDimension(R.styleable.KwaiLoadingView_loading_width, 0.0f);
        float dimension2 = typedArrayObtainStyledAttributes.getDimension(R.styleable.KwaiLoadingView_loading_hight, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
        setLoadingText(text);
        if (resourceId != 0) {
            this.c = resourceId;
        }
        if (dimension <= 0.0f || dimension2 <= 0.0f) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = this.d.getLayoutParams();
        layoutParams.width = (int) dimension;
        layoutParams.height = (int) dimension2;
        this.d.setLayoutParams(layoutParams);
    }

    private void a(boolean z) {
        if (z) {
            a();
        } else {
            b();
        }
    }

    private void c() {
        if (this.d.getBackground() == null) {
            this.b = (AnimationDrawable) getContext().getResources().getDrawable(this.c);
            this.d.setBackground(this.b);
        }
    }

    public void a() {
        c();
        if (this.b != null) {
            this.b.start();
        }
    }

    public void a(boolean z, CharSequence charSequence) {
        if (this.d == null) {
            return;
        }
        if (z || charSequence != null) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        this.d.setVisibility(z ? 0 : 8);
        try {
            this.e.setText(charSequence);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.e.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        if (TextUtils.isEmpty(charSequence)) {
            this.e.setVisibility(8);
        } else {
            this.e.setVisibility(0);
        }
    }

    public void b() {
        if (this.b != null) {
            this.b.stop();
        }
    }

    public TextView getTitleDetailView() {
        if (this.f == null) {
            this.f = new TextView(getContext(), null, R.style.Theme_Widget_Text);
            this.f.setGravity(17);
            this.f.setTextColor(getResources().getColor(R.color.loading_title_color));
            this.f.setTextSize(0, getContext().getResources().getDimension(R.dimen.text_size_12));
            LinearLayout linearLayout = (LinearLayout) this.d.getParent();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.topMargin = g.a(getContext(), 5.0f);
            layoutParams.gravity = 1;
            linearLayout.addView(this.f, layoutParams);
        }
        return this.f;
    }

    public TextView getTitleView() {
        return this.e;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        b();
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.View
    public void onVisibilityAggregated(boolean z) {
        super.onVisibilityAggregated(z);
        if (z != this.a) {
            this.a = z;
            a(z);
        }
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        a(i);
    }

    public void setAnimRes(@DrawableRes int i) {
        this.c = i;
    }

    public void setLoadingText(CharSequence charSequence) {
        TextView textView;
        int i;
        this.e.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            textView = this.e;
            i = 8;
        } else {
            textView = this.e;
            i = 0;
        }
        textView.setVisibility(i);
    }

    public void setTitleDetailText(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            getTitleDetailView().setVisibility(0);
            getTitleDetailView().setText(charSequence);
        } else if (this.f != null) {
            this.f.setVisibility(8);
        }
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        int visibility = getVisibility();
        super.setVisibility(i);
        if (visibility != i) {
            a(i);
        }
    }
}
