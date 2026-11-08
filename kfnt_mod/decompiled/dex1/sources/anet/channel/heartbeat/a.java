package anet.channel.heartbeat;

import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.Session;
import anet.channel.thread.ThreadPoolExecutorFactory;
import anet.channel.util.ALog;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class a implements IHeartbeat, Runnable {

    /* renamed from: a, reason: collision with root package name */
    private Session f449a;

    /* renamed from: b, reason: collision with root package name */
    private volatile long f450b = 0;

    /* renamed from: c, reason: collision with root package name */
    private volatile boolean f451c = false;
    private long d = 0;

    a() {
    }

    @Override // anet.channel.heartbeat.IHeartbeat
    public void start(Session session) {
        if (session == null) {
            throw new NullPointerException("session is null");
        }
        this.f449a = session;
        this.d = session.getConnStrategy().getHeartbeat();
        if (this.d <= 0) {
            this.d = 45000L;
        }
        ALog.i("awcn.DefaultHeartbeatImpl", "heartbeat start", session.p, "session", session, "interval", Long.valueOf(this.d));
        a(this.d);
    }

    @Override // anet.channel.heartbeat.IHeartbeat
    public void stop() {
        if (this.f449a == null) {
            return;
        }
        ALog.i("awcn.DefaultHeartbeatImpl", "heartbeat stop", this.f449a.p, "session", this.f449a);
        this.f451c = true;
    }

    @Override // anet.channel.heartbeat.IHeartbeat
    public void reSchedule() {
        this.f450b = System.currentTimeMillis() + this.d;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f451c) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis < this.f450b - 1000) {
            a(this.f450b - jCurrentTimeMillis);
            return;
        }
        if (!GlobalAppRuntimeInfo.isAppBackground()) {
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.DefaultHeartbeatImpl", "heartbeat", this.f449a.p, "session", this.f449a);
            }
            this.f449a.ping(true);
            a(this.d);
            return;
        }
        ALog.e("awcn.DefaultHeartbeatImpl", "close session in background", this.f449a.p, "session", this.f449a);
        this.f449a.close(false);
    }

    private void a(long j) {
        try {
            this.f450b = System.currentTimeMillis() + j;
            ThreadPoolExecutorFactory.submitScheduledTask(this, j + 50, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            ALog.e("awcn.DefaultHeartbeatImpl", "Submit heartbeat task failed.", this.f449a.p, e, new Object[0]);
        }
    }
}
