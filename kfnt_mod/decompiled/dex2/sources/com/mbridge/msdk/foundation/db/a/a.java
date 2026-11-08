package com.mbridge.msdk.foundation.db.a;

import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.db.o;
import com.mbridge.msdk.foundation.same.a.b;
import java.util.Collection;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ReplaceTempDaoMiddle.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    private o a;
    private b b;

    private a() {
        this.b = new b(1000);
        this.a = o.a(i.a(com.mbridge.msdk.foundation.controller.a.f().j()));
        a(this.a.a(), false);
    }

    /* compiled from: ReplaceTempDaoMiddle.java */
    /* renamed from: com.mbridge.msdk.foundation.db.a.a$a, reason: collision with other inner class name */
    private static class C0110a {
        private static a a = new a();
    }

    public static a a() {
        return C0110a.a;
    }

    public final JSONObject a(String str) {
        JSONObject jSONObjectB = this.b.b(str);
        if (jSONObjectB != null) {
            return jSONObjectB;
        }
        JSONObject jSONObjectA = this.a.a(str);
        if (jSONObjectA != null) {
            this.b.a(str, jSONObjectA);
        }
        return jSONObjectA;
    }

    public final void a(JSONObject jSONObject, boolean z) {
        if (jSONObject != null) {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(next);
                this.b.a(next, jSONObjectOptJSONObject);
                if (z) {
                    this.a.a(next, jSONObjectOptJSONObject);
                }
            }
        }
    }

    public final JSONArray b() {
        return new JSONArray((Collection) this.b.a());
    }
}
