package com.kwai.filedownloader;

import com.kwai.filedownloader.x;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class q {
    private final b a = new b();

    private static class a {
        private static final q a = new q();

        static {
            com.kwai.filedownloader.message.e.a().a(new aa());
        }
    }

    private static class b {
        private ThreadPoolExecutor a;
        private LinkedBlockingQueue<Runnable> b;

        b() {
            a();
        }

        private void a() {
            this.b = new LinkedBlockingQueue<>();
            this.a = com.kwai.filedownloader.e.b.a(3, this.b, "LauncherTask");
        }

        public void a(x.b bVar) {
            this.a.execute(new c(bVar));
        }

        public void b(x.b bVar) {
            this.b.remove(bVar);
        }
    }

    private static class c implements Runnable {
        private final x.b a;
        private boolean b = false;

        c(x.b bVar) {
            this.a = bVar;
        }

        public boolean equals(Object obj) {
            return super.equals(obj) || obj == this.a;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.b) {
                return;
            }
            this.a.p();
        }
    }

    q() {
    }

    public static q a() {
        return a.a;
    }

    synchronized void a(x.b bVar) {
        this.a.a(bVar);
    }

    synchronized void b(x.b bVar) {
        this.a.b(bVar);
    }
}
