package com.kwad.sdk.reward.presenter;

import android.view.ViewGroup;
import com.kwad.sdk.R;
import com.kwad.sdk.contentalliance.detail.video.DetailVideoView;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.utils.af;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class l extends com.kwad.sdk.reward.g {
    private DetailVideoView b;
    private ViewGroup c;

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        DetailVideoView detailVideoView;
        int i;
        super.a();
        this.b = (DetailVideoView) a(R.id.ksad_video_player);
        this.c = (ViewGroup) a(R.id.ksad_activity_apk_info_area);
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.a.g);
        if (!(!af.e(q())) || (!f() && !g())) {
            this.c.setVisibility(8);
            return;
        }
        this.c.setVisibility(0);
        if (!com.kwad.sdk.core.response.a.a.M(adInfoJ)) {
            detailVideoView = this.b;
            i = 17;
        } else {
            detailVideoView = this.b;
            i = 21;
        }
        detailVideoView.a(i);
    }
}
