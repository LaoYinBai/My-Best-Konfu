package a.a.c;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.util.ArrayMap;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {
    @SuppressLint({"PrivateApi"})
    public static Activity a() throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Activity activity;
        Class<?> cls;
        Field declaredField;
        try {
            cls = Class.forName("com.unity3d.player.UnityPlayer");
            declaredField = cls.getDeclaredField("currentActivity");
        } catch (Throwable th) {
            th.printStackTrace();
            activity = null;
        }
        if (!declaredField.getType().toString().contains("Activity")) {
            throw new Exception("not activity");
        }
        declaredField.setAccessible(true);
        activity = (Activity) declaredField.get(cls);
        if (activity != null) {
            return activity;
        }
        try {
            Class<?> cls2 = Class.forName("android.app.ActivityThread");
            Object objInvoke = cls2.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
            Field declaredField2 = cls2.getDeclaredField("mActivities");
            declaredField2.setAccessible(true);
            Map map = Build.VERSION.SDK_INT < 19 ? (HashMap) declaredField2.get(objInvoke) : (ArrayMap) declaredField2.get(objInvoke);
            if (map.size() >= 1) {
                for (Object obj : map.values()) {
                    Class<?> cls3 = obj.getClass();
                    Field declaredField3 = cls3.getDeclaredField("paused");
                    declaredField3.setAccessible(true);
                    if (!declaredField3.getBoolean(obj)) {
                        Field declaredField4 = cls3.getDeclaredField(TTDownloadField.TT_ACTIVITY);
                        declaredField4.setAccessible(true);
                        return (Activity) declaredField4.get(obj);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
