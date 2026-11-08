package com.kwad.sdk.reward.b.a;

import android.content.Context;
import androidx.annotation.Nullable;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.ag;
import com.kwad.sdk.utils.t;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends com.kwad.sdk.reward.b.a {
    private c b = new c();
    private C0067a c = new C0067a();
    private final b d = new b(com.kwad.sdk.core.config.b.az());

    /* renamed from: com.kwad.sdk.reward.b.a.a$a, reason: collision with other inner class name */
    static class C0067a extends com.kwad.sdk.reward.b.a {
        public C0067a() {
            this.a = "安装应用";
        }
    }

    static class b extends com.kwad.sdk.reward.b.a {
        public b(int i) {
            this.a = String.format("体验应用%s秒", i + "");
        }
    }

    static class c extends com.kwad.sdk.reward.b.a {
        public c() {
            this.a = "观看视频";
        }
    }

    public static void a(a aVar, Context context, AdTemplate adTemplate) {
        if (ag.a(context, com.kwad.sdk.core.response.a.a.v(d.j(adTemplate)))) {
            aVar.h();
        } else {
            aVar.i();
        }
    }

    private void n() {
        if (this.b.d() && this.c.d() && this.d.d()) {
            a();
        } else {
            b();
        }
    }

    @Override // com.kwad.sdk.reward.b.a, com.kwad.sdk.reward.b.b
    public boolean d() {
        return this.b.d() && this.c.d() && this.d.d();
    }

    public void g() {
        com.kwad.sdk.core.d.a.a("LaunchAppTask", "markWatchVideoCompleted");
        this.b.a();
        n();
    }

    public void h() {
        com.kwad.sdk.core.d.a.a("LaunchAppTask", "markInstallCompleted");
        this.c.a();
        n();
    }

    public void i() {
        com.kwad.sdk.core.d.a.a("LaunchAppTask", "markInstallUncompleted");
        this.c.b();
        n();
    }

    public void j() {
        com.kwad.sdk.core.d.a.a("LaunchAppTask", "markUseAppCompleted");
        this.d.a();
        n();
    }

    public boolean k() {
        com.kwad.sdk.core.d.a.a("LaunchAppTask", "isInstallCompleted");
        return this.c.d();
    }

    public int l() {
        Iterator<com.kwad.sdk.reward.b.b> it = m().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (!it.next().d()) {
                i++;
            }
        }
        return i;
    }

    public List<com.kwad.sdk.reward.b.b> m() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.b);
        arrayList.add(this.c);
        arrayList.add(this.d);
        return arrayList;
    }

    @Override // com.kwad.sdk.reward.b.a, com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) {
        try {
            this.b.parseJson(jSONObject.optJSONObject("mWatchVideoTask"));
            this.c.parseJson(jSONObject.optJSONObject("mInstallAppTask"));
            this.d.parseJson(jSONObject.optJSONObject("mUseAppTask"));
        } catch (Throwable unused) {
        }
    }

    @Override // com.kwad.sdk.reward.b.a, com.kwad.sdk.core.b
    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        t.a(jSONObject, "mWatchVideoTask", this.b);
        t.a(jSONObject, "mInstallAppTask", this.c);
        t.a(jSONObject, "mUseAppTask", this.d);
        return jSONObject;
    }
}
