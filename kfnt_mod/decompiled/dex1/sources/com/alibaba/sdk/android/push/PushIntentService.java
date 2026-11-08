package com.alibaba.sdk.android.push;

import android.app.IntentService;
import android.content.Intent;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PushIntentService extends IntentService {
    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
    }

    public PushIntentService() {
        this("MPS:PushIntentService");
    }

    public PushIntentService(String str) {
        super(str);
    }
}
