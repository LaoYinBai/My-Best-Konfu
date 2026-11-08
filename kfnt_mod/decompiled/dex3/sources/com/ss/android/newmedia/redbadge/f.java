package com.ss.android.newmedia.redbadge;

import android.content.Context;
import android.content.Intent;
import com.ss.android.message.b;
import com.ss.android.pushmanager.IMessageContext;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class f implements com.ss.android.message.b {
    @Override // com.ss.android.message.b
    public void a(Context context, Map<String, String> map) {
    }

    @Override // com.ss.android.message.b
    public void a(b.a aVar) {
    }

    @Override // com.ss.android.message.IPushLifeCycleListener
    public void onNotifyDestroy() {
    }

    @Override // com.ss.android.message.IPushLifeCycleListener
    public void onNotifyServiceCreate(Context context) {
    }

    @Override // com.ss.android.message.IPushLifeCycleListener
    public void onNotifyServiceStart(Intent intent) {
    }

    @Override // com.ss.android.message.b
    public void a(Context context, IMessageContext iMessageContext) {
        if (com.ss.android.message.a.b.c(context) || !com.ss.android.message.a.b.e(context)) {
            return;
        }
        try {
            c.a(iMessageContext);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
