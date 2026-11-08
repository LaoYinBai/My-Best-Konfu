package com.huawei.hms.update.a.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    public int a;
    public String b;
    public String c;
    public int d;
    public String e;

    public c() {
        this.a = 0;
        this.b = "";
        this.c = "";
        this.d = 0;
        this.e = "";
    }

    public c(String str, int i, String str2, int i2, String str3) {
        this.a = 0;
        this.b = "";
        this.c = "";
        this.d = 0;
        this.e = "";
        this.b = str;
        this.a = i;
        this.c = str2;
        this.d = i2;
        this.e = str3;
    }

    public boolean a() {
        return this.a > 0 && this.d > 0 && this.c != null && !this.c.isEmpty();
    }
}
