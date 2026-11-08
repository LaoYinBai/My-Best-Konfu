package com.umeng.message;

import android.content.Context;
import android.content.Intent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.service.UMJobIntentService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class UmengMessageService extends UMJobIntentService {
    private static final String a = "UmengMessageService";

    public abstract void onMessage(Context context, Intent intent);

    @Override // com.umeng.message.service.UMJobIntentService, com.umeng.message.service.JobIntentService
    protected void a(Intent intent) {
        try {
            onMessage(this, intent);
            String stringExtra = intent.getStringExtra("body");
            UMLog uMLog = UMConfigure.umDebugLog;
            UMLog.mutlInfo(a, 2, "message:" + stringExtra);
        } catch (Throwable unused) {
        }
    }
}
