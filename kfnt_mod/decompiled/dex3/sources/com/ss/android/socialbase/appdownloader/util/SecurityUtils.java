package com.ss.android.socialbase.appdownloader.util;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Process;
import android.telephony.TelephonyManager;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.sigmob.sdk.common.Constants;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SecurityUtils {
    private static Boolean isSecure;

    public static boolean isSecure() {
        if (isSecure == null) {
            return true;
        }
        return isSecure.booleanValue();
    }

    @WorkerThread
    public static synchronized void init(@NonNull Context context) {
        if (isSecure == null) {
            isSecure = Boolean.valueOf((isRoot() || isDebug(context) || isUsbCharging(context) || !hasSimCard(context) || isFridaExist() || isXposedExist(context)) ? false : true);
        }
    }

    public static boolean isRoot() {
        if (getRoSecureProp() == 0) {
            return true;
        }
        return isSUExist();
    }

    public static boolean isDebug(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception unused) {
            return false;
        }
    }

    private static int getRoSecureProp() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String str = null;
        try {
            Object objInvoke = Class.forName("android.os.SystemProperties").getMethod(MonitorConstants.CONNECT_TYPE_GET, String.class).invoke(null, decode("726f2e736563757265"));
            if (objInvoke != null) {
                str = (String) objInvoke;
            }
        } catch (Exception unused) {
        }
        return (str != null && Constants.FAIL.equals(str)) ? 0 : 1;
    }

    private static boolean isSUExist() {
        for (String str : new String[]{decode("2f7362696e2f7375"), decode("2f73797374656d2f62696e2f7375"), decode("2f73797374656d2f7862696e2f7375"), decode("2f646174612f6c6f63616c2f7862696e2f7375"), decode("2f646174612f6c6f63616c2f62696e2f7375"), decode("2f73797374656d2f73642f7862696e2f7375"), decode("2f73797374656d2f62696e2f6661696c736166652f7375"), decode("2f646174612f6c6f63616c2f7375")}) {
            if (new File(str).exists()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUsbCharging(Context context) {
        Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        return intentRegisterReceiver != null && intentRegisterReceiver.getIntExtra("plugged", -1) == 2;
    }

    public static boolean hasSimCard(Context context) {
        try {
            int simState = ((TelephonyManager) context.getSystemService("phone")).getSimState();
            return (simState == 1 || simState == 0) ? false : true;
        } catch (Throwable unused) {
            return false;
        }
    }

    @WorkerThread
    public static boolean isFridaExist() throws UnknownHostException {
        try {
            InetAddress.getByName("127.0.0.1");
            new Socket("127.0.0.1", Integer.parseInt(decode("3237303432")));
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    @WorkerThread
    public static boolean isXposedExist(Context context) {
        return findHookAppFile() || findHookAppName(context);
    }

    private static boolean findHookAppFile() throws IOException {
        try {
            HashSet<String> hashSet = new HashSet();
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/" + Process.myPid() + "/maps"));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (line.endsWith(".so") || line.endsWith(".jar")) {
                    hashSet.add(line.substring(line.lastIndexOf(" ") + 1));
                }
            }
            bufferedReader.close();
            for (String str : hashSet) {
                if (str.contains(decode("636f6d2e73617572696b2e737562737472617465")) || str.contains(decode("58706f7365644272696467652e6a6172")) || str.contains(decode("6c696273616e64686f6f6b2e656478702e736f"))) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean findHookAppName(Context context) {
        List listAsList = Arrays.asList(decode("64652e726f62762e616e64726f69642e78706f736564"), decode("636f6d2e746f706a6f686e77752e6d616769736b"), decode("696f2e76612e6578706f736564"), decode("636f6d2e77696e642e636f74746572"), decode("6f72672e6d656f776361742e656478706f7365642e6d616e61676572"), decode("6d652e7765697368752e657870"), decode("636f6d2e73617572696b2e737562737472617465"));
        PackageManager packageManager = context.getPackageManager();
        Iterator it = listAsList.iterator();
        while (it.hasNext()) {
            try {
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if (packageManager.getPackageInfo((String) it.next(), 0) != null) {
                return true;
            }
        }
        return false;
    }

    private static String decode(@NonNull String str) {
        return DownloadUtils.hexToString(str);
    }
}
