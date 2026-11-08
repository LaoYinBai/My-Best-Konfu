package com.qq.e.comm.util;

import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CallbackUtil {
    private static final Map<String, Boolean> a = new HashMap();

    private static boolean a(Class cls, String str, Class... clsArr) throws NoSuchMethodException, SecurityException {
        String string;
        if (cls == null) {
            string = "";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(cls.getName());
            sb.append("#");
            sb.append(str);
            for (Class cls2 : clsArr) {
                sb.append("_");
                sb.append(cls2.getName());
            }
            string = sb.toString();
        }
        Boolean bool = a.get(string);
        if (bool != null) {
            return Boolean.TRUE.equals(bool);
        }
        try {
            cls.getDeclaredMethod(str, clsArr);
            a.put(string, Boolean.TRUE);
            return true;
        } catch (NoSuchMethodException unused) {
            a.put(string, Boolean.FALSE);
            return false;
        }
    }

    public static boolean hasRenderFailCallback(Object obj) {
        if (obj == null) {
            return false;
        }
        return a(obj.getClass(), "onRenderFail", new Class[0]);
    }

    public static boolean hasRenderSuccessCallback(Object obj) {
        if (obj == null) {
            return false;
        }
        return a(obj.getClass(), "onRenderSuccess", new Class[0]);
    }
}
