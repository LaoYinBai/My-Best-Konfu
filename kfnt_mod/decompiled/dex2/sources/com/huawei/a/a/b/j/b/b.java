package com.huawei.a.a.b.j.b;

import android.content.Context;
import com.huawei.a.a.b.k.e;
import com.mbridge.msdk.playercommon.exoplayer2.source.ExtractorMediaSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b implements com.huawei.a.a.b.j.c.a {
    private Context a = com.huawei.a.a.a.b.j();
    private String b;
    private JSONObject c;
    private String d;
    private String e;
    private String f;
    private String g;
    private Boolean h;

    public b(String str, JSONObject jSONObject, String str2, String str3, long j) {
        this.b = str;
        this.c = jSONObject;
        this.d = str2;
        this.e = str3;
        this.f = String.valueOf(j);
        if (com.huawei.a.a.a.a.e(str2, "oper")) {
            com.huawei.a.a.b.h.b bVarA = com.huawei.a.a.b.h.a.a().a(str2, j);
            this.g = bVarA.a();
            this.h = Boolean.valueOf(bVarA.b());
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        JSONArray jSONArray;
        com.huawei.a.a.b.e.a.b("EventRecordTask", "Begin to run EventRecordTask...");
        int iH = com.huawei.a.a.a.b.h();
        int iH2 = com.huawei.a.a.a.c.h(this.d, this.e);
        if (com.huawei.a.a.b.k.b.a(this.a, "stat_v2_1", iH * ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES)) {
            com.huawei.a.a.b.e.a.b("hmsSdk", "stat sp file reach max limited size, discard new event");
            com.huawei.a.a.b.j.a.a.a().a("", "alltype");
            return;
        }
        com.huawei.a.a.b.c.b.d dVar = new com.huawei.a.a.b.c.b.d();
        dVar.b(this.b);
        dVar.c(this.c.toString());
        dVar.a(this.e);
        dVar.d(this.f);
        dVar.e(this.g);
        dVar.f(this.h == null ? null : String.valueOf(this.h));
        try {
            JSONObject jSONObjectD = dVar.d();
            String strA = e.a(this.d, this.e);
            try {
                jSONArray = new JSONArray(com.huawei.a.a.b.i.a.b(this.a, "stat_v2_1", strA, ""));
            } catch (JSONException unused) {
                com.huawei.a.a.b.e.a.b("EventRecordTask", "Cached data corrupted: stat_v2_1");
                jSONArray = new JSONArray();
            }
            jSONArray.put(jSONObjectD);
            com.huawei.a.a.b.i.a.a(this.a, "stat_v2_1", strA, jSONArray.toString());
            if (jSONArray.toString().length() > iH2 * 1024) {
                com.huawei.a.a.b.j.a.a.a().a(this.d, this.e);
            }
        } catch (JSONException unused2) {
            com.huawei.a.a.b.e.a.c("EventRecordTask", "eventRecord toJson error! The record failed.");
        }
    }
}
