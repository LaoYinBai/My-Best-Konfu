package com.sigmob.sdk.nativead;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.sdk.base.models.SigImage;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class t implements s {
    private final String a;
    private final String b;
    private final String c;
    private final List<SigImage> d;
    private final int e;
    private final q f = new q();

    public t(Context context, BaseAdUnit baseAdUnit) {
        this.a = baseAdUnit.getTitle();
        this.b = baseAdUnit.getDesc();
        this.c = baseAdUnit.getIconUrl();
        this.d = baseAdUnit.getImageUrlList();
        this.e = baseAdUnit.getNativeAd().type.intValue();
        this.f.a(context, baseAdUnit, this);
    }

    @Override // com.sigmob.sdk.nativead.s
    public k a(Activity activity) {
        if (this.f != null) {
            return this.f.a(activity);
        }
        return null;
    }

    @Override // com.sigmob.sdk.nativead.s
    public String a() {
        return this.f != null ? this.f.w() : "";
    }

    @Override // com.sigmob.sdk.nativead.s
    public void a(ViewGroup viewGroup, g gVar) {
        if (this.f != null) {
            this.f.a(viewGroup, gVar);
        }
    }

    @Override // com.sigmob.sdk.nativead.s
    public void a(ViewGroup viewGroup, List<View> list, n nVar) {
        if (this.f != null) {
            this.f.a(viewGroup, list, nVar);
        }
    }

    @Override // com.sigmob.sdk.nativead.s
    public void a(List<ImageView> list, int i) {
        if (this.f != null) {
            this.f.a(list, i);
        }
    }

    @Override // com.sigmob.sdk.nativead.s
    public Bitmap b() {
        return this.f.x();
    }

    @Override // com.sigmob.sdk.nativead.s
    public String c() {
        return this.a;
    }

    @Override // com.sigmob.sdk.nativead.s
    public String d() {
        return this.b;
    }

    @Override // com.sigmob.sdk.nativead.s
    public String e() {
        return this.c;
    }

    @Override // com.sigmob.sdk.nativead.s
    public List<SigImage> f() {
        return this.d;
    }

    @Override // com.sigmob.sdk.nativead.s
    public int g() {
        return this.e;
    }

    @Override // com.sigmob.sdk.nativead.s
    public void h() {
        if (this.f != null) {
            this.f.t();
        }
    }

    @Override // com.sigmob.sdk.nativead.s
    public void i() {
        if (this.f != null) {
            this.f.y();
        }
    }

    @Override // com.sigmob.sdk.nativead.s
    public void j() {
        if (this.f != null) {
            this.f.z();
        }
    }

    @Override // com.sigmob.sdk.nativead.s
    public void k() {
        if (this.f != null) {
            this.f.B();
        }
    }

    @Override // com.sigmob.sdk.nativead.s
    public void l() {
        if (this.f != null) {
            this.f.C();
        }
    }
}
