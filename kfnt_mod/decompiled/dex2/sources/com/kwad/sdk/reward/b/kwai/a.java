package com.kwad.sdk.reward.b.kwai;

import androidx.annotation.Nullable;
import com.kwad.sdk.utils.t;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends com.kwad.sdk.reward.b.a {
    private b b = new b();
    private final C0069a c = new C0069a(com.kwad.sdk.core.config.b.aA());

    /* renamed from: com.kwad.sdk.reward.b.kwai.a$a, reason: collision with other inner class name */
    static class C0069a extends com.kwad.sdk.reward.b.a {
        public C0069a(int i) {
            this.a = String.format("浏览详情页%s秒", i + "");
        }
    }

    static class b extends com.kwad.sdk.reward.b.a {
        public b() {
            this.a = "观看视频";
        }
    }

    private void k() {
        if (this.b.d() && this.c.d()) {
            a();
        } else {
            b();
        }
    }

    @Override // com.kwad.sdk.reward.b.a, com.kwad.sdk.reward.b.b
    public boolean d() {
        return this.b.d() && this.c.d();
    }

    public void g() {
        com.kwad.sdk.core.d.a.a("LandPageOpenTask", "markWatchVideoCompleted");
        this.b.a();
        k();
    }

    public void h() {
        com.kwad.sdk.core.d.a.a("LandPageOpenTask", "markOpenNsCompleted");
        this.c.a();
        k();
    }

    public int i() {
        Iterator<com.kwad.sdk.reward.b.b> it = j().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (!it.next().d()) {
                i++;
            }
        }
        return i;
    }

    public List<com.kwad.sdk.reward.b.b> j() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.b);
        arrayList.add(this.c);
        return arrayList;
    }

    @Override // com.kwad.sdk.reward.b.a, com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) {
        try {
            this.b.parseJson(jSONObject.optJSONObject("mWatchVideoTask"));
            this.c.parseJson(jSONObject.optJSONObject("mOpenNsTask"));
        } catch (Throwable unused) {
        }
    }

    @Override // com.kwad.sdk.reward.b.a, com.kwad.sdk.core.b
    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        t.a(jSONObject, "mWatchVideoTask", this.b);
        t.a(jSONObject, "mOpenNsTask", this.c);
        return jSONObject;
    }
}
