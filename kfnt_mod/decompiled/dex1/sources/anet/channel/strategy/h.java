package anet.channel.strategy;

import anet.channel.AwcnConfig;
import anet.channel.entity.ConnType;
import anet.channel.util.ALog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class h implements IStrategyFilter {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ g f565a;

    h(g gVar) {
        this.f565a = gVar;
    }

    @Override // anet.channel.strategy.IStrategyFilter
    public boolean accept(IConnStrategy iConnStrategy) {
        boolean zIsQuicEnable = AwcnConfig.isQuicEnable();
        boolean z = this.f565a.f563b.d().f532c;
        String str = iConnStrategy.getProtocol().protocol;
        if ((zIsQuicEnable && z) || (!ConnType.QUIC.equals(str) && !ConnType.QUIC_PLAIN.equals(str))) {
            return true;
        }
        ALog.i("awcn.StrategyCenter", "quic strategy disabled", null, "strategy", iConnStrategy);
        return false;
    }
}
