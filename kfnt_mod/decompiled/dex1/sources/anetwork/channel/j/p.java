package anetwork.channel.j;

import anet.channel.appmonitor.AppMonitor;
import anet.channel.statist.ExceptionStatistic;
import anet.channel.statist.RequestStatistic;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anetwork.channel.aidl.DefaultFinishEvent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class p implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ m f745a;

    p(m mVar) {
        this.f745a = mVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f745a.f739a.d.compareAndSet(false, true)) {
            RequestStatistic requestStatistic = this.f745a.f739a.f736a.f700b;
            if (requestStatistic.isDone.compareAndSet(false, true)) {
                requestStatistic.statusCode = ErrorConstant.ERROR_REQUEST_TIME_OUT;
                requestStatistic.msg = ErrorConstant.getErrMsg(ErrorConstant.ERROR_REQUEST_TIME_OUT);
                requestStatistic.rspEnd = System.currentTimeMillis();
                ALog.e("anet.UnifiedRequestTask", "task time out", this.f745a.f739a.f738c, "rs", requestStatistic);
                AppMonitor.getInstance().commitStat(new ExceptionStatistic(ErrorConstant.ERROR_REQUEST_TIME_OUT, null, requestStatistic, null));
            }
            this.f745a.f739a.b();
            this.f745a.f739a.f737b.a(new DefaultFinishEvent(ErrorConstant.ERROR_REQUEST_TIME_OUT, null, requestStatistic));
        }
    }
}
