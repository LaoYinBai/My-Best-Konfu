package anet.channel.d;

import anet.channel.util.ALog;
import anet.channel.util.AppLifecycle;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class d implements AppLifecycle.AppLifecycleListener {
    @Override // anet.channel.util.AppLifecycle.AppLifecycleListener
    public void forground() {
    }

    d() {
    }

    @Override // anet.channel.util.AppLifecycle.AppLifecycleListener
    public void background() {
        ALog.i("awcn.NetworkDetector", "background", null, new Object[0]);
        a.f419c.lock();
        try {
            a.d.signal();
        } finally {
            a.f419c.unlock();
        }
    }
}
