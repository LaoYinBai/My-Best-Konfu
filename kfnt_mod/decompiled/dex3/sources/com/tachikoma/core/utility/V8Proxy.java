package com.tachikoma.core.utility;

import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Object;
import com.kwad.v8.V8Value;
import com.tachikoma.core.bridge.JSContext;
import com.tachikoma.core.bridge.TKJSContext;
import com.tachikoma.core.exception.TKExceptionDispatcher;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class V8Proxy {
    public static V8Array createV8Array(V8Value v8Value, Object... objArr) {
        V8Array v8Array = new V8Array(v8Value.getRuntime());
        if (objArr != null) {
            for (Object obj : objArr) {
                v8Array.push(obj);
            }
        }
        return v8Array;
    }

    public static V8Array createV8Array(V8Value v8Value, String... strArr) {
        V8Array v8Array = new V8Array(v8Value.getRuntime());
        if (strArr != null) {
            for (String str : strArr) {
                v8Array.push(str);
            }
        }
        return v8Array;
    }

    public static int executeIntegerFunction(V8Object v8Object, TKJSContext tKJSContext, String str, int... iArr) {
        try {
            if (iArr == null) {
                return v8Object.executeIntegerFunction(str, (V8Array) null);
            }
            V8Array v8ArrayMakeV8Array = tKJSContext.context().makeV8Array();
            for (int i : iArr) {
                v8ArrayMakeV8Array.push(i);
            }
            return v8Object.executeIntegerFunction(str, v8ArrayMakeV8Array);
        } catch (Exception e) {
            TKExceptionDispatcher.dispatchJSException(e, "executeIntegerFunction: name is " + str, tKJSContext.hashCode());
            return -1;
        }
    }

    public static Pair<V8Object, Object> executeJsFunction(V8Object v8Object, TKJSContext tKJSContext, String str, Object... objArr) {
        try {
            V8Object v8Object2 = (V8Object) v8Object.executeJSFunction(str, objArr);
            JSContext.V8AssociateReference associateReference = tKJSContext.context().getAssociateReference(v8Object2);
            Object obj = associateReference.nativeObject;
            associateReference.close();
            return new Pair<>(v8Object2, obj);
        } catch (Exception e) {
            TKExceptionDispatcher.dispatchJSException(e, "executeJsFunction: name is " + str, tKJSContext.hashCode());
            return null;
        }
    }

    @Nullable
    public static Object getAssociateJsObject(List<Object> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    public static TKJSContext getTKContext(List<Object> list) {
        if (list == null || list.size() - 2 < 0) {
            return null;
        }
        return (TKJSContext) list.get(list.size() - 2);
    }

    public static int getV8IntValue(V8Object v8Object, String str) {
        if (!isV8Valid(v8Object) || v8Object.get(str) == null || (v8Object.get(str) instanceof V8Object)) {
            return -1;
        }
        return ((Number) v8Object.get(str)).intValue();
    }

    public static int getV8IntValueSafely(V8Object v8Object, String str, int i) {
        try {
            return getV8IntValue(v8Object, str);
        } catch (Exception unused) {
            return i;
        }
    }

    @Nullable
    public static <T> T getV8ValueSafely(V8Object v8Object, String str, Class<T> cls) {
        if (!isV8Valid(v8Object)) {
            return null;
        }
        if ((v8Object.get(str) instanceof V8Object) && ((V8Object) v8Object.get(str)).isUndefined()) {
            return null;
        }
        return (T) v8Object.get(str);
    }

    public static boolean isV8Valid(V8Object v8Object) {
        return (v8Object == null || v8Object.isUndefined() || v8Object.isReleased()) ? false : true;
    }

    public static void release(V8Value v8Value) {
        if (v8Value == null || v8Value.isReleased()) {
            return;
        }
        v8Value.close();
    }
}
