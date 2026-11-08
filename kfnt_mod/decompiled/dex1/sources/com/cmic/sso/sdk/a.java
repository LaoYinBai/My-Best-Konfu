package com.cmic.sso.sdk;

import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private ConcurrentHashMap<String, Object> f5343a;

    public a(int i) {
        this.f5343a = new ConcurrentHashMap<>(i);
    }

    public void a(String str, byte[] bArr) {
        if (str == null || bArr == null) {
            return;
        }
        this.f5343a.put(str, bArr);
    }

    public byte[] a(String str) {
        if (str != null) {
            return (byte[]) this.f5343a.get(str);
        }
        return null;
    }

    public void a(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        this.f5343a.put(str, str2);
    }

    public String b(String str) {
        return b(str, "");
    }

    public String b(String str, String str2) {
        return (str == null || !this.f5343a.containsKey(str)) ? str2 : (String) this.f5343a.get(str);
    }

    public void a(String str, boolean z) {
        if (str != null) {
            this.f5343a.put(str, Boolean.valueOf(z));
        }
    }

    public boolean b(String str, boolean z) {
        return (str == null || !this.f5343a.containsKey(str)) ? z : ((Boolean) this.f5343a.get(str)).booleanValue();
    }

    public void a(String str, int i) {
        if (str != null) {
            this.f5343a.put(str, Integer.valueOf(i));
        }
    }

    public int c(String str) {
        return b(str, 0);
    }

    public int b(String str, int i) {
        return (str == null || !this.f5343a.containsKey(str)) ? i : ((Integer) this.f5343a.get(str)).intValue();
    }

    public void a(String str, long j) {
        if (str != null) {
            this.f5343a.put(str, Long.valueOf(j));
        }
    }

    public long b(String str, long j) {
        return (str == null || !this.f5343a.containsKey(str)) ? j : ((Long) this.f5343a.get(str)).longValue();
    }
}
