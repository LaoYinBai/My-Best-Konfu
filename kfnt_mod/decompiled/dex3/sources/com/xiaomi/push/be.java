package com.xiaomi.push;

import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class be {
    public int a;

    /* renamed from: a, reason: collision with other field name */
    public String f149a;

    /* renamed from: a, reason: collision with other field name */
    public Map<String, String> f150a = new HashMap();

    public String a() {
        return this.f149a;
    }

    public String toString() {
        return String.format("resCode = %1$d, headers = %2$s, response = %3$s", Integer.valueOf(this.a), this.f150a.toString(), this.f149a);
    }
}
