package com.sigmob.sdk.common.mta;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class PointEntityClick extends PointEntityBase {
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;

    public String getClick_duration() {
        return this.c;
    }

    public String getIs_valid_click() {
        return this.d;
    }

    public String getLocation() {
        return this.b;
    }

    public String getPressure() {
        return this.f;
    }

    public String getTouchSize() {
        return this.g;
    }

    public String getTouchType() {
        return this.e;
    }

    public void setClick_duration(String str) {
        this.c = str;
    }

    public void setIs_valid_click(String str) {
        this.d = str;
    }

    public void setLocation(String str) {
        this.b = str;
    }

    public void setPressure(String str) {
        this.f = str;
    }

    public void setTouchSize(String str) {
        this.g = str;
    }

    public void setTouchType(String str) {
        this.e = str;
    }
}
