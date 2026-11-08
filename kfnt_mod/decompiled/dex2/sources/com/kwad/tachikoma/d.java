package com.kwad.tachikoma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.kuaishou.tachikoma.api.TKContext;
import com.kuaishou.tachikoma.api.TachikomaApi;
import com.kuaishou.tachikoma.api.app.IRenderListener;
import com.kwad.sdk.R;
import com.kwad.sdk.plugin.h;
import com.tachikoma.core.layout.TKLayout;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d extends FrameLayout implements h {
    a a;
    private TKLayout b;
    private TKContext c;
    private TextView d;
    private String e;
    private IRenderListener f;

    public d(Context context) throws SecurityException {
        super(context);
        this.f = new IRenderListener() { // from class: com.kwad.tachikoma.d.1
            @Override // com.kuaishou.tachikoma.api.app.IRenderListener
            public void failed(Throwable th) {
                com.kwad.sdk.core.d.a.a(th);
            }

            @Override // com.kuaishou.tachikoma.api.app.IRenderListener
            public void success() {
                d.this.d();
            }
        };
        c();
    }

    private void b() throws SecurityException {
        this.c = TachikomaApi.getInstance().newTKJSContext(this.b);
        this.a = new a(this.c, this.e);
        this.c.buildJsBridge().addJavascriptInterface(this.a, "KwaiAd");
    }

    private void c() throws SecurityException {
        LayoutInflater.from(getContext()).inflate(R.layout.ksad_tachkoma_view, this);
        this.b = findViewById(R.id.ksad_tachikoma_layout);
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (com.kwad.sdk.b.c.booleanValue()) {
            if (this.d == null) {
                this.d = new TextView(getContext());
                this.d.setTextSize(12.0f);
                this.d.setTextColor(-65536);
                addView(this.d);
            }
            this.d.setText("Tachikoma");
            this.d.setVisibility(0);
        }
    }

    @Override // com.kwad.sdk.plugin.h
    public void a() {
        this.c.onDestroy();
        this.c = null;
    }

    @Override // com.kwad.sdk.plugin.h
    public void a(com.kwad.sdk.core.webview.kwai.a aVar) {
        this.a.a(aVar);
    }

    @Override // com.kwad.sdk.plugin.h
    public void a(String str, String str2) {
        if (this.c != null) {
            this.c.evaluateScript(str, "jsURL", str2, this.f);
        }
    }

    @Override // com.kwad.sdk.plugin.h
    public View getView() {
        return this;
    }

    public void setJsFileName(String str) {
        this.e = str;
    }
}
