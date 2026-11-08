package anet.channel.d;

import anet.channel.AwcnConfig;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.strategy.l;
import anet.channel.util.ALog;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class b implements Runnable {
    b() {
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        Map.Entry entryPollFirstEntry;
        ALog.e("awcn.NetworkDetector", "network detect thread start", null, new Object[0]);
        while (true) {
            try {
                a.f419c.lock();
                if (!GlobalAppRuntimeInfo.isAppBackground()) {
                    a.d.await();
                }
                if (a.f417a.isEmpty()) {
                    a.e.await();
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                a.f419c.unlock();
                throw th;
            }
            a.f419c.unlock();
            while (GlobalAppRuntimeInfo.isAppBackground()) {
                synchronized (a.f417a) {
                    if (!AwcnConfig.isHorseRaceEnable()) {
                        a.f417a.clear();
                        entryPollFirstEntry = null;
                    } else {
                        entryPollFirstEntry = a.f417a.pollFirstEntry();
                    }
                }
                if (entryPollFirstEntry == null) {
                    break;
                }
                try {
                    a.b((l.c) entryPollFirstEntry.getValue());
                } catch (Exception e) {
                    ALog.e("awcn.NetworkDetector", "start hr task failed", null, e, new Object[0]);
                }
            }
        }
    }
}
