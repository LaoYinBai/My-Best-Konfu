package com.bytedance.sdk.openadsdk.api.plugin;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.bytedance.b.a.c.h;
import com.bytedance.pangle.ApkVerifier;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.ZeusParam;
import com.bytedance.pangle.ZeusPluginStateListener;
import com.bytedance.pangle.download.PluginDownloadBean;
import com.bytedance.pangle.helper.PluginDirHelper;
import com.bytedance.pangle.log.IZeusReporter;
import com.bytedance.pangle.plugin.Plugin;
import com.bytedance.pangle.plugin.PluginProvider;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdEvent;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTAppContextHolder;
import com.bytedance.sdk.openadsdk.TTPluginListener;
import dalvik.system.DexClassLoader;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {
    private static volatile DexClassLoader f;
    private String h;

    /* renamed from: a, reason: collision with root package name */
    private static final String f5269a = "next" + File.separator;

    /* renamed from: b, reason: collision with root package name */
    private static final String f5270b = File.separator + "conf";

    /* renamed from: c, reason: collision with root package name */
    private static final CountDownLatch f5271c = new CountDownLatch(1);
    private static final HashMap<String, TTPluginListener> d = new HashMap<>();
    private static final HashMap<String, Handler> e = new HashMap<>();
    private static volatile f g = null;

    private f(Context context) {
        e.a(context);
        c(context.getApplicationContext());
    }

    public static f a(Context context) {
        if (g == null) {
            synchronized (f.class) {
                if (g == null) {
                    g = new f(context);
                }
            }
        }
        return g;
    }

    public DexClassLoader a() throws InterruptedException {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            if (!Zeus.isPluginInstalled(TTAdConstant.BUILT_IN_PLUGIN_NAME)) {
                f5271c.await(60000L, TimeUnit.MILLISECONDS);
            }
            if (!Zeus.isPluginLoaded(TTAdConstant.BUILT_IN_PLUGIN_NAME) && Zeus.loadPlugin(TTAdConstant.BUILT_IN_PLUGIN_NAME)) {
                f = Zeus.getPlugin(TTAdConstant.BUILT_IN_PLUGIN_NAME).mClassLoader;
            }
        } catch (Exception e2) {
            com.bytedance.sdk.openadsdk.api.b.d.a("TTPluginManager", "Unexpected error for load plugin.", e2);
            e.a(3, e2.getMessage(), System.currentTimeMillis() - jCurrentTimeMillis);
        }
        return f;
    }

    private void c(final Context context) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            ZeusParam zeusParamBuild = new ZeusParam.Builder().autoFetch(true).closeDefaultReport(true).reporter(new IZeusReporter() { // from class: com.bytedance.sdk.openadsdk.api.plugin.f.3
                @Override // com.bytedance.pangle.log.IZeusReporter
                public void report(String str, JSONObject jSONObject) {
                    e.a(str, jSONObject);
                }
            }).verifyWith(new ApkVerifier() { // from class: com.bytedance.sdk.openadsdk.api.plugin.f.2
                @Override // com.bytedance.pangle.ApkVerifier
                public boolean verify(File file) {
                    d dVarB = f.b(file);
                    if (dVarB == null) {
                        return a.f5279a.a(f.this.h, file);
                    }
                    return a.f5279a.a(dVarB.f5261a, file);
                }
            }).pluginProvider(new PluginProvider() { // from class: com.bytedance.sdk.openadsdk.api.plugin.f.1
                @Override // com.bytedance.pangle.plugin.PluginProvider
                public boolean useLocalPlugin() {
                    return true;
                }

                @Override // com.bytedance.pangle.plugin.PluginProvider
                public File provideBuiltInPlugin() {
                    if (Zeus.isPluginInstalled(TTAdConstant.BUILT_IN_PLUGIN_NAME)) {
                        return null;
                    }
                    return f.this.d(context);
                }

                @Override // com.bytedance.pangle.plugin.PluginProvider
                public List<PluginDownloadBean> providePluginConfig() {
                    return f.e(context);
                }
            }).withCheckPermission(false).build();
            PluginDirHelper.setPluginDir(f(context));
            Zeus.init((Application) context, zeusParamBuild);
            Zeus.registerPluginStateListener(new ZeusPluginStateListener() { // from class: com.bytedance.sdk.openadsdk.api.plugin.f.4
                @Override // com.bytedance.pangle.ZeusPluginStateListener
                public void onPluginInstallResult(String str, boolean z) {
                    com.bytedance.sdk.openadsdk.api.b.d.a("TTPluginManager", "Plugin install result: [" + str + "]," + z);
                    if (!TTAdConstant.BUILT_IN_PLUGIN_NAME.equals(str)) {
                        f.b(z, str, (TTPluginListener) f.d.get(str));
                        return;
                    }
                    if (z && Zeus.loadPlugin(str)) {
                        DexClassLoader unused = f.f = Zeus.getPlugin(str).mClassLoader;
                    }
                    f.f5271c.countDown();
                }
            });
        } catch (Throwable th) {
            com.bytedance.sdk.openadsdk.api.b.d.a("TTPluginManager", "Unexpected error for init zeus.", th);
            e.a(4, th.getMessage(), System.currentTimeMillis() - jCurrentTimeMillis);
        }
    }

    private File a(File file, String str) {
        if (TextUtils.isEmpty(str)) {
            com.bytedance.sdk.openadsdk.api.b.d.a("TTPluginManager", "Selected plugin with empty sign");
            return null;
        }
        this.h = str;
        return file;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File d(Context context) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        AssetManager assets = context.getAssets();
        try {
            String strA = com.bytedance.sdk.openadsdk.api.plugin.b.a(PluginConstants.getSign());
            File fileF = f(context);
            File file = new File(fileF, (strA + "-4021-4021") + ".apk");
            if (a(assets.open(strA), file)) {
                com.bytedance.sdk.openadsdk.api.b.d.c("TTPluginManager", "Select nested");
                return a(file, PluginConstants.getSign());
            }
            throw new IOException("Select nested failed");
        } catch (Throwable th) {
            if (th instanceof FileNotFoundException) {
                e.a(1, th.getMessage(), System.currentTimeMillis() - jCurrentTimeMillis);
                return null;
            }
            e.a(2, th.getMessage(), System.currentTimeMillis() - jCurrentTimeMillis);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static d b(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        File file2 = new File(g(TTAppContextHolder.getContext()), b(file.getName()) + ".conf");
        if (file2.exists()) {
            return a(c(file2));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<PluginDownloadBean> e(Context context) {
        File[] fileArrListFiles = g(context).listFiles(new FilenameFilter() { // from class: com.bytedance.sdk.openadsdk.api.plugin.f.5
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str) {
                return str != null && str.endsWith(".conf");
            }
        });
        if (fileArrListFiles == null || fileArrListFiles.length == 0) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (File file : fileArrListFiles) {
            d dVarA = a(c(file));
            if (dVarA != null && dVarA.f5262b.exists()) {
                arrayList.add(dVarA);
            }
        }
        return arrayList;
    }

    public Bundle a(String str, Bundle bundle) {
        Plugin plugin;
        if (Zeus.isPluginInstalled(str) && (plugin = Zeus.getPlugin(str)) != null) {
            bundle.putString(PluginConstants.KEY_PLUGIN_VERSION, a(plugin.getVersion()));
        }
        g.a(str, bundle);
        Bundle bundle2 = new Bundle();
        bundle2.putBundle(str, bundle);
        Bundle bundle3 = new Bundle();
        bundle3.putBundle(PluginConstants.KEY_PL_CONFIG_INFO, bundle2);
        return bundle3;
    }

    public void a(final TTPluginListener tTPluginListener) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() { // from class: com.bytedance.sdk.openadsdk.api.plugin.f.6
            @Override // java.lang.Runnable
            public void run() {
                com.bytedance.sdk.openadsdk.api.b.d.a("TTPluginManager", "Load plugin failed, caused by timeout.");
                tTPluginListener.onPluginListener(1001, null, null, null);
            }
        }, 180000L);
        String strPackageName = tTPluginListener.packageName();
        Plugin plugin = (Zeus.isPluginInstalled(strPackageName) && (Zeus.isPluginLoaded(strPackageName) || Zeus.loadPlugin(strPackageName))) ? Zeus.getPlugin(strPackageName) : null;
        StringBuilder sb = new StringBuilder();
        sb.append("Find plugin:");
        sb.append(plugin != null);
        com.bytedance.sdk.openadsdk.api.b.d.a("TTPluginManager", sb.toString());
        if (plugin != null) {
            a(plugin, tTPluginListener);
            handler.removeCallbacksAndMessages(null);
            tTPluginListener.onPluginListener(1000, plugin.mClassLoader, plugin.mResources, null);
        } else {
            d.put(strPackageName, tTPluginListener);
            e.put(strPackageName, handler);
        }
    }

    public static d a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return a(new JSONObject(str));
        } catch (JSONException unused) {
            com.bytedance.sdk.openadsdk.api.b.d.d("TTPluginManager", "Invalid plugin info:" + str);
            return null;
        }
    }

    public static d a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        d dVar = new d();
        dVar.mPackageName = jSONObject.optString(h.t);
        dVar.mVersionCode = jSONObject.optInt("version_code");
        dVar.mUrl = jSONObject.optString("download_url");
        dVar.mMd5 = jSONObject.optString("md5");
        dVar.mApiVersionMin = jSONObject.optInt("min_version");
        dVar.mApiVersionMax = jSONObject.optInt("max_version");
        dVar.f5261a = jSONObject.optString(DispatchConstants.SIGN);
        dVar.isRevert = jSONObject.optBoolean("is_revert");
        dVar.f5262b = new File(jSONObject.optString("plugin_file"));
        return dVar;
    }

    private static String b(String str) {
        if (str == null) {
            return "";
        }
        int iIndexOf = str.indexOf(".");
        return iIndexOf > 0 ? str.substring(0, iIndexOf) : str;
    }

    private static File f(Context context) {
        return new File(h(context), f5269a);
    }

    private static File g(Context context) {
        return new File(h(context), f5270b);
    }

    private static File h(Context context) {
        return new File(context.getDir("tt_pangle_bykv_file", 0), "pangle_com.byted.pangle");
    }

    private static boolean a(InputStream inputStream, File file) throws Throwable {
        if (inputStream == null || file == null) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                    a(inputStream);
                    a((Closeable) null);
                    return false;
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int i = inputStream.read(bArr);
                        if (i <= 0) {
                            a(inputStream);
                            a(fileOutputStream2);
                            return true;
                        }
                        fileOutputStream2.write(bArr, 0, i);
                    }
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    a(inputStream);
                    a(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable unused) {
        }
    }

    private static String c(File file) throws Throwable {
        FileReader fileReader;
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            fileReader = new FileReader(file);
            try {
                StringBuilder sb = new StringBuilder();
                char[] cArr = new char[1024];
                while (true) {
                    int i = fileReader.read(cArr);
                    if (i == -1) {
                        break;
                    }
                    sb.append(cArr, 0, i);
                }
                String string = sb.toString();
                try {
                    fileReader.close();
                } catch (IOException unused) {
                }
                return string;
            } catch (Exception unused2) {
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException unused3) {
                    }
                }
                return null;
            } catch (Throwable th) {
                th = th;
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        } catch (Exception unused5) {
            fileReader = null;
        } catch (Throwable th2) {
            th = th2;
            fileReader = null;
        }
    }

    private static void a(Closeable closeable) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    private static void a(Plugin plugin, TTPluginListener tTPluginListener) {
        Bundle bundle = new Bundle();
        Bundle bundleConfig = tTPluginListener.config();
        bundleConfig.putString(PluginConstants.KEY_PLUGIN_VERSION, a(plugin.getVersion()));
        bundle.putBundle(plugin.mPkgName, bundleConfig);
        bundle.putInt("action", 0);
        TTAdManager adManager = TTAdSdk.getAdManager();
        if (adManager != null) {
            adManager.getExtra(Bundle.class, bundle);
        }
    }

    private static String a(int i) {
        char[] charArray = String.valueOf(i).toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < charArray.length; i2++) {
            sb.append(charArray[i2]);
            if (i2 < charArray.length - 1) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(d dVar, TTPluginListener tTPluginListener) {
        if (dVar == null || dVar.f5262b == null) {
            com.bytedance.sdk.openadsdk.api.b.d.c("TTPluginManager", "plugin config is null");
            return false;
        }
        boolean zSyncInstallPlugin = Zeus.syncInstallPlugin(dVar.f5262b.getAbsolutePath());
        b(zSyncInstallPlugin, dVar.mPackageName, tTPluginListener);
        return zSyncInstallPlugin;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(boolean z, String str, TTPluginListener tTPluginListener) {
        Plugin plugin;
        StringBuilder sb = new StringBuilder();
        sb.append("Install dl plugin ");
        sb.append(str);
        sb.append(z ? " success" : " failed");
        sb.append(", need notify: ");
        sb.append(tTPluginListener != null);
        com.bytedance.sdk.openadsdk.api.b.d.a("TTPluginManager", sb.toString());
        Handler handler = e.get(str);
        if (tTPluginListener == null || handler == null) {
            return;
        }
        if (z && Zeus.loadPlugin(str) && (plugin = Zeus.getPlugin(str)) != null) {
            a(plugin, tTPluginListener);
            handler.removeCallbacksAndMessages(null);
            tTPluginListener.onPluginListener(1000, plugin.mClassLoader, plugin.mResources, null);
        } else {
            tTPluginListener.onPluginListener(1001, null, null, null);
        }
        d.remove(str);
        e.remove(str);
    }

    static final class b implements TTAdEvent {
        b() {
        }

        @Override // com.bytedance.sdk.openadsdk.TTAdEvent
        public void onEvent(int i, Bundle bundle) {
            if (i == 1) {
                d dVarA = f.a(bundle.getString("config"));
                if (dVarA == null || TextUtils.isEmpty(dVarA.mPackageName)) {
                    com.bytedance.sdk.openadsdk.api.b.d.c("TTPluginManager", "plugin update received with invalid config");
                    return;
                }
                boolean z = bundle.getBoolean("success");
                TTPluginListener tTPluginListener = (TTPluginListener) f.d.get(dVarA.mPackageName);
                if (z) {
                    com.bytedance.sdk.openadsdk.api.b.d.c("TTPluginManager", "plugin update received: " + dVarA.mPackageName);
                    if (!dVarA.isRevert) {
                        if (f.b(dVarA, tTPluginListener)) {
                            bundle.putBoolean("installed", true);
                            return;
                        }
                        return;
                    }
                    Zeus.unInstallPlugin(dVarA.mPackageName);
                    return;
                }
                if (tTPluginListener != null) {
                    tTPluginListener.onPluginListener(1001, null, null, null);
                }
                com.bytedance.sdk.openadsdk.api.b.d.c("TTPluginManager", "plugin update failed");
            }
        }
    }

    private static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final c f5279a = new c();
    }
}
