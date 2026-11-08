package anetwork.channel.j;

import anet.channel.appmonitor.AppMonitor;
import anet.channel.request.Request;
import anet.channel.statist.ExceptionStatistic;
import anet.channel.statist.RequestStatistic;
import anet.channel.thread.ThreadPoolExecutorFactory;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anetwork.channel.aidl.DefaultFinishEvent;
import anetwork.channel.f.b;
import com.bytedance.msdk.adapter.util.TTLogUtil;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class m {

    /* renamed from: a, reason: collision with root package name */
    protected l f739a;

    public m(anetwork.channel.entity.h hVar, anetwork.channel.entity.d dVar) {
        dVar.a(hVar.e);
        this.f739a = new l(hVar, dVar);
    }

    class a implements b.a {

        /* renamed from: b, reason: collision with root package name */
        private int f741b;

        /* renamed from: c, reason: collision with root package name */
        private Request f742c;
        private anetwork.channel.f.a d;

        a(int i, Request request, anetwork.channel.f.a aVar) {
            this.f741b = 0;
            this.f742c = null;
            this.d = null;
            this.f741b = i;
            this.f742c = request;
            this.d = aVar;
        }

        public Future a(Request request, anetwork.channel.f.a aVar) {
            if (m.this.f739a.d.get()) {
                ALog.i("anet.UnifiedRequestTask", "request canneled or timeout in processing interceptor", request.getSeq(), new Object[0]);
                return null;
            }
            if (this.f741b < anetwork.channel.f.c.a()) {
                return anetwork.channel.f.c.a(this.f741b).a(m.this.new a(this.f741b + 1, request, aVar));
            }
            m.this.f739a.f736a.a(request);
            m.this.f739a.f737b = aVar;
            anetwork.channel.a.a aVarA = anetwork.channel.b.b.h() ? anetwork.channel.a.b.a(m.this.f739a.f736a.g(), m.this.f739a.f736a.h()) : null;
            m.this.f739a.e = aVarA != null ? new c(m.this.f739a, aVarA) : new g(m.this.f739a, null, null);
            m.this.f739a.e.run();
            m.this.c();
            return null;
        }
    }

    public Future a() {
        this.f739a.f736a.f700b.start = System.currentTimeMillis();
        if (ALog.isPrintLog(2)) {
            ALog.i("anet.UnifiedRequestTask", TTLogUtil.TAG_EVENT_REQUEST, this.f739a.f738c, "Url", this.f739a.f736a.g());
        }
        if (anetwork.channel.b.b.b(this.f739a.f736a.f())) {
            d dVar = new d(this.f739a);
            this.f739a.e = dVar;
            dVar.f714a = new anet.channel.request.b(ThreadPoolExecutorFactory.submitBackupTask(new n(this)), this.f739a.f736a.a().getSeq());
            c();
            return new f(this);
        }
        ThreadPoolExecutorFactory.submitPriorityTask(new o(this), ThreadPoolExecutorFactory.Priority.HIGH);
        return new f(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.f739a.f = ThreadPoolExecutorFactory.submitScheduledTask(new p(this), this.f739a.f736a.b(), TimeUnit.MILLISECONDS);
    }

    void b() {
        if (this.f739a.d.compareAndSet(false, true)) {
            ALog.e("anet.UnifiedRequestTask", "task cancelled", this.f739a.f738c, "URL", this.f739a.f736a.f().simpleUrlString());
            RequestStatistic requestStatistic = this.f739a.f736a.f700b;
            if (requestStatistic.isDone.compareAndSet(false, true)) {
                requestStatistic.ret = 2;
                requestStatistic.statusCode = ErrorConstant.ERROR_REQUEST_CANCEL;
                requestStatistic.msg = ErrorConstant.getErrMsg(ErrorConstant.ERROR_REQUEST_CANCEL);
                requestStatistic.rspEnd = System.currentTimeMillis();
                AppMonitor.getInstance().commitStat(new ExceptionStatistic(ErrorConstant.ERROR_REQUEST_CANCEL, null, requestStatistic, null));
                if (requestStatistic.recDataSize > 102400) {
                    anet.channel.monitor.b.a().a(requestStatistic.sendStart, requestStatistic.rspEnd, requestStatistic.recDataSize);
                }
            }
            this.f739a.b();
            this.f739a.a();
            this.f739a.f737b.a(new DefaultFinishEvent(ErrorConstant.ERROR_REQUEST_CANCEL, null, requestStatistic));
        }
    }
}
