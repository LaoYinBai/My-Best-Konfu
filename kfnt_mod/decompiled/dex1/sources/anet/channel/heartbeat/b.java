package anet.channel.heartbeat;

import anet.channel.Session;
import anet.channel.thread.ThreadPoolExecutorFactory;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b implements IHeartbeat, Runnable {

    /* renamed from: a, reason: collision with root package name */
    private Session f452a = null;

    /* renamed from: b, reason: collision with root package name */
    private volatile boolean f453b = false;

    /* renamed from: c, reason: collision with root package name */
    private volatile long f454c = System.currentTimeMillis();

    @Override // anet.channel.heartbeat.IHeartbeat
    public void start(Session session) {
        if (session == null) {
            throw new NullPointerException("session is null");
        }
        this.f452a = session;
        this.f454c = System.currentTimeMillis() + 45000;
        ThreadPoolExecutorFactory.submitScheduledTask(this, 45000L, TimeUnit.MILLISECONDS);
    }

    @Override // anet.channel.heartbeat.IHeartbeat
    public void stop() {
        this.f453b = true;
    }

    @Override // anet.channel.heartbeat.IHeartbeat
    public void reSchedule() {
        this.f454c = System.currentTimeMillis() + 45000;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f453b) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis < this.f454c - 1000) {
            ThreadPoolExecutorFactory.submitScheduledTask(this, this.f454c - jCurrentTimeMillis, TimeUnit.MILLISECONDS);
        } else {
            this.f452a.close(false);
        }
    }
}
