package com.kwad.sdk.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.kwad.sdk.R;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KsAutoCloseView extends LinearLayout implements View.OnClickListener {
    private static String b = "%s秒后自动关闭";
    private int a;
    private TextView c;
    private ImageView d;
    private a e;
    private boolean f;
    private boolean g;

    public interface a {
        void a();

        void b();
    }

    public KsAutoCloseView(Context context) {
        super(context);
        this.a = 10;
        this.f = true;
        this.g = false;
        a(context, null, 0);
    }

    public KsAutoCloseView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 10;
        this.f = true;
        this.g = false;
        a(context, attributeSet, 0);
    }

    public KsAutoCloseView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 10;
        this.f = true;
        this.g = false;
        a(context, attributeSet, i);
    }

    @RequiresApi(api = 21)
    public KsAutoCloseView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.a = 10;
        this.f = true;
        this.g = false;
        a(context, attributeSet, i);
    }

    private void a(Context context, @Nullable AttributeSet attributeSet, int i) {
        inflate(context, R.layout.ksad_interstitial_auto_close, this);
        this.c = (TextView) findViewById(R.id.ksad_auto_close_text);
        this.d = (ImageView) findViewById(R.id.ksad_auto_close_btn);
        this.d.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        this.c.setText(String.format(b, Integer.valueOf(i)));
    }

    static /* synthetic */ int e(KsAutoCloseView ksAutoCloseView) {
        int i = ksAutoCloseView.a;
        ksAutoCloseView.a = i - 1;
        return i;
    }

    public void a(int i) {
        if (i <= 0) {
            return;
        }
        this.a = i;
        post(new Runnable() { // from class: com.kwad.sdk.widget.KsAutoCloseView.1
            @Override // java.lang.Runnable
            public void run() {
                if (KsAutoCloseView.this.f) {
                    if (!KsAutoCloseView.this.g) {
                        if (KsAutoCloseView.this.a == 0) {
                            if (KsAutoCloseView.this.e != null) {
                                KsAutoCloseView.this.e.a();
                                return;
                            }
                            return;
                        }
                        KsAutoCloseView.this.b(KsAutoCloseView.this.a);
                        KsAutoCloseView.e(KsAutoCloseView.this);
                    }
                    KsAutoCloseView.this.postDelayed(this, 1000L);
                }
            }
        });
    }

    public void a(boolean z) {
        this.f = z;
        int i = this.f ? 0 : 8;
        if (this.c != null) {
            this.c.setVisibility(i);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.e != null && view.equals(this.d)) {
            this.e.b();
        }
    }

    public void setCountDownPaused(boolean z) {
        this.g = z;
    }

    public void setViewListener(a aVar) {
        this.e = aVar;
    }
}
