package com.sigmob.sdk.videoAd;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d {
    private String a;
    private String b;
    private String c;
    private String title;

    public d(String str, String str2, String str3, String str4) {
        this.title = str;
        this.a = str2;
        this.b = str3;
        this.c = str4;
    }

    public String a() {
        return this.title;
    }

    public String b() {
        return this.a;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.c;
    }

    public String toString() {
        return "DialogConfig{title='" + this.title + "', context='" + this.a + "', cancelTxt='" + this.b + "', closeTxtl='" + this.c + "'}";
    }
}
