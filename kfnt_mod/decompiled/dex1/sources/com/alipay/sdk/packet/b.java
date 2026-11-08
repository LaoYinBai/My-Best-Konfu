package com.alipay.sdk.packet;

import android.text.TextUtils;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private final String f2615a;

    /* renamed from: b, reason: collision with root package name */
    private final String f2616b;

    public b(String str, String str2) {
        this.f2615a = str;
        this.f2616b = str2;
    }

    public String a() {
        return this.f2615a;
    }

    public String b() {
        return this.f2616b;
    }

    public JSONObject c() {
        if (TextUtils.isEmpty(this.f2616b)) {
            return null;
        }
        try {
            return new JSONObject(this.f2616b);
        } catch (Exception e) {
            com.alipay.sdk.util.c.a(e);
            return null;
        }
    }

    public String toString() {
        return String.format("<Letter envelop=%s body=%s>", this.f2615a, this.f2616b);
    }
}
