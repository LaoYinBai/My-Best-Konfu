package anet.channel.strategy;

import anet.channel.strategy.l;
import anet.channel.strategy.utils.SerialLruCache;
import anet.channel.util.ALog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class StrategyList implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private List<IPConnStrategy> f527a;

    /* renamed from: b, reason: collision with root package name */
    private Map<Integer, ConnHistoryItem> f528b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f529c;
    private transient Comparator<IPConnStrategy> d;

    /* JADX INFO: Access modifiers changed from: private */
    interface Predicate<T> {
        boolean apply(T t);
    }

    public StrategyList() {
        this.f527a = new ArrayList();
        this.f528b = new SerialLruCache(40);
        this.f529c = false;
        this.d = null;
    }

    StrategyList(List<IPConnStrategy> list) {
        this.f527a = new ArrayList();
        this.f528b = new SerialLruCache(40);
        this.f529c = false;
        this.d = null;
        this.f527a = list;
    }

    public void checkInit() {
        if (this.f527a == null) {
            this.f527a = new ArrayList();
        }
        if (this.f528b == null) {
            this.f528b = new SerialLruCache(40);
        }
        Iterator<Map.Entry<Integer, ConnHistoryItem>> it = this.f528b.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue().d()) {
                it.remove();
            }
        }
        for (IPConnStrategy iPConnStrategy : this.f527a) {
            if (!this.f528b.containsKey(Integer.valueOf(iPConnStrategy.getUniqueId()))) {
                this.f528b.put(Integer.valueOf(iPConnStrategy.getUniqueId()), new ConnHistoryItem());
            }
        }
        Collections.sort(this.f527a, a());
    }

    public String toString() {
        return this.f527a.toString();
    }

    public List<IConnStrategy> getStrategyList() {
        if (this.f527a.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        LinkedList linkedList = null;
        for (IPConnStrategy iPConnStrategy : this.f527a) {
            ConnHistoryItem connHistoryItem = this.f528b.get(Integer.valueOf(iPConnStrategy.getUniqueId()));
            if (connHistoryItem != null && connHistoryItem.c()) {
                ALog.i("awcn.StrategyList", "strategy ban!", null, "strategy", iPConnStrategy);
            } else {
                if (linkedList == null) {
                    linkedList = new LinkedList();
                }
                linkedList.add(iPConnStrategy);
            }
        }
        return linkedList == null ? Collections.EMPTY_LIST : linkedList;
    }

    public void update(l.b bVar) {
        Iterator<IPConnStrategy> it = this.f527a.iterator();
        while (it.hasNext()) {
            it.next().f517c = true;
        }
        for (int i = 0; i < bVar.h.length; i++) {
            for (int i2 = 0; i2 < bVar.f.length; i2++) {
                a(bVar.f[i2], 1, bVar.h[i]);
            }
            if (bVar.g != null) {
                this.f529c = true;
                for (int i3 = 0; i3 < bVar.g.length; i3++) {
                    a(bVar.g[i3], 0, bVar.h[i]);
                }
            } else {
                this.f529c = false;
            }
        }
        if (bVar.i != null) {
            for (int i4 = 0; i4 < bVar.i.length; i4++) {
                l.e eVar = bVar.i[i4];
                a(eVar.f582a, anet.channel.strategy.utils.c.c(eVar.f582a) ? -1 : 1, eVar.f583b);
            }
        }
        ListIterator<IPConnStrategy> listIterator = this.f527a.listIterator();
        while (listIterator.hasNext()) {
            if (listIterator.next().f517c) {
                listIterator.remove();
            }
        }
        Collections.sort(this.f527a, a());
    }

    private void a(String str, int i, l.a aVar) {
        int iA = a(this.f527a, new j(this, aVar, str, ConnProtocol.valueOf(aVar)));
        if (iA != -1) {
            IPConnStrategy iPConnStrategy = this.f527a.get(iA);
            iPConnStrategy.cto = aVar.f573c;
            iPConnStrategy.rto = aVar.d;
            iPConnStrategy.heartbeat = aVar.f;
            iPConnStrategy.f515a = i;
            iPConnStrategy.f516b = 0;
            iPConnStrategy.f517c = false;
            return;
        }
        IPConnStrategy iPConnStrategyA = IPConnStrategy.a(str, aVar);
        if (iPConnStrategyA != null) {
            iPConnStrategyA.f515a = i;
            iPConnStrategyA.f516b = 0;
            if (!this.f528b.containsKey(Integer.valueOf(iPConnStrategyA.getUniqueId()))) {
                this.f528b.put(Integer.valueOf(iPConnStrategyA.getUniqueId()), new ConnHistoryItem());
            }
            this.f527a.add(iPConnStrategyA);
        }
    }

    public boolean shouldRefresh() {
        boolean z = true;
        boolean z2 = true;
        for (IPConnStrategy iPConnStrategy : this.f527a) {
            if (!this.f528b.get(Integer.valueOf(iPConnStrategy.getUniqueId())).b()) {
                if (iPConnStrategy.f515a == 0) {
                    z = false;
                }
                z2 = false;
            }
        }
        return (this.f529c && z) || z2;
    }

    public void notifyConnEvent(IConnStrategy iConnStrategy, ConnEvent connEvent) {
        if (!(iConnStrategy instanceof IPConnStrategy) || this.f527a.indexOf(iConnStrategy) == -1) {
            return;
        }
        this.f528b.get(Integer.valueOf(((IPConnStrategy) iConnStrategy).getUniqueId())).a(connEvent.isSuccess);
        Collections.sort(this.f527a, this.d);
    }

    private Comparator a() {
        if (this.d == null) {
            this.d = new k(this);
        }
        return this.d;
    }

    private static <T> int a(Collection<T> collection, Predicate<T> predicate) {
        if (collection == null) {
            return -1;
        }
        int i = 0;
        Iterator<T> it = collection.iterator();
        while (it.hasNext() && !predicate.apply(it.next())) {
            i++;
        }
        if (i == collection.size()) {
            return -1;
        }
        return i;
    }
}
