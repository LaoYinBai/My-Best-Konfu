package com.kwad.sdk.collector.a;

import com.heytap.mcssdk.mode.CommandMessage;
import com.kwad.sdk.core.network.d;
import com.kwad.sdk.utils.t;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends d {
    private C0026a b;

    /* renamed from: com.kwad.sdk.collector.a.a$a, reason: collision with other inner class name */
    static class C0026a {
        private List<String> a;

        public C0026a(List<String> list) {
            this.a = list;
        }

        public JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            t.a(jSONObject, "packageName", this.a);
            return jSONObject;
        }
    }

    public a(List<String> list) {
        this.b = new C0026a(list);
        a("targetAppInfo", this.b.a());
        b(CommandMessage.SDK_VERSION, "3.3.17");
        a("sdkVersionCode", 3031700);
    }

    @Override // com.kwad.sdk.core.network.b, com.kwad.sdk.core.network.g
    public String a() {
        return com.kwad.sdk.d.l();
    }
}
