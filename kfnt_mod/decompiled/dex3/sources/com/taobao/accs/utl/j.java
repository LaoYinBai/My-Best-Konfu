package com.taobao.accs.utl;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;
import com.ta.utdid2.device.UTDevice;
import com.umeng.message.entity.UMessage;
import java.io.File;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class j {
    public static String a = "";
    public static String b = null;
    public static com.taobao.accs.e c = null;
    public static final String channelService = "com.taobao.accs.ChannelService";
    private static String d = "";
    private static String e = "";
    private static boolean f = true;
    private static boolean g = false;
    public static final String msgService = "com.taobao.accs.data.MsgDistributeService";

    public static boolean a(Context context) {
        String str;
        if (g) {
            return f;
        }
        try {
            if (TextUtils.isEmpty(com.taobao.accs.client.a.d)) {
                if (TextUtils.isEmpty(d)) {
                    d = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.processName;
                }
                str = d;
            } else {
                str = com.taobao.accs.client.a.d;
            }
            if (TextUtils.isEmpty(e)) {
                e = a(context, Process.myPid());
            }
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(e)) {
                f = str.equalsIgnoreCase(e);
                g = true;
            }
        } catch (Throwable th) {
            ALog.e("AdapterUtilityImpl", "isMainProcess", th, new Object[0]);
        }
        return f;
    }

    public static String a(Context context, int i) {
        if (com.taobao.accs.client.a.f != null) {
            return com.taobao.accs.client.a.f.getCurrProcessName();
        }
        String str = "";
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : com.taobao.accs.client.a.a(context).a().getRunningAppProcesses()) {
            try {
                if (runningAppProcessInfo.pid == i) {
                    str = runningAppProcessInfo.processName;
                }
            } catch (Exception unused) {
            }
        }
        return str;
    }

    public static long a() {
        try {
            File dataDirectory = Environment.getDataDirectory();
            if (dataDirectory == null) {
                return -1L;
            }
            if (Build.VERSION.SDK_INT >= 9) {
                return dataDirectory.getUsableSpace();
            }
            if (!dataDirectory.exists()) {
                return -1L;
            }
            StatFs statFs = new StatFs(dataDirectory.getPath());
            return statFs.getBlockSize() * statFs.getAvailableBlocks();
        } catch (Throwable th) {
            ALog.e("AdapterUtilityImpl", "getUsableSpace", th, new Object[0]);
            return -1L;
        }
    }

    public static String a(Throwable th) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null && stackTrace.length > 0) {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    stringBuffer.append(stackTraceElement.toString());
                    stringBuffer.append("\n");
                }
            }
        } catch (Exception unused) {
        }
        return stringBuffer.toString();
    }

    public static String b(Context context) {
        if (c == null) {
            return UTDevice.getUtdid(context);
        }
        return c.a();
    }

    public static boolean c(Context context) {
        if (context == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = com.taobao.accs.client.a.a(context).b().getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnected();
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static final boolean a(String str, int i) {
        if (str == null) {
            return false;
        }
        StatFs statFs = new StatFs(str);
        int blockSize = statFs.getBlockSize();
        long availableBlocks = statFs.getAvailableBlocks();
        StringBuilder sb = new StringBuilder();
        sb.append("st.getAvailableBlocks()=");
        sb.append(statFs.getAvailableBlocks());
        sb.append(",st.getAvailableBlocks() * blockSize=");
        long j = blockSize;
        sb.append(statFs.getAvailableBlocks() * j);
        Log.d("FileCheckUtils", sb.toString());
        return statFs.getAvailableBlocks() > 10 && availableBlocks * j > ((long) i);
    }

    public static String d(Context context) {
        String strValueOf;
        boolean z = true;
        if (w.a(context)) {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            String packageName = context.getApplicationContext().getPackageName();
            int i = applicationInfo.uid;
            try {
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
                Method declaredMethod = notificationManager.getClass().getDeclaredMethod("getService", new Class[0]);
                declaredMethod.setAccessible(true);
                Object objInvoke = declaredMethod.invoke(notificationManager, new Object[0]);
                Method declaredMethod2 = objInvoke.getClass().getDeclaredMethod("areNotificationsEnabledForPackage", String.class, Integer.TYPE);
                declaredMethod2.setAccessible(true);
                strValueOf = String.valueOf(declaredMethod2.invoke(objInvoke, packageName, Integer.valueOf(i)));
            } catch (Throwable th) {
                ALog.e("AdapterUtilityImpl", "Android O isNotificationEnabled", th, new Object[0]);
                return UtilityImpl.NET_TYPE_UNKNOWN;
            }
        } else {
            try {
                AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
                ApplicationInfo applicationInfo2 = context.getApplicationInfo();
                String packageName2 = context.getApplicationContext().getPackageName();
                int i2 = applicationInfo2.uid;
                Class<?> cls = Class.forName(AppOpsManager.class.getName());
                if (((Integer) cls.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class).invoke(appOpsManager, Integer.valueOf(((Integer) cls.getDeclaredField("OP_POST_NOTIFICATION").get(appOpsManager)).intValue()), Integer.valueOf(i2), packageName2)).intValue() != 0) {
                    z = false;
                }
                strValueOf = String.valueOf(z);
            } catch (Throwable th2) {
                ALog.e("AdapterUtilityImpl", "isNotificationEnabled", th2, new Object[0]);
                return UtilityImpl.NET_TYPE_UNKNOWN;
            }
        }
        return strValueOf;
    }
}
