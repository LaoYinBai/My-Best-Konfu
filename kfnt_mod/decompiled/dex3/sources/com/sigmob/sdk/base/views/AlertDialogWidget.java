package com.sigmob.sdk.base.views;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AlertDialogWidget extends RelativeLayout {
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;
    private Button h;
    private Button i;
    private int j;
    private TextView k;
    private com.sigmob.sdk.videoAd.d l;

    public interface a {
        void a();

        void b();
    }

    public AlertDialogWidget(Context context, com.sigmob.sdk.videoAd.d dVar) {
        super(context);
        this.l = null;
        this.l = dVar;
        this.a = com.sigmob.sdk.common.utils.c.b(10.0f, context);
        this.b = com.sigmob.sdk.common.utils.c.b(3.0f, context);
        this.c = com.sigmob.sdk.common.utils.c.b(200.0f, context);
        this.f = (int) ((this.c * 16.0f) / 9.0d);
        this.d = com.sigmob.sdk.common.utils.c.b(50.0f, context);
        com.sigmob.sdk.common.utils.c.b(35.0f, context);
        this.e = com.sigmob.sdk.common.utils.c.b(100.0f, context);
        this.g = this.d + this.b;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        setBackgroundColor(Color.parseColor("#50000000"));
        setLayoutParams(layoutParams);
        e(context);
    }

    private TextView a(Context context) {
        TextView textView = new TextView(context);
        textView.setPadding(this.a * 3, this.a * 2, 0, 0);
        textView.setText(this.l.a());
        textView.setTextColor(c.a);
        textView.setTextSize((float) (this.a * 0.7d));
        return textView;
    }

    private TextView b(Context context) {
        this.k = new TextView(context);
        this.k.setPadding(this.a * 3, this.a * 2, 0, 0);
        this.k.setTextColor(c.b);
        this.k.setTextSize((float) (this.a * 0.55d));
        this.k.setMaxLines(2);
        return this.k;
    }

    private Button c(Context context) {
        Button button = new Button(context);
        button.setText(this.l.c());
        button.setTextColor(c.a);
        button.setTextSize((float) (this.a * 0.6d));
        button.setBackgroundColor(0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.e, this.d + this.a);
        layoutParams.setMargins(this.a * 6, 0, this.a, 0);
        button.setLayoutParams(layoutParams);
        return button;
    }

    private Button d(Context context) {
        Button button = new Button(context);
        button.setText(this.l.d());
        button.setLayoutParams(new LinearLayout.LayoutParams(this.e + this.a, this.d + this.a));
        button.setTextColor(-1);
        button.setBackgroundColor(0);
        button.setTextSize((float) (this.a * 0.6d));
        h.a(button, c.a, this.b, Color.parseColor("#66000000"), this.a, 0, 0);
        return button;
    }

    private void e(Context context) {
        RelativeLayout relativeLayout = new RelativeLayout(context);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.addView(a(context), new RelativeLayout.LayoutParams(-2, -2));
        linearLayout.addView(b(context), new RelativeLayout.LayoutParams(-2, -2));
        h.a(linearLayout, Color.parseColor("#FFFFFF"), this.b, Color.parseColor("#66000000"), this.a, 0, 0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.f, this.c);
        layoutParams.addRule(13);
        relativeLayout.setLayoutParams(layoutParams);
        relativeLayout.addView(linearLayout, new RelativeLayout.LayoutParams(-1, -1));
        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setOrientation(0);
        this.h = c(context);
        linearLayout2.addView(this.h);
        this.i = d(context);
        linearLayout2.addView(this.i);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, this.g);
        layoutParams2.setMargins(0, 0, 0, this.a * 2);
        layoutParams2.addRule(12);
        relativeLayout.addView(linearLayout2, layoutParams2);
        addView(relativeLayout);
    }

    public void setDialogListener(final a aVar) {
        if (this.h != null) {
            this.h.setOnClickListener(new View.OnClickListener() { // from class: com.sigmob.sdk.base.views.AlertDialogWidget.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    aVar.a();
                }
            });
        }
        if (this.i != null) {
            this.i.setOnClickListener(new View.OnClickListener() { // from class: com.sigmob.sdk.base.views.AlertDialogWidget.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    aVar.b();
                }
            });
        }
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
    }

    public void setduration(int i) {
        this.j = i;
        if (this.k != null) {
            this.k.setText(this.l.b().replace("_SEC_", String.valueOf(this.j)));
        }
    }
}
