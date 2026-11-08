package com.sigmob.sdk.splash;

import android.content.Context;
import com.sigmob.sdk.base.common.t;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.common.models.sigdsp.pb.SplashAdSetting;
import com.sigmob.sdk.common.mta.PointCategory;
import com.sigmob.sdk.common.utils.m;
import com.sigmob.sdk.common.utils.q;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d extends com.sigmob.sdk.base.common.f {
    private int e = 3;
    private boolean f;
    private int g;
    private int h;

    public static d c(BaseAdUnit baseAdUnit) {
        d dVar = new d();
        dVar.b(baseAdUnit);
        return dVar;
    }

    @Override // com.sigmob.sdk.base.common.f
    public void a(Context context, int i, BaseAdUnit baseAdUnit) {
        m.a.a(context, "context cannot be null");
        t.b(PointCategory.START, null, baseAdUnit);
        com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_START);
    }

    @Override // com.sigmob.sdk.base.common.f
    public void a(Context context, q qVar, q qVar2, com.sigmob.sdk.base.a aVar, BaseAdUnit baseAdUnit) {
        m.a.a(context, "context cannot be null");
        com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_CLICK);
    }

    @Override // com.sigmob.sdk.base.common.f
    public void b(Context context, int i, BaseAdUnit baseAdUnit) {
        m.a.a(context, "context cannot be null");
        t.b("close", null, baseAdUnit);
        com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_CLOSE);
    }

    @Override // com.sigmob.sdk.base.common.f
    public void b(BaseAdUnit baseAdUnit) {
        super.b(baseAdUnit);
        SplashAdSetting splashAdSetting = baseAdUnit.getSplashAdSetting();
        if (splashAdSetting != null) {
            this.e = splashAdSetting.show_duration.intValue();
            this.f = splashAdSetting.enable_close_on_click.booleanValue();
            this.g = splashAdSetting.auto_click_mode.intValue();
            this.h = splashAdSetting.auto_click_time_ratio.intValue();
        }
    }

    @Override // com.sigmob.sdk.base.common.f
    public void c(Context context, int i, BaseAdUnit baseAdUnit) {
        m.a.a(context, "context cannot be null");
        t.b(PointCategory.SKIP, null, baseAdUnit);
        com.sigmob.sdk.base.a.c.a(baseAdUnit, com.sigmob.sdk.base.common.a.AD_SKIP);
    }

    public int d() {
        return this.e;
    }

    public boolean e() {
        return this.f;
    }

    public int f() {
        return this.g;
    }

    public int g() {
        return this.h;
    }
}
