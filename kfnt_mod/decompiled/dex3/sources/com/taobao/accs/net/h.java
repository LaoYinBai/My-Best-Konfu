package com.taobao.accs.net;

import anet.channel.strategy.dispatch.DispatchEvent;
import anet.channel.strategy.dispatch.HttpDispatcher;
import com.taobao.accs.common.ThreadPoolExecutorFactory;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class h implements HttpDispatcher.IDispatchEventListener {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public void onEvent(DispatchEvent dispatchEvent) {
        ThreadPoolExecutorFactory.schedule(new i(this), 2000L, TimeUnit.MILLISECONDS);
    }
}
