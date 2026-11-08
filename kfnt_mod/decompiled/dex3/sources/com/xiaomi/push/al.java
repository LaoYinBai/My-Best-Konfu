package com.xiaomi.push;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class al {
    private int a;

    /* renamed from: a, reason: collision with other field name */
    private Handler f112a;

    /* renamed from: a, reason: collision with other field name */
    private a f113a;

    /* renamed from: a, reason: collision with other field name */
    private volatile b f114a;

    /* renamed from: a, reason: collision with other field name */
    private volatile boolean f115a;
    private final boolean b;

    private class a extends Thread {

        /* renamed from: a, reason: collision with other field name */
        private final LinkedBlockingQueue<b> f116a;

        public a() {
            super("PackageProcessor");
            this.f116a = new LinkedBlockingQueue<>();
        }

        private void a(int i, b bVar) {
            try {
                al.this.f112a.sendMessage(al.this.f112a.obtainMessage(i, bVar));
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.a(e);
            }
        }

        public void a(b bVar) {
            try {
                this.f116a.add(bVar);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws InterruptedException {
            long j = al.this.a > 0 ? al.this.a : Long.MAX_VALUE;
            while (!al.this.f115a) {
                try {
                    b bVarPoll = this.f116a.poll(j, TimeUnit.SECONDS);
                    al.this.f114a = bVarPoll;
                    if (bVarPoll != null) {
                        a(0, bVarPoll);
                        bVarPoll.b();
                        a(1, bVarPoll);
                    } else if (al.this.a > 0) {
                        al.this.a();
                    }
                } catch (InterruptedException e) {
                    com.xiaomi.channel.commonutils.logger.b.a(e);
                }
            }
        }
    }

    public static abstract class b {
        public void a() {
        }

        public abstract void b();

        /* renamed from: c */
        public void mo197c() {
        }
    }

    public al() {
        this(false);
    }

    public al(boolean z) {
        this(z, 0);
    }

    public al(boolean z, int i) {
        this.f112a = null;
        this.f115a = false;
        this.a = 0;
        this.f112a = new am(this, Looper.getMainLooper());
        this.b = z;
        this.a = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a() {
        this.f113a = null;
        this.f115a = true;
    }

    public synchronized void a(b bVar) {
        if (this.f113a == null) {
            this.f113a = new a();
            this.f113a.setDaemon(this.b);
            this.f115a = false;
            this.f113a.start();
        }
        this.f113a.a(bVar);
    }

    public void a(b bVar, long j) {
        this.f112a.postDelayed(new an(this, bVar), j);
    }
}
