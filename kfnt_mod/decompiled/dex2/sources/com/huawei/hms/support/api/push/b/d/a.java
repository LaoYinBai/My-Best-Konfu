package com.huawei.hms.support.api.push.b.d;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.support.log.HMSLog;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    public static String a(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 128)).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            HMSLog.i("PushSelfShowLog", "get the app name of package:" + str + " failed.");
            return null;
        }
    }

    public static Intent b(Context context, String str) {
        try {
            return context.getPackageManager().getLaunchIntentForPackage(str);
        } catch (Exception unused) {
            HMSLog.w("PushSelfShowLog", str + "not have launch activity");
            return null;
        }
    }

    public static boolean c(Context context, String str) {
        if (context == null || str == null || "".equals(str)) {
            return false;
        }
        try {
            if (context.getPackageManager().getApplicationInfo(str, 8192) == null) {
                return false;
            }
            HMSLog.d("PushSelfShowLog", str + " is installed");
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static Boolean a(Context context, String str, Intent intent) {
        try {
            List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            if (listQueryIntentActivities != null && listQueryIntentActivities.size() > 0) {
                int size = listQueryIntentActivities.size();
                for (int i = 0; i < size; i++) {
                    if (listQueryIntentActivities.get(i).activityInfo != null && str.equals(listQueryIntentActivities.get(i).activityInfo.applicationInfo.packageName)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            HMSLog.e("PushSelfShowLog", e.toString(), e);
        }
        return false;
    }

    public static boolean a() {
        return Build.VERSION.SDK_INT >= 11;
    }

    public static long a(String str) {
        if (str == null) {
            str = "";
        }
        try {
            Date date = new Date();
            int hours = (date.getHours() * 2) + (date.getMinutes() / 30);
            String strConcat = str.concat(str);
            HMSLog.i("PushSelfShowLog", "startIndex is " + hours + ",ap is:" + strConcat + ",length is:" + strConcat.length());
            int length = strConcat.length();
            for (int i = hours; i < length; i++) {
                if (strConcat.charAt(i) != '0') {
                    long minutes = 60000 * (((i - hours) * 30) - (date.getMinutes() % 30));
                    HMSLog.d("PushSelfShowLog", "startIndex is:" + hours + " i is:" + i + " delay:" + minutes);
                    if (minutes >= 0) {
                        return minutes;
                    }
                    return 0L;
                }
            }
        } catch (Exception e) {
            HMSLog.e("PushSelfShowLog", "error ", e);
        }
        return 0L;
    }

    @SuppressLint({"TrulyRandom"})
    public static void a(Context context, Intent intent, long j) {
        try {
            HMSLog.d("PushSelfShowLog", "enter setAPDelayAlarm(intent:" + intent.toURI() + " interval:" + j + "ms, context:" + context);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            if (alarmManager != null) {
                alarmManager.set(0, System.currentTimeMillis() + j, PendingIntent.getBroadcast(context, new SecureRandom().nextInt(), intent, 0));
            }
        } catch (Exception e) {
            HMSLog.w("PushSelfShowLog", "set DelayAlarm error" + e.toString());
        }
    }

    public static boolean a(Context context) {
        return "com.huawei.android.pushagent".equals(context.getPackageName());
    }

    public static boolean b(Context context) {
        return "com.huawei.hwid".equals(context.getPackageName());
    }

    public static boolean a(Context context, Intent intent) {
        if (context == null) {
            HMSLog.i("PushSelfShowLog", "context is null");
            return false;
        }
        if (intent == null) {
            HMSLog.i("PushSelfShowLog", "intent is null");
            return false;
        }
        List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 640);
        if (listQueryIntentActivities == null || listQueryIntentActivities.size() == 0) {
            HMSLog.e("PushSelfShowLog", "no activity exist, may be system Err!! pkgName:");
            return false;
        }
        boolean z = listQueryIntentActivities.get(0).activityInfo.exported;
        HMSLog.i("PushSelfShowLog", "exportedFlag:" + z);
        String str = listQueryIntentActivities.get(0).activityInfo.permission;
        HMSLog.i("PushSelfShowLog", "need permission:" + str);
        if (!z) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        HMSLog.e("PushSelfShowLog", "does't have the permission to open this activity");
        return false;
    }

    public static void a(Context context, int i) {
        if (context == null) {
            HMSLog.e("PushSelfShowLog", "context is null");
            return;
        }
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(RemoteMessageConst.NOTIFICATION);
            if (notificationManager != null) {
                notificationManager.cancel(i);
            }
        } catch (Exception e) {
            HMSLog.e("PushSelfShowLog", "removeNotifiCationById err:" + e.toString());
        }
    }

    public static void b(Context context, Intent intent) {
        try {
            int intExtra = intent.hasExtra("selfshow_notify_id") ? intent.getIntExtra("selfshow_notify_id", 0) + 3 : 0;
            HMSLog.d("PushSelfShowLog", "setDelayAlarm(cancel) alarmNotityId " + intExtra + " and intent is " + intent.toURI());
            Intent intent2 = new Intent("com.huawei.intent.action.PUSH_DELAY_NOTIFY");
            intent2.setPackage(context.getPackageName()).setFlags(32);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            PendingIntent broadcast = PendingIntent.getBroadcast(context, intExtra, intent2, 536870912);
            if (broadcast != null && alarmManager != null) {
                HMSLog.d("PushSelfShowLog", "  alarm cancel");
                alarmManager.cancel(broadcast);
            } else {
                HMSLog.d("PushSelfShowLog", "alarm not exist");
            }
        } catch (Exception e) {
            HMSLog.e("PushSelfShowLog", "cancelAlarm err:" + e.toString());
        }
    }

    public static void d(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            HMSLog.i("PushSelfShowLog", "url is null.");
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            intent.setFlags(402653184);
            List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
            String str2 = null;
            Iterator<ResolveInfo> it = listQueryIntentActivities.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String str3 = it.next().activityInfo.packageName;
                if (e(context, str3)) {
                    str2 = str3;
                    break;
                }
            }
            if (str2 == null) {
                Iterator<ResolveInfo> it2 = listQueryIntentActivities.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    String str4 = it2.next().activityInfo.packageName;
                    if ("com.android.browser".equalsIgnoreCase(str4)) {
                        str2 = str4;
                        break;
                    }
                }
            }
            if (str2 != null) {
                intent.setPackage(str2);
            }
            context.startActivity(intent);
        } catch (Exception e) {
            HMSLog.e("PushSelfShowLog", "start browser activity failed, exception:" + e.getMessage());
        }
    }

    public static boolean e(Context context, String str) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        context.getPackageManager().getPreferredActivities(arrayList, arrayList2, str);
        return arrayList2.size() > 0;
    }
}
