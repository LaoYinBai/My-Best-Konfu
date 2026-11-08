package com.bytedance.pangle.plugin;

import android.content.ComponentCallbacks;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Configuration;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.alipay.sdk.util.f;
import com.bytedance.pangle.PluginClassLoader;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.ZeusApplication;
import com.bytedance.pangle.b.b;
import com.bytedance.pangle.helper.PluginDirHelper;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.provider.ContentProviderManager;
import com.bytedance.pangle.res.PluginResources;
import com.bytedance.pangle.transform.ZeusTransformUtils;
import com.bytedance.pangle.wrapper.PluginApplicationWrapper;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class c {
    c() {
    }

    final synchronized boolean a(String str) {
        Plugin plugin = PluginManager.getInstance().getPlugin(str);
        if (plugin == null) {
            ZeusLogger.w(ZeusLogger.TAG_LOAD, "PluginLoader loadPlugin, plugin == null, pkg = ".concat(String.valueOf(str)));
            return false;
        }
        if (!plugin.isInstalled()) {
            ZeusLogger.w(ZeusLogger.TAG_LOAD, "PluginLoader loadPlugin, UN_INSTALLED, ".concat(String.valueOf(str)));
            return false;
        }
        if (plugin.isLoaded()) {
            return true;
        }
        com.bytedance.pangle.log.c cVarA = com.bytedance.pangle.log.c.a(ZeusLogger.TAG_LOAD, "PluginLoader", "loadPlugin:".concat(String.valueOf(str)));
        a(com.bytedance.pangle.b.b.g, b.a.w, plugin.mPkgName, plugin.getVersion(), -1L, null);
        StringBuilder sb = new StringBuilder();
        boolean zA = a(str, plugin, sb);
        cVarA.b("loadPluginInternal:".concat(String.valueOf(zA)));
        if (zA) {
            plugin.setLifeCycle(3);
            a(com.bytedance.pangle.b.b.h, b.a.x, plugin.mPkgName, plugin.getVersion(), cVarA.a(), sb.toString());
        } else {
            sb.append("plugin:");
            sb.append(plugin.mPkgName);
            sb.append(" versionCode:");
            sb.append(plugin.getVersion());
            sb.append("load failed;");
            a(com.bytedance.pangle.b.b.h, b.a.y, plugin.mPkgName, plugin.getVersion(), -1L, sb.toString());
        }
        ZeusLogger.i(ZeusLogger.TAG_LOAD, "PluginLoader loadFinished, ".concat(String.valueOf(plugin)));
        if (!plugin.isLoaded()) {
            return false;
        }
        ZeusLogger.d(ZeusLogger.TAG_LOAD, "PluginLoader postResult, LOADED " + plugin.mPkgName);
        return true;
    }

    private static synchronized boolean a(String str, final Plugin plugin, StringBuilder sb) {
        try {
            com.bytedance.pangle.log.c cVarA = com.bytedance.pangle.log.c.a(ZeusLogger.TAG_LOAD, "PluginLoader", "load:".concat(String.valueOf(str)));
            if (plugin == null) {
                sb.append("loadPluginInternal, plugin == null;");
                ZeusLogger.e(ZeusLogger.TAG_LOAD, "PluginLoader loadPluginInternal, plugin[" + str + "] not exist !!!", str);
                return false;
            }
            if (!plugin.isInstalled()) {
                sb.append("loadPluginInternal, !plugin.isInstalled();");
                ZeusLogger.e(ZeusLogger.TAG_LOAD, "PluginLoader loadPluginInternal, plugin[" + str + "] not installed !!!", str);
                return false;
            }
            long jA = cVarA.a("isInstalled");
            if (jA > 20 || jA < 0) {
                sb.append("isInstall cost:");
                sb.append(jA);
                sb.append(f.f2657b);
            }
            String sourceFile = PluginDirHelper.getSourceFile(plugin.mPkgName, plugin.getVersion());
            if (!new File(sourceFile).exists()) {
                sb.append("loadPluginInternal, sourceApk not exist;");
                ZeusLogger.e(ZeusLogger.TAG_LOAD, "PluginLoader loadPluginInternal, plugin[" + str + "] file not exist !!!", str);
                return false;
            }
            long jA2 = cVarA.a("getSourceFile");
            if (jA2 > 20 || jA2 < 0) {
                sb.append("getSourceFile cost:");
                sb.append(jA2);
                sb.append(f.f2657b);
            }
            File file = new File(PluginDirHelper.getNativeLibraryDir(plugin.mPkgName, plugin.getVersion()));
            long jA3 = cVarA.a("getNativeLibraryDir");
            if (jA3 > 20 || jA3 < 0) {
                sb.append("getNativeLibraryDir cost:");
                sb.append(jA3);
                sb.append(f.f2657b);
            }
            File file2 = new File(file.getParentFile(), "dalvik-cache");
            if (!file2.exists()) {
                file2.mkdirs();
            }
            long jA4 = cVarA.a("dalvikCacheDir");
            if (jA4 > 20 || jA4 < 0) {
                sb.append("dalvikCacheDirTime cost:");
                sb.append(jA4);
                sb.append(f.f2657b);
            }
            plugin.mClassLoader = new PluginClassLoader(sourceFile, file2.getPath(), file.getAbsolutePath(), null);
            long jA5 = cVarA.a("classloader");
            if (jA5 > 20 || jA5 < 0) {
                sb.append("classloader cost:");
                sb.append(jA5);
                sb.append(f.f2657b);
            }
            PackageInfo packageArchiveInfo = Zeus.getAppApplication().getPackageManager().getPackageArchiveInfo(sourceFile, 15);
            long jA6 = cVarA.a("getPackageInfo");
            if (jA6 > 20 || jA6 < 0) {
                sb.append("getPackageInfo cost:");
                sb.append(jA6);
                sb.append(f.f2657b);
            }
            plugin.mHostApplication = (PluginApplicationWrapper) ZeusTransformUtils.wrapperContext2Application(Zeus.getAppApplication(), plugin.mPkgName);
            plugin.mHostApplicationInfoHookSomeField = new ApplicationInfo(Zeus.getAppApplication().getApplicationInfo());
            plugin.mHostApplicationInfoHookSomeField.nativeLibraryDir = file.getAbsolutePath();
            plugin.mHostApplicationInfoHookSomeField.dataDir = plugin.mHostApplication.getDataDir().getAbsolutePath();
            plugin.mHostApplicationInfoHookSomeField.sourceDir = sourceFile;
            if (TextUtils.isEmpty(packageArchiveInfo.applicationInfo.sourceDir)) {
                packageArchiveInfo.applicationInfo.sourceDir = sourceFile;
            }
            if (TextUtils.isEmpty(packageArchiveInfo.applicationInfo.publicSourceDir)) {
                packageArchiveInfo.applicationInfo.publicSourceDir = sourceFile;
            }
            long jA7 = cVarA.a("setApplication");
            if (jA7 > 20 || jA7 < 0) {
                sb.append("setApplication cost:");
                sb.append(jA7);
                sb.append(f.f2657b);
            }
            plugin.mResources = new PluginResources(Zeus.getAppApplication().getPackageManager().getResourcesForApplication(packageArchiveInfo.applicationInfo));
            long jA8 = cVarA.a("makeResources");
            if (jA8 > 20 || jA8 < 0) {
                sb.append("makeResources cost:");
                sb.append(jA8);
                sb.append(f.f2657b);
            }
            Zeus.getAppApplication().registerComponentCallbacks(new ComponentCallbacks() { // from class: com.bytedance.pangle.plugin.c.1
                @Override // android.content.ComponentCallbacks
                public final void onLowMemory() {
                }

                @Override // android.content.ComponentCallbacks
                public final void onConfigurationChanged(Configuration configuration) {
                    plugin.mResources.updateConfiguration(configuration, Zeus.getAppApplication().getResources().getDisplayMetrics());
                }
            });
            ActivityInfo[] activityInfoArr = packageArchiveInfo.activities;
            if (activityInfoArr != null) {
                for (ActivityInfo activityInfo : activityInfoArr) {
                    if (TextUtils.isEmpty(activityInfo.processName) || !activityInfo.processName.contains(":")) {
                        activityInfo.processName = "main";
                    } else {
                        activityInfo.processName = activityInfo.processName.split(":")[1];
                    }
                    plugin.pluginActivities.put(activityInfo.name, activityInfo);
                }
            }
            ServiceInfo[] serviceInfoArr = packageArchiveInfo.services;
            if (serviceInfoArr != null) {
                for (ServiceInfo serviceInfo : serviceInfoArr) {
                    if (TextUtils.isEmpty(serviceInfo.processName) || !serviceInfo.processName.contains(":")) {
                        serviceInfo.processName = "main";
                    } else {
                        serviceInfo.processName = serviceInfo.processName.split(":")[1];
                    }
                    plugin.pluginServices.put(serviceInfo.name, serviceInfo);
                }
            }
            long jA9 = cVarA.a("resolveActivityServices");
            if (jA9 > 20 || jA9 < 0) {
                sb.append("resolveActivityServices cost:");
                sb.append(jA9);
                sb.append(f.f2657b);
            }
            ActivityInfo[] activityInfoArr2 = packageArchiveInfo.receivers;
            if (activityInfoArr2 != null) {
                for (ActivityInfo activityInfo2 : activityInfoArr2) {
                    if (TextUtils.isEmpty(activityInfo2.processName) || !activityInfo2.processName.contains(":")) {
                        activityInfo2.processName = "main";
                    } else {
                        activityInfo2.processName = activityInfo2.processName.split(":")[1];
                    }
                    plugin.pluginReceiver.put(activityInfo2.name, activityInfo2);
                }
            }
            long jA10 = cVarA.a("resolveReceiver");
            if (jA10 > 20 || jA10 < 0) {
                sb.append("resolveReceiver cost:");
                sb.append(jA10);
                sb.append(f.f2657b);
            }
            ProviderInfo[] providerInfoArr = packageArchiveInfo.providers;
            if (providerInfoArr != null) {
                for (ProviderInfo providerInfo : providerInfoArr) {
                    if (TextUtils.isEmpty(providerInfo.processName) || !providerInfo.processName.contains(":")) {
                        providerInfo.processName = "main";
                    } else {
                        providerInfo.processName = providerInfo.processName.split(":")[1];
                    }
                    plugin.pluginProvider.put(providerInfo.name, providerInfo);
                }
            }
            long jA11 = cVarA.a("resolveProvider");
            if (jA11 > 20 || jA11 < 0) {
                sb.append("resolveProvider cost:");
                sb.append(jA11);
                sb.append(f.f2657b);
            }
            if (plugin.pluginProvider != null && plugin.pluginProvider.size() > 0) {
                ContentProviderManager.getInstance().installContentProviders(plugin.pluginProvider.values(), plugin);
            }
            long jA12 = cVarA.a("installProvider");
            if (jA12 > 20 || jA12 < 0) {
                sb.append("installProvider cost:");
                sb.append(jA12);
                sb.append(f.f2657b);
            }
            if (!TextUtils.isEmpty(packageArchiveInfo.applicationInfo.className)) {
                plugin.mApplication = (ZeusApplication) plugin.mClassLoader.loadClass(packageArchiveInfo.applicationInfo.className).newInstance();
                plugin.mApplication.attach(plugin, Zeus.getAppApplication());
            }
            long jA13 = cVarA.a("makeApplication");
            if (jA13 > 20 || jA13 < 0) {
                sb.append("makeApplication cost:");
                sb.append(jA13);
                sb.append(f.f2657b);
            }
            return true;
        } catch (Throwable th) {
            sb.append("loadPluginInternal ");
            sb.append(th.getMessage());
            sb.append(f.f2657b);
            ZeusLogger.e(ZeusLogger.TAG_LOAD, "PluginLoader loadPluginInternal, plugin[" + str + "] ", str, th);
            return false;
        }
    }

    private static void a(String str, int i, @NonNull String str2, int i2, long j, String str3) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject.putOpt("status_code", com.bytedance.pangle.log.d.a(Integer.valueOf(i)));
            jSONObject.putOpt("plugin_package_name", com.bytedance.pangle.log.d.a(str2));
            jSONObject.putOpt("version_code", com.bytedance.pangle.log.d.a(Integer.valueOf(i2)));
            jSONObject3.putOpt("duration", com.bytedance.pangle.log.d.a(Long.valueOf(j)));
            jSONObject2.putOpt("message", com.bytedance.pangle.log.d.a(str3));
            jSONObject2.putOpt("timestamp", com.bytedance.pangle.log.d.a(Long.valueOf(System.currentTimeMillis())));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        com.bytedance.pangle.b.b.a().a(str, jSONObject, jSONObject3, jSONObject2);
    }
}
