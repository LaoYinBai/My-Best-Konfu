package com.sigmob.sdk.common.mta;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class PointEntityGDPR extends PointEntitySuper {
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;

    public String getAge() {
        return this.d;
    }

    public String getAge_restricted() {
        return this.c;
    }

    public String getGdpr_dialog_region() {
        return this.e;
    }

    public String getGdpr_region() {
        return this.f;
    }

    public String getUser_consent() {
        return this.b;
    }

    public void setAge(String str) {
        this.d = str;
    }

    public void setAge_restricted(String str) {
        this.c = str;
    }

    public void setGdpr_dialog_region(String str) {
        this.e = str;
    }

    public void setGdpr_region(String str) {
        this.f = str;
    }

    public void setUser_consent(String str) {
        this.b = str;
    }
}
