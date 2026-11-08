package com.sigmob.sdk.splash;

import android.content.Context;
import android.widget.RelativeLayout;
import com.sigmob.sdk.base.common.j;
import com.sigmob.sdk.base.models.BaseAdUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class e extends RelativeLayout {
    protected int a;

    public e(Context context) {
        super(context);
    }

    public static e a(Context context, BaseAdUnit baseAdUnit) {
        if (baseAdUnit.getMaterial().creative_type == null) {
            return null;
        }
        return baseAdUnit.getMaterial().creative_type.intValue() == j.CreativeTypeSplashVideo.a() ? new h(context, baseAdUnit) : new SplashAdImageContentView(context);
    }

    public void a() {
        setVisibility(0);
    }

    public boolean a(BaseAdUnit baseAdUnit) {
        return false;
    }

    public void b() {
    }

    public void c() {
    }

    public int getDuration() {
        return this.a;
    }
}
