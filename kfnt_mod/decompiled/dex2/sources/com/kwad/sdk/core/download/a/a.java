package com.kwad.sdk.core.download.a;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.core.page.AdWebViewActivityProxy;
import com.kwad.sdk.core.page.AdWebViewLandPageActivityProxy;
import com.kwad.sdk.core.page.AdWebViewVideoActivityProxy;
import com.kwad.sdk.core.report.p;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {

    /* renamed from: com.kwad.sdk.core.download.a.a$a, reason: collision with other inner class name */
    public static class C0035a {
        private final Context a;
        private boolean b;
        private boolean c;
        private AdTemplate d;
        private b e;
        private com.kwad.sdk.core.download.a.b f;
        private boolean g;
        private int h;
        private boolean i;
        private boolean j;
        private p.a k;
        private JSONObject l;
        private boolean m = false;

        public C0035a(Context context) {
            this.a = context;
        }

        public Context a() {
            return this.a;
        }

        public C0035a a(int i) {
            this.h = i;
            return this;
        }

        public C0035a a(b bVar) {
            this.e = bVar;
            return this;
        }

        public C0035a a(com.kwad.sdk.core.download.a.b bVar) {
            this.f = bVar;
            return this;
        }

        public C0035a a(p.a aVar) {
            this.k = aVar;
            return this;
        }

        public C0035a a(AdTemplate adTemplate) {
            this.d = adTemplate;
            return this;
        }

        public C0035a a(JSONObject jSONObject) {
            this.l = jSONObject;
            return this;
        }

        public C0035a a(boolean z) {
            this.g = z;
            return this;
        }

        public C0035a b(boolean z) {
            this.i = z;
            return this;
        }

        public AdTemplate b() {
            return this.d;
        }

        public C0035a c(boolean z) {
            this.j = z;
            return this;
        }

        public b c() {
            return this.e;
        }

        public C0035a d(boolean z) {
            this.m = z;
            return this;
        }

        public com.kwad.sdk.core.download.a.b d() {
            return this.f;
        }

        public C0035a e(boolean z) {
            this.c = z;
            return this;
        }

        public boolean e() {
            return this.g;
        }

        public C0035a f(boolean z) {
            this.b = z;
            return this;
        }

        public boolean f() {
            return this.i;
        }

        public int g() {
            return this.h;
        }

        public boolean h() {
            return this.j;
        }

        public boolean i() {
            return this.m;
        }

        public JSONObject j() {
            return this.l;
        }

        public boolean k() {
            return this.c;
        }

        public boolean l() {
            return this.b;
        }
    }

    public interface b {
        void a();
    }

    public static int a(@NonNull Context context, @NonNull AdTemplate adTemplate, @NonNull b bVar, @Nullable com.kwad.sdk.core.download.a.b bVar2, boolean z) {
        return a(new C0035a(context).a(adTemplate).a(bVar).a(bVar2).a(z).b(false));
    }

    public static int a(@NonNull Context context, @NonNull AdTemplate adTemplate, @NonNull b bVar, @Nullable com.kwad.sdk.core.download.a.b bVar2, boolean z, boolean z2) {
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
        com.kwad.sdk.home.download.a.a().a(true);
        if (d.a(context, adTemplate, 1) == 1) {
            bVar.a();
            return 0;
        }
        if (!com.kwad.sdk.core.response.a.a.B(adInfoJ)) {
            bVar.a();
            if (com.kwad.sdk.utils.f.a(context, com.kwad.sdk.core.response.a.a.aL(adInfoJ), com.kwad.sdk.core.response.a.a.v(adInfoJ))) {
                com.kwad.sdk.core.report.a.k(adTemplate);
            } else {
                AdWebViewActivityProxy.launch(context, adTemplate);
            }
            return 0;
        }
        if (bVar2 == null) {
            return 0;
        }
        int iA = bVar2.a(new C0035a(context).a(z).a(adTemplate).b(z2).d(false));
        if (adInfoJ.status != 2 && adInfoJ.status != 3) {
            bVar.a();
        }
        return iA;
    }

    public static int a(C0035a c0035a) {
        if (c0035a.l()) {
            a(c0035a.a(), c0035a.b(), c0035a.c(), c0035a.d(), c0035a.g, c0035a.f());
            return 0;
        }
        if (com.kwad.sdk.core.download.a.b.c(c0035a) == 3) {
            return 0;
        }
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(c0035a.b());
        com.kwad.sdk.home.download.a.a().a(true);
        if (d.a(c0035a.a(), c0035a.b(), 1) == 1) {
            c(c0035a);
            return 0;
        }
        if (c0035a.k() && (!com.kwad.sdk.core.response.a.a.B(adInfoJ) || g(c0035a))) {
            c(c0035a);
            f(c0035a);
            return 0;
        }
        if (!com.kwad.sdk.core.response.a.a.B(adInfoJ)) {
            if (c0035a.b().isWebViewDownload) {
                return e(c0035a);
            }
            if (com.kwad.sdk.utils.f.a(c0035a.a(), com.kwad.sdk.core.response.a.a.aL(adInfoJ), com.kwad.sdk.core.response.a.a.v(adInfoJ))) {
                c(c0035a);
                com.kwad.sdk.core.report.a.k(c0035a.b());
                return 0;
            }
            c(c0035a);
            AdWebViewActivityProxy.launch(c0035a.a(), c0035a.b());
            return 0;
        }
        if (com.kwad.sdk.core.response.a.a.B(adInfoJ)) {
            if (AdWebViewLandPageActivityProxy.showingAdWebViewLandPage || com.kwad.sdk.core.download.kwai.b.a()) {
                c0035a.d(false);
                c(c0035a);
            } else {
                c(c0035a);
                if (!b(c0035a)) {
                    c0035a.d(true);
                }
            }
            return e(c0035a);
        }
        return 0;
    }

    private static boolean b(C0035a c0035a) {
        AdTemplate adTemplateB = c0035a.b();
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplateB);
        if (!c0035a.k() || !com.kwad.sdk.core.response.a.a.V(adInfoJ) || TextUtils.isEmpty(com.kwad.sdk.core.response.a.a.G(adInfoJ)) || AdWebViewVideoActivityProxy.showingAdWebViewVideoActivity || c0035a.d().d()) {
            return false;
        }
        AdWebViewVideoActivityProxy.launch(c0035a.a(), adTemplateB);
        return true;
    }

    private static void c(C0035a c0035a) {
        d(c0035a);
        if (c0035a.c() != null) {
            c0035a.c().a();
        }
    }

    private static void d(C0035a c0035a) {
        if (c0035a.h()) {
            com.kwad.sdk.core.report.a.a(c0035a.d, c0035a.k, c0035a.j());
        }
    }

    private static int e(C0035a c0035a) {
        com.kwad.sdk.core.download.a.b bVarD = c0035a.d();
        if (bVarD == null) {
            bVarD = new com.kwad.sdk.core.download.a.b(c0035a.d);
            c0035a.a(bVarD);
        }
        return bVarD.a(c0035a);
    }

    private static void f(C0035a c0035a) {
        int i;
        AdTemplate adTemplateB = c0035a.b();
        Context contextA = c0035a.a();
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplateB);
        if (com.kwad.sdk.utils.f.a(contextA, com.kwad.sdk.core.response.a.a.aL(adInfoJ), com.kwad.sdk.core.response.a.a.v(adInfoJ))) {
            com.kwad.sdk.core.report.a.k(adTemplateB);
            return;
        }
        if (g(c0035a)) {
            i = 4;
        } else {
            if (com.kwad.sdk.core.response.a.a.V(adInfoJ) && !adTemplateB.mAdWebVideoPageShowing) {
                AdWebViewVideoActivityProxy.launch(contextA, adTemplateB);
                return;
            }
            i = 0;
        }
        AdWebViewActivityProxy.launch(contextA, adTemplateB, i);
    }

    private static boolean g(C0035a c0035a) {
        AdTemplate adTemplateB = c0035a.b();
        return com.kwad.sdk.core.response.a.b.j(adTemplateB) && !adTemplateB.interactLandingPageShowing;
    }
}
