package com.apm.insight.k;

import androidx.annotation.Nullable;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class l {

    /* renamed from: a, reason: collision with root package name */
    private final int f2940a;

    /* renamed from: b, reason: collision with root package name */
    private String f2941b;

    /* renamed from: c, reason: collision with root package name */
    private JSONObject f2942c;
    private byte[] d;

    public l(int i) {
        this.f2940a = i;
    }

    public l(int i, String str) {
        this.f2940a = i;
        this.f2941b = str;
    }

    public l(int i, Throwable th) {
        this.f2940a = i;
        if (th != null) {
            this.f2941b = th.getMessage();
        }
    }

    public l(int i, JSONObject jSONObject) {
        this.f2940a = i;
        this.f2942c = jSONObject;
    }

    public l(int i, byte[] bArr) {
        this.f2940a = i;
        this.d = bArr;
    }

    public boolean a() {
        return this.f2940a != 207;
    }

    @Nullable
    public byte[] b() {
        return this.d;
    }
}
