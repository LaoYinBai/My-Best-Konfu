package com.sigmob.windad;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public enum WindAgeRestrictedUserStatus {
    WindAgeRestrictedStatusUnknown(0),
    WindAgeRestrictedStatusYES(1),
    WindAgeRestrictedStatusNO(2);

    private int a;

    WindAgeRestrictedUserStatus(int i) {
        this.a = i;
    }

    public int getValue() {
        return this.a;
    }
}
