package com.kwai.library.widget.refresh;

import android.content.Context;
import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements c {
    private final float a;

    public a(@NonNull Context context) {
        this.a = g.a(context);
    }

    @Override // com.kwai.library.widget.refresh.c
    public float a(float f, float f2) {
        return this.a - (((this.a * this.a) * 2.0f) / (f + (this.a * 2.0f)));
    }
}
