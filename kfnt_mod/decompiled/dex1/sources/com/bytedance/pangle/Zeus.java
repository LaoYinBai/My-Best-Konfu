package com.bytedance.pangle;

import android.app.Application;
import android.content.pm.ProviderInfo;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.android.server.SystemConfig;
import com.bytedance.pangle.ZeusParam;
import com.bytedance.pangle.download.PluginDownloadBean;
import com.bytedance.pangle.download.ZeusPluginListener;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.plugin.Plugin;
import com.bytedance.pangle.plugin.PluginManager;
import com.bytedance.pangle.util.MethodUtils;
import com.bytedance.pangle.util.j;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class Zeus {
    private static Application sApplication;
    private static final HashMap<String, ProviderInfo> serverManagerHashMap = new HashMap<>();

    public static Application getAppApplication() {
        if (sApplication == null) {
            b.a();
            try {
                sApplication = (Application) MethodUtils.invokeMethod(com.bytedance.pangle.helper.a.a(), "getApplication", new Object[0]);
            } catch (Throwable unused) {
            }
        }
        return sApplication;
    }

    public static void setAppContext(Application application) {
        sApplication = application;
    }

    public static HashMap<String, ProviderInfo> getServerManagerHashMap() {
        return serverManagerHashMap;
    }

    public static void init(Application application) {
        init(application, new ZeusParam.Builder().build());
    }

    public static void init(Application application, ZeusParam zeusParam) {
        setAppContext(application);
        g.a().a(application, zeusParam);
    }

    public static boolean hasInit() {
        return g.a().f4631a;
    }

    public static void preInit() {
        if (com.bytedance.pangle.util.g.e()) {
            com.bytedance.pangle.helper.e.f4645a.execute(new Runnable() { // from class: com.bytedance.pangle.Zeus.1
                @Override // java.lang.Runnable
                public final void run() {
                    b.a();
                    try {
                        SystemConfig.getInstance();
                    } catch (Throwable unused) {
                    }
                }
            });
        }
    }

    public static void registerPluginStateListener(ZeusPluginStateListener zeusPluginStateListener) {
        g gVarA = g.a();
        if (gVarA.f4633c.isEmpty()) {
            gVarA.f4633c = new CopyOnWriteArrayList();
        }
        gVarA.f4633c.add(zeusPluginStateListener);
    }

    public static boolean hasNewPlugin(String str) {
        return com.bytedance.pangle.download.g.a().a(str);
    }

    public static void downloadAndInstall(String str, ZeusPluginListener zeusPluginListener) throws JSONException {
        PluginDownloadBean next;
        com.bytedance.pangle.download.g gVarA = com.bytedance.pangle.download.g.a();
        synchronized (gVarA) {
            Iterator<PluginDownloadBean> it = gVarA.f4581c.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                } else {
                    next = it.next();
                    if (TextUtils.equals(next.mPackageName, str)) {
                        break;
                    }
                }
            }
            if (next == null) {
                Iterator<PluginDownloadBean> it2 = gVarA.f4580b.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    PluginDownloadBean next2 = it2.next();
                    if (TextUtils.equals(next2.mPackageName, str)) {
                        next = next2;
                        break;
                    }
                }
            }
        }
        if (next != null) {
            next.isWifiOnly = false;
            com.bytedance.pangle.download.g.a(next, zeusPluginListener);
        } else {
            zeusPluginListener.onEvent(13, "The plugin was not found in the cache.");
            ZeusLogger.e(ZeusLogger.TAG_DOWNLOAD, "The plugin was not found in the cache.");
        }
    }

    public static void unregisterPluginStateListener(ZeusPluginStateListener zeusPluginStateListener) {
        g gVarA = g.a();
        if (gVarA.f4633c == null || !gVarA.f4633c.contains(zeusPluginStateListener)) {
            return;
        }
        gVarA.f4633c.remove(zeusPluginStateListener);
    }

    public static Plugin getPlugin(String str) {
        return getPlugin(str, true);
    }

    public static Plugin getPlugin(String str, boolean z) {
        return PluginManager.getInstance().getPlugin(str, z);
    }

    public static boolean isPluginInstalled(String str) {
        Plugin plugin = PluginManager.getInstance().getPlugin(str);
        return plugin != null && plugin.isInstalled();
    }

    public static boolean isPluginLoaded(String str) {
        return PluginManager.getInstance().isLoaded(str);
    }

    public static boolean loadPlugin(String str) {
        return PluginManager.getInstance().loadPlugin(str);
    }

    public static boolean syncInstallPlugin(String str) {
        c cVarA = com.bytedance.pangle.servermanager.b.a();
        if (cVarA == null) {
            return false;
        }
        try {
            return cVarA.c(str);
        } catch (RemoteException e) {
            ZeusLogger.e(ZeusLogger.TAG_INSTALL, "syncInstallPlugin error.", e);
            return false;
        }
    }

    public static boolean unInstallPlugin(String str) {
        ZeusLogger.d("unInstallPlugin");
        return PluginManager.getInstance().deletePackage(str) == 0;
    }

    public static void markOfflineFlag(String str) {
        j.a().b(str);
    }

    public static boolean hasOfflineFlag(String str) {
        return j.a().d(str);
    }

    public static void clearOfflineFlag(String str) {
        j.a().c(str);
    }

    public static String getHostAbi() {
        return com.bytedance.pangle.helper.b.a();
    }

    public static int getHostAbiBit() {
        return com.bytedance.pangle.helper.b.b();
    }

    public static int getInstalledPluginVersion(String str) throws JSONException {
        Plugin plugin = PluginManager.getInstance().getPlugin(str);
        if (plugin == null) {
            return -1;
        }
        int version = plugin.getVersion();
        ZeusLogger.d(ZeusLogger.TAG_DOWNLOAD, " getInstalledPluginVersion, " + str + " = " + version);
        return version;
    }
}
