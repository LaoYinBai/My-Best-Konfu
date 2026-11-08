package com.bytedance.pangle.download;

import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.plugin.PluginProvider;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class c implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private boolean f4565a = false;

    /* renamed from: b, reason: collision with root package name */
    private final g f4566b;

    c(g gVar) {
        this.f4566b = gVar;
    }

    @Override // java.lang.Runnable
    public final synchronized void run() {
        List<PluginDownloadBean> listProvidePluginConfig;
        boolean z;
        if (this.f4565a && g.a().d) {
            return;
        }
        if (System.currentTimeMillis() - this.f4566b.f4579a < 300000) {
            return;
        }
        if (d.a(Zeus.getAppApplication())) {
            for (int i = 0; i < 2; i++) {
                try {
                    ZeusLogger.d(ZeusLogger.TAG_DOWNLOAD, "Skip! useInternalNetworkImpl = false!");
                    PluginProvider pluginProvider = com.bytedance.pangle.g.a().f4632b.getPluginProvider();
                    if (pluginProvider != null && (listProvidePluginConfig = pluginProvider.providePluginConfig()) != null) {
                        this.f4566b.f4579a = System.currentTimeMillis();
                        ZeusLogger.i(ZeusLogger.TAG_DOWNLOAD, "handlePlugins, pluginSize = " + listProvidePluginConfig.size());
                        for (int i2 = 0; i2 < listProvidePluginConfig.size(); i2++) {
                            PluginDownloadBean pluginDownloadBean = listProvidePluginConfig.get(i2);
                            if (pluginDownloadBean != null) {
                                if (pluginDownloadBean.isOffline) {
                                    g.a(pluginDownloadBean);
                                    Zeus.markOfflineFlag(pluginDownloadBean.mPackageName);
                                } else {
                                    if (Zeus.hasOfflineFlag(pluginDownloadBean.mPackageName)) {
                                        Zeus.clearOfflineFlag(pluginDownloadBean.mPackageName);
                                    }
                                    if (pluginDownloadBean.isRevert) {
                                        if (pluginDownloadBean.mVersionCode < Zeus.getInstalledPluginVersion(pluginDownloadBean.mPackageName)) {
                                            Zeus.unInstallPlugin(pluginDownloadBean.mPackageName);
                                        }
                                        if (pluginDownloadBean.mVersionCode == 0) {
                                        }
                                    }
                                    z = true;
                                }
                                z = false;
                            } else {
                                z = false;
                            }
                            if (!z) {
                                listProvidePluginConfig.remove(pluginDownloadBean);
                            }
                        }
                        g.a(listProvidePluginConfig);
                    }
                    return;
                } catch (Exception e) {
                    ZeusLogger.e(ZeusLogger.TAG_DOWNLOAD, "Request plugin config failed!!!", e);
                }
            }
        }
    }
}
