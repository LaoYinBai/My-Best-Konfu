package com.xiaomi.push;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import com.xiaomi.push.mpcd.receivers.BroadcastActionsReceiver;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class dm {
    private static volatile Handler a;

    private static IntentFilter a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        intentFilter.addAction("android.intent.action.PACKAGE_DATA_CLEARED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("android.intent.action.PACKAGE_RESTARTED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme(com.umeng.message.common.a.u);
        return intentFilter;
    }

    /* renamed from: a, reason: collision with other method in class */
    private static ds m203a() {
        return new dn();
    }

    public static void a(Context context) {
        dt.a(context).m204a();
        try {
            com.xiaomi.channel.commonutils.logger.b.b("cde init handler thread:" + b());
            context.registerReceiver(new BroadcastActionsReceiver(m203a()), a());
        } catch (Throwable th) {
            com.xiaomi.channel.commonutils.logger.b.a(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Handler b() {
        if (a == null) {
            synchronized (dm.class) {
                if (a == null) {
                    HandlerThread handlerThread = new HandlerThread("cde-HandlerThread");
                    handlerThread.start();
                    a = new Handler(handlerThread.getLooper());
                }
            }
        }
        return a;
    }
}
