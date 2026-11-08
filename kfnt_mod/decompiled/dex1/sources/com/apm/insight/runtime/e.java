package com.apm.insight.runtime;

import androidx.annotation.Nullable;
import com.bytedance.common.utility.date.DateDef;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private static HashMap<String, e> f3029a = new HashMap<>();

    /* renamed from: b, reason: collision with root package name */
    private JSONObject f3030b = null;

    /* renamed from: c, reason: collision with root package name */
    private JSONObject f3031c = null;
    private boolean d = false;
    private String e;

    public e(JSONObject jSONObject, String str) {
        this.e = str;
        a(jSONObject);
        f3029a.put(this.e, this);
        com.apm.insight.l.q.a((Object) ("after update aid " + str));
    }

    public static void a(String str, JSONObject jSONObject) {
        e eVar = f3029a.get(str);
        if (eVar != null) {
            eVar.a(jSONObject);
        } else {
            new e(jSONObject, str);
        }
    }

    private void a(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject;
        this.f3030b = jSONObject;
        if (jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject("error_module")) == null) {
            return;
        }
        this.d = jSONObjectOptJSONObject.optInt("switcher") == 1;
    }

    public static boolean b(String str) {
        return f3029a.get(str) != null;
    }

    @Nullable
    public static JSONObject c(String str) {
        e eVar = f3029a.get(str);
        if (eVar != null) {
            return eVar.a();
        }
        return null;
    }

    public static e d(String str) {
        return f3029a.get(str);
    }

    public static long e(String str) {
        e eVar = f3029a.get(str);
        if (eVar == null) {
            return DateDef.HOUR;
        }
        try {
            return 1000 * Long.decode(com.apm.insight.l.l.b(eVar.a(), "over_all", "get_settings_interval")).longValue();
        } catch (Throwable unused) {
            return DateDef.HOUR;
        }
    }

    public static boolean f(String str) {
        e eVar = f3029a.get(str);
        return eVar != null && eVar.b();
    }

    public static boolean g(String str) {
        e eVar = f3029a.get(str);
        return eVar != null && eVar.c();
    }

    public static boolean h(String str) {
        e eVar = f3029a.get(str);
        return eVar != null && eVar.d();
    }

    @Nullable
    public JSONObject a() {
        return this.f3030b;
    }

    public boolean a(String str) {
        if (this.f3030b == null) {
            return false;
        }
        return this.d;
    }

    public boolean b() {
        return this.f3030b != null && 1 == com.apm.insight.l.l.a(this.f3030b, 0, "crash_module", "switcher");
    }

    public boolean c() {
        return this.f3030b != null && 1 == com.apm.insight.l.l.a(this.f3030b, 0, "crash_module", "switcher");
    }

    public boolean d() {
        return this.f3030b != null && 1 == com.apm.insight.l.l.a(this.f3030b, 0, "crash_module", "switcher");
    }
}
