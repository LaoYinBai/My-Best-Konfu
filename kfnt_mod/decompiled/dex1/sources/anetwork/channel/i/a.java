package anetwork.channel.i;

import anet.channel.statist.RequestStatistic;
import anet.channel.util.StringUtils;
import java.io.Serializable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements Serializable, Cloneable {
    private static final long serialVersionUID = -3538602124202475612L;
    public int F;
    public String G;

    /* renamed from: a, reason: collision with root package name */
    public String f707a = "";

    /* renamed from: b, reason: collision with root package name */
    public boolean f708b = false;

    /* renamed from: c, reason: collision with root package name */
    public int f709c = 0;
    public String d = "";

    @Deprecated
    public String e = "";
    public String f = "";

    @Deprecated
    public boolean g = false;
    public boolean h = false;

    @Deprecated
    public int i = 0;

    @Deprecated
    public int j = 0;

    @Deprecated
    public long k = 0;

    @Deprecated
    public long l = 0;
    public long m = 0;
    public long n = 0;

    @Deprecated
    public long o = 0;

    @Deprecated
    public long p = 0;
    public long q = 0;

    @Deprecated
    public long r = 0;
    public long s = 0;
    public long t = 0;
    public long u = 0;
    public long v = 0;

    @Deprecated
    public long w = 0;
    public long x = 0;
    public long y = 0;

    @Deprecated
    public long z = 0;
    public long A = 0;
    public long B = 0;

    @Deprecated
    public long C = 0;
    public long D = 0;

    @Deprecated
    public String E = "";

    public void a(RequestStatistic requestStatistic) {
        if (requestStatistic != null) {
            this.f709c = requestStatistic.statusCode;
            this.f707a = requestStatistic.protocolType;
            this.f708b = requestStatistic.ret == 1;
            this.d = requestStatistic.host;
            if (requestStatistic.ip != null && requestStatistic.port != 0) {
                this.f = String.format("%s:%d", requestStatistic.ip, Integer.valueOf(requestStatistic.port));
            }
            this.F = requestStatistic.retryTimes;
            this.h = requestStatistic.isSSL;
            this.m = requestStatistic.oneWayTime;
            this.n = requestStatistic.cacheTime;
            this.s = requestStatistic.processTime;
            this.t = requestStatistic.sendBeforeTime;
            this.u = requestStatistic.firstDataTime;
            this.v = requestStatistic.recDataTime;
            this.A = requestStatistic.sendDataSize;
            this.B = requestStatistic.recDataSize;
            this.x = requestStatistic.serverRT;
            this.D = this.v != 0 ? this.B / this.v : this.B;
        }
    }

    public String a() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("isSuccess=");
        sb.append(this.f708b);
        sb.append(",host=");
        sb.append(this.d);
        sb.append(",resultCode=");
        sb.append(this.f709c);
        sb.append(",connType=");
        sb.append(this.f707a);
        sb.append(",oneWayTime_ANet=");
        sb.append(this.m);
        sb.append(",ip_port=");
        sb.append(this.f);
        sb.append(",isSSL=");
        sb.append(this.h);
        sb.append(",cacheTime=");
        sb.append(this.n);
        sb.append(",processTime=");
        sb.append(this.s);
        sb.append(",sendBeforeTime=");
        sb.append(this.t);
        sb.append(",postBodyTime=");
        sb.append(this.q);
        sb.append(",firstDataTime=");
        sb.append(this.u);
        sb.append(",recDataTime=");
        sb.append(this.v);
        sb.append(",serverRT=");
        sb.append(this.x);
        sb.append(",rtt=");
        sb.append(this.y);
        sb.append(",sendSize=");
        sb.append(this.A);
        sb.append(",totalSize=");
        sb.append(this.B);
        sb.append(",dataSpeed=");
        sb.append(this.D);
        sb.append(",retryTime=");
        sb.append(this.F);
        return sb.toString();
    }

    public String toString() {
        if (StringUtils.isBlank(this.G)) {
            this.G = a();
        }
        return "StatisticData [" + this.G + "]";
    }
}
