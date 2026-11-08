package com.kwad.sdk.crash.report.request;

import androidx.annotation.NonNull;
import com.kwad.sdk.core.network.i;
import com.kwad.sdk.core.network.j;
import com.kwad.sdk.crash.report.ReportEvent;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    public void a(final List<ReportEvent> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        new i<a, CrashReportResult>() { // from class: com.kwad.sdk.crash.report.request.b.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.kwad.sdk.core.network.i
            @NonNull
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public CrashReportResult b(String str) throws Throwable {
                JSONObject jSONObject = new JSONObject(str);
                CrashReportResult crashReportResult = new CrashReportResult();
                crashReportResult.parseJson(jSONObject);
                return crashReportResult;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.kwad.sdk.core.network.a
            @NonNull
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public a b() {
                return new a(list);
            }

            @Override // com.kwad.sdk.core.network.i
            protected boolean c() {
                return false;
            }
        }.a(new j<a, CrashReportResult>() { // from class: com.kwad.sdk.crash.report.request.b.2
            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull a aVar, int i, String str) {
            }

            @Override // com.kwad.sdk.core.network.j, com.kwad.sdk.core.network.h
            public void a(@NonNull a aVar, @NonNull CrashReportResult crashReportResult) {
            }
        });
    }
}
