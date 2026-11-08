package anet.channel.f;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import anet.channel.AwcnConfig;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.IConnStrategy;
import anet.channel.strategy.IStrategyFilter;
import anet.channel.strategy.StrategyCenter;
import anet.channel.thread.ThreadPoolExecutorFactory;
import anet.channel.util.ALog;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static String f440a;

    /* renamed from: b, reason: collision with root package name */
    private static HashMap<String, Long> f441b = new HashMap<>();

    /* renamed from: c, reason: collision with root package name */
    private static AtomicBoolean f442c = new AtomicBoolean(false);
    private static IStrategyFilter d = new b();
    private static AtomicInteger e = new AtomicInteger(1);

    public static void a(NetworkStatusHelper.NetworkStatus networkStatus) {
        if (!AwcnConfig.isQuicEnable()) {
            ALog.i("awcn.QuicConnDetector", "startDetect", null, "quic global config close.");
            return;
        }
        if (NetworkStatusHelper.isConnected()) {
            if (TextUtils.isEmpty(f440a)) {
                ALog.e("awcn.QuicConnDetector", "startDetect", null, "host is null");
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            Long l = f441b.get(networkStatus.getType());
            if (l == null || l.longValue() + 1800000 <= jCurrentTimeMillis) {
                List<IConnStrategy> connStrategyListByHost = StrategyCenter.getInstance().getConnStrategyListByHost(f440a, d);
                if (connStrategyListByHost.isEmpty()) {
                    ALog.e("awcn.QuicConnDetector", "startDetect", null, "quic strategy is null.");
                } else {
                    f441b.put(networkStatus.getType(), Long.valueOf(jCurrentTimeMillis));
                    ThreadPoolExecutorFactory.submitPriorityTask(new c(connStrategyListByHost), ThreadPoolExecutorFactory.Priority.LOW);
                }
            }
        }
    }

    public static void a() {
        ALog.e("awcn.QuicConnDetector", "registerListener", null, new Object[0]);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(GlobalAppRuntimeInfo.getContext());
        f440a = defaultSharedPreferences.getString("quic_detector_host", "");
        NetworkStatusHelper.addStatusChangeListener(new e());
        StrategyCenter.getInstance().registerListener(new f(defaultSharedPreferences));
    }
}
