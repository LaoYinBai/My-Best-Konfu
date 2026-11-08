package org.android.agoo.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.utl.ALog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class Config {
    public static final String AGOO_CLEAR_TIME = "agoo_clear_time";
    public static final String AGOO_ENABLE_DAEMONSERVER = "agoo_enable_daemonserver";
    public static final String AGOO_UNREPORT_TIMES = "agoo_UnReport_times";
    public static final String KEY_DEVICE_TOKEN = "deviceId";
    public static final String PREFERENCES = "Agoo_AppStore";
    public static final String PROPERTY_AGOO_SERVICE_MODE = "agoo_service_mode";
    public static final String PROPERTY_APP_KEY = "agoo_app_key";
    public static final String PROPERTY_APP_VERSION = "app_version";
    public static final String PROPERTY_DEVICE_TOKEN = "app_device_token";
    public static final String PROPERTY_PUSH_USER_TOKEN = "app_push_user_token";
    public static final String PROPERTY_TT_ID = "app_tt_id";
    public static final String TAG = "Config";
    public static String a;
    private static String b;
    private static String c;

    public static void setAgooAppKey(Context context, String str) {
        try {
        } catch (Throwable th) {
            ALog.e(TAG, "setAgooAppKey", th, new Object[0]);
        }
        if (TextUtils.isEmpty(str)) {
            ALog.e(TAG, "setAgooAppKey appkey null", new Object[0]);
            return;
        }
        b = str;
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(PREFERENCES, 4).edit();
        editorEdit.putString(PROPERTY_APP_KEY, str);
        editorEdit.apply();
        ALog.d(TAG, "setAgooAppKey", new Object[]{"appkey", str});
    }

    public static String a(Context context) {
        String string;
        String str = b;
        try {
            string = context.getSharedPreferences(PREFERENCES, 4).getString(PROPERTY_APP_KEY, b);
        } catch (Throwable th) {
            ALog.e(TAG, "getAgooAppKey", th, new Object[0]);
            string = str;
        }
        if (TextUtils.isEmpty(string)) {
            ALog.e(TAG, "getAgooAppKey null!!", new Object[0]);
        }
        ALog.d(TAG, "getAgooAppKey", new Object[]{"appkey", string});
        return string;
    }

    public static String b(Context context) {
        if (TextUtils.isEmpty(a)) {
            return ACCSManager.getDefaultConfig(context);
        }
        return a;
    }

    public static void a(Context context, String str) {
        ALog.i(TAG, "setDeviceToken", new Object[]{"token", str});
        if (TextUtils.isEmpty(str)) {
            return;
        }
        c = str;
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(PREFERENCES, 4).edit();
            editorEdit.putString(KEY_DEVICE_TOKEN, str);
            editorEdit.apply();
        } catch (Throwable th) {
            ALog.e(TAG, "setDeviceToken", th, new Object[0]);
        }
    }

    public static String g(Context context) {
        String string;
        String str = c;
        try {
            string = context.getSharedPreferences(PREFERENCES, 4).getString(KEY_DEVICE_TOKEN, c);
        } catch (Throwable th) {
            ALog.e(TAG, "getDeviceToken", th, new Object[0]);
            string = str;
        }
        ALog.i(TAG, "getDeviceToken", new Object[]{"token", string});
        return string;
    }

    public static void a(Context context, int i) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES, 4);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt(AGOO_UNREPORT_TIMES, sharedPreferences.getInt(AGOO_UNREPORT_TIMES, 0) + i);
            editorEdit.apply();
        } catch (Throwable unused) {
        }
    }

    public static boolean c(Context context) {
        try {
            return context.getSharedPreferences(PREFERENCES, 4).getInt(AGOO_UNREPORT_TIMES, 0) > 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static void d(Context context) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(PREFERENCES, 4).edit();
            editorEdit.putInt(AGOO_UNREPORT_TIMES, 0);
            editorEdit.apply();
        } catch (Throwable unused) {
        }
    }

    public static int e(Context context) {
        try {
            return context.getSharedPreferences(PREFERENCES, 4).getInt(AGOO_UNREPORT_TIMES, 0);
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static void a(Context context, long j) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(PREFERENCES, 4).edit();
            editorEdit.putLong(AGOO_CLEAR_TIME, j);
            editorEdit.apply();
        } catch (Throwable unused) {
        }
    }

    public static boolean b(Context context, long j) {
        try {
            long j2 = context.getSharedPreferences(PREFERENCES, 4).getLong(AGOO_CLEAR_TIME, 0L);
            StringBuilder sb = new StringBuilder();
            sb.append("now=");
            sb.append(j);
            sb.append(",now - lastTime=");
            long j3 = j - j2;
            sb.append(j3);
            sb.append(",istrue=");
            sb.append(j3 > 86400000);
            ALog.d("isClearTime", sb.toString(), new Object[0]);
            return j != 0 && j3 > 86400000;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static void a(Context context, boolean z) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(PREFERENCES, 4).edit();
            editorEdit.putBoolean(AGOO_ENABLE_DAEMONSERVER, z);
            editorEdit.apply();
        } catch (Throwable unused) {
        }
    }

    public static boolean f(Context context) {
        try {
            return context.getSharedPreferences(PREFERENCES, 4).getBoolean(AGOO_ENABLE_DAEMONSERVER, true);
        } catch (Throwable unused) {
            return false;
        }
    }

    public static String h(Context context) {
        try {
            return context.getSharedPreferences(PREFERENCES, 4).getString(PROPERTY_PUSH_USER_TOKEN, "");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void b(Context context, String str) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(PREFERENCES, 4).edit();
            if (!TextUtils.isEmpty(str)) {
                editorEdit.putString(PROPERTY_PUSH_USER_TOKEN, str);
            }
            editorEdit.apply();
        } catch (Throwable unused) {
        }
    }
}
