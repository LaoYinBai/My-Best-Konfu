package com.apm.insight.runtime.a;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.apm.insight.CrashType;
import com.apm.insight.entity.Header;
import com.apm.insight.runtime.a.c;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    @SuppressLint({"StaticFieldLeak"})
    private static volatile f f3016a;

    /* renamed from: b, reason: collision with root package name */
    private Context f3017b;

    /* renamed from: c, reason: collision with root package name */
    private Map<CrashType, c> f3018c = new HashMap();
    private b d;
    private d e;

    private f(@NonNull Context context) {
        this.f3017b = context;
        try {
            this.d = b.d();
            this.e = new d(this.f3017b);
        } catch (Throwable th) {
            com.apm.insight.b.a().a("NPTH_CATCH", th);
        }
    }

    @Nullable
    private c a(CrashType crashType) {
        c jVar = this.f3018c.get(crashType);
        if (jVar != null) {
            return jVar;
        }
        switch (crashType) {
            case JAVA:
                jVar = new j(this.f3017b, this.d, this.e);
                break;
            case LAUNCH:
                jVar = new k(this.f3017b, this.d, this.e);
                break;
            case NATIVE:
                jVar = new l(this.f3017b, this.d, this.e);
                break;
            case ANR:
                jVar = new a(this.f3017b, this.d, this.e);
                break;
            case DART:
                jVar = new h(this.f3017b, this.d, this.e);
                break;
            case CUSTOM_JAVA:
                jVar = new g(this.f3017b, this.d, this.e);
                break;
            case BLOCK:
                jVar = new e(this.f3017b, this.d, this.e);
                break;
            case ENSURE:
                jVar = new i(this.f3017b, this.d, this.e);
                break;
        }
        if (jVar != null) {
            this.f3018c.put(crashType, jVar);
        }
        return jVar;
    }

    public static f a() {
        if (f3016a == null) {
            Context contextG = com.apm.insight.h.g();
            if (contextG == null) {
                throw new IllegalArgumentException("NpthBus not init");
            }
            f3016a = new f(contextG);
        }
        return f3016a;
    }

    public com.apm.insight.entity.a a(CrashType crashType, com.apm.insight.entity.a aVar) {
        c cVarA;
        return (crashType == null || (cVarA = a(crashType)) == null) ? aVar : cVarA.a(aVar, null, false);
    }

    public com.apm.insight.entity.a a(CrashType crashType, com.apm.insight.entity.a aVar, @Nullable c.a aVar2, boolean z) {
        c cVarA;
        return (crashType == null || (cVarA = a(crashType)) == null) ? aVar : cVarA.a(aVar, aVar2, z);
    }

    public com.apm.insight.entity.a a(List<com.apm.insight.entity.a> list, JSONArray jSONArray) throws JSONException {
        if (list == null || list.isEmpty()) {
            return null;
        }
        com.apm.insight.entity.a aVar = new com.apm.insight.entity.a();
        JSONArray jSONArray2 = new JSONArray();
        Iterator<com.apm.insight.entity.a> it = list.iterator();
        while (it.hasNext()) {
            jSONArray2.put(it.next().h());
        }
        aVar.a("data", (Object) jSONArray2);
        aVar.a("all_data", (Object) jSONArray);
        Header headerA = Header.a(this.f3017b);
        Header.a(headerA);
        headerA.c();
        headerA.d();
        headerA.e();
        Header.b(headerA);
        aVar.a(headerA);
        return aVar;
    }
}
