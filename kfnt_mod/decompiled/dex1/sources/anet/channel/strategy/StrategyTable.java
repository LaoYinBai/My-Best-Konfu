package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.entity.ConnType;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.dispatch.HttpDispatcher;
import anet.channel.strategy.l;
import anet.channel.strategy.utils.SerialLruCache;
import anet.channel.util.ALog;
import anet.channel.util.AppLifecycle;
import anet.channel.util.HttpConstant;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class StrategyTable implements Serializable {
    protected static Comparator<StrategyCollection> f = new o();

    /* renamed from: a, reason: collision with root package name */
    protected String f530a;

    /* renamed from: b, reason: collision with root package name */
    protected volatile String f531b;
    Map<String, Long> d;
    private HostLruCache g;
    private volatile transient int h;

    /* renamed from: c, reason: collision with root package name */
    boolean f532c = false;
    protected transient boolean e = false;

    private static class HostLruCache extends SerialLruCache<String, StrategyCollection> {
        public HostLruCache(int i) {
            super(i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // anet.channel.strategy.utils.SerialLruCache
        public boolean entryRemoved(Map.Entry<String, StrategyCollection> entry) {
            if (!entry.getValue().e) {
                return true;
            }
            Iterator it = entrySet().iterator();
            while (it.hasNext()) {
                if (!((StrategyCollection) ((Map.Entry) it.next()).getValue()).e) {
                    it.remove();
                    return false;
                }
            }
            return false;
        }
    }

    protected StrategyTable(String str) {
        this.f530a = str;
        a();
    }

    private void b() {
        if (HttpDispatcher.getInstance().isInitHostsChanged(this.f530a)) {
            for (String str : HttpDispatcher.getInstance().getInitHosts()) {
                this.g.put(str, new StrategyCollection(str));
            }
        }
    }

    protected void a() {
        if (this.g == null) {
            this.g = new HostLruCache(256);
            b();
        }
        Iterator it = this.g.values().iterator();
        while (it.hasNext()) {
            ((StrategyCollection) it.next()).checkInit();
        }
        ALog.i("awcn.StrategyTable", "strategy map", null, "size", Integer.valueOf(this.g.size()));
        this.h = GlobalAppRuntimeInfo.isTargetProcess() ? 0 : -1;
        if (this.d == null) {
            this.d = new ConcurrentHashMap();
        }
    }

    public List<IConnStrategy> queryByHost(String str) {
        StrategyCollection strategyCollection;
        if (TextUtils.isEmpty(str) || !anet.channel.strategy.utils.c.c(str)) {
            return Collections.EMPTY_LIST;
        }
        c();
        synchronized (this.g) {
            strategyCollection = (StrategyCollection) this.g.get(str);
            if (strategyCollection == null) {
                strategyCollection = new StrategyCollection(str);
                this.g.put(str, strategyCollection);
            }
        }
        if (strategyCollection.f520c == 0 || (strategyCollection.isExpired() && anet.channel.strategy.dispatch.a.a() == 0)) {
            a(str);
        }
        return strategyCollection.queryStrategyList();
    }

    public String getCnameByHost(String str) {
        StrategyCollection strategyCollection;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        synchronized (this.g) {
            strategyCollection = (StrategyCollection) this.g.get(str);
        }
        if (strategyCollection != null && strategyCollection.isExpired() && anet.channel.strategy.dispatch.a.a() == 0) {
            a(str);
        }
        if (strategyCollection != null) {
            return strategyCollection.d;
        }
        return null;
    }

    public void update(l.d dVar) {
        l.b[] bVarArr;
        ALog.i("awcn.StrategyTable", "update strategyTable with httpDns response", this.f530a, new Object[0]);
        try {
            this.f531b = dVar.f579a;
            this.h = dVar.f;
            bVarArr = dVar.f580b;
        } catch (Throwable th) {
            ALog.e("awcn.StrategyTable", "fail to update strategyTable", this.f530a, th, new Object[0]);
        }
        if (bVarArr == null) {
            return;
        }
        synchronized (this.g) {
            for (l.b bVar : bVarArr) {
                if (bVar != null && bVar.f574a != null) {
                    if (bVar.j) {
                        this.g.remove(bVar.f574a);
                    } else {
                        StrategyCollection strategyCollection = (StrategyCollection) this.g.get(bVar.f574a);
                        if (strategyCollection == null) {
                            strategyCollection = new StrategyCollection(bVar.f574a);
                            this.g.put(bVar.f574a, strategyCollection);
                        }
                        strategyCollection.update(bVar);
                    }
                }
            }
        }
        this.e = true;
        if (ALog.isPrintLog(1)) {
            StringBuilder sb = new StringBuilder("uniqueId : ");
            sb.append(this.f530a);
            sb.append("\n-------------------------domains:------------------------------------");
            ALog.d("awcn.StrategyTable", sb.toString(), null, new Object[0]);
            synchronized (this.g) {
                for (Map.Entry entry : this.g.entrySet()) {
                    sb.setLength(0);
                    sb.append((String) entry.getKey());
                    sb.append(" = ");
                    sb.append(((StrategyCollection) entry.getValue()).toString());
                    ALog.d("awcn.StrategyTable", sb.toString(), null, new Object[0]);
                }
            }
        }
    }

    private void a(String str) {
        TreeSet treeSet = new TreeSet();
        treeSet.add(str);
        a(treeSet);
    }

    protected void a(String str, boolean z) {
        StrategyCollection strategyCollection;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.g) {
            strategyCollection = (StrategyCollection) this.g.get(str);
            if (strategyCollection == null) {
                strategyCollection = new StrategyCollection(str);
                this.g.put(str, strategyCollection);
            }
        }
        if (z || strategyCollection.f520c == 0 || (strategyCollection.isExpired() && anet.channel.strategy.dispatch.a.a() == 0)) {
            a(str);
        }
    }

    private void a(Set<String> set) {
        if (set == null || set.isEmpty()) {
            return;
        }
        if ((GlobalAppRuntimeInfo.isAppBackground() && AppLifecycle.lastEnterBackgroundTime > 0) || !NetworkStatusHelper.isConnected()) {
            ALog.i("awcn.StrategyTable", "app in background or no network", this.f530a, new Object[0]);
            return;
        }
        int iA = anet.channel.strategy.dispatch.a.a();
        if (iA == 3) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        synchronized (this.g) {
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                StrategyCollection strategyCollection = (StrategyCollection) this.g.get(it.next());
                if (strategyCollection != null) {
                    strategyCollection.f520c = com.bytedance.b.a.a.c.g.e + jCurrentTimeMillis;
                }
            }
        }
        if (iA == 0) {
            b(set);
        }
        HttpDispatcher.getInstance().sendAmdcRequest(set, this.h);
    }

    private void b(Set<String> set) {
        TreeSet treeSet = new TreeSet(f);
        synchronized (this.g) {
            treeSet.addAll(this.g.values());
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            StrategyCollection strategyCollection = (StrategyCollection) it.next();
            if (!strategyCollection.isExpired() || set.size() >= 40) {
                return;
            }
            strategyCollection.f520c = com.bytedance.b.a.a.c.g.e + jCurrentTimeMillis;
            set.add(strategyCollection.f518a);
        }
    }

    private void c() {
        try {
            if (HttpDispatcher.getInstance().isInitHostsChanged(this.f530a)) {
                TreeSet treeSet = null;
                synchronized (this.g) {
                    for (String str : HttpDispatcher.getInstance().getInitHosts()) {
                        if (!this.g.containsKey(str)) {
                            this.g.put(str, new StrategyCollection(str));
                            if (treeSet == null) {
                                treeSet = new TreeSet();
                            }
                            treeSet.add(str);
                        }
                    }
                }
                if (treeSet != null) {
                    a(treeSet);
                }
            }
        } catch (Exception e) {
            ALog.e("awcn.StrategyTable", "checkInitHost failed", this.f530a, e, new Object[0]);
        }
    }

    void a(String str, IConnStrategy iConnStrategy, ConnEvent connEvent) {
        StrategyCollection strategyCollection;
        if (ALog.isPrintLog(1)) {
            ALog.d("awcn.StrategyTable", "[notifyConnEvent]", null, HttpConstant.HOST, str, "IConnStrategy", iConnStrategy, "ConnEvent", connEvent);
        }
        String str2 = iConnStrategy.getProtocol().protocol;
        if (ConnType.QUIC.equals(str2) || ConnType.QUIC_PLAIN.equals(str2)) {
            this.f532c = connEvent.isSuccess;
            ALog.e("awcn.StrategyTable", "enbale quic", null, "uniqueId", this.f530a, "enable", Boolean.valueOf(connEvent.isSuccess));
        }
        if (!connEvent.isSuccess && anet.channel.strategy.utils.c.b(iConnStrategy.getIp())) {
            this.d.put(str, Long.valueOf(System.currentTimeMillis()));
            ALog.e("awcn.StrategyTable", "disable ipv6", null, "uniqueId", this.f530a, com.alipay.sdk.cons.c.f, str);
        }
        synchronized (this.g) {
            strategyCollection = (StrategyCollection) this.g.get(str);
        }
        if (strategyCollection != null) {
            strategyCollection.notifyConnEvent(iConnStrategy, connEvent);
        }
    }

    boolean a(String str, long j) {
        Long l = this.d.get(str);
        if (l == null) {
            return false;
        }
        if (l.longValue() + j >= System.currentTimeMillis()) {
            return true;
        }
        this.d.remove(str);
        return false;
    }
}
