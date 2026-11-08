package com.bytedance.pangle;

import android.app.Application;
import android.content.pm.ProviderInfo;
import android.text.TextUtils;
import com.android.server.SystemConfig;
import com.bytedance.pangle.ZeusParam;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.plugin.PluginManager;
import com.bytedance.pangle.provider.ContentProviderManager;
import com.bytedance.pangle.util.FieldUtils;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g {
    private static volatile g d;

    /* renamed from: a, reason: collision with root package name */
    boolean f4631a;

    /* renamed from: b, reason: collision with root package name */
    public ZeusParam f4632b;

    /* renamed from: c, reason: collision with root package name */
    public List<ZeusPluginStateListener> f4633c = Collections.emptyList();

    public static g a() {
        if (d == null) {
            synchronized (g.class) {
                if (d == null) {
                    d = new g();
                }
            }
        }
        return d;
    }

    final synchronized void a(Application application, ZeusParam zeusParam) {
        if (this.f4631a) {
            ZeusLogger.w(ZeusLogger.TAG_INIT, "ZeusManager zeus has been inited!");
            return;
        }
        if (application == null) {
            throw new IllegalArgumentException("context must be not null !!!");
        }
        if (zeusParam == null) {
            zeusParam = new ZeusParam.Builder().build();
            ZeusLogger.i(ZeusLogger.TAG_INIT, "ZeusManager init, use default ZeusParam");
        }
        this.f4632b = zeusParam;
        ZeusLogger.setDebug(this.f4632b.isDebug());
        ZeusLogger.i(ZeusLogger.TAG_INIT, "ZeusManager init, context = " + application + ", hParam = " + this.f4632b);
        if (!this.f4632b.isCloseDefaultReport()) {
            com.bytedance.pangle.helper.d.a(application, String.valueOf(zeusParam.getAppId()), zeusParam.getChannel(), String.valueOf(zeusParam.getDid().get()));
        }
        com.bytedance.pangle.b.b bVarA = com.bytedance.pangle.b.b.a();
        com.bytedance.pangle.b.a aVar = new com.bytedance.pangle.b.a() { // from class: com.bytedance.pangle.g.1
            @Override // com.bytedance.pangle.b.a
            public final void a(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
                com.bytedance.pangle.log.d.a(str, jSONObject, jSONObject2, jSONObject3);
            }
        };
        synchronized (bVarA.f4525a) {
            bVarA.f4525a.add(aVar);
        }
        if (this.f4632b.isEnable()) {
            b.a();
            if (com.bytedance.pangle.util.g.e()) {
                com.bytedance.pangle.helper.e.f4645a.execute(new Runnable() { // from class: com.bytedance.pangle.g.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            SystemConfig.getInstance();
                        } catch (Throwable unused) {
                        }
                    }
                });
            }
            if (com.bytedance.pangle.util.g.d()) {
                try {
                    FieldUtils.writeField(com.bytedance.pangle.helper.a.a(), "mHiddenApiWarningShown", Boolean.TRUE);
                    ZeusLogger.w(ZeusLogger.TAG_INIT, "ZeusManager disableApiWarningShownForAndroidP, true");
                } catch (Exception e) {
                    ZeusLogger.e(ZeusLogger.TAG_INIT, "disableApiWarningShownForAndroidP failed", e);
                }
            }
            b();
            ContentProviderManager.getInstance().initSystemContentProviderInfo();
            if (com.bytedance.pangle.helper.c.b(application)) {
                if (this.f4632b.autoFetch()) {
                    com.bytedance.pangle.download.f.a();
                    com.bytedance.pangle.download.f.b();
                }
                PluginManager.getInstance().installFromDownloadDir();
            }
            com.bytedance.pangle.receiver.b.a(application);
        }
        this.f4631a = true;
    }

    private static void b() {
        try {
            for (ProviderInfo providerInfo : Zeus.getAppApplication().getPackageManager().getPackageInfo(Zeus.getAppApplication().getPackageName(), 8).providers) {
                if (!TextUtils.isEmpty(providerInfo.authority)) {
                    if (providerInfo.authority.contains(Zeus.getAppApplication().getPackageName() + ZeusConstants.f4512a)) {
                        Zeus.getServerManagerHashMap().put((TextUtils.isEmpty(providerInfo.processName) || !providerInfo.processName.contains(":")) ? "main" : providerInfo.processName.split(":")[1], providerInfo);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
