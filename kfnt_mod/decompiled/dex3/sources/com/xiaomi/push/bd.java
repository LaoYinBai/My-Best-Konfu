package com.xiaomi.push;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class bd implements bf {
    private final String a;
    private final String b;

    public bd(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("Name may not be null");
        }
        this.a = str;
        this.b = str2;
    }

    @Override // com.xiaomi.push.bf
    public String a() {
        return this.a;
    }

    @Override // com.xiaomi.push.bf
    public String b() {
        return this.b;
    }
}
