package anet.channel.f;

import anet.channel.entity.ConnType;
import anet.channel.strategy.IConnStrategy;
import anet.channel.strategy.IStrategyFilter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class b implements IStrategyFilter {
    b() {
    }

    @Override // anet.channel.strategy.IStrategyFilter
    public boolean accept(IConnStrategy iConnStrategy) {
        String str = iConnStrategy.getProtocol().protocol;
        return ConnType.QUIC.equals(str) || ConnType.QUIC_PLAIN.equals(str);
    }
}
