package com.kwad.sdk.reflux.kwai;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.compliance.widget.ComplianceTextView;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.report.p;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.feed.widget.base.a;
import com.kwad.sdk.utils.bb;
import com.kwad.sdk.widget.DownloadProgressView;
import com.kwad.sdk.widget.KSFrameLayout;
import com.kwad.sdk.widget.KsLogoView;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a extends KSFrameLayout implements View.OnClickListener {
    private static int k = 8;
    protected TextView a;
    protected TextView b;
    protected TextView c;
    protected ImageView d;
    protected ImageView e;
    protected KsLogoView f;
    protected ComplianceTextView g;
    protected DownloadProgressView h;
    private com.kwad.sdk.reflux.b i;
    private a.InterfaceC0062a j;

    public a(@NonNull Context context) {
        super(context);
        b(context, null, 0);
    }

    private void b(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        a(context, attributeSet, i);
        if (this.h != null) {
            this.h.setOnClickListener(this);
        }
        setOnClickListener(this);
    }

    protected void a(int i, boolean z) {
        if (this.i == null) {
            return;
        }
        final AdTemplate adTemplateC = this.i.c();
        com.kwad.sdk.core.download.a.a.a(new a.C0035a(bb.a(this)).a(adTemplateC).a(this.i.n()).a(i).a(z).a(new a.b() { // from class: com.kwad.sdk.reflux.kwai.a.1
            @Override // com.kwad.sdk.core.download.a.a.b
            public void a() {
                a.this.a(adTemplateC);
            }
        }));
    }

    protected abstract void a(@NonNull Context context, @Nullable AttributeSet attributeSet, int i);

    @Override // com.kwad.sdk.widget.KSFrameLayout, com.kwad.sdk.widget.j
    public void a(View view) {
        super.a(view);
        com.kwai.sodler.lib.a.b("BaseRefluxCardView", "onFirstVisible: " + getClass().getSimpleName());
        c();
    }

    protected void a(AdTemplate adTemplate) {
        p.a aVar = new p.a();
        aVar.g = getTouchCoords();
        if (this.i != null) {
            aVar.z = this.i.j();
        }
        com.kwad.sdk.core.report.a.a(adTemplate, aVar, (JSONObject) null);
        if (this.j != null) {
            this.j.a();
        }
    }

    public void a(com.kwad.sdk.reflux.b bVar) {
        if (bVar == null) {
            return;
        }
        this.i = bVar;
        if (this.a != null && bVar.d() != null) {
            this.a.setText(bVar.d());
        }
        if (this.b != null && bVar.f() != null) {
            this.b.setText(bVar.f());
        }
        if (this.c != null && bVar.g() != null) {
            this.c.setText(bVar.g());
        }
        AdTemplate adTemplateC = bVar.c();
        if (adTemplateC != null) {
            if (this.d != null && bVar.e() != null) {
                KSImageLoader.loadAppIcon(this.d, bVar.e(), adTemplateC, k);
            }
            if (this.e != null && bVar.h() != null) {
                KSImageLoader.loadImage(this.e, bVar.h(), adTemplateC);
            }
            if (this.f != null) {
                this.f.a(adTemplateC);
            }
            if (this.g != null) {
                this.g.setAdTemplate(adTemplateC);
            }
            if (this.h != null) {
                this.h.a(adTemplateC);
            }
            AdInfo adInfoJ = d.j(adTemplateC);
            if (this.h != null) {
                this.h.a(adInfoJ, bVar.l(), bVar.m());
            }
        }
    }

    protected void c() {
        if (this.i == null) {
            return;
        }
        AdTemplate adTemplateC = this.i.c();
        if (!adTemplateC.mPvReported && this.j != null) {
            this.j.b();
        }
        p.a aVar = new p.a();
        if (this.i != null) {
            aVar.z = this.i.j();
        }
        com.kwad.sdk.core.report.a.a(adTemplateC, (JSONObject) null, aVar);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.equals(this.h)) {
            a(1, true);
        } else if (view.equals(this)) {
            a(2, true);
        }
    }

    public void setAdClickListener(a.InterfaceC0062a interfaceC0062a) {
        this.j = interfaceC0062a;
    }
}
