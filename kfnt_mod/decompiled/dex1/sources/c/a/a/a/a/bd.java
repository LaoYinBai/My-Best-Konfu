package c.a.a.a.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bd {

    private static final class a {

        /* renamed from: a, reason: collision with root package name */
        private static volatile String f2079a;

        private a() {
        }

        public static String a(Context context) {
            String strB;
            String str = f2079a;
            if (str != null) {
                return str;
            }
            synchronized (a.class) {
                strB = f2079a;
                if (strB == null) {
                    strB = b(context);
                    f2079a = strB;
                }
            }
            return strB;
        }

        @SuppressLint({"MissingPermission"})
        private static String b(Context context) {
            String deviceId;
            try {
                deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
            } catch (Throwable unused) {
                deviceId = "";
            }
            return deviceId == null ? "" : deviceId.trim();
        }
    }

    private static final class b {

        /* renamed from: a, reason: collision with root package name */
        private static volatile String f2080a;

        private b() {
        }

        public static String a(Context context) {
            String strB;
            String str = f2080a;
            if (str != null) {
                return str;
            }
            synchronized (b.class) {
                strB = f2080a;
                if (strB == null) {
                    strB = b(context);
                    f2080a = strB;
                }
            }
            return strB;
        }

        @SuppressLint({"MissingPermission"})
        private static String b(Context context) {
            String subscriberId;
            try {
                subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
            } catch (Throwable unused) {
                subscriberId = null;
            }
            return subscriberId == null ? "" : subscriberId.trim();
        }
    }

    public static String a() {
        String displayName;
        try {
            displayName = TimeZone.getDefault().getDisplayName(false, 0);
        } catch (Throwable unused) {
            displayName = null;
        }
        return displayName == null ? "" : displayName.trim();
    }

    public static String a(Context context) {
        return a.a(context);
    }

    public static String b(Context context) {
        return b.a(context);
    }

    public static String c(Context context) {
        String str;
        try {
            Locale locale = context.getResources().getConfiguration().locale;
            str = locale.getLanguage() + "_" + locale.getCountry();
        } catch (Throwable unused) {
            str = null;
        }
        return str == null ? "" : str.trim();
    }

    public static String d(Context context) {
        double dDoubleValue;
        try {
            Class<?> cls = Class.forName("com.android.internal.os.PowerProfile");
            Object objNewInstance = cls.getConstructor(Context.class).newInstance(context);
            Method declaredMethod = cls.getDeclaredMethod("getAveragePower", String.class);
            declaredMethod.setAccessible(true);
            dDoubleValue = ((Double) declaredMethod.invoke(objNewInstance, "battery.capacity")).doubleValue();
        } catch (Throwable unused) {
            dDoubleValue = 0.0d;
        }
        bg.c("capacity : " + dDoubleValue);
        return Integer.toString((int) dDoubleValue);
    }
}
