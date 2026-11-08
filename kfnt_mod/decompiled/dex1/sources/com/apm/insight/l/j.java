package com.apm.insight.l;

import java.net.BindException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.SSLException;
import org.apache.http.conn.ConnectTimeoutException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class j {

    /* renamed from: a, reason: collision with root package name */
    private static final Set<String> f2955a = new HashSet();

    static {
        f2955a.add("HeapTaskDaemon");
        f2955a.add("ThreadPlus");
        f2955a.add("ApiDispatcher");
        f2955a.add("ApiLocalDispatcher");
        f2955a.add("AsyncLoader");
        f2955a.add("AsyncTask");
        f2955a.add("Binder");
        f2955a.add("PackageProcessor");
        f2955a.add("SettingsObserver");
        f2955a.add("WifiManager");
        f2955a.add("JavaBridge");
        f2955a.add("Compiler");
        f2955a.add("Signal Catcher");
        f2955a.add("GC");
        f2955a.add("ReferenceQueueDaemon");
        f2955a.add("FinalizerDaemon");
        f2955a.add("FinalizerWatchdogDaemon");
        f2955a.add("CookieSyncManager");
        f2955a.add("RefQueueWorker");
        f2955a.add("CleanupReference");
        f2955a.add("VideoManager");
        f2955a.add("DBHelper-AsyncOp");
        f2955a.add("InstalledAppTracker2");
        f2955a.add("AppData-AsyncOp");
        f2955a.add("IdleConnectionMonitor");
        f2955a.add("LogReaper");
        f2955a.add("ActionReaper");
        f2955a.add("Okio Watchdog");
        f2955a.add("CheckWaitingQueue");
        f2955a.add("NPTH-CrashTimer");
        f2955a.add("NPTH-JavaCallback");
        f2955a.add("NPTH-LocalParser");
        f2955a.add("ANR_FILE_MODIFY");
    }

    public static Set<String> a() {
        return f2955a;
    }

    public static boolean a(Throwable th) {
        if (th == null) {
            return true;
        }
        try {
            if ((th instanceof ConnectTimeoutException) || (th instanceof SocketTimeoutException) || (th instanceof BindException) || (th instanceof ConnectException) || (th instanceof NoRouteToHostException) || (th instanceof PortUnreachableException) || (th instanceof SocketException) || (th instanceof UnknownHostException) || (th instanceof ProtocolException)) {
                return true;
            }
            return th instanceof SSLException;
        } catch (Throwable th2) {
            th2.printStackTrace();
            return false;
        }
    }
}
