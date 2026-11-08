package com.huawei.hms.framework.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class NetworkUtil {
    private static final String TAG = "NetworkUtil";
    private static final int TYPE_WIFI_P2P = 13;

    public static final class NetType {
        public static final int TYPE_2G = 2;
        public static final int TYPE_3G = 3;
        public static final int TYPE_4G = 4;
        public static final int TYPE_NO_NETWORK = -1;
        public static final int TYPE_UNKNOWN = 0;
        public static final int TYPE_WIFI = 1;
    }

    public static String getDnsServerIps(Context context) {
        return Arrays.toString(getDnsServerIpsFromConnectionManager(context));
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String[] getDnsServerIpsFromConnectionManager(android.content.Context r4) {
        /*
            java.util.LinkedList r0 = new java.util.LinkedList
            r0.<init>()
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 21
            if (r1 < r2) goto L4b
            if (r4 == 0) goto L4b
            java.lang.String r1 = "connectivity"
            java.lang.Object r4 = r4.getSystemService(r1)
            android.net.ConnectivityManager r4 = (android.net.ConnectivityManager) r4
            if (r4 == 0) goto L4b
            android.net.NetworkInfo r1 = r4.getActiveNetworkInfo()     // Catch: java.lang.RuntimeException -> L23 java.lang.SecurityException -> L2c
            if (r1 == 0) goto L4b
            java.util.LinkedList r4 = getDnsServerIpsFromLinkProperties(r4, r1)     // Catch: java.lang.RuntimeException -> L23 java.lang.SecurityException -> L2c
            r0 = r4
            goto L4b
        L23:
            r4 = move-exception
            java.lang.String r1 = com.huawei.hms.framework.common.NetworkUtil.TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            goto L34
        L2c:
            r4 = move-exception
            java.lang.String r1 = com.huawei.hms.framework.common.NetworkUtil.TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
        L34:
            java.lang.String r3 = "getActiveNetworkInfo failed, exception:"
            r2.append(r3)
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = r4.getSimpleName()
            r2.append(r4)
            java.lang.String r4 = r2.toString()
            com.huawei.hms.framework.common.Logger.i(r1, r4)
        L4b:
            boolean r4 = r0.isEmpty()
            if (r4 == 0) goto L55
            r4 = 0
            java.lang.String[] r4 = new java.lang.String[r4]
            goto L61
        L55:
            int r4 = r0.size()
            java.lang.String[] r4 = new java.lang.String[r4]
            java.lang.Object[] r4 = r0.toArray(r4)
            java.lang.String[] r4 = (java.lang.String[]) r4
        L61:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.common.NetworkUtil.getDnsServerIpsFromConnectionManager(android.content.Context):java.lang.String[]");
    }

    private static LinkedList<String> getDnsServerIpsFromLinkProperties(ConnectivityManager connectivityManager, NetworkInfo networkInfo) {
        NetworkInfo networkInfo2;
        LinkProperties linkProperties;
        LinkedList<String> linkedList = new LinkedList<>();
        Network[] allNetworks = connectivityManager.getAllNetworks();
        if (allNetworks != null && allNetworks.length != 0) {
            for (Network network : allNetworks) {
                if (network != null && (networkInfo2 = connectivityManager.getNetworkInfo(network)) != null && networkInfo2.getType() == networkInfo.getType() && (linkProperties = connectivityManager.getLinkProperties(network)) != null) {
                    Iterator<InetAddress> it = linkProperties.getDnsServers().iterator();
                    while (it.hasNext()) {
                        linkedList.add(it.next().getHostAddress());
                    }
                }
            }
        }
        return linkedList;
    }

    public static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager connectivityManager;
        if (context != null && (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) != null) {
            try {
                return connectivityManager.getActiveNetworkInfo();
            } catch (SecurityException unused) {
            }
        }
        return null;
    }

    public static int getNetworkType(Context context) {
        if (context != null) {
            return getNetworkType(getNetworkInfo(context));
        }
        return 0;
    }

    public static int getNetworkType(NetworkInfo networkInfo) {
        int i;
        if (networkInfo == null || !networkInfo.isConnected()) {
            return -1;
        }
        int type = networkInfo.getType();
        if (1 == type || 13 == type) {
            return 1;
        }
        if (type == 0) {
            int subtype = networkInfo.getSubtype();
            switch (subtype) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    i = 2;
                    break;
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    i = 3;
                    break;
                case 13:
                    i = 4;
                    break;
                default:
                    i = 0;
                    break;
            }
            if (i != 0 || Build.VERSION.SDK_INT < 25) {
                return i;
            }
            switch (subtype) {
                case 16:
                    return 2;
                case 17:
                    return 3;
            }
        }
        return 0;
    }

    public static String getSSIDByNetWorkId(Context context) {
        return "";
    }
}
