package anetwork.channel.entity;

import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.statist.RequestMonitor;
import anet.channel.statist.RequestStatistic;
import anet.channel.util.ALog;
import anetwork.channel.aidl.DefaultFinishEvent;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class g implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ DefaultFinishEvent f696a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ anetwork.channel.aidl.g f697b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ d f698c;

    g(d dVar, DefaultFinishEvent defaultFinishEvent, anetwork.channel.aidl.g gVar) {
        this.f698c = dVar;
        this.f696a = defaultFinishEvent;
        this.f697b = gVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        String strOptString = null;
        if (this.f696a != null) {
            this.f696a.a((Object) null);
        }
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            RequestStatistic requestStatistic = this.f696a.e;
            if (requestStatistic != null) {
                requestStatistic.lastProcessTime = jCurrentTimeMillis - requestStatistic.rspEnd;
                requestStatistic.oneWayTime = requestStatistic.retryCostTime + (jCurrentTimeMillis - requestStatistic.start);
                this.f696a.c().a(requestStatistic);
            }
            this.f697b.a(this.f696a);
            if (this.f698c.f689c != null) {
                this.f698c.f689c.a();
            }
            if (requestStatistic != null) {
                requestStatistic.callbackTime = System.currentTimeMillis() - jCurrentTimeMillis;
                if (requestStatistic.ret != 1 || requestStatistic.statusCode <= 0) {
                    ALog.e("anet.Repeater", requestStatistic.toString(), this.f698c.f688b, new Object[0]);
                } else if (ALog.isPrintLog(2)) {
                    ALog.i("anet.Repeater", requestStatistic.toString(), this.f698c.f688b, new Object[0]);
                }
                CopyOnWriteArrayList<String> bucketInfo = GlobalAppRuntimeInfo.getBucketInfo();
                if (bucketInfo != null) {
                    int size = bucketInfo.size();
                    for (int i = 0; i < size - 1; i += 2) {
                        requestStatistic.putExtra(bucketInfo.get(i), bucketInfo.get(i + 1));
                    }
                }
                AppMonitor.getInstance().commitStat(requestStatistic);
                if (anetwork.channel.b.b.a(requestStatistic)) {
                    AppMonitor.getInstance().commitStat(new RequestMonitor(requestStatistic));
                }
                try {
                    String str = requestStatistic.ip;
                    if (requestStatistic.extra != null) {
                        strOptString = requestStatistic.extra.optString("firstIp");
                    }
                    if (anet.channel.strategy.utils.c.b(str) || anet.channel.strategy.utils.c.b(strOptString)) {
                        AppMonitor.getInstance().commitStat(new RequestMonitor(requestStatistic));
                    }
                } catch (Exception unused) {
                }
                anetwork.channel.h.b.a().a(this.f698c.e.g(), this.f696a.c());
            }
        } catch (Throwable unused2) {
        }
    }
}
