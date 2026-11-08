package anet.channel.strategy.dispatch;

import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.util.ALog;
import com.bytedance.b.a.a.c.g;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class b {
    public static final String TAG = "awcn.AmdcThreadPoolExecutor";

    /* renamed from: b, reason: collision with root package name */
    private static Random f551b = new Random();

    /* renamed from: a, reason: collision with root package name */
    private Map<String, Object> f552a;

    b() {
    }

    public void a(Map<String, Object> map) {
        try {
            map.put("Env", GlobalAppRuntimeInfo.getEnv());
            synchronized (this) {
                if (this.f552a == null) {
                    this.f552a = map;
                    int iNextInt = f551b.nextInt(3000) + 2000;
                    ALog.i(TAG, "merge amdc request", null, g.p, Integer.valueOf(iNextInt));
                    anet.channel.strategy.utils.a.a(new a(), iNextInt);
                } else {
                    Set set = (Set) this.f552a.get(DispatchConstants.HOSTS);
                    Set set2 = (Set) map.get(DispatchConstants.HOSTS);
                    if (map.get("Env") != this.f552a.get("Env")) {
                        this.f552a = map;
                    } else if (set.size() + set2.size() <= 40) {
                        set2.addAll(set);
                        this.f552a = map;
                    } else {
                        anet.channel.strategy.utils.a.a(new a(map));
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    private class a implements Runnable {

        /* renamed from: b, reason: collision with root package name */
        private Map<String, Object> f554b;

        a(Map<String, Object> map) {
            this.f554b = map;
        }

        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Map<String, Object> map;
            try {
                Map<String, Object> map2 = this.f554b;
                if (map2 == null) {
                    synchronized (b.class) {
                        map = b.this.f552a;
                        b.this.f552a = null;
                    }
                    map2 = map;
                }
                if (NetworkStatusHelper.isConnected()) {
                    if (GlobalAppRuntimeInfo.getEnv() != map2.get("Env")) {
                        ALog.w(b.TAG, "task's env changed", null, new Object[0]);
                    } else {
                        c.a(e.a(map2));
                    }
                }
            } catch (Exception e) {
                ALog.e(b.TAG, "exec amdc task failed.", null, e, new Object[0]);
            }
        }
    }
}
