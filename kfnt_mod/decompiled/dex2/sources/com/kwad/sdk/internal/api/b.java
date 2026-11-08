package com.kwad.sdk.internal.api;

import android.text.TextUtils;
import com.kwad.sdk.api.KsImage;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b implements KsImage {
    private int a;
    private int b;
    private String c;

    public b(int i, int i2, String str) {
        this.a = i;
        this.b = i2;
        this.c = str;
    }

    @Override // com.kwad.sdk.api.KsImage
    public int getHeight() {
        return this.b;
    }

    @Override // com.kwad.sdk.api.KsImage
    public String getImageUrl() {
        return this.c;
    }

    @Override // com.kwad.sdk.api.KsImage
    public int getWidth() {
        return this.a;
    }

    @Override // com.kwad.sdk.api.KsImage
    public boolean isValid() {
        return this.a > 0 && this.b > 0 && !TextUtils.isEmpty(this.c);
    }
}
