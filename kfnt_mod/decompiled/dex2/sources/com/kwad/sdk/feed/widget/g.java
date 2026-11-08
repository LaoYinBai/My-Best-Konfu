package com.kwad.sdk.feed.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.kwad.sdk.R;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.page.AdWebViewActivityProxy;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.feed.FeedDownloadActivityProxy;
import com.kwad.sdk.feed.widget.base.RatioFrameLayout;
import com.kwad.sdk.widget.KsLogoView;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g extends com.kwad.sdk.feed.widget.base.a implements View.OnClickListener {
    private TextView a;
    private ImageView f;
    private ImageView g;
    private KsLogoView h;

    public g(@NonNull Context context) {
        super(context);
    }

    @Override // com.kwad.sdk.feed.widget.base.a
    public void a(@NonNull AdTemplate adTemplate) {
        super.a(adTemplate);
        this.a.setText(com.kwad.sdk.core.response.a.a.s(this.c));
        List<String> listO = com.kwad.sdk.core.response.a.a.O(this.c);
        this.h.a(adTemplate);
        if (listO.size() >= 1) {
            KSImageLoader.loadFeeImage(this.f, listO.get(0), this.b);
        } else {
            com.kwad.sdk.core.d.a.e("FeedTextImmerseImageView", "getImageUrlList size less than one");
        }
        this.g.setOnClickListener(this);
        setOnClickListener(this);
    }

    @Override // com.kwad.sdk.feed.widget.base.a
    protected void c() {
        ((RatioFrameLayout) findViewById(R.id.ksad_container)).setRatio(0.56f);
        this.a = (TextView) findViewById(R.id.ksad_ad_desc);
        this.f = (ImageView) findViewById(R.id.ksad_ad_image);
        this.g = (ImageView) findViewById(R.id.ksad_ad_dislike);
        this.h = (KsLogoView) findViewById(R.id.ksad_feed_logo);
    }

    @Override // com.kwad.sdk.feed.widget.base.a
    protected int getLayoutId() {
        return R.layout.ksad_feed_text_immerse_image;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.g) {
            m();
            return;
        }
        if (com.kwad.sdk.core.download.a.d.a(getContext(), this.b, 1) == 1) {
            k();
        } else if (com.kwad.sdk.core.response.a.a.B(this.c)) {
            k();
            FeedDownloadActivityProxy.launch(this.e, this.b, this.d);
        } else {
            k();
            AdWebViewActivityProxy.launch(getContext(), this.b);
        }
    }
}
