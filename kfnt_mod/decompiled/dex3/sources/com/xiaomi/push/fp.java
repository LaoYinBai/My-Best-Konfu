package com.xiaomi.push;

import android.util.Pair;
import com.taobao.accs.utl.UtilityImpl;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.av;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class fp {
    private static final AtomicInteger a = new AtomicInteger(0);

    /* renamed from: a, reason: collision with other field name */
    public static boolean f391a;

    /* renamed from: a, reason: collision with other field name */
    protected fq f394a;

    /* renamed from: a, reason: collision with other field name */
    protected XMPushService f396a;

    /* renamed from: a, reason: collision with other field name */
    protected int f392a = 0;

    /* renamed from: a, reason: collision with other field name */
    protected long f393a = -1;

    /* renamed from: b, reason: collision with other field name */
    protected volatile long f401b = 0;

    /* renamed from: c, reason: collision with other field name */
    protected volatile long f404c = 0;

    /* renamed from: a, reason: collision with other field name */
    private LinkedList<Pair<Integer, Long>> f399a = new LinkedList<>();

    /* renamed from: a, reason: collision with other field name */
    private final Collection<fs> f398a = new CopyOnWriteArrayList();

    /* renamed from: a, reason: collision with other field name */
    protected final Map<fu, a> f400a = new ConcurrentHashMap();

    /* renamed from: b, reason: collision with other field name */
    protected final Map<fu, a> f403b = new ConcurrentHashMap();

    /* renamed from: a, reason: collision with other field name */
    protected gb f395a = null;

    /* renamed from: a, reason: collision with other field name */
    protected String f397a = "";

    /* renamed from: b, reason: collision with other field name */
    protected String f402b = "";
    private int c = 2;
    protected final int b = a.getAndIncrement();
    private long e = 0;
    protected long d = 0;

    public static class a {
        private fu a;

        /* renamed from: a, reason: collision with other field name */
        private gc f405a;

        public a(fu fuVar, gc gcVar) {
            this.a = fuVar;
            this.f405a = gcVar;
        }

        public void a(fg fgVar) {
            this.a.a(fgVar);
        }

        public void a(gg ggVar) {
            if (this.f405a == null || this.f405a.mo143a(ggVar)) {
                this.a.a(ggVar);
            }
        }
    }

    static {
        f391a = false;
        try {
            f391a = Boolean.getBoolean("smack.debugEnabled");
        } catch (Exception unused) {
        }
        fv.m339a();
    }

    protected fp(XMPushService xMPushService, fq fqVar) throws ClassNotFoundException {
        this.f394a = fqVar;
        this.f396a = xMPushService;
        m333b();
    }

    private String a(int i) {
        return i == 1 ? "connected" : i == 0 ? "connecting" : i == 2 ? "disconnected" : UtilityImpl.NET_TYPE_UNKNOWN;
    }

    /* renamed from: a, reason: collision with other method in class */
    private void m328a(int i) {
        synchronized (this.f399a) {
            try {
                if (i == 1) {
                    this.f399a.clear();
                } else {
                    this.f399a.add(new Pair<>(Integer.valueOf(i), Long.valueOf(System.currentTimeMillis())));
                    if (this.f399a.size() > 6) {
                        this.f399a.remove(0);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int a() {
        return this.f392a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public long m329a() {
        return this.f404c;
    }

    /* renamed from: a, reason: collision with other method in class */
    public fq m330a() {
        return this.f394a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public String mo331a() {
        return this.f394a.c();
    }

    public void a(int i, int i2, Exception exc) {
        if (i != this.c) {
            com.xiaomi.channel.commonutils.logger.b.m22a(String.format("update the connection status. %1$s -> %2$s : %3$s ", a(this.c), a(i), com.xiaomi.push.service.az.a(i2)));
        }
        if (bg.b(this.f396a)) {
            m328a(i);
        }
        if (i == 1) {
            this.f396a.a(10);
            if (this.c != 0) {
                com.xiaomi.channel.commonutils.logger.b.m22a("try set connected while not connecting.");
            }
            this.c = i;
            Iterator<fs> it = this.f398a.iterator();
            while (it.hasNext()) {
                it.next().a(this);
            }
            return;
        }
        if (i == 0) {
            if (this.c != 2) {
                com.xiaomi.channel.commonutils.logger.b.m22a("try set connecting while not disconnected.");
            }
            this.c = i;
            Iterator<fs> it2 = this.f398a.iterator();
            while (it2.hasNext()) {
                it2.next().b(this);
            }
            return;
        }
        if (i == 2) {
            this.f396a.a(10);
            if (this.c == 0) {
                Iterator<fs> it3 = this.f398a.iterator();
                while (it3.hasNext()) {
                    it3.next().a(this, exc == null ? new CancellationException("disconnect while connecting") : exc);
                }
            } else if (this.c == 1) {
                Iterator<fs> it4 = this.f398a.iterator();
                while (it4.hasNext()) {
                    it4.next().a(this, i2, exc);
                }
            }
            this.c = i;
        }
    }

    public void a(fs fsVar) {
        if (fsVar == null || this.f398a.contains(fsVar)) {
            return;
        }
        this.f398a.add(fsVar);
    }

    public void a(fu fuVar, gc gcVar) {
        if (fuVar == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        this.f400a.put(fuVar, new a(fuVar, gcVar));
    }

    public abstract void a(gg ggVar);

    public abstract void a(av.b bVar);

    public synchronized void a(String str) {
        if (this.c == 0) {
            com.xiaomi.channel.commonutils.logger.b.m22a("setChallenge hash = " + bl.a(str).substring(0, 8));
            this.f397a = str;
            a(1, 0, null);
        } else {
            com.xiaomi.channel.commonutils.logger.b.m22a("ignore setChallenge because connection was disconnected");
        }
    }

    public abstract void a(String str, String str2);

    public abstract void a(fg[] fgVarArr);

    /* renamed from: a */
    public boolean mo326a() {
        return false;
    }

    public synchronized boolean a(long j) {
        return this.e >= j;
    }

    public int b() {
        return this.c;
    }

    /* renamed from: b, reason: collision with other method in class */
    public String m332b() {
        return this.f394a.b();
    }

    /* renamed from: b, reason: collision with other method in class */
    protected void m333b() throws ClassNotFoundException {
        String property;
        if (this.f394a.m337a() && this.f395a == null) {
            Class<?> cls = null;
            try {
                property = System.getProperty("smack.debuggerClass");
            } catch (Throwable unused) {
                property = null;
            }
            if (property != null) {
                try {
                    cls = Class.forName(property);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (cls == null) {
                this.f395a = new bu(this);
                return;
            }
            try {
                this.f395a = (gb) cls.getConstructor(fp.class, Writer.class, Reader.class).newInstance(this);
            } catch (Exception e2) {
                throw new IllegalArgumentException("Can't initialize the configured debugger!", e2);
            }
        }
    }

    public abstract void b(int i, Exception exc);

    public abstract void b(fg fgVar);

    public void b(fs fsVar) {
        this.f398a.remove(fsVar);
    }

    public void b(fu fuVar, gc gcVar) {
        if (fuVar == null) {
            throw new NullPointerException("Packet listener is null.");
        }
        this.f403b.put(fuVar, new a(fuVar, gcVar));
    }

    public abstract void b(boolean z);

    /* renamed from: b, reason: collision with other method in class */
    public boolean m334b() {
        return this.c == 0;
    }

    public synchronized void c() {
        this.e = System.currentTimeMillis();
    }

    /* renamed from: c, reason: collision with other method in class */
    public boolean m335c() {
        return this.c == 1;
    }

    public void d() {
        synchronized (this.f399a) {
            this.f399a.clear();
        }
    }
}
