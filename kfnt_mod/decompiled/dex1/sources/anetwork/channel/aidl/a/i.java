package anetwork.channel.aidl.a;

import anet.channel.util.ALog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class i implements Runnable {
    i() {
    }

    @Override // java.lang.Runnable
    public void run() {
        if (g.f667c) {
            g.f667c = false;
            ALog.w("anet.RemoteGetter", "binding service timeout. reset status!", null, new Object[0]);
        }
    }
}
