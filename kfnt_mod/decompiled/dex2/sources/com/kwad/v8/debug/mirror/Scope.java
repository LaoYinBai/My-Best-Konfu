package com.kwad.v8.debug.mirror;

import com.kwad.v8.V8Array;
import com.kwad.v8.V8Object;
import com.kwad.v8.V8Value;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class Scope extends Mirror {
    private static final String SCOPE_OBJECT = "scopeObject";
    private static final String SCOPE_TYPE = "scopeType";
    private static final String SET_VARIABLE_VALUE = "setVariableValue";

    public enum ScopeType {
        Global(0),
        Local(1),
        With(2),
        Closure(3),
        Catch(4),
        Block(5),
        Script(6);

        int index;

        ScopeType(int i) {
            this.index = i;
        }
    }

    Scope(V8Object v8Object) {
        super(v8Object);
    }

    public ObjectMirror getScopeObject() throws Throwable {
        V8Object v8Object = null;
        try {
            V8Object v8ObjectExecuteObjectFunction = this.v8Object.executeObjectFunction(SCOPE_OBJECT, null);
            try {
                ObjectMirror objectMirror = (ObjectMirror) createMirror(v8ObjectExecuteObjectFunction);
                if (v8ObjectExecuteObjectFunction != null) {
                    v8ObjectExecuteObjectFunction.close();
                }
                return objectMirror;
            } catch (Throwable th) {
                th = th;
                v8Object = v8ObjectExecuteObjectFunction;
                if (v8Object != null) {
                    v8Object.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public ScopeType getType() {
        return ScopeType.values()[this.v8Object.executeIntegerFunction(SCOPE_TYPE, null)];
    }

    public void setVariableValue(String str, double d) {
        V8Array v8Array = new V8Array(this.v8Object.getRuntime());
        v8Array.push(str);
        v8Array.push(d);
        try {
            this.v8Object.executeVoidFunction(SET_VARIABLE_VALUE, v8Array);
        } finally {
            v8Array.close();
        }
    }

    public void setVariableValue(String str, int i) {
        V8Array v8Array = new V8Array(this.v8Object.getRuntime());
        v8Array.push(str);
        v8Array.push(i);
        try {
            this.v8Object.executeVoidFunction(SET_VARIABLE_VALUE, v8Array);
        } finally {
            v8Array.close();
        }
    }

    public void setVariableValue(String str, V8Value v8Value) {
        V8Array v8Array = new V8Array(this.v8Object.getRuntime());
        v8Array.push(str);
        v8Array.push(v8Value);
        try {
            this.v8Object.executeVoidFunction(SET_VARIABLE_VALUE, v8Array);
        } finally {
            v8Array.close();
        }
    }

    public void setVariableValue(String str, String str2) {
        V8Array v8Array = new V8Array(this.v8Object.getRuntime());
        v8Array.push(str);
        v8Array.push(str2);
        try {
            this.v8Object.executeVoidFunction(SET_VARIABLE_VALUE, v8Array);
        } finally {
            v8Array.close();
        }
    }

    public void setVariableValue(String str, boolean z) {
        V8Array v8Array = new V8Array(this.v8Object.getRuntime());
        v8Array.push(str);
        v8Array.push(z);
        try {
            this.v8Object.executeVoidFunction(SET_VARIABLE_VALUE, v8Array);
        } finally {
            v8Array.close();
        }
    }
}
