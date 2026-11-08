package com.kwad.sdk.collector;

import android.content.Context;
import androidx.annotation.NonNull;
import com.kwad.sdk.collector.d;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {

    public interface a {
        void a(int i, String str);

        void a(AppStatusRules appStatusRules);
    }

    public static void a(final Context context, final a aVar) {
        if (context == null) {
            return;
        }
        if (com.kwad.sdk.core.config.b.ai()) {
            d.a(context, new d.a() { // from class: com.kwad.sdk.collector.c.1
                @Override // com.kwad.sdk.collector.d.a
                public void a() {
                    c.b(context, aVar);
                }

                @Override // com.kwad.sdk.collector.d.a
                public void a(String str) {
                    com.kwad.sdk.core.d.a.e("AppStatusFetchConfigManager", "onLoadError: " + str);
                }
            });
        } else {
            b(context, aVar);
        }
    }

    public static void b(final Context context, final a aVar) {
        new com.kwad.sdk.core.network.i<com.kwad.sdk.collector.a.a, AppStatusRules>() { // from class: com.kwad.sdk.collector.c.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.kwad.sdk.core.network.i
            @NonNull
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public AppStatusRules b(String str) {
                return AppStatusRules.createFromJson(str);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.kwad.sdk.core.network.a
            @NonNull
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public com.kwad.sdk.collector.a.a b() {
                return new com.kwad.sdk.collector.a.a(com.kwad.sdk.utils.h.b(context));
            }
        }.a(new com.kwad.sdk.core.network.j<com.kwad.sdk.collector.a.a, AppStatusRules>() { // from class: com.kwad.sdk.collector.c.3
            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull com.kwad.sdk.collector.a.a aVar2) {
                super.a((AnonymousClass3) aVar2);
            }

            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull com.kwad.sdk.collector.a.a aVar2, int i, String str) {
                super.a((AnonymousClass3) aVar2, i, str);
                if (aVar != null) {
                    aVar.a(i, str);
                }
            }

            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull com.kwad.sdk.collector.a.a aVar2, @NonNull AppStatusRules appStatusRules) {
                if (aVar != null) {
                    aVar.a(appStatusRules);
                }
            }
        });
    }
}
