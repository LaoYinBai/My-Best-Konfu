package com.kwad.sdk.reflux.kwai;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.compliance.widget.ComplianceTextView;
import com.kwad.sdk.core.imageloader.ImageLoaderProxy;
import com.kwad.sdk.core.imageloader.core.DisplayImageOptionsCompat;
import com.kwad.sdk.core.imageloader.core.decode.DecodedResult;
import com.kwad.sdk.core.imageloader.core.listener.SimpleImageLoadingListener;
import com.kwad.sdk.core.imageloader.utils.BlurUtils;
import com.kwad.sdk.widget.DownloadProgressView;
import com.kwad.sdk.widget.KsLogoView;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends a {
    private ImageView i;

    public b(@NonNull Context context) {
        super(context);
    }

    @Override // com.kwad.sdk.reflux.kwai.a
    protected void a(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        inflate(context, R.layout.ksad_reflux_card_left, this);
        this.a = (TextView) findViewById(R.id.ksad_reflux_card_title);
        this.e = (ImageView) findViewById(R.id.ksad_reflux_card_img);
        this.f = (KsLogoView) findViewById(R.id.ksad_reflux_card_logo);
        this.d = (ImageView) findViewById(R.id.ksad_reflux_app_icon);
        this.b = (TextView) findViewById(R.id.ksad_reflux_app_name);
        this.c = (TextView) findViewById(R.id.ksad_reflux_app_desc);
        this.g = (ComplianceTextView) findViewById(R.id.ksad_reflux_card_title_compliance);
        this.h = (DownloadProgressView) findViewById(R.id.ksad_reflux_app_download_btn);
        this.i = (ImageView) findViewById(R.id.ksad_reflux_card_blur);
    }

    @Override // com.kwad.sdk.reflux.kwai.a
    public void a(com.kwad.sdk.reflux.b bVar) {
        super.a(bVar);
        if (bVar == null || bVar.c() == null || this.i == null) {
            return;
        }
        ImageLoaderProxy.INSTANCE.load(getContext(), bVar.h(), this.i, new DisplayImageOptionsCompat.Builder().setBlurRadius(50).build(), new SimpleImageLoadingListener() { // from class: com.kwad.sdk.reflux.kwai.b.1
            @Override // com.kwad.sdk.core.imageloader.core.listener.SimpleImageLoadingListener, com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
            public boolean onDecode(String str, InputStream inputStream, DecodedResult decodedResult) {
                decodedResult.mBitmap = BlurUtils.stackBlur(BitmapFactory.decodeStream(inputStream), 50, false);
                return true;
            }
        });
    }
}
