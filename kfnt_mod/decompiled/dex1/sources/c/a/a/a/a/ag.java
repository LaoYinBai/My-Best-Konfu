package c.a.a.a.a;

import android.app.Application;
import android.content.Context;
import java.net.Inet4Address;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ag {

    /* renamed from: a, reason: collision with root package name */
    private static Application f2055a;

    public static String a() {
        try {
            Context applicationContext = c().getApplicationContext();
            return "" + applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 0).versionCode;
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String b() {
        String hostAddress = "";
        String hostAddress2 = "";
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if ("wlan0".equals(networkInterfaceNextElement.getName())) {
                    for (InterfaceAddress interfaceAddress : networkInterfaceNextElement.getInterfaceAddresses()) {
                        if (interfaceAddress.getAddress() instanceof Inet4Address) {
                            hostAddress = ((Inet4Address) interfaceAddress.getAddress()).getHostAddress();
                        }
                    }
                }
                if ("rmnet0".equals(networkInterfaceNextElement.getName())) {
                    for (InterfaceAddress interfaceAddress2 : networkInterfaceNextElement.getInterfaceAddresses()) {
                        if (interfaceAddress2.getAddress() instanceof Inet4Address) {
                            hostAddress2 = ((Inet4Address) interfaceAddress2.getAddress()).getHostAddress();
                        }
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return !hostAddress.equals("") ? hostAddress : hostAddress2;
    }

    private static Application c() {
        Application application = f2055a;
        if (application != null) {
            return application;
        }
        try {
            Application application2 = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, new Object[0]);
            f2055a = application2;
            return application2;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
