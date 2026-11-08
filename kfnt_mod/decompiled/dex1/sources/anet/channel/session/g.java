package anet.channel.session;

import anet.channel.RequestCb;
import anet.channel.bytes.ByteArray;
import anet.channel.statist.RequestStatistic;
import anet.channel.util.ALog;
import anet.channel.util.HttpHelper;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class g implements RequestCb {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ f f503a;

    g(f fVar) {
        this.f503a = fVar;
    }

    @Override // anet.channel.RequestCb
    public void onResponseCode(int i, Map<String, List<String>> map) {
        ALog.i("awcn.HttpSession", "", this.f503a.f500a.getSeq(), "httpStatusCode", Integer.valueOf(i));
        ALog.i("awcn.HttpSession", "", this.f503a.f500a.getSeq(), "response headers", map);
        this.f503a.f501b.onResponseCode(i, map);
        this.f503a.f502c.serverRT = HttpHelper.parseServerRT(map);
        this.f503a.d.handleResponseCode(this.f503a.f500a, i);
        this.f503a.d.handleResponseHeaders(this.f503a.f500a, map);
    }

    @Override // anet.channel.RequestCb
    public void onDataReceive(ByteArray byteArray, boolean z) {
        this.f503a.f501b.onDataReceive(byteArray, z);
    }

    @Override // anet.channel.RequestCb
    public void onFinish(int i, String str, RequestStatistic requestStatistic) {
        if (i <= 0 && i != -204) {
            this.f503a.d.handleCallbacks(2, new anet.channel.entity.b(2, 0, "Http connect fail"));
        }
        this.f503a.f501b.onFinish(i, str, requestStatistic);
    }
}
