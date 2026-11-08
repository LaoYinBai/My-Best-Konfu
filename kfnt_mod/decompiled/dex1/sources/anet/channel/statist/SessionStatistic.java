package anet.channel.statist;

import anet.channel.entity.a;
import anet.channel.util.ALog;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import org.json.JSONObject;

@Monitor(module = "networkPrefer", monitorPoint = "session")
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SessionStatistic extends StatObject {
    public static int maxRetryTime;

    @Measure
    public long ackTime;

    @Measure(max = 15000.0d)
    public long authTime;

    @Measure
    public long cfRCount;

    @Dimension
    public String closeReason;

    @Measure(max = 15000.0d, name = "connTime")
    public long connectionTime;

    @Dimension(name = "protocolType")
    public String conntype;

    @Dimension
    public long errorCode;

    @Dimension
    public String host;

    @Measure
    public long inceptCount;

    @Dimension
    public String ip;

    @Dimension
    public int ipRefer;

    @Dimension
    public int ipType;

    @Dimension
    public boolean isBackground;

    @Dimension
    public long isKL;

    @Dimension
    public String isTunnel;

    @Measure
    public int lastPingInterval;

    @Dimension
    public String netType;

    @Measure
    public long pRate;

    @Dimension
    public int port;

    @Measure
    public long ppkgCount;

    @Measure
    public long recvSizeCount;

    @Dimension
    public int ret;

    @Dimension
    public long retryTimes;

    @Dimension
    public int sdkv;

    @Measure
    public long sendSizeCount;

    @Measure(max = 15000.0d)
    public long sslCalTime;

    @Measure(max = 15000.0d)
    public long sslTime;

    @Dimension
    public int isProxy = 0;

    @Dimension
    public JSONObject extra = null;

    @Measure(max = 86400.0d)
    public long liveTime = 0;

    @Measure(constantValue = 1.0d)
    public long requestCount = 1;

    @Measure(constantValue = 0.0d)
    public long stdRCount = 1;
    public boolean isCommitted = false;

    public SessionStatistic(a aVar) {
        this.ipRefer = 0;
        this.ipType = 1;
        this.ip = aVar.a();
        this.port = aVar.b();
        if (aVar.f429a != null) {
            this.ipRefer = aVar.f429a.getIpSource();
            this.ipType = aVar.f429a.getIpType();
        }
        this.pRate = aVar.g();
        this.conntype = aVar.c().toString();
        this.retryTimes = aVar.f430b;
        maxRetryTime = aVar.f431c;
    }

    @Override // anet.channel.statist.StatObject
    public boolean beforeCommit() {
        if (this.ret == 0 && (this.retryTimes != maxRetryTime || this.errorCode == -2613 || this.errorCode == -2601)) {
            if (ALog.isPrintLog(1)) {
                ALog.d("SessionStat no need commit", null, "retry:", Long.valueOf(this.retryTimes), "maxRetryTime", Integer.valueOf(maxRetryTime), AccountMonitorConstants.CommonParameter.ERROR, Long.valueOf(this.errorCode));
            }
            return false;
        }
        if (this.isCommitted) {
            return false;
        }
        this.isCommitted = true;
        return true;
    }

    public AlarmObject getAlarmObject() {
        AlarmObject alarmObject = new AlarmObject();
        alarmObject.module = "networkPrefer";
        alarmObject.modulePoint = "connect_succ_rate";
        alarmObject.isSuccess = this.ret != 0;
        if (alarmObject.isSuccess) {
            alarmObject.arg = this.closeReason;
        } else {
            alarmObject.errorCode = String.valueOf(this.errorCode);
        }
        return alarmObject;
    }
}
