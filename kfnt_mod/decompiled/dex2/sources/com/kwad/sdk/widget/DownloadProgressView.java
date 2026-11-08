package com.kwad.sdk.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.api.KsAppDownloadListener;
import com.kwad.sdk.core.page.widget.TextProgressBar;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DownloadProgressView extends FrameLayout {
    protected TextProgressBar a;
    protected TextView b;
    protected AdTemplate c;
    protected AdInfo d;

    @ColorInt
    private int e;

    @ColorInt
    private int f;

    @ColorInt
    private int g;
    private int h;
    private Drawable i;
    private Drawable j;
    private String k;
    private final KsAppDownloadListener l;

    public DownloadProgressView(@NonNull Context context) {
        this(context, null);
    }

    public DownloadProgressView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DownloadProgressView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = new com.kwad.sdk.core.download.a.c() { // from class: com.kwad.sdk.widget.DownloadProgressView.2
            @Override // com.kwad.sdk.core.download.a.c
            public void a(int i2) {
                DownloadProgressView.this.b.setVisibility(8);
                DownloadProgressView.this.a.setVisibility(0);
                DownloadProgressView.this.a.a(com.kwad.sdk.core.response.a.a.a(), i2);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFailed() {
                DownloadProgressView.this.b.setText(com.kwad.sdk.core.response.a.a.A(DownloadProgressView.this.d));
                DownloadProgressView.this.b.setVisibility(0);
                DownloadProgressView.this.a.setVisibility(8);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFinished() {
                DownloadProgressView.this.b.setVisibility(8);
                DownloadProgressView.this.a.setVisibility(0);
                DownloadProgressView.this.a.a(com.kwad.sdk.core.response.a.a.a(DownloadProgressView.this.c), DownloadProgressView.this.a.getMax());
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onIdle() {
                DownloadProgressView.this.b.setText(com.kwad.sdk.core.response.a.a.A(DownloadProgressView.this.d));
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onInstalled() {
                DownloadProgressView.this.b.setVisibility(8);
                DownloadProgressView.this.a.setVisibility(0);
                DownloadProgressView.this.a.a(com.kwad.sdk.core.response.a.a.m(DownloadProgressView.this.d), DownloadProgressView.this.a.getMax());
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onProgressUpdate(int i2) {
                DownloadProgressView.this.b.setVisibility(8);
                DownloadProgressView.this.a.setVisibility(0);
                DownloadProgressView.this.a.a(com.kwad.sdk.core.response.a.a.a(i2, DownloadProgressView.this.k), i2);
            }
        };
        a(context, attributeSet, i);
        a(context);
    }

    protected void a(Context context) {
        inflate(context, R.layout.ksad_download_progress_layout, this);
        this.a = (TextProgressBar) findViewById(R.id.ksad_progress_bar);
        this.a.setTextDimen(this.h);
        this.a.a(this.f, this.g);
        this.a.setProgressDrawable(this.i);
        this.b = (TextView) findViewById(R.id.ksad_normal_text);
        this.b.setTextColor(this.e);
        this.b.setTextSize(0, this.h);
        this.b.setVisibility(0);
        this.b.setBackground(this.j);
        findViewById(R.id.ksad_foreground_cover).setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.widget.DownloadProgressView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DownloadProgressView.this.performClick();
            }
        });
    }

    @SuppressLint({"CustomViewStyleable"})
    protected void a(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ksad_DownloadProgressView);
        this.e = typedArrayObtainStyledAttributes.getColor(R.styleable.ksad_DownloadProgressView_ksad_downloadTextColor, -45056);
        this.f = typedArrayObtainStyledAttributes.getColor(R.styleable.ksad_DownloadProgressView_ksad_downloadLeftTextColor, -1);
        this.g = typedArrayObtainStyledAttributes.getColor(R.styleable.ksad_DownloadProgressView_ksad_downloadRightTextColor, -45056);
        this.h = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ksad_DownloadProgressView_ksad_downloadTextSize, com.kwad.sdk.a.kwai.a.a(getContext(), 11.0f));
        this.i = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ksad_DownloadProgressView_ksad_progressDrawable);
        if (this.i == null) {
            this.i = getResources().getDrawable(R.drawable.ksad_feed_download_progress);
        }
        this.j = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ksad_DownloadProgressView_ksad_backgroundDrawable);
        if (this.j == null) {
            this.j = getResources().getDrawable(R.drawable.ksad_feed_app_download_before_bg);
        }
        this.k = typedArrayObtainStyledAttributes.getString(R.styleable.ksad_DownloadProgressView_ksad_downloadingFormat);
        if (this.k == null) {
            this.k = "下载中  %s%%";
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.kwad.sdk.core.response.model.AdInfo r4, int r5, int r6) {
        /*
            r3 = this;
            if (r5 == 0) goto L6c
            r0 = 2
            r1 = 0
            r2 = 8
            if (r5 == r0) goto L56
            switch(r5) {
                case 7: goto L42;
                case 8: goto L29;
                default: goto Lb;
            }
        Lb:
            switch(r5) {
                case 11: goto L42;
                case 12: goto Lf;
                default: goto Le;
            }
        Le:
            goto L75
        Lf:
            android.widget.TextView r5 = r3.b
            r5.setVisibility(r2)
            com.kwad.sdk.core.page.widget.TextProgressBar r5 = r3.a
            r5.setVisibility(r1)
            com.kwad.sdk.core.page.widget.TextProgressBar r5 = r3.a
            java.lang.String r4 = com.kwad.sdk.core.response.a.a.m(r4)
            com.kwad.sdk.core.page.widget.TextProgressBar r6 = r3.a
            int r6 = r6.getMax()
            r5.a(r4, r6)
            goto L75
        L29:
            android.widget.TextView r4 = r3.b
            r4.setVisibility(r2)
            com.kwad.sdk.core.page.widget.TextProgressBar r4 = r3.a
            r4.setVisibility(r1)
            com.kwad.sdk.core.page.widget.TextProgressBar r4 = r3.a
            com.kwad.sdk.core.response.model.AdTemplate r5 = r3.c
            java.lang.String r5 = com.kwad.sdk.core.response.a.a.a(r5)
            com.kwad.sdk.core.page.widget.TextProgressBar r6 = r3.a
            int r6 = r6.getMax()
            goto L68
        L42:
            android.widget.TextView r5 = r3.b
            java.lang.String r4 = com.kwad.sdk.core.response.a.a.A(r4)
            r5.setText(r4)
            android.widget.TextView r4 = r3.b
            r4.setVisibility(r1)
            com.kwad.sdk.core.page.widget.TextProgressBar r4 = r3.a
            r4.setVisibility(r2)
            goto L75
        L56:
            android.widget.TextView r4 = r3.b
            r4.setVisibility(r2)
            com.kwad.sdk.core.page.widget.TextProgressBar r4 = r3.a
            r4.setVisibility(r1)
            com.kwad.sdk.core.page.widget.TextProgressBar r4 = r3.a
            java.lang.String r5 = r3.k
            java.lang.String r5 = com.kwad.sdk.core.response.a.a.a(r6, r5)
        L68:
            r4.a(r5, r6)
            goto L75
        L6c:
            android.widget.TextView r5 = r3.b
            java.lang.String r4 = com.kwad.sdk.core.response.a.a.A(r4)
            r5.setText(r4)
        L75:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.widget.DownloadProgressView.a(com.kwad.sdk.core.response.model.AdInfo, int, int):void");
    }

    public void a(AdTemplate adTemplate) {
        this.c = adTemplate;
        this.d = com.kwad.sdk.core.response.a.d.j(adTemplate);
        this.b.setText(com.kwad.sdk.core.response.a.a.A(this.d));
        this.a.setVisibility(8);
        this.b.setVisibility(0);
    }

    public KsAppDownloadListener getAppDownloadListener() {
        return this.l;
    }
}
