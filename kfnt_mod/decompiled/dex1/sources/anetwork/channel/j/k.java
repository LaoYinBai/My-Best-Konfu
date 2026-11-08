package anetwork.channel.j;

import android.support.v4.media.session.PlaybackStateCompat;
import anet.channel.RequestCb;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.bytes.ByteArray;
import anet.channel.flow.FlowStat;
import anet.channel.flow.NetworkAnalysis;
import anet.channel.request.Request;
import anet.channel.statist.ExceptionStatistic;
import anet.channel.statist.RequestStatistic;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.thread.ThreadPoolExecutorFactory;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anet.channel.util.HttpConstant;
import anet.channel.util.HttpHelper;
import anet.channel.util.HttpUrl;
import anetwork.channel.a.a;
import anetwork.channel.aidl.DefaultFinishEvent;
import anetwork.channel.j.g;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class k implements RequestCb {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Request f733a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ RequestStatistic f734b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ g f735c;

    k(g gVar, Request request, RequestStatistic requestStatistic) {
        this.f735c = gVar;
        this.f733a = request;
        this.f734b = requestStatistic;
    }

    @Override // anet.channel.RequestCb
    public void onResponseCode(int i, Map<String, List<String>> map) {
        String singleHeaderFieldByKey;
        if (this.f735c.h.get()) {
            return;
        }
        if (ALog.isPrintLog(2)) {
            ALog.i("anet.NetworkTask", "onResponseCode", this.f733a.getSeq(), "code", Integer.valueOf(i));
            ALog.i("anet.NetworkTask", "onResponseCode", this.f733a.getSeq(), "headers", map);
        }
        if (HttpHelper.checkRedirect(this.f733a, i) && (singleHeaderFieldByKey = HttpHelper.getSingleHeaderFieldByKey(map, "Location")) != null) {
            HttpUrl httpUrl = HttpUrl.parse(singleHeaderFieldByKey);
            if (httpUrl != null) {
                if (this.f735c.h.compareAndSet(false, true)) {
                    httpUrl.lockScheme();
                    this.f735c.f720a.f736a.a(httpUrl);
                    this.f735c.f720a.d = new AtomicBoolean();
                    this.f735c.f720a.e = new g(this.f735c.f720a, null, null);
                    this.f734b.recordRedirect(i, httpUrl.simpleUrlString());
                    ThreadPoolExecutorFactory.submitPriorityTask(this.f735c.f720a.e, ThreadPoolExecutorFactory.Priority.HIGH);
                    return;
                }
                return;
            }
            ALog.e("anet.NetworkTask", "redirect url is invalid!", this.f733a.getSeq(), "redirect url", singleHeaderFieldByKey);
        }
        try {
            this.f735c.f720a.a();
            anetwork.channel.c.a.a(this.f735c.f720a.f736a.g(), map);
            this.f735c.i = HttpHelper.parseContentLength(map);
            String strG = this.f735c.f720a.f736a.g();
            if (this.f735c.f722c != null && i == 304) {
                this.f735c.f722c.f.putAll(map);
                a.C0010a c0010aA = anetwork.channel.a.d.a(map);
                if (c0010aA != null && c0010aA.e > this.f735c.f722c.e) {
                    this.f735c.f722c.e = c0010aA.e;
                }
                this.f735c.f720a.f737b.a(200, this.f735c.f722c.f);
                this.f735c.f720a.f737b.a(1, this.f735c.f722c.f621a.length, ByteArray.wrap(this.f735c.f722c.f621a));
                long jCurrentTimeMillis = System.currentTimeMillis();
                this.f735c.f721b.put(strG, this.f735c.f722c);
                ALog.i("anet.NetworkTask", "update cache", this.f735c.f720a.f738c, "cost", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis), "key", strG);
                return;
            }
            if (this.f735c.f721b != null) {
                if ("no-store".equals(HttpHelper.getSingleHeaderFieldByKey(map, HttpConstant.CACHE_CONTROL))) {
                    this.f735c.f721b.remove(strG);
                } else {
                    g gVar = this.f735c;
                    a.C0010a c0010aA2 = anetwork.channel.a.d.a(map);
                    gVar.f722c = c0010aA2;
                    if (c0010aA2 != null) {
                        HttpHelper.removeHeaderFiledByKey(map, HttpConstant.CACHE_CONTROL);
                        map.put(HttpConstant.CACHE_CONTROL, Arrays.asList("no-store"));
                        this.f735c.d = new ByteArrayOutputStream(this.f735c.i != 0 ? this.f735c.i : 5120);
                    }
                }
            }
            map.put(HttpConstant.X_PROTOCOL, Arrays.asList(this.f734b.protocolType));
            if (anetwork.channel.b.b.k() && this.f735c.i <= 131072) {
                this.f735c.m = new g.a(i, map);
            } else {
                this.f735c.f720a.f737b.a(i, map);
                this.f735c.k = true;
            }
        } catch (Exception e) {
            ALog.w("anet.NetworkTask", "[onResponseCode] error.", this.f735c.f720a.f738c, e, new Object[0]);
        }
    }

    @Override // anet.channel.RequestCb
    public void onDataReceive(ByteArray byteArray, boolean z) {
        if (this.f735c.h.get()) {
            return;
        }
        if (this.f735c.j == 0) {
            ALog.i("anet.NetworkTask", "[onDataReceive] receive first data chunk!", this.f735c.f720a.f738c, new Object[0]);
        }
        if (z) {
            ALog.i("anet.NetworkTask", "[onDataReceive] receive last data chunk!", this.f735c.f720a.f738c, new Object[0]);
        }
        this.f735c.j++;
        try {
            if (this.f735c.m != null) {
                this.f735c.m.f725c.add(byteArray);
                if (this.f734b.recDataSize > PlaybackStateCompat.ACTION_PREPARE_FROM_URI || z) {
                    this.f735c.j = this.f735c.m.a(this.f735c.f720a.f737b, this.f735c.i);
                    this.f735c.k = true;
                    this.f735c.l = this.f735c.j > 1;
                    this.f735c.m = null;
                }
            } else {
                this.f735c.f720a.f737b.a(this.f735c.j, this.f735c.i, byteArray);
                this.f735c.l = true;
            }
            if (this.f735c.d != null) {
                this.f735c.d.write(byteArray.getBuffer(), 0, byteArray.getDataLength());
                if (z) {
                    String strG = this.f735c.f720a.f736a.g();
                    this.f735c.f722c.f621a = this.f735c.d.toByteArray();
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    this.f735c.f721b.put(strG, this.f735c.f722c);
                    ALog.i("anet.NetworkTask", "write cache", this.f735c.f720a.f738c, "cost", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis), "size", Integer.valueOf(this.f735c.f722c.f621a.length), "key", strG);
                }
            }
        } catch (Exception e) {
            ALog.w("anet.NetworkTask", "[onDataReceive] error.", this.f735c.f720a.f738c, e, new Object[0]);
        }
    }

    @Override // anet.channel.RequestCb
    public void onFinish(int i, String str, RequestStatistic requestStatistic) {
        String strValueOf;
        DefaultFinishEvent defaultFinishEvent;
        if (this.f735c.h.getAndSet(true)) {
            return;
        }
        int i2 = 3;
        if (ALog.isPrintLog(2)) {
            ALog.i("anet.NetworkTask", "[onFinish]", this.f735c.f720a.f738c, "code", Integer.valueOf(i), "msg", str);
        }
        if (i < 0) {
            try {
                if (this.f735c.f720a.f736a.d()) {
                    if (!this.f735c.k && !this.f735c.l) {
                        ALog.e("anet.NetworkTask", "clear response buffer and retry", this.f735c.f720a.f738c, new Object[0]);
                        if (this.f735c.m != null) {
                            if (!this.f735c.m.f725c.isEmpty()) {
                                i2 = 4;
                            }
                            requestStatistic.roaming = i2;
                            this.f735c.m.a();
                            this.f735c.m = null;
                        }
                        this.f735c.f720a.f736a.k();
                        this.f735c.f720a.d = new AtomicBoolean();
                        this.f735c.f720a.e = new g(this.f735c.f720a, this.f735c.f721b, this.f735c.f722c);
                        if (requestStatistic.tnetErrorCode != 0) {
                            strValueOf = i + "|" + requestStatistic.tnetErrorCode;
                            requestStatistic.tnetErrorCode = 0;
                        } else {
                            strValueOf = String.valueOf(i);
                        }
                        requestStatistic.appendErrorTrace(strValueOf);
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        requestStatistic.retryCostTime += jCurrentTimeMillis - requestStatistic.start;
                        requestStatistic.start = jCurrentTimeMillis;
                        ThreadPoolExecutorFactory.submitPriorityTask(this.f735c.f720a.e, ThreadPoolExecutorFactory.Priority.HIGH);
                        return;
                    }
                    requestStatistic.msg += ":回调后触发重试";
                    if (this.f735c.l) {
                        requestStatistic.roaming = 2;
                    } else if (this.f735c.k) {
                        requestStatistic.roaming = 1;
                    }
                    ALog.e("anet.NetworkTask", "Cannot retry request after onHeader/onDataReceived callback!", this.f735c.f720a.f738c, new Object[0]);
                }
            } catch (Exception unused) {
                return;
            }
        }
        if (this.f735c.m != null) {
            this.f735c.m.a(this.f735c.f720a.f737b, this.f735c.i);
        }
        this.f735c.f720a.a();
        requestStatistic.isDone.set(true);
        if ("wv_h5".equals(this.f735c.e)) {
            ALog.e("anet.NetworkTask", null, this.f735c.f720a.f738c, "url", this.f733a.getHttpUrl().simpleUrlString(), "content-length", Integer.valueOf(this.f735c.i), "recDataLength", Long.valueOf(requestStatistic.rspBodyDeflateSize));
        }
        if (this.f735c.f720a.f736a.j() && requestStatistic.contentLength != 0 && requestStatistic.contentLength != requestStatistic.rspBodyDeflateSize) {
            requestStatistic.ret = 0;
            requestStatistic.statusCode = ErrorConstant.ERROR_DATA_LENGTH_NOT_MATCH;
            str = ErrorConstant.getErrMsg(ErrorConstant.ERROR_DATA_LENGTH_NOT_MATCH);
            requestStatistic.msg = str;
            ALog.e("anet.NetworkTask", "received data length not match with content-length", this.f735c.f720a.f738c, "content-length", Integer.valueOf(this.f735c.i), "recDataLength", Long.valueOf(requestStatistic.rspBodyDeflateSize));
            ExceptionStatistic exceptionStatistic = new ExceptionStatistic(ErrorConstant.ERROR_DATA_LENGTH_NOT_MATCH, str, "rt");
            exceptionStatistic.url = this.f735c.f720a.f736a.g();
            AppMonitor.getInstance().commitStat(exceptionStatistic);
            i = ErrorConstant.ERROR_DATA_LENGTH_NOT_MATCH;
        }
        if (i == 304 && this.f735c.f722c != null) {
            requestStatistic.protocolType = "cache";
            defaultFinishEvent = new DefaultFinishEvent(200, str, requestStatistic);
        } else {
            defaultFinishEvent = new DefaultFinishEvent(i, str, requestStatistic);
        }
        this.f735c.f720a.f737b.a(defaultFinishEvent);
        if (i >= 0) {
            anet.channel.monitor.b.a().a(requestStatistic.sendStart, requestStatistic.rspEnd, requestStatistic.rspHeadDeflateSize + requestStatistic.rspBodyDeflateSize);
        } else {
            requestStatistic.netType = NetworkStatusHelper.getNetworkSubType();
        }
        NetworkAnalysis.getInstance().commitFlow(new FlowStat(this.f735c.e, requestStatistic));
    }
}
