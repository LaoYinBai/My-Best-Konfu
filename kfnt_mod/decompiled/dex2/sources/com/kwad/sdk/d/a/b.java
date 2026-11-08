package com.kwad.sdk.d.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.page.widget.TextProgressBar;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.at;
import com.kwad.sdk.utils.bb;
import com.kwad.sdk.widget.KSFrameLayout;
import com.kwad.sdk.widget.KsLogoView;
import com.kwad.sdk.widget.f;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends KSFrameLayout implements CompoundButton.OnCheckedChangeListener, com.kwad.sdk.widget.d {
    private String a;

    @Nullable
    private View b;
    private KSFrameLayout c;
    private KsLogoView d;
    private ImageView e;
    private TextProgressBar f;
    private ViewGroup g;
    private ViewGroup h;
    private ViewGroup i;
    private CompoundButton j;
    private View k;
    private View l;
    private TextView m;
    private ImageView n;
    private TextView o;
    private TextView p;
    private TextProgressBar q;
    private TextView r;
    private d s;
    private final a t;
    private boolean u;
    private AdTemplate v;

    public static class a {
        private boolean a = false;
        private boolean b = false;
        private int c = 0;
        private boolean d = true;

        public int a() {
            return this.c;
        }

        public void a(int i) {
            this.c = i;
        }

        public void a(boolean z) {
            this.a = z;
        }

        public void b(boolean z) {
            this.b = z;
        }

        public boolean b() {
            return this.d;
        }

        public void c(boolean z) {
            this.d = z;
        }
    }

    public b(@NonNull Context context, a aVar) {
        super(context);
        this.a = "%s秒后进入试玩页";
        this.u = false;
        this.t = aVar == null ? new a() : aVar;
        inflate(context, this.t.b() ? R.layout.ksad_interstitial_native_above : R.layout.ksad_interstitial_native, this);
        c(this.t.a);
    }

    private void a(View view, boolean z) {
        if (this.s != null) {
            this.s.b(z);
        }
        if (view.equals(this)) {
            if (this.s != null) {
                this.s.a(this);
                return;
            }
            return;
        }
        if (view.equals(this.k)) {
            if (!(1 == this.t.a()) || this.s == null) {
                return;
            }
            this.s.a();
            return;
        }
        if (view.equals(this.m)) {
            if (this.s != null) {
                this.s.b();
                return;
            }
            return;
        }
        if (view.equals(this.q)) {
            if (this.s != null) {
                this.s.b(view);
                return;
            }
            return;
        }
        if (view.equals(this.h)) {
            if (this.s != null) {
                this.s.h(view);
                return;
            }
            return;
        }
        if (view.equals(this.f)) {
            if (this.s != null) {
                this.s.c(view);
                return;
            }
            return;
        }
        if (view.equals(this.l)) {
            if (this.s != null) {
                this.s.f(view);
            }
        } else if (view.equals(this.c)) {
            if (this.s != null) {
                this.s.d(view);
            }
        } else if (view.equals(this.e)) {
            if (this.s != null) {
                this.s.e(view);
            }
        } else {
            if (!view.equals(this.i) || this.s == null) {
                return;
            }
            this.s.g(view);
        }
    }

    private void a(KSFrameLayout kSFrameLayout, boolean z) {
        kSFrameLayout.setClickable(true);
        new f(kSFrameLayout, this);
        this.c.setWidthBasedRatio(!z);
    }

    private void c(boolean z) {
        setClickable(true);
        this.c = (KSFrameLayout) findViewById(R.id.ksad_interstitial_native_video_container);
        this.b = findViewById(R.id.ksad_interstitial_full_bg);
        this.e = (ImageView) findViewById(R.id.ksad_video_first_frame_container);
        this.d = (KsLogoView) findViewById(R.id.ksad_ad_interstitial_logo);
        this.g = (ViewGroup) findViewById(R.id.ksad_interstitial_playing);
        this.h = (ViewGroup) findViewById(R.id.ksad_interstitial_play_end);
        this.f = (TextProgressBar) findViewById(R.id.ksad_interstitial_download_btn);
        this.i = (ViewGroup) findViewById(R.id.ksad_ad_desc_layout);
        this.k = findViewById(R.id.ksad_interstitial_close_outer);
        this.f.setTextDimen(bb.a(getContext(), 10.0f));
        this.f.setTextColor(-1);
        this.j = (CompoundButton) findViewById(R.id.ksad_interstitial_mute);
        this.m = (TextView) findViewById(R.id.ksad_interstitial_count_down);
        this.n = (ImageView) findViewById(R.id.ksad_interstitial_logo);
        this.o = (TextView) findViewById(R.id.ksad_interstitial_name);
        this.p = (TextView) findViewById(R.id.ksad_interstitial_desc);
        this.q = (TextProgressBar) findViewById(R.id.ksad_app_download_btn);
        this.l = findViewById(R.id.ksad_ad_download_container);
        new f(this, this);
        new f(this.e, this);
        new f(this.f, this);
        new f(this.q, this);
        new f(this.k, this);
        new f(this.h, this);
        new f(this.m, this);
        new f(this.l, this);
        new f(this.i, this);
        this.j.setOnCheckedChangeListener(this);
        this.r = (TextView) findViewById(R.id.ksad_interstitial_playable_timer);
        a(this.c, z);
    }

    public void a(float f, com.kwad.sdk.core.video.videoview.b bVar) {
        this.c.setRatio(f);
        this.c.addView(bVar);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) bVar.getLayoutParams();
        layoutParams.topMargin = 0;
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.gravity = 17;
        bVar.setLayoutParams(layoutParams);
    }

    public void a(int i, int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        marginLayoutParams.width = i;
        marginLayoutParams.height = i2;
        setLayoutParams(marginLayoutParams);
        requestLayout();
    }

    public void a(AdTemplate adTemplate) {
        this.d.a(adTemplate);
    }

    public void a(AdTemplate adTemplate, AdInfo adInfo) {
        TextView textView;
        String strAt;
        if (com.kwad.sdk.core.response.a.a.B(adInfo)) {
            this.n.setVisibility(0);
            this.n.setImageResource(R.drawable.ksad_default_app_icon);
            KSImageLoader.loadWithRadius(this.n, com.kwad.sdk.core.response.a.a.au(adInfo), adTemplate, 4);
            textView = this.o;
            strAt = com.kwad.sdk.core.response.a.a.t(adInfo);
        } else {
            this.n.setVisibility(0);
            this.n.setImageResource(R.drawable.ksad_default_app_icon);
            KSImageLoader.loadWithRadius(this.n, com.kwad.sdk.core.response.a.d.o(adTemplate), adTemplate, 4);
            textView = this.o;
            strAt = com.kwad.sdk.core.response.a.a.at(adInfo);
        }
        textView.setText(strAt);
        this.p.setText(com.kwad.sdk.core.response.a.a.s(adInfo));
        this.q.a(com.kwad.sdk.core.response.a.a.A(adInfo), 0);
    }

    public void a(String str) {
        if (this.m == null) {
            return;
        }
        if (str != null) {
            this.m.setText(str);
        }
        if (!this.t.b || this.u || this.m.getVisibility() == 0) {
            return;
        }
        this.m.setVisibility(0);
    }

    public void a(String str, int i) {
        if (this.f != null) {
            this.f.a(str, i);
        }
        if (this.q != null) {
            this.q.a(str, i);
        }
    }

    public void a(String str, AdTemplate adTemplate) {
        if (at.a(str)) {
            return;
        }
        this.e.setImageDrawable(null);
        KSImageLoader.loadImage(this.e, str, adTemplate);
    }

    public void a(boolean z) {
        if (this.g != null) {
            this.g.setVisibility(z ? 0 : 8);
        }
    }

    public void a(boolean z, int i) {
        if (this.r == null) {
            return;
        }
        this.r.setVisibility(z ? 0 : 8);
        if (i >= 0) {
            this.r.setText(String.format(this.a, String.valueOf(i)));
        }
    }

    public void a(boolean z, boolean z2) {
        if (this.e != null) {
            this.e.setVisibility(z ? 0 : 8);
            this.e.setClickable(z2);
        }
    }

    @Override // com.kwad.sdk.widget.d
    public void a_(View view) {
        a(view, true);
    }

    @Override // com.kwad.sdk.widget.d
    public void b(View view) {
        if (com.kwad.sdk.core.response.a.c.g(this.v)) {
            a(view, false);
        }
    }

    public void b(boolean z) {
        if (this.j != null) {
            this.j.setChecked(z);
        }
    }

    public void c() {
        if (this.m != null) {
            this.m.setVisibility(8);
            this.u = true;
        }
    }

    public void d() {
        a(false);
        this.h.setVisibility(0);
    }

    @Nullable
    public View getBlurBgView() {
        return this.b;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (this.s != null) {
            this.s.a(z);
        }
    }

    public void setAdTemplate(AdTemplate adTemplate) {
        this.v = adTemplate;
    }

    public void setViewListener(d dVar) {
        this.s = dVar;
    }
}
