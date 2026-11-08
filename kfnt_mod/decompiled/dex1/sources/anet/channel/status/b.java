package anet.channel.status;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Pair;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.thread.ThreadPoolExecutorFactory;
import anet.channel.util.ALog;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class b {
    private static Method s;
    private static String[] m = {"net.dns1", "net.dns2", "net.dns3", "net.dns4"};

    /* renamed from: a, reason: collision with root package name */
    static volatile Context f508a = null;

    /* renamed from: b, reason: collision with root package name */
    static volatile boolean f509b = false;

    /* renamed from: c, reason: collision with root package name */
    static volatile NetworkStatusHelper.NetworkStatus f510c = NetworkStatusHelper.NetworkStatus.NONE;
    static volatile String d = "unknown";
    static volatile String e = "";
    static volatile String f = "";
    static volatile String g = "";
    static volatile String h = "unknown";
    static volatile String i = "";
    static volatile Pair<String, Integer> j = null;
    static volatile boolean k = false;
    static volatile List<InetAddress> l = Collections.EMPTY_LIST;
    private static volatile boolean n = false;
    private static ConnectivityManager o = null;
    private static TelephonyManager p = null;
    private static WifiManager q = null;
    private static SubscriptionManager r = null;
    private static BroadcastReceiver t = new BroadcastReceiver() { // from class: anet.channel.status.NetworkStatusMonitor$2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.NetworkStatusMonitor", "receiver:" + intent.getAction(), null, new Object[0]);
            }
            ThreadPoolExecutorFactory.submitScheduledTask(new d(this));
        }
    };

    b() {
    }

    static void a() {
        if (n || f508a == null) {
            return;
        }
        synchronized (f508a) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            try {
                f508a.registerReceiver(t, intentFilter);
            } catch (Exception unused) {
                ALog.e("awcn.NetworkStatusMonitor", "registerReceiver failed", null, new Object[0]);
            }
        }
        d();
        n = true;
    }

    static void b() {
        if (f508a != null) {
            f508a.unregisterReceiver(t);
        }
    }

    static void c() {
        if (Build.VERSION.SDK_INT >= 24) {
            NetworkInfo networkInfoE = e();
            f509b = networkInfoE != null && networkInfoE.isConnected();
            o.registerDefaultNetworkCallback(new c());
        }
    }

    static void d() {
        NetworkInfo networkInfoE;
        boolean z;
        ALog.d("awcn.NetworkStatusMonitor", "[checkNetworkStatus]", null, new Object[0]);
        NetworkStatusHelper.NetworkStatus networkStatus = f510c;
        String str = e;
        String str2 = f;
        try {
            try {
                networkInfoE = e();
                z = false;
            } catch (Exception e2) {
                ALog.e("awcn.NetworkStatusMonitor", "getNetworkInfo exception", null, e2, new Object[0]);
                a(NetworkStatusHelper.NetworkStatus.NONE, "unknown");
                networkInfoE = null;
                z = true;
            }
            if (!z) {
                if (networkInfoE == null || !networkInfoE.isConnected()) {
                    a(NetworkStatusHelper.NetworkStatus.NO, "no network");
                    ALog.i("awcn.NetworkStatusMonitor", "checkNetworkStatus", null, "no network");
                } else {
                    ALog.i("awcn.NetworkStatusMonitor", "checkNetworkStatus", null, "info.isConnected", Boolean.valueOf(networkInfoE.isConnected()), "info.isAvailable", Boolean.valueOf(networkInfoE.isAvailable()));
                    if (networkInfoE.getType() == 0) {
                        String subtypeName = networkInfoE.getSubtypeName();
                        String strReplace = !TextUtils.isEmpty(subtypeName) ? subtypeName.replace(" ", "") : "";
                        a(a(networkInfoE.getSubtype(), strReplace), strReplace);
                        e = a(networkInfoE.getExtraInfo());
                        g();
                    } else if (networkInfoE.getType() == 1) {
                        a(NetworkStatusHelper.NetworkStatus.WIFI, OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR);
                        WifiInfo wifiInfoH = h();
                        if (wifiInfoH != null) {
                            g = wifiInfoH.getBSSID();
                            f = wifiInfoH.getSSID();
                        }
                        h = OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR;
                        i = OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR;
                        j = i();
                    } else {
                        a(NetworkStatusHelper.NetworkStatus.NONE, "unknown");
                    }
                    k = networkInfoE.isRoaming();
                    anet.channel.util.c.e();
                }
            }
            if (f510c == networkStatus && e.equalsIgnoreCase(str) && f.equalsIgnoreCase(str2)) {
                return;
            }
            if (ALog.isPrintLog(2)) {
                NetworkStatusHelper.printNetworkDetail();
            }
            NetworkStatusHelper.notifyStatusChanged(f510c);
        } catch (Exception e3) {
            ALog.e("awcn.NetworkStatusMonitor", "checkNetworkStatus", null, e3, new Object[0]);
        }
    }

    private static void a(NetworkStatusHelper.NetworkStatus networkStatus, String str) {
        f510c = networkStatus;
        d = str;
        e = "";
        f = "";
        g = "";
        j = null;
        h = "";
        i = "";
    }

    private static NetworkStatusHelper.NetworkStatus a(int i2, String str) {
        switch (i2) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return NetworkStatusHelper.NetworkStatus.G2;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return NetworkStatusHelper.NetworkStatus.G3;
            case 13:
            case 18:
            case 19:
                return NetworkStatusHelper.NetworkStatus.G4;
            default:
                if (str.equalsIgnoreCase("TD-SCDMA") || str.equalsIgnoreCase("WCDMA") || str.equalsIgnoreCase("CDMA2000")) {
                    return NetworkStatusHelper.NetworkStatus.G3;
                }
                return NetworkStatusHelper.NetworkStatus.NONE;
        }
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "unknown";
        }
        String lowerCase = str.toLowerCase(Locale.US);
        return lowerCase.contains("cmwap") ? "cmwap" : lowerCase.contains("uniwap") ? "uniwap" : lowerCase.contains("3gwap") ? "3gwap" : lowerCase.contains("ctwap") ? "ctwap" : lowerCase.contains("cmnet") ? "cmnet" : lowerCase.contains("uninet") ? "uninet" : lowerCase.contains("3gnet") ? "3gnet" : lowerCase.contains("ctnet") ? "ctnet" : "unknown";
    }

    private static void g() {
        try {
            if (p == null) {
                p = (TelephonyManager) f508a.getSystemService("phone");
            }
            i = p.getSimOperator();
            if (Build.VERSION.SDK_INT >= 22) {
                if (r == null) {
                    r = SubscriptionManager.from(f508a);
                    s = r.getClass().getDeclaredMethod("getDefaultDataSubscriptionInfo", new Class[0]);
                }
                if (s != null) {
                    h = ((SubscriptionInfo) s.invoke(r, new Object[0])).getCarrierName().toString();
                }
            }
        } catch (Exception unused) {
        }
    }

    static NetworkInfo e() {
        if (o == null) {
            o = (ConnectivityManager) f508a.getSystemService("connectivity");
        }
        return o.getActiveNetworkInfo();
    }

    private static WifiInfo h() {
        try {
            if (q == null) {
                q = (WifiManager) f508a.getSystemService(OnekeyLoginConstants.NETWORK_TYPE_WIFI_STR);
            }
            return q.getConnectionInfo();
        } catch (Throwable th) {
            ALog.e("awcn.NetworkStatusMonitor", "getWifiInfo", null, th, new Object[0]);
            return null;
        }
    }

    private static Pair<String, Integer> i() {
        try {
            String property = System.getProperty("http.proxyHost");
            if (TextUtils.isEmpty(property)) {
                return null;
            }
            return Pair.create(property, Integer.valueOf(Integer.parseInt(System.getProperty("http.proxyPort"))));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    static String f() throws NoSuchMethodException, SecurityException {
        try {
            Method method = Class.forName("android.os.SystemProperties").getMethod(ApiRequest.METHOD_GET, String.class);
            for (String str : m) {
                String str2 = (String) method.invoke(null, str);
                if (!TextUtils.isEmpty(str2)) {
                    return str2;
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }
}
