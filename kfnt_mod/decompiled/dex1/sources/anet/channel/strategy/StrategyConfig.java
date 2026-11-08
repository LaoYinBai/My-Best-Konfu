package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.strategy.l;
import anet.channel.strategy.utils.SerialLruCache;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class StrategyConfig implements Serializable {
    public static final String NO_RESULT = "No_Result";

    /* renamed from: a, reason: collision with root package name */
    private SerialLruCache<String, String> f521a = null;

    /* renamed from: b, reason: collision with root package name */
    private Map<String, String> f522b = null;

    /* renamed from: c, reason: collision with root package name */
    private transient StrategyInfoHolder f523c = null;

    StrategyConfig() {
    }

    void a(StrategyInfoHolder strategyInfoHolder) {
        this.f523c = strategyInfoHolder;
    }

    void a() {
        if (this.f521a == null) {
            this.f521a = new SerialLruCache<>(256);
        }
        if (this.f522b == null) {
            this.f522b = new ConcurrentHashMap();
        }
    }

    void a(l.d dVar) {
        if (dVar.f580b == null) {
            return;
        }
        synchronized (this) {
            TreeMap treeMap = null;
            for (int i = 0; i < dVar.f580b.length; i++) {
                l.b bVar = dVar.f580b[i];
                if (bVar.j) {
                    this.f521a.remove(bVar.f574a);
                } else if (bVar.d != null) {
                    if (treeMap == null) {
                        treeMap = new TreeMap();
                    }
                    treeMap.put(bVar.f574a, bVar.d);
                } else {
                    if (!HttpConstant.HTTP.equalsIgnoreCase(bVar.f576c) && !"https".equalsIgnoreCase(bVar.f576c)) {
                        this.f521a.put(bVar.f574a, NO_RESULT);
                    } else {
                        this.f521a.put(bVar.f574a, bVar.f576c);
                    }
                    if (!TextUtils.isEmpty(bVar.e)) {
                        this.f522b.put(bVar.f574a, bVar.e);
                    } else {
                        this.f522b.remove(bVar.f574a);
                    }
                }
            }
            if (treeMap != null) {
                for (Map.Entry entry : treeMap.entrySet()) {
                    String str = (String) entry.getValue();
                    if (this.f521a.containsKey(str)) {
                        this.f521a.put(entry.getKey(), this.f521a.get(str));
                    } else {
                        this.f521a.put(entry.getKey(), NO_RESULT);
                    }
                }
            }
        }
        if (ALog.isPrintLog(1)) {
            ALog.d("awcn.StrategyConfig", "", null, "SchemeMap", this.f521a.toString());
            ALog.d("awcn.StrategyConfig", "", null, "UnitMap", this.f522b.toString());
        }
    }

    String a(String str) {
        String str2;
        if (TextUtils.isEmpty(str) || !anet.channel.strategy.utils.c.c(str)) {
            return null;
        }
        synchronized (this.f521a) {
            str2 = this.f521a.get(str);
            if (str2 == null) {
                this.f521a.put(str, NO_RESULT);
            }
        }
        if (str2 == null) {
            this.f523c.d().a(str, false);
        } else if (NO_RESULT.equals(str2)) {
            return null;
        }
        return str2;
    }

    String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.f522b.get(str);
    }
}
