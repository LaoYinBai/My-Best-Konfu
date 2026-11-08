package com.kwad.sdk.reward;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.kwad.sdk.core.network.BaseResultData;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h {
    private volatile boolean a;
    private com.kwad.sdk.core.network.i<i, BaseResultData> b;

    private static class a {
        private static h a = new h();
    }

    private h() {
        this.a = false;
    }

    public static h a() {
        return a.a;
    }

    private void b(final AdTemplate adTemplate) {
        if (this.a) {
            return;
        }
        this.a = true;
        this.b = new com.kwad.sdk.core.network.i<i, BaseResultData>() { // from class: com.kwad.sdk.reward.h.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.kwad.sdk.core.network.a
            @NonNull
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public i b() {
                return new i(adTemplate);
            }

            @Override // com.kwad.sdk.core.network.i
            @NonNull
            protected BaseResultData b(String str) {
                return new BaseResultData() { // from class: com.kwad.sdk.reward.RewardCallbackVerifyHelper$1$1
                };
            }
        };
        this.b.a(new com.kwad.sdk.core.network.j<i, BaseResultData>() { // from class: com.kwad.sdk.reward.h.2
            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull i iVar, int i, String str) {
                super.a((AnonymousClass2) iVar, i, str);
                h.this.a = false;
                com.kwad.sdk.core.d.a.c("RewardCallbackVerifyHelper", "callbackUrlInfo verify failed");
            }

            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull i iVar, @NonNull BaseResultData baseResultData) {
                super.a((AnonymousClass2) iVar, (i) baseResultData);
                h.this.a = false;
                com.kwad.sdk.core.d.a.c("RewardCallbackVerifyHelper", "callbackUrlInfo verify success");
            }
        });
    }

    public void a(AdTemplate adTemplate) {
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
        if (TextUtils.isEmpty(com.kwad.sdk.core.response.a.a.ar(adInfoJ))) {
            m.a(adTemplate, adInfoJ);
        } else {
            b(adTemplate);
        }
    }

    public void b() {
        if (this.b != null) {
            this.b.e();
        }
    }
}
