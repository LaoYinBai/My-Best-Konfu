package com.bytedance.msdk.api;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AdmobNativeAdOptions {
    public static final int ADCHOICES_BOTTOM_LEFT = 3;
    public static final int ADCHOICES_BOTTOM_RIGHT = 2;
    public static final int ADCHOICES_TOP_LEFT = 0;
    public static final int ADCHOICES_TOP_RIGHT = 1;

    /* renamed from: a, reason: collision with root package name */
    private int f4264a = 1;

    /* renamed from: b, reason: collision with root package name */
    private boolean f4265b = true;

    /* renamed from: c, reason: collision with root package name */
    private boolean f4266c = true;

    public int getAdChoicesPlacement() {
        return this.f4264a;
    }

    public boolean isRequestMultipleImages() {
        return this.f4266c;
    }

    public boolean isReturnUrlsForImageAssets() {
        return this.f4265b;
    }

    public AdmobNativeAdOptions setAdChoicesPlacement(int i) {
        this.f4264a = i;
        return this;
    }

    public AdmobNativeAdOptions setRequestMultipleImages(boolean z) {
        this.f4266c = z;
        return this;
    }

    public AdmobNativeAdOptions setReturnUrlsForImageAssets(boolean z) {
        this.f4265b = z;
        return this;
    }
}
