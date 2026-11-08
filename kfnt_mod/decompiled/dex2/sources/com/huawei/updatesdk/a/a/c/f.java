package com.huawei.updatesdk.a.a.c;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class f {
    private static final String a = "f";

    public static Class a(Field field) {
        int i;
        if (Map.class.isAssignableFrom(field.getType())) {
            i = 1;
        } else {
            if (!List.class.isAssignableFrom(field.getType())) {
                return null;
            }
            i = 0;
        }
        return a(field, i);
    }

    private static Class a(Field field, int i) {
        Type[] actualTypeArguments;
        Type genericType = field.getGenericType();
        if ((genericType instanceof ParameterizedType) && (actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments()) != null && actualTypeArguments.length > i) {
            try {
                if (actualTypeArguments[i] instanceof Class) {
                    return (Class) actualTypeArguments[i];
                }
                String string = actualTypeArguments[i].toString();
                int iIndexOf = string.indexOf("class ");
                if (iIndexOf < 0) {
                    iIndexOf = 0;
                }
                int iIndexOf2 = string.indexOf("<");
                if (iIndexOf2 < 0) {
                    iIndexOf2 = string.length();
                }
                return Class.forName(string.substring(iIndexOf, iIndexOf2));
            } catch (ClassNotFoundException e) {
                com.huawei.updatesdk.a.a.b.a.a.a.d(a, "getType exception!" + e.getMessage());
            }
        }
        return null;
    }

    public static Field[] a(Class cls) {
        Field[] fieldArrA = cls.getSuperclass() != null ? a(cls.getSuperclass()) : null;
        Field[] declaredFields = cls.getDeclaredFields();
        if (fieldArrA != null && fieldArrA.length > 0) {
            Field[] fieldArr = new Field[declaredFields.length + fieldArrA.length];
            System.arraycopy(fieldArrA, 0, fieldArr, 0, fieldArrA.length);
            System.arraycopy(declaredFields, 0, fieldArr, fieldArrA.length, declaredFields.length);
            declaredFields = fieldArr;
        }
        ArrayList arrayList = new ArrayList();
        for (Field field : declaredFields) {
            if (field.getName().indexOf("$") < 0) {
                arrayList.add(field);
            }
        }
        if (arrayList.size() == declaredFields.length) {
            return declaredFields;
        }
        Field[] fieldArr2 = new Field[arrayList.size()];
        arrayList.toArray(fieldArr2);
        return fieldArr2;
    }
}
