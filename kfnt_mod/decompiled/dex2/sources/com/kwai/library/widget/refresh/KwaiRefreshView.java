package com.kwai.library.widget.refresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KwaiRefreshView extends RelativeLayout implements f {
    private ImageView a;
    private boolean b;

    @DrawableRes
    private int c;

    @Nullable
    private AnimationDrawable d;

    @Nullable
    private Runnable e;

    public KwaiRefreshView(Context context) {
        this(context, null);
    }

    public KwaiRefreshView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public KwaiRefreshView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = R.drawable.ksad_kwai_loading_view_gray;
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.KwaiRefreshView);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.KwaiRefreshView_loading_anim, 0);
        typedArrayObtainStyledAttributes.recycle();
        if (resourceId != 0) {
            this.c = resourceId;
        }
        LayoutInflater.from(context).inflate(R.layout.ksad_kwai_refresh_image_view, (ViewGroup) this, true);
        this.a = (ImageView) findViewById(R.id.pull_to_refresh_loading);
        post(new Runnable() { // from class: com.kwai.library.widget.refresh.KwaiRefreshView.1
            @Override // java.lang.Runnable
            public void run() {
                KwaiRefreshView.this.setPadding(0, 0, 0, 0);
            }
        });
    }

    private void g() {
        if (this.d == null) {
            this.a.setBackgroundResource(this.c);
            this.d = (AnimationDrawable) this.a.getBackground();
        }
    }

    @Override // com.kwai.library.widget.refresh.f
    public void a() {
        this.b = false;
        if (this.d == null || !this.d.isRunning()) {
            return;
        }
        this.d.stop();
    }

    @Override // com.kwai.library.widget.refresh.f
    public void a(float f, float f2) {
        if (this.b) {
            return;
        }
        g();
        if (this.d != null) {
            this.d.selectDrawable(Math.min(40, (int) (40.0f * f2)));
        }
        if (f2 > 0.5f || getAlpha() != 0.0f) {
            return;
        }
        setAlpha(1.0f);
    }

    @Override // com.kwai.library.widget.refresh.f
    public void b() {
        if (this.e != null) {
            g.a(this.e);
            this.e = null;
        }
        this.b = true;
        g();
        if (this.d != null) {
            this.d.start();
        }
        setAlpha(1.0f);
    }

    @Override // com.kwai.library.widget.refresh.f
    public void c() {
        this.e = new Runnable() { // from class: com.kwai.library.widget.refresh.KwaiRefreshView.2
            @Override // java.lang.Runnable
            public void run() {
                if (KwaiRefreshView.this.d != null) {
                    KwaiRefreshView.this.d.stop();
                }
                KwaiRefreshView.this.setAlpha(0.0f);
                KwaiRefreshView.this.b = false;
            }
        };
        g.a(this.e, 500L);
    }

    @Override // com.kwai.library.widget.refresh.f
    public void d() {
    }

    @Override // com.kwai.library.widget.refresh.f
    public void e() {
    }

    @Override // com.kwai.library.widget.refresh.f
    public int f() {
        return 500;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.i("KwaiRefreshView", "onFinishInflate()");
    }
}
