package com.qq.e.comm.managers.status;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public enum Carrier {
    UNKNOWN(0),
    CMCC(1),
    UNICOM(2),
    TELECOM(3);

    private int a;

    Carrier(int i) {
        this.a = i;
    }

    public int getValue() {
        return this.a;
    }
}
