package com.huawei.updatesdk.a.b.d.a;

import com.huawei.hms.framework.common.ContainerUtils;
import com.huawei.updatesdk.a.a.c.f;
import com.huawei.updatesdk.a.a.c.g;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    public static final String CLIENT_API = "clientApi";
    private static final String END_FLAG = "_";
    private static String url;
    private String method_;
    private String ver_ = "1.1";

    private String a(Field field) throws IllegalAccessException, IllegalArgumentException {
        Object obj = field.get(this);
        if (obj != null && (obj instanceof b)) {
            return ((b) obj).toJson();
        }
        if (obj != null) {
            return String.valueOf(obj);
        }
        return null;
    }

    public static void a(String str) {
        url = str;
    }

    public String a() {
        return this.method_;
    }

    public String b() throws IllegalAccessException, ArrayIndexOutOfBoundsException, IllegalArgumentException {
        c();
        Map<String, Field> mapE = e();
        String[] strArr = new String[mapE.size()];
        mapE.keySet().toArray(strArr);
        Arrays.sort(strArr);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        do {
            String strA = a(mapE.get(strArr[i]));
            if (strA != null) {
                String strA2 = g.a(strA);
                sb.append(strArr[i]);
                sb.append(ContainerUtils.KEY_VALUE_DELIMITER);
                sb.append(strA2);
                sb.append(ContainerUtils.FIELD_DELIMITER);
            }
            i++;
        } while (i < strArr.length);
        int length = sb.length();
        if (length > 0) {
            int i2 = length - 1;
            if (sb.charAt(i2) == '&') {
                sb.deleteCharAt(i2);
            }
        }
        return sb.toString();
    }

    public void b(String str) {
        this.method_ = str;
    }

    protected void c() {
    }

    public void c(String str) {
        this.ver_ = str;
    }

    public String d() {
        return url + CLIENT_API;
    }

    protected Map<String, Field> e() {
        HashMap map = new HashMap();
        for (Field field : f.a(getClass())) {
            field.setAccessible(true);
            String name = field.getName();
            if (name.endsWith(END_FLAG)) {
                map.put(name.substring(0, name.length() - END_FLAG.length()), field);
            }
        }
        return map;
    }
}
