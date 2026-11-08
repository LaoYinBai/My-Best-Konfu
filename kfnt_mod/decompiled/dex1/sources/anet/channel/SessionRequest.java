package anet.channel;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.entity.ConnType;
import anet.channel.entity.EventType;
import anet.channel.session.TnetSpdySession;
import anet.channel.statist.AlarmObject;
import anet.channel.statist.SessionConnStat;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.IConnStrategy;
import anet.channel.strategy.StrategyCenter;
import anet.channel.thread.ThreadPoolExecutorFactory;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.HttpUrl;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class SessionRequest {

    /* renamed from: a, reason: collision with root package name */
    SessionCenter f381a;

    /* renamed from: b, reason: collision with root package name */
    d f382b;
    volatile Session d;
    private String h;
    private String i;
    private SessionInfo j;
    private volatile Future k;

    /* renamed from: c, reason: collision with root package name */
    volatile boolean f383c = false;
    volatile boolean e = false;
    HashMap<SessionGetCallback, c> f = new HashMap<>();
    SessionConnStat g = null;
    private Object l = new Object();

    /* JADX INFO: Access modifiers changed from: private */
    interface IConnCb {
        void onDisConnect(Session session, long j, int i);

        void onFailed(Session session, long j, int i, int i2);

        void onSuccess(Session session, long j);
    }

    SessionRequest(String str, SessionCenter sessionCenter) {
        this.h = str;
        this.i = this.h.substring(this.h.indexOf(HttpConstant.SCHEME_SPLIT) + 3);
        this.f381a = sessionCenter;
        this.j = sessionCenter.g.b(this.i);
        this.f382b = sessionCenter.e;
    }

    protected String a() {
        return this.h;
    }

    void a(boolean z) {
        this.f383c = z;
        if (z) {
            return;
        }
        if (this.k != null) {
            this.k.cancel(true);
            this.k = null;
        }
        this.d = null;
    }

    private class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        String f387a;

        b(String str) {
            this.f387a = null;
            this.f387a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SessionRequest.this.f383c) {
                ALog.e("awcn.SessionRequest", "Connecting timeout!!! reset status!", this.f387a, new Object[0]);
                SessionRequest.this.g.ret = 2;
                SessionRequest.this.g.totalTime = System.currentTimeMillis() - SessionRequest.this.g.start;
                if (SessionRequest.this.d != null) {
                    SessionRequest.this.d.u = false;
                    SessionRequest.this.d.close();
                    SessionRequest.this.g.syncValueFromSession(SessionRequest.this.d);
                }
                AppMonitor.getInstance().commitStat(SessionRequest.this.g);
                SessionRequest.this.a(false);
            }
        }
    }

    protected class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        SessionGetCallback f389a;

        /* renamed from: b, reason: collision with root package name */
        AtomicBoolean f390b = new AtomicBoolean(false);

        protected c(SessionGetCallback sessionGetCallback) {
            this.f389a = null;
            this.f389a = sessionGetCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f390b.compareAndSet(false, true)) {
                ALog.e("awcn.SessionRequest", "get session timeout", null, new Object[0]);
                synchronized (SessionRequest.this.f) {
                    SessionRequest.this.f.remove(this.f389a);
                }
                this.f389a.onSessionGetFail();
            }
        }
    }

    protected synchronized void a(Context context, int i, String str, SessionGetCallback sessionGetCallback, long j) {
        Session sessionA = this.f382b.a(this, i);
        if (sessionA != null) {
            ALog.d("awcn.SessionRequest", "Available Session exist!!!", str, new Object[0]);
            if (sessionGetCallback != null) {
                sessionGetCallback.onSessionGetSuccess(sessionA);
            }
            return;
        }
        if (TextUtils.isEmpty(str)) {
            str = anet.channel.util.i.a(null);
        }
        ALog.d("awcn.SessionRequest", "SessionRequest start", str, com.alipay.sdk.cons.c.f, this.h, "type", Integer.valueOf(i));
        if (this.f383c) {
            ALog.d("awcn.SessionRequest", "session connecting", str, com.alipay.sdk.cons.c.f, a());
            if (sessionGetCallback != null) {
                if (b() == i) {
                    c cVar = new c(sessionGetCallback);
                    synchronized (this.f) {
                        this.f.put(sessionGetCallback, cVar);
                    }
                    ThreadPoolExecutorFactory.submitScheduledTask(cVar, j, TimeUnit.MILLISECONDS);
                } else {
                    sessionGetCallback.onSessionGetFail();
                }
            }
            return;
        }
        a(true);
        this.k = ThreadPoolExecutorFactory.submitScheduledTask(new b(str), 45L, TimeUnit.SECONDS);
        this.g = new SessionConnStat();
        this.g.start = System.currentTimeMillis();
        if (!NetworkStatusHelper.isConnected()) {
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.SessionRequest", "network is not available, can't create session", str, "isConnected", Boolean.valueOf(NetworkStatusHelper.isConnected()));
            }
            c();
            throw new RuntimeException("no network");
        }
        List<IConnStrategy> listA = a(i, str);
        if (listA.isEmpty()) {
            ALog.i("awcn.SessionRequest", "no avalible strategy, can't create session", str, com.alipay.sdk.cons.c.f, this.h, "type", Integer.valueOf(i));
            c();
            throw new NoAvailStrategyException("no avalible strategy");
        }
        List<anet.channel.entity.a> listA2 = a(listA, str);
        try {
            anet.channel.entity.a aVarRemove = listA2.remove(0);
            a(context, aVarRemove, new a(context, listA2, aVarRemove), aVarRemove.h());
            if (sessionGetCallback != null) {
                c cVar2 = new c(sessionGetCallback);
                synchronized (this.f) {
                    this.f.put(sessionGetCallback, cVar2);
                }
                ThreadPoolExecutorFactory.submitScheduledTask(cVar2, j, TimeUnit.MILLISECONDS);
            }
        } catch (Throwable unused) {
            c();
        }
        return;
    }

    class a implements IConnCb {

        /* renamed from: a, reason: collision with root package name */
        boolean f384a = false;

        /* renamed from: c, reason: collision with root package name */
        private Context f386c;
        private List<anet.channel.entity.a> d;
        private anet.channel.entity.a e;

        a(Context context, List<anet.channel.entity.a> list, anet.channel.entity.a aVar) {
            this.f386c = context;
            this.d = list;
            this.e = aVar;
        }

        @Override // anet.channel.SessionRequest.IConnCb
        public void onFailed(Session session, long j, int i, int i2) {
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.SessionRequest", "Connect failed", this.e.h(), "session", session, com.alipay.sdk.cons.c.f, SessionRequest.this.a(), "isHandleFinish", Boolean.valueOf(this.f384a));
            }
            if (SessionRequest.this.e) {
                SessionRequest.this.e = false;
                return;
            }
            if (this.f384a) {
                return;
            }
            this.f384a = true;
            SessionRequest.this.f382b.b(SessionRequest.this, session);
            if (session.u && NetworkStatusHelper.isConnected() && !this.d.isEmpty()) {
                if (ALog.isPrintLog(1)) {
                    ALog.d("awcn.SessionRequest", "use next connInfo to create session", this.e.h(), com.alipay.sdk.cons.c.f, SessionRequest.this.a());
                }
                if (this.e.f430b == this.e.f431c && (i2 == -2003 || i2 == -2410)) {
                    ListIterator<anet.channel.entity.a> listIterator = this.d.listIterator();
                    while (listIterator.hasNext()) {
                        if (session.getIp().equals(listIterator.next().f429a.getIp())) {
                            listIterator.remove();
                        }
                    }
                }
                if (anet.channel.strategy.utils.c.b(session.getIp())) {
                    ListIterator<anet.channel.entity.a> listIterator2 = this.d.listIterator();
                    while (listIterator2.hasNext()) {
                        if (anet.channel.strategy.utils.c.b(listIterator2.next().f429a.getIp())) {
                            listIterator2.remove();
                        }
                    }
                }
                if (!this.d.isEmpty()) {
                    anet.channel.entity.a aVarRemove = this.d.remove(0);
                    SessionRequest.this.a(this.f386c, aVarRemove, SessionRequest.this.new a(this.f386c, this.d, aVarRemove), aVarRemove.h());
                    return;
                } else {
                    SessionRequest.this.c();
                    SessionRequest.this.a(session, i, i2);
                    return;
                }
            }
            SessionRequest.this.c();
            SessionRequest.this.a(session, i, i2);
            synchronized (SessionRequest.this.f) {
                for (Map.Entry<SessionGetCallback, c> entry : SessionRequest.this.f.entrySet()) {
                    c value = entry.getValue();
                    if (value.f390b.compareAndSet(false, true)) {
                        ThreadPoolExecutorFactory.removeScheduleTask(value);
                        entry.getKey().onSessionGetFail();
                    }
                }
                SessionRequest.this.f.clear();
            }
        }

        @Override // anet.channel.SessionRequest.IConnCb
        public void onSuccess(Session session, long j) {
            ALog.d("awcn.SessionRequest", "Connect Success", this.e.h(), "session", session, com.alipay.sdk.cons.c.f, SessionRequest.this.a());
            try {
                if (SessionRequest.this.e) {
                    SessionRequest.this.e = false;
                    session.close(false);
                    return;
                }
                SessionRequest.this.f382b.a(SessionRequest.this, session);
                SessionRequest.this.a(session);
                synchronized (SessionRequest.this.f) {
                    for (Map.Entry<SessionGetCallback, c> entry : SessionRequest.this.f.entrySet()) {
                        c value = entry.getValue();
                        if (value.f390b.compareAndSet(false, true)) {
                            ThreadPoolExecutorFactory.removeScheduleTask(value);
                            entry.getKey().onSessionGetSuccess(session);
                        }
                    }
                    SessionRequest.this.f.clear();
                }
            } catch (Exception e) {
                ALog.e("awcn.SessionRequest", "[onSuccess]:", this.e.h(), e, new Object[0]);
            } finally {
                SessionRequest.this.c();
            }
        }

        @Override // anet.channel.SessionRequest.IConnCb
        public void onDisConnect(Session session, long j, int i) {
            boolean zIsAppBackground = GlobalAppRuntimeInfo.isAppBackground();
            ALog.d("awcn.SessionRequest", "Connect Disconnect", this.e.h(), "session", session, com.alipay.sdk.cons.c.f, SessionRequest.this.a(), "appIsBg", Boolean.valueOf(zIsAppBackground), "isHandleFinish", Boolean.valueOf(this.f384a));
            SessionRequest.this.f382b.b(SessionRequest.this, session);
            if (this.f384a) {
                return;
            }
            this.f384a = true;
            if (session.t) {
                if (zIsAppBackground) {
                    ALog.e("awcn.SessionRequest", "[onDisConnect]app background, don't Recreate", this.e.h(), "session", session);
                } else {
                    if (!NetworkStatusHelper.isConnected()) {
                        ALog.e("awcn.SessionRequest", "[onDisConnect]no network, don't Recreate", this.e.h(), "session", session);
                        return;
                    }
                    try {
                        ALog.d("awcn.SessionRequest", "session disconnected, try to recreate session", this.e.h(), new Object[0]);
                        ThreadPoolExecutorFactory.submitScheduledTask(new g(this, session), (long) (Math.random() * 10.0d * 1000.0d), TimeUnit.MILLISECONDS);
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }

    void a(Session session) {
        AlarmObject alarmObject = new AlarmObject();
        alarmObject.module = "networkPrefer";
        alarmObject.modulePoint = "policy";
        alarmObject.arg = this.h;
        alarmObject.isSuccess = true;
        AppMonitor.getInstance().commitAlarm(alarmObject);
        this.g.syncValueFromSession(session);
        this.g.ret = 1;
        this.g.totalTime = System.currentTimeMillis() - this.g.start;
        AppMonitor.getInstance().commitStat(this.g);
    }

    void a(Session session, int i, int i2) {
        if (256 != i || i2 == -2613 || i2 == -2601) {
            return;
        }
        AlarmObject alarmObject = new AlarmObject();
        alarmObject.module = "networkPrefer";
        alarmObject.modulePoint = "policy";
        alarmObject.arg = this.h;
        alarmObject.errorCode = String.valueOf(i2);
        alarmObject.isSuccess = false;
        AppMonitor.getInstance().commitAlarm(alarmObject);
        this.g.ret = 0;
        this.g.appendErrorTrace(i2);
        this.g.errorCode = String.valueOf(i2);
        this.g.totalTime = System.currentTimeMillis() - this.g.start;
        this.g.syncValueFromSession(session);
        AppMonitor.getInstance().commitStat(this.g);
    }

    private List<IConnStrategy> a(int i, String str) {
        List<IConnStrategy> connStrategyListByHost;
        HttpUrl httpUrl;
        try {
            httpUrl = HttpUrl.parse(a());
        } catch (Throwable th) {
            th = th;
            connStrategyListByHost = Collections.EMPTY_LIST;
        }
        if (httpUrl == null) {
            return Collections.EMPTY_LIST;
        }
        connStrategyListByHost = StrategyCenter.getInstance().getConnStrategyListByHost(httpUrl.host());
        try {
            if (!connStrategyListByHost.isEmpty()) {
                boolean zEqualsIgnoreCase = "https".equalsIgnoreCase(httpUrl.scheme());
                boolean zB = anet.channel.util.c.b();
                ListIterator<IConnStrategy> listIterator = connStrategyListByHost.listIterator();
                while (listIterator.hasNext()) {
                    IConnStrategy next = listIterator.next();
                    ConnType connTypeValueOf = ConnType.valueOf(next.getProtocol());
                    if (connTypeValueOf != null) {
                        if (connTypeValueOf.isSSL() != zEqualsIgnoreCase || (i != anet.channel.entity.c.f437c && connTypeValueOf.getType() != i)) {
                            listIterator.remove();
                        }
                        if (zB && anet.channel.strategy.utils.c.b(next.getIp())) {
                            listIterator.remove();
                        }
                    }
                }
            }
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.SessionRequest", "[getAvailStrategy]", str, "strategies", connStrategyListByHost);
            }
        } catch (Throwable th2) {
            th = th2;
            ALog.e("awcn.SessionRequest", "", str, th, new Object[0]);
            return connStrategyListByHost;
        }
        return connStrategyListByHost;
    }

    private List<anet.channel.entity.a> a(List<IConnStrategy> list, String str) {
        if (list.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        while (i < list.size()) {
            IConnStrategy iConnStrategy = list.get(i);
            int retryTimes = iConnStrategy.getRetryTimes();
            int i3 = i2;
            for (int i4 = 0; i4 <= retryTimes; i4++) {
                i3++;
                anet.channel.entity.a aVar = new anet.channel.entity.a(a(), str + "_" + i3, iConnStrategy);
                aVar.f430b = i4;
                aVar.f431c = retryTimes;
                arrayList.add(aVar);
            }
            i++;
            i2 = i3;
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, anet.channel.entity.a aVar, IConnCb iConnCb, String str) {
        ConnType connTypeC = aVar.c();
        if (context != null && !connTypeC.isHttpType()) {
            TnetSpdySession tnetSpdySession = new TnetSpdySession(context, aVar);
            tnetSpdySession.initConfig(this.f381a.d);
            tnetSpdySession.initSessionInfo(this.j);
            tnetSpdySession.setTnetPublicKey(this.f381a.g.c(this.i));
            this.d = tnetSpdySession;
        } else {
            this.d = new anet.channel.session.d(context, aVar);
        }
        ALog.i("awcn.SessionRequest", "create connection...", str, HttpConstant.HOST, a(), "Type", aVar.c(), "IP", aVar.a(), "Port", Integer.valueOf(aVar.b()), "heartbeat", Integer.valueOf(aVar.g()), "session", this.d);
        a(this.d, iConnCb, System.currentTimeMillis());
        this.d.connect();
        this.g.retryTimes++;
        this.g.startConnect = System.currentTimeMillis();
        if (this.g.retryTimes == 0) {
            this.g.putExtra("firstIp", aVar.a());
        }
    }

    private void a(Session session, IConnCb iConnCb, long j) {
        if (iConnCb == null) {
            return;
        }
        session.registerEventcb(EventType.ALL, new e(this, iConnCb, j));
        session.registerEventcb(1792, new f(this, session));
    }

    protected void b(boolean z) {
        ALog.d("awcn.SessionRequest", "closeSessions", this.f381a.f378c, com.alipay.sdk.cons.c.f, this.h, "autoCreate", Boolean.valueOf(z));
        if (!z && this.d != null) {
            this.d.u = false;
            this.d.close(false);
        }
        List<Session> listA = this.f382b.a(this);
        if (listA != null) {
            for (Session session : listA) {
                if (session != null) {
                    session.close(z);
                }
            }
        }
    }

    protected void a(String str) {
        ALog.d("awcn.SessionRequest", "reCreateSession", str, com.alipay.sdk.cons.c.f, this.h);
        b(true);
    }

    protected void a(long j) throws InterruptedException, TimeoutException {
        ALog.d("awcn.SessionRequest", "[await]", null, "timeoutMs", Long.valueOf(j));
        if (j <= 0) {
            return;
        }
        synchronized (this.l) {
            long jCurrentTimeMillis = System.currentTimeMillis() + j;
            while (this.f383c) {
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                if (jCurrentTimeMillis2 >= jCurrentTimeMillis) {
                    break;
                } else {
                    this.l.wait(jCurrentTimeMillis - jCurrentTimeMillis2);
                }
            }
            if (this.f383c) {
                throw new TimeoutException();
            }
        }
    }

    protected int b() {
        Session session = this.d;
        if (session != null) {
            return session.j.getType();
        }
        return -1;
    }

    void c() {
        a(false);
        synchronized (this.l) {
            this.l.notifyAll();
        }
    }

    void a(Session session, int i, String str) {
        Context context = GlobalAppRuntimeInfo.getContext();
        if (context == null || this.j == null || !this.j.isAccs) {
            return;
        }
        try {
            Intent intent = new Intent("com.taobao.accs.intent.action.RECEIVE");
            intent.setPackage(context.getPackageName());
            intent.setClassName(context, "com.taobao.accs.data.MsgDistributeService");
            intent.putExtra("command", 103);
            intent.putExtra(com.alipay.sdk.cons.c.f, session.getHost());
            intent.putExtra("is_center_host", true);
            boolean zIsAvailable = session.isAvailable();
            if (!zIsAvailable) {
                intent.putExtra(AccountMonitorConstants.CommonParameter.ERROR, i);
                intent.putExtra("errorDetail", str);
            }
            intent.putExtra("connect_avail", zIsAvailable);
            intent.putExtra("type_inapp", true);
            context.startService(intent);
        } catch (Throwable th) {
            ALog.e("awcn.SessionRequest", "sendConnectInfoBroadCastToAccs", null, th, new Object[0]);
        }
    }
}
