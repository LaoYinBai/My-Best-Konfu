package com.sigmob.sdk.base.services;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Base64;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.common.SDKConfig;
import com.sigmob.sdk.common.mta.PointCategory;
import com.sigmob.sdk.common.mta.PointEntityAntiSpan;
import com.sigmob.sdk.common.mta.PointType;
import com.sigmob.sdk.common.utils.DeviceUtils;
import com.xiaomi.mipush.sdk.Constants;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class WifiScanReceiver extends BroadcastReceiver {
    private List<ScanResult> a;
    private long b = 0;

    private void a(String str, String str2) {
        PointEntityAntiSpan pointEntityAntiSpan = new PointEntityAntiSpan();
        pointEntityAntiSpan.setCategory(PointCategory.WIFI_LIST);
        pointEntityAntiSpan.setAc_type(PointType.ANTI_SPAM);
        pointEntityAntiSpan.setWifi_id_list(str);
        pointEntityAntiSpan.setWifi_mac_list(str2);
        pointEntityAntiSpan.commit();
    }

    @SuppressLint({"MissingPermission"})
    public void a(Context context) {
        try {
            WifiManager wifiManagerF = DeviceUtils.f(context);
            if (wifiManagerF != null && wifiManagerF.getWifiState() == 3 && SDKConfig.sharedInstance().getAndroidConfig().up_wifi_list_interval.intValue() >= 60) {
                SigmobLog.d("private :use_wifi ");
                SigmobLog.i("scanResult " + wifiManagerF.startScan());
            }
        } catch (Throwable unused) {
        }
    }

    @Override // android.content.BroadcastReceiver
    @SuppressLint({"MissingPermission"})
    public void onReceive(Context context, Intent intent) {
        try {
            WifiManager wifiManagerF = DeviceUtils.f(context);
            if (wifiManagerF == null) {
            }
            SigmobLog.d("private :use_wifi");
            String action = intent.getAction();
            char c = 65535;
            int iHashCode = action.hashCode();
            if (iHashCode != -1875733435) {
                if (iHashCode == 1878357501 && action.equals("android.net.wifi.SCAN_RESULTS")) {
                    c = 0;
                }
            } else if (action.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                c = 1;
            }
            switch (c) {
                case 0:
                    List<ScanResult> scanResults = wifiManagerF.getScanResults();
                    if (SDKConfig.sharedInstance().getAndroidConfig().up_wifi_list_interval.intValue() >= 60 && this.b + (r8 * 1000) < System.currentTimeMillis() && !scanResults.isEmpty()) {
                        this.b = System.currentTimeMillis();
                        this.a = scanResults;
                        String str = "";
                        String str2 = "";
                        for (int i = 0; i < this.a.size(); i++) {
                            ScanResult scanResult = this.a.get(i);
                            str = str + Base64.encodeToString(scanResult.SSID.getBytes(), 2);
                            str2 = str2 + scanResult.BSSID;
                            if (i != this.a.size() - 1) {
                                str = str + Constants.ACCEPT_TIME_SEPARATOR_SP;
                                str2 = str2 + Constants.ACCEPT_TIME_SEPARATOR_SP;
                            }
                        }
                        SigmobLog.d("name List " + str);
                        SigmobLog.d("mac List " + str2);
                        a(str, str2);
                        break;
                    }
                    break;
                case 1:
                    a(context);
                    break;
            }
        } catch (Throwable th) {
            SigmobLog.e("WifiScanReceiver error", th);
        }
    }
}
