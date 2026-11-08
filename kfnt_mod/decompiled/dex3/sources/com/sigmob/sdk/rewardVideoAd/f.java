package com.sigmob.sdk.rewardVideoAd;

import android.content.Context;
import android.os.Bundle;
import com.sigmob.sdk.base.common.AdActivity;
import com.sigmob.sdk.base.common.aa;
import com.sigmob.sdk.base.common.j;
import com.sigmob.sdk.base.common.k;
import com.sigmob.sdk.base.models.BaseAdUnit;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class f extends aa {
    private RewardVideoAdBroadcastReceiver e;

    interface a extends k.b {
        void b(BaseAdUnit baseAdUnit, String str);

        void f(BaseAdUnit baseAdUnit);

        void g(BaseAdUnit baseAdUnit);

        void h(BaseAdUnit baseAdUnit);

        void i(BaseAdUnit baseAdUnit);
    }

    protected f(k.b bVar) {
        super(bVar);
    }

    @Override // com.sigmob.sdk.base.common.aa, com.sigmob.sdk.base.common.r
    public void a(Context context, BaseAdUnit baseAdUnit, Bundle bundle) {
        super.a(context, baseAdUnit, bundle);
        if (this.b instanceof a) {
            this.e = new RewardVideoAdBroadcastReceiver(baseAdUnit, (a) this.b, this.a);
            this.e.a(this.e, context);
            this.b.c(baseAdUnit);
        }
        String str = "reward";
        if ((baseAdUnit.getAd_type() == 1 || baseAdUnit.getAd_type() == 4) && baseAdUnit.getCreativeType() == j.CreativeTypeMRAID.a()) {
            str = "mraid";
        }
        AdActivity.a(context, AdActivity.class, this.a, bundle, str);
    }

    @Override // com.sigmob.sdk.mraid.d, com.sigmob.sdk.base.common.r
    public void a(Context context, Map<String, Object> map, BaseAdUnit baseAdUnit) {
        super.a(context, map, baseAdUnit);
    }

    @Override // com.sigmob.sdk.base.common.aa, com.sigmob.sdk.mraid.d, com.sigmob.sdk.base.common.r
    public void c(BaseAdUnit baseAdUnit) {
        super.c(baseAdUnit);
        if (this.e != null) {
            this.e.a(this.e);
        }
    }
}
