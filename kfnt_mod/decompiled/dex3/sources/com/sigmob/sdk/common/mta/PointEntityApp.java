package com.sigmob.sdk.common.mta;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PointEntityApp extends PointEntitySuper {
    private String b;
    private String c;
    private String d;
    private String e;

    public String getApp_name() {
        return this.b;
    }

    public String getApp_version() {
        return this.e;
    }

    public String getPackage_name() {
        return this.c;
    }

    public String getUpdate() {
        return this.d;
    }

    public void setApp_name(String str) {
        this.b = str;
    }

    public void setApp_version(String str) {
        this.e = str;
    }

    public void setPackage_name(String str) {
        this.c = str;
    }

    public void setUpdate(String str) {
        this.d = str;
    }
}
