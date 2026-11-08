package com.kwad.sdk.widget;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.annotation.RequiresApi;

@RequiresApi(api = 21)
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g extends ViewOutlineProvider {
    private float a;

    public g(float f) {
        this.a = f;
    }

    @RequiresApi(api = 21)
    public static void a(View view, float f) {
        boolean z;
        if (f <= 0.0f) {
            view.setOutlineProvider(null);
            z = false;
        } else {
            view.setOutlineProvider(new g(f));
            z = true;
        }
        view.setClipToOutline(z);
    }

    @Override // android.view.ViewOutlineProvider
    public void getOutline(View view, Outline outline) {
        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), this.a);
    }
}
