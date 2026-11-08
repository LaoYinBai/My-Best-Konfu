package com.alibaba.sdk.android.push;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.ss.android.b.a.b;
import com.ss.android.pushmanager.a.a;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MsgService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        int iOnStartCommand = super.onStartCommand(intent, i, i2);
        if (b.a(a.b.class).g()) {
            return 2;
        }
        return iOnStartCommand;
    }
}
