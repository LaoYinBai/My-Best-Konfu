package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.push.bi;
import com.xiaomi.push.jl;
import com.xiaomi.push.service.XMPushService;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class hb {
    private int a;

    /* renamed from: a, reason: collision with other field name */
    private long f461a;

    /* renamed from: a, reason: collision with other field name */
    private ha f463a;

    /* renamed from: a, reason: collision with other field name */
    private String f464a;

    /* renamed from: a, reason: collision with other field name */
    private boolean f465a = false;

    /* renamed from: a, reason: collision with other field name */
    private bi f462a = bi.a();

    static class a {
        static final hb a = new hb();
    }

    private fd a(bi.a aVar) {
        if (aVar.f153a == 0) {
            if (aVar.f154a instanceof fd) {
                return (fd) aVar.f154a;
            }
            return null;
        }
        fd fdVarM376a = m376a();
        fdVarM376a.a(fc.CHANNEL_STATS_COUNTER.a());
        fdVarM376a.c(aVar.f153a);
        fdVarM376a.c(aVar.f155a);
        return fdVarM376a;
    }

    private fe a(int i) {
        ArrayList arrayList = new ArrayList();
        fe feVar = new fe(this.f464a, arrayList);
        if (!bg.e(this.f463a.f458a)) {
            feVar.a(i.m(this.f463a.f458a));
        }
        jn jnVar = new jn(i);
        jf jfVarA = new jl.a().a(jnVar);
        try {
            feVar.b(jfVarA);
        } catch (iz unused) {
        }
        LinkedList<bi.a> linkedListM131a = this.f462a.m131a();
        while (linkedListM131a.size() > 0) {
            try {
                fd fdVarA = a(linkedListM131a.getLast());
                if (fdVarA != null) {
                    fdVarA.b(jfVarA);
                }
                if (jnVar.a_() > i) {
                    break;
                }
                if (fdVarA != null) {
                    arrayList.add(fdVarA);
                }
                linkedListM131a.removeLast();
            } catch (iz | NoSuchElementException unused2) {
            }
        }
        return feVar;
    }

    public static ha a() {
        ha haVar;
        synchronized (a.a) {
            haVar = a.a.f463a;
        }
        return haVar;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static hb m374a() {
        return a.a;
    }

    /* renamed from: a, reason: collision with other method in class */
    private void m375a() {
        if (!this.f465a || System.currentTimeMillis() - this.f461a <= this.a) {
            return;
        }
        this.f465a = false;
        this.f461a = 0L;
    }

    /* renamed from: a, reason: collision with other method in class */
    synchronized fd m376a() {
        fd fdVar;
        fdVar = new fd();
        fdVar.a(bg.m124a((Context) this.f463a.f458a));
        fdVar.f351a = (byte) 0;
        fdVar.f355b = 1;
        fdVar.d((int) (System.currentTimeMillis() / 1000));
        return fdVar;
    }

    /* renamed from: a, reason: collision with other method in class */
    synchronized fe m377a() {
        fe feVarA;
        feVarA = null;
        if (b()) {
            feVarA = a(bg.e(this.f463a.f458a) ? 750 : 375);
        }
        return feVarA;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m378a(int i) {
        if (i > 0) {
            int i2 = i * 1000;
            if (i2 > 604800000) {
                i2 = 604800000;
            }
            if (this.a == i2 && this.f465a) {
                return;
            }
            this.f465a = true;
            this.f461a = System.currentTimeMillis();
            this.a = i2;
            com.xiaomi.channel.commonutils.logger.b.c("enable dot duration = " + i2 + " start = " + this.f461a);
        }
    }

    synchronized void a(fd fdVar) {
        this.f462a.a(fdVar);
    }

    public synchronized void a(XMPushService xMPushService) {
        this.f463a = new ha(xMPushService);
        this.f464a = "";
        com.xiaomi.push.service.bk.a().a(new hc(this));
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m379a() {
        return this.f465a;
    }

    boolean b() {
        m375a();
        return this.f465a && this.f462a.m130a() > 0;
    }
}
