package com.tachikoma.core.component.listview.viewpager;

import android.view.View;
import androidx.annotation.NonNull;
import com.kwad.v8.V8Object;
import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.utility.V8Proxy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKPageTransformer implements ITKPageTransformer {
    private final TKJSContext mTKJSContext;
    private final V8Object mTKPageTransformerCallback;

    public TKPageTransformer(V8Object v8Object, TKJSContext tKJSContext) {
        this.mTKJSContext = tKJSContext;
        this.mTKPageTransformerCallback = v8Object.twin();
    }

    @Override // com.tachikoma.core.component.listview.viewpager.ITKPageTransformer
    public void transformPage(@NonNull View view, float f) {
        V8Proxy.executeJsFunction(this.mTKPageTransformerCallback, this.mTKJSContext, "transformPage", view, Float.valueOf(f));
    }
}
