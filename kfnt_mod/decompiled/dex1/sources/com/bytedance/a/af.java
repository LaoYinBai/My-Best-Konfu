package com.bytedance.a;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class af {

    /* renamed from: b, reason: collision with root package name */
    public boolean f3339b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f3340c;
    public boolean d;
    public boolean e;

    public af(boolean z, boolean z2) {
        this.f3340c = z;
        this.d = z2;
        this.e = false;
    }

    public af(boolean z, boolean z2, boolean z3) {
        this.f3340c = z;
        this.d = z2;
        this.e = z3;
    }

    public abstract boolean a(JSONObject jSONObject);
}
