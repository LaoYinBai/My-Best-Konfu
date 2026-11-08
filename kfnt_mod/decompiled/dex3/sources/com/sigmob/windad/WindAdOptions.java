package com.sigmob.windad;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class WindAdOptions {
    private String a;
    private String b;
    private boolean c;

    public WindAdOptions(String str, String str2, boolean z) {
        this.a = str;
        this.b = str2;
        this.c = z;
    }

    public String getAppId() {
        return this.a;
    }

    public String getAppKey() {
        return this.b;
    }

    public boolean getUseMediation() {
        return this.c;
    }
}
