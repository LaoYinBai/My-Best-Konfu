package anet.channel.d;

import android.content.Context;
import androidx.core.view.InputDeviceCompat;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.RequestCb;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.entity.ConnType;
import anet.channel.request.Request;
import anet.channel.session.TnetSpdySession;
import anet.channel.session.b;
import anet.channel.statist.HorseRaceStat;
import anet.channel.strategy.ConnProtocol;
import anet.channel.strategy.IConnStrategy;
import anet.channel.strategy.StrategyCenter;
import anet.channel.strategy.l;
import anet.channel.util.ALog;
import anet.channel.util.AppLifecycle;
import anet.channel.util.ErrorConstant;
import anet.channel.util.HttpConstant;
import anet.channel.util.HttpUrl;
import anet.channel.util.j;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.adnet.game.http.cb.NetCallback;
import java.io.IOException;
import java.net.Socket;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static final TreeMap<String, l.c> f417a = new TreeMap<>();

    /* renamed from: b, reason: collision with root package name */
    private static final AtomicInteger f418b = new AtomicInteger(1);

    /* renamed from: c, reason: collision with root package name */
    private static final ReentrantLock f419c = new ReentrantLock();
    private static final Condition d = f419c.newCondition();
    private static final Condition e = f419c.newCondition();
    private static volatile Thread f = null;
    private static final Runnable g = new b();

    public static void a() {
        ALog.i("awcn.NetworkDetector", "registerListener", null, new Object[0]);
        StrategyCenter.getInstance().registerListener(new c());
        AppLifecycle.registerLifecycleListener(new d());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(l.c cVar) throws Throwable {
        if (cVar.f578b == null || cVar.f578b.length == 0) {
            return;
        }
        String str = cVar.f577a;
        for (int i = 0; i < cVar.f578b.length; i++) {
            l.e eVar = cVar.f578b[i];
            String str2 = eVar.f583b.f572b;
            if (str2.equalsIgnoreCase(HttpConstant.HTTP) || str2.equalsIgnoreCase("https")) {
                a(str, eVar);
            } else if (str2.equalsIgnoreCase(ConnType.HTTP2) || str2.equalsIgnoreCase(ConnType.SPDY) || str2.equalsIgnoreCase(ConnType.QUIC)) {
                b(str, eVar);
            } else if (str2.equalsIgnoreCase("tcp")) {
                c(str, eVar);
            }
        }
    }

    private static void a(String str, l.e eVar) throws Throwable {
        HttpUrl httpUrl = HttpUrl.parse(eVar.f583b.f572b + HttpConstant.SCHEME_SPLIT + str + eVar.f584c);
        if (httpUrl == null) {
            return;
        }
        ALog.i("awcn.NetworkDetector", "startShortLinkTask", null, "url", httpUrl);
        Request requestBuild = new Request.Builder().setUrl(httpUrl).addHeader("Connection", "close").setConnectTimeout(eVar.f583b.f573c).setReadTimeout(eVar.f583b.d).setRedirectEnable(false).setSslSocketFactory(new j(str)).setSeq("HR" + f418b.getAndIncrement()).build();
        requestBuild.setDnsOptimize(eVar.f582a, eVar.f583b.f571a);
        long jCurrentTimeMillis = System.currentTimeMillis();
        b.a aVarA = anet.channel.session.b.a(requestBuild, (RequestCb) null);
        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
        HorseRaceStat horseRaceStat = new HorseRaceStat(str, eVar);
        horseRaceStat.connTime = jCurrentTimeMillis2;
        if (aVarA.f494a <= 0) {
            horseRaceStat.connErrorCode = aVarA.f494a;
        } else {
            horseRaceStat.connRet = 1;
            horseRaceStat.reqRet = aVarA.f494a != 200 ? 0 : 1;
            horseRaceStat.reqErrorCode = aVarA.f494a;
            horseRaceStat.reqTime = horseRaceStat.connTime;
        }
        AppMonitor.getInstance().commitStat(horseRaceStat);
    }

    private static void b(String str, l.e eVar) {
        ConnProtocol connProtocolValueOf = ConnProtocol.valueOf(eVar.f583b);
        ConnType connTypeValueOf = ConnType.valueOf(connProtocolValueOf);
        if (connTypeValueOf == null) {
            return;
        }
        ALog.i("awcn.NetworkDetector", "startLongLinkTask", null, com.alipay.sdk.cons.c.f, str, "ip", eVar.f582a, "port", Integer.valueOf(eVar.f583b.f571a), "protocol", connProtocolValueOf);
        String str2 = "HR" + f418b.getAndIncrement();
        Context context = GlobalAppRuntimeInfo.getContext();
        StringBuilder sb = new StringBuilder();
        sb.append(connTypeValueOf.isSSL() ? "https://" : BDAccountNetApi.Platform.PROJECT_MODE_SCHEME);
        sb.append(str);
        TnetSpdySession tnetSpdySession = new TnetSpdySession(context, new anet.channel.entity.a(sb.toString(), str2, a(connProtocolValueOf, eVar)));
        HorseRaceStat horseRaceStat = new HorseRaceStat(str, eVar);
        long jCurrentTimeMillis = System.currentTimeMillis();
        tnetSpdySession.registerEventcb(InputDeviceCompat.SOURCE_KEYBOARD, new e(horseRaceStat, jCurrentTimeMillis, str2, eVar, tnetSpdySession));
        tnetSpdySession.connect();
        synchronized (horseRaceStat) {
            try {
                horseRaceStat.wait(eVar.f583b.f573c == 0 ? NetCallback.ERROR_CODE_REQUEST_SUCCESS : eVar.f583b.f573c);
                if (horseRaceStat.connTime == 0) {
                    horseRaceStat.connTime = System.currentTimeMillis() - jCurrentTimeMillis;
                }
                AppMonitor.getInstance().commitStat(horseRaceStat);
            } catch (InterruptedException unused) {
            }
        }
        tnetSpdySession.close(false);
    }

    private static IConnStrategy a(ConnProtocol connProtocol, l.e eVar) {
        return new g(eVar, connProtocol);
    }

    private static void c(String str, l.e eVar) throws IOException {
        String str2 = "HR" + f418b.getAndIncrement();
        ALog.i("awcn.NetworkDetector", "startTcpTask", str2, "ip", eVar.f582a, "port", Integer.valueOf(eVar.f583b.f571a));
        HorseRaceStat horseRaceStat = new HorseRaceStat(str, eVar);
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            Socket socket = new Socket(eVar.f582a, eVar.f583b.f571a);
            socket.setSoTimeout(eVar.f583b.f573c == 0 ? NetCallback.ERROR_CODE_REQUEST_SUCCESS : eVar.f583b.f573c);
            ALog.i("awcn.NetworkDetector", "socket connect success", str2, new Object[0]);
            horseRaceStat.connRet = 1;
            horseRaceStat.connTime = System.currentTimeMillis() - jCurrentTimeMillis;
            socket.close();
        } catch (IOException unused) {
            horseRaceStat.connTime = System.currentTimeMillis() - jCurrentTimeMillis;
            horseRaceStat.connErrorCode = ErrorConstant.ERROR_IO_EXCEPTION;
        }
        AppMonitor.getInstance().commitStat(horseRaceStat);
    }
}
