package com.vivo.push;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.vivo.push.util.n;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class f {
    private static final Handler a = new Handler(Looper.getMainLooper());
    private static final HandlerThread b;
    private static final Handler c;

    static {
        HandlerThread handlerThread = new HandlerThread("push_client_thread");
        b = handlerThread;
        handlerThread.start();
        c = new Handler(b.getLooper()) { // from class: com.vivo.push.f.1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                Object obj = message.obj;
                if (obj instanceof e) {
                    e eVar = (e) obj;
                    n.c("PushClientThread", "PushClientThread-handleMessage, task = " + eVar);
                    eVar.run();
                }
            }
        };
    }

    public static void a(e eVar) {
        if (eVar == null) {
            n.a("PushClientThread", "client thread error, task is null!");
            return;
        }
        int i = eVar.b;
        Message message = new Message();
        message.what = i;
        message.obj = eVar;
        c.sendMessageDelayed(message, 0L);
    }

    public static void a(Runnable runnable) {
        c.removeCallbacks(runnable);
        c.postDelayed(runnable, 15000L);
    }

    public static void b(Runnable runnable) {
        a.post(runnable);
    }
}
