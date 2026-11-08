package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.AwcnConfig;
import anet.channel.util.ALog;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class d implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f542a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ StrategyInfoHolder f543b;

    d(StrategyInfoHolder strategyInfoHolder, String str) {
        this.f543b = strategyInfoHolder;
        this.f542a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            ALog.i("awcn.StrategyInfoHolder", "start loading strategy files", null, new Object[0]);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (AwcnConfig.isAsyncLoadStrategyEnable()) {
                ALog.i("awcn.StrategyInfoHolder", "load strategy async", null, new Object[0]);
                if (!TextUtils.isEmpty(this.f542a)) {
                    this.f543b.a(this.f542a, true);
                }
                StrategyConfig strategyConfig = (StrategyConfig) m.a("StrategyConfig", null);
                if (strategyConfig != null) {
                    strategyConfig.a();
                    strategyConfig.a(this.f543b);
                    synchronized (this.f543b) {
                        this.f543b.f525b = strategyConfig;
                    }
                }
            }
            File[] fileArrB = m.b();
            if (fileArrB == null) {
                return;
            }
            int i = 0;
            for (int i2 = 0; i2 < fileArrB.length && i < 2; i2++) {
                File file = fileArrB[i2];
                if (!file.isDirectory()) {
                    String name = file.getName();
                    if (!name.equals(this.f542a) && !name.startsWith("StrategyConfig")) {
                        this.f543b.a(name, false);
                        i++;
                    }
                }
            }
            ALog.i("awcn.StrategyInfoHolder", "end loading strategy files", null, "total cost", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        } catch (Exception unused) {
        }
    }
}
