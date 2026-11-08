package com.cgs.out;

import android.net.wifi.ScanResult;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface CGExtend {
    public static final int ACTION_ADB_LOG = 1000;
    public static final int ACTION_DO_NOTHING = 0;
    public static final int ACTION_KILLING = 2000;

    boolean canGetDeviceInformation();

    String getAndroidId();

    String getBSSID();

    String getDeviceId();

    int getIpAddress();

    String getMacAddress();

    String getSSID();

    List<ScanResult> getScanResults();

    String getSubscriberId();

    int receive(int i, int i2, int i3, int i4);
}
