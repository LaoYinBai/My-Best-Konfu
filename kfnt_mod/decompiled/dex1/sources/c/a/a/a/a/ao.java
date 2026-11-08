package c.a.a.a.a;

import com.bytedance.JProtect;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@JProtect
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ao {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<Class<?>, Class<?>> f2066a;

    static {
        HashMap map = new HashMap();
        f2066a = map;
        map.put(Boolean.class, Boolean.TYPE);
        f2066a.put(Byte.class, Byte.TYPE);
        f2066a.put(Character.class, Character.TYPE);
        f2066a.put(Short.class, Short.TYPE);
        f2066a.put(Integer.class, Integer.TYPE);
        f2066a.put(Float.class, Float.TYPE);
        f2066a.put(Long.class, Long.TYPE);
        f2066a.put(Double.class, Double.TYPE);
        Map<Class<?>, Class<?>> map2 = f2066a;
        Class<?> cls = Boolean.TYPE;
        map2.put(cls, cls);
        Map<Class<?>, Class<?>> map3 = f2066a;
        Class<?> cls2 = Byte.TYPE;
        map3.put(cls2, cls2);
        Map<Class<?>, Class<?>> map4 = f2066a;
        Class<?> cls3 = Character.TYPE;
        map4.put(cls3, cls3);
        Map<Class<?>, Class<?>> map5 = f2066a;
        Class<?> cls4 = Short.TYPE;
        map5.put(cls4, cls4);
        Map<Class<?>, Class<?>> map6 = f2066a;
        Class<?> cls5 = Integer.TYPE;
        map6.put(cls5, cls5);
        Map<Class<?>, Class<?>> map7 = f2066a;
        Class<?> cls6 = Float.TYPE;
        map7.put(cls6, cls6);
        Map<Class<?>, Class<?>> map8 = f2066a;
        Class<?> cls7 = Long.TYPE;
        map8.put(cls7, cls7);
        Map<Class<?>, Class<?>> map9 = f2066a;
        Class<?> cls8 = Double.TYPE;
        map9.put(cls8, cls8);
    }

    public static Method a(Class<?> cls, String str, Class... clsArr) throws NoSuchMethodException {
        Method methodA = a(cls.getDeclaredMethods(), str, (Class<?>[]) clsArr);
        if (methodA != null) {
            methodA.setAccessible(true);
            return methodA;
        }
        if (cls.getSuperclass() != null) {
            return a(cls.getSuperclass(), str, clsArr);
        }
        throw new NoSuchMethodException();
    }

    private static Method a(Method[] methodArr, String str, Class<?>[] clsArr) {
        if (str == null) {
            throw new NullPointerException("Method name must not be null.");
        }
        for (Method method : methodArr) {
            if (method.getName().equals(str) && a(method.getParameterTypes(), clsArr)) {
                return method;
            }
        }
        return null;
    }

    private static boolean a(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr == null) {
            return clsArr2 == null || clsArr2.length == 0;
        }
        if (clsArr2 == null) {
            return clsArr.length == 0;
        }
        if (clsArr.length != clsArr2.length) {
            return false;
        }
        for (int i = 0; i < clsArr.length; i++) {
            if (!clsArr[i].isAssignableFrom(clsArr2[i]) && (!f2066a.containsKey(clsArr[i]) || !f2066a.get(clsArr[i]).equals(f2066a.get(clsArr2[i])))) {
                return false;
            }
        }
        return true;
    }
}
