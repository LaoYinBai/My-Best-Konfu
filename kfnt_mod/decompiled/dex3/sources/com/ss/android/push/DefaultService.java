package com.ss.android.push;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DefaultService extends IntentService {
    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
    }

    public DefaultService() {
        super("DefaultService");
    }

    @Override // android.app.IntentService, android.app.Service
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }
}
