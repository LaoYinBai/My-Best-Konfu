package com.kwad.sdk.core.report;

import android.content.pm.PackageManager;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.api.model.AdExposureFailedReason;
import com.kwad.sdk.core.report.p;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.ag;
import com.kwad.sdk.utils.z;
import com.kwai.library.widget.refresh.RefreshLayout;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static ExecutorService a = com.kwad.sdk.core.i.b.g();

    /* renamed from: com.kwad.sdk.core.report.a$a, reason: collision with other inner class name */
    public static class C0045a extends com.kwad.sdk.core.response.kwai.a {
        public int a;
        public String b;

        public C0045a(int i, String str) {
            this.a = i;
            this.b = str;
        }
    }

    public static void a(AdTemplate adTemplate) {
        g(adTemplate, 4);
    }

    public static void a(@Nullable AdTemplate adTemplate, int i) {
        p.a aVar = new p.a();
        aVar.i = i;
        a(adTemplate, 320, aVar, (JSONObject) null);
    }

    public static void a(AdTemplate adTemplate, int i, int i2) {
        p.a aVar = new p.a();
        aVar.d = 69;
        aVar.r = i;
        aVar.s = i2;
        a(adTemplate, 501, aVar, (JSONObject) null);
    }

    public static void a(AdTemplate adTemplate, int i, int i2, int i3) {
        p.a aVar = new p.a();
        aVar.d = i;
        aVar.r = i2;
        aVar.s = i3;
        a(adTemplate, 141, aVar, (JSONObject) null);
    }

    public static void a(AdTemplate adTemplate, int i, long j, int i2, @Nullable JSONObject jSONObject) {
        p.a aVar = new p.a();
        aVar.x = j;
        aVar.y = i2;
        aVar.c = i;
        a(adTemplate, 3, aVar, jSONObject);
    }

    public static void a(AdTemplate adTemplate, int i, AdExposureFailedReason adExposureFailedReason) {
        if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4) {
            p.a aVar = new p.a();
            if (adExposureFailedReason != null) {
                aVar.e = i;
                aVar.f = adExposureFailedReason.winEcpm;
            } else {
                aVar.e = i;
            }
            a(adTemplate, 809, aVar, (JSONObject) null);
        }
    }

    public static void a(@Nullable final AdTemplate adTemplate, final int i, @Nullable final p.a aVar, @Nullable final JSONObject jSONObject) {
        if (adTemplate == null || !q(adTemplate)) {
            return;
        }
        if (aVar == null) {
            aVar = new p.a();
        }
        aVar.v = com.kwad.sdk.core.response.a.a.H(com.kwad.sdk.core.response.a.d.j(adTemplate));
        com.kwad.sdk.core.d.a.a("AdReportManager", i + "");
        new o() { // from class: com.kwad.sdk.core.report.a.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.kwad.sdk.core.network.a
            @NonNull
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public p b() {
                return new p(adTemplate, i, aVar, jSONObject);
            }
        }.d();
    }

    public static void a(AdTemplate adTemplate, int i, @Nullable z.a aVar) {
        p.a aVar2 = new p.a();
        aVar2.b = i;
        if (aVar != null) {
            aVar2.g = aVar;
        }
        a(adTemplate, 2, aVar2, (JSONObject) null);
    }

    public static void a(AdTemplate adTemplate, int i, z.a aVar, @Nullable JSONObject jSONObject) {
        p.a aVar2 = new p.a();
        aVar2.b = i;
        aVar2.g = aVar;
        a(adTemplate, 2, aVar2, jSONObject);
    }

    public static void a(AdTemplate adTemplate, int i, @Nullable JSONObject jSONObject) {
        p.a aVar = new p.a();
        aVar.c = i;
        a(adTemplate, 3, aVar, jSONObject);
    }

    public static void a(AdTemplate adTemplate, int i, JSONObject jSONObject, String str) {
        p.a aVar = new p.a();
        aVar.h = str;
        a(adTemplate, i, aVar, jSONObject);
    }

    public static void a(AdTemplate adTemplate, C0045a c0045a) {
        p.a aVar = new p.a();
        if (c0045a != null) {
            aVar.m = c0045a.toJson().toString();
        }
        a(adTemplate, 40, aVar, (JSONObject) null);
    }

    public static void a(@Nullable AdTemplate adTemplate, p.a aVar) {
        a(adTemplate, 50, aVar, (JSONObject) null);
    }

    public static void a(AdTemplate adTemplate, p.a aVar, @Nullable JSONObject jSONObject) {
        a(adTemplate, 2, aVar, jSONObject);
    }

    public static void a(AdTemplate adTemplate, z.a aVar) {
        p.a aVar2 = new p.a();
        aVar2.g = aVar;
        a(adTemplate, 2, aVar2, (JSONObject) null);
    }

    public static void a(@NonNull AdTemplate adTemplate, @Nullable JSONObject jSONObject) {
        a(adTemplate, jSONObject, (p.a) null);
    }

    public static void a(@NonNull AdTemplate adTemplate, @Nullable JSONObject jSONObject, @Nullable p.a aVar) {
        if (adTemplate.mPvReported) {
            return;
        }
        adTemplate.mPvReported = true;
        a(adTemplate, 1, aVar, jSONObject);
    }

    public static void a(AdTemplate adTemplate, @Nullable JSONObject jSONObject, @Nullable p.a aVar, z.a aVar2, String str) {
        if (aVar == null) {
            aVar = new p.a();
        }
        aVar.g = aVar2;
        aVar.h = str;
        a(adTemplate, 2, aVar, jSONObject);
    }

    public static void a(AdTemplate adTemplate, @Nullable JSONObject jSONObject, @Nullable p.a aVar, String str) {
        if (aVar == null) {
            aVar = new p.a();
        }
        aVar.h = str;
        a(adTemplate, 2, aVar, jSONObject);
    }

    public static void b(AdTemplate adTemplate) {
        p.a aVar = new p.a();
        aVar.j = adTemplate.downloadSource;
        a(adTemplate, 30, aVar, (JSONObject) null);
    }

    public static void b(@Nullable AdTemplate adTemplate, int i) {
        p.a aVar = new p.a();
        aVar.i = i;
        a(adTemplate, 321, aVar, (JSONObject) null);
    }

    public static void b(AdTemplate adTemplate, int i, @Nullable JSONObject jSONObject) {
        p.a aVar = new p.a();
        aVar.a = i;
        a(adTemplate, 402, aVar, jSONObject);
    }

    public static void b(@Nullable AdTemplate adTemplate, p.a aVar) {
        a(adTemplate, 51, aVar, (JSONObject) null);
    }

    public static void b(AdTemplate adTemplate, JSONObject jSONObject) {
        e(adTemplate, 601, jSONObject);
    }

    public static void c(final AdTemplate adTemplate) {
        a.submit(new Runnable() { // from class: com.kwad.sdk.core.report.a.2
            @Override // java.lang.Runnable
            public void run() throws PackageManager.NameNotFoundException {
                AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
                int iB = ag.b(adInfoJ.downloadId, com.kwad.sdk.core.response.a.a.v(adInfoJ));
                p.a aVar = new p.a();
                aVar.j = adTemplate.downloadSource;
                aVar.k = iB;
                aVar.l = adTemplate.installFrom;
                a.a(adTemplate, 32, aVar, (JSONObject) null);
            }
        });
    }

    public static void c(@Nullable AdTemplate adTemplate, int i) {
        p.a aVar = new p.a();
        aVar.i = i;
        a(adTemplate, 803, aVar, (JSONObject) null);
    }

    public static void c(AdTemplate adTemplate, int i, @Nullable JSONObject jSONObject) {
        p.a aVar = new p.a();
        aVar.d = i;
        a(adTemplate, 140, aVar, jSONObject);
    }

    public static void c(@Nullable AdTemplate adTemplate, p.a aVar) {
        a(adTemplate, 52, aVar, (JSONObject) null);
    }

    public static void c(AdTemplate adTemplate, @Nullable JSONObject jSONObject) {
        p.a aVar = new p.a();
        aVar.j = adTemplate.downloadSource;
        a(adTemplate, 33, aVar, jSONObject);
    }

    public static void d(AdTemplate adTemplate) {
        g(adTemplate, 36);
    }

    public static void d(AdTemplate adTemplate, int i) {
        if (adTemplate == null) {
            return;
        }
        p.a aVar = new p.a();
        aVar.p = com.kwad.sdk.core.response.a.a.v(com.kwad.sdk.core.response.a.d.j(adTemplate));
        a(adTemplate, i, aVar, new JSONObject());
    }

    public static void d(AdTemplate adTemplate, int i, @Nullable JSONObject jSONObject) {
        p.a aVar = new p.a();
        aVar.p = com.kwad.sdk.core.response.a.a.v(com.kwad.sdk.core.response.a.d.j(adTemplate));
        aVar.d = i;
        a(adTemplate, 140, aVar, jSONObject);
    }

    public static void d(@Nullable AdTemplate adTemplate, p.a aVar) {
        a(adTemplate, 59, aVar, (JSONObject) null);
    }

    public static void d(AdTemplate adTemplate, @Nullable JSONObject jSONObject) {
        p.a aVar = new p.a();
        aVar.j = adTemplate.downloadSource;
        a(adTemplate, 34, aVar, jSONObject);
    }

    public static void e(AdTemplate adTemplate) {
        g(adTemplate, 38);
    }

    public static void e(AdTemplate adTemplate, int i) {
        a(adTemplate, i, new p.a(), new JSONObject());
    }

    private static void e(AdTemplate adTemplate, int i, JSONObject jSONObject) {
        a(adTemplate, i, (p.a) null, jSONObject);
    }

    public static void e(final AdTemplate adTemplate, final JSONObject jSONObject) {
        a.submit(new Runnable() { // from class: com.kwad.sdk.core.report.a.1
            @Override // java.lang.Runnable
            public void run() {
                p.a aVar = new p.a();
                aVar.j = adTemplate.downloadSource;
                a.f(adTemplate, aVar);
                a.a(adTemplate, 31, aVar, jSONObject);
                AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
                ag.a(adInfoJ.downloadFilePath, adInfoJ.downloadId);
            }
        });
    }

    public static void f(AdTemplate adTemplate) {
        g(adTemplate, 41);
    }

    public static void f(AdTemplate adTemplate, int i) {
        p.a aVar = new p.a();
        aVar.d = i;
        a(adTemplate, 141, aVar, (JSONObject) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f(AdTemplate adTemplate, p.a aVar) {
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
        String str = adInfoJ.downloadFilePath;
        if (str == null) {
            return;
        }
        String strV = com.kwad.sdk.core.response.a.a.v(adInfoJ);
        String strA = ag.a(str);
        if (strA == null || TextUtils.isEmpty(strA) || strA.equals(strV)) {
            return;
        }
        aVar.q = strA;
        aVar.p = strV;
        adInfoJ.adBaseInfo.appPackageName = strA;
    }

    public static void f(AdTemplate adTemplate, JSONObject jSONObject) {
        p.a aVar = new p.a();
        aVar.j = adTemplate.downloadSource;
        a(adTemplate, 35, aVar, jSONObject);
    }

    public static void g(AdTemplate adTemplate) {
        p.a aVar = new p.a();
        aVar.p = com.kwad.sdk.core.response.a.a.v(com.kwad.sdk.core.response.a.d.j(adTemplate));
        a(adTemplate, 768, aVar, new JSONObject());
    }

    private static void g(AdTemplate adTemplate, int i) {
        a(adTemplate, i, (p.a) null, new JSONObject());
    }

    public static void g(@Nullable AdTemplate adTemplate, @Nullable JSONObject jSONObject) {
        e(adTemplate, 399, jSONObject);
    }

    public static void h(AdTemplate adTemplate) {
        g(adTemplate, 37);
    }

    public static void h(@Nullable AdTemplate adTemplate, @Nullable JSONObject jSONObject) {
        e(adTemplate, RefreshLayout.DEFAULT_ANIMATE_DURATION, jSONObject);
    }

    public static void i(@Nullable AdTemplate adTemplate) {
        g(adTemplate, (JSONObject) null);
    }

    public static void i(AdTemplate adTemplate, @Nullable JSONObject jSONObject) {
        e(adTemplate, 501, jSONObject);
    }

    public static void j(@Nullable AdTemplate adTemplate) {
        h(adTemplate, null);
    }

    public static void j(AdTemplate adTemplate, @Nullable JSONObject jSONObject) {
        e(adTemplate, 450, jSONObject);
    }

    public static void k(@Nullable AdTemplate adTemplate) {
        g(adTemplate, 323);
    }

    public static void k(AdTemplate adTemplate, @Nullable JSONObject jSONObject) {
        e(adTemplate, 451, jSONObject);
    }

    public static void l(@Nullable AdTemplate adTemplate) {
        g(adTemplate, 58);
    }

    public static void m(AdTemplate adTemplate) {
        a(adTemplate, 600, (p.a) null, (JSONObject) null);
    }

    public static void n(AdTemplate adTemplate) {
        p.a aVar = new p.a();
        aVar.u = com.kwad.sdk.core.response.a.a.ag(com.kwad.sdk.core.response.a.d.j(adTemplate));
        com.kwad.sdk.core.d.a.a("AdReportManager", "reportDownloadCardClose downloadStatus=" + aVar.u);
        a(adTemplate, 713, aVar, (JSONObject) null);
    }

    public static void o(AdTemplate adTemplate) {
        g(adTemplate, 722);
    }

    public static void p(AdTemplate adTemplate) {
        g(adTemplate, 721);
    }

    private static boolean q(AdTemplate adTemplate) {
        return com.kwad.sdk.core.response.a.d.c(adTemplate) || com.kwad.sdk.core.response.a.d.a(adTemplate);
    }
}
