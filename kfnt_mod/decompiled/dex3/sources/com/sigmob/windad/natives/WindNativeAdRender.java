package com.sigmob.windad.natives;

import android.content.Context;
import android.view.View;
import com.sigmob.windad.natives.NativeADData;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface WindNativeAdRender<T extends NativeADData> {
    View createView(Context context, int i);

    void renderAdView(View view, T t);
}
