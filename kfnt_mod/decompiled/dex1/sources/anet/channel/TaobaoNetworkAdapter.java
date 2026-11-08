package anet.channel;

import android.content.Context;
import anet.channel.Config;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.entity.ConnType;
import anet.channel.entity.ENV;
import anet.channel.flow.NetworkAnalysis;
import anet.channel.strategy.ConnProtocol;
import anet.channel.strategy.StrategyTemplate;
import anet.channel.thread.ThreadPoolExecutorFactory;
import anet.channel.util.ALog;
import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TaobaoNetworkAdapter implements Serializable {
    public static AtomicBoolean isInited = new AtomicBoolean();

    public static void init(Context context, HashMap<String, Object> map) {
        if (isInited.compareAndSet(false, true)) {
            ALog.setLog(new anet.channel.e.a());
            anetwork.channel.b.b.a(new anet.channel.c.a());
            AppMonitor.setInstance(new anet.channel.appmonitor.a());
            NetworkAnalysis.setInstance(new anet.channel.a.a());
            ThreadPoolExecutorFactory.submitPriorityTask(new h(), ThreadPoolExecutorFactory.Priority.NORMAL);
            if (map != null) {
                try {
                    Integer num = (Integer) map.get("outline");
                    if (num != null && num.intValue() == -1) {
                        ALog.e("awcn.TaobaoNetworkAdapter", "taobao speed mode enable.", null, new Object[0]);
                        GlobalAppRuntimeInfo.addBucketInfo("tbspeed", "speed");
                        if (anetwork.channel.b.b.e()) {
                            anetwork.channel.aidl.a.g.a(context, false);
                        }
                        AwcnConfig.setAsyncLoadStrategyEnable(true);
                    }
                } catch (Exception unused) {
                }
            }
            if (map != null) {
                try {
                    if ("com.taobao.taobao".equals((String) map.get("process"))) {
                        String str = (String) map.get("onlineAppKey");
                        a("guide-acs.m.taobao.com", str, ConnProtocol.valueOf(ConnType.HTTP2, ConnType.RTT_0, ConnType.PK_ACS), true);
                        ConnProtocol connProtocolValueOf = ConnProtocol.valueOf(ConnType.HTTP2, ConnType.RTT_0, ConnType.PK_CDN);
                        a("gw.alicdn.com", str, connProtocolValueOf, false);
                        a("dorangesource.alicdn.com", str, connProtocolValueOf, false);
                        a("ossgw.alicdn.com", str, connProtocolValueOf, false);
                    }
                } catch (Exception unused2) {
                }
            }
        }
    }

    private static void a(String str, String str2, ConnProtocol connProtocol, boolean z) {
        StrategyTemplate.getInstance().registerConnProtocol(str, connProtocol);
        if (z) {
            SessionCenter.getInstance(new Config.Builder().setAppkey(str2).setEnv(ENV.ONLINE).build()).registerSessionInfo(SessionInfo.create(str, z, false, null, null, null));
        }
    }
}
