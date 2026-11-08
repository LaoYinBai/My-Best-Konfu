package com.ss.android.message;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import com.bytedance.push.utils.h;
import com.ss.android.pushmanager.setting.PushSetting;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NotifyIntentService extends IntentService {
    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
    }

    public NotifyIntentService() {
        super("NotifyIntentService");
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        try {
            if (Build.VERSION.SDK_INT < 18 || !PushSetting.getInstance().isUseStartForegroundNotification()) {
                return;
            }
            if (h.b() && h.a()) {
                return;
            }
            startForeground(1, new Notification.Builder(getApplicationContext()).setSmallIcon(com.ss.android.message.a.a.b(this, "status_icon")).build());
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        try {
            stopForeground(true);
        } catch (Throwable unused) {
        }
        super.onDestroy();
    }
}
