package com.kwad.sdk.feed.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.kwad.sdk.R;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.page.widget.RoundAngleImageView;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.feed.widget.base.RatioFrameLayout;
import com.kwad.sdk.utils.bb;
import com.kwad.sdk.widget.DownloadProgressView;
import com.kwad.sdk.widget.KsLogoView;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h extends com.kwad.sdk.feed.widget.base.a implements View.OnClickListener {
    private TextView a;
    private RoundAngleImageView f;
    private ImageView g;
    private com.kwad.sdk.core.download.a.b h;
    private KsLogoView i;
    private DownloadProgressView j;

    public h(@NonNull Context context) {
        super(context);
    }

    private void d() {
        this.i.a(this.b);
        this.j.a(this.b);
        this.j.setOnClickListener(this);
        if (com.kwad.sdk.core.response.a.a.B(this.c)) {
            this.h = new com.kwad.sdk.core.download.a.b(this.b, null, this.j.getAppDownloadListener());
            this.h.c(this.j.getAppDownloadListener());
            this.h.a((DialogInterface.OnShowListener) this);
            this.h.a((DialogInterface.OnDismissListener) this);
        }
    }

    @Override // com.kwad.sdk.feed.widget.base.a
    public void a(@NonNull AdTemplate adTemplate) {
        super.a(adTemplate);
        this.a.setText(com.kwad.sdk.core.response.a.a.s(this.c));
        List<String> listO = com.kwad.sdk.core.response.a.a.O(this.c);
        if (listO.size() >= 1) {
            KSImageLoader.loadFeeImage(this.f, listO.get(0), this.b);
        } else {
            com.kwad.sdk.core.d.a.e("FeedTextLeftImageView", "getImageUrlList size less than one");
        }
        d();
        this.g.setOnClickListener(this);
        setOnClickListener(this);
    }

    @Override // com.kwad.sdk.feed.widget.base.a
    protected void c() {
        this.a = (TextView) findViewById(R.id.ksad_ad_desc);
        ((RatioFrameLayout) findViewById(R.id.ksad_image_container)).setRatio(0.66f);
        this.f = (RoundAngleImageView) findViewById(R.id.ksad_ad_image);
        this.f.setRadius(com.kwad.sdk.a.kwai.a.a(getContext(), 3.0f));
        this.g = (ImageView) findViewById(R.id.ksad_ad_dislike);
        this.j = (DownloadProgressView) findViewById(R.id.ksad_app_download_btn);
        this.i = (KsLogoView) findViewById(R.id.ksad_feed_logo);
    }

    @Override // com.kwad.sdk.feed.widget.base.a
    protected int getLayoutId() {
        return R.layout.ksad_feed_text_left_image;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.g) {
            m();
        } else {
            com.kwad.sdk.core.download.a.a.a(new a.C0035a(bb.a(this)).a(this.b).a(this.h).a(view == this.j ? 1 : 2).a(view == this.j).a(new a.b() { // from class: com.kwad.sdk.feed.widget.h.1
                @Override // com.kwad.sdk.core.download.a.a.b
                public void a() {
                    h.this.k();
                }
            }));
        }
    }
}
