package com.bytedance.sdk.openadsdk;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TTLocation implements LocationProvider {

    /* renamed from: a, reason: collision with root package name */
    private double f5181a;

    /* renamed from: b, reason: collision with root package name */
    private double f5182b;

    public TTLocation(double d, double d2) {
        this.f5181a = 0.0d;
        this.f5182b = 0.0d;
        this.f5181a = d;
        this.f5182b = d2;
    }

    @Override // com.bytedance.sdk.openadsdk.LocationProvider
    public double getLatitude() {
        return this.f5181a;
    }

    public void setLatitude(double d) {
        this.f5181a = d;
    }

    @Override // com.bytedance.sdk.openadsdk.LocationProvider
    public double getLongitude() {
        return this.f5182b;
    }

    public void setLongitude(double d) {
        this.f5182b = d;
    }
}
