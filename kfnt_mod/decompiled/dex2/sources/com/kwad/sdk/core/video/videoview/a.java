package com.kwad.sdk.core.video.videoview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.kwad.sdk.R;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.aw;
import com.kwad.sdk.utils.z;
import com.kwad.sdk.widget.KSRelativeLayout;
import org.json.JSONException;

@SuppressLint({"ViewConstructor"})
@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends c implements View.OnClickListener {
    private InterfaceC0047a A;
    private final com.kwad.sdk.core.download.a.c B;
    protected boolean a;
    protected ImageView b;
    protected TextView c;
    private Context e;

    @NonNull
    private AdTemplate f;
    private AdInfo g;
    private boolean h;
    private boolean i;
    private boolean j;
    private int k;
    private int l;
    private KSRelativeLayout m;
    private RelativeLayout n;
    private boolean o;
    private boolean p;
    private LinearLayout q;
    private LinearLayout r;
    private ProgressBar s;
    private ImageView t;
    private ViewGroup u;
    private ImageView v;
    private TextView w;
    private TextView x;
    private com.kwad.sdk.core.download.a.b y;
    private b z;

    /* renamed from: com.kwad.sdk.core.video.videoview.a$a, reason: collision with other inner class name */
    public interface InterfaceC0047a {
        void a(int i, z.a aVar);
    }

    public interface b {
        void a(long j);

        void b_();

        void e();

        void f();
    }

    public a(Context context, @NonNull AdTemplate adTemplate, @NonNull d dVar) {
        super(context, dVar);
        this.j = true;
        this.a = false;
        this.p = false;
        this.B = new com.kwad.sdk.core.download.a.c() { // from class: com.kwad.sdk.core.video.videoview.a.1
            @Override // com.kwad.sdk.core.download.a.c
            public void a(int i) {
                a.this.x.setText(com.kwad.sdk.core.response.a.a.a());
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFailed() {
                a.this.x.setText(com.kwad.sdk.core.response.a.a.A(a.this.g));
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadFinished() {
                a.this.x.setText(com.kwad.sdk.core.response.a.a.a(a.this.f));
            }

            @Override // com.kwad.sdk.core.download.a.c, com.kwad.sdk.api.KsAppDownloadListener
            public void onDownloadStarted() {
                a.this.x.setText(com.kwad.sdk.core.response.a.a.a(0));
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onIdle() {
                a.this.a(com.kwad.sdk.core.response.a.a.A(a.this.g));
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onInstalled() {
                a.this.x.setText(com.kwad.sdk.core.response.a.a.m(a.this.g));
            }

            @Override // com.kwad.sdk.api.KsAppDownloadListener
            public void onProgressUpdate(int i) {
                a.this.x.setText(com.kwad.sdk.core.response.a.a.a(i));
            }
        };
        this.e = context;
        this.f = adTemplate;
        this.g = com.kwad.sdk.core.response.a.d.j(adTemplate);
        m();
    }

    private void b(int i) {
        if (this.A != null) {
            this.A.a(i, this.m.getTouchCoords());
        }
    }

    private void m() {
        ImageView imageView;
        int i;
        LayoutInflater.from(this.e).inflate(R.layout.ksad_feed_video_palyer_controller, (ViewGroup) this, true);
        this.m = (KSRelativeLayout) findViewById(R.id.ksad_video_root_container);
        this.n = (RelativeLayout) findViewById(R.id.ksad_data_flow_container);
        this.c = (TextView) findViewById(R.id.ksad_data_flow_play_tip);
        this.b = (ImageView) findViewById(R.id.ksad_data_flow_play_btn);
        this.b.setOnClickListener(this);
        this.q = (LinearLayout) findViewById(R.id.ksad_video_network_unavailable);
        this.r = (LinearLayout) findViewById(R.id.ksad_video_error_container);
        this.s = (ProgressBar) findViewById(R.id.ksad_video_progress);
        this.t = (ImageView) findViewById(R.id.ksad_video_thumb_image);
        String strA = com.kwad.sdk.core.response.a.a.aa(this.g).a();
        if (TextUtils.isEmpty(strA)) {
            imageView = this.t;
            i = 8;
        } else {
            this.t.setImageDrawable(null);
            KSImageLoader.loadImage(this.t, strA, this.f);
            imageView = this.t;
            i = 0;
        }
        imageView.setVisibility(i);
        this.c.setText(aw.a(com.kwad.sdk.core.response.a.a.b(this.g) * 1000));
        a();
    }

    private void n() {
        if (this.u != null) {
            this.u.setVisibility(8);
        }
    }

    private void o() {
        this.n.setVisibility(0);
        this.t.setVisibility(0);
        this.f.mVideoPlayerStatus.setVideoPlayerBehavior(2);
    }

    private void p() {
        this.n.setVisibility(8);
    }

    private void q() {
        this.d.setKsPlayLogParam(com.kwad.sdk.contentalliance.detail.video.b.a(this.f));
    }

    private void setTopBottomVisible(boolean z) {
        if (this.p) {
            return;
        }
        this.s.setVisibility(z ? 0 : 8);
        this.o = z;
    }

    protected void a() {
    }

    @Override // com.kwad.sdk.core.video.videoview.c
    protected void a(int i) throws JSONException {
        com.kwad.sdk.core.d.a.a("AdVideoPlayerController", "onPlayStateChanged playState=" + i);
        if (i == 4) {
            if (this.z != null) {
                this.z.e();
            }
            this.t.setVisibility(8);
        }
        if (i == 9) {
            if (this.z != null) {
                this.z.f();
            }
            l();
            setTopBottomVisible(false);
            KSImageLoader.loadImage(this.t, com.kwad.sdk.core.response.a.a.h(this.g), this.f);
            this.t.setVisibility(0);
            b();
            return;
        }
        switch (i) {
            case -1:
                l();
                setTopBottomVisible(false);
                this.q.setVisibility(8);
                this.r.setVisibility(0);
                com.kwad.sdk.core.report.d.b(this.f, this.k, this.l);
                break;
            case 1:
                p();
                this.q.setVisibility(8);
                this.r.setVisibility(8);
                this.s.setVisibility(8);
                n();
                break;
            case 2:
                if (this.z != null) {
                    this.z.b_();
                }
                setTopBottomVisible(true);
                k();
                break;
        }
    }

    @Override // com.kwad.sdk.core.video.videoview.c
    public void a(int i, int i2) {
        this.l = i2;
        this.k = i;
    }

    public void a(String str) {
        ((TextView) findViewById(R.id.ksad_app_download)).setText(str);
    }

    public void a(boolean z) {
        ProgressBar progressBar;
        int i;
        if (this.p) {
            return;
        }
        if (!z) {
            progressBar = this.s;
            i = 8;
        } else {
            if (!this.o) {
                return;
            }
            progressBar = this.s;
            i = 0;
        }
        progressBar.setVisibility(i);
    }

    protected void b() {
        if (com.kwad.sdk.core.response.a.a.B(this.g)) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ksad_video_complete_app_container);
            this.v = (ImageView) findViewById(R.id.ksad_video_complete_app_icon);
            this.w = (TextView) findViewById(R.id.ksad_app_name);
            this.x = (TextView) findViewById(R.id.ksad_app_download);
            KSImageLoader.loadAppIcon(this.v, com.kwad.sdk.core.response.a.d.o(this.f), this.f, 12);
            this.w.setText(com.kwad.sdk.core.response.a.a.at(this.g));
            this.x.setText(com.kwad.sdk.core.response.a.a.A(this.g));
            this.u = linearLayout;
            this.v.setOnClickListener(this);
            this.w.setOnClickListener(this);
            this.x.setOnClickListener(this);
            this.y = new com.kwad.sdk.core.download.a.b(this.f);
            this.y.a(this.B);
        } else {
            LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.ksad_video_complete_h5_container);
            this.x = (TextView) findViewById(R.id.ksad_h5_open);
            this.x.setText(com.kwad.sdk.core.response.a.a.A(this.g));
            this.x.setOnClickListener(this);
            this.u = linearLayout2;
        }
        this.u.setOnClickListener(this);
        this.u.setVisibility(0);
    }

    protected void c() {
        this.q.setVisibility(0);
    }

    protected void d() {
        this.q.setVisibility(8);
    }

    @Override // com.kwad.sdk.core.video.videoview.c
    protected void e() {
        l();
        this.s.setProgress(0);
        this.s.setSecondaryProgress(0);
        p();
        this.q.setVisibility(8);
        this.r.setVisibility(8);
        this.s.setVisibility(8);
        this.t.setVisibility(8);
        this.n.setVisibility(8);
        this.f.mVideoPlayerStatus.setVideoPlayerBehavior(1);
        n();
    }

    protected void f() {
        if (!this.d.d()) {
            if (this.d.h() || this.d.f()) {
                q();
                this.d.b();
                return;
            }
            return;
        }
        if (!com.ksad.download.c.b.a(this.e)) {
            c();
            return;
        }
        d();
        if (!this.a && ((!this.j || !com.ksad.download.c.b.b(this.e)) && (!this.j || (!this.i && !this.h)))) {
            o();
        } else {
            q();
            this.d.a();
        }
    }

    protected void g() {
        this.d.c();
    }

    public AdTemplate getAdTemplate() {
        return this.f;
    }

    protected void h() {
        this.d.k();
    }

    @Override // com.kwad.sdk.core.video.videoview.c
    protected void i() {
        long currentPosition = this.d.getCurrentPosition();
        long duration = this.d.getDuration();
        this.s.setSecondaryProgress(this.d.getBufferPercentage());
        this.s.setProgress((int) ((currentPosition * 100.0f) / duration));
        if (this.z != null) {
            this.z.a(currentPosition);
        }
    }

    public void j() {
        this.p = true;
        this.s.setVisibility(8);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.b) {
            this.h = true;
            this.j = true;
            f();
        } else if (view == this.v) {
            b(1);
        } else {
            b(view == this.w ? 2 : view == this.x ? 3 : 4);
        }
    }

    public void setAdClickListener(InterfaceC0047a interfaceC0047a) {
        this.A = interfaceC0047a;
    }

    public void setCanControlPlay(boolean z) {
        this.a = z;
    }

    public void setDataAutoStart(boolean z) {
        this.j = z;
    }

    public void setDataFlowAutoStart(boolean z) {
        this.i = z;
    }

    public void setVideoPlayCallback(b bVar) {
        this.z = bVar;
    }
}
