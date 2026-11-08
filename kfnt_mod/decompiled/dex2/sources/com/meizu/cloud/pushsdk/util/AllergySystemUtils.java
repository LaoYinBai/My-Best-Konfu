package com.meizu.cloud.pushsdk.util;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import com.meizu.cloud.pushinternal.DebugLogger;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class AllergySystemUtils {
    private static final String TAG = "AllergySystemUtils";

    public static String getBssId(Context context) {
        WifiInfo connectionInfo;
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
                return null;
            }
            return connectionInfo.getBSSID();
        } catch (Exception e) {
            DebugLogger.e(TAG, "getBssId error " + e.getMessage());
            return null;
        }
    }

    public static String getOperator(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return telephonyManager.getSimOperator();
            }
            return null;
        } catch (Exception e) {
            DebugLogger.e(TAG, "getOperator error " + e.getMessage());
            return null;
        }
    }

    public static List<String> getWifiList(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager != null) {
                List<ScanResult> scanResults = wifiManager.getScanResults();
                ArrayList arrayList2 = new ArrayList();
                if (scanResults != null) {
                    for (ScanResult scanResult : scanResults) {
                        if (!arrayList2.contains(scanResult.SSID)) {
                            arrayList.add(scanResult.SSID + ";" + scanResult.BSSID + ";" + scanResult.level);
                            arrayList2.add(scanResult.SSID);
                        }
                        if (arrayList.size() >= 10) {
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            DebugLogger.e(TAG, "getWifiList error " + e.getMessage());
        }
        return arrayList;
    }
}
