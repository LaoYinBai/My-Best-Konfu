package com.mbridge.msdk.foundation.entity;

import com.mbridge.msdk.MBridgeConstans;
import java.util.List;

/* compiled from: ClickTime.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class e {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private int f;
    private String g;
    private String h;
    private String i;
    private int j;
    private int k;
    private String l;
    private int m;
    private int n;
    private String o;
    private int p;
    private String q;
    private int r;

    public e() {
    }

    public e(String str, String str2, String str3, String str4, String str5, int i, String str6, String str7, String str8, int i2, int i3, String str9, int i4, int i5, String str10, int i6, int i7, String str11) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = i;
        this.g = str6;
        this.h = str7;
        this.i = str8;
        this.j = i2;
        this.k = i3;
        this.l = str9;
        this.m = i4;
        this.n = i5;
        this.o = str10;
        this.p = i6;
        this.q = str11;
        this.r = i7;
    }

    public final void a(int i) {
        this.p = i;
    }

    public final void a(String str) {
        this.q = str;
    }

    public final void b(String str) {
        this.l = str;
    }

    public final void b(int i) {
        this.m = i;
    }

    public final void c(int i) {
        this.n = i;
    }

    public final void c(String str) {
        this.o = str;
    }

    public final void d(int i) {
        this.k = i;
    }

    public final void d(String str) {
        this.g = str;
    }

    public final void e(int i) {
        this.f = i;
    }

    public final void e(String str) {
        this.h = str;
    }

    public final void f(String str) {
        this.i = str;
    }

    public final void f(int i) {
        this.j = i;
    }

    public final void g(String str) {
        this.e = str;
    }

    public final void h(String str) {
        this.c = str;
    }

    public final void i(String str) {
        this.d = str;
    }

    public final void j(String str) {
        this.a = str;
    }

    public final void k(String str) {
        this.b = str;
    }

    public static String a(List<e> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (e eVar : list) {
            if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
                sb.append("rid=" + eVar.a);
                sb.append("&rid_n=" + eVar.b);
                sb.append("&network_type=" + eVar.p);
                sb.append("&network_str=" + eVar.q);
                sb.append("&cid=" + eVar.c);
                sb.append("&click_type=" + eVar.k);
                sb.append("&type=" + eVar.j);
                sb.append("&click_duration=" + eVar.d);
                sb.append("&key=2000013");
                sb.append("&unit_id=" + eVar.l);
                sb.append("&last_url=" + eVar.e);
                sb.append("&content=" + eVar.i);
                sb.append("&code=" + eVar.f);
                sb.append("&exception=" + eVar.g);
                sb.append("&header=" + eVar.h);
                sb.append("&landing_type=" + eVar.m);
                sb.append("&link_type=" + eVar.n);
                sb.append("&click_time=" + eVar.o + "\n");
            } else {
                sb.append("rid=" + eVar.a);
                sb.append("&rid_n=" + eVar.b);
                sb.append("&cid=" + eVar.c);
                sb.append("&click_type=" + eVar.k);
                sb.append("&type=" + eVar.j);
                sb.append("&click_duration=" + eVar.d);
                sb.append("&key=2000013");
                sb.append("&unit_id=" + eVar.l);
                sb.append("&last_url=" + eVar.e);
                sb.append("&content=" + eVar.i);
                sb.append("&code=" + eVar.f);
                sb.append("&exception=" + eVar.g);
                sb.append("&header=" + eVar.h);
                sb.append("&landing_type=" + eVar.m);
                sb.append("&link_type=" + eVar.n);
                sb.append("&click_time=" + eVar.o + "\n");
            }
        }
        return sb.toString();
    }

    public static String a(e eVar) {
        if (eVar == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String str = eVar.e;
        if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
            sb.append("rid=" + eVar.a);
            sb.append("&rid_n=" + eVar.b);
            sb.append("&network_type=" + eVar.p);
            sb.append("&network_str=" + eVar.q);
            sb.append("&click_type=" + eVar.k);
            sb.append("&type=" + eVar.j);
            sb.append("&cid=" + eVar.c);
            sb.append("&click_duration=" + eVar.d);
            sb.append("&key=2000012");
            sb.append("&unit_id=" + eVar.l);
            sb.append("&last_url=" + str);
            sb.append("&code=" + eVar.f);
            sb.append("&exception=" + eVar.g);
            sb.append("&landing_type=" + eVar.m);
            sb.append("&link_type=" + eVar.n);
            sb.append("&click_time=" + eVar.o + "\n");
        } else {
            sb.append("rid=" + eVar.a);
            sb.append("&rid_n=" + eVar.b);
            sb.append("&click_type=" + eVar.k);
            sb.append("&type=" + eVar.j);
            sb.append("&cid=" + eVar.c);
            sb.append("&click_duration=" + eVar.d);
            sb.append("&key=2000012");
            sb.append("&unit_id=" + eVar.l);
            sb.append("&last_url=" + str);
            sb.append("&code=" + eVar.f);
            sb.append("&exception=" + eVar.g);
            sb.append("&landing_type=" + eVar.m);
            sb.append("&link_type=" + eVar.n);
            sb.append("&click_time=" + eVar.o + "\n");
        }
        return sb.toString();
    }

    public final String toString() {
        return "ClickTime [campaignId=" + this.c + ", click_duration=" + this.d + ", lastUrl=" + this.e + ", code=" + this.f + ", excepiton=" + this.g + ", header=" + this.h + ", content=" + this.i + ", type=" + this.j + ", click_type=" + this.k + "]";
    }
}
