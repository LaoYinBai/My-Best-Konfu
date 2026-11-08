package com.kwad.sdk.core.video.kwai.kwai;

import android.content.Context;
import com.kwad.sdk.core.report.g;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends com.kwad.sdk.core.report.b<c, b> {
    private static a a = new a();
    private static volatile boolean b = false;

    private a() {
    }

    public static void a(final String str, final String str2) {
        a.a(new g<c>() { // from class: com.kwad.sdk.core.video.kwai.kwai.a.1
            @Override // com.kwad.sdk.core.report.g
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public c a() {
                return new c(str, str2);
            }
        });
    }

    public static a c() {
        return a;
    }

    @Override // com.kwad.sdk.core.report.b
    public void a(Context context) {
        if (b) {
            return;
        }
        super.a(context);
        b = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kwad.sdk.core.report.b
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public b a(List list, boolean z) {
        return new b(list);
    }
}
