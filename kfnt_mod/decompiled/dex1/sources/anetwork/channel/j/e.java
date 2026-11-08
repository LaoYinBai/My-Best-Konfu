package anetwork.channel.j;

import anet.channel.RequestCb;
import anet.channel.bytes.ByteArray;
import anet.channel.statist.RequestStatistic;
import anet.channel.util.ALog;
import anet.channel.util.HttpHelper;
import anetwork.channel.aidl.DefaultFinishEvent;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class e implements RequestCb {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ d f717a;

    e(d dVar) {
        this.f717a = dVar;
    }

    @Override // anet.channel.RequestCb
    public void onResponseCode(int i, Map<String, List<String>> map) {
        if (this.f717a.f716c.d.get()) {
            return;
        }
        this.f717a.f716c.a();
        anetwork.channel.c.a.a(this.f717a.f716c.f736a.g(), map);
        this.f717a.d = HttpHelper.parseContentLength(map);
        if (this.f717a.f716c.f737b != null) {
            this.f717a.f716c.f737b.a(i, map);
        }
    }

    @Override // anet.channel.RequestCb
    public void onDataReceive(ByteArray byteArray, boolean z) {
        if (this.f717a.f716c.d.get()) {
            return;
        }
        d.b(this.f717a);
        if (this.f717a.f716c.f737b != null) {
            this.f717a.f716c.f737b.a(this.f717a.e, this.f717a.d, byteArray);
        }
    }

    @Override // anet.channel.RequestCb
    public void onFinish(int i, String str, RequestStatistic requestStatistic) {
        if (this.f717a.f716c.d.getAndSet(true)) {
            return;
        }
        if (ALog.isPrintLog(2)) {
            ALog.i("anet.DegradeTask", "[onFinish]", this.f717a.f716c.f738c, "code", Integer.valueOf(i), "msg", str);
        }
        this.f717a.f716c.a();
        requestStatistic.isDone.set(true);
        if (this.f717a.f716c.f737b != null) {
            this.f717a.f716c.f737b.a(new DefaultFinishEvent(i, str, requestStatistic));
        }
    }
}
