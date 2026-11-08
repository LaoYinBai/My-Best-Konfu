package anet.channel.strategy;

import anet.channel.strategy.StrategyList;
import anet.channel.strategy.l;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class j implements StrategyList.Predicate<IPConnStrategy> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ l.a f567a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ String f568b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ ConnProtocol f569c;
    final /* synthetic */ StrategyList d;

    j(StrategyList strategyList, l.a aVar, String str, ConnProtocol connProtocol) {
        this.d = strategyList;
        this.f567a = aVar;
        this.f568b = str;
        this.f569c = connProtocol;
    }

    @Override // anet.channel.strategy.StrategyList.Predicate
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public boolean apply(IPConnStrategy iPConnStrategy) {
        return iPConnStrategy.getPort() == this.f567a.f571a && iPConnStrategy.getIp().equals(this.f568b) && iPConnStrategy.protocol.equals(this.f569c);
    }
}
