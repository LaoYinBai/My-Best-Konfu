package anet.channel.statist;

import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.ConnProtocol;
import anet.channel.strategy.l;

@Monitor(module = "networkPrefer", monitorPoint = "horseRace")
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class HorseRaceStat extends StatObject {

    @Dimension
    public volatile int connErrorCode;

    @Measure
    public volatile long connTime;

    @Dimension
    public volatile String host;

    @Dimension
    public volatile String ip;

    @Dimension
    public volatile String path;

    @Dimension
    public volatile int port;

    @Dimension
    public volatile String protocol;

    @Dimension
    public volatile int reqErrorCode;

    @Measure
    public volatile long reqTime;

    @Dimension
    public volatile int connRet = 0;

    @Dimension
    public volatile int reqRet = 0;

    @Dimension
    public volatile String nettype = NetworkStatusHelper.getNetworkSubType();

    @Dimension
    public volatile String mnc = NetworkStatusHelper.getSimOp();

    @Dimension
    public volatile String bssid = NetworkStatusHelper.getWifiBSSID();

    public HorseRaceStat(String str, l.e eVar) {
        this.host = str;
        this.ip = eVar.f582a;
        this.port = eVar.f583b.f571a;
        this.protocol = ConnProtocol.valueOf(eVar.f583b).name;
        this.path = eVar.f584c;
    }
}
