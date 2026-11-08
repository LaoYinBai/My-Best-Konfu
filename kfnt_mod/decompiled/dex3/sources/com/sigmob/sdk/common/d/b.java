package com.sigmob.sdk.common.d;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import com.sigmob.logger.SigmobLog;
import com.xiaomi.mipush.sdk.Constants;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    private static Pattern a = Pattern.compile("_(\\w)");

    @SuppressLint({"UseSparseArrays"})
    public static String a(Object obj) {
        return a(obj, new HashMap(), null, false);
    }

    public static String a(Object obj, String str, boolean z) {
        return a(obj, new HashMap(), str, z);
    }

    private static String a(Object obj, HashMap<Integer, Object> map, String str, boolean z) {
        if (obj == null) {
            return "null";
        }
        String strB = null;
        Class<?> cls = obj.getClass();
        if (obj instanceof String) {
            strB = b(((String) obj).replace("\"", "'"));
        } else if (cls.isPrimitive() || cls == Boolean.class || cls == Short.class || cls == Integer.class || cls == Long.class || cls == Float.class || cls == Double.class || cls == Byte.class || cls == Character.class) {
            strB = b(obj);
        } else if (map.get(Integer.valueOf(obj.hashCode())) != null) {
            return "null";
        }
        map.put(Integer.valueOf(obj.hashCode()), obj);
        if (TextUtils.isEmpty(strB)) {
            if (obj instanceof List) {
                List list = (List) obj;
                obj = list.toArray(new Object[list.size()]);
            } else if (obj instanceof Map) {
                strB = a((Map) obj, map, z);
            } else if (!cls.isArray()) {
                strB = a(obj, map, z);
            }
            strB = b(obj, map, z);
        }
        if (TextUtils.isEmpty(str)) {
            return strB;
        }
        StringBuilder sb = new StringBuilder("{");
        if (z) {
            str = a(str);
        }
        sb.append(b(str));
        sb.append(':');
        sb.append(strB);
        sb.append('}');
        return sb.toString();
    }

    private static String a(Object obj, HashMap<Integer, Object> map, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean z2 = false;
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object obj2 = field.get(obj);
                String name = field.getName();
                boolean zIsStatic = Modifier.isStatic(field.getModifiers());
                boolean zIsAnnotationPresent = field.isAnnotationPresent(c.class);
                if (!name.startsWith("this$") && !zIsStatic && (obj2 == null || !zIsAnnotationPresent)) {
                    if (z2) {
                        sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                    }
                    String strB = b(name);
                    if (z) {
                        strB = a(strB);
                    }
                    sb.append(strB);
                    sb.append(Constants.COLON_SEPARATOR);
                    sb.append(a(obj2, map, null, z));
                    z2 = true;
                }
            } catch (Throwable th) {
                SigmobLog.e("json seriallize error", th);
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private static String a(String str) {
        if (!a.matcher(str).find()) {
            return str;
        }
        Matcher matcher = a.matcher(str.toLowerCase());
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    private static String a(Map map, HashMap<Integer, Object> map2, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean z2 = false;
        for (Map.Entry entry : map.entrySet()) {
            String string = entry.getKey().toString();
            Object value = entry.getValue();
            if (z2) {
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
            }
            String strB = b(string);
            if (z) {
                strB = a(strB);
            }
            sb.append(strB);
            sb.append(Constants.COLON_SEPARATOR);
            sb.append(a(value, map2, null, z));
            z2 = true;
        }
        sb.append("}");
        return sb.toString();
    }

    private static String b(Object obj) {
        return obj.toString();
    }

    private static String b(Object obj, HashMap<Integer, Object> map, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            sb.append(a(Array.get(obj, i), map, null, z));
            if (i < length - 1) {
                sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static String b(String str) {
        return "\"" + str + "\"";
    }
}
