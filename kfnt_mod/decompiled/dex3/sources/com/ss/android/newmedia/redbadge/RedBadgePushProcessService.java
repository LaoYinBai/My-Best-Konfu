package com.ss.android.newmedia.redbadge;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import com.ss.android.pushmanager.IMessageContext;
import com.ss.android.pushmanager.a.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RedBadgePushProcessService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        final Bundle extras = intent != null ? intent.getExtras() : null;
        com.ss.android.message.d.a().a(new Runnable() { // from class: com.ss.android.newmedia.redbadge.RedBadgePushProcessService.1
            @Override // java.lang.Runnable
            public void run() {
                if (extras == null) {
                    return;
                }
                IMessageContext iMessageContextA = com.ss.android.pushmanager.a.a.b().a();
                if (extras.getBoolean("app_entrance")) {
                    if (com.bytedance.common.push.utility.a.b()) {
                        com.bytedance.common.push.utility.a.b("RedbadgeService", "BUNDLE_APP_ENTRANCE");
                    }
                    c.a(iMessageContextA).a();
                } else if (extras.getBoolean("app_exit")) {
                    if (com.bytedance.common.push.utility.a.b()) {
                        com.bytedance.common.push.utility.a.b("RedbadgeService", "BUNDLE_APP_EXIT");
                    }
                    c.a(iMessageContextA).b();
                }
            }
        });
        int iOnStartCommand = super.onStartCommand(intent, i, i2);
        a.b bVarB = com.ss.android.pushmanager.a.a.b();
        if (bVarB == null || !bVarB.g()) {
            return iOnStartCommand;
        }
        return 2;
    }
}
