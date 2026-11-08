package com.kwad.sdk.d.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Message;
import android.view.View;
import androidx.annotation.NonNull;
import com.kwad.sdk.R;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.bb;
import com.kwad.sdk.utils.bc;
import java.util.concurrent.atomic.AtomicBoolean;

@SuppressLint({"ViewConstructor"})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends com.kwad.sdk.core.video.videoview.a implements bc.a {
    private View e;
    private final bc f;
    private final AtomicBoolean g;
    private boolean h;

    public c(Context context, AdTemplate adTemplate, @NonNull com.kwad.sdk.core.video.videoview.d dVar) {
        super(context, adTemplate, dVar);
        this.f = new bc(this);
        this.g = new AtomicBoolean(true);
        this.h = false;
        this.e = this;
    }

    private void n() {
        this.h = false;
    }

    private void o() {
        if (this.g.getAndSet(false)) {
            com.kwad.sdk.core.d.a.c("InterstitialVideoPlayerController", "onViewAttached");
            this.f.sendEmptyMessage(1);
        }
    }

    @Override // com.kwad.sdk.core.video.videoview.a
    protected void a() {
        super.a();
        if (this.b != null) {
            this.b.setImageResource(R.drawable.ksad_interstitial_video_play);
        }
        if (this.c != null) {
            this.c.setVisibility(8);
        }
    }

    @Override // com.kwad.sdk.utils.bc.a
    public void a(Message message) {
        if (!this.h && message.what == 1) {
            if (bb.a(this.e, 30)) {
                f();
            } else {
                g();
            }
            this.f.sendEmptyMessageDelayed(1, 500L);
        }
    }

    @Override // com.kwad.sdk.core.video.videoview.a
    protected void b() {
    }

    @Override // com.kwad.sdk.core.video.videoview.a
    public void h() {
        super.h();
        this.h = true;
    }

    protected void m() {
        if (this.g.getAndSet(true)) {
            return;
        }
        com.kwad.sdk.core.d.a.c("InterstitialVideoPlayerController", "onViewDetached");
        this.f.removeCallbacksAndMessages(null);
        h();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        com.kwad.sdk.core.d.a.c("InterstitialVideoPlayerController", "onAttachedToWindow");
        n();
        o();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        com.kwad.sdk.core.d.a.c("InterstitialVideoPlayerController", "onDetachedFromWindow");
        m();
    }

    @Override // android.view.View
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        com.kwad.sdk.core.d.a.c("InterstitialVideoPlayerController", "onFinishTemporaryDetach");
        o();
    }

    @Override // android.view.View
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        com.kwad.sdk.core.d.a.c("InterstitialVideoPlayerController", "onStartTemporaryDetach");
        m();
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
    }
}
