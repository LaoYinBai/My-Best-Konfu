package anet.channel.monitor;

import anet.channel.util.ALog;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static volatile a f458a;

    /* renamed from: b, reason: collision with root package name */
    private Map<INetworkQualityChangeListener, f> f459b = new ConcurrentHashMap();

    /* renamed from: c, reason: collision with root package name */
    private f f460c = new f();

    private a() {
    }

    public static a a() {
        if (f458a == null) {
            synchronized (a.class) {
                if (f458a == null) {
                    f458a = new a();
                }
            }
        }
        return f458a;
    }

    public void a(INetworkQualityChangeListener iNetworkQualityChangeListener, f fVar) {
        if (iNetworkQualityChangeListener == null) {
            ALog.e("BandWidthListenerHelp", "listener is null", null, new Object[0]);
            return;
        }
        if (fVar == null) {
            this.f460c.f473b = System.currentTimeMillis();
            this.f459b.put(iNetworkQualityChangeListener, this.f460c);
        } else {
            fVar.f473b = System.currentTimeMillis();
            this.f459b.put(iNetworkQualityChangeListener, fVar);
        }
    }

    public void a(INetworkQualityChangeListener iNetworkQualityChangeListener) {
        this.f459b.remove(iNetworkQualityChangeListener);
    }

    public void a(double d) {
        boolean zA;
        for (Map.Entry<INetworkQualityChangeListener, f> entry : this.f459b.entrySet()) {
            INetworkQualityChangeListener key = entry.getKey();
            f value = entry.getValue();
            if (key != null && value != null && !value.b() && value.f472a != (zA = value.a(d))) {
                value.f472a = zA;
                key.onNetworkQualityChanged(zA ? NetworkSpeed.Slow : NetworkSpeed.Fast);
            }
        }
    }
}
