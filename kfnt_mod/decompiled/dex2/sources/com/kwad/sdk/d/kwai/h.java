package com.kwad.sdk.d.kwai;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.kwad.sdk.R;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h extends b {
    private static int g = 4;
    private Context a;
    private c b;
    private AdInfo c;
    private AdTemplate d;
    private a e = new a();
    private com.kwad.sdk.core.download.a.b f;

    static class a {
        private ImageView a;
        private TextView b;
        private TextView c;

        a() {
        }
    }

    private static void a(Context context, a aVar, AdInfo adInfo, AdTemplate adTemplate) {
        ImageView imageView = aVar.a;
        String strAu = com.kwad.sdk.core.response.a.a.au(adInfo);
        if (TextUtils.isEmpty(strAu)) {
            imageView.setVisibility(8);
        } else {
            imageView.setVisibility(0);
            imageView.setImageResource(R.drawable.ksad_default_app_icon);
            KSImageLoader.loadWithRadius(imageView, strAu, adTemplate, g);
        }
        TextView textView = aVar.b;
        TextView textView2 = aVar.c;
        textView.setText(!TextUtils.isEmpty(com.kwad.sdk.core.response.a.a.u(adInfo)) ? com.kwad.sdk.core.response.a.a.u(adInfo) : !TextUtils.isEmpty(adInfo.advertiserInfo.adAuthorText) ? adInfo.advertiserInfo.adAuthorText : "可爱的广告君");
        textView.setVisibility(0);
        textView2.setText(com.kwad.sdk.core.response.a.a.s(adInfo));
    }

    private void a(final com.kwad.sdk.d.a.b bVar) {
        this.f = new com.kwad.sdk.core.download.a.b(this.d, null, new com.kwad.sdk.core.download.a.c() { // from class: com.kwad.sdk.d.kwai.h.1
            @Override // com.kwad.sdk.core.download.a.c
            public void a(int i) {
                bVar.a(com.kwad.sdk.core.response.a.a.b(i), i);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFailed() {
                bVar.a(com.kwad.sdk.core.response.a.a.A(h.this.c), 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFinished() {
                bVar.a(com.kwad.sdk.core.response.a.a.a(h.this.d), 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onIdle() {
                bVar.a(com.kwad.sdk.core.response.a.a.A(h.this.c), 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onInstalled() {
                bVar.a(com.kwad.sdk.core.response.a.a.m(h.this.c), 0);
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onProgressUpdate(int i) {
                bVar.a(com.kwad.sdk.core.response.a.a.a(i), i);
            }
        });
    }

    private static void b(Context context, a aVar, AdInfo adInfo, AdTemplate adTemplate) {
        TextView textView = aVar.b;
        textView.setText(!TextUtils.isEmpty(com.kwad.sdk.core.response.a.a.t(adInfo)) ? com.kwad.sdk.core.response.a.a.t(adInfo) : !TextUtils.isEmpty(adInfo.advertiserInfo.adAuthorText) ? adInfo.advertiserInfo.adAuthorText : "可爱的广告君");
        textView.setVisibility(0);
        String strAu = com.kwad.sdk.core.response.a.a.au(adInfo);
        ImageView imageView = aVar.a;
        if (TextUtils.isEmpty(strAu)) {
            imageView.setVisibility(8);
        } else {
            imageView.setImageResource(R.drawable.ksad_default_app_icon);
            KSImageLoader.loadWithRadius(imageView, strAu, adTemplate, g);
            imageView.setVisibility(0);
        }
        aVar.c.setText(com.kwad.sdk.core.response.a.a.s(adInfo));
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.b = (c) p();
        this.d = this.b.a;
        this.c = com.kwad.sdk.core.response.a.d.j(this.b.a);
        this.e.a = (ImageView) this.b.e.findViewById(R.id.ksad_app_icon);
        this.e.b = (TextView) this.b.e.findViewById(R.id.ksad_app_title);
        this.e.c = (TextView) this.b.e.findViewById(R.id.ksad_app_desc);
        if (com.kwad.sdk.core.response.a.a.B(this.c)) {
            b(this.a, this.e, this.c, this.d);
            a(this.b.e);
        } else {
            a(this.a, this.e, this.c, this.d);
        }
        this.b.e.a(com.kwad.sdk.core.response.a.a.A(this.c), 0);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.a = q();
    }
}
