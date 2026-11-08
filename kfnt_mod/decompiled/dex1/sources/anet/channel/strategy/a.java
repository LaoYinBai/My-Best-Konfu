package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.strategy.dispatch.DispatchConstants;
import anet.channel.util.ALog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class a {

    /* renamed from: a, reason: collision with root package name */
    final ConcurrentHashMap<String, List<IPConnStrategy>> f534a = new ConcurrentHashMap<>();

    /* renamed from: b, reason: collision with root package name */
    final HashMap<String, Object> f535b = new HashMap<>();

    a() {
    }

    List a(String str) {
        Object obj;
        if (TextUtils.isEmpty(str) || !anet.channel.strategy.utils.c.c(str) || DispatchConstants.getAmdcServerDomain().equalsIgnoreCase(str)) {
            return Collections.EMPTY_LIST;
        }
        if (ALog.isPrintLog(1)) {
            ALog.d("awcn.LocalDnsStrategyTable", "try resolve ip with local dns", null, com.alipay.sdk.cons.c.f, str);
        }
        List arrayList = Collections.EMPTY_LIST;
        if (!this.f534a.containsKey(str)) {
            synchronized (this.f535b) {
                if (!this.f535b.containsKey(str)) {
                    obj = new Object();
                    this.f535b.put(str, obj);
                    a(str, obj);
                } else {
                    obj = this.f535b.get(str);
                }
            }
            if (obj != null) {
                try {
                    synchronized (obj) {
                        obj.wait(500L);
                    }
                } catch (InterruptedException unused) {
                }
            }
        }
        List<IPConnStrategy> list = this.f534a.get(str);
        if (list != null && list != Collections.EMPTY_LIST) {
            arrayList = new ArrayList(list);
        }
        ALog.i("awcn.LocalDnsStrategyTable", "get local strategy", null, "strategyList", list);
        return arrayList;
    }

    void a(String str, ConnProtocol connProtocol) {
        List<IPConnStrategy> list = this.f534a.get(str);
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<IPConnStrategy> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().getProtocol().equals(connProtocol)) {
                return;
            }
        }
        list.add(IPConnStrategy.a(list.get(0).getIp(), !(connProtocol.protocol.equalsIgnoreCase("https") || !TextUtils.isEmpty(connProtocol.publicKey)) ? 80 : 443, connProtocol, 0, 0, 1, 45000));
        ALog.i("awcn.LocalDnsStrategyTable", "setProtocolForHost", null, "strategyList", list);
    }

    void a(String str, IConnStrategy iConnStrategy, ConnEvent connEvent) {
        List<IPConnStrategy> list;
        if (connEvent.isSuccess || TextUtils.isEmpty(str) || (list = this.f534a.get(str)) == null || list == Collections.EMPTY_LIST) {
            return;
        }
        Iterator<IPConnStrategy> it = list.iterator();
        while (it.hasNext()) {
            if (it.next() == iConnStrategy) {
                it.remove();
            }
        }
        if (list.isEmpty()) {
            this.f534a.put(str, Collections.EMPTY_LIST);
        }
    }

    private void a(String str, Object obj) {
        anet.channel.strategy.utils.a.a(new b(this, str, obj));
    }
}
