package com.kwad.sdk.f.a;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.api.KsAppDownloadListener;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f extends com.kwad.sdk.f.kwai.a implements View.OnClickListener {
    private ViewGroup b;
    private ImageView c;
    private TextView d;
    private TextView e;
    private AdTemplate f;
    private AdInfo g;

    @Nullable
    private com.kwad.sdk.core.download.a.b h;
    private com.kwad.sdk.contentalliance.detail.video.d i = new com.kwad.sdk.contentalliance.detail.video.e() { // from class: com.kwad.sdk.f.a.f.1
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void f() {
            try {
                f.this.e();
            } catch (Throwable th) {
                com.kwad.sdk.core.d.a.b(th);
                com.kwad.sdk.core.kwai.a.a(th);
            }
        }
    };
    private KsAppDownloadListener j = new com.kwad.sdk.core.download.a.c() { // from class: com.kwad.sdk.f.a.f.2
        @Override // com.kwad.sdk.api.KsAppDownloadListener
        public void onDownloadFailed() {
            f.this.e.setText(com.kwad.sdk.core.response.a.a.A(f.this.g));
        }

        @Override // com.kwad.sdk.api.KsAppDownloadListener
        public void onDownloadFinished() {
            f.this.e.setText(com.kwad.sdk.core.response.a.a.a(f.this.f));
        }

        @Override // com.kwad.sdk.api.KsAppDownloadListener
        public void onIdle() {
            f.this.e.setText(com.kwad.sdk.core.response.a.a.A(f.this.g));
        }

        @Override // com.kwad.sdk.api.KsAppDownloadListener
        public void onInstalled() {
            f.this.e.setText("立即打开");
        }

        @Override // com.kwad.sdk.api.KsAppDownloadListener
        public void onProgressUpdate(int i) {
        }
    };

    private void b(int i) {
        com.kwad.sdk.core.download.a.a.a(new a.C0035a(this.b.getContext()).a(this.f).a(i).a(this.h).a(new a.b() { // from class: com.kwad.sdk.f.a.f.3
            @Override // com.kwad.sdk.core.download.a.a.b
            public void a() {
                com.kwad.sdk.core.report.a.a(f.this.f, 2, f.this.a.c.getTouchCoords());
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        KSImageLoader.loadAppIcon(this.c, com.kwad.sdk.core.response.a.a.au(this.g), this.f, 12);
        this.d.setText(com.kwad.sdk.core.response.a.a.t(this.g));
        this.e.setText(com.kwad.sdk.core.response.a.a.A(this.g));
        if (this.h != null) {
            this.h.a(this.j);
        }
        this.b.setOnClickListener(this);
        this.b.setVisibility(0);
    }

    private void f() {
        this.a.a.a(this.b, null);
    }

    @Override // com.kwad.sdk.f.kwai.a, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.f = this.a.d;
        this.g = com.kwad.sdk.core.response.a.d.j(this.f);
        this.h = this.a.e;
        this.a.f.a(this.i);
        this.b.setVisibility(8);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.f.b(this.i);
        if (this.h != null) {
            this.h.b(this.j);
        }
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (ViewGroup) a(R.id.ksad_video_complete_app_container);
        this.c = (ImageView) a(R.id.ksad_app_icon);
        this.d = (TextView) a(R.id.ksad_app_name);
        this.e = (TextView) a(R.id.ksad_app_download);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int i;
        if (view == this.b) {
            i = 2;
        } else if (view != this.e) {
            return;
        } else {
            i = 1;
        }
        b(i);
        f();
    }
}
