package com.baidu.mobads.sdk.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.baidu.mobads.sdk.internal.ak;
import com.baidu.mobads.sdk.internal.bb;
import com.baidu.mobads.sdk.internal.bz;
import com.baidu.mobads.sdk.internal.ca;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class AdView extends RelativeLayout {
    private static final boolean AUTOPLAY = true;
    private static final float HEIGHT_FACTOR = 0.15f;
    protected static final String P_VERSION = "3.61";
    private AtomicBoolean hasCalledRequestMethod;
    private ca mAdProd;

    public AdView(Context context) {
        super(context);
        this.hasCalledRequestMethod = new AtomicBoolean(false);
    }

    public AdView(Context context, String str) {
        this(context, true, AdSize.Banner, str);
    }

    public AdView(Context context, AdSize adSize, String str) {
        this(context, true, adSize, str);
    }

    AdView(Context context, boolean z, AdSize adSize, String str) {
        this(context, null, z, adSize, str);
    }

    public AdView(Context context, AttributeSet attributeSet, boolean z, AdSize adSize, String str) {
        super(context, attributeSet);
        this.hasCalledRequestMethod = new AtomicBoolean(false);
        bz bzVar = new bz(context);
        this.mAdProd = new ca(this, context, bzVar, str, z);
        bzVar.a(new bz.a() { // from class: com.baidu.mobads.sdk.api.AdView.1
            @Override // com.baidu.mobads.sdk.internal.bz.a
            public void onWindowVisibilityChanged(int i) {
                AdView.this.mAdProd.b(i);
            }

            @Override // com.baidu.mobads.sdk.internal.bz.a
            public void onWindowFocusChanged(boolean z2) {
                AdView.this.mAdProd.a(z2);
            }

            @Override // com.baidu.mobads.sdk.internal.bz.a
            public void onLayoutComplete(int i, int i2) throws JSONException {
                AdView.this.callRequest();
            }

            @Override // com.baidu.mobads.sdk.internal.bz.a
            @SuppressLint({"MissingSuperCall"})
            public void onDetachedFromWindow() {
                AdView.this.mAdProd.p();
            }

            @Override // com.baidu.mobads.sdk.internal.bz.a
            public void onAttachedToWindow() throws JSONException {
                AdView.this.callRequest();
                AdView.this.mAdProd.o();
            }

            @Override // com.baidu.mobads.sdk.internal.bz.a
            public boolean onKeyDown(int i, KeyEvent keyEvent) {
                return AdView.this.mAdProd.a(i, keyEvent);
            }
        });
        addView(bzVar, new ViewGroup.LayoutParams(-1, -1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callRequest() throws JSONException {
        if (this.hasCalledRequestMethod.get()) {
            return;
        }
        this.hasCalledRequestMethod.set(true);
        this.mAdProd.b_();
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        bb.a().a("AdView.setLayoutParams=", Integer.valueOf(layoutParams.width), Integer.valueOf(layoutParams.height), Integer.valueOf(getWidth()), Integer.valueOf(getHeight()));
        int iMin = layoutParams.width;
        int iMin2 = layoutParams.height;
        DisplayMetrics displayMetricsF = ak.f(getContext());
        int i = displayMetricsF.widthPixels;
        int i2 = displayMetricsF.heightPixels;
        float f = displayMetricsF.density;
        bb.a().a("AdView.setLayoutParams", Integer.valueOf(i), Integer.valueOf(i2), Float.valueOf(f));
        if (iMin <= 0) {
            iMin = Math.min(i, i2);
        } else if (iMin > 0) {
            float f2 = 200.0f * f;
            if (iMin < f2) {
                iMin = (int) f2;
            }
        }
        if (iMin2 <= 0) {
            iMin2 = (int) (Math.min(i, i2) * HEIGHT_FACTOR);
        } else if (iMin2 > 0) {
            float f3 = f * 30.0f;
            if (iMin2 < f3) {
                iMin2 = (int) f3;
            }
        }
        layoutParams.width = iMin;
        layoutParams.height = iMin2;
        if (this.mAdProd != null) {
            this.mAdProd.a(iMin);
            this.mAdProd.c(iMin2);
        }
        bb.a().a("AdView.setLayoutParams adapter", Integer.valueOf(layoutParams.width), Integer.valueOf(layoutParams.height));
        super.setLayoutParams(layoutParams);
    }

    public void setListener(AdViewListener adViewListener) {
        if (this.mAdProd != null) {
            this.mAdProd.a(adViewListener);
        }
    }

    public void destroy() {
        if (this.mAdProd != null) {
            this.mAdProd.d();
        }
    }

    public void setAppSid(String str) {
        if (this.mAdProd != null) {
            this.mAdProd.f(str);
        }
    }
}
