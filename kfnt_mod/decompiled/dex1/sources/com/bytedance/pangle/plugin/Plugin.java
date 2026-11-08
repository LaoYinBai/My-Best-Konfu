package com.bytedance.pangle.plugin;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.appcompat.widget.ActivityChooserView;
import com.bytedance.pangle.PluginClassLoader;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.ZeusApplication;
import com.bytedance.pangle.ZeusPluginStateListener;
import com.bytedance.pangle.g;
import com.bytedance.pangle.helper.PluginDirHelper;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.e;
import com.bytedance.pangle.util.j;
import com.bytedance.pangle.wrapper.PluginApplicationWrapper;
import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class Plugin {
    public static final int LIFE_INSTALLED = 2;
    public static final int LIFE_LOADED = 3;
    public static final int LIFE_PENDING = 1;
    private static final String TAG = "Plugin";
    private int mApiVersionCode;
    public ZeusApplication mApplication;
    public PluginClassLoader mClassLoader;
    private Handler mHandler;
    public PluginApplicationWrapper mHostApplication;
    public ApplicationInfo mHostApplicationInfoHookSomeField;
    private volatile boolean mInitialized;
    public int mMaxVersionCode;
    public int mMinVersionCode;
    private String mPackageDir;
    public String mPkgName;
    public Resources mResources;
    private int mVersionCode;
    public HashMap<String, ActivityInfo> pluginActivities = new HashMap<>();
    public HashMap<String, ServiceInfo> pluginServices = new HashMap<>();
    public HashMap<String, ActivityInfo> pluginReceiver = new HashMap<>();
    public HashMap<String, ProviderInfo> pluginProvider = new HashMap<>();
    private volatile int mLifeCycle = 1;
    final Object installLock = new Object();
    final Object initializeLock = new Object();

    @Deprecated
    public boolean isHostModule() {
        return false;
    }

    public Plugin(JSONObject jSONObject, Handler handler) throws JSONException {
        this.mMaxVersionCode = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        try {
            this.mHandler = handler;
            this.mPkgName = jSONObject.getString("packageName");
            this.mMinVersionCode = jSONObject.optInt("minPluginVersion", 0);
            this.mMaxVersionCode = jSONObject.optInt("maxPluginVersion", ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
            this.mApiVersionCode = jSONObject.getInt("apiVersionCode");
        } catch (JSONException e) {
            throw e;
        }
    }

    void init() {
        boolean z;
        if (this.mInitialized) {
            return;
        }
        synchronized (this.initializeLock) {
            if (this.mInitialized) {
                return;
            }
            if (com.bytedance.pangle.helper.c.b(Zeus.getAppApplication())) {
                if (!TextUtils.isEmpty(j.a().f4718a.getString("HOST_ABI_".concat(String.valueOf(this.mPkgName)), ""))) {
                    j jVarA = j.a();
                    String str = this.mPkgName;
                    boolean z2 = !TextUtils.equals(jVarA.f4718a.getString("HOST_ABI_".concat(String.valueOf(str)), ""), Zeus.getHostAbi());
                    ZeusLogger.i(ZeusLogger.TAG_INIT, "ZeusSpUtils isHostAbiUpdate HOST_ABI=" + jVarA.f4718a.getString("HOST_ABI_".concat(String.valueOf(str)), "") + ", " + Zeus.getHostAbi() + ", result=" + z2);
                    z = z2;
                } else {
                    z = false;
                }
                deleteIfNeeded();
                int installedMaxVer = getInstalledMaxVer();
                if (checkVersionValid(installedMaxVer, this.mApiVersionCode, g.a().f4632b.getHostVersionCode(), z)) {
                    updateToInstalled(installedMaxVer);
                } else {
                    installedMaxVer = 0;
                }
                deleteOtherExpiredVer(installedMaxVer);
                ZeusLogger.i(ZeusLogger.TAG_INIT, "Plugin initPlugins result=".concat(String.valueOf(this)));
                j jVarA2 = j.a();
                String str2 = this.mPkgName;
                SharedPreferences.Editor editorEdit = jVarA2.f4718a.edit();
                editorEdit.putString("ROM_LAST_".concat(String.valueOf(str2)), Build.VERSION.INCREMENTAL);
                editorEdit.apply();
                j jVarA3 = j.a();
                String str3 = this.mPkgName;
                String string = jVarA3.f4718a.getString("HOST_ABI_".concat(String.valueOf(str3)), "");
                SharedPreferences.Editor editorEdit2 = jVarA3.f4718a.edit();
                editorEdit2.putString("HOST_ABI_".concat(String.valueOf(str3)), Zeus.getHostAbi());
                editorEdit2.apply();
                ZeusLogger.i(ZeusLogger.TAG_INIT, "ZeusSpUtils setHostAbiUpdated HOST_ABI=" + string + " --> " + Zeus.getHostAbi());
                j jVarA4 = j.a();
                String str4 = this.mPkgName;
                int i = this.mApiVersionCode;
                int iA = jVarA4.a(str4);
                if (iA != i) {
                    SharedPreferences.Editor editorEdit3 = jVarA4.f4718a.edit();
                    editorEdit3.putInt("PLUGIN_API_VERSION_".concat(String.valueOf(str4)), i);
                    editorEdit3.apply();
                }
                ZeusLogger.i(ZeusLogger.TAG_INIT, "ZeusSpUtils setPluginApiVersion " + iA + " --> " + i);
            } else {
                updateInstallStateFromMainProcess();
            }
            this.mInitialized = true;
        }
    }

    public void setLifeCycle(int i) {
        this.mLifeCycle = i;
    }

    public int getLifeCycle() throws JSONException {
        updateInstallStateFromMainProcess();
        return this.mLifeCycle;
    }

    public int getApiVersionCode() {
        return this.mApiVersionCode;
    }

    public int getVersion() throws JSONException {
        updateInstallStateFromMainProcess();
        return this.mVersionCode;
    }

    private void updateInstallStateFromMainProcess() throws JSONException {
        com.bytedance.pangle.c cVarA;
        try {
            if (com.bytedance.pangle.helper.c.b(Zeus.getAppApplication()) || this.mLifeCycle >= 2 || (cVarA = com.bytedance.pangle.servermanager.b.a()) == null || !cVarA.a(this.mPkgName)) {
                return;
            }
            updateToInstalled(cVarA.b(this.mPkgName));
        } catch (Throwable th) {
            ZeusLogger.e(ZeusLogger.TAG_PPM, "updateInstallStateFromMainProcess error. process = " + com.bytedance.pangle.helper.c.a(Zeus.getAppApplication()), this.mPkgName, th);
        }
    }

    public boolean isInstalled() throws JSONException {
        updateInstallStateFromMainProcess();
        return this.mLifeCycle >= 2;
    }

    private void updateToInstalled(int i) {
        this.mVersionCode = i;
        this.mLifeCycle = 2;
    }

    public boolean isLoaded() {
        return this.mLifeCycle == 3;
    }

    private int getInstalledMaxVer() throws NumberFormatException {
        if (TextUtils.isEmpty(this.mPackageDir)) {
            this.mPackageDir = PluginDirHelper.getPackageDir(this.mPkgName);
        }
        File[] fileArrListFiles = new File(this.mPackageDir).listFiles(new FileFilter() { // from class: com.bytedance.pangle.plugin.Plugin.1
            @Override // java.io.FileFilter
            public final boolean accept(File file) {
                return file != null && file.getName().matches("^version-(\\d+)$");
            }
        });
        int i = -1;
        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
            for (File file : fileArrListFiles) {
                int i2 = Integer.parseInt(file.getName().split("-")[1]);
                if (i2 > i && j.a().a(this.mPkgName, i2) && new File(PluginDirHelper.getSourceFile(this.mPkgName, i2)).exists()) {
                    i = i2;
                }
            }
        }
        ZeusLogger.i(ZeusLogger.TAG_INIT, "Plugin getInstalledMaxVersion, pkg=" + this.mPkgName + ", maxVer=" + i);
        return i;
    }

    private boolean checkVersionValid(int i, int i2, long j, boolean z) {
        int iA = j.a().a(this.mPkgName);
        if (iA > i2) {
            ZeusLogger.w(ZeusLogger.TAG_INIT, TAG.concat(String.valueOf(String.format(" checkVersionValid %s apiVersion downgrade , lastApiVersion=%s , currentApiVersion=%s", this.mPkgName, Integer.valueOf(iA), Integer.valueOf(i2)))));
            return false;
        }
        boolean z2 = i >= 0 && i >= this.mMinVersionCode && i <= this.mMaxVersionCode;
        int i3 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        if (z2 && i2 != -1) {
            j jVarA = j.a();
            String str = this.mPkgName;
            int i4 = jVarA.f4718a.getInt("API_MIN_" + str + "_" + i, 0);
            j jVarA2 = j.a();
            String str2 = this.mPkgName;
            int i5 = jVarA2.f4718a.getInt("API_MAX_" + str2 + "_" + i, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
            if (i5 == 0) {
                i5 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            }
            if (i2 < i4 || i2 > i5) {
                ZeusLogger.w(ZeusLogger.TAG_INIT, TAG.concat(String.valueOf(String.format(" checkVersionValid plugin[%s, ver=%s] is not compatible with api[ver_code=%s], apiCompatibleVer=[%s,%s]", this.mPkgName, Integer.valueOf(this.mVersionCode), Integer.valueOf(i2), Integer.valueOf(i4), Integer.valueOf(i5)))));
                z2 = false;
            }
        }
        if (z2 && j != -1) {
            j jVarA3 = j.a();
            String str3 = this.mPkgName;
            int i6 = jVarA3.f4718a.getInt("HOST_MIN_" + str3 + "_" + i, 0);
            j jVarA4 = j.a();
            String str4 = this.mPkgName;
            int i7 = jVarA4.f4718a.getInt("HOST_MAX_" + str4 + "_" + i, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
            if (i7 != 0) {
                i3 = i7;
            }
            if (j < i6 || j > i3) {
                ZeusLogger.w(ZeusLogger.TAG_INIT, TAG.concat(String.valueOf(String.format(" checkVersionValid plugin[%s, ver=%s] is not compatible with host[ver_code=%s], hostCompatibleVer=[%s,%s]", this.mPkgName, Integer.valueOf(this.mVersionCode), Long.valueOf(j), Integer.valueOf(i6), Integer.valueOf(i3)))));
                z2 = false;
            }
        }
        if (z2 && z && com.bytedance.pangle.helper.b.b(new File(PluginDirHelper.getSourceFile(this.mPkgName, i)))) {
            ZeusLogger.w(ZeusLogger.TAG_INIT, TAG.concat(String.valueOf(String.format(" checkVersionValid plugin[%s, ver=%s] not match hostAbi", this.mPkgName, Integer.valueOf(i)))));
            z2 = false;
        }
        ZeusLogger.i(ZeusLogger.TAG_INIT, "Plugin checkVersionValid, pkg=" + this.mPkgName + ", ver=" + this.mVersionCode + ", valid=" + z2);
        return z2;
    }

    private void deleteIfNeeded() {
        if (com.bytedance.pangle.helper.c.b(Zeus.getAppApplication())) {
            if (j.a().f4718a.getBoolean("DELETED__".concat(String.valueOf(this.mPkgName)), false)) {
                j jVarA = j.a();
                String str = this.mPkgName;
                SharedPreferences.Editor editorEdit = jVarA.f4718a.edit();
                editorEdit.remove("DELETED__".concat(String.valueOf(str)));
                editorEdit.apply();
                deleteInstalledPlugin();
                ZeusLogger.w(ZeusLogger.TAG_INIT, "Plugin deleteIfNeeded " + this.mPkgName);
            }
        }
    }

    private void deleteInstalledPlugin() {
        if (TextUtils.isEmpty(this.mPackageDir)) {
            this.mPackageDir = PluginDirHelper.getPackageDir(this.mPkgName);
        }
        new File(this.mPackageDir).listFiles(new FileFilter() { // from class: com.bytedance.pangle.plugin.Plugin.2
            @Override // java.io.FileFilter
            public final boolean accept(File file) throws NumberFormatException {
                if (file.getName().matches("^version-(\\d+)$")) {
                    j.a().a(Plugin.this.mPkgName, Integer.parseInt(file.getName().split("-")[1]), false);
                }
                return false;
            }
        });
        e.a(this.mPackageDir);
    }

    private void deleteOtherExpiredVer(int i) {
        if (com.bytedance.pangle.helper.c.b(Zeus.getAppApplication())) {
            if (TextUtils.isEmpty(this.mPackageDir)) {
                this.mPackageDir = PluginDirHelper.getPackageDir(this.mPkgName);
            }
            final String strConcat = PluginDirHelper.VERSION_PREFIX.concat(String.valueOf(i));
            new File(this.mPackageDir).listFiles(new FileFilter() { // from class: com.bytedance.pangle.plugin.Plugin.3
                @Override // java.io.FileFilter
                public final boolean accept(File file) throws NumberFormatException {
                    if (file != null && !strConcat.equals(file.getName()) && !"data".equals(file.getName())) {
                        e.a(file.getAbsolutePath());
                        ZeusLogger.w(ZeusLogger.TAG_INIT, "Plugin deleteOtherExpired " + file.getAbsolutePath());
                        if (file.getName().matches("^version-(\\d+)$")) {
                            j.a().a(Plugin.this.mPkgName, Integer.parseInt(file.getName().split("-")[1]), false);
                        }
                    }
                    return false;
                }
            });
        }
    }

    public void setHostCompatVersion(int i, int i2, int i3) {
        j jVarA = j.a();
        String str = this.mPkgName;
        SharedPreferences.Editor editorEdit = jVarA.f4718a.edit();
        editorEdit.putInt("HOST_MIN_" + str + "_" + i, i2);
        editorEdit.putInt("HOST_MAX_" + str + "_" + i, i3);
        editorEdit.apply();
    }

    public void setApiCompatVersion(int i, int i2, int i3) {
        j jVarA = j.a();
        String str = this.mPkgName;
        SharedPreferences.Editor editorEdit = jVarA.f4718a.edit();
        editorEdit.putInt("API_MIN_" + str + "_" + i, i2);
        editorEdit.putInt("API_MAX_" + str + "_" + i, i3);
        editorEdit.apply();
    }

    public boolean isVersionInstalled(int i) {
        return j.a().a(this.mPkgName, i);
    }

    boolean install(File file, com.bytedance.pangle.e.a.e eVar) {
        String str;
        int i;
        boolean z = false;
        try {
            ZeusLogger.i(ZeusLogger.TAG_INSTALL, "Plugin install from local file " + file + ", " + Thread.currentThread().getName());
            str = eVar.f4595a;
            i = eVar.f4596b;
        } catch (Throwable th) {
            ZeusLogger.e(ZeusLogger.TAG_INSTALL, "Plugin IMPOSSIBLE!!!", th);
        }
        synchronized (this.installLock) {
            try {
                ZeusLogger.i(ZeusLogger.TAG_INSTALL, "Plugin synchronized begin, plugin=".concat(String.valueOf(this)));
                boolean zCheckValid = checkValid(file, str, i);
                if (zCheckValid) {
                    boolean zA = b.a(file, str, i);
                    if (zA) {
                        try {
                            String strA = com.bytedance.pangle.util.d.a(PluginDirHelper.getSourceFile(this.mPkgName, i));
                            j jVarA = j.a();
                            String str2 = this.mPkgName;
                            SharedPreferences.Editor editorEdit = jVarA.f4718a.edit();
                            editorEdit.putString("MD5_".concat(String.valueOf(str2)), strA);
                            editorEdit.apply();
                            j.a().a(this.mPkgName, i, true);
                            ZeusLogger.i(ZeusLogger.TAG_INSTALL, "Plugin markPluginInstalled, " + this.mPkgName + ":" + i + " md5=" + strA);
                            e.a(file);
                        } catch (Throwable th2) {
                            th = th2;
                            z = zA;
                            throw th;
                        }
                    }
                    z = zA;
                }
                synchronized (this) {
                    try {
                        if (zCheckValid) {
                            if (this.mLifeCycle == 3) {
                                ZeusLogger.w(ZeusLogger.TAG_INSTALL, "Plugin LIFE_LOADED, valid next restart " + str + ":" + i);
                            } else if (z) {
                                updateToInstalled(i);
                                ZeusLogger.i(ZeusLogger.TAG_INSTALL, "Plugin INSTALLED " + str + ":" + i);
                                postPluginInstalled(this.mPkgName, z);
                            } else {
                                ZeusLogger.i(ZeusLogger.TAG_INSTALL, "Plugin INSTALL_FAILED" + str + ":" + i);
                                postPluginInstalled(this.mPkgName, z);
                                e.a(file);
                                ZeusLogger.w(ZeusLogger.TAG_INSTALL, "Plugin delete file by failedCount > 0 " + str + ":" + i);
                            }
                        } else {
                            e.a(file);
                            ZeusLogger.w(ZeusLogger.TAG_INSTALL, "Plugin deleting invalid " + str + ":" + i);
                        }
                    } catch (Throwable th3) {
                        throw th3;
                    }
                }
                return z;
            } catch (Throwable th4) {
                th = th4;
            }
        }
    }

    private void postPluginInstalled(final String str, final boolean z) {
        this.mHandler.post(new Runnable() { // from class: com.bytedance.pangle.plugin.Plugin.4
            @Override // java.lang.Runnable
            public final void run() {
                List<ZeusPluginStateListener> list = g.a().f4633c;
                if (list == null || list.size() <= 0) {
                    return;
                }
                Iterator<ZeusPluginStateListener> it = list.iterator();
                while (it.hasNext()) {
                    it.next().onPluginInstallResult(str, z);
                }
            }
        });
    }

    private boolean checkValid(File file, String str, int i) throws JSONException {
        if (!TextUtils.equals(this.mPkgName, str)) {
            ZeusLogger.w(ZeusLogger.TAG_INSTALL, "Plugin checkValid " + str + " package name not match !!!");
            return false;
        }
        if (i < this.mMinVersionCode || i > this.mMaxVersionCode) {
            ZeusLogger.w(ZeusLogger.TAG_INSTALL, "Plugin checkValid " + str + " " + String.format(" pluginApk ver[%s] not match plugin VerRange[%s, %s].", Integer.valueOf(i), Integer.valueOf(this.mMinVersionCode), Integer.valueOf(this.mMaxVersionCode)));
            return false;
        }
        if (i < this.mVersionCode && isInstalled()) {
            ZeusLogger.w(ZeusLogger.TAG_INSTALL, "Plugin checkValid " + str + String.format(" pluginApk ver[%s] lower than installed plugin[%s].", Integer.valueOf(i), Integer.valueOf(this.mVersionCode)));
            return false;
        }
        if (file == null || !file.exists()) {
            ZeusLogger.e(ZeusLogger.TAG_INSTALL, "Plugin checkValid " + str + " pluginApk not exist.");
            return false;
        }
        if (i == this.mVersionCode && j.a().f4718a.getString("MD5_".concat(String.valueOf(str)), "").equals(com.bytedance.pangle.util.d.a(file))) {
            ZeusLogger.e(ZeusLogger.TAG_INSTALL, "Plugin checkValid " + str + " pluginApk with the same md5 has already installed.");
            return false;
        }
        ZeusLogger.i(ZeusLogger.TAG_INSTALL, "Plugin checkValid " + str + ":" + i + " true");
        return true;
    }

    public boolean isOffline() {
        return j.a().d(this.mPkgName);
    }

    public void markOffline() {
        j.a().b(this.mPkgName);
    }

    public void clearOffline() {
        j.a().c(this.mPkgName);
    }

    public String getPackageVersionDir() {
        if (this.mVersionCode > 0) {
            return PluginDirHelper.getPackageVersionDir(this.mPkgName, this.mVersionCode);
        }
        return PluginDirHelper.getPackageDir(this.mPkgName);
    }

    public String getNativeLibraryDir() {
        if (this.mVersionCode > 0) {
            return PluginDirHelper.getNativeLibraryDir(this.mPkgName, this.mVersionCode);
        }
        return PluginDirHelper.getPackageDir(this.mPkgName);
    }

    public String toString() {
        return "Plugin{pkg=" + this.mPkgName + ", ver=" + this.mVersionCode + ", life=" + this.mLifeCycle + '}';
    }
}
