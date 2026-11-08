package com.apm.insight.d;

import androidx.annotation.Nullable;
import com.apm.insight.CrashType;
import com.apm.insight.f;
import com.apm.insight.h;
import com.apm.insight.k.d;
import com.apm.insight.runtime.p;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {
    public static void a(String str) {
        a(str, null, null, null);
    }

    public static void a(String str, @Nullable Map<? extends String, ? extends String> map, @Nullable Map<String, String> map2, @Nullable f fVar) {
        a(str, map, map2, null, fVar);
    }

    public static void a(final String str, @Nullable final Map<? extends String, ? extends String> map, @Nullable final Map<String, String> map2, @Nullable final Map<String, String> map3, @Nullable final f fVar) {
        final long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            p.b().a(new Runnable() { // from class: com.apm.insight.d.a.1
                @Override // java.lang.Runnable
                public void run() {
                    boolean zA;
                    try {
                        com.apm.insight.entity.a aVarA = com.apm.insight.runtime.a.f.a().a(CrashType.DART, com.apm.insight.entity.a.a(jCurrentTimeMillis, h.g(), str));
                        if (map != null) {
                            JSONObject jSONObjectOptJSONObject = aVarA.h().optJSONObject("custom");
                            if (jSONObjectOptJSONObject == null) {
                                jSONObjectOptJSONObject = new JSONObject();
                            }
                            com.apm.insight.entity.a.a(jSONObjectOptJSONObject, (Map<? extends String, ? extends String>) map);
                            aVarA.a("custom", jSONObjectOptJSONObject);
                        }
                        if (map2 != null) {
                            JSONObject jSONObjectOptJSONObject2 = aVarA.h().optJSONObject("custom_long");
                            if (jSONObjectOptJSONObject2 == null) {
                                jSONObjectOptJSONObject2 = new JSONObject();
                            }
                            com.apm.insight.entity.a.a(jSONObjectOptJSONObject2, (Map<? extends String, ? extends String>) map2);
                            aVarA.a("custom_long", jSONObjectOptJSONObject2);
                        }
                        if (map3 != null) {
                            JSONObject jSONObjectOptJSONObject3 = aVarA.h().optJSONObject("filters");
                            if (jSONObjectOptJSONObject3 == null) {
                                jSONObjectOptJSONObject3 = new JSONObject();
                                aVarA.a("filters", jSONObjectOptJSONObject3);
                            }
                            com.apm.insight.entity.a.a(jSONObjectOptJSONObject3, (Map<? extends String, ? extends String>) map3);
                        }
                        zA = d.a().a(jCurrentTimeMillis, aVarA.h());
                    } catch (Throwable unused) {
                        zA = false;
                    }
                    if (fVar != null) {
                        try {
                            fVar.a(zA);
                        } catch (Throwable unused2) {
                        }
                    }
                }
            });
        } catch (Throwable unused) {
        }
    }
}
