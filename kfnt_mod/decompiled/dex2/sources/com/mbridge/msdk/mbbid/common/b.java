package com.mbridge.msdk.mbbid.common;

/* compiled from: BidRequestParams.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class b {
    private String a;
    private String b;
    private String c;

    public b(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public b(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public String getmUnitId() {
        return this.b;
    }

    public void setmUnitId(String str) {
        this.b = str;
    }

    public String getmFloorPrice() {
        return this.c;
    }

    public void setmFloorPrice(String str) {
        this.c = str;
    }

    public String getmPlacementId() {
        return this.a;
    }

    public void setmPlacementId(String str) {
        this.a = str;
    }
}
