package com.unity3d.services.analytics;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public enum AcquisitionType {
    SOFT,
    PREMIUM;

    @Override // java.lang.Enum
    public String toString() {
        switch (this) {
            case SOFT:
                return "soft";
            case PREMIUM:
                return "premium";
            default:
                return "";
        }
    }
}
