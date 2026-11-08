package com.kwad.sdk.splashscreen.c;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.core.download.a.b;
import com.kwad.sdk.core.page.widget.webview.KsAdWebView;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.splashscreen.e;
import com.kwad.sdk.splashscreen.f;
import com.kwad.sdk.widget.d;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements d {
    private ViewStub a;
    private KsAdWebView b;
    private ViewGroup c;
    private View d;

    @Nullable
    private AdTemplate e;

    @Nullable
    private AdInfo f;
    private TextView g;
    private boolean h;

    @Nullable
    private f i;
    private com.kwad.sdk.widget.f j;
    private b k;
    private com.kwad.sdk.splashscreen.a l;

    public a(ViewStub viewStub, KsAdWebView ksAdWebView, boolean z, b bVar) {
        this.a = viewStub;
        this.b = ksAdWebView;
        this.k = bVar;
        this.h = z;
    }

    private void a(ViewGroup viewGroup) {
        this.d = viewGroup.findViewById(R.id.ksad_splash_actionbar_native);
        this.g = (TextView) viewGroup.findViewById(R.id.ksad_splash_actionbar_text);
        this.j = new com.kwad.sdk.widget.f(this.d.getContext(), this.d, this);
        a(e.a(this.e, this.f, this.k));
    }

    private void a(e eVar) {
        if (eVar == null) {
            return;
        }
        a(eVar.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (this.g == null || str == null) {
            return;
        }
        this.g.setText(str);
    }

    private void a(boolean z, boolean z2) {
        if (this.i != null) {
            this.i.a(z, z2);
        }
    }

    public void a() {
        com.kwad.sdk.core.d.a.a("SplashActionBarNativeHelper", "rollBackToNative mRootViewStub: " + this.a + ", mSplashWebView: " + this.b);
        if (this.a == null || this.b == null) {
            return;
        }
        if (this.c == null) {
            this.c = (ViewGroup) this.a.inflate();
            a(this.c);
        }
        this.c.setVisibility(0);
        this.b.setVisibility(8);
    }

    public void a(AdTemplate adTemplate) {
        this.e = adTemplate;
        if (this.l == null) {
            this.l = new com.kwad.sdk.splashscreen.a(this.e) { // from class: com.kwad.sdk.splashscreen.c.a.1
                @Override // com.kwad.sdk.splashscreen.a
                protected void a(String str) {
                    a.this.a(str);
                }
            };
        } else {
            this.l.a(this.e);
        }
        if (adTemplate != null) {
            this.f = com.kwad.sdk.core.response.a.d.j(adTemplate);
        }
        if (this.k == null || this.l == null) {
            return;
        }
        this.k.a(this.l);
    }

    public void a(@Nullable f fVar) {
        this.i = fVar;
    }

    @Override // com.kwad.sdk.widget.d
    public void a_(View view) {
        a(true, view.equals(this.d));
    }

    public void b() {
        if (this.k == null || this.l == null) {
            return;
        }
        this.k.b(this.l);
    }

    @Override // com.kwad.sdk.widget.d
    public void b(View view) {
        if (this.h) {
            a(false, view.equals(this.d));
        }
    }
}
