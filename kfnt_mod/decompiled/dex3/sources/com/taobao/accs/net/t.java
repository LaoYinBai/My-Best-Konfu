package com.taobao.accs.net;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.strategy.IConnStrategy;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.android.downloadlib.applink.AppLinkMonitor;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import com.tachikoma.core.utility.UriUtil;
import com.taobao.accs.AccsClientConfig;
import com.taobao.accs.ErrorCode;
import com.taobao.accs.common.Constants;
import com.taobao.accs.common.ThreadPoolExecutorFactory;
import com.taobao.accs.data.Message;
import com.taobao.accs.ut.monitor.SessionMonitor;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.UByte;
import org.android.spdy.RequestPriority;
import org.android.spdy.SessionCb;
import org.android.spdy.SessionInfo;
import org.android.spdy.SpdyAgent;
import org.android.spdy.SpdyByteArray;
import org.android.spdy.SpdyDataProvider;
import org.android.spdy.SpdyRequest;
import org.android.spdy.SpdySession;
import org.android.spdy.SpdySessionKind;
import org.android.spdy.SpdyVersion;
import org.android.spdy.Spdycb;
import org.android.spdy.SuperviseConnectInfo;
import org.android.spdy.SuperviseData;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class t extends b implements SessionCb, Spdycb {
    private SpdySession A;
    private Object B;
    private long C;
    private long D;
    private long E;
    private long F;
    private int G;
    private String H;
    private SessionMonitor I;
    private com.taobao.accs.ut.a.c J;
    private boolean K;
    private String L;
    private boolean M;
    private g N;
    private String O;
    protected ScheduledFuture<?> o;
    protected String p;
    protected int q;
    protected String r;
    protected int s;
    private int t;
    private LinkedList<Message> u;
    private a v;
    private boolean w;
    private String x;
    private String y;
    private SpdyAgent z;

    @Override // com.taobao.accs.net.b
    protected boolean h() {
        return false;
    }

    public t(Context context, int i, String str) {
        super(context, i, str);
        this.t = 3;
        this.u = new LinkedList<>();
        this.w = true;
        this.z = null;
        this.A = null;
        this.B = new Object();
        this.G = -1;
        this.H = null;
        this.K = false;
        this.L = "";
        this.M = false;
        this.N = new g(o());
        t();
    }

    @Override // com.taobao.accs.net.b
    public void a() {
        this.w = true;
        ALog.d(d(), PointCategory.START, new Object[0]);
        a(this.d);
        if (this.v == null) {
            ALog.i(d(), "start thread", new Object[0]);
            this.v = new a("NetworkThread_" + this.m);
            this.v.setPriority(2);
            this.v.start();
        }
        a(false, false);
    }

    @Override // com.taobao.accs.net.b
    public void a(Message message, boolean z) {
        if (!this.w || message == null) {
            ALog.e(d(), "not running or msg null! " + this.w, new Object[0]);
            return;
        }
        try {
            if (ThreadPoolExecutorFactory.getScheduledExecutor().getQueue().size() > 1000) {
                throw new RejectedExecutionException("accs");
            }
            ScheduledFuture<?> scheduledFutureSchedule = ThreadPoolExecutorFactory.getScheduledExecutor().schedule(new u(this, message, z), message.delyTime, TimeUnit.MILLISECONDS);
            if (message.getType() == 1 && message.cunstomDataId != null) {
                if (message.isControlFrame()) {
                    a(message.cunstomDataId);
                }
                this.e.a.put(message.cunstomDataId, scheduledFutureSchedule);
            }
            if (message.getNetPermanceMonitor() != null) {
                message.getNetPermanceMonitor().setDeviceId(UtilityImpl.l(this.d));
                message.getNetPermanceMonitor().setConnType(this.c);
                message.getNetPermanceMonitor().onEnterQueueData();
            }
        } catch (RejectedExecutionException unused) {
            this.e.a(message, ErrorCode.MESSAGE_QUEUE_FULL);
            ALog.e(d(), "send queue full count:" + ThreadPoolExecutorFactory.getScheduledExecutor().getQueue().size(), new Object[0]);
        } catch (Throwable th) {
            this.e.a(message, -8);
            ALog.e(d(), "send error", th, new Object[0]);
        }
    }

    @Override // com.taobao.accs.net.b
    public void e() {
        super.e();
        this.w = false;
        ThreadPoolExecutorFactory.getScheduledExecutor().execute(new v(this));
        ALog.e(d(), "shut down", new Object[0]);
    }

    @Override // com.taobao.accs.net.b
    public void a(boolean z, boolean z2) {
        ALog.d(d(), "try ping, force:" + z, new Object[0]);
        if (this.c == 1) {
            ALog.d(d(), "INAPP, skip", new Object[0]);
        } else {
            b(Message.BuildPing(z, (int) (z2 ? Math.random() * 10.0d * 1000.0d : 0.0d)), z);
        }
    }

    public void n() {
        ALog.e(d(), " force close!", new Object[0]);
        try {
            this.A.closeSession();
            this.I.setCloseType(1);
        } catch (Exception unused) {
        }
        d(3);
    }

    @Override // com.taobao.accs.net.b
    public com.taobao.accs.ut.a.c c() {
        if (this.J == null) {
            this.J = new com.taobao.accs.ut.a.c();
        }
        this.J.b = this.c;
        this.J.d = this.u.size();
        this.J.i = UtilityImpl.k(this.d);
        this.J.f = this.L;
        this.J.a = this.t;
        this.J.c = this.I != null && this.I.getRet();
        this.J.j = p();
        this.J.e = this.e != null ? this.e.d() : 0;
        this.J.g = this.y;
        return this.J;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Message message) {
        if (message.command == null || this.u.size() == 0) {
            return;
        }
        for (int size = this.u.size() - 1; size >= 0; size--) {
            Message message2 = this.u.get(size);
            if (message2 != null && message2.command != null && message2.getPackageName().equals(message.getPackageName())) {
                switch (message.command.intValue()) {
                    case 1:
                    case 2:
                        if (message2.command.intValue() == 1 || message2.command.intValue() == 2) {
                            this.u.remove(size);
                            break;
                        }
                        break;
                    case 3:
                    case 4:
                        if (message2.command.intValue() == 3 || message2.command.intValue() == 4) {
                            this.u.remove(size);
                            break;
                        }
                        break;
                    case 5:
                    case 6:
                        if (message2.command.intValue() == 5 || message2.command.intValue() == 6) {
                            this.u.remove(size);
                            break;
                        }
                        break;
                }
                ALog.d(d(), "clearRepeatControlCommand message:" + message2.command + "/" + message2.getPackageName(), new Object[0]);
            }
        }
        if (this.e != null) {
            this.e.b(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        SessionInfo sessionInfo;
        if (this.t == 2 || this.t == 1) {
            return;
        }
        if (this.N == null) {
            this.N = new g(o());
        }
        List<IConnStrategy> listA = this.N.a(o());
        int port = Constants.PORT;
        if (listA != null && listA.size() > 0) {
            for (IConnStrategy iConnStrategy : listA) {
                if (iConnStrategy != null) {
                    ALog.e(d(), BaseMonitor.ALARM_POINT_CONNECT, "ip", iConnStrategy.getIp(), "port", Integer.valueOf(iConnStrategy.getPort()));
                }
            }
            if (this.M) {
                this.N.b();
                this.M = false;
            }
            IConnStrategy iConnStrategyA = this.N.a();
            this.p = iConnStrategyA == null ? o() : iConnStrategyA.getIp();
            if (iConnStrategyA != null) {
                port = iConnStrategyA.getPort();
            }
            this.q = port;
            com.taobao.accs.utl.k.a("accs", BaseMonitor.COUNT_POINT_DNS, "httpdns", 0.0d);
            ALog.e(d(), "connect from amdc succ", "ip", this.p, "port", Integer.valueOf(this.q), "originPos", Integer.valueOf(this.N.c()));
        } else {
            if (str != null) {
                this.p = str;
            } else {
                this.p = o();
            }
            if (System.currentTimeMillis() % 2 == 0) {
                port = 80;
            }
            this.q = port;
            com.taobao.accs.utl.k.a("accs", BaseMonitor.COUNT_POINT_DNS, "localdns", 0.0d);
            ALog.i(d(), "connect get ip from amdc fail!!", new Object[0]);
        }
        this.x = UriUtil.HTTPS_PREFIX + this.p + com.xiaomi.mipush.sdk.Constants.COLON_SEPARATOR + this.q + "/accs/";
        ALog.e(d(), BaseMonitor.ALARM_POINT_CONNECT, "URL", this.x);
        this.O = String.valueOf(System.currentTimeMillis());
        if (this.I != null) {
            AppMonitor.getInstance().commitStat(this.I);
        }
        this.I = new SessionMonitor();
        this.I.setConnectType(this.c == 0 ? "service" : "inapp");
        if (this.z != null) {
            try {
                this.E = System.currentTimeMillis();
                this.F = System.nanoTime();
                this.r = UtilityImpl.a(this.d);
                this.s = UtilityImpl.b(this.d);
                this.C = System.currentTimeMillis();
                this.I.onStartConnect();
                d(2);
                synchronized (this.B) {
                    try {
                        try {
                            if (!TextUtils.isEmpty(this.r) && this.s >= 0 && this.K) {
                                ALog.e(d(), BaseMonitor.ALARM_POINT_CONNECT, "proxy", this.r, "port", Integer.valueOf(this.s));
                                sessionInfo = new SessionInfo(this.p, this.q, o() + "_" + this.b, this.r, this.s, this.O, this, 4226);
                                this.L = this.r + com.xiaomi.mipush.sdk.Constants.COLON_SEPARATOR + this.s;
                            } else {
                                ALog.e(d(), "connect normal", new Object[0]);
                                sessionInfo = new SessionInfo(this.p, this.q, o() + "_" + this.b, (String) null, 0, this.O, this, 4226);
                                this.L = "";
                            }
                            sessionInfo.setPubKeySeqNum(q());
                            sessionInfo.setConnectionTimeoutMs(b.ACCS_RECEIVE_TIMEOUT);
                            this.A = this.z.createSession(sessionInfo);
                            this.I.connection_stop_date = 0L;
                            this.B.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        this.K = false;
                    }
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    private int q() {
        boolean zL = l();
        if (AccsClientConfig.mEnv == 2) {
            return 0;
        }
        int channelPubKey = this.i.getChannelPubKey();
        if (channelPubKey <= 0) {
            return zL ? 4 : 3;
        }
        ALog.i(d(), "getPublicKeyType use custom pub key", "pubKey", Integer.valueOf(channelPubKey));
        return channelPubKey;
    }

    private void r() {
        if (this.A == null) {
            d(3);
            return;
        }
        try {
            String strEncode = URLEncoder.encode(UtilityImpl.l(this.d));
            String strA = UtilityImpl.a(this.d, i(), this.i.getAppSecret(), UtilityImpl.l(this.d), this.m);
            String strC = c(this.x);
            ALog.e(d(), BaseMonitor.ALARM_POINT_AUTH, "url", strC);
            this.y = strC;
            if (!a(strEncode, i(), strA)) {
                ALog.e(d(), "auth param error!", new Object[0]);
                e(-6);
            } else {
                new URL(strC);
                SpdyRequest spdyRequest = new SpdyRequest(new URL(strC), "GET", RequestPriority.DEFAULT_PRIORITY, 80000, b.ACCS_RECEIVE_TIMEOUT);
                spdyRequest.setDomain(o());
                this.A.submitRequest(spdyRequest, new SpdyDataProvider((byte[]) null), o(), this);
            }
        } catch (Throwable th) {
            ALog.e(d(), "auth exception ", th, new Object[0]);
            e(-7);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(java.lang.String r12, java.lang.String r13, java.lang.String r14) {
        /*
            r11 = this;
            android.content.Context r0 = r11.d
            int r0 = com.taobao.accs.utl.w.b(r0)
            r1 = 2
            r2 = 1
            if (r0 != r1) goto Lb
            return r2
        Lb:
            boolean r0 = android.text.TextUtils.isEmpty(r12)
            r3 = 0
            if (r0 != 0) goto L1e
            boolean r0 = android.text.TextUtils.isEmpty(r13)
            if (r0 != 0) goto L1e
            boolean r0 = android.text.TextUtils.isEmpty(r14)
            if (r0 == 0) goto Lb4
        L1e:
            r0 = 3
            r11.d(r0)
            boolean r12 = android.text.TextUtils.isEmpty(r12)
            if (r12 == 0) goto L2a
        L28:
            r0 = 1
            goto L38
        L2a:
            boolean r12 = android.text.TextUtils.isEmpty(r13)
            if (r12 == 0) goto L32
            r0 = 2
            goto L38
        L32:
            boolean r12 = android.text.TextUtils.isEmpty(r14)
            if (r12 == 0) goto L28
        L38:
            com.taobao.accs.ut.monitor.SessionMonitor r12 = r11.I
            r12.setFailReason(r0)
            com.taobao.accs.ut.monitor.SessionMonitor r12 = r11.I
            r12.onConnectStop()
            int r12 = r11.c
            if (r12 != 0) goto L49
            java.lang.String r12 = "service"
            goto L4b
        L49:
            java.lang.String r12 = "inapp"
        L4b:
            com.taobao.accs.net.t$a r13 = r11.v
            if (r13 == 0) goto L54
            com.taobao.accs.net.t$a r13 = r11.v
            int r13 = r13.a
            goto L55
        L54:
            r13 = 0
        L55:
            com.taobao.accs.utl.UTMini r4 = com.taobao.accs.utl.UTMini.getInstance()
            r5 = 66001(0x101d1, float:9.2487E-41)
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r6 = "DISCONNECT "
            r14.append(r6)
            r14.append(r12)
            java.lang.String r6 = r14.toString()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r0)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r13)
            int r12 = com.taobao.accs.common.Constants.SDK_VERSION_CODE
            java.lang.Integer r9 = java.lang.Integer.valueOf(r12)
            java.lang.String[] r10 = new java.lang.String[r1]
            java.lang.String r12 = r11.y
            r10[r3] = r12
            java.lang.String r12 = r11.L
            r10[r2] = r12
            r4.commitEvent(r5, r6, r7, r8, r9, r10)
            java.lang.String r12 = "accs"
            java.lang.String r14 = "connect"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "retrytimes:"
            r1.append(r2)
            r1.append(r13)
            java.lang.String r13 = r1.toString()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = ""
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = ""
            com.taobao.accs.utl.k.a(r12, r14, r13, r0, r1)
            r2 = 0
        Lb4:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.net.t.a(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void s() {
        if (this.c == 1) {
            return;
        }
        this.C = System.currentTimeMillis();
        this.D = System.nanoTime();
        f.a(this.d).a();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private synchronized void d(int i) {
        ALog.e(d(), "notifyStatus start", "status", a(i));
        if (i == this.t) {
            ALog.i(d(), "ignore notifyStatus", new Object[0]);
            return;
        }
        this.t = i;
        switch (i) {
            case 1:
                f.a(this.d).f();
                s();
                if (this.o != null) {
                    this.o.cancel(true);
                }
                synchronized (this.B) {
                    try {
                        this.B.notifyAll();
                    } catch (Exception unused) {
                    }
                }
                synchronized (this.u) {
                    try {
                        this.u.notifyAll();
                    } catch (Exception unused2) {
                    }
                }
                ALog.i(d(), "notifyStatus end", "status", a(i));
                return;
            case 2:
                if (this.o != null) {
                    this.o.cancel(true);
                }
                ThreadPoolExecutorFactory.getScheduledExecutor().schedule(new w(this, this.O), 120000L, TimeUnit.MILLISECONDS);
                ALog.i(d(), "notifyStatus end", "status", a(i));
                return;
            case 3:
                s();
                f.a(this.d).d();
                synchronized (this.B) {
                    try {
                        this.B.notifyAll();
                    } catch (Exception unused3) {
                    }
                }
                this.e.a(-10);
                a(false, true);
                ALog.i(d(), "notifyStatus end", "status", a(i));
                return;
            case 4:
                ALog.i(d(), "notifyStatus end", "status", a(i));
                return;
            default:
                ALog.i(d(), "notifyStatus end", "status", a(i));
                return;
        }
    }

    public String o() {
        String channelHost = this.i.getChannelHost();
        ALog.i(d(), "getChannelHost", Constants.KEY_HOST, channelHost);
        return channelHost == null ? "" : channelHost;
    }

    private void t() {
        try {
            SpdyAgent.enableDebug = true;
            this.z = SpdyAgent.getInstance(this.d, SpdyVersion.SPDY3, SpdySessionKind.NONE_SESSION);
            if (SpdyAgent.checkLoadSucc()) {
                com.taobao.accs.utl.q.a();
                if (!l()) {
                    this.z.setAccsSslCallback(new x(this));
                }
                if (com.taobao.accs.utl.t.a(false)) {
                    return;
                }
                String str = this.c == 0 ? "service" : "inapp";
                ALog.d(d(), "into--[setTnetLogPath]", new Object[0]);
                String strC = UtilityImpl.c(this.d, str);
                ALog.d(d(), "config tnet log path:" + strC, new Object[0]);
                if (TextUtils.isEmpty(strC)) {
                    return;
                }
                this.z.configLogFile(strC, UtilityImpl.TNET_FILE_SIZE, 5);
                return;
            }
            ALog.e(d(), "initClient", new Object[0]);
            this.z = null;
            com.taobao.accs.utl.q.b();
        } catch (Throwable th) {
            ALog.e(d(), "initClient", th, new Object[0]);
        }
    }

    private class a extends Thread {
        public int a;
        long b;
        private final String d;

        public a(String str) {
            super(str);
            this.d = getName();
            this.a = 0;
        }

        private void a(boolean z) {
            if (t.this.t == 1) {
                if (t.this.t != 1 || System.currentTimeMillis() - this.b <= AppLinkMonitor.DELAY_TIME) {
                    return;
                }
                this.a = 0;
                return;
            }
            ALog.d(t.this.d(), "tryConnect", DBDefinition.FORCE, Boolean.valueOf(z));
            if (!UtilityImpl.k(t.this.d)) {
                ALog.e(this.d, "Network not available", new Object[0]);
                return;
            }
            if (z) {
                this.a = 0;
            }
            ALog.i(this.d, "tryConnect", DBDefinition.FORCE, Boolean.valueOf(z), "failTimes", Integer.valueOf(this.a));
            if (t.this.t == 1 || this.a < 4) {
                if (t.this.t != 1) {
                    if (t.this.c == 1 && this.a == 0) {
                        ALog.i(this.d, "tryConnect in app, no sleep", new Object[0]);
                    } else {
                        ALog.i(this.d, "tryConnect, need sleep", new Object[0]);
                        try {
                            sleep(AppLinkMonitor.DELAY_TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    t.this.L = "";
                    if (this.a == 3) {
                        t.this.N.b(t.this.o());
                    }
                    t.this.d((String) null);
                    t.this.I.setRetryTimes(this.a);
                    if (t.this.t != 1) {
                        this.a++;
                        ALog.e(this.d, "try connect fail, ready for reconnect", new Object[0]);
                        a(false);
                        return;
                    }
                    this.b = System.currentTimeMillis();
                    return;
                }
                return;
            }
            t.this.K = true;
            ALog.e(this.d, "tryConnect fail", "maxTimes", 4);
        }

        /* JADX WARN: Removed duplicated region for block: B:170:0x0463 A[Catch: Throwable -> 0x04df, TRY_ENTER, TryCatch #2 {Throwable -> 0x04df, blocks: (B:170:0x0463, B:172:0x0470, B:173:0x047b, B:174:0x0481, B:194:0x04de, B:197:0x04e2, B:198:0x04f1, B:205:0x0500, B:175:0x0482, B:177:0x048f, B:179:0x049d, B:181:0x04a1, B:183:0x04a9, B:185:0x04b1, B:187:0x04c4, B:188:0x04c7, B:189:0x04d9, B:199:0x04f2, B:200:0x04fb), top: B:216:0x0461, inners: #12, #13 }] */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0063 A[Catch: all -> 0x050b, TryCatch #4 {, blocks: (B:7:0x0027, B:9:0x0033, B:16:0x004e, B:18:0x0063, B:20:0x0075, B:21:0x007c, B:13:0x0048, B:14:0x004b), top: B:217:0x0027, inners: #0 }] */
        /* JADX WARN: Removed duplicated region for block: B:197:0x04e2 A[Catch: Throwable -> 0x04df, TryCatch #2 {Throwable -> 0x04df, blocks: (B:170:0x0463, B:172:0x0470, B:173:0x047b, B:174:0x0481, B:194:0x04de, B:197:0x04e2, B:198:0x04f1, B:205:0x0500, B:175:0x0482, B:177:0x048f, B:179:0x049d, B:181:0x04a1, B:183:0x04a9, B:185:0x04b1, B:187:0x04c4, B:188:0x04c7, B:189:0x04d9, B:199:0x04f2, B:200:0x04fb), top: B:216:0x0461, inners: #12, #13 }] */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 1301
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.net.t.a.run():void");
        }
    }

    public boolean p() {
        return this.w;
    }

    public void spdySessionFailedError(SpdySession spdySession, int i, Object obj) {
        if (spdySession != null) {
            try {
                spdySession.cleanUp();
            } catch (Exception e) {
                ALog.e(d(), "session cleanUp has exception: " + e, new Object[0]);
            }
        }
        int i2 = this.v != null ? this.v.a : 0;
        ALog.e(d(), "spdySessionFailedError", "retryTimes", Integer.valueOf(i2), "errorId", Integer.valueOf(i));
        this.K = false;
        this.M = true;
        d(3);
        this.I.setFailReason(i);
        this.I.onConnectStop();
        String str = this.c == 0 ? "service" : "inapp";
        UTMini.getInstance().commitEvent(66001, "DISCONNECT " + str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(Constants.SDK_VERSION_CODE), this.y, this.L);
        com.taobao.accs.utl.k.a("accs", BaseMonitor.ALARM_POINT_CONNECT, "retrytimes:" + i2, i + "", "");
    }

    public void spdySessionConnectCB(SpdySession spdySession, SuperviseConnectInfo superviseConnectInfo) {
        this.G = superviseConnectInfo.connectTime;
        int i = superviseConnectInfo.handshakeTime;
        ALog.e(d(), "spdySessionConnectCB", "sessionConnectInterval", Integer.valueOf(this.G), "sslTime", Integer.valueOf(i), "reuse", Integer.valueOf(superviseConnectInfo.sessionTicketReused));
        r();
        this.I.setRet(true);
        this.I.onConnectStop();
        this.I.tcp_time = this.G;
        this.I.ssl_time = i;
        String str = this.c == 0 ? "service" : "inapp";
        UTMini.getInstance().commitEvent(66001, "CONNECTED " + str + " " + superviseConnectInfo.sessionTicketReused, String.valueOf(this.G), String.valueOf(i), Integer.valueOf(Constants.SDK_VERSION_CODE), String.valueOf(superviseConnectInfo.sessionTicketReused), this.y, this.L);
        com.taobao.accs.utl.k.a("accs", BaseMonitor.ALARM_POINT_CONNECT, "");
    }

    public void spdySessionCloseCallback(SpdySession spdySession, Object obj, SuperviseConnectInfo superviseConnectInfo, int i) {
        ALog.e(d(), "spdySessionCloseCallback", Constants.KEY_ERROR_CODE, Integer.valueOf(i));
        if (spdySession != null) {
            try {
                spdySession.cleanUp();
            } catch (Exception e) {
                ALog.e(d(), "session cleanUp has exception: " + e, new Object[0]);
            }
        }
        d(3);
        this.I.onCloseConnect();
        if (this.I.getConCloseDate() > 0 && this.I.getConStopDate() > 0) {
            this.I.getConCloseDate();
            this.I.getConStopDate();
        }
        this.I.setCloseReason(this.I.getCloseReason() + "tnet error:" + i);
        if (superviseConnectInfo != null) {
            this.I.live_time = superviseConnectInfo.keepalive_period_second;
        }
        AppMonitor.getInstance().commitStat(this.I);
        for (Message message : this.e.e()) {
            if (message.getNetPermanceMonitor() != null) {
                message.getNetPermanceMonitor().setFailReason("session close");
                AppMonitor.getInstance().commitStat(message.getNetPermanceMonitor());
            }
        }
        String str = this.c == 0 ? "service" : "inapp";
        ALog.d(d(), "spdySessionCloseCallback, conKeepTime:" + this.I.live_time + " connectType:" + str, new Object[0]);
        UTMini uTMini = UTMini.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append("DISCONNECT CLOSE ");
        sb.append(str);
        uTMini.commitEvent(66001, sb.toString(), Integer.valueOf(i), Long.valueOf(this.I.live_time), Integer.valueOf(Constants.SDK_VERSION_CODE), this.y, this.L);
    }

    public void spdyPingRecvCallback(SpdySession spdySession, long j, Object obj) {
        ALog.d(d(), "spdyPingRecvCallback uniId:" + j, new Object[0]);
        if (j < 0) {
            return;
        }
        this.e.b();
        f.a(this.d).e();
        f.a(this.d).a();
        this.I.onPingCBReceive();
        if (this.I.ping_rec_times % 2 == 0) {
            UtilityImpl.a(this.d, Constants.SP_KEY_SERVICE_END, System.currentTimeMillis());
        }
    }

    public void spdyCustomControlFrameRecvCallback(SpdySession spdySession, Object obj, int i, int i2, int i3, int i4, byte[] bArr) {
        s();
        ALog.e(d(), "onFrame", "type", Integer.valueOf(i2), "len", Integer.valueOf(bArr.length));
        StringBuilder sb = new StringBuilder();
        if (ALog.isPrintLog(ALog.Level.D) && bArr.length < 512) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            for (byte b : bArr) {
                sb.append(Integer.toHexString(b & UByte.MAX_VALUE));
                sb.append(" ");
            }
            ALog.d(d(), ((Object) sb) + " log time:" + (System.currentTimeMillis() - jCurrentTimeMillis), new Object[0]);
        }
        if (i2 == 200) {
            try {
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                this.e.a(bArr);
                com.taobao.accs.ut.a.d dVarG = this.e.g();
                if (dVarG != null) {
                    dVarG.c = String.valueOf(jCurrentTimeMillis2);
                    dVarG.g = this.c == 0 ? "service" : "inapp";
                    dVarG.a();
                }
            } catch (Throwable th) {
                ALog.e(d(), "onDataReceive ", th, new Object[0]);
                UTMini.getInstance().commitEvent(66001, "SERVICE_DATA_RECEIVE", UtilityImpl.a(th));
            }
            ALog.d(d(), "try handle msg", new Object[0]);
            g();
        } else {
            ALog.e(d(), "drop frame", "len", Integer.valueOf(bArr.length));
        }
        ALog.d(d(), "spdyCustomControlFrameRecvCallback", new Object[0]);
    }

    public void spdyStreamCloseCallback(SpdySession spdySession, long j, int i, Object obj, SuperviseData superviseData) {
        ALog.d(d(), "spdyStreamCloseCallback", new Object[0]);
        if (i != 0) {
            ALog.e(d(), "spdyStreamCloseCallback", "statusCode", Integer.valueOf(i));
            e(i);
        }
    }

    public void spdyRequestRecvCallback(SpdySession spdySession, long j, Object obj) {
        ALog.d(d(), "spdyRequestRecvCallback", new Object[0]);
    }

    public void spdyOnStreamResponse(SpdySession spdySession, long j, Map<String, List<String>> map, Object obj) throws NumberFormatException {
        this.C = System.currentTimeMillis();
        this.D = System.nanoTime();
        try {
            Map<String, String> mapA = UtilityImpl.a(map);
            ALog.d("SilenceConn_", "spdyOnStreamResponse", "header", map);
            int i = Integer.parseInt(mapA.get(":status"));
            ALog.e(d(), "spdyOnStreamResponse", "httpStatusCode", Integer.valueOf(i));
            if (i == 200) {
                d(1);
                String str = mapA.get("x-at");
                if (!TextUtils.isEmpty(str)) {
                    this.k = str;
                }
                this.I.auth_time = this.I.connection_stop_date > 0 ? System.currentTimeMillis() - this.I.connection_stop_date : 0L;
                String str2 = this.c == 0 ? "service" : "inapp";
                UTMini.getInstance().commitEvent(66001, "CONNECTED 200 " + str2, this.y, this.L, Integer.valueOf(Constants.SDK_VERSION_CODE), com.sigmob.sdk.common.Constants.FAIL);
                com.taobao.accs.utl.k.a("accs", BaseMonitor.ALARM_POINT_AUTH, "");
            } else {
                e(i);
            }
        } catch (Exception e) {
            ALog.e(d(), e.toString(), new Object[0]);
            n();
            this.I.setCloseReason(com.umeng.commonsdk.framework.c.c);
        }
        ALog.d(d(), "spdyOnStreamResponse", new Object[0]);
    }

    private void e(int i) {
        this.k = null;
        n();
        int i2 = this.v != null ? this.v.a : 0;
        this.I.setCloseReason("code not 200 is" + i);
        this.M = true;
        String str = this.c == 0 ? "service" : "inapp";
        UTMini.getInstance().commitEvent(66001, "CONNECTED NO 200 " + str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(Constants.SDK_VERSION_CODE), this.y, this.L);
        com.taobao.accs.utl.k.a("accs", BaseMonitor.ALARM_POINT_AUTH, "", i + "", "");
    }

    public void spdyDataSendCallback(SpdySession spdySession, boolean z, long j, int i, Object obj) {
        ALog.d(d(), "spdyDataSendCallback", new Object[0]);
    }

    public void spdyDataRecvCallback(SpdySession spdySession, boolean z, long j, int i, Object obj) {
        ALog.d(d(), "spdyDataRecvCallback", new Object[0]);
    }

    @Override // com.taobao.accs.net.b
    public void b() {
        this.K = false;
        this.f = 0;
    }

    public void bioPingRecvCallback(SpdySession spdySession, int i) {
        ALog.w(d(), "bioPingRecvCallback uniId:" + i, new Object[0]);
    }

    @Override // com.taobao.accs.net.b
    public void a(String str, boolean z, String str2) {
        try {
            d(4);
            n();
            this.I.setCloseReason(str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.taobao.accs.net.b
    public boolean a(String str) {
        boolean z;
        synchronized (this.u) {
            z = true;
            int size = this.u.size() - 1;
            while (true) {
                if (size >= 0) {
                    Message message = this.u.get(size);
                    if (message != null && message.getType() == 1 && message.cunstomDataId != null && message.cunstomDataId.equals(str)) {
                        this.u.remove(size);
                        break;
                    }
                    size--;
                } else {
                    z = false;
                    break;
                }
            }
        }
        return z;
    }

    public byte[] getSSLMeta(SpdySession spdySession) {
        return UtilityImpl.a(this.d, this.m, this.b, spdySession.getDomain());
    }

    public int putSSLMeta(SpdySession spdySession, byte[] bArr) {
        return UtilityImpl.a(this.d, this.m, this.b, spdySession.getDomain(), bArr);
    }

    public void spdyDataChunkRecvCB(SpdySession spdySession, boolean z, long j, SpdyByteArray spdyByteArray, Object obj) {
        ALog.d(d(), "spdyDataChunkRecvCB", new Object[0]);
    }

    @Override // com.taobao.accs.net.b
    public String d() {
        return "SilenceConn_" + this.m;
    }

    public void spdyCustomControlFrameFailCallback(SpdySession spdySession, Object obj, int i, int i2) {
        b(i);
    }

    @Override // com.taobao.accs.net.b
    protected void a(Context context) {
        if (this.g) {
            return;
        }
        super.a(context);
        GlobalAppRuntimeInfo.setBackground(false);
        this.g = true;
        ALog.i(d(), "init awcn success!", new Object[0]);
    }

    @Override // com.taobao.accs.net.b
    public String b(String str) {
        return UriUtil.HTTPS_PREFIX + this.i.getChannelHost();
    }
}
