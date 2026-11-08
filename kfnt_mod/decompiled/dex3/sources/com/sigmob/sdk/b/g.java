package com.sigmob.sdk.b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.io.File;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class g {
    private final String b;
    private volatile e c;
    private final b e;
    private final c f;
    private final AtomicInteger a = new AtomicInteger(0);
    private final List<b> d = new CopyOnWriteArrayList();

    private static final class a extends Handler implements b {
        private final String a;
        private final List<b> b;

        public a(String str, List<b> list) {
            super(Looper.getMainLooper());
            this.a = str;
            this.b = list;
        }

        @Override // com.sigmob.sdk.b.b
        public void a(File file, String str, int i) {
            Message messageObtainMessage = obtainMessage();
            messageObtainMessage.arg1 = i;
            messageObtainMessage.obj = file;
            sendMessage(messageObtainMessage);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Iterator<b> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().a((File) message.obj, this.a, message.arg1);
            }
        }
    }

    public g(String str, c cVar) {
        this.b = (String) l.a(str);
        this.f = (c) l.a(cVar);
        this.e = new a(str, this.d);
    }

    private synchronized void b() {
        this.c = this.c == null ? d() : this.c;
    }

    private synchronized void c() {
        if (this.a.decrementAndGet() <= 0) {
            this.c.a();
            this.c = null;
        }
    }

    private e d() {
        e eVar = new e(new h(this.b, this.f.d, this.f.e), new com.sigmob.sdk.b.a.b(this.f.a(this.b), this.f.c));
        eVar.a(this.e);
        return eVar;
    }

    public int a() {
        return this.a.get();
    }

    public void a(d dVar, Socket socket) {
        b();
        try {
            this.a.incrementAndGet();
            this.c.a(dVar, socket);
        } finally {
            c();
        }
    }
}
