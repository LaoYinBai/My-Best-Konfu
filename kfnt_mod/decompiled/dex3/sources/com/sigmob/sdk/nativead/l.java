package com.sigmob.sdk.nativead;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.ss.union.game.sdk.common.ui.roundedimageview.RoundedDrawable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class l extends com.sigmob.sdk.base.common.g {
    private RelativeLayout g;
    private LinearLayout h;
    private TextView i;
    private Activity j;
    private BaseAdUnit k;
    private String l;

    public l(Activity activity, BaseAdUnit baseAdUnit, Bundle bundle, Bundle bundle2, String str, com.sigmob.sdk.base.common.h hVar) {
        super(activity, str, hVar);
        this.l = "此广告由SigMob提供，为了在应用程序上向您推荐展示出更加个性和实用的广告，对您可能会接收到的一部分广告进行更具相关性的定制，从而使您在应用程序上有更好的用户体验。SigMob非常重视数据安全，将努力采取合理的安全措施（包括技术方面和管理方面）来保护数据安全，防止数据信息被不正当使用或未经授权的情况下被访问。";
        this.j = activity;
        this.k = baseAdUnit;
    }

    @Override // com.sigmob.sdk.base.common.g
    public void a(Configuration configuration) {
    }

    @Override // com.sigmob.sdk.base.common.g
    public void a(Bundle bundle) {
    }

    @Override // com.sigmob.sdk.base.common.g
    public void b() {
        this.e.removeAllViews();
        this.e.setBackgroundColor(-1);
        this.f.onSetContentView(this.e);
        LinearLayout linearLayout = new LinearLayout(k());
        linearLayout.setOrientation(1);
        this.e.addView(linearLayout, new RelativeLayout.LayoutParams(-1, -1));
        this.g = new RelativeLayout(k());
        linearLayout.addView(this.g, new RelativeLayout.LayoutParams(-1, com.sigmob.sdk.common.utils.c.b(50.0f, k())));
        View view = new View(k());
        view.setBackgroundColor(Color.parseColor("#E0E6EC"));
        linearLayout.addView(view, new RelativeLayout.LayoutParams(-1, com.sigmob.sdk.common.utils.c.b(1.0f, k())));
        int iB = com.sigmob.sdk.common.utils.c.b(10.0f, k());
        ImageView imageView = new ImageView(this.j);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sigmob.sdk.nativead.l.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                l.this.k().finish();
            }
        });
        imageView.setImageResource(com.sigmob.sdk.common.utils.o.c(this.j, "sig_image_video_back_left_black"));
        int i = iB * 2;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i);
        layoutParams.setMargins(iB, 0, iB, 0);
        layoutParams.addRule(9);
        layoutParams.addRule(15);
        this.g.addView(imageView, layoutParams);
        TextView textView = new TextView(this.j);
        textView.setText("为什么看到此广告");
        textView.setTextColor(RoundedDrawable.DEFAULT_BORDER_COLOR);
        textView.setTextSize(1, 20.0f);
        textView.setGravity(17);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13);
        this.g.addView(textView, layoutParams2);
        this.h = new LinearLayout(k());
        linearLayout.addView(this.h, new RelativeLayout.LayoutParams(-1, -1));
        this.i = new TextView(this.j);
        this.i.setText(this.l);
        this.i.setTextColor(RoundedDrawable.DEFAULT_BORDER_COLOR);
        this.i.setTextSize(1, 15.0f);
        this.i.setLineSpacing(2.0f, 1.2f);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(13);
        int i2 = iB / 2;
        layoutParams3.setMargins(i2, i2, i2, i2);
        this.h.addView(this.i, layoutParams3);
    }

    @Override // com.sigmob.sdk.base.common.g
    public void c() {
    }

    @Override // com.sigmob.sdk.base.common.g
    public void d() {
    }

    @Override // com.sigmob.sdk.base.common.g
    public void e() {
    }

    @Override // com.sigmob.sdk.base.common.g
    public void f() {
    }

    @Override // com.sigmob.sdk.base.common.g
    public boolean g() {
        k().finish();
        return false;
    }
}
