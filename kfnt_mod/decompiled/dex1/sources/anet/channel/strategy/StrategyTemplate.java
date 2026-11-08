package anet.channel.strategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class StrategyTemplate {
    Map<String, ConnProtocol> templateMap = new ConcurrentHashMap();

    public static StrategyTemplate getInstance() {
        return a.f533a;
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        static StrategyTemplate f533a = new StrategyTemplate();

        a() {
        }
    }

    public void registerConnProtocol(String str, ConnProtocol connProtocol) {
        if (connProtocol != null) {
            this.templateMap.put(str, connProtocol);
            try {
                IStrategyInstance strategyCenter = StrategyCenter.getInstance();
                if (strategyCenter instanceof g) {
                    ((g) strategyCenter).f563b.f526c.a(str, connProtocol);
                }
            } catch (Exception unused) {
            }
        }
    }

    public ConnProtocol getConnProtocol(String str) {
        return this.templateMap.get(str);
    }
}
