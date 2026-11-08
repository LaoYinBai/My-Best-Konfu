package com.sigmob.sdk.base.models;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CurrentAppOrientation {
    private final String a;
    private final boolean b;

    public CurrentAppOrientation(String str, boolean z) {
        this.a = str;
        this.b = z;
    }

    public String toString() {
        return "\"appOrientation\"={\"orientation\"=\"" + this.a + "\", \"locked\"=" + this.b + '}';
    }
}
