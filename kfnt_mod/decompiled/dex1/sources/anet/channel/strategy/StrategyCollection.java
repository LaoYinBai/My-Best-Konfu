package anet.channel.strategy;

import anet.channel.strategy.dispatch.DispatchConstants;
import anet.channel.strategy.l;
import anet.channel.util.ALog;
import com.baidu.mobads.sdk.internal.av;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class StrategyCollection implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    String f518a;

    /* renamed from: b, reason: collision with root package name */
    StrategyList f519b;

    /* renamed from: c, reason: collision with root package name */
    volatile long f520c;
    volatile String d;
    boolean e;
    private transient long f;

    public StrategyCollection() {
        this.f519b = null;
        this.f520c = 0L;
        this.d = null;
        this.e = false;
        this.f = 0L;
    }

    protected StrategyCollection(String str) {
        this.f519b = null;
        this.f520c = 0L;
        this.d = null;
        this.e = false;
        this.f = 0L;
        this.f518a = str;
        this.e = DispatchConstants.isAmdcServerDomain(str);
    }

    public void checkInit() {
        if (System.currentTimeMillis() - this.f520c > av.e) {
            this.f519b = null;
        } else if (this.f519b != null) {
            this.f519b.checkInit();
        }
    }

    public synchronized List<IConnStrategy> queryStrategyList() {
        if (this.f519b == null) {
            return Collections.EMPTY_LIST;
        }
        return this.f519b.getStrategyList();
    }

    public synchronized void notifyConnEvent(IConnStrategy iConnStrategy, ConnEvent connEvent) {
        if (this.f519b != null) {
            this.f519b.notifyConnEvent(iConnStrategy, connEvent);
            if (!connEvent.isSuccess && this.f519b.shouldRefresh()) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - this.f > 60000) {
                    StrategyCenter.getInstance().forceRefreshStrategy(this.f518a);
                    this.f = jCurrentTimeMillis;
                }
            }
        }
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > this.f520c;
    }

    public synchronized void update(l.b bVar) {
        this.f520c = System.currentTimeMillis() + (bVar.f575b * 1000);
        if (!bVar.f574a.equalsIgnoreCase(this.f518a)) {
            ALog.e("StrategyCollection", "update error!", null, com.alipay.sdk.cons.c.f, this.f518a, "dnsInfo.host", bVar.f574a);
            return;
        }
        this.d = bVar.d;
        if ((bVar.f != null && bVar.f.length != 0 && bVar.h != null && bVar.h.length != 0) || (bVar.i != null && bVar.i.length != 0)) {
            if (this.f519b == null) {
                this.f519b = new StrategyList();
            }
            this.f519b.update(bVar);
            return;
        }
        this.f519b = null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append("\nStrategyList = ");
        sb.append(this.f520c);
        if (this.f519b != null) {
            sb.append(this.f519b.toString());
        } else if (this.d != null) {
            sb.append('[');
            sb.append(this.f518a);
            sb.append("=>");
            sb.append(this.d);
            sb.append(']');
        } else {
            sb.append("[]");
        }
        return sb.toString();
    }
}
