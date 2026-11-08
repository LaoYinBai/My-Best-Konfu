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
public class b extends com.kwad.sdk.feed.widget.base.a implements View.OnClickListener {
    private TextView a;
    private ImageView f;
    private ImageView g;
    private ImageView h;
    private ImageView i;
    private KsLogoView j;

    public b(@NonNull Context context) {
        super(context);
    }

    @Override // com.kwad.sdk.feed.widget.base.a
    public void a(@NonNull AdTemplate adTemplate) {
        super.a(adTemplate);
        this.a.setText(com.kwad.sdk.core.response.a.a.s(this.c));
        this.j.a(adTemplate);
        List<String> listO = com.kwad.sdk.core.response.a.a.O(this.c);
        if (listO.size() >= 3) {
            KSImageLoader.loadFeeImage(this.f, listO.get(0), this.b);
            KSImageLoader.loadFeeImage(this.g, listO.get(1), this.b);
            KSImageLoader.loadFeeImage(this.h, listO.get(2), this.b);
        } else {
            com.kwad.sdk.core.d.a.e("FeedTextAboveGroupImageView", "getImageUrlList size less than three");
        }
        this.i.setOnClickListener(this);
        setOnClickListener(this);
    }

    @Override // com.kwad.sdk.feed.widget.base.a
    protected void c() {
        this.a = (TextView) findViewById(R.id.ksad_ad_desc);
        ((RatioFrameLayout) findViewById(R.id.ksad_image_container)).setRatio(0.56f);
        this.f = (ImageView) findViewById(R.id.ksad_ad_image_left);
        this.g = (ImageView) findViewById(R.id.ksad_ad_image_mid);
        this.h = (ImageView) findViewById(R.id.ksad_ad_image_right);
        this.i = (ImageView) findViewById(R.id.ksad_ad_dislike);
        this.j = (KsLogoView) findViewById(R.id.ksad_ad_dislike_logo);
    }

    @Override // com.kwad.sdk.feed.widget.base.a
    protected int getLayoutId() {
        return R.layout.ksad_feed_text_above_group_image;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.i) {
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
