package com.kwad.sdk.core.report;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.KsAdSDKImpl;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class o extends com.kwad.sdk.core.network.a<p> {
    private ReportResultData a;

    @NonNull
    private ReportResultData a(com.kwad.sdk.core.network.c cVar) {
        if (this.a == null) {
            this.a = new ReportResultData() { // from class: com.kwad.sdk.core.report.ReportNetwork$1
                @Override // com.kwad.sdk.core.network.BaseResultData, com.kwad.sdk.core.b
                public void parseJson(@Nullable JSONObject jSONObject) {
                    super.parseJson(jSONObject);
                }
            };
        }
        if (cVar != null) {
            try {
                this.a.parseJson(new JSONObject(cVar.b));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return this.a;
    }

    private void a(p pVar) {
        try {
            List<String> listJ = pVar.j();
            Iterator<String> it = listJ.iterator();
            while (it.hasNext()) {
                KsAdSDKImpl.get().getProxyForHttp().doGetWithoutResponse(it.next(), null);
            }
            d.a(pVar.i(), listJ);
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.kwad.sdk.core.network.a
    public void a(p pVar, com.kwad.sdk.core.network.c cVar) {
    }

    @Override // com.kwad.sdk.core.network.a
    public void d() {
        if (com.ksad.download.c.b.a(KsAdSDKImpl.get().getContext())) {
            super.d();
        } else {
            com.kwad.sdk.core.d.a.e("ReportNetwork", "no network while report log");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006c  */
    @Override // com.kwad.sdk.core.network.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void f() {
        /*
            r7 = this;
            com.kwad.sdk.core.network.g r0 = r7.b()
            com.kwad.sdk.core.report.p r0 = (com.kwad.sdk.core.report.p) r0
            r1 = 0
            java.lang.String r2 = r0.a()     // Catch: java.lang.Exception -> L48
            com.kwad.sdk.KsAdSDKImpl r3 = com.kwad.sdk.KsAdSDKImpl.get()     // Catch: java.lang.Exception -> L43
            com.kwad.sdk.export.proxy.AdHttpProxy r3 = r3.getProxyForHttp()     // Catch: java.lang.Exception -> L43
            org.json.JSONObject r4 = r0.e()     // Catch: java.lang.Exception -> L43
            com.kwad.sdk.core.network.c r3 = r3.doPost(r2, r1, r4)     // Catch: java.lang.Exception -> L43
            if (r3 == 0) goto L3e
            int r1 = r3.a     // Catch: java.lang.Exception -> L3c
            r4 = 200(0xc8, float:2.8E-43)
            if (r1 != r4) goto L3e
            java.lang.String r1 = "ReportNetwork"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L3c
            r4.<init>()     // Catch: java.lang.Exception -> L3c
            java.lang.String r5 = "report success actionType:"
            r4.append(r5)     // Catch: java.lang.Exception -> L3c
            int r5 = r0.b     // Catch: java.lang.Exception -> L3c
            r4.append(r5)     // Catch: java.lang.Exception -> L3c
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Exception -> L3c
        L38:
            com.kwad.sdk.core.d.a.a(r1, r4)     // Catch: java.lang.Exception -> L3c
            goto L4f
        L3c:
            r1 = move-exception
            goto L4c
        L3e:
            java.lang.String r1 = "ReportNetwork"
            java.lang.String r4 = "report fail result is null"
            goto L38
        L43:
            r3 = move-exception
            r6 = r3
            r3 = r1
            r1 = r6
            goto L4c
        L48:
            r2 = move-exception
            r3 = r1
            r1 = r2
            r2 = r3
        L4c:
            com.kwad.sdk.core.d.a.a(r1)
        L4f:
            com.kwad.sdk.core.report.ReportResultData r1 = r7.a(r3)
            boolean r3 = r1.isCheatingFlow()
            if (r3 == 0) goto L64
            com.kwad.sdk.core.response.model.AdTemplate r3 = r0.i()
            boolean r4 = r1.isCheatingFlow()
            r3.setCheatingFlow(r4)
        L64:
            com.kwad.sdk.core.response.model.AdTemplate r3 = r0.i()
            boolean r3 = r3.mCheatingFlow
            if (r3 != 0) goto L6f
            r7.a(r0)
        L6f:
            java.lang.Boolean r0 = com.kwad.sdk.b.c
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto La9
            boolean r0 = r1.isResultOk()
            if (r0 == 0) goto L7e
            goto La9
        L7e:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "请求返回失败 code:"
            r3.append(r4)
            int r4 = r1.result
            r3.append(r4)
            java.lang.String r4 = ", errorMsg:"
            r3.append(r4)
            java.lang.String r1 = r1.errorMsg
            r3.append(r1)
            java.lang.String r1 = "\n url="
            r3.append(r1)
            r3.append(r2)
            java.lang.String r1 = r3.toString()
            r0.<init>(r1)
            throw r0
        La9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.core.report.o.f():void");
    }
}
