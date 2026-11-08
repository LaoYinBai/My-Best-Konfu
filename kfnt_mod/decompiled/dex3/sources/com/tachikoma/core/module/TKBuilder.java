package com.tachikoma.core.module;

import androidx.annotation.NonNull;
import com.tachikoma.core.api.IAnimatedImageInner;
import com.tachikoma.core.api.IHostEnvInner;
import com.tachikoma.core.api.IRequestDelegateInner;
import com.tachikoma.core.api.IWebImageHandlerInner;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKBuilder {
    private static final String TAG = "TKBuilder";
    private IAnimatedImageInner mAnimatedImageInner;
    private IHostEnvInner mEnv;
    private IRequestDelegateInner mRequestDelegate;
    private IWebImageHandlerInner mWebImageHandler;

    public Map<Class<?>, Object> build() {
        HashMap map = new HashMap();
        if (this.mWebImageHandler != null) {
            map.put(IWebImageHandlerInner.class, this.mWebImageHandler);
        }
        if (this.mAnimatedImageInner != null) {
            map.put(IAnimatedImageInner.class, this.mAnimatedImageInner);
        }
        if (this.mEnv != null) {
            map.put(IHostEnvInner.class, this.mEnv);
        }
        if (this.mRequestDelegate != null) {
            map.put(IRequestDelegateInner.class, this.mRequestDelegate);
        }
        return map;
    }

    public void setAnimatedImageInner(IAnimatedImageInner iAnimatedImageInner) {
        this.mAnimatedImageInner = iAnimatedImageInner;
    }

    public void setEnv(@NonNull IHostEnvInner iHostEnvInner) {
        this.mEnv = iHostEnvInner;
    }

    public void setNetWork(IRequestDelegateInner iRequestDelegateInner) {
        this.mRequestDelegate = iRequestDelegateInner;
    }

    public void setWebImageHandler(@NonNull IWebImageHandlerInner iWebImageHandlerInner) {
        this.mWebImageHandler = iWebImageHandlerInner;
    }
}
