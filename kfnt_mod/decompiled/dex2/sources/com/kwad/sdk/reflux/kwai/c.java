package com.kwad.sdk.reflux.kwai;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.compliance.widget.ComplianceTextView;
import com.kwad.sdk.widget.DownloadProgressView;
import com.kwad.sdk.widget.KsLogoView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends a {
    public c(@NonNull Context context) {
        super(context);
    }

    @Override // com.kwad.sdk.reflux.kwai.a
    protected void a(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        inflate(context, R.layout.ksad_reflux_card_top, this);
        this.a = (TextView) findViewById(R.id.ksad_reflux_card_title);
        this.e = (ImageView) findViewById(R.id.ksad_reflux_card_img);
        this.f = (KsLogoView) findViewById(R.id.ksad_reflux_card_logo);
        this.d = (ImageView) findViewById(R.id.ksad_reflux_app_icon);
        this.b = (TextView) findViewById(R.id.ksad_reflux_app_name);
        this.c = (TextView) findViewById(R.id.ksad_reflux_app_desc);
        this.g = (ComplianceTextView) findViewById(R.id.ksad_reflux_card_title_compliance);
        this.h = (DownloadProgressView) findViewById(R.id.ksad_reflux_app_download_btn);
    }
}
