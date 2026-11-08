package anet.channel.strategy;

import java.util.Comparator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class o implements Comparator<StrategyCollection> {
    o() {
    }

    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(StrategyCollection strategyCollection, StrategyCollection strategyCollection2) {
        if (strategyCollection.f520c != strategyCollection2.f520c) {
            return (int) (strategyCollection.f520c - strategyCollection2.f520c);
        }
        return strategyCollection.f518a.compareTo(strategyCollection2.f518a);
    }
}
