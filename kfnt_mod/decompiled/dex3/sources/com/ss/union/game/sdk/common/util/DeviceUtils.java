package com.ss.union.game.sdk.common.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.webkit.WebSettings;
import com.ss.android.download.api.constant.BaseConstants;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.union.game.sdk.feedback.picture.select.d;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.commonsdk.proguard.o;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TimeZone;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DeviceUtils {
    private static String a;
    private static String b;
    private static String c;
    private static final FileFilter d = new FileFilter() { // from class: com.ss.union.game.sdk.common.util.DeviceUtils.1
        @Override // java.io.FileFilter
        public boolean accept(File file) {
            String name = file.getName();
            if (!name.startsWith(o.v)) {
                return false;
            }
            for (int i = 3; i < name.length(); i++) {
                if (!Character.isDigit(name.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    };

    private DeviceUtils() {
    }

    public static int getTimeZoneOffset() {
        int rawOffset = TimeZone.getDefault().getRawOffset() / BaseConstants.Time.HOUR;
        if (rawOffset < -12) {
            rawOffset = -12;
        }
        if (rawOffset > 12) {
            return 12;
        }
        return rawOffset;
    }

    public static String getMCC() {
        Context context = GlobalApplicationUtils.getContext();
        if (context == null) {
            return "";
        }
        try {
            String networkOperator = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperator();
            return !TextUtils.isEmpty(networkOperator) ? networkOperator.substring(0, 3) : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String getLanguage() {
        Context context = GlobalApplicationUtils.getContext();
        return context == null ? "" : context.getResources().getConfiguration().locale.getLanguage();
    }

    @SuppressLint({"MissingPermission"})
    public static String getImei() {
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        Context context = GlobalApplicationUtils.getContext();
        if (context == null) {
            return "";
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            a = (telephonyManager == null || telephonyManager.getDeviceId() == null) ? "" : telephonyManager.getDeviceId();
            if (a(a)) {
                a = "";
            }
            return a;
        } catch (Exception unused) {
            return "";
        }
    }

    private static boolean a(String str) {
        if (StringUtils.isEmpty(str) || str.equalsIgnoreCase(UtilityImpl.NET_TYPE_UNKNOWN)) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '0') {
                return false;
            }
        }
        return true;
    }

    @SuppressLint({"MissingPermission"})
    public static String getImsi() {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        Context context = GlobalApplicationUtils.getContext();
        if (context == null) {
            return "";
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            b = (telephonyManager == null || telephonyManager.getSubscriberId() == null) ? "" : telephonyManager.getSubscriberId();
        } catch (Exception unused) {
        }
        return b;
    }

    public static synchronized String getAppName() {
        Context context = GlobalApplicationUtils.getContext();
        if (context == null) {
            return "";
        }
        try {
            return context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getVersionName() {
        Context context = GlobalApplicationUtils.getContext();
        if (context == null) {
            return "";
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getVersionCode() {
        Context context = GlobalApplicationUtils.getContext();
        if (context == null) {
            return -1;
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String getAppPackageName() {
        Context context = GlobalApplicationUtils.getContext();
        if (context == null) {
            return "";
        }
        try {
            return context.getPackageName();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String getModel() {
        return Build.MODEL;
    }

    public static String getBrand() {
        return Build.BRAND.toLowerCase();
    }

    public static int getOsApi() {
        return Build.VERSION.SDK_INT;
    }

    public static String getOsVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getDefaultUserAgent() {
        Context context = GlobalApplicationUtils.getContext();
        if (context == null) {
            return "";
        }
        try {
            try {
                return WebSettings.getDefaultUserAgent(context);
            } catch (Exception unused) {
                return System.getProperty("http.agent");
            }
        } catch (Exception unused2) {
            return "";
        }
    }

    public static String getAndroidId() {
        if (TextUtils.isEmpty(c)) {
            Context context = GlobalApplicationUtils.getContext();
            if (context == null) {
                return "";
            }
            try {
                c = Settings.Secure.getString(context.getContentResolver(), "android_id");
            } catch (Throwable unused) {
            }
        }
        return c;
    }

    public static boolean isPad() {
        Context context = GlobalApplicationUtils.getContext();
        return context != null && (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public static int getCPUMaxFreqKHz() throws IOException {
        int iIntValue = -1;
        for (int i = 0; i < getNumberOfCPUCores(); i++) {
            try {
                File file = new File("/sys/devices/system/cpu/cpu" + i + "/cpufreq/cpuinfo_max_freq");
                if (file.exists() && file.canRead()) {
                    byte[] bArr = new byte[128];
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        fileInputStream.read(bArr);
                        int i2 = 0;
                        while (Character.isDigit(bArr[i2]) && i2 < bArr.length) {
                            i2++;
                        }
                        Integer numValueOf = Integer.valueOf(Integer.parseInt(new String(bArr, 0, i2)));
                        if (numValueOf.intValue() > iIntValue) {
                            iIntValue = numValueOf.intValue();
                        }
                    } catch (NumberFormatException unused) {
                    } catch (Throwable th) {
                        fileInputStream.close();
                        throw th;
                    }
                    fileInputStream.close();
                }
            } catch (IOException unused2) {
                return -1;
            }
        }
        if (iIntValue != -1) {
            return iIntValue;
        }
        FileInputStream fileInputStream2 = new FileInputStream("/proc/cpuinfo");
        try {
            int iA = a("cpu MHz", fileInputStream2) * 1000;
            if (iA <= iIntValue) {
                iA = iIntValue;
            }
            fileInputStream2.close();
            return iA;
        } catch (Throwable th2) {
            fileInputStream2.close();
            throw th2;
        }
    }

    public static int getNumberOfCPUCores() {
        if (Build.VERSION.SDK_INT <= 10) {
            return 1;
        }
        try {
            int iB = b("/sys/devices/system/cpu/possible");
            if (iB == -1) {
                iB = b("/sys/devices/system/cpu/present");
            }
            if (iB == -1) {
                iB = new File("/sys/devices/system/cpu/").listFiles(d).length;
            }
            return iB;
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static long getTotalMemory() {
        Context context = GlobalApplicationUtils.getContext();
        if (Build.VERSION.SDK_INT >= 16) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
            return memoryInfo.totalMem;
        }
        long jA = -1;
        try {
            FileInputStream fileInputStream = new FileInputStream("/proc/meminfo");
            try {
                jA = a("MemTotal", fileInputStream) * DownloadConstants.KB;
                fileInputStream.close();
            } catch (Throwable th) {
                fileInputStream.close();
                throw th;
            }
        } catch (Throwable unused) {
        }
        return jA;
    }

    private static int a(String str, FileInputStream fileInputStream) throws IOException {
        byte[] bArr = new byte[d.d];
        try {
            int i = fileInputStream.read(bArr);
            int i2 = 0;
            while (i2 < i) {
                if (bArr[i2] == 10 || i2 == 0) {
                    if (bArr[i2] == 10) {
                        i2++;
                    }
                    for (int i3 = i2; i3 < i; i3++) {
                        int i4 = i3 - i2;
                        if (bArr[i3] != str.charAt(i4)) {
                            break;
                        }
                        if (i4 == str.length() - 1) {
                            return a(bArr, i3);
                        }
                    }
                }
                i2++;
            }
            return -1;
        } catch (IOException | NumberFormatException unused) {
            return -1;
        }
    }

    private static int a(byte[] bArr, int i) {
        while (i < bArr.length && bArr[i] != 10) {
            if (Character.isDigit(bArr[i])) {
                int i2 = i + 1;
                while (i2 < bArr.length && Character.isDigit(bArr[i2])) {
                    i2++;
                }
                return Integer.parseInt(new String(bArr, 0, i, i2 - i));
            }
            i++;
        }
        return -1;
    }

    private static int b(String str) throws Throwable {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(str);
        } catch (IOException unused) {
            fileInputStream = null;
        } catch (Throwable th) {
            th = th;
            fileInputStream = null;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = bufferedReader.readLine();
            bufferedReader.close();
            int iC = c(line);
            try {
                fileInputStream.close();
            } catch (IOException unused2) {
            }
            return iC;
        } catch (IOException unused3) {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException unused4) {
                }
            }
            return -1;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException unused5) {
                }
            }
            throw th;
        }
    }

    private static int c(String str) {
        if (str == null || !str.matches("0-[\\d]+$")) {
            return -1;
        }
        return Integer.valueOf(str.substring(2)).intValue() + 1;
    }
}
