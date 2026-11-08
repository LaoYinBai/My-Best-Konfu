package com.taobao.accs.net;

import anet.channel.strategy.StrategyCenter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class i implements Runnable {
    final /* synthetic */ h a;

    i(h hVar) {
        this.a = hVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        StrategyCenter.getInstance().saveData();
    }
}
