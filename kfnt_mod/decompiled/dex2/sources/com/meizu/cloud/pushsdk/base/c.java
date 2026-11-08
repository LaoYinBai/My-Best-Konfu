package com.meizu.cloud.pushsdk.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.meizu.cloud.pushinternal.DebugLogger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private static String a = "";
    private static String b = "";

    public static String a(Context context) {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        b = !a() ? d(context) : b(context);
        return b;
    }

    private static String a(String str) throws IOException {
        String str2;
        String str3;
        try {
            FileInputStream fileInputStream = new FileInputStream("/sys/class/net/" + str + "/address");
            Scanner scanner = new Scanner(fileInputStream);
            strTrim = scanner.hasNextLine() ? scanner.nextLine().trim() : null;
            fileInputStream.close();
        } catch (FileNotFoundException unused) {
            str2 = "DeviceUtils";
            str3 = "getMacAddressWithIfName File not found Exception";
            DebugLogger.e(str2, str3);
            return strTrim;
        } catch (IOException unused2) {
            str2 = "DeviceUtils";
            str3 = "getMacAddressWithIfName IOException";
            DebugLogger.e(str2, str3);
            return strTrim;
        } catch (Exception unused3) {
            str2 = "DeviceUtils";
            str3 = "getMacAddressWithIfName Exception ";
            DebugLogger.e(str2, str3);
            return strTrim;
        }
        return strTrim;
    }

    public static boolean a() {
        String strA = j.a("ro.target.product");
        if (TextUtils.isEmpty(strA)) {
            DebugLogger.i("DeviceUtils", "current product is phone");
            return true;
        }
        DebugLogger.i("DeviceUtils", "current product is " + strA);
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String b(Context context) {
        com.meizu.cloud.pushsdk.base.a.d dVarA = com.meizu.cloud.pushsdk.base.a.a.a("android.telephony.MzTelephonyManager").a("getDeviceId", new Class[0]).a(new Object[0]);
        if (dVarA.a) {
            return (String) dVarA.b;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getDeviceId();
        }
        return null;
    }

    @SuppressLint({"HardwareIds"})
    public static String c(Context context) {
        WifiInfo connectionInfo;
        String macAddress;
        String str;
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        try {
            String str2 = null;
            if (Build.VERSION.SDK_INT >= 23) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo == null) {
                        macAddress = a("wlan0");
                        if (TextUtils.isEmpty(macAddress)) {
                            str = "eth0";
                        }
                        str2 = macAddress;
                    } else if (activeNetworkInfo.getType() == 1) {
                        str = "wlan0";
                    } else if (activeNetworkInfo.getType() == 9) {
                        str = "eth0";
                    }
                    macAddress = a(str);
                    str2 = macAddress;
                }
                a = str2;
            } else {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                if (wifiManager != null && (connectionInfo = wifiManager.getConnectionInfo()) != null) {
                    macAddress = connectionInfo.getMacAddress();
                    str2 = macAddress;
                }
                a = str2;
            }
        } catch (Exception unused) {
            DebugLogger.e("DeviceUtils", "get address exception ");
        }
        return a;
    }

    private static String d(Context context) {
        StringBuilder sb = new StringBuilder();
        String str = Build.SERIAL;
        DebugLogger.i("DeviceUtils", "device serial " + str);
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            String strC = c(context);
            DebugLogger.e("DeviceUtils", "mac address " + strC);
            if (!TextUtils.isEmpty(strC)) {
                sb.append(strC.replace(":", "").toUpperCase());
                return sb.toString();
            }
        }
        return null;
    }
}
