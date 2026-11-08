package com.vivo.push.sdk.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.vivo.push.sdk.a;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.n;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CommandService extends Service {
    @Override // android.app.Service
    public void onCreate() {
        n.c("CommandService", getClass().getSimpleName() + " -- oncreate " + getPackageName());
        super.onCreate();
        a.a().a(ContextDelegate.getContext(getApplicationContext()));
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        n.c("CommandService", getClass().getSimpleName() + " -- onStartCommand " + getPackageName());
        if (intent == null) {
            return 2;
        }
        if (!a(intent.getAction())) {
            n.a("CommandService", getPackageName() + " receive invalid action " + intent.getAction());
            return 2;
        }
        try {
            a.a().a(intent);
        } catch (Exception e) {
            n.a("CommandService", "onStartCommand -- error", e);
        }
        return 2;
    }

    protected boolean a(String str) {
        return "com.vivo.pushservice.action.RECEIVE".equals(str);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        n.c("CommandService", getClass().getSimpleName() + " -- onDestroy " + getPackageName());
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        n.c("CommandService", "onBind initSuc: ");
        return null;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        n.c("CommandService", "onUnbind");
        return super.onUnbind(intent);
    }
}
