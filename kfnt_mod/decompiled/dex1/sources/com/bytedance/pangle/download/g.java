package com.bytedance.pangle.download;

import android.os.Handler;
import android.os.Looper;
import com.bytedance.b.a.c.h;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.helper.PluginDirHelper;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.plugin.Plugin;
import com.bytedance.pangle.plugin.PluginManager;
import com.bytedance.pangle.plugin.PluginProvider;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g {
    private static final ExecutorService e = Executors.newSingleThreadExecutor();
    private static volatile g f;

    /* renamed from: a, reason: collision with root package name */
    volatile long f4579a;
    private List<PluginDownloadBean> g = new CopyOnWriteArrayList();

    /* renamed from: b, reason: collision with root package name */
    public List<PluginDownloadBean> f4580b = new CopyOnWriteArrayList();
    private Map<String, Long> h = new ConcurrentHashMap();

    /* renamed from: c, reason: collision with root package name */
    public List<PluginDownloadBean> f4581c = new CopyOnWriteArrayList();
    private Handler i = new Handler(Looper.getMainLooper());
    private Runnable j = null;
    public boolean d = false;

    private g() {
    }

    public static g a() {
        if (f == null) {
            synchronized (g.class) {
                if (f == null) {
                    f = new g();
                }
            }
        }
        return f;
    }

    public final void b() {
        ZeusLogger.d(ZeusLogger.TAG_DOWNLOAD, "asyncFetchPlugins start");
        e.execute(new c(this));
    }

    static void a(List<PluginDownloadBean> list) {
        for (PluginDownloadBean pluginDownloadBean : list) {
            if (pluginDownloadBean.mDownloadType == 0) {
                a(pluginDownloadBean, null);
            }
        }
    }

    public static void a(PluginDownloadBean pluginDownloadBean, ZeusPluginListener zeusPluginListener) {
        try {
            Plugin plugin = Zeus.getPlugin(pluginDownloadBean.mPackageName);
            if (b(pluginDownloadBean)) {
                if (zeusPluginListener != null) {
                    zeusPluginListener.onEvent(13, "already download");
                    return;
                }
                return;
            }
            if (plugin != null) {
                plugin.setHostCompatVersion(pluginDownloadBean.mVersionCode, pluginDownloadBean.mClientVersionMin, pluginDownloadBean.mClientVersionMax);
                plugin.setApiCompatVersion(pluginDownloadBean.mVersionCode, pluginDownloadBean.mApiVersionMin, pluginDownloadBean.mApiVersionMax);
            }
            PluginProvider pluginProvider = com.bytedance.pangle.g.a().f4632b.getPluginProvider();
            if (pluginProvider != null && pluginProvider.useLocalPlugin()) {
                PluginManager.getInstance().installFromDownloadDir();
            } else {
                e.a().a(pluginDownloadBean.mUrl, pluginDownloadBean.mPackageName, pluginDownloadBean.mVersionCode, pluginDownloadBean.mMd5, pluginDownloadBean.isWifiOnly, pluginDownloadBean.mBackupUrlList, pluginDownloadBean.mDownloadType, zeusPluginListener);
            }
        } catch (Exception e2) {
            if (zeusPluginListener != null) {
                zeusPluginListener.onEvent(13, e2.getMessage());
            }
        }
    }

    private static boolean b(PluginDownloadBean pluginDownloadBean) throws JSONException {
        Plugin plugin = Zeus.getPlugin(pluginDownloadBean.mPackageName);
        if (!(plugin != null && plugin.isVersionInstalled(pluginDownloadBean.mVersionCode)) || !PluginDirHelper.isPackageVersionDirExists(pluginDownloadBean.mPackageName, pluginDownloadBean.mVersionCode)) {
            return false;
        }
        ZeusLogger.e(ZeusLogger.TAG_DOWNLOAD, "interceptPluginDownload, packageName:" + pluginDownloadBean.mPackageName + " downloadVersionCode:" + pluginDownloadBean.mVersionCode);
        return true;
    }

    static void a(PluginDownloadBean pluginDownloadBean) {
        Zeus.unInstallPlugin(pluginDownloadBean.mPackageName);
    }

    public final boolean a(String str) {
        Long l;
        try {
            Plugin plugin = Zeus.getPlugin(str);
            if (plugin != null && !plugin.isLoaded() && (l = this.h.get(str)) != null) {
                if (System.currentTimeMillis() - l.longValue() < 5000) {
                    return false;
                }
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    static /* synthetic */ void a(g gVar) {
        if (gVar.j != null) {
            gVar.i.removeCallbacks(gVar.j);
        }
        gVar.j = new Runnable() { // from class: com.bytedance.pangle.download.g.2

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ long f4583a = h.o;

            @Override // java.lang.Runnable
            public final void run() {
                if (!b.a().f4561a) {
                    g.this.b();
                }
                g.this.i.postDelayed(this, this.f4583a * 1000);
            }
        };
        gVar.i.postDelayed(gVar.j, 1800000L);
    }
}
