package com.sigmob.sdk.nativead;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SigAppInfoView extends RelativeLayout {
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;

    public SigAppInfoView(Context context) {
        super(context);
        View.inflate(context, getLayoutId(), this);
        this.a = (TextView) findViewById(com.sigmob.sdk.common.utils.o.a(getContext(), "sig_app_info_ver"));
        this.b = (TextView) findViewById(com.sigmob.sdk.common.utils.o.a(getContext(), "sig_app_info_dev"));
        this.c = (TextView) findViewById(com.sigmob.sdk.common.utils.o.a(getContext(), "sig_app_info_privacy"));
        this.d = (TextView) findViewById(com.sigmob.sdk.common.utils.o.a(getContext(), "sig_app_info_permissions"));
        this.c.getPaint().setFlags(8);
        this.c.getPaint().setAntiAlias(true);
        this.d.getPaint().setFlags(8);
        this.d.getPaint().setAntiAlias(true);
    }

    private int getLayoutId() {
        return com.sigmob.sdk.common.utils.o.b(getContext(), "sig_app_info_layout");
    }

    public void a(String str, String str2) {
        this.a.setText(str);
        this.b.setText(str2);
    }
}
