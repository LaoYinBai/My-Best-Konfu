package com.kwad.sdk.reward.d;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kwad.sdk.R;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.response.model.AdProductInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.response.model.CouponInfo;
import com.kwad.sdk.utils.af;
import com.kwad.sdk.utils.at;
import com.kwad.sdk.utils.w;
import com.kwad.sdk.widget.KSCornerImageView;
import com.kwad.sdk.widget.KSCouponLabelTextView;
import com.kwad.sdk.widget.KsPriceView;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g extends d implements View.OnClickListener {
    private ViewGroup a;
    private ViewGroup b;
    private KSCornerImageView c;
    private TextView d;
    private LinearLayout e;
    private KsPriceView f;
    private TextView g;
    private View h;
    private KSCornerImageView i;
    private b j;
    private a k;

    public interface a {
        void a();
    }

    public g(ViewGroup viewGroup, b bVar) {
        this.a = viewGroup;
        this.j = bVar;
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View a(Context context, CouponInfo couponInfo, ViewGroup viewGroup) {
        KSCouponLabelTextView kSCouponLabelTextView = (KSCouponLabelTextView) LayoutInflater.from(context).inflate(R.layout.ksad_reward_order_card_coupon, viewGroup, false);
        kSCouponLabelTextView.setText(couponInfo.getDisplayName());
        return kSCouponLabelTextView;
    }

    private void a(final AdProductInfo adProductInfo, AdTemplate adTemplate) {
        KSImageLoader.loadImage(this.c, adProductInfo.getIcon(), adTemplate);
        this.d.setText(adProductInfo.getName());
        if (adProductInfo.isCouponListEmpty()) {
            this.e.setVisibility(8);
            this.k.a();
        } else {
            this.e.setVisibility(0);
            this.e.post(new Runnable() { // from class: com.kwad.sdk.reward.d.g.1
                @Override // java.lang.Runnable
                public void run() {
                    Iterator<CouponInfo> it = adProductInfo.getCouponList().iterator();
                    while (it.hasNext()) {
                        View viewA = g.this.a(g.this.e.getContext(), it.next(), g.this.e);
                        ViewGroup.LayoutParams layoutParams = viewA.getLayoutParams();
                        if (layoutParams == null) {
                            layoutParams = new ViewGroup.LayoutParams(-2, -1);
                        } else {
                            layoutParams.width = -2;
                            layoutParams.height = -1;
                        }
                        g.this.e.addView(viewA, layoutParams);
                        w.a(new com.kwad.sdk.widget.c(com.kwad.sdk.core.config.b.aL()), g.this.e);
                    }
                    if (g.this.k != null) {
                        if (g.this.e.getChildCount() > 0) {
                            g.this.e.getChildAt(0).post(new Runnable() { // from class: com.kwad.sdk.reward.d.g.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    g.this.k.a();
                                }
                            });
                        } else {
                            g.this.k.a();
                        }
                    }
                }
            });
        }
        this.f.a(adProductInfo.getPrice(), adProductInfo.getOriginPrice());
        String strAF = com.kwad.sdk.core.config.b.aF();
        if (at.a(strAF)) {
            return;
        }
        KSImageLoader.loadImage(this.i, strAF, adTemplate);
    }

    private void b() {
        this.b = (ViewGroup) this.a.findViewById(R.id.ksad_reward_order_root);
        this.c = (KSCornerImageView) this.a.findViewById(R.id.ksad_reward_order_icon);
        this.d = (TextView) this.a.findViewById(R.id.ksad_reward_order_title);
        this.e = (LinearLayout) this.a.findViewById(R.id.ksad_reward_order_coupon_list);
        this.f = (KsPriceView) this.a.findViewById(R.id.ksad_reward_order_price);
        this.g = (TextView) this.a.findViewById(R.id.ksad_reward_order_btn_buy);
        this.h = this.a.findViewById(R.id.ksad_reward_order_text_area);
        this.i = (KSCornerImageView) this.a.findViewById(R.id.ksad_reward_order_kwai_logo);
        this.g.setText(com.kwad.sdk.core.config.b.aE());
        this.g.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.h.setOnClickListener(this);
        Context context = this.a.getContext();
        if (af.e(context)) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            layoutParams2.width = context.getResources().getDimensionPixelSize(R.dimen.ksad_reward_follow_card_width_horizontal);
            this.a.setLayoutParams(layoutParams2);
        }
    }

    @Override // com.kwad.sdk.reward.d.d
    public ViewGroup a() {
        return this.b;
    }

    @Override // com.kwad.sdk.reward.d.d
    public void a(AdTemplate adTemplate) {
        super.a(adTemplate);
        a(com.kwad.sdk.core.response.a.a.aK(com.kwad.sdk.core.response.a.d.j(adTemplate)), adTemplate);
    }

    public void a(a aVar) {
        this.k = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.j == null) {
            return;
        }
        if (view.equals(this.g)) {
            this.j.c();
        } else if (view.equals(this.c)) {
            this.j.a();
        } else if (view.equals(this.h)) {
            this.j.b();
        }
    }
}
