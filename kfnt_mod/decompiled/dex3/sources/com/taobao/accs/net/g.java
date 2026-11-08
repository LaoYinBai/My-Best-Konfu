package com.taobao.accs.net;

import anet.channel.entity.ConnType;
import anet.channel.strategy.IConnStrategy;
import anet.channel.strategy.StrategyCenter;
import anet.channel.strategy.dispatch.HttpDispatcher;
import com.taobao.accs.utl.ALog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class g {
    private int a = 0;
    private List<IConnStrategy> b = new ArrayList();

    public g(String str) {
        HttpDispatcher.getInstance().addListener(new h(this));
        a(str);
    }

    public List<IConnStrategy> a(String str) {
        List<IConnStrategy> connStrategyListByHost;
        if ((this.a == 0 || this.b.isEmpty()) && (connStrategyListByHost = StrategyCenter.getInstance().getConnStrategyListByHost(str)) != null && !connStrategyListByHost.isEmpty()) {
            this.b.clear();
            for (IConnStrategy iConnStrategy : connStrategyListByHost) {
                ConnType connTypeValueOf = ConnType.valueOf(iConnStrategy.getProtocol());
                if (connTypeValueOf.getTypeLevel() == ConnType.TypeLevel.SPDY && connTypeValueOf.isSSL()) {
                    this.b.add(iConnStrategy);
                }
            }
        }
        return this.b;
    }

    public IConnStrategy a() {
        return a(this.b);
    }

    public IConnStrategy a(List<IConnStrategy> list) {
        if (list == null || list.isEmpty()) {
            ALog.d("HttpDnsProvider", "strategys null or 0", new Object[0]);
            return null;
        }
        if (this.a < 0 || this.a >= list.size()) {
            this.a = 0;
        }
        return list.get(this.a);
    }

    public void b() {
        this.a++;
        if (ALog.isPrintLog(ALog.Level.D)) {
            ALog.d("HttpDnsProvider", "updateStrategyPos StrategyPos:" + this.a, new Object[0]);
        }
    }

    public int c() {
        return this.a;
    }

    public void b(String str) {
        StrategyCenter.getInstance().forceRefreshStrategy(str);
    }
}
