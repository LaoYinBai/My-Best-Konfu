package com.xiaomi.push;

import android.os.Bundle;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class gf extends gg {
    private boolean a;
    private String b;

    /* renamed from: b, reason: collision with other field name */
    private boolean f425b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;

    public gf() {
        this.b = null;
        this.c = null;
        this.a = false;
        this.i = "";
        this.j = "";
        this.k = "";
        this.l = "";
        this.f425b = false;
    }

    public gf(Bundle bundle) {
        super(bundle);
        this.b = null;
        this.c = null;
        this.a = false;
        this.i = "";
        this.j = "";
        this.k = "";
        this.l = "";
        this.f425b = false;
        this.b = bundle.getString("ext_msg_type");
        this.d = bundle.getString("ext_msg_lang");
        this.c = bundle.getString("ext_msg_thread");
        this.e = bundle.getString("ext_msg_sub");
        this.f = bundle.getString("ext_msg_body");
        this.g = bundle.getString("ext_body_encode");
        this.h = bundle.getString("ext_msg_appid");
        this.a = bundle.getBoolean("ext_msg_trans", false);
        this.f425b = bundle.getBoolean("ext_msg_encrypt", false);
        this.i = bundle.getString("ext_msg_seq");
        this.j = bundle.getString("ext_msg_mseq");
        this.k = bundle.getString("ext_msg_fseq");
        this.l = bundle.getString("ext_msg_status");
    }

    @Override // com.xiaomi.push.gg
    public Bundle a() {
        Bundle bundleA = super.a();
        if (!TextUtils.isEmpty(this.b)) {
            bundleA.putString("ext_msg_type", this.b);
        }
        if (this.d != null) {
            bundleA.putString("ext_msg_lang", this.d);
        }
        if (this.e != null) {
            bundleA.putString("ext_msg_sub", this.e);
        }
        if (this.f != null) {
            bundleA.putString("ext_msg_body", this.f);
        }
        if (!TextUtils.isEmpty(this.g)) {
            bundleA.putString("ext_body_encode", this.g);
        }
        if (this.c != null) {
            bundleA.putString("ext_msg_thread", this.c);
        }
        if (this.h != null) {
            bundleA.putString("ext_msg_appid", this.h);
        }
        if (this.a) {
            bundleA.putBoolean("ext_msg_trans", true);
        }
        if (!TextUtils.isEmpty(this.i)) {
            bundleA.putString("ext_msg_seq", this.i);
        }
        if (!TextUtils.isEmpty(this.j)) {
            bundleA.putString("ext_msg_mseq", this.j);
        }
        if (!TextUtils.isEmpty(this.k)) {
            bundleA.putString("ext_msg_fseq", this.k);
        }
        if (this.f425b) {
            bundleA.putBoolean("ext_msg_encrypt", true);
        }
        if (!TextUtils.isEmpty(this.l)) {
            bundleA.putString("ext_msg_status", this.l);
        }
        return bundleA;
    }

    @Override // com.xiaomi.push.gg
    /* renamed from: a */
    public String mo354a() {
        gk gkVarA;
        StringBuilder sb = new StringBuilder();
        sb.append("<message");
        if (p() != null) {
            sb.append(" xmlns=\"");
            sb.append(p());
            sb.append("\"");
        }
        if (this.d != null) {
            sb.append(" xml:lang=\"");
            sb.append(h());
            sb.append("\"");
        }
        if (j() != null) {
            sb.append(" id=\"");
            sb.append(j());
            sb.append("\"");
        }
        if (l() != null) {
            sb.append(" to=\"");
            sb.append(gr.a(l()));
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(d())) {
            sb.append(" seq=\"");
            sb.append(d());
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(e())) {
            sb.append(" mseq=\"");
            sb.append(e());
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(f())) {
            sb.append(" fseq=\"");
            sb.append(f());
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(g())) {
            sb.append(" status=\"");
            sb.append(g());
            sb.append("\"");
        }
        if (m() != null) {
            sb.append(" from=\"");
            sb.append(gr.a(m()));
            sb.append("\"");
        }
        if (k() != null) {
            sb.append(" chid=\"");
            sb.append(gr.a(k()));
            sb.append("\"");
        }
        if (this.a) {
            sb.append(" transient=\"true\"");
        }
        if (!TextUtils.isEmpty(this.h)) {
            sb.append(" appid=\"");
            sb.append(c());
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(this.b)) {
            sb.append(" type=\"");
            sb.append(this.b);
            sb.append("\"");
        }
        if (this.f425b) {
            sb.append(" s=\"1\"");
        }
        sb.append(">");
        if (this.e != null) {
            sb.append("<subject>");
            sb.append(gr.a(this.e));
            sb.append("</subject>");
        }
        if (this.f != null) {
            sb.append("<body");
            if (!TextUtils.isEmpty(this.g)) {
                sb.append(" encode=\"");
                sb.append(this.g);
                sb.append("\"");
            }
            sb.append(">");
            sb.append(gr.a(this.f));
            sb.append("</body>");
        }
        if (this.c != null) {
            sb.append("<thread>");
            sb.append(this.c);
            sb.append("</thread>");
        }
        if ("error".equalsIgnoreCase(this.b) && (gkVarA = a()) != null) {
            sb.append(gkVarA.m358a());
        }
        sb.append(o());
        sb.append("</message>");
        return sb.toString();
    }

    public void a(String str) {
        this.h = str;
    }

    public void a(String str, String str2) {
        this.f = str;
        this.g = str2;
    }

    public void a(boolean z) {
        this.a = z;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.i = str;
    }

    public void b(boolean z) {
        this.f425b = z;
    }

    public String c() {
        return this.h;
    }

    public void c(String str) {
        this.j = str;
    }

    public String d() {
        return this.i;
    }

    public void d(String str) {
        this.k = str;
    }

    public String e() {
        return this.j;
    }

    public void e(String str) {
        this.l = str;
    }

    @Override // com.xiaomi.push.gg
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        gf gfVar = (gf) obj;
        if (!super.equals(gfVar)) {
            return false;
        }
        if (this.f == null ? gfVar.f != null : !this.f.equals(gfVar.f)) {
            return false;
        }
        if (this.d == null ? gfVar.d != null : !this.d.equals(gfVar.d)) {
            return false;
        }
        if (this.e == null ? gfVar.e != null : !this.e.equals(gfVar.e)) {
            return false;
        }
        if (this.c == null ? gfVar.c == null : this.c.equals(gfVar.c)) {
            return this.b == gfVar.b;
        }
        return false;
    }

    public String f() {
        return this.k;
    }

    public void f(String str) {
        this.b = str;
    }

    public String g() {
        return this.l;
    }

    public void g(String str) {
        this.e = str;
    }

    public String h() {
        return this.d;
    }

    public void h(String str) {
        this.f = str;
    }

    @Override // com.xiaomi.push.gg
    public int hashCode() {
        return ((((((((this.b != null ? this.b.hashCode() : 0) * 31) + (this.f != null ? this.f.hashCode() : 0)) * 31) + (this.c != null ? this.c.hashCode() : 0)) * 31) + (this.d != null ? this.d.hashCode() : 0)) * 31) + (this.e != null ? this.e.hashCode() : 0);
    }

    public void i(String str) {
        this.c = str;
    }

    public void j(String str) {
        this.d = str;
    }
}
