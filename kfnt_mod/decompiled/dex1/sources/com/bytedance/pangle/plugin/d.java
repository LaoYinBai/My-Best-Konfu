package com.bytedance.pangle.plugin;

import android.text.TextUtils;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.g;
import com.bytedance.pangle.helper.PluginDirHelper;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.e;
import com.bytedance.pangle.util.f;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class d implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private File f4663a = null;

    d() {
    }

    private void a(File file) {
        PluginProvider pluginProvider;
        File fileProvideBuiltInPlugin;
        ZeusLogger.i(ZeusLogger.TAG_INIT, "ZeusScanRunnable listPluginDownloadDir, dir = ".concat(String.valueOf(file)));
        file.listFiles(new FileFilter() { // from class: com.bytedance.pangle.plugin.d.1
            @Override // java.io.FileFilter
            public final boolean accept(File file2) {
                if (file2 != null && (file2.getName().endsWith(".apk") || file2.getName().endsWith(".jar"))) {
                    PluginManager.getInstance().asyncInstall(file2);
                    return true;
                }
                if (file2.getAbsolutePath().endsWith(".temp") && System.currentTimeMillis() - file2.lastModified() < 259200000) {
                    ZeusLogger.w(ZeusLogger.TAG_INIT, "ZeusScanRunnable installPluginDir find : ".concat(String.valueOf(file2)));
                    return false;
                }
                e.a(file2);
                ZeusLogger.w(ZeusLogger.TAG_INIT, "ZeusScanRunnable installPluginDir deleted : ".concat(String.valueOf(file2)));
                return false;
            }
        });
        if (file.equals(this.f4663a) || (pluginProvider = g.a().f4632b.getPluginProvider()) == null || (fileProvideBuiltInPlugin = pluginProvider.provideBuiltInPlugin()) == null) {
            return;
        }
        PluginManager.getInstance().asyncInstall(fileProvideBuiltInPlugin);
    }

    @Override // java.lang.Runnable
    public final void run() throws IOException {
        try {
            String downloadDir = PluginDirHelper.getDownloadDir();
            for (String str : Zeus.getAppApplication().getAssets().list("plugins/")) {
                if (str.endsWith(".apk")) {
                    String strConcat = "plugins/".concat(String.valueOf(str));
                    File file = new File(downloadDir, str);
                    ZeusLogger.i(ZeusLogger.TAG_INIT, "ZeusScanRunnable releaseCopyFile " + strConcat + " --> " + file.getAbsolutePath());
                    f.a(Zeus.getAppApplication().getAssets().open(strConcat), new FileOutputStream(file), 0L);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        a(new File(PluginDirHelper.getDownloadDir()));
        String pushDir = PluginDirHelper.getPushDir();
        if (TextUtils.isEmpty(pushDir)) {
            return;
        }
        this.f4663a = new File(pushDir);
        a(this.f4663a);
    }
}
