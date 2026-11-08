package anet.channel.d;

import anet.channel.RequestCb;
import anet.channel.bytes.ByteArray;
import anet.channel.statist.RequestStatistic;
import anet.channel.util.ALog;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class f implements RequestCb {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ e f423a;

    @Override // anet.channel.RequestCb
    public void onDataReceive(ByteArray byteArray, boolean z) {
    }

    f(e eVar) {
        this.f423a = eVar;
    }

    @Override // anet.channel.RequestCb
    public void onResponseCode(int i, Map<String, List<String>> map) {
        this.f423a.f420a.reqErrorCode = i;
    }

    @Override // anet.channel.RequestCb
    public void onFinish(int i, String str, RequestStatistic requestStatistic) {
        ALog.i("awcn.NetworkDetector", "LongLinkTask request finish", this.f423a.f422c, "statusCode", Integer.valueOf(i), "msg", str);
        if (this.f423a.f420a.reqErrorCode == 0) {
            this.f423a.f420a.reqErrorCode = i;
        } else {
            this.f423a.f420a.reqRet = this.f423a.f420a.reqErrorCode == 200 ? 1 : 0;
        }
        this.f423a.f420a.reqTime = (System.currentTimeMillis() - this.f423a.f421b) + this.f423a.f420a.connTime;
        synchronized (this.f423a.f420a) {
            this.f423a.f420a.notify();
        }
    }
}
