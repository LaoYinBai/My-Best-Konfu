package com.bytedance.push;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.bytedance.push.legacy.MessageContext;
import com.bytedance.push.settings.AliveOnlineSettings;
import com.bytedance.push.settings.SettingsManager;
import com.bytedance.push.third.PushManager;
import com.ss.android.pushmanager.setting.PushSetting;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g implements com.bytedance.push.c.h {

    /* renamed from: a, reason: collision with root package name */
    private d f4807a;

    /* renamed from: b, reason: collision with root package name */
    private AtomicBoolean f4808b = new AtomicBoolean(false);

    /* renamed from: c, reason: collision with root package name */
    private AtomicBoolean f4809c = new AtomicBoolean(false);

    @Override // com.bytedance.push.c.h
    public void a(d dVar) {
        if (this.f4808b.getAndSet(true)) {
            return;
        }
        this.f4807a = dVar;
        com.bytedance.push.utils.e.a(dVar.f);
        if (!TextUtils.isEmpty(dVar.n)) {
            com.ss.android.pushmanager.a.b(dVar.n);
        }
        com.ss.android.message.a.b.b(this.f4807a.h);
        com.ss.android.message.a.a(this.f4807a.f4770a);
        com.bytedance.push.g.a aVar = new com.bytedance.push.g.a(this.f4807a);
        i.j().a(dVar, aVar);
        MessageContext messageContext = new MessageContext(this.f4807a);
        com.bytedance.push.legacy.a.a(dVar, aVar, messageContext);
        com.bytedance.push.third.f.a().a(dVar.k);
        com.bytedance.push.third.f.a().a(this.f4807a.f4770a, messageContext);
        if (TextUtils.equals(dVar.h, this.f4807a.f4770a.getPackageName())) {
            if (dVar.w) {
                a(dVar.f4770a);
            }
        } else if (dVar.h.endsWith(":pushservice")) {
            a().c().a();
            com.bytedance.push.alive.b.a(this.f4807a.f4770a).a();
        } else if (dVar.h.endsWith(":push")) {
            com.bytedance.push.alive.b.a(this.f4807a.f4770a).a();
        }
        i.o().a();
    }

    private void a(Context context) {
        AliveOnlineSettings aliveOnlineSettings = (AliveOnlineSettings) SettingsManager.obtain(context, AliveOnlineSettings.class);
        aliveOnlineSettings.h(false);
        aliveOnlineSettings.c(false);
        aliveOnlineSettings.e(true);
        aliveOnlineSettings.b(false);
    }

    @Override // com.bytedance.push.c.h
    public void a(Map<String, String> map, boolean z) {
        Application application = this.f4807a.f4770a;
        if (com.ss.android.message.a.b.c(application)) {
            com.bytedance.push.utils.e.d("Start", "ssidsMap = [" + map + "] forceUpdate = " + z);
            if (a(map)) {
                com.bytedance.push.c.j jVarA = a();
                if (this.f4809c.compareAndSet(false, true)) {
                    com.bytedance.push.alive.b.a(this.f4807a.f4770a).b();
                    b(application);
                    a(application, jVarA);
                    new com.bytedance.push.j.b(jVarA, this.f4807a.x).a();
                }
                jVarA.i().b();
                jVarA.c().a(z);
                com.bytedance.push.third.f.a().a(application, map);
            }
        }
    }

    @Override // com.bytedance.push.c.h
    public void a(Context context, JSONObject jSONObject) {
        new com.bytedance.push.h.d(context, jSONObject, this.f4807a.w).run();
    }

    private void a(final Context context, final com.bytedance.push.c.j jVar) {
        jVar.b().a(context, this.f4807a.j);
        com.ss.android.message.d.a().a(new Runnable() { // from class: com.bytedance.push.g.1
            @Override // java.lang.Runnable
            public void run() {
                jVar.b().a(context);
            }
        }, TimeUnit.SECONDS.toMillis(15L));
    }

    private void b(final Context context) {
        if (com.bytedance.push.utils.e.a() && !a("BDPush", this.f4807a.f4770a)) {
            throw new IllegalArgumentException("configuration error，please filter \"BDPush\" in logcat to correct the error");
        }
        com.ss.android.message.d.a().a(new Runnable() { // from class: com.bytedance.push.g.2
            @Override // java.lang.Runnable
            public void run() {
                com.bytedance.push.d.c.a(context);
                g.this.c(context);
            }
        }, TimeUnit.SECONDS.toMillis(15L));
    }

    private boolean a(String str, Context context) {
        boolean zCheckThirdPushConfig = PushManager.inst().checkThirdPushConfig(str, context);
        if (zCheckThirdPushConfig) {
            com.bytedance.push.utils.e.c(str, "configuration correct");
        } else {
            com.bytedance.push.utils.e.b(str, "configuration error!!!");
        }
        return zCheckThirdPushConfig;
    }

    public com.bytedance.push.c.j a() {
        return i.j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context) {
        try {
            String pushDaemonMonitorResult = PushSetting.getInstance().getPushDaemonMonitorResult();
            if (com.bytedance.common.push.utility.i.a(pushDaemonMonitorResult)) {
                return;
            }
            i.m().a(context, "ss_push", new JSONObject(pushDaemonMonitorResult));
            PushSetting.getInstance().setPushDaemonMonitorResult("");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private boolean a(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return false;
        }
        String str = map.get("clientudid");
        String str2 = map.get(com.bytedance.b.a.c.h.x);
        String str3 = map.get("install_id");
        if (com.bytedance.common.push.utility.i.a(str) || com.bytedance.common.push.utility.i.a(str2) || com.bytedance.common.push.utility.i.a(str3)) {
            return false;
        }
        PushSetting.getInstance().saveSSIDs(map);
        return true;
    }
}
