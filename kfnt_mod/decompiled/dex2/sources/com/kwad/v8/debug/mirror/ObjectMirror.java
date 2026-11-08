package com.kwad.v8.debug.mirror;

import com.kwad.v8.V8Array;
import com.kwad.v8.V8Object;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ObjectMirror extends ValueMirror {
    private static final String PROPERTIES = "properties";
    private static final String PROPERTY_NAMES = "propertyNames";

    public enum PropertyKind {
        Named(1),
        Indexed(2);

        int index;

        PropertyKind(int i) {
            this.index = i;
        }
    }

    ObjectMirror(V8Object v8Object) {
        super(v8Object);
    }

    public PropertiesArray getProperties(PropertyKind propertyKind, int i) throws Throwable {
        V8Array v8Array = new V8Array(this.v8Object.getRuntime());
        v8Array.push(propertyKind.index);
        v8Array.push(i);
        V8Array v8Array2 = null;
        try {
            V8Array v8ArrayExecuteArrayFunction = this.v8Object.executeArrayFunction(PROPERTIES, v8Array);
            try {
                PropertiesArray propertiesArray = new PropertiesArray(v8ArrayExecuteArrayFunction);
                v8Array.close();
                if (v8ArrayExecuteArrayFunction != null && !v8ArrayExecuteArrayFunction.isReleased()) {
                    v8ArrayExecuteArrayFunction.close();
                }
                return propertiesArray;
            } catch (Throwable th) {
                th = th;
                v8Array2 = v8ArrayExecuteArrayFunction;
                v8Array.close();
                if (v8Array2 != null && !v8Array2.isReleased()) {
                    v8Array2.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public String[] getPropertyNames(PropertyKind propertyKind, int i) throws Throwable {
        V8Array v8ArrayExecuteArrayFunction;
        Throwable th;
        V8Array v8Array = new V8Array(this.v8Object.getRuntime());
        v8Array.push(propertyKind.index);
        v8Array.push(i);
        try {
            v8ArrayExecuteArrayFunction = this.v8Object.executeArrayFunction(PROPERTY_NAMES, v8Array);
            try {
                String[] strArr = new String[v8ArrayExecuteArrayFunction.length()];
                for (int i2 = 0; i2 < strArr.length; i2++) {
                    strArr[i2] = v8ArrayExecuteArrayFunction.getString(i2);
                }
                v8Array.close();
                if (v8ArrayExecuteArrayFunction != null) {
                    v8ArrayExecuteArrayFunction.close();
                }
                return strArr;
            } catch (Throwable th2) {
                th = th2;
                v8Array.close();
                if (v8ArrayExecuteArrayFunction != null) {
                    v8ArrayExecuteArrayFunction.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            v8ArrayExecuteArrayFunction = null;
            th = th3;
        }
    }

    @Override // com.kwad.v8.debug.mirror.Mirror
    public boolean isObject() {
        return true;
    }

    @Override // com.kwad.v8.debug.mirror.Mirror
    public String toString() {
        return this.v8Object.toString();
    }
}
