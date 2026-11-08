package cn.com.chinatelecom.account.api;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import cn.com.chinatelecom.account.api.a.b;
import cn.com.chinatelecom.account.api.e.d;
import cn.com.chinatelecom.account.api.e.g;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.facebook.stetho.dumpapp.Framer;
import java.security.interfaces.RSAPublicKey;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class ClientUtils {
    private static final String TAG = "ClientUtils";
    public static final int TYPE_SDK_API = 0;
    public static final int TYPE_SDK_BIO = 2;
    public static final int TYPE_SDK_HY = 1;
    private static int sdkType;

    public static String enrdata(String str, String str2) {
        try {
            return b.a(str, (RSAPublicKey) b.a(str2));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getAT(Context context) {
        try {
            return ((ActivityManager) context.getSystemService(TTDownloadField.TT_ACTIVITY)).getRunningTasks(1).get(0).topActivity.getClassName();
        } catch (Throwable th) {
            th.printStackTrace();
            return "error";
        }
    }

    public static String getApiVersion() {
        return "3.0";
    }

    public static String getCurrentNetworkType(Context context) {
        return g.f(context);
    }

    public static boolean getHealthy(Context context) {
        try {
            return d.d(context);
        } catch (Throwable th) {
            CtAuth.warn(TAG, "getHealthy error ：" + th.getMessage(), th);
            return false;
        }
    }

    public static boolean getMacData() {
        try {
            return d.c();
        } catch (Throwable th) {
            CtAuth.warn(TAG, "getMacData error ：" + th.getMessage(), th);
            return false;
        }
    }

    public static String getMobileBrand() {
        return Build.BRAND;
    }

    public static String getModel() {
        return Build.MODEL;
    }

    public static boolean getNetSafe(Context context) {
        try {
            return d.c(context);
        } catch (Throwable th) {
            CtAuth.warn(TAG, "getNetSafe error ：" + th.getMessage(), th);
            return false;
        }
    }

    public static String getOnlineType(Context context) {
        return g.g(context);
    }

    public static String getOperatorType(Context context) {
        return g.h(context);
    }

    public static String getOs() {
        return getMobileBrand() + "-" + getModel() + "-A:" + Build.VERSION.RELEASE;
    }

    public static String getPID() {
        String str;
        String str2 = "";
        try {
            str = Thread.currentThread().getId() + "" + Process.myPid();
        } catch (Exception e) {
            e = e;
        }
        try {
            return str.length() > 6 ? str.substring(0, 6) : "ctacco";
        } catch (Exception e2) {
            e = e2;
            str2 = str;
            e.printStackTrace();
            return str2;
        }
    }

    public static int getSdkType() {
        return sdkType;
    }

    public static String getSdkVersion() {
        return sdkType == 1 ? "SDK-HY-v3.8.3" : sdkType == 2 ? "SDK-BIOM-v3.8.3" : "SDK-API-v3.8.3";
    }

    public static boolean getTimePass(Context context) {
        try {
            if (!d.b(context)) {
                if (!d.d()) {
                    return false;
                }
            }
            return true;
        } catch (Throwable th) {
            CtAuth.warn(TAG, "getTimePass error ：" + th.getMessage(), th);
            return false;
        }
    }

    public static long getTp() {
        return System.currentTimeMillis();
    }

    public static boolean isAT(Context context, String str) {
        byte[] bArr = {105, 100, 36, 105, 101, 103, 36, 105, 98, 99, 100, 107, 126, 111, 102, 111, 105, 101, 103, 36, 107, 105, 105, 101, 127, 100, 126, 36, 121, 110, 97, 36, 127, 99, 36, 71, 99, 100, 99, 75, 127, 126, 98, 75, 105, 126, 99, 124, 99, 126, 115};
        byte[] bArr2 = {105, 100, 36, 105, 101, 103, 36, 105, 98, 99, 100, 107, 126, 111, 102, 111, 105, 101, 103, 36, 107, 105, 105, 101, 127, 100, 126, 36, 104, 99, 101, 103, 111, 126, Framer.EXIT_FRAME_PREFIX, 99, 105, 121, 36, 127, 99, 36, 72, 99, 101, 103, 75, 127, 126, 98, 75, 105, 126, 99, 124, 99, 126, 115};
        try {
            String className = ((ActivityManager) context.getSystemService(TTDownloadField.TT_ACTIVITY)).getRunningTasks(1).get(0).topActivity.getClassName();
            if (!className.equals(str) && !className.equals(cn.com.chinatelecom.account.api.a.d.a(bArr))) {
                if (!className.equals(cn.com.chinatelecom.account.api.a.d.a(bArr2))) {
                    return false;
                }
            }
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static boolean isJY() {
        return true;
    }

    public static boolean objChange(Object obj, String str) {
        try {
            return d.a(obj, str);
        } catch (Throwable th) {
            CtAuth.warn(TAG, "objChange error ：" + th.getMessage(), th);
            return false;
        }
    }

    public static void setSdkType(int i) {
        sdkType = i;
    }

    public static String strBuf() {
        try {
            return d.b().toString();
        } catch (Throwable th) {
            CtAuth.warn(TAG, "strBuf error ：" + th.getMessage(), th);
            return "";
        }
    }
}
