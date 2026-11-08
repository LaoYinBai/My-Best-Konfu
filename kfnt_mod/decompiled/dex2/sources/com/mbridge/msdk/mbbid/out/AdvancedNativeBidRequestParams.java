package com.mbridge.msdk.mbbid.out;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class AdvancedNativeBidRequestParams extends CommonBidRequestParams {
    private int a;
    private int b;

    public AdvancedNativeBidRequestParams(String str, String str2, int i, int i2) {
        super(str, str2);
        this.a = i2;
        this.b = i;
    }

    public AdvancedNativeBidRequestParams(String str, String str2, String str3, int i, int i2) {
        super(str, str2, str3);
        this.a = i2;
        this.b = i;
    }

    public int getHeight() {
        return this.a;
    }

    public void setHeight(int i) {
        this.a = i;
    }

    public int getWidth() {
        return this.b;
    }

    public void setWidth(int i) {
        this.b = i;
    }
}
