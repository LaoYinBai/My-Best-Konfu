package com.tachikoma.core.component.listview.viewpager;

import com.kwad.v8.V8Object;
import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.exception.TKExceptionDispatcher;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKOnPageChangeCallbackImpl implements ITKOnPageChangeCallback {
    private final TKJSContext mTKJSContext;
    private final V8Object mTKOnChangeCallback;

    public TKOnPageChangeCallbackImpl(V8Object v8Object, TKJSContext tKJSContext) {
        this.mTKJSContext = tKJSContext;
        this.mTKOnChangeCallback = v8Object.twin();
    }

    private void executeJsFunction(String str, Object... objArr) {
        try {
            this.mTKOnChangeCallback.executeJSFunction(str, objArr);
        } catch (Throwable th) {
            TKExceptionDispatcher.dispatchJSException(th, "executeJsFunction: name is " + str, this.mTKJSContext.hashCode());
        }
    }

    @Override // com.tachikoma.core.component.listview.viewpager.ITKOnPageChangeCallback
    public void onPageScrollStateChanged(int i) {
        executeJsFunction("onPageScrollStateChanged", Integer.valueOf(i));
    }

    @Override // com.tachikoma.core.component.listview.viewpager.ITKOnPageChangeCallback
    public void onPageScrolled(int i, float f, int i2) {
        executeJsFunction("onPageScrolled", Integer.valueOf(i), Float.valueOf(f), Integer.valueOf(i2));
    }

    @Override // com.tachikoma.core.component.listview.viewpager.ITKOnPageChangeCallback
    public void onPageSelected(int i) {
        executeJsFunction("onPageSelected", Integer.valueOf(i));
    }
}
