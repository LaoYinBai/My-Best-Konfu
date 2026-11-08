package com.kwad.sdk.reward.kwai;

import android.view.View;
import android.view.ViewGroup;
import com.kwad.sdk.R;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdProductInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.reward.g;
import com.kwad.sdk.reward.kwai.b;
import com.kwad.sdk.utils.bb;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends g implements b.a {
    private AdTemplate b;
    private boolean c = false;

    private static boolean a(AdInfo adInfo) {
        AdProductInfo adProductInfoAK = com.kwad.sdk.core.response.a.a.aK(adInfo);
        return (!com.kwad.sdk.core.response.a.a.aI(adInfo) || adProductInfoAK == null || adProductInfoAK.isCouponListEmpty()) ? false : true;
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.b = this.a.g;
    }

    @Override // com.kwad.sdk.reward.kwai.b.a
    public void b() {
        this.a.a(q(), 29, 1);
    }

    public void e() {
        com.kwad.sdk.core.d.a.a("RewardCouponDialogPresenter", "onBind hasShown : " + this.c);
        if (this.c) {
            return;
        }
        AdInfo adInfoJ = d.j(this.b);
        ViewGroup viewGroup = (ViewGroup) this.a.j.findViewById(R.id.ksad_reward_order_coupon_list);
        if (viewGroup == null) {
            return;
        }
        int childCount = viewGroup.getChildCount();
        View childAt = viewGroup;
        if (childCount > 0) {
            childAt = viewGroup.getChildAt(0);
        }
        final int[] iArrC = bb.c(childAt);
        if (!a(adInfoJ) || iArrC == null) {
            return;
        }
        childAt.post(new Runnable() { // from class: com.kwad.sdk.reward.kwai.c.1
            @Override // java.lang.Runnable
            public void run() {
                com.kwad.sdk.core.d.a.a("RewardCouponDialogPresenter", "targetView x: " + iArrC[0] + ", y: " + iArrC[1]);
                b.a(c.this.o(), c.this.b, c.this, iArrC);
                c.this.c = true;
            }
        });
    }
}
