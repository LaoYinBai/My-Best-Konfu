package anet.channel.d;

import anet.channel.strategy.ConnProtocol;
import anet.channel.strategy.IConnStrategy;
import anet.channel.strategy.l;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class g implements IConnStrategy {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ l.e f424a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ ConnProtocol f425b;

    @Override // anet.channel.strategy.IConnStrategy
    public int getHeartbeat() {
        return 0;
    }

    @Override // anet.channel.strategy.IConnStrategy
    public int getIpSource() {
        return 2;
    }

    @Override // anet.channel.strategy.IConnStrategy
    public int getIpType() {
        return 1;
    }

    @Override // anet.channel.strategy.IConnStrategy
    public int getRetryTimes() {
        return 0;
    }

    g(l.e eVar, ConnProtocol connProtocol) {
        this.f424a = eVar;
        this.f425b = connProtocol;
    }

    @Override // anet.channel.strategy.IConnStrategy
    public String getIp() {
        return this.f424a.f582a;
    }

    @Override // anet.channel.strategy.IConnStrategy
    public int getPort() {
        return this.f424a.f583b.f571a;
    }

    @Override // anet.channel.strategy.IConnStrategy
    public ConnProtocol getProtocol() {
        return this.f425b;
    }

    @Override // anet.channel.strategy.IConnStrategy
    public int getConnectionTimeout() {
        return this.f424a.f583b.f573c;
    }

    @Override // anet.channel.strategy.IConnStrategy
    public int getReadTimeout() {
        return this.f424a.f583b.d;
    }
}
