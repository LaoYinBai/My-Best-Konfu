package com.kwad.sdk.d;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.kwad.sdk.api.KsAdVideoPlayConfig;
import com.kwad.sdk.api.KsInterstitialAd;
import com.kwad.sdk.api.KsVideoPlayConfig;
import com.kwad.sdk.api.loader.Wrapper;
import com.kwad.sdk.core.response.model.AdTemplate;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends AlertDialog {
    private com.kwad.sdk.d.a.a a;
    private AdTemplate b;

    @NonNull
    private KsAdVideoPlayConfig c;
    private Activity d;
    private KsInterstitialAd.AdInteractionListener e;

    public b(@NonNull Activity activity, @NonNull AdTemplate adTemplate, KsVideoPlayConfig ksVideoPlayConfig, KsInterstitialAd.AdInteractionListener adInteractionListener) {
        super(Wrapper.wrapContextIfNeed(activity));
        this.d = activity;
        this.e = adInteractionListener;
        this.c = new KsAdVideoPlayConfig.Builder().videoSoundEnable(ksVideoPlayConfig != null && ksVideoPlayConfig.isVideoSoundEnable()).dataFlowAutoStart(com.kwad.sdk.core.config.b.p()).build();
        setOwnerActivity(activity);
        this.b = adTemplate;
        if (this.a == null) {
            this.a = new com.kwad.sdk.d.a.a(Wrapper.wrapContextIfNeed(activity));
            this.a.a(this.b, this, this.c, adInteractionListener, ksVideoPlayConfig != null && ksVideoPlayConfig.isShowLandscape());
        }
    }

    public void a(KsInterstitialAd.AdInteractionListener adInteractionListener) {
        this.e = adInteractionListener;
        if (this.a != null) {
            this.a.setAdInteractionListener(adInteractionListener);
        }
    }

    public boolean a() {
        try {
            if (isShowing() || this.d == null || this.d.isFinishing()) {
                return true;
            }
            show();
            this.e.onAdShow();
            com.kwad.sdk.core.report.a.a(this.b, (JSONObject) null);
            return true;
        } catch (Throwable th) {
            com.kwad.sdk.core.d.a.a(th);
            return false;
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        if (this.e != null) {
            this.e.onPageDismiss();
        }
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        if (com.kwad.sdk.core.config.b.al()) {
            super.onBackPressed();
        }
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(this.a);
        setCanceledOnTouchOutside(false);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().setLayout(-1, -1);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        com.kwad.sdk.core.d.a.a("InterstitialDialog", "onDetachedFromWindow");
        if (this.a != null) {
            this.a.e();
        }
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        com.kwad.sdk.core.d.a.a("InterstitialDialog", "onStart");
        setTitle((CharSequence) null);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        com.kwad.sdk.core.d.a.a("InterstitialDialog", "onWindowFocusChanged , hasFocus: " + z);
        if (z) {
            this.a.c();
        } else {
            this.a.d();
        }
    }
}
