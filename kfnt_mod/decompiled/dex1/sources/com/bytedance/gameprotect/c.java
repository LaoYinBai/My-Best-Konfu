package com.bytedance.gameprotect;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {
    public static Activity a() {
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object objInvoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mActivities");
            declaredField.setAccessible(true);
            for (Object obj : ((Map) declaredField.get(objInvoke)).values()) {
                Class<?> cls2 = obj.getClass();
                Field declaredField2 = cls2.getDeclaredField("paused");
                declaredField2.setAccessible(true);
                if (!declaredField2.getBoolean(obj)) {
                    Field declaredField3 = cls2.getDeclaredField(TTDownloadField.TT_ACTIVITY);
                    declaredField3.setAccessible(true);
                    return (Activity) declaredField3.get(obj);
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static View a(String str) {
        View viewB = b();
        if (viewB == null) {
            return null;
        }
        if (str.equals(" ")) {
            return viewB;
        }
        for (View view : a(viewB)) {
            if (view.toString().startsWith(str)) {
                return view;
            }
        }
        return null;
    }

    public static String a(String str, String str2, String str3) {
        try {
            Class<?> cls = Class.forName(str);
            Method declaredMethod = cls.getDeclaredMethod(str2, new Class[0]);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField(str3);
            declaredField.setAccessible(true);
            return ((Instrumentation) declaredField.get(objInvoke)).getClass().getName();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static List<View> a(View view) {
        ArrayList arrayList = new ArrayList();
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childAt = viewGroup.getChildAt(i);
                arrayList.add(childAt);
                arrayList.addAll(a(childAt));
            }
        }
        return arrayList;
    }

    public static View b() {
        Window window;
        Activity activityA = a();
        if (activityA == null || (window = activityA.getWindow()) == null) {
            return null;
        }
        return window.getDecorView();
    }

    public static DisplayMetrics c() {
        Resources resources;
        Activity activityA = a();
        if (activityA == null || (resources = activityA.getResources()) == null) {
            return null;
        }
        return resources.getDisplayMetrics();
    }
}
