package com.bytedance.pangle.plugin;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Keep;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.download.ZeusPluginListener;
import com.bytedance.pangle.g;
import com.bytedance.pangle.helper.PluginDirHelper;
import com.bytedance.pangle.helper.e;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.j;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import org.json.JSONException;
import org.json.JSONObject;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PluginManager {
    private static final String TAG = "PluginManager";
    private static volatile PluginManager sInstance;
    private ExecutorService mInstallThreadPool;
    private volatile boolean mIsParsePluginConfig;
    private HashMap<String, Plugin> loadedPlugin = new HashMap<>();
    private volatile Map<String, Plugin> mPlugins = new ConcurrentHashMap();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private c pluginLoader = new c();
    private b pluginInstaller = new b();

    public static PluginManager getInstance() {
        if (sInstance == null) {
            synchronized (PluginManager.class) {
                if (sInstance == null) {
                    sInstance = new PluginManager();
                }
            }
        }
        return sInstance;
    }

    private PluginManager() {
    }

    public boolean loadPlugin(String str) {
        return this.pluginLoader.a(str);
    }

    public List<Plugin> getPlugins() {
        if (!this.mIsParsePluginConfig) {
            parsePluginConfig();
        }
        Iterator<Plugin> it = this.mPlugins.values().iterator();
        while (it.hasNext()) {
            it.next().init();
        }
        return new ArrayList(this.mPlugins.values());
    }

    public Plugin getPlugin(String str, boolean z) {
        if (str == null) {
            return null;
        }
        if (!this.mIsParsePluginConfig) {
            parsePluginConfig();
        }
        Plugin plugin = this.mPlugins.get(str);
        if (!z || plugin == null) {
            return null;
        }
        plugin.init();
        return plugin;
    }

    public Plugin getPlugin(String str) {
        return getPlugin(str, true);
    }

    private synchronized void parsePluginConfig() {
        if (this.mIsParsePluginConfig) {
            return;
        }
        ZeusLogger.v(ZeusLogger.TAG_INIT, "PluginManager parsePluginsJson");
        ArrayList<String> arrayList = new ArrayList();
        try {
            Bundle bundle = Zeus.getAppApplication().getPackageManager().getPackageInfo(Zeus.getAppApplication().getPackageName(), 128).applicationInfo.metaData;
            for (String str : bundle.keySet()) {
                if (str.startsWith("ZEUS_PLUGIN_")) {
                    arrayList.add(bundle.getString(str));
                }
            }
            try {
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                for (String str2 : arrayList) {
                    try {
                        Plugin plugin = new Plugin(new JSONObject(str2), this.mHandler);
                        concurrentHashMap.put(plugin.mPkgName, plugin);
                        ZeusLogger.i(ZeusLogger.TAG_INIT, "PluginManagerparsePluginsJson. find " + plugin.mPkgName);
                    } catch (JSONException e) {
                        ZeusLogger.e(ZeusLogger.TAG_INIT, "PluginManager parsePluginsJson failed. " + str2.trim(), e);
                    }
                }
                this.mPlugins = concurrentHashMap;
                ZeusLogger.i(ZeusLogger.TAG_INIT, "PluginManager parsePluginsJson success");
            } catch (Exception e2) {
                ZeusLogger.e(ZeusLogger.TAG_INIT, "PluginManager parsePluginsJson failed.", e2);
            }
            this.mIsParsePluginConfig = true;
        } catch (PackageManager.NameNotFoundException e3) {
            ZeusLogger.e(ZeusLogger.TAG_INIT, "PluginManager parsePluginsJson failed.", e3);
        }
    }

    public boolean checkPluginInstalled(String str) {
        Plugin plugin = getPlugin(str);
        ensurePluginFileExist(plugin);
        boolean z = plugin != null && plugin.isInstalled();
        ZeusLogger.d(ZeusLogger.TAG_PPM, "PluginManager checkPluginInstalled, " + str + " = " + z);
        return z;
    }

    private void ensurePluginFileExist(Plugin plugin) {
        if (plugin == null || !plugin.isInstalled() || new File(PluginDirHelper.getSourceFile(plugin.mPkgName, plugin.getVersion())).exists()) {
            return;
        }
        deletePackage(plugin.mPkgName);
    }

    public boolean isLoaded(String str) {
        Plugin plugin = getPlugin(str);
        return plugin != null && plugin.isLoaded();
    }

    public int deletePackage(String str) {
        ZeusLogger.d(ZeusLogger.TAG_PPM, "PluginManager deletePackage, ".concat(String.valueOf(str)));
        if (getPlugin(str) == null) {
            return 0;
        }
        j.a().e(str);
        ZeusLogger.w(ZeusLogger.TAG_INSTALL, "PluginManager mark deleted : ".concat(String.valueOf(str)));
        return 0;
    }

    public void delete(String str) {
        if (getPlugin(str) != null) {
            j.a().e(str);
            ZeusLogger.w(ZeusLogger.TAG_INSTALL, "PluginManager mark deleted : ".concat(String.valueOf(str)));
        }
    }

    public boolean syncInstall(File file) {
        ZeusLogger.i(ZeusLogger.TAG_INSTALL, "PluginManager syncInstall, file=".concat(String.valueOf(file)));
        return new a(file, null).a();
    }

    public void asyncInstall(File file) {
        asyncInstall(file, null);
    }

    public void asyncInstall(File file, ZeusPluginListener zeusPluginListener) {
        if (file != null) {
            if (this.mInstallThreadPool != null) {
                this.mInstallThreadPool.execute(new a(file, zeusPluginListener));
            }
            ZeusLogger.i(ZeusLogger.TAG_INSTALL, "PluginManager asyncInstall, file=".concat(String.valueOf(file)));
        } else {
            if (zeusPluginListener != null) {
                zeusPluginListener.onEvent(22, "asyncInstall apk is null !");
            }
            ZeusLogger.w(ZeusLogger.TAG_INSTALL, "PluginManager asyncInstall apk is null !");
        }
    }

    public void installFromDownloadDir() {
        if (com.bytedance.pangle.helper.c.b(Zeus.getAppApplication())) {
            if (this.mInstallThreadPool == null) {
                this.mInstallThreadPool = e.a(g.a().f4632b.getInstallThreads());
            }
            e.f4645a.execute(new d());
        }
    }
}
