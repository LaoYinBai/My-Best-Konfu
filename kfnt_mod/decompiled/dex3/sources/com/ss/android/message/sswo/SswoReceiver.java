package com.ss.android.message.sswo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.bytedance.common.push.utility.b.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SswoReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, final Intent intent) {
        b.a(new Runnable() { // from class: com.ss.android.message.sswo.SswoReceiver.1
            @Override // java.lang.Runnable
            public void run() {
                SswoReceiver.this.a(intent, context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Intent intent, Context context) {
        try {
            String action = intent.getAction();
            if ("android.intent.action.SCREEN_OFF".equals(action)) {
                a.a(context).c();
            } else if ("android.intent.action.USER_PRESENT".equals(action) || "android.intent.action.SCREEN_ON".equals(action)) {
                a.a(context).d();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
