package com.sigmob.devicehelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.sigmob.devicehelper.oaId.helpers.DevicesIDsHelper;
import com.sigmob.logger.SigmobLog;
import com.umeng.commonsdk.proguard.b;
import com.umeng.message.MsgConstant;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class DeviceHelper {
    private static String a = null;
    private static String b = null;
    private static String c = "";
    private static Handler d;
    private static Handler e;
    private static int f;
    private static boolean g;
    private static long h;
    private static long i;
    private static long j;
    private static String k;

    @SuppressLint({"MissingPermission"})
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager;
        String deviceId;
        try {
            SigmobLog.d("private :getIMEI");
            telephonyManager = (TelephonyManager) context.getSystemService("phone");
        } catch (Exception unused) {
        }
        if (telephonyManager == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            deviceId = telephonyManager.getImei();
            if (TextUtils.isEmpty(deviceId)) {
                try {
                    return telephonyManager.getDeviceId();
                } catch (Throwable unused2) {
                    return telephonyManager.getMeid();
                }
            }
        } else {
            deviceId = telephonyManager.getDeviceId();
        }
        if (deviceId != null) {
            return deviceId;
        }
        return null;
    }

    @SuppressLint({"MissingPermission"})
    public static String getIMEI(Context context, int i2) {
        TelephonyManager telephonyManager;
        String deviceId;
        try {
            SigmobLog.d("private :getIMEI " + i2);
            telephonyManager = (TelephonyManager) context.getSystemService("phone");
        } catch (Exception unused) {
        }
        if (telephonyManager == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            deviceId = telephonyManager.getImei(i2);
            if (TextUtils.isEmpty(deviceId)) {
                try {
                    return telephonyManager.getDeviceId(i2);
                } catch (Throwable unused2) {
                    return telephonyManager.getMeid(i2);
                }
            }
        } else {
            deviceId = telephonyManager.getDeviceId();
        }
        if (deviceId != null) {
            return deviceId;
        }
        return null;
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getIMSI(Context context) {
        return null;
    }

    public static String getMacAddress() {
        return "";
    }

    public static String getOAID(final Context context) {
        if (TextUtils.isEmpty(a)) {
            if (d == null) {
                d = new Handler(Looper.getMainLooper());
            }
            d.post(new Runnable() { // from class: com.sigmob.devicehelper.DeviceHelper.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        DevicesIDsHelper.b(context, new DevicesIDsHelper.AppIdsUpdater() { // from class: com.sigmob.devicehelper.DeviceHelper.2.1
                            @Override // com.sigmob.devicehelper.oaId.helpers.DevicesIDsHelper.AppIdsUpdater
                            public void OnIdsAvalid(String str) {
                                if (!TextUtils.isEmpty(str)) {
                                    String unused = DeviceHelper.a = str;
                                }
                                if (DeviceHelper.d != null) {
                                    DeviceHelper.d.removeCallbacksAndMessages(null);
                                    Handler unused2 = DeviceHelper.d = null;
                                }
                            }
                        });
                    } catch (Exception e2) {
                        SigmobLog.e(e2.getMessage());
                    }
                }
            });
        }
        return a;
    }

    public static String getOAID_API(final Context context) {
        if (TextUtils.isEmpty(c)) {
            if (e == null) {
                e = new Handler(Looper.getMainLooper());
            }
            if (f > 10 || g) {
                return "";
            }
            f++;
            g = true;
            e.post(new Runnable() { // from class: com.sigmob.devicehelper.DeviceHelper.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        new DevicesIDsHelper().a(context, new DevicesIDsHelper.AppIdsUpdater() { // from class: com.sigmob.devicehelper.DeviceHelper.1.1
                            @Override // com.sigmob.devicehelper.oaId.helpers.DevicesIDsHelper.AppIdsUpdater
                            public void OnIdsAvalid(String str) {
                                boolean unused = DeviceHelper.g = false;
                                if (!TextUtils.isEmpty(str)) {
                                    String unused2 = DeviceHelper.c = str;
                                }
                                Log.d("oaid", "oaid_src: " + str);
                                if (DeviceHelper.e != null) {
                                    DeviceHelper.e.removeCallbacksAndMessages(null);
                                    Handler unused3 = DeviceHelper.e = null;
                                }
                            }
                        });
                    } catch (Exception e2) {
                        SigmobLog.e(e2.getMessage());
                    }
                }
            });
        }
        return c;
    }

    public static String getVAID() {
        return b;
    }

    @SuppressLint({"MissingPermission"})
    public static String getWifiName(Context context) {
        return k;
    }

    @SuppressLint({"MissingPermission"})
    public static String getWifimac(Context context) {
        return "";
    }

    public static boolean isCanRetryIMEI() {
        boolean z = System.currentTimeMillis() - h > b.d;
        if (z) {
            h = System.currentTimeMillis();
        }
        SigmobLog.d("isCanRetryIMEI status " + z);
        return z;
    }

    public static boolean isCanRetryLocation() {
        boolean z = System.currentTimeMillis() - i > 36000;
        if (z) {
            i = System.currentTimeMillis();
        }
        SigmobLog.d("isCanRetryLocation status " + z);
        return z;
    }

    public static boolean isCanRetryWIFI() {
        boolean z = System.currentTimeMillis() - j > b.d;
        if (z) {
            j = System.currentTimeMillis();
        }
        SigmobLog.d("isCanRetryWIFI status " + z);
        return z;
    }

    public static boolean isCanUseLocation(Context context) {
        boolean z = context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0 || context.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0;
        SigmobLog.d("isCanUseLocation status " + z);
        return z;
    }

    public static boolean isCanUsePhoneState(Context context) {
        boolean z = context.checkCallingOrSelfPermission(MsgConstant.PERMISSION_READ_PHONE_STATE) == 0;
        SigmobLog.d("isCanUsePhoneState status " + z);
        return z;
    }

    public static boolean isCanUseWifiState(Context context) {
        boolean z = context.checkCallingOrSelfPermission(MsgConstant.PERMISSION_ACCESS_WIFI_STATE) == 0;
        SigmobLog.d("isCanUseWifiState status " + z);
        return z;
    }

    public static boolean isCanUseWriteExternal(Context context) {
        boolean z = context.checkCallingOrSelfPermission(MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE) == 0;
        SigmobLog.d("isCanUseWriteExternal status " + z);
        return z;
    }

    public static void resetRetryIMEI() {
        h = 0L;
    }
}
