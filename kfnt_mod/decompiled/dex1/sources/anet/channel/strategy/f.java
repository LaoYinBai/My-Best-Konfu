package anet.channel.strategy;

import anet.channel.statist.StrategyStatObject;
import anet.channel.strategy.StrategyInfoHolder;
import java.io.Serializable;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class f implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Map.Entry f560a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ StrategyInfoHolder.LruStrategyMap f561b;

    f(StrategyInfoHolder.LruStrategyMap lruStrategyMap, Map.Entry entry) {
        this.f561b = lruStrategyMap;
        this.f560a = entry;
    }

    @Override // java.lang.Runnable
    public void run() {
        StrategyTable strategyTable = (StrategyTable) this.f560a.getValue();
        if (strategyTable.e) {
            StrategyStatObject strategyStatObject = new StrategyStatObject(1);
            strategyStatObject.writeStrategyFileId = strategyTable.f530a;
            m.a((Serializable) this.f560a.getValue(), strategyTable.f530a, strategyStatObject);
            strategyTable.e = false;
        }
    }
}
