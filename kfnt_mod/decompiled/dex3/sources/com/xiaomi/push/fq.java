package com.xiaomi.push;

import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class fq implements Cloneable {
    public static String a = "wcc-ml-test10.bj";
    public static final String b = ae.a;
    public static String c = null;

    /* renamed from: a, reason: collision with other field name */
    private int f406a;

    /* renamed from: a, reason: collision with other field name */
    private ft f407a;

    /* renamed from: a, reason: collision with other field name */
    private boolean f408a = fp.f391a;

    /* renamed from: b, reason: collision with other field name */
    private boolean f409b = true;
    private String d;
    private String e;
    private String f;

    public fq(Map<String, Integer> map, int i, String str, ft ftVar) {
        a(map, i, str, ftVar);
    }

    public static final String a() {
        return c != null ? c : ab.m90a() ? "sandbox.xmpush.xiaomi.com" : ab.b() ? b : "app.chat.xiaomi.net";
    }

    public static final void a(String str) {
        c = str;
    }

    private void a(Map<String, Integer> map, int i, String str, ft ftVar) {
        this.f406a = i;
        this.d = str;
        this.f407a = ftVar;
    }

    /* renamed from: a, reason: collision with other method in class */
    public int m336a() {
        return this.f406a;
    }

    public void a(boolean z) {
        this.f408a = z;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m337a() {
        return this.f408a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public byte[] mo338a() {
        return null;
    }

    public String b() {
        return this.f;
    }

    public void b(String str) {
        this.f = str;
    }

    public String c() {
        if (this.e == null) {
            this.e = a();
        }
        return this.e;
    }

    public void c(String str) {
        this.e = str;
    }
}
