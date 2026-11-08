package com.bytedance.push.b;

import android.graphics.Bitmap;
import android.net.Uri;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    public final Uri f4756a;

    /* renamed from: b, reason: collision with root package name */
    public final int f4757b;

    /* renamed from: c, reason: collision with root package name */
    public final int f4758c;
    public final Bitmap.Config d;

    public c(Uri uri, int i, int i2, Bitmap.Config config) {
        this.f4756a = uri;
        this.f4757b = i;
        this.f4758c = i2;
        this.d = config;
    }

    public boolean a() {
        return this.f4757b != 0;
    }
}
