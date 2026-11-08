package com.kwad.sdk.d;

import android.app.Activity;
import androidx.annotation.NonNull;
import com.kwad.sdk.api.KsInterstitialAd;
import com.kwad.sdk.api.KsScene;
import com.kwad.sdk.api.KsVideoPlayConfig;
import com.kwad.sdk.api.model.AdExposureFailedReason;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.plugin.DevelopMangerPlugin;
import com.kwad.sdk.plugin.f;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements KsInterstitialAd {
    private KsScene a;

    @NonNull
    private final AdTemplate b;
    private KsInterstitialAd.AdInteractionListener c;
    private b d;

    public a(@NonNull KsScene ksScene, @NonNull AdTemplate adTemplate) {
        this.a = ksScene;
        this.b = adTemplate;
    }

    @Override // com.kwad.sdk.api.KsInterstitialAd
    public int getECPM() {
        return com.kwad.sdk.core.response.a.a.F(d.j(this.b));
    }

    @Override // com.kwad.sdk.api.KsInterstitialAd
    public int getInteractionType() {
        return com.kwad.sdk.core.response.a.a.E(d.j(this.b));
    }

    @Override // com.kwad.sdk.api.KsInterstitialAd
    public int getMaterialType() {
        return com.kwad.sdk.core.response.a.a.R(d.j(this.b));
    }

    @Override // com.kwad.sdk.api.KsInterstitialAd
    public boolean isVideo() {
        return com.kwad.sdk.core.response.a.a.P(d.j(this.b));
    }

    @Override // com.kwad.sdk.api.KsInterstitialAd
    public void reportAdExposureFailed(int i, AdExposureFailedReason adExposureFailedReason) {
        com.kwad.sdk.core.report.a.a(this.b, i, adExposureFailedReason);
    }

    @Override // com.kwad.sdk.api.KsInterstitialAd
    public void setAdInteractionListener(@NonNull KsInterstitialAd.AdInteractionListener adInteractionListener) {
        this.c = adInteractionListener;
        if (this.d != null) {
            this.d.a(adInteractionListener);
        }
    }

    @Override // com.kwad.sdk.api.KsInterstitialAd
    public void setBidEcpm(int i) {
        this.b.mBidEcpm = i;
        com.kwad.sdk.core.report.a.m(this.b);
    }

    @Override // com.kwad.sdk.api.KsInterstitialAd
    public void showInterstitialAd(Activity activity, KsVideoPlayConfig ksVideoPlayConfig) {
        AdTemplate adTemplate;
        int i;
        DevelopMangerPlugin.DevelopValue developValueA;
        if (activity == null) {
            com.kwad.sdk.core.d.a.e("StayAdHelper", "showInterstitialAd activity must not be null");
            return;
        }
        DevelopMangerPlugin developMangerPlugin = (DevelopMangerPlugin) f.a(DevelopMangerPlugin.class);
        if (developMangerPlugin != null && (developValueA = developMangerPlugin.a("KEY_INIT_VOICE_STATUS")) != null) {
            ksVideoPlayConfig.setVideoSoundEnable(((Boolean) developValueA.getValue()).booleanValue());
        }
        if (ksVideoPlayConfig.isVideoSoundEnable()) {
            adTemplate = this.b;
            i = 2;
        } else {
            adTemplate = this.b;
            i = 1;
        }
        adTemplate.mInitVoiceStatus = i;
        if (this.d == null) {
            this.d = new b(activity, this.b, ksVideoPlayConfig, this.c);
            this.d.a();
        }
    }
}
