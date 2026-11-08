package com.kwad.jni;

import androidx.annotation.Keep;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.atomic.AtomicReference;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DestructorThread {
    private static final b sDestructorStack = new b();
    private static final ReferenceQueue sReferenceQueue = new ReferenceQueue();
    private static final a sDestructorList = new a();
    private static final Thread sThread = new Thread("HybridData DestructorThread") { // from class: com.kwad.jni.DestructorThread.1
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    Destructor destructor = (Destructor) DestructorThread.sReferenceQueue.remove();
                    destructor.destruct();
                    if (destructor.previous == null) {
                        DestructorThread.sDestructorStack.a();
                    }
                    a.c(destructor);
                } catch (InterruptedException unused) {
                }
            }
        }
    };

    @Keep
    public static abstract class Destructor extends PhantomReference<Object> {
        private Destructor next;
        private Destructor previous;

        private Destructor() {
            super(null, DestructorThread.sReferenceQueue);
        }

        public Destructor(Object obj) {
            super(obj, DestructorThread.sReferenceQueue);
            DestructorThread.sDestructorStack.a(this);
        }

        protected abstract void destruct();
    }

    private static class a {
        private final Destructor a;

        public a() {
            this.a = new c();
            this.a.next = new c();
            this.a.next.previous = this.a;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void c(Destructor destructor) {
            destructor.next.previous = destructor.previous;
            destructor.previous.next = destructor.next;
        }

        public void a(Destructor destructor) {
            destructor.next = this.a.next;
            this.a.next = destructor;
            destructor.next.previous = destructor;
            destructor.previous = this.a;
        }
    }

    private static class b {
        private final AtomicReference<Destructor> a;

        private b() {
            this.a = new AtomicReference<>();
        }

        public void a() {
            Destructor andSet = this.a.getAndSet(null);
            while (andSet != null) {
                Destructor destructor = andSet.next;
                DestructorThread.sDestructorList.a(andSet);
                andSet = destructor;
            }
        }

        public void a(Destructor destructor) {
            Destructor destructor2;
            do {
                destructor2 = this.a.get();
                destructor.next = destructor2;
            } while (!this.a.compareAndSet(destructor2, destructor));
        }
    }

    private static class c extends Destructor {
        private c() {
            super();
        }

        @Override // com.kwad.jni.DestructorThread.Destructor
        protected void destruct() {
            throw new IllegalStateException("Cannot destroy Terminus Destructor.");
        }
    }

    static {
        sThread.start();
    }
}
