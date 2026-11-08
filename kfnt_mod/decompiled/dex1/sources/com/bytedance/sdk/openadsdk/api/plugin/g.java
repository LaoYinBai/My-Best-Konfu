package com.bytedance.sdk.openadsdk.api.plugin;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.AdConfig;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTInitializer;
import com.bytedance.sdk.openadsdk.api.plugin.f;
import dalvik.system.DexClassLoader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g implements TTInitializer {

    /* renamed from: b, reason: collision with root package name */
    private volatile TTInitializer f5282b;

    /* renamed from: c, reason: collision with root package name */
    private static final Bundle f5281c = new Bundle();

    /* renamed from: a, reason: collision with root package name */
    public static ScheduledExecutorService f5280a = Executors.newSingleThreadScheduledExecutor(new a());

    public static void a(String str, Bundle bundle) {
        if (TextUtils.isEmpty(str) || bundle == null) {
            return;
        }
        f5281c.putBundle(str, bundle);
    }

    @Override // com.bytedance.sdk.openadsdk.TTInitializer
    public void init(Context context, AdConfig adConfig, TTAdSdk.InitCallback initCallback) {
        if (Build.VERSION.SDK_INT < 21) {
            initCallback.fail(4201, "Only support >= 5.0");
            return;
        }
        f.a(context);
        if (this.f5282b != null) {
            this.f5282b.init(context, adConfig, initCallback);
        } else {
            a(context, adConfig, initCallback);
        }
    }

    @Override // com.bytedance.sdk.openadsdk.TTInitializer
    public TTAdManager init(Context context, AdConfig adConfig) {
        throw new RuntimeException("Please use init(Context context, AdConfig config, TTAdSdk.InitCallback callback)!");
    }

    @Override // com.bytedance.sdk.openadsdk.TTInitializer
    public boolean isInitSuccess() {
        if (this.f5282b != null) {
            return this.f5282b.isInitSuccess();
        }
        return false;
    }

    @Override // com.bytedance.sdk.openadsdk.TTInitializer
    public TTAdManager getAdManager() {
        return com.bytedance.sdk.openadsdk.api.plugin.a.f5192a;
    }

    public void a(final Context context, final AdConfig adConfig, final TTAdSdk.InitCallback initCallback) {
        f5280a.execute(new Runnable() { // from class: com.bytedance.sdk.openadsdk.api.plugin.g.1
            @Override // java.lang.Runnable
            public void run() {
                TTInitializer tTInitializerA = g.this.a(context, adConfig);
                if (tTInitializerA != null) {
                    tTInitializerA.init(context, adConfig, initCallback);
                    com.bytedance.sdk.openadsdk.api.plugin.a.f5192a.a(g.this.f5282b.getAdManager());
                    e.a();
                    return;
                }
                initCallback.fail(4201, "No initializer");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TTInitializer a(Context context, AdConfig adConfig) {
        if (this.f5282b == null) {
            synchronized (this) {
                if (this.f5282b == null) {
                    e.a(adConfig);
                    com.bytedance.sdk.openadsdk.api.b.d.a("TTPluginManager", "Create initializer");
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    this.f5282b = a(context);
                    long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.putOpt("duration", Long.valueOf(jCurrentTimeMillis2));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    adConfig.setExtra("plugin", jSONObject);
                }
            }
        }
        return this.f5282b;
    }

    private static TTInitializer a(Context context) {
        DexClassLoader dexClassLoaderA;
        try {
            dexClassLoaderA = f.a(context).a();
        } catch (Throwable th) {
            e.a(6, th.getMessage(), 0L);
            com.bytedance.sdk.openadsdk.api.b.d.d("TTPluginManager", "Create initializer failed: " + th);
        }
        if (dexClassLoaderA == null) {
            e.a(6, "Load plugin failed", 0L);
            com.bytedance.sdk.openadsdk.api.b.d.d("TTPluginManager", "Load plugin failed");
            return null;
        }
        Class<?> clsLoadClass = dexClassLoaderA.loadClass(TTAdSdk.INITIALIZER_CLASS_NAME);
        Bundle bundle = new Bundle();
        bundle.putSerializable(PluginConstants.KEY_PL_UPDATE_LISTENER, new f.b());
        bundle.putBundle(PluginConstants.KEY_PL_CONFIG_INFO, f5281c);
        TTInitializer tTInitializer = (TTInitializer) clsLoadClass.getDeclaredMethod("getInstance", Bundle.class).invoke(null, bundle);
        com.bytedance.sdk.openadsdk.api.b.d.a("TTPluginManager", "Create initializer success");
        return tTInitializer;
    }

    public static class a implements ThreadFactory {

        /* renamed from: a, reason: collision with root package name */
        private final ThreadGroup f5286a;

        /* renamed from: b, reason: collision with root package name */
        private final AtomicInteger f5287b;

        /* renamed from: c, reason: collision with root package name */
        private final String f5288c;

        a() {
            this.f5287b = new AtomicInteger(1);
            this.f5286a = new ThreadGroup("tt_pangle_group_pl_init");
            this.f5288c = "tt_pangle_thread_pl_init";
        }

        a(String str) {
            this.f5287b = new AtomicInteger(1);
            this.f5286a = new ThreadGroup("tt_pangle_group_pl_init");
            this.f5288c = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.f5286a, runnable, this.f5288c + this.f5287b.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 10) {
                thread.setPriority(10);
            }
            return thread;
        }
    }
}
