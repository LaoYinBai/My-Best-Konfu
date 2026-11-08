package com.bytedance.push.alive;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class AlvService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        a.a(getApplicationContext()).a();
    }
}
