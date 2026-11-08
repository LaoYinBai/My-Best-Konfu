package com.huawei.updatesdk.a.b.d;

import com.huawei.updatesdk.a.b.d.a.d;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final Map<String, Class> a = new HashMap();

    public static d a(String str) throws IllegalAccessException, InstantiationException {
        Class cls = a.get(str);
        if (cls != null) {
            return (d) cls.newInstance();
        }
        throw new InstantiationException("ResponseBean class not found, method:" + str);
    }

    public static void a(String str, Class cls) {
        a.put(str, cls);
    }
}
