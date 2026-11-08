package com.taobao.accs.net;

import anet.channel.Session;
import anet.channel.heartbeat.IHeartbeat;
import anet.channel.thread.ThreadPoolExecutorFactory;
import com.taobao.accs.utl.ALog;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class s implements IHeartbeat, Runnable {
    private static final String a = "s";
    private Session b;
    private long c = 45000;
    private volatile int d = 1;
    private Future e;

    void a(int i) {
        if (this.d != i && this.d + i > 1) {
            ALog.i(a, "reset state, last state: " + this.d + " current state: " + i, new Object[0]);
            this.d = i;
            this.c = this.d < 2 ? 45000L : 270000L;
            reSchedule();
            return;
        }
        this.d = i;
    }

    public void start(Session session) {
        if (session == null) {
            throw new NullPointerException("session is null");
        }
        this.b = session;
        this.c = this.d < 2 ? 45000L : 270000L;
        ALog.i(a, "heartbeat start", session.mSeq, "session", session, "interval", Long.valueOf(this.c));
        a(this.c);
    }

    public void stop() {
        ALog.i(a, "heartbeat stop", this.b.mSeq, "session", this.b);
        if (this.b == null || this.e == null) {
            return;
        }
        this.e.cancel(true);
    }

    public void reSchedule() {
        ALog.e(a, "reSchedule ", new Object[0]);
        a(this.c);
    }

    @Override // java.lang.Runnable
    public void run() {
        ALog.e(a, "ping ", new Object[0]);
        this.b.ping(true);
    }

    private synchronized void a(long j) {
        try {
            ALog.i(a, "submit ping current delay: " + j, new Object[0]);
            if (this.e != null) {
                this.e.cancel(false);
                this.e = null;
            }
            this.e = ThreadPoolExecutorFactory.submitScheduledTask(this, j + 50, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            ALog.e(a, "Submit heartbeat task failed.", this.b.mSeq, e);
        }
    }
}
