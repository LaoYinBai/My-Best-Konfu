package anet.channel.d;

import anet.channel.AwcnConfig;
import anet.channel.strategy.IStrategyListener;
import anet.channel.strategy.l;
import anet.channel.util.ALog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class c implements IStrategyListener {
    c() {
    }

    @Override // anet.channel.strategy.IStrategyListener
    public void onStrategyUpdated(l.d dVar) {
        ALog.i("awcn.NetworkDetector", "onStrategyUpdated", null, new Object[0]);
        if (!AwcnConfig.isHorseRaceEnable() || dVar.f581c == null || dVar.f581c.length == 0) {
            return;
        }
        if (a.f == null) {
            Thread unused = a.f = new Thread(a.g);
            a.f.setName("AWCN HR");
            a.f.start();
            ALog.i("awcn.NetworkDetector", "start horse race thread", null, new Object[0]);
        }
        synchronized (a.f417a) {
            for (int i = 0; i < dVar.f581c.length; i++) {
                l.c cVar = dVar.f581c[i];
                a.f417a.put(cVar.f577a, cVar);
            }
        }
        a.f419c.lock();
        try {
            a.e.signal();
        } finally {
            a.f419c.unlock();
        }
    }
}
