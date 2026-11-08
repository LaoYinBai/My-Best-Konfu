package com.sigmob.sdk.nativead;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sigmob.sdk.base.views.CircleImageView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SigAppView extends RelativeLayout {
    private TextView a;
    private TextView b;
    private ViewGroup c;
    private CircleImageView d;

    public SigAppView(Context context) {
        super(context);
        View.inflate(context, getLayoutId(), this);
        this.a = (TextView) findViewById(com.sigmob.sdk.common.utils.o.a(getContext(), "sig_app_name"));
        this.b = (TextView) findViewById(com.sigmob.sdk.common.utils.o.a(getContext(), "sig_app_cta"));
        this.c = (ViewGroup) findViewById(com.sigmob.sdk.common.utils.o.a(getContext(), "sig_app_icon"));
        this.d = new CircleImageView(context);
        this.d.setCircle(false);
        this.d.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.c.addView(this.d, new ViewGroup.LayoutParams(-1, -1));
    }

    private int getLayoutId() {
        return com.sigmob.sdk.common.utils.o.b(getContext(), "sig_app_layout");
    }

    public void a(String str, String str2, String str3) {
        this.a.setText(str2);
        this.b.setText(str3);
        com.sigmob.sdk.common.utils.g.a(getContext()).a(str).a(this.d);
    }

    public View getCtaView() {
        return this.b;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.b.setOnClickListener(onClickListener);
    }
}
