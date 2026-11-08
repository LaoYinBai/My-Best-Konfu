package com.bytedance.msdk.api.v2;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMLocation {

    /* renamed from: a, reason: collision with root package name */
    private double f4348a;

    /* renamed from: b, reason: collision with root package name */
    private double f4349b;

    public GMLocation(double d, double d2) {
        this.f4348a = 0.0d;
        this.f4349b = 0.0d;
        this.f4348a = d;
        this.f4349b = d2;
    }

    public double getLatitude() {
        return this.f4348a;
    }

    public double getLongitude() {
        return this.f4349b;
    }

    public void setLatitude(double d) {
        this.f4348a = d;
    }

    public void setLongitude(double d) {
        this.f4349b = d;
    }
}
