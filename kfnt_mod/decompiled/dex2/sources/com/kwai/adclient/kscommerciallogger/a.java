package com.kwai.adclient.kscommerciallogger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private com.kwai.adclient.kscommerciallogger.kwai.a a;
    private com.kwai.adclient.kscommerciallogger.kwai.b b;
    private JSONObject c;
    private boolean d;

    /* renamed from: com.kwai.adclient.kscommerciallogger.a$a, reason: collision with other inner class name */
    private static final class C0087a {
        private static a a;

        public static a a() {
            if (a == null) {
                a = new a();
            }
            return a;
        }
    }

    private a() {
        this.d = false;
    }

    public static a a() {
        return C0087a.a();
    }

    private void b(@NonNull com.kwai.adclient.kscommerciallogger.model.a aVar) {
        if (this.a != null) {
            com.kwai.adclient.kscommerciallogger.kwai.a aVar2 = this.a;
            String strD = aVar.d();
            String str = aVar.b() == null ? "" : aVar.b().value;
            Object[] objArr = new Object[5];
            objArr[0] = aVar.c() == null ? "" : aVar.c().value;
            objArr[1] = aVar.e() == null ? "" : aVar.e().a();
            objArr[2] = aVar.h();
            objArr[3] = b.a(aVar.f());
            objArr[4] = b.a(aVar.g());
            aVar2.a(strD, str, objArr);
        }
    }

    public void a(@NonNull com.kwai.adclient.kscommerciallogger.kwai.a aVar, @NonNull com.kwai.adclient.kscommerciallogger.kwai.b bVar, @Nullable JSONObject jSONObject, boolean z) {
        this.a = aVar;
        this.b = bVar;
        this.c = jSONObject;
        this.d = z;
    }

    public void a(com.kwai.adclient.kscommerciallogger.model.a aVar) {
        b(aVar);
        if (this.b != null) {
            this.b.a(aVar.a(), aVar.toString());
        }
    }

    public JSONObject b() {
        return this.c;
    }

    public boolean c() {
        return this.d;
    }
}
