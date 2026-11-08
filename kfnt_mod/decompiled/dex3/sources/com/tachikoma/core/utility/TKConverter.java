package com.tachikoma.core.utility;

import android.content.Context;
import android.util.TypedValue;
import com.kwad.v8.V8Array;
import com.kwad.v8.V8Object;
import com.kwad.v8.V8Value;
import com.tachikoma.core.bridge.JSContext;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKConverter {
    public static Object doConverter(Object obj, Class cls) throws NumberFormatException {
        Object objValueOf;
        if (Integer.TYPE.equals(cls)) {
            try {
                objValueOf = Integer.valueOf(obj.toString());
            } catch (NumberFormatException unused) {
                objValueOf = Integer.valueOf(Float.valueOf(obj.toString()).intValue());
            }
        } else {
            objValueOf = obj;
        }
        if (Byte.TYPE.equals(cls)) {
            objValueOf = Byte.valueOf(obj.toString());
        }
        if (Short.TYPE.equals(cls)) {
            objValueOf = Short.valueOf(obj.toString());
        }
        if (Long.TYPE.equals(cls)) {
            objValueOf = Long.valueOf(obj.toString());
        }
        if (Float.TYPE.equals(cls)) {
            objValueOf = Float.valueOf(obj.toString());
        }
        if (Double.TYPE.equals(cls)) {
            objValueOf = Double.valueOf(obj.toString());
        }
        if (Boolean.TYPE.equals(cls)) {
            objValueOf = Boolean.valueOf(obj.toString());
        }
        if (Character.TYPE.equals(cls)) {
            objValueOf = Character.valueOf(obj.toString().charAt(0));
        }
        return cls.getName().equals("java.lang.String") ? obj.toString() : objValueOf;
    }

    public static float dp2px(Context context, float f) {
        return TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static float px2dp(Context context, float f) {
        return (f / context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    public static Object toV8Value(Object obj, JSContext jSContext) {
        int iIntValue;
        if ((obj instanceof Number) || (obj instanceof String) || (obj instanceof Boolean)) {
            return obj;
        }
        if (!(obj instanceof Map)) {
            if (!(obj instanceof List)) {
                return null;
            }
            V8Array v8ArrayMakeV8Array = jSContext.makeV8Array();
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                Object v8Value = toV8Value(it.next(), jSContext);
                v8ArrayMakeV8Array.push(v8Value);
                if (v8Value instanceof V8Value) {
                    ((V8Value) v8Value).close();
                }
            }
            return v8ArrayMakeV8Array;
        }
        V8Object v8ObjectMakeObject = jSContext.makeObject();
        for (Map.Entry entry : ((Map) obj).entrySet()) {
            if (entry.getKey() instanceof String) {
                String str = (String) entry.getKey();
                Object v8Value2 = toV8Value(entry.getValue(), jSContext);
                if ((v8Value2 instanceof Long) || (v8Value2 instanceof Double) || (v8Value2 instanceof Float)) {
                    v8ObjectMakeObject.add(str, ((Number) v8Value2).doubleValue());
                } else {
                    if (v8Value2 instanceof Integer) {
                        iIntValue = ((Integer) v8Value2).intValue();
                    } else if (v8Value2 instanceof Short) {
                        iIntValue = ((Short) v8Value2).intValue();
                    } else if (v8Value2 instanceof String) {
                        v8ObjectMakeObject.add(str, (String) v8Value2);
                    } else if (v8Value2 instanceof Boolean) {
                        v8ObjectMakeObject.add(str, ((Boolean) v8Value2).booleanValue());
                    } else if (v8Value2 instanceof V8Value) {
                        V8Value v8Value3 = (V8Value) v8Value2;
                        v8ObjectMakeObject.add(str, v8Value3);
                        v8Value3.close();
                    } else {
                        v8ObjectMakeObject.addNull(str);
                    }
                    v8ObjectMakeObject.add(str, iIntValue);
                }
            }
        }
        return v8ObjectMakeObject;
    }
}
