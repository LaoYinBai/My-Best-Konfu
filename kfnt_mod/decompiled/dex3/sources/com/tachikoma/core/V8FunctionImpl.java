package com.tachikoma.core;

import com.kwad.v8.V8Function;
import com.kwad.v8.V8Object;
import com.kwad.v8.V8Value;
import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.utility.TKEnv;
import com.tachikoma.core.utility.V8Proxy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class V8FunctionImpl implements IV8Function {
    private final TKJSContext mTKJSContext;
    private V8Function mV8Function;

    public V8FunctionImpl(V8Function v8Function, TKJSContext tKJSContext) {
        this.mV8Function = null;
        if (v8Function != null) {
            this.mV8Function = v8Function.twin();
        }
        this.mTKJSContext = tKJSContext;
    }

    @Override // com.tachikoma.core.IV8Function
    public Object call(Object... objArr) {
        if (this.mV8Function == null) {
            return null;
        }
        try {
            return this.mV8Function.call((V8Object) null, V8Proxy.createV8Array((V8Value) this.mV8Function, objArr));
        } catch (Throwable th) {
            if (this.mTKJSContext != null && TKEnv.isDebug(this.mTKJSContext)) {
                throw th;
            }
            if (this.mTKJSContext != null && this.mTKJSContext.getExceptionHandler() != null) {
                this.mTKJSContext.getExceptionHandler().onException(th);
            }
            return null;
        }
    }
}
