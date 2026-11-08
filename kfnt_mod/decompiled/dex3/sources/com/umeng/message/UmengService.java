package com.umeng.message;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.ss.android.b.a.b;
import com.ss.android.pushmanager.a.a;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UmengService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        int iOnStartCommand = super.onStartCommand(intent, i, i2);
        if (((a.b) b.a(a.b.class)).g()) {
            return 2;
        }
        return iOnStartCommand;
    }
}
