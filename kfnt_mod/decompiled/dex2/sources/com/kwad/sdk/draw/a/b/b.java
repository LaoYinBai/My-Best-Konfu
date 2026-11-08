package com.kwad.sdk.draw.a.b;

import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.core.webview.jshandler.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends com.kwad.sdk.draw.kwai.a {
    private FrameLayout b;

    @Nullable
    private com.kwad.sdk.i.b c;
    private b.c d = new b.c() { // from class: com.kwad.sdk.draw.a.b.b.1
        @Override // com.kwad.sdk.core.webview.jshandler.b.c
        public void a(b.a aVar) {
            if (b.this.a.a != null) {
                b.this.a.a.onAdClicked();
            }
        }
    };

    @Override // com.kwad.sdk.draw.kwai.a, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.c = this.a.g;
        if (this.c == null) {
            return;
        }
        this.c.a(this.d);
        this.c.a(this.b, this.a.b, this.a.c, this.a.d);
        this.c.b();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        if (this.c != null) {
            this.c.g();
        }
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (FrameLayout) a(R.id.ksad_play_end_web_card_container);
    }
}
