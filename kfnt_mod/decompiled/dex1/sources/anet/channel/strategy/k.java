package anet.channel.strategy;

import java.util.Comparator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class k implements Comparator<IPConnStrategy> {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ StrategyList f570a;

    k(StrategyList strategyList) {
        this.f570a = strategyList;
    }

    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(IPConnStrategy iPConnStrategy, IPConnStrategy iPConnStrategy2) {
        ConnHistoryItem connHistoryItem = (ConnHistoryItem) this.f570a.f528b.get(Integer.valueOf(iPConnStrategy.getUniqueId()));
        ConnHistoryItem connHistoryItem2 = (ConnHistoryItem) this.f570a.f528b.get(Integer.valueOf(iPConnStrategy2.getUniqueId()));
        int iA = connHistoryItem.a();
        int iA2 = connHistoryItem2.a();
        if (iA != iA2) {
            return iA - iA2;
        }
        if (iPConnStrategy.f515a != iPConnStrategy2.f515a) {
            return iPConnStrategy.f515a - iPConnStrategy2.f515a;
        }
        return iPConnStrategy.protocol.isHttp - iPConnStrategy2.protocol.isHttp;
    }
}
