package com.sigmob.sdk.a;

import android.content.Context;
import com.sigmob.sdk.base.common.f;
import com.sigmob.sdk.base.common.t;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.common.mta.PointCategory;
import com.sigmob.sdk.common.utils.m;
import com.sigmob.sdk.common.utils.q;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c extends f {
    public static c c(BaseAdUnit baseAdUnit) {
        c cVar = new c();
        cVar.b(baseAdUnit);
        return cVar;
    }

    @Override // com.sigmob.sdk.base.common.f
    public void a(Context context, q qVar, q qVar2, com.sigmob.sdk.base.a aVar, BaseAdUnit baseAdUnit) {
        m.a.a(context, "context cannot be null");
        t.a(PointCategory.DRIFT, "click", baseAdUnit);
        com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_CLICK);
    }

    @Override // com.sigmob.sdk.base.common.f
    public void b(Context context, int i, BaseAdUnit baseAdUnit) {
        m.a.a(context, "context cannot be null");
        t.a("close", (String) null, baseAdUnit);
    }

    @Override // com.sigmob.sdk.base.common.f
    public void b(BaseAdUnit baseAdUnit) {
        super.b(baseAdUnit);
    }
}
