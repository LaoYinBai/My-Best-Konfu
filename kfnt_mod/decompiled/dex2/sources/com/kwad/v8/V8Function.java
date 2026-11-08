package com.kwad.v8;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class V8Function extends V8Object {
    protected V8Function(V8 v8) {
        this(v8, null);
    }

    public V8Function(V8 v8, JavaCallback javaCallback) {
        super(v8, javaCallback);
    }

    public Object call(V8Object v8Object, V8Array v8Array) {
        this.v8.checkThread();
        checkReleased();
        this.v8.checkRuntime(v8Object);
        this.v8.checkRuntime(v8Array);
        if (v8Object == null) {
            v8Object = this.v8;
        }
        return this.v8.executeFunction(this.v8.getV8RuntimePtr(), v8Object.isUndefined() ? this.v8.getHandle() : v8Object.getHandle(), this.objectHandle, v8Array == null ? 0L : v8Array.getHandle());
    }

    @Override // com.kwad.v8.V8Object, com.kwad.v8.V8Value
    protected V8Value createTwin() {
        return new V8Function(this.v8);
    }

    @Override // com.kwad.v8.V8Value
    protected void initialize(long j, Object obj) {
        if (obj == null) {
            super.initialize(j, null);
            return;
        }
        long[] jArrInitNewV8Function = this.v8.initNewV8Function(j);
        this.v8.createAndRegisterMethodDescriptor((JavaCallback) obj, jArrInitNewV8Function[1]);
        this.released = false;
        addObjectReference(jArrInitNewV8Function[0]);
    }

    @Override // com.kwad.v8.V8Object
    public String toString() {
        return (this.released || this.v8.isReleased()) ? "[Function released]" : super.toString();
    }

    @Override // com.kwad.v8.V8Object, com.kwad.v8.V8Value
    public V8Function twin() {
        return (V8Function) super.twin();
    }
}
