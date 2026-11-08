package com.vivo.push;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.umeng.message.proguard.l;
import com.vivo.push.util.n;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class i {
    protected Context a;
    protected Handler b;
    private final Object c = new Object();

    public abstract void b(Message message);

    public i() {
        HandlerThread handlerThread = new HandlerThread(getClass().getSimpleName(), 1);
        handlerThread.start();
        this.b = new a(handlerThread.getLooper());
    }

    public final void a(Context context) {
        this.a = context;
    }

    public final void a(Message message) {
        synchronized (this.c) {
            if (this.b == null) {
                String str = "Dead worker dropping a message: " + message.what;
                n.e(getClass().getSimpleName(), str + " (Thread " + Thread.currentThread().getId() + l.t);
            } else {
                this.b.sendMessage(message);
            }
        }
    }

    class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            i.this.b(message);
        }
    }
}
