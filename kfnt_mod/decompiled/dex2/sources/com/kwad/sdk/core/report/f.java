package com.kwad.sdk.core.report;

import android.content.Context;
import androidx.annotation.NonNull;
import com.kwad.sdk.core.config.b;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f extends b<ReportAction, e> {
    private static volatile boolean a = false;
    private static f b = new f();

    private f() {
    }

    public static void a(@NonNull ReportAction reportAction) {
        if (reportAction == null) {
            return;
        }
        if (com.kwad.sdk.b.c.booleanValue()) {
            com.kwad.sdk.core.d.a.a("BatchReporter", "report actionType:" + reportAction.i);
        }
        if (b(reportAction.i)) {
            b(reportAction);
        } else {
            c(reportAction);
        }
    }

    public static void b(final ReportAction reportAction) {
        if (reportAction == null) {
            return;
        }
        b.b(new g<ReportAction>() { // from class: com.kwad.sdk.core.report.f.2
            @Override // com.kwad.sdk.core.report.g
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public ReportAction a() {
                return reportAction.b();
            }
        });
    }

    private static boolean b(long j) {
        return b.a.aU.a(j);
    }

    public static f c() {
        return b;
    }

    private static void c(final ReportAction reportAction) {
        if (reportAction == null) {
            return;
        }
        b.a(new g<ReportAction>() { // from class: com.kwad.sdk.core.report.f.1
            @Override // com.kwad.sdk.core.report.g
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public ReportAction a() {
                return reportAction.b();
            }
        });
    }

    @Override // com.kwad.sdk.core.report.b
    public void a(Context context) {
        if (a) {
            return;
        }
        super.a(context);
        n.a(context);
        n.a();
        int iA = com.kwad.sdk.core.config.item.c.a(context);
        com.kwad.sdk.core.d.a.a("BatchReporter", "cache type = " + iA);
        if (iA == 2) {
            a(l.a(context));
        }
        a = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kwad.sdk.core.report.b
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public e a(List<ReportAction> list, boolean z) {
        return z ? new com.kwad.sdk.core.report.a.a(list) : new e(list);
    }
}
