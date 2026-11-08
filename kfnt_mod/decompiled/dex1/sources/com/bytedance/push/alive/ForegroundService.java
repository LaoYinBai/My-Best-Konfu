package com.bytedance.push.alive;

import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import com.bytedance.push.settings.AliveOnlineSettings;
import com.bytedance.push.settings.SettingsManager;
import com.ss.android.message.NotifyIntentService;
import com.ss.android.pushmanager.setting.PushSetting;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ForegroundService extends AlvService {

    /* renamed from: a, reason: collision with root package name */
    private f f4727a;

    @Override // com.bytedance.push.alive.AlvService, android.app.Service
    public void onCreate() {
        super.onCreate();
        com.ss.android.message.d.a().a(new Runnable() { // from class: com.bytedance.push.alive.ForegroundService.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ForegroundService.this.a();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.f4727a = new f(this, com.ss.android.message.d.a().c());
        this.f4727a.a(getApplicationContext());
        b();
        if (com.bytedance.common.push.utility.a.b()) {
            com.bytedance.common.push.utility.a.b("PushService NotifyService", "onCreate");
        }
    }

    private void b() {
        try {
            if (PushSetting.getInstance().isUseStartForegroundNotification()) {
                if (Build.VERSION.SDK_INT < 18) {
                    startForeground(-2048, new Notification());
                } else if (Build.VERSION.SDK_INT < 24) {
                    try {
                        if (!(com.bytedance.push.utils.h.b() && com.bytedance.push.utils.h.a())) {
                            Notification notificationBuild = new Notification.Builder(getApplicationContext()).setSmallIcon(com.ss.android.message.a.a.b(this, "status_icon")).build();
                            startService(new Intent(this, (Class<?>) NotifyIntentService.class));
                            startForeground(1, notificationBuild);
                        }
                    } catch (Throwable unused) {
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (com.bytedance.common.push.utility.a.b()) {
            com.bytedance.common.push.utility.a.b("PushService NotifyService", "onStartCommand");
        }
        return ((AliveOnlineSettings) SettingsManager.obtain(com.ss.android.message.a.a(), AliveOnlineSettings.class)).j() ? 1 : 2;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        try {
            this.f4727a.c(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
