package com.huawei.hms.support.api.push.c;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.android.HwBuildEx;
import com.huawei.hms.support.log.HMSLog;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a {
    private static final Object a = new Object();
    private static int b = -1;

    private static boolean c(Context context) {
        HMSLog.d("CommFun", "existFrameworkPush:" + b);
        try {
            File file = new File("/system/framework/hwpush.jar");
            if (b()) {
                HMSLog.d("CommFun", "push jarFile is exist");
                return true;
            }
            if (!file.isFile()) {
                return false;
            }
            HMSLog.d("CommFun", "push jarFile is exist");
            return true;
        } catch (Exception e) {
            HMSLog.e("CommFun", "get Apk version faild ,Exception e= " + e.toString());
            return false;
        }
    }

    public static boolean a(Context context) {
        HMSLog.d("CommFun", "existFrameworkPush:" + b);
        synchronized (a) {
            if (-1 != b) {
                return 1 == b;
            }
            if (c(context)) {
                b = 1;
            } else {
                b = 0;
            }
            return 1 == b;
        }
    }

    private static boolean b() throws ClassNotFoundException {
        try {
            Class<?> cls = Class.forName("huawei.cust.HwCfgFilePolicy");
            File file = (File) cls.getDeclaredMethod("getCfgFile", String.class, Integer.TYPE).invoke(cls, "jars/hwpush.jar", Integer.valueOf(((Integer) cls.getDeclaredField("CUST_TYPE_CONFIG").get(cls)).intValue()));
            if (file != null && file.exists()) {
                HMSLog.d("CommFun", "get push cust File path is " + file.getCanonicalPath());
                return true;
            }
        } catch (IOException unused) {
            HMSLog.e("CommFun", "check jarFile exist but get not path");
        } catch (ClassNotFoundException unused2) {
            HMSLog.e("CommFun", "HwCfgFilePolicy ClassNotFoundException");
        } catch (IllegalAccessException unused3) {
            HMSLog.e("CommFun", "check cust exist push IllegalAccessException.");
        } catch (IllegalArgumentException unused4) {
            HMSLog.e("CommFun", "check cust exist push IllegalArgumentException.");
        } catch (NoSuchFieldException unused5) {
            HMSLog.e("CommFun", "check cust exist push NoSuchFieldException.");
        } catch (NoSuchMethodException unused6) {
            HMSLog.e("CommFun", "check cust exist push NoSuchMethodException.");
        } catch (SecurityException unused7) {
            HMSLog.e("CommFun", "check cust exist push SecurityException.");
        } catch (InvocationTargetException unused8) {
            HMSLog.e("CommFun", "check cust exist push InvocationTargetException.");
        }
        return false;
    }

    public static long b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.huawei.android.pushagent", 0).versionCode;
        } catch (Exception unused) {
            HMSLog.e("CommFun", "get nc versionCode error");
            return -1L;
        }
    }

    public static boolean a() {
        return HwBuildEx.VERSION.EMUI_SDK_INT >= 21;
    }

    public static boolean a(JSONObject jSONObject, JSONObject jSONObject2, String str) {
        return jSONObject == null || (TextUtils.isEmpty(str) && jSONObject2 == null);
    }
}
