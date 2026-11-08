package com.kwad.v8.debug.mirror;

import com.kwad.v8.Releasable;
import com.kwad.v8.V8Object;
import com.kwad.v8.V8ResultUndefined;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class Mirror implements Releasable {
    private static final String IS_ARRAY = "isArray";
    private static final String IS_BOOLEAN = "isBoolean";
    private static final String IS_FUNCTION = "isFunction";
    private static final String IS_NULL = "isNull";
    private static final String IS_NUMBER = "isNumber";
    private static final String IS_OBJECT = "isObject";
    private static final String IS_STRING = "isString";
    private static final String IS_UNDEFINED = "isUndefined";
    private static final String IS_VALUE = "isValue";
    protected V8Object v8Object;

    Mirror(V8Object v8Object) {
        this.v8Object = v8Object.twin();
    }

    protected static ValueMirror createMirror(V8Object v8Object) {
        return isNull(v8Object) ? new NullMirror(v8Object) : isUndefined(v8Object) ? new UndefinedMirror(v8Object) : isFunction(v8Object) ? new FunctionMirror(v8Object) : isArray(v8Object) ? new ArrayMirror(v8Object) : isObject(v8Object) ? new ObjectMirror(v8Object) : isString(v8Object) ? new StringMirror(v8Object) : isNumber(v8Object) ? new NumberMirror(v8Object) : isBoolean(v8Object) ? new BooleanMirror(v8Object) : new ValueMirror(v8Object);
    }

    private static boolean isArray(V8Object v8Object) {
        try {
            return v8Object.executeBooleanFunction(IS_ARRAY, null);
        } catch (V8ResultUndefined unused) {
            return false;
        }
    }

    private static boolean isBoolean(V8Object v8Object) {
        try {
            return v8Object.executeBooleanFunction(IS_BOOLEAN, null);
        } catch (V8ResultUndefined unused) {
            return false;
        }
    }

    private static boolean isFunction(V8Object v8Object) {
        try {
            return v8Object.executeBooleanFunction(IS_FUNCTION, null);
        } catch (V8ResultUndefined unused) {
            return false;
        }
    }

    private static boolean isNull(V8Object v8Object) {
        try {
            return v8Object.executeBooleanFunction(IS_NULL, null);
        } catch (V8ResultUndefined unused) {
            return false;
        }
    }

    private static boolean isNumber(V8Object v8Object) {
        try {
            return v8Object.executeBooleanFunction(IS_NUMBER, null);
        } catch (V8ResultUndefined unused) {
            return false;
        }
    }

    private static boolean isObject(V8Object v8Object) {
        try {
            return v8Object.executeBooleanFunction(IS_OBJECT, null);
        } catch (V8ResultUndefined unused) {
            return false;
        }
    }

    private static boolean isString(V8Object v8Object) {
        try {
            return v8Object.executeBooleanFunction(IS_STRING, null);
        } catch (V8ResultUndefined unused) {
            return false;
        }
    }

    private static boolean isUndefined(V8Object v8Object) {
        try {
            return v8Object.executeBooleanFunction(IS_UNDEFINED, null);
        } catch (V8ResultUndefined unused) {
            return false;
        }
    }

    protected static boolean isValue(V8Object v8Object) {
        try {
            return v8Object.executeBooleanFunction(IS_VALUE, null);
        } catch (V8ResultUndefined unused) {
            return false;
        }
    }

    @Override // com.kwad.v8.Releasable, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.v8Object == null || this.v8Object.isReleased()) {
            return;
        }
        this.v8Object.close();
        this.v8Object = null;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof Mirror)) {
            return this.v8Object.equals(((Mirror) obj).v8Object);
        }
        return false;
    }

    public int hashCode() {
        return this.v8Object.hashCode();
    }

    public boolean isArray() {
        return false;
    }

    public boolean isBoolean() {
        return false;
    }

    public boolean isFrame() {
        return false;
    }

    public boolean isFunction() {
        return false;
    }

    public boolean isNull() {
        return false;
    }

    public boolean isNumber() {
        return false;
    }

    public boolean isObject() {
        return false;
    }

    public boolean isProperty() {
        return false;
    }

    public boolean isString() {
        return false;
    }

    public boolean isUndefined() {
        return this.v8Object.executeBooleanFunction(IS_UNDEFINED, null);
    }

    public boolean isValue() {
        return false;
    }

    @Override // com.kwad.v8.Releasable
    @Deprecated
    public void release() {
        close();
    }

    public String toString() {
        return this.v8Object.toString();
    }
}
