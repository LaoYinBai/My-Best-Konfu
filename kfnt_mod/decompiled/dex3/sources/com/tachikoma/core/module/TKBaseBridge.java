package com.tachikoma.core.module;

import android.content.Context;
import androidx.annotation.Nullable;
import com.kwad.v8.V8Function;
import com.tachikoma.annotation.TK_EXPORT_CLASS;
import com.tachikoma.core.api.IBaseBridgeInner;
import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.utility.V8Proxy;
import java.util.List;

@TK_EXPORT_CLASS
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKBaseBridge implements IBaseBridgeInner {
    TKJSContext mTKJSContext;

    public TKBaseBridge(Context context, List<Object> list) {
        this.mTKJSContext = V8Proxy.getTKContext(list);
    }

    private IBaseBridgeInner getBridge() {
        return (IBaseBridgeInner) this.mTKJSContext.getHandler(IBaseBridgeInner.class);
    }

    @Override // com.tachikoma.core.api.IBaseBridgeInner
    public Object invoke(String str, @Nullable String str2, @Nullable V8Function v8Function) {
        return getBridge().invoke(str, str2, v8Function);
    }

    @Override // com.tachikoma.core.api.IBaseBridgeInner
    public Object invokeM(String str, String str2, @Nullable String str3, @Nullable V8Function v8Function) {
        return getBridge().invokeM(str, str2, str3, v8Function);
    }
}
