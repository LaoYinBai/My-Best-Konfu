package com.kwad.sdk.core.report;

import com.kwad.sdk.utils.t;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e extends com.kwad.sdk.core.network.d {
    public e(List<ReportAction> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<ReportAction> it = list.iterator();
        while (it.hasNext()) {
            t.a(jSONArray, it.next().a());
        }
        a("actionList", jSONArray);
    }

    @Override // com.kwad.sdk.core.network.b, com.kwad.sdk.core.network.g
    public String a() {
        return com.kwad.sdk.d.d();
    }
}
