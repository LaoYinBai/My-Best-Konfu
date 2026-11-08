package com.huawei.hms.framework.network.grs.local.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private String a;
    private long b;
    private Map<String, c> c = new HashMap(16);

    public String a() {
        return this.a;
    }

    public void a(long j) {
        this.b = j;
    }

    public void a(String str) {
        this.a = str;
    }

    public void a(String str, c cVar) {
        this.c.put(str, cVar);
    }

    public c b(String str) {
        return this.c.get(str);
    }

    public Map<String, c> b() {
        return this.c;
    }
}
