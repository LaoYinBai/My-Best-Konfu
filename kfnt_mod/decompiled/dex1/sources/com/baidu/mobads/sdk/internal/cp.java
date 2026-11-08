package com.baidu.mobads.sdk.internal;

import android.content.Context;
import com.baidu.mobads.sdk.api.FullScreenVideoAd;
import com.baidu.mobads.sdk.api.IAdInterListener;
import com.baidu.mobads.sdk.api.ScreenVideoAdListener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class cp extends cx {
    private FullScreenVideoAd.FullScreenVideoAdListener o;

    public cp(Context context, String str, boolean z) {
        super(context, str, z, IAdInterListener.AdProdType.PRODUCT_FULLSCREENVIDEO);
    }

    @Override // com.baidu.mobads.sdk.internal.cx
    public void a(ScreenVideoAdListener screenVideoAdListener) {
        super.a(screenVideoAdListener);
        if (screenVideoAdListener instanceof FullScreenVideoAd.FullScreenVideoAdListener) {
            this.o = (FullScreenVideoAd.FullScreenVideoAdListener) screenVideoAdListener;
        }
    }

    @Override // com.baidu.mobads.sdk.internal.cx, com.baidu.mobads.sdk.internal.ar
    protected void e(String str) {
        if (this.o != null) {
            this.o.onAdSkip(Float.parseFloat(str));
        }
    }
}
