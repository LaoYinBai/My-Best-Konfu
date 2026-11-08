package com.apm.insight.entity;

import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private String f2840a;

    /* renamed from: b, reason: collision with root package name */
    private JSONObject f2841b;

    /* renamed from: c, reason: collision with root package name */
    private String f2842c;
    private boolean d;
    private String e;
    private String f;
    private String g;
    private List<String> h;

    public String a() {
        return this.f2840a;
    }

    public void a(String str) {
        this.f2840a = str;
    }

    public void a(List<String> list) {
        this.h = list;
    }

    public void a(JSONObject jSONObject) {
        this.f2841b = jSONObject;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public JSONObject b() {
        return this.f2841b;
    }

    public void b(String str) {
        this.f2842c = str;
    }

    public String c() {
        return this.f2842c;
    }

    public void c(String str) {
        this.e = str;
    }

    public String d() {
        return this.e;
    }

    public void d(String str) {
        this.f = str;
    }

    public String e() {
        return this.f;
    }

    public void e(String str) {
        this.g = str;
    }

    public String f() {
        return this.g;
    }

    public List<String> g() {
        return this.h;
    }
}
