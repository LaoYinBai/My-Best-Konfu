package com.ss.android.message;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.bytedance.push.third.f;
import com.ss.android.pushmanager.a.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NotifyService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        com.bytedance.push.alive.b.a(getApplicationContext()).a();
        d.a().a(new Runnable() { // from class: com.ss.android.message.NotifyService.1
            @Override // java.lang.Runnable
            public void run() {
                NotifyService.this.a();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        try {
            f.a().onNotifyServiceCreate(getApplicationContext());
        } catch (Throwable unused) {
        }
        com.bytedance.common.push.utility.a.b("NotifyService", "do onCreate end");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        try {
            f.a().onNotifyDestroy();
        } catch (Exception e) {
            try {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // android.app.Service
    public int onStartCommand(final Intent intent, int i, int i2) {
        int iOnStartCommand = super.onStartCommand(intent, i, i2);
        if (com.bytedance.common.push.utility.a.b()) {
            com.bytedance.common.push.utility.a.b("PushService NotifyService", "onStartCommand");
        }
        d.a().a(new Runnable() { // from class: com.ss.android.message.NotifyService.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    com.bytedance.common.push.utility.a.b("NotifyService", "do handleIntent start");
                    try {
                        f.a().onNotifyServiceStart(intent);
                    } catch (Throwable unused) {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        if (((a.b) com.ss.android.b.a.b.a(a.b.class)).g()) {
            return 2;
        }
        return iOnStartCommand;
    }
}
