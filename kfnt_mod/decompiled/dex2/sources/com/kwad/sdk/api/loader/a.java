package com.kwad.sdk.api.loader;

import com.heytap.mcssdk.mode.CommandMessage;
import java.io.File;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class a {

    /* renamed from: com.kwad.sdk.api.loader.a$a, reason: collision with other inner class name */
    static class C0023a {
        int a;
        String b;
        String c;
        long d;
        String e;
        transient File f;

        C0023a() {
        }

        public void a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return;
            }
            this.a = jSONObject.optInt("dynamicType");
            this.b = jSONObject.optString("dynamicUrl");
            this.c = jSONObject.optString("md5");
            this.d = jSONObject.optLong("interval");
            this.e = jSONObject.optString(CommandMessage.SDK_VERSION);
        }

        public boolean a() {
            return this.a == 1;
        }

        public boolean b() {
            return this.a == -1;
        }
    }

    static class b {
        long a;
        String b;
        C0023a c;

        b() {
        }

        public void a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return;
            }
            this.a = jSONObject.optLong("result");
            this.b = jSONObject.optString("errorMsg");
            this.c = new C0023a();
            this.c.a(jSONObject.optJSONObject("data"));
        }

        boolean a() {
            return this.a == 1 && this.c != null;
        }
    }
}
