package anetwork.channel.j;

import anet.channel.bytes.ByteArray;
import anet.channel.statist.RequestStatistic;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anetwork.channel.a.a;
import anetwork.channel.aidl.DefaultFinishEvent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c implements a {

    /* renamed from: a, reason: collision with root package name */
    private l f711a;

    /* renamed from: b, reason: collision with root package name */
    private anetwork.channel.a.a f712b;

    /* renamed from: c, reason: collision with root package name */
    private volatile boolean f713c = false;

    public c(l lVar, anetwork.channel.a.a aVar) {
        this.f711a = null;
        this.f712b = null;
        this.f711a = lVar;
        this.f712b = aVar;
    }

    @Override // anet.channel.request.Cancelable
    public void cancel() {
        this.f713c = true;
        this.f711a.f736a.f700b.ret = 2;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean zEquals;
        a.C0010a c0010a;
        if (this.f713c) {
            return;
        }
        RequestStatistic requestStatistic = this.f711a.f736a.f700b;
        if (this.f712b != null) {
            String strG = this.f711a.f736a.g();
            String str = this.f711a.f736a.a().getHeaders().get(HttpConstant.CACHE_CONTROL);
            boolean zEquals2 = "no-store".equals(str);
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (zEquals2) {
                this.f712b.remove(strG);
                zEquals = false;
                c0010a = null;
            } else {
                zEquals = "no-cache".equals(str);
                a.C0010a c0010a2 = this.f712b.get(strG);
                if (ALog.isPrintLog(2)) {
                    String str2 = this.f711a.f738c;
                    Object[] objArr = new Object[8];
                    objArr[0] = "hit";
                    objArr[1] = Boolean.valueOf(c0010a2 != null);
                    objArr[2] = "cost";
                    objArr[3] = Long.valueOf(requestStatistic.cacheTime);
                    objArr[4] = "length";
                    objArr[5] = Integer.valueOf(c0010a2 != null ? c0010a2.f621a.length : 0);
                    objArr[6] = "key";
                    objArr[7] = strG;
                    ALog.i("anet.CacheTask", "read cache", str2, objArr);
                }
                c0010a = c0010a2;
            }
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            requestStatistic.cacheTime = jCurrentTimeMillis2 - jCurrentTimeMillis;
            if (c0010a != null && !zEquals && c0010a.a()) {
                if (this.f711a.d.compareAndSet(false, true)) {
                    this.f711a.a();
                    requestStatistic.ret = 1;
                    requestStatistic.statusCode = 200;
                    requestStatistic.msg = "SUCCESS";
                    requestStatistic.protocolType = "cache";
                    requestStatistic.rspEnd = jCurrentTimeMillis2;
                    requestStatistic.processTime = jCurrentTimeMillis2 - requestStatistic.start;
                    if (ALog.isPrintLog(2)) {
                        ALog.i("anet.CacheTask", "hit fresh cache", this.f711a.f738c, "URL", this.f711a.f736a.f().urlString());
                    }
                    this.f711a.f737b.a(200, c0010a.f);
                    this.f711a.f737b.a(1, c0010a.f621a.length, ByteArray.wrap(c0010a.f621a));
                    this.f711a.f737b.a(new DefaultFinishEvent(200, "SUCCESS", requestStatistic));
                    return;
                }
                return;
            }
            if (this.f713c) {
                return;
            }
            g gVar = new g(this.f711a, zEquals2 ? null : this.f712b, c0010a);
            this.f711a.e = gVar;
            gVar.run();
        }
    }
}
