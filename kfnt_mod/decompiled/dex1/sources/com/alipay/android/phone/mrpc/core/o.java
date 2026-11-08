package com.alipay.android.phone.mrpc.core;

import com.bytedance.sdk.account.utils.UrlBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class o extends t {

    /* renamed from: b, reason: collision with root package name */
    private String f2467b;

    /* renamed from: c, reason: collision with root package name */
    private byte[] f2468c;
    private boolean g;
    private ArrayList<Header> e = new ArrayList<>();
    private Map<String, String> f = new HashMap();
    private String d = UrlBuilder.CONTENT_TYPE;

    public o(String str) {
        this.f2467b = str;
    }

    public final String a() {
        return this.f2467b;
    }

    public final void a(String str) {
        this.d = str;
    }

    public final void a(String str, String str2) {
        if (this.f == null) {
            this.f = new HashMap();
        }
        this.f.put(str, str2);
    }

    public final void a(Header header) {
        this.e.add(header);
    }

    public final void a(boolean z) {
        this.g = z;
    }

    public final void a(byte[] bArr) {
        this.f2468c = bArr;
    }

    public final String b(String str) {
        if (this.f == null) {
            return null;
        }
        return this.f.get(str);
    }

    public final byte[] b() {
        return this.f2468c;
    }

    public final String c() {
        return this.d;
    }

    public final ArrayList<Header> d() {
        return this.e;
    }

    public final boolean e() {
        return this.g;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        o oVar = (o) obj;
        if (this.f2468c == null) {
            if (oVar.f2468c != null) {
                return false;
            }
        } else if (!this.f2468c.equals(oVar.f2468c)) {
            return false;
        }
        if (this.f2467b == null) {
            if (oVar.f2467b != null) {
                return false;
            }
        } else if (!this.f2467b.equals(oVar.f2467b)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return (((this.f == null || !this.f.containsKey("id")) ? 1 : this.f.get("id").hashCode() + 31) * 31) + (this.f2467b == null ? 0 : this.f2467b.hashCode());
    }

    public final String toString() {
        return String.format("Url : %s,HttpHeader: %s", this.f2467b, this.e);
    }
}
