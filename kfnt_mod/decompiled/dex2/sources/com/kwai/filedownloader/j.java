package com.kwai.filedownloader;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class j {
    static int a = 10;
    static int b = 5;
    private final Executor c;
    private final Handler d;
    private final LinkedBlockingQueue<t> e;
    private final Object f;
    private final ArrayList<t> g;

    private static final class a {
        private static final j a = new j();
    }

    private static class b implements Handler.Callback {
        private b() {
        }

        private void a(ArrayList<t> arrayList) {
            Iterator<t> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
            arrayList.clear();
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                ((t) message.obj).b();
            } else if (message.what == 2) {
                a((ArrayList) message.obj);
                j.a().c();
            }
            return true;
        }
    }

    private j() {
        this.c = com.kwai.filedownloader.e.b.a(5, "BlockCompleted");
        this.f = new Object();
        this.g = new ArrayList<>();
        this.d = new Handler(Looper.getMainLooper(), new b());
        this.e = new LinkedBlockingQueue<>();
    }

    public static j a() {
        return a.a;
    }

    private void b(t tVar) {
        this.d.sendMessage(this.d.obtainMessage(1, tVar));
    }

    public static boolean b() {
        return a > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        int i;
        synchronized (this.f) {
            if (this.g.isEmpty()) {
                if (this.e.isEmpty()) {
                    return;
                }
                if (b()) {
                    i = a;
                    int iMin = Math.min(this.e.size(), b);
                    for (int i2 = 0; i2 < iMin; i2++) {
                        this.g.add(this.e.remove());
                    }
                } else {
                    this.e.drainTo(this.g);
                    i = 0;
                }
                this.d.sendMessageDelayed(this.d.obtainMessage(2, this.g), i);
            }
        }
    }

    private void c(t tVar) {
        synchronized (this.f) {
            this.e.offer(tVar);
        }
        c();
    }

    void a(t tVar) {
        a(tVar, false);
    }

    void a(final t tVar, boolean z) {
        if (tVar.c()) {
            tVar.b();
            return;
        }
        if (tVar.d()) {
            this.c.execute(new Runnable() { // from class: com.kwai.filedownloader.j.1
                @Override // java.lang.Runnable
                public void run() {
                    tVar.b();
                }
            });
            return;
        }
        if (!b() && !this.e.isEmpty()) {
            synchronized (this.f) {
                if (!this.e.isEmpty()) {
                    Iterator<t> it = this.e.iterator();
                    while (it.hasNext()) {
                        b(it.next());
                    }
                }
                this.e.clear();
            }
        }
        if (!b() || z) {
            b(tVar);
        } else {
            c(tVar);
        }
    }
}
