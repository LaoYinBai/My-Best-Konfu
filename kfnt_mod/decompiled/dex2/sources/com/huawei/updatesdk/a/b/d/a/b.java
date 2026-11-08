package com.huawei.updatesdk.a.b.d.a;

import com.huawei.updatesdk.a.a.c.f;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class b {
    private static final char COMMA = ',';
    private static final String END_FLAG = "_";
    private static final String TAG = "b";

    private static class a implements PrivilegedAction {
        private Field a;

        a(Field field) {
            this.a = field;
        }

        @Override // java.security.PrivilegedAction
        public Object run() {
            this.a.setAccessible(true);
            return null;
        }
    }

    private String arrayToJson(Object obj) throws IllegalAccessException, IllegalArgumentException {
        int length = Array.getLength(obj);
        if (length <= 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < length; i++) {
            String strValueToJson = valueToJson(Array.get(obj, i));
            if (strValueToJson != null) {
                sb.append(strValueToJson);
                sb.append(COMMA);
            }
        }
        formatJsonStr(sb);
        sb.append("]");
        return sb.toString();
    }

    private void formatJsonStr(StringBuilder sb) {
        int length = sb.length();
        if (length > 0) {
            int i = length - 1;
            if (sb.charAt(i) == ',') {
                sb.delete(i, length);
            }
        }
    }

    private Object jsonBeanFromJson(Class cls, Object obj) throws JSONException, IllegalAccessException, InstantiationException, ClassNotFoundException, IllegalArgumentException {
        b bVar = (b) cls.newInstance();
        bVar.fromJson((JSONObject) obj);
        return bVar;
    }

    private String mapToJson(Map map) throws IllegalAccessException, IllegalArgumentException {
        if (map.size() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        Iterator it = map.entrySet().iterator();
        while (true) {
            Map.Entry entry = (Map.Entry) it.next();
            String str = (String) entry.getKey();
            String strValueToJson = valueToJson(entry.getValue());
            if (strValueToJson != null) {
                sb.append("\"");
                sb.append(str);
                sb.append("\":");
                sb.append(strValueToJson);
            }
            if (!it.hasNext()) {
                sb.append("}");
                return sb.toString();
            }
            if (strValueToJson != null) {
                sb.append(COMMA);
            }
        }
    }

    private void processValueError(Field field, Object obj) throws IllegalAccessException, IllegalArgumentException {
        Object objValueOf;
        if (obj == null || !(obj instanceof String)) {
            return;
        }
        try {
            Class<?> type = field.getType();
            if (type.isPrimitive()) {
                String name = type.getName();
                if ("int".equals(name)) {
                    objValueOf = Integer.valueOf(Integer.parseInt((String) obj));
                } else if ("float".equals(name)) {
                    objValueOf = Float.valueOf(Float.parseFloat((String) obj));
                } else if ("long".equals(name)) {
                    objValueOf = Long.valueOf(Long.parseLong((String) obj));
                } else if ("boolean".equals(name)) {
                    objValueOf = Boolean.valueOf(Boolean.parseBoolean((String) obj));
                } else if ("double".equals(name)) {
                    objValueOf = Double.valueOf(Double.parseDouble((String) obj));
                } else if ("short".equals(name)) {
                    objValueOf = Short.valueOf(Short.parseShort((String) obj));
                } else if ("byte".equals(name)) {
                    objValueOf = Byte.valueOf(Byte.parseByte((String) obj));
                } else if (!"char".equals(name)) {
                    return;
                } else {
                    objValueOf = Character.valueOf(((String) obj).charAt(0));
                }
                field.set(this, objValueOf);
            }
        } catch (IllegalAccessException e) {
            com.huawei.updatesdk.a.a.b.a.a.a.d(TAG, "processValueError Throwable:" + e.getMessage());
        }
    }

    private Object valueFromJson(Class cls, Class cls2, Object obj) throws IllegalAccessException, JSONException, InstantiationException, ClassNotFoundException, IllegalArgumentException {
        if (cls.isPrimitive() || cls.equals(String.class)) {
            return ("float".equals(cls.getName()) && (obj instanceof Double)) ? Float.valueOf(((Double) obj).floatValue()) : obj;
        }
        if (List.class.isAssignableFrom(cls)) {
            return listFromJson(cls2, obj);
        }
        if (b.class.isAssignableFrom(cls)) {
            if (!cls.equals(b.class)) {
                return jsonBeanFromJson(cls, obj);
            }
            throw new IllegalArgumentException("error type, type:" + cls);
        }
        if (Map.class.isAssignableFrom(cls)) {
            return mapFromJson(cls2, obj);
        }
        throw new IllegalArgumentException("unsupport type, Type:" + cls);
    }

    private String valueToJson(Object obj) throws IllegalAccessException, IllegalArgumentException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return JSONObject.quote(obj.toString());
        }
        if ((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Boolean) || (obj instanceof Float) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Double) || (obj instanceof Short)) {
            return String.valueOf(obj);
        }
        if (obj instanceof b) {
            return ((b) obj).toJson();
        }
        if (obj instanceof List) {
            return listToJson((List) obj);
        }
        if (obj instanceof Map) {
            return mapToJson((Map) obj);
        }
        if (obj.getClass().isArray()) {
            return arrayToJson(obj);
        }
        return null;
    }

    public void fromJson(JSONObject jSONObject) throws JSONException, IllegalAccessException, InstantiationException, ClassNotFoundException, IllegalArgumentException {
        Field field;
        Object objValueFromJson;
        Field[] fieldArrA = f.a(getClass());
        Object obj = null;
        for (int i = 0; i < fieldArrA.length; i++) {
            AccessController.doPrivileged(new a(fieldArrA[i]));
            String name = fieldArrA[i].getName();
            if (name.endsWith(END_FLAG)) {
                String strSubstring = name.substring(0, name.length() - END_FLAG.length());
                if (jSONObject.has(strSubstring)) {
                    Object obj2 = jSONObject.get(strSubstring);
                    if (!JSONObject.NULL.equals(obj2)) {
                        try {
                            objValueFromJson = valueFromJson(fieldArrA[i].getType(), f.a(fieldArrA[i]), obj2);
                        } catch (ClassNotFoundException e) {
                            e = e;
                        } catch (IllegalAccessException e2) {
                            e = e2;
                        } catch (IllegalArgumentException e3) {
                            e = e3;
                        } catch (InstantiationException e4) {
                            e = e4;
                        } catch (JSONException e5) {
                            e = e5;
                        }
                        try {
                            fieldArrA[i].set(this, objValueFromJson);
                            obj = objValueFromJson;
                        } catch (ClassNotFoundException e6) {
                            e = e6;
                            obj = objValueFromJson;
                            com.huawei.updatesdk.a.a.b.a.a.a.a(TAG, getClass().getName() + ".fromJson error, fieldName:" + strSubstring, e);
                            field = fieldArrA[i];
                            processValueError(field, obj);
                        } catch (IllegalAccessException e7) {
                            e = e7;
                            obj = objValueFromJson;
                            com.huawei.updatesdk.a.a.b.a.a.a.a(TAG, getClass().getName() + ".fromJson error, fieldName:" + strSubstring, e);
                            field = fieldArrA[i];
                            processValueError(field, obj);
                        } catch (IllegalArgumentException e8) {
                            e = e8;
                            obj = objValueFromJson;
                            com.huawei.updatesdk.a.a.b.a.a.a.a(TAG, getClass().getName() + ".fromJson error, fieldName:" + strSubstring, e);
                            field = fieldArrA[i];
                            processValueError(field, obj);
                        } catch (InstantiationException e9) {
                            e = e9;
                            obj = objValueFromJson;
                            com.huawei.updatesdk.a.a.b.a.a.a.a(TAG, getClass().getName() + ".fromJson error, fieldName:" + strSubstring, e);
                            field = fieldArrA[i];
                            processValueError(field, obj);
                        } catch (JSONException e10) {
                            e = e10;
                            obj = objValueFromJson;
                            com.huawei.updatesdk.a.a.b.a.a.a.a(TAG, getClass().getName() + ".fromJson error, fieldName:" + strSubstring, e);
                            field = fieldArrA[i];
                            processValueError(field, obj);
                        }
                    }
                }
            }
        }
    }

    protected Object listFromJson(Class cls, Object obj) throws IllegalAccessException, JSONException, InstantiationException, ClassNotFoundException, IllegalArgumentException {
        if (cls == null) {
            throw new IllegalArgumentException("generic type is null");
        }
        if (!(obj instanceof JSONArray)) {
            throw new IllegalArgumentException("jsonobject is not JSONArray, jsonValue:" + obj);
        }
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArray = (JSONArray) obj;
        for (int i = 0; i < jSONArray.length(); i++) {
            Object objValueFromJson = valueFromJson(cls, null, jSONArray.get(i));
            if (objValueFromJson != null) {
                if (cls.equals(objValueFromJson.getClass())) {
                    arrayList.add(objValueFromJson);
                } else {
                    com.huawei.updatesdk.a.a.b.a.a.a.d(TAG, "listFromJson error, memberClass:" + cls + ", valueClass:" + objValueFromJson.getClass());
                }
            }
        }
        return arrayList;
    }

    protected String listToJson(List list) throws IllegalAccessException, IllegalArgumentException {
        if (list.size() <= 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            String strValueToJson = valueToJson(list.get(i));
            if (strValueToJson != null) {
                sb.append(strValueToJson);
                sb.append(COMMA);
            }
        }
        formatJsonStr(sb);
        sb.append("]");
        return sb.toString();
    }

    protected Object mapFromJson(Class cls, Object obj) throws IllegalAccessException, JSONException, InstantiationException, ClassNotFoundException, IllegalArgumentException {
        if (cls == null) {
            throw new IllegalArgumentException("generic type is null");
        }
        if (!(obj instanceof JSONObject)) {
            throw new IllegalArgumentException("jsonobject is not JSONObject, jsonValue:" + obj);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        JSONObject jSONObject = (JSONObject) obj;
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            Object objValueFromJson = valueFromJson(cls, null, jSONObject.get(next));
            if (objValueFromJson != null) {
                if (cls.equals(objValueFromJson.getClass())) {
                    linkedHashMap.put(next, objValueFromJson);
                } else {
                    com.huawei.updatesdk.a.a.b.a.a.a.d(TAG, "mapFromJson error, memberClass:" + cls + ", valueClass:" + objValueFromJson.getClass());
                }
            }
        }
        return linkedHashMap;
    }

    public String toJson() throws IllegalAccessException, IllegalArgumentException {
        Field[] fieldArrA = f.a(getClass());
        if (fieldArrA.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < fieldArrA.length; i++) {
            AccessController.doPrivileged(new a(fieldArrA[i]));
            String name = fieldArrA[i].getName();
            if (name != null && name.endsWith(END_FLAG)) {
                String strSubstring = name.substring(0, name.length() - END_FLAG.length());
                String strValueToJson = valueToJson(fieldArrA[i].get(this));
                if (strValueToJson != null) {
                    sb.append("\"");
                    sb.append(strSubstring);
                    sb.append("\":");
                    sb.append(strValueToJson);
                    sb.append(COMMA);
                }
            }
        }
        formatJsonStr(sb);
        sb.append("}");
        return sb.toString();
    }
}
