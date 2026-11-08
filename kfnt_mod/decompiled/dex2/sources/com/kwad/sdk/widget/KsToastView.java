package com.kwad.sdk.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KsToastView extends LinearLayout {
    TextView a;
    private int b;
    private String c;
    private Runnable d;

    public KsToastView(Context context) {
        super(context);
        this.b = 3;
        this.c = "%ss后自动进入";
        this.d = null;
        a(context);
    }

    public KsToastView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = 3;
        this.c = "%ss后自动进入";
        this.d = null;
        a(context);
    }

    public KsToastView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = 3;
        this.c = "%ss后自动进入";
        this.d = null;
        a(context);
    }

    public KsToastView(Context context, boolean z) {
        super(context);
        this.b = 3;
        this.c = "%ss后自动进入";
        this.d = null;
        a(context);
    }

    private void a(Context context) {
        inflate(context, R.layout.ksad_interstitial_toast_layout, this);
        this.a = (TextView) findViewById(R.id.ksad_total_count_down_text);
    }

    static /* synthetic */ int b(KsToastView ksToastView) {
        int i = ksToastView.b;
        ksToastView.b = i - 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        this.a.setText(String.format(this.c, Integer.valueOf(i)));
    }

    public void a(int i) {
        if (i <= 0) {
            return;
        }
        if (this.d == null) {
            this.d = new Runnable() { // from class: com.kwad.sdk.widget.KsToastView.1
                @Override // java.lang.Runnable
                public void run() {
                    if (KsToastView.this.b == 0) {
                        return;
                    }
                    KsToastView.this.b(KsToastView.this.b);
                    KsToastView.b(KsToastView.this);
                    KsToastView.this.postDelayed(this, 1000L);
                }
            };
        }
        this.b = i;
        post(this.d);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.d);
    }
}
