package com.kuaishou.tachikoma.api;

import androidx.annotation.Nullable;
import com.kuaishou.tachikoma.api.page.IFunction;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Function;
import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.exception.TKExceptionDispatcher;
import com.tachikoma.core.utility.V8Proxy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class FunctionCallImpl implements IFunction {

    @Nullable
    private V8Function mV8Function;

    FunctionCallImpl(@Nullable V8Function v8Function) {
        if (v8Function != null) {
            this.mV8Function = v8Function.twin();
        }
    }

    @Override // com.kuaishou.tachikoma.api.page.IFunction
    @Nullable
    public Object call(@Nullable Object... objArr) {
        Object objCall;
        V8Array v8ArrayCreateV8Array;
        if (!V8Proxy.isV8Valid(this.mV8Function)) {
            return null;
        }
        try {
            v8ArrayCreateV8Array = V8Proxy.createV8Array(this.mV8Function, objArr);
            objCall = this.mV8Function.call(null, v8ArrayCreateV8Array);
        } catch (Throwable th) {
            th = th;
            objCall = null;
        }
        try {
            V8Proxy.release(v8ArrayCreateV8Array);
            return objCall;
        } catch (Throwable th2) {
            th = th2;
            TKExceptionDispatcher.reportException((TKJSContext) null, th);
            return objCall;
        }
    }
}
