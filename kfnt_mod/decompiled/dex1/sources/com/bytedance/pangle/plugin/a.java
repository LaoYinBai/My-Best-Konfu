package com.bytedance.pangle.plugin;

import com.bytedance.pangle.download.ZeusPluginListener;
import com.bytedance.pangle.e.a.e;
import com.bytedance.pangle.log.ZeusLogger;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class a implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private File f4660a;

    /* renamed from: b, reason: collision with root package name */
    private ZeusPluginListener f4661b;

    a(File file, ZeusPluginListener zeusPluginListener) {
        this.f4660a = file;
        this.f4661b = zeusPluginListener;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        a();
    }

    final boolean a() throws Throwable {
        e eVarA = com.bytedance.pangle.e.a.d.a(this.f4660a);
        boolean zInstall = false;
        if (eVarA != null) {
            Plugin plugin = PluginManager.getInstance().getPlugin(eVarA.f4595a);
            if (plugin != null) {
                zInstall = plugin.install(this.f4660a, eVarA);
                if (this.f4661b != null) {
                    this.f4661b.onEvent(zInstall ? 21 : 22, "install result = ".concat(String.valueOf(zInstall)));
                }
            } else {
                if (this.f4661b != null) {
                    this.f4661b.onEvent(22, "cannot query valid plugin !!! packageName = " + eVarA.f4595a);
                }
                ZeusLogger.w(ZeusLogger.TAG_INSTALL, "PluginInstallRunnable cannot query valid plugin !!! packageName = " + eVarA.f4595a);
            }
        } else {
            if (this.f4661b != null) {
                this.f4661b.onEvent(22, "read local file package info failed !!!" + this.f4660a.getAbsolutePath() + " exists:" + this.f4660a.exists());
            }
            ZeusLogger.w(ZeusLogger.TAG_INSTALL, "PluginInstallRunnable read local file package info failed !!!" + this.f4660a.getAbsolutePath() + " exists:" + this.f4660a.exists());
        }
        return zInstall;
    }
}
