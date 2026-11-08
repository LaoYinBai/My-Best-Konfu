package com.kwad.sdk.fullscreen;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.kwad.sdk.api.KsFullScreenVideoAd;
import com.kwad.sdk.api.KsVideoPlayConfig;
import com.kwad.sdk.api.model.AdExposureFailedReason;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.plugin.DevelopMangerPlugin;
import com.kwad.sdk.plugin.f;
import com.kwad.sdk.reward.n;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b implements KsFullScreenVideoAd {

    @NonNull
    private AdTemplate a;
    private AdInfo b;
    private KsFullScreenVideoAd.FullScreenVideoAdInteractionListener c;

    public b(@NonNull AdTemplate adTemplate) {
        this.a = adTemplate;
        this.b = d.j(adTemplate);
    }

    private void a(Context context, KsVideoPlayConfig ksVideoPlayConfig) {
        if (!isAdEnable()) {
            com.kwad.sdk.core.d.a.c("KsFullScreenVideoAdControl", "isAdEnable is false");
            return;
        }
        if (ksVideoPlayConfig == null) {
            ksVideoPlayConfig = new KsVideoPlayConfig.Builder().build();
        }
        DevelopMangerPlugin.DevelopValue developValueA = ((DevelopMangerPlugin) f.a(DevelopMangerPlugin.class)).a("KEY_INIT_VOICE_STATUS");
        if (developValueA != null) {
            ksVideoPlayConfig.setVideoSoundEnable(((Boolean) developValueA.getValue()).booleanValue());
        }
        KsFullScreenVideoActivityProxy.launch(context, this.a, ksVideoPlayConfig, this.c);
    }

    @Override // com.kwad.sdk.api.KsFullScreenVideoAd
    public int getECPM() {
        return com.kwad.sdk.core.response.a.a.F(this.b);
    }

    @Override // com.kwad.sdk.api.KsFullScreenVideoAd
    public int getInteractionType() {
        return com.kwad.sdk.core.response.a.a.E(this.b);
    }

    @Override // com.kwad.sdk.api.KsFullScreenVideoAd
    public int getMaterialType() {
        return com.kwad.sdk.core.response.a.a.R(this.b);
    }

    @Override // com.kwad.sdk.api.KsFullScreenVideoAd
    public boolean isAdEnable() {
        if (com.kwad.sdk.core.config.b.C() >= 0) {
            return true;
        }
        return n.b(this.a);
    }

    @Override // com.kwad.sdk.api.KsFullScreenVideoAd
    public void reportAdExposureFailed(int i, AdExposureFailedReason adExposureFailedReason) {
        com.kwad.sdk.core.report.a.a(this.a, i, adExposureFailedReason);
    }

    @Override // com.kwad.sdk.api.KsFullScreenVideoAd
    public void setBidEcpm(int i) {
        this.a.mBidEcpm = i;
        com.kwad.sdk.core.report.a.m(this.a);
    }

    @Override // com.kwad.sdk.api.KsFullScreenVideoAd
    public void setFullScreenVideoAdInteractionListener(KsFullScreenVideoAd.FullScreenVideoAdInteractionListener fullScreenVideoAdInteractionListener) {
        this.c = fullScreenVideoAdInteractionListener;
    }

    @Override // com.kwad.sdk.api.KsFullScreenVideoAd
    public void showFullScreenVideoAd(Activity activity, KsVideoPlayConfig ksVideoPlayConfig) {
        a(activity, ksVideoPlayConfig);
    }
}
