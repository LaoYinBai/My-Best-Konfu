package com.bytedance.msdk.api.v2.ad.custom.base;

import android.app.Activity;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class GMCustomVideoAdapter extends GMCustomBaseAdapter implements IGMCustomLoadAdCall {
    public abstract boolean isReady();

    @Override // com.bytedance.msdk.api.v2.ad.custom.base.GMCustomBaseAdapter
    public final boolean isReadyInner() {
        try {
            return isReady();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public abstract void showAd(Activity activity);

    public final void showAdInner(Activity activity) {
        try {
            this.f4371a = true;
            showAd(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
