package com.kwad.sdk.reward.widget;

import android.app.Activity;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.reward.j;
import com.kwad.sdk.reward.k;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static j.b a(final j.a aVar, final com.kwad.sdk.reward.a aVar2) {
        final Activity activity = aVar2.h;
        final com.kwad.sdk.reward.c.a aVar3 = aVar2.k;
        final AdTemplate adTemplate = aVar2.g;
        final AdInfo adInfoJ = d.j(adTemplate);
        final JSONObject jSONObject = aVar2.e;
        return new j.c() { // from class: com.kwad.sdk.reward.widget.a.1
            @Override // com.kwad.sdk.reward.j.c, com.kwad.sdk.reward.j.b
            public void a() {
                aVar3.c();
                if (aVar.a() == 0) {
                    com.kwad.sdk.core.report.a.j(adTemplate, jSONObject);
                } else {
                    com.kwad.sdk.core.report.a.c(adTemplate, 149, jSONObject);
                }
            }

            @Override // com.kwad.sdk.reward.j.c, com.kwad.sdk.reward.j.b
            public void b() {
                com.kwad.sdk.core.report.a.f(adTemplate, 151);
                if (com.kwad.sdk.core.response.a.a.aG(adInfoJ)) {
                    activity.finish();
                    return;
                }
                com.kwad.sdk.core.report.a.a(adTemplate, (int) (com.kwad.sdk.core.response.a.a.o(adInfoJ) / 1000), (int) (aVar3.h() / 1000));
                aVar2.u = true;
                aVar3.a(true);
                k.a(aVar2);
            }

            @Override // com.kwad.sdk.reward.j.c, com.kwad.sdk.reward.j.b
            public void c() {
                aVar3.b();
                if (aVar.a() == 1 || aVar.a() == 2) {
                    com.kwad.sdk.core.report.a.f(adTemplate, 150);
                } else {
                    com.kwad.sdk.core.report.a.k(adTemplate, jSONObject);
                }
            }

            @Override // com.kwad.sdk.reward.j.c, com.kwad.sdk.reward.j.b
            public void d() {
                super.d();
                com.kwad.sdk.core.report.a.f(adTemplate, 150);
                aVar2.a(aVar2.i, 156, 1);
            }

            @Override // com.kwad.sdk.reward.j.c, com.kwad.sdk.reward.j.b
            public void e() {
                super.e();
                com.kwad.sdk.core.report.a.f(adTemplate, 150);
                aVar2.a(aVar2.i, 156, 1);
            }
        };
    }

    public static void a(com.kwad.sdk.reward.a aVar) {
        String str;
        Activity activity = aVar.h;
        AdTemplate adTemplate = aVar.g;
        AdInfo adInfoJ = d.j(adTemplate);
        String str2 = "观看完整视频即可获取奖励";
        long jB = com.kwad.sdk.core.response.a.a.b(adInfoJ) * 1000;
        long jN = com.kwad.sdk.core.response.a.a.n(adInfoJ);
        if (jN > 0) {
            if (jB > jN) {
                str = "观看视频" + jN + "s即可获取奖励";
            } else {
                str = "观看完整视频即可获取奖励";
            }
            str2 = str;
        }
        j.a aVarA = j.a(adTemplate, str2, aVar.y, aVar.z);
        j.a(activity, adTemplate, aVarA, a(aVarA, aVar));
    }
}
