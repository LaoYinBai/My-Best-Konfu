package com.alibaba.sdk.android.push;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.ss.android.b.a.b;
import com.ss.android.message.NotifyService;
import com.ss.android.pushmanager.a.a;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AliyunPushIntentService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            Intent intent2 = new Intent(this, (Class<?>) NotifyService.class);
            intent2.setAction("com.ss.android.message.action.PUSH_SERVICE");
            intent2.setPackage(getPackageName());
            startService(intent2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        stopSelf();
        int iOnStartCommand = super.onStartCommand(intent, i, i2);
        if (b.a(a.b.class).g()) {
            return 2;
        }
        return iOnStartCommand;
    }
}
