package com.facebook.stetho.json;

import com.facebook.stetho.common.ExceptionUtil;
import com.facebook.stetho.json.annotation.JsonProperty;
import com.facebook.stetho.json.annotation.JsonValue;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ObjectMapper {

    @GuardedBy("mJsonValueMethodCache")
    private final Map<Class<?>, Method> mJsonValueMethodCache = new IdentityHashMap();

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T convertValue(Object obj, Class<T> cls) throws IllegalArgumentException {
        if (obj == 0) {
            return null;
        }
        if (cls != Object.class && cls.isAssignableFrom(obj.getClass())) {
            return obj;
        }
        try {
            if (obj instanceof JSONObject) {
                return (T) _convertFromJSONObject((JSONObject) obj, cls);
            }
            if (cls == JSONObject.class) {
                return (T) _convertToJSONObject(obj);
            }
            throw new IllegalArgumentException("Expecting either fromValue or toValueType to be a JSONObject");
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        } catch (InstantiationException e2) {
            throw new IllegalArgumentException(e2);
        } catch (NoSuchMethodException e3) {
            throw new IllegalArgumentException(e3);
        } catch (InvocationTargetException e4) {
            throw ExceptionUtil.propagate(e4.getCause());
        } catch (JSONException e5) {
            throw new IllegalArgumentException(e5);
        }
    }

    private <T> T _convertFromJSONObject(JSONObject jSONObject, Class<T> cls) throws IllegalAccessException, JSONException, NoSuchMethodException, InstantiationException, SecurityException, IllegalArgumentException, InvocationTargetException {
        Constructor<T> declaredConstructor = cls.getDeclaredConstructor((Class[]) null);
        declaredConstructor.setAccessible(true);
        T tNewInstance = declaredConstructor.newInstance(new Object[0]);
        for (Field field : cls.getFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                Object valueForField = getValueForField(field, jSONObject.opt(field.getName()));
                try {
                    field.set(tNewInstance, valueForField);
                } catch (IllegalArgumentException e) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Class: ");
                    sb.append(cls.getSimpleName());
                    sb.append(" Field: ");
                    sb.append(field.getName());
                    sb.append(" type ");
                    sb.append(valueForField != null ? valueForField.getClass().getName() : "null");
                    throw new IllegalArgumentException(sb.toString(), e);
                }
            }
        }
        return tNewInstance;
    }

    private Object getValueForField(Field field, Object obj) throws JSONException {
        if (obj != null) {
            try {
                if (obj == JSONObject.NULL) {
                    return null;
                }
                if (obj.getClass() == field.getType()) {
                    return obj;
                }
                if (obj instanceof JSONObject) {
                    return convertValue(obj, field.getType());
                }
                if (field.getType().isEnum()) {
                    return getEnumValue((String) obj, field.getType().asSubclass(Enum.class));
                }
                if (obj instanceof JSONArray) {
                    return convertArrayToList(field, (JSONArray) obj);
                }
                if (obj instanceof Number) {
                    Number number = (Number) obj;
                    Class<?> type = field.getType();
                    if (type != Integer.class && type != Integer.TYPE) {
                        if (type != Long.class && type != Long.TYPE) {
                            if (type != Double.class && type != Double.TYPE) {
                                if (type != Float.class && type != Float.TYPE) {
                                    if (type != Byte.class && type != Byte.TYPE) {
                                        if (type != Short.class && type != Short.TYPE) {
                                            throw new IllegalArgumentException("Not setup to handle class " + type.getName());
                                        }
                                        return Short.valueOf(number.shortValue());
                                    }
                                    return Byte.valueOf(number.byteValue());
                                }
                                return Float.valueOf(number.floatValue());
                            }
                            return Double.valueOf(number.doubleValue());
                        }
                        return Long.valueOf(number.longValue());
                    }
                    return Integer.valueOf(number.intValue());
                }
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException("Unable to set value for field " + field.getName(), e);
            }
        }
        return obj;
    }

    private Enum getEnumValue(String str, Class<? extends Enum> cls) {
        Method jsonValueMethod = getJsonValueMethod(cls);
        if (jsonValueMethod != null) {
            return getEnumByMethod(str, cls, jsonValueMethod);
        }
        return Enum.valueOf(cls, str);
    }

    private Enum getEnumByMethod(String str, Class<? extends Enum> cls, Method method) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        for (Enum r3 : (Enum[]) cls.getEnumConstants()) {
            try {
                Object objInvoke = method.invoke(r3, new Object[0]);
                if (objInvoke != null && objInvoke.toString().equals(str)) {
                    return r3;
                }
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }
        throw new IllegalArgumentException("No enum constant " + cls.getName() + "." + str);
    }

    private List<Object> convertArrayToList(Field field, JSONArray jSONArray) throws JSONException, IllegalAccessException {
        if (List.class.isAssignableFrom(field.getType())) {
            Type[] actualTypeArguments = ((ParameterizedType) field.getGenericType()).getActualTypeArguments();
            if (actualTypeArguments.length != 1) {
                throw new IllegalArgumentException("Only able to handle a single type in a list " + field.getName());
            }
            Class<? extends Enum> cls = (Class) actualTypeArguments[0];
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                if (cls.isEnum()) {
                    arrayList.add(getEnumValue(jSONArray.getString(i), cls));
                } else if (canDirectlySerializeClass(cls)) {
                    arrayList.add(jSONArray.get(i));
                } else {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (jSONObject == null) {
                        arrayList.add(null);
                    } else {
                        arrayList.add(convertValue(jSONObject, cls));
                    }
                }
            }
            return arrayList;
        }
        throw new IllegalArgumentException("only know how to deserialize List<?> on field " + field.getName());
    }

    private JSONObject _convertToJSONObject(Object obj) throws IllegalAccessException, JSONException, SecurityException, IllegalArgumentException, InvocationTargetException {
        JsonProperty jsonProperty;
        JSONObject jSONObject = new JSONObject();
        for (Field field : obj.getClass().getFields()) {
            if (!Modifier.isStatic(field.getModifiers()) && (jsonProperty = (JsonProperty) field.getAnnotation(JsonProperty.class)) != null) {
                Object jsonValue = field.get(obj);
                Class<?> type = field.getType();
                if (jsonValue != null) {
                    type = jsonValue.getClass();
                }
                String name = field.getName();
                if (jsonProperty.required() && jsonValue == null) {
                    jsonValue = JSONObject.NULL;
                } else if (jsonValue != JSONObject.NULL) {
                    jsonValue = getJsonValue(jsonValue, type, field);
                }
                jSONObject.put(name, jsonValue);
            }
        }
        return jSONObject;
    }

    private Object getJsonValue(Object obj, Class<?> cls, Field field) throws IllegalAccessException, InvocationTargetException {
        if (obj == null) {
            return null;
        }
        if (List.class.isAssignableFrom(cls)) {
            return convertListToJsonArray(obj);
        }
        Method jsonValueMethod = getJsonValueMethod(cls);
        if (jsonValueMethod != null) {
            return jsonValueMethod.invoke(obj, new Object[0]);
        }
        if (!canDirectlySerializeClass(cls)) {
            return convertValue(obj, JSONObject.class);
        }
        if (cls.equals(Double.class) || cls.equals(Float.class)) {
            double dDoubleValue = ((Number) obj).doubleValue();
            if (Double.isNaN(dDoubleValue)) {
                return "NaN";
            }
            if (dDoubleValue == Double.POSITIVE_INFINITY) {
                return "Infinity";
            }
            if (dDoubleValue == Double.NEGATIVE_INFINITY) {
                return "-Infinity";
            }
        }
        return obj;
    }

    private JSONArray convertListToJsonArray(Object obj) throws IllegalAccessException, InvocationTargetException {
        JSONArray jSONArray = new JSONArray();
        for (Object obj2 : (List) obj) {
            Object jsonValue = null;
            if (obj2 != null) {
                jsonValue = getJsonValue(obj2, obj2.getClass(), null);
            }
            jSONArray.put(jsonValue);
        }
        return jSONArray;
    }

    @Nullable
    private Method getJsonValueMethod(Class<?> cls) {
        Method jsonValueMethodImpl;
        synchronized (this.mJsonValueMethodCache) {
            jsonValueMethodImpl = this.mJsonValueMethodCache.get(cls);
            if (jsonValueMethodImpl == null && !this.mJsonValueMethodCache.containsKey(cls)) {
                jsonValueMethodImpl = getJsonValueMethodImpl(cls);
                this.mJsonValueMethodCache.put(cls, jsonValueMethodImpl);
            }
        }
        return jsonValueMethodImpl;
    }

    @Nullable
    private static Method getJsonValueMethodImpl(Class<?> cls) throws SecurityException {
        Method[] methods = cls.getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getAnnotation(JsonValue.class) != null) {
                return methods[i];
            }
        }
        return null;
    }

    private static boolean canDirectlySerializeClass(Class cls) {
        return isWrapperOrPrimitiveType(cls) || cls.equals(String.class);
    }

    private static boolean isWrapperOrPrimitiveType(Class<?> cls) {
        return cls.isPrimitive() || cls.equals(Boolean.class) || cls.equals(Integer.class) || cls.equals(Character.class) || cls.equals(Byte.class) || cls.equals(Short.class) || cls.equals(Double.class) || cls.equals(Long.class) || cls.equals(Float.class);
    }
}
