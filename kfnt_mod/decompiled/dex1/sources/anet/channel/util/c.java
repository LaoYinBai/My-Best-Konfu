package anet.channel.util;

import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.statist.NetTypeStat;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.thread.ThreadPoolExecutorFactory;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {
    public static final int IPV4_ONLY = 1;
    public static final int IPV6_ONLY = 2;
    public static final int IP_DUAL_STACK = 3;
    public static final int IP_STACK_UNKNOWN = 0;

    /* renamed from: b, reason: collision with root package name */
    static volatile String f606b;

    /* renamed from: c, reason: collision with root package name */
    static f f607c;

    /* renamed from: a, reason: collision with root package name */
    static final byte[][] f605a = {new byte[]{-64, 0, 0, -86}, new byte[]{-64, 0, 0, -85}};
    static ConcurrentHashMap<String, f> d = new ConcurrentHashMap<>();
    static ConcurrentHashMap<String, Integer> e = new ConcurrentHashMap<>();

    public static boolean a() {
        return false;
    }

    static {
        f606b = null;
        f607c = null;
        try {
            f607c = new f((Inet6Address) InetAddress.getAllByName("64:ff9b::")[0], 96);
            f606b = b(NetworkStatusHelper.getStatus());
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(NetworkStatusHelper.NetworkStatus networkStatus) {
        if (networkStatus.isWifi()) {
            String wifiBSSID = NetworkStatusHelper.getWifiBSSID();
            if (TextUtils.isEmpty(wifiBSSID)) {
                wifiBSSID = "";
            }
            return "WIFI$" + wifiBSSID;
        }
        if (!networkStatus.isMobile()) {
            return "UnknownNetwork";
        }
        return networkStatus.getType() + "$" + NetworkStatusHelper.getApn();
    }

    public static boolean b() {
        Integer num = e.get(f606b);
        return num != null && num.intValue() == 1;
    }

    public static int c() {
        Integer num = e.get(f606b);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public static f d() {
        f fVar = d.get(f606b);
        return fVar == null ? f607c : fVar;
    }

    public static String a(Inet4Address inet4Address) throws Exception {
        if (inet4Address == null) {
            throw new InvalidParameterException("address in null");
        }
        f fVarD = d();
        if (fVarD == null) {
            throw new Exception("cannot get nat64 prefix");
        }
        byte[] address = inet4Address.getAddress();
        byte[] address2 = fVarD.f612b.getAddress();
        int i = fVarD.f611a / 8;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = i2 + i;
            if (i4 > 15 || i3 >= 4) {
                break;
            }
            if (i4 != 8) {
                address2[i4] = (byte) (address[i3] | address2[i4]);
                i3++;
            }
            i2++;
        }
        return InetAddress.getByAddress(address2).getHostAddress();
    }

    public static String a(String str) throws Exception {
        return a((Inet4Address) Inet4Address.getByName(str));
    }

    /* JADX WARN: Failed to analyze thrown exceptions
    java.util.ConcurrentModificationException
    	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1095)
    	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1049)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:118)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:69)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:179)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:132)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:69)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:179)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:132)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:69)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:179)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:132)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:69)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:179)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:132)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:69)
     */
    private static int h() throws SocketException {
        String str;
        int iIntValue;
        TreeMap treeMap = new TreeMap();
        Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
        while (true) {
            str = null;
            iIntValue = 0;
            if (!it.hasNext()) {
                break;
            }
            NetworkInterface networkInterface = (NetworkInterface) it.next();
            if (!networkInterface.getInterfaceAddresses().isEmpty()) {
                String displayName = networkInterface.getDisplayName();
                ALog.i("awcn.Inet64Util", "find NetworkInterface:" + displayName, null, new Object[0]);
                Iterator<InterfaceAddress> it2 = networkInterface.getInterfaceAddresses().iterator();
                int i = 0;
                while (it2.hasNext()) {
                    InetAddress address = it2.next().getAddress();
                    if (address instanceof Inet6Address) {
                        Inet6Address inet6Address = (Inet6Address) address;
                        if (!a(inet6Address)) {
                            ALog.e("awcn.Inet64Util", "Found IPv6 address:" + inet6Address.toString(), null, new Object[0]);
                            i |= 2;
                        }
                    } else if (address instanceof Inet4Address) {
                        Inet4Address inet4Address = (Inet4Address) address;
                        if (!a((InetAddress) inet4Address) && !inet4Address.getHostAddress().startsWith("192.168.43.")) {
                            ALog.e("awcn.Inet64Util", "Found IPv4 address:" + inet4Address.toString(), null, new Object[0]);
                            i |= 1;
                        }
                    }
                }
                if (i != 0) {
                    treeMap.put(displayName.toLowerCase(), Integer.valueOf(i));
                }
            }
        }
        if (treeMap.isEmpty()) {
            return 0;
        }
        if (treeMap.size() == 1) {
            return ((Integer) treeMap.firstEntry().getValue()).intValue();
        }
        if (NetworkStatusHelper.getStatus().isWifi()) {
            str = "wlan";
        } else if (NetworkStatusHelper.getStatus().isMobile()) {
            str = "rmnet";
        }
        if (str != null) {
            Iterator it3 = treeMap.entrySet().iterator();
            while (true) {
                if (!it3.hasNext()) {
                    break;
                }
                Map.Entry entry = (Map.Entry) it3.next();
                if (((String) entry.getKey()).startsWith(str)) {
                    iIntValue = ((Integer) entry.getValue()).intValue();
                    break;
                }
            }
        }
        return (iIntValue == 2 && treeMap.containsKey("v4-wlan0")) ? iIntValue | ((Integer) treeMap.remove("v4-wlan0")).intValue() : iIntValue;
    }

    public static void e() throws SocketException {
        f606b = b(NetworkStatusHelper.getStatus());
        if (e.putIfAbsent(f606b, 0) != null) {
            return;
        }
        int i = i();
        e.put(f606b, Integer.valueOf(i));
        NetTypeStat netTypeStat = new NetTypeStat();
        netTypeStat.ipStackType = i;
        String str = f606b;
        if (i == 2 || i == 3) {
            ThreadPoolExecutorFactory.submitScheduledTask(new d(str, netTypeStat), 1500L, TimeUnit.MILLISECONDS);
        } else if (GlobalAppRuntimeInfo.isTargetProcess()) {
            AppMonitor.getInstance().commitStat(netTypeStat);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int i() throws SocketException {
        int iH;
        try {
            iH = h();
        } catch (Exception unused) {
            iH = 0;
        }
        ALog.e("awcn.Inet64Util", "startIpStackDetect", null, "ip stack", Integer.valueOf(iH));
        return iH;
    }

    private static boolean a(InetAddress inetAddress) {
        return inetAddress.isLoopbackAddress() || inetAddress.isLinkLocalAddress() || inetAddress.isAnyLocalAddress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static f j() throws UnknownHostException {
        InetAddress byName;
        boolean z;
        try {
            byName = InetAddress.getByName("ipv4only.arpa");
        } catch (Exception unused) {
            byName = null;
        }
        if (byName instanceof Inet6Address) {
            ALog.i("awcn.Inet64Util", "Resolved AAAA: " + byName.toString(), null, new Object[0]);
            byte[] address = byName.getAddress();
            if (address.length != 16) {
                return null;
            }
            int i = 12;
            while (true) {
                z = true;
                if (i < 0) {
                    z = false;
                    break;
                }
                if ((address[i] & f605a[0][0]) != 0 && address[i + 1] == 0 && address[i + 2] == 0) {
                    int i2 = i + 3;
                    if (address[i2] == f605a[0][3] || address[i2] == f605a[1][3]) {
                        break;
                    }
                }
                i--;
            }
            if (z) {
                address[i + 3] = 0;
                address[i + 2] = 0;
                address[i + 1] = 0;
                address[i] = 0;
                return new f(Inet6Address.getByAddress("ipv4only.arpa", address, 0), i * 8);
            }
        } else if (byName instanceof Inet4Address) {
            ALog.i("awcn.Inet64Util", "Resolved A: " + byName.toString(), null, new Object[0]);
        }
        return null;
    }
}
