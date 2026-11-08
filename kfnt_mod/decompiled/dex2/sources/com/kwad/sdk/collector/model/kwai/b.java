package com.kwad.sdk.collector.model.kwai;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.utils.ae;
import com.kwad.sdk.utils.t;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b implements com.kwad.sdk.collector.model.b<b> {
    private static SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
    private long b;
    private String c;
    private String d;
    private long e;

    public b(long j, String str, String str2) {
        this.b = -1L;
        this.b = j;
        this.c = str;
        this.d = str2;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(b bVar) {
        if (bVar == null) {
            return 1;
        }
        long jC = this.e - com.kwad.sdk.collector.model.c.c(bVar);
        if (jC == 0) {
            return 0;
        }
        return jC > 0 ? 1 : -1;
    }

    @NonNull
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public b clone() {
        b bVar = new b(this.b, this.c, this.d);
        com.kwad.sdk.collector.model.c.a(bVar, this.e);
        return bVar;
    }

    public String a(long j) {
        return a.format(new Date(j));
    }

    public String b() {
        return this.c;
    }

    public void b(long j) {
        this.b = j;
    }

    public String c() {
        return this.d;
    }

    public void c(long j) {
        this.e = j;
    }

    public long d() {
        return this.e;
    }

    @Override // com.kwad.sdk.collector.model.b
    public void destroy() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        if (this.b != bVar.b) {
            return false;
        }
        long j = this.b;
        if (j == 0) {
            j = 1;
        }
        if (this.e / j == bVar.e / j && ae.a(this.c, bVar.c)) {
            return ae.a(this.d, bVar.d);
        }
        return false;
    }

    public int hashCode() {
        long j = this.b;
        if (j == 0) {
            j = 1;
        }
        long j2 = this.b / j;
        return ((((this.c != null ? this.c.hashCode() : 1) * 31) + (this.d != null ? this.d.hashCode() : 1)) * 31) + ((int) (j2 ^ (j2 >>> 32)));
    }

    @Override // com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) {
    }

    @Override // com.kwad.sdk.core.b
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        t.a(jSONObject, "name", this.c);
        t.a(jSONObject, "packageName", this.d);
        t.a(jSONObject, "lastRunningTime", this.e);
        return jSONObject;
    }

    public String toString() {
        return "AppRunningInfo{packageName='" + com.kwad.sdk.collector.model.c.b(this) + "', lastRunningTime=" + a(com.kwad.sdk.collector.model.c.c(this)) + '}';
    }
}
