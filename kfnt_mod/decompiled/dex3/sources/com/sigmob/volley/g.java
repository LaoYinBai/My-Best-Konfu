package com.sigmob.volley;

import android.net.TrafficStats;
import android.os.SystemClock;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class g implements Comparable, Runnable {
    private final f a;
    private final o b;
    private final l c;
    private volatile boolean d = false;

    public g(f fVar, l lVar, o oVar) {
        this.c = lVar;
        this.a = fVar;
        this.b = oVar;
    }

    private void a(l<?> lVar, s sVar) {
        this.b.a(lVar, lVar.b(sVar));
    }

    private void b(l<?> lVar) {
        TrafficStats.setThreadStatsTag(lVar.g());
    }

    public void a(l<?> lVar) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        try {
            lVar.a("network-queue-take");
            if (lVar.k()) {
                lVar.b("network-discard-cancelled");
                lVar.u();
                return;
            }
            b(lVar);
            i iVarA = this.a.a(lVar);
            lVar.a("network-http-complete");
            if (iVarA.e && lVar.t()) {
                lVar.b("not-modified");
                lVar.u();
                return;
            }
            n<?> nVarA = lVar.a(iVarA);
            lVar.a("network-parse-complete");
            lVar.s();
            this.b.a(lVar, nVarA);
            lVar.a(nVarA);
        } catch (s e) {
            e.a(SystemClock.elapsedRealtime() - jElapsedRealtime);
            a(lVar, e);
            lVar.u();
        } catch (Throwable th) {
            t.a(th, "Unhandled exception %s", th.toString());
            s sVar = new s(th);
            sVar.a(SystemClock.elapsedRealtime() - jElapsedRealtime);
            this.b.a(lVar, sVar);
            lVar.u();
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return (obj == null && hashCode() == obj.hashCode()) ? 0 : 1;
    }

    @Override // java.lang.Runnable
    public void run() {
        a(this.c);
    }
}
