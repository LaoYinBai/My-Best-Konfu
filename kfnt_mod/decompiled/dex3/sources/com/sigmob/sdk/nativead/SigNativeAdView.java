package com.sigmob.sdk.nativead;

import android.content.Context;
import com.sigmob.sdk.base.models.BaseAdUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SigNativeAdView extends SigAdView {
    r d;

    public SigNativeAdView(Context context) {
        super(context);
    }

    public void a(r rVar) {
        this.d = rVar;
    }

    @Override // com.sigmob.sdk.nativead.SigAdView
    public void c() {
        super.c();
        this.d = null;
    }

    public b getAdConfig() {
        if (this.d != null) {
            return this.d.u();
        }
        return null;
    }

    public BaseAdUnit getAdUnit() {
        if (this.d != null) {
            return this.d.k();
        }
        return null;
    }

    public SigAppInfoView getAppInfoView() {
        if (this.d != null) {
            return this.d.m();
        }
        return null;
    }

    public s getNativeAdUnit() {
        if (this.d != null) {
            return this.d.l();
        }
        return null;
    }

    public double getVideoDuration() {
        return 0.0d;
    }

    public double getVideoProgress() {
        return 0.0d;
    }

    public void setUIStyle(f fVar) {
        this.a = fVar;
    }
}
