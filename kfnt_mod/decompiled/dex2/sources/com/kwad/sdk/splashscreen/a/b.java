package com.kwad.sdk.splashscreen.a;

import android.util.DisplayMetrics;
import android.view.ViewGroup;
import com.kwad.sdk.R;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.mvp.Presenter;
import com.kwad.sdk.widget.KsLogoView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends Presenter {
    protected com.kwad.sdk.splashscreen.g a;
    private KsLogoView b;

    private void a(ViewGroup viewGroup) {
        DisplayMetrics displayMetrics = viewGroup.getContext().getResources().getDisplayMetrics();
        if (displayMetrics.heightPixels / displayMetrics.widthPixels > 1.7777777910232544d) {
            ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                marginLayoutParams.leftMargin = com.kwad.sdk.a.kwai.a.a(viewGroup.getContext(), 12.0f);
                marginLayoutParams.topMargin = com.kwad.sdk.a.kwai.a.a(viewGroup.getContext(), 12.0f);
            }
        }
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void a() {
        AdInfo.AdSplashInfo adSplashInfo;
        super.a();
        this.a = (com.kwad.sdk.splashscreen.g) p();
        this.b = (KsLogoView) this.a.e.findViewById(R.id.ksad_splash_logo_container);
        a((ViewGroup) this.b);
        if (this.a.d.adInfoList.isEmpty() || (adSplashInfo = this.a.d.adInfoList.get(0).adSplashInfo) == null) {
            return;
        }
        if (adSplashInfo.logoPosition == 0) {
            this.b.setVisibility(8);
        } else {
            this.b.setVisibility(0);
            this.b.a(this.a.d);
        }
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
    }
}
