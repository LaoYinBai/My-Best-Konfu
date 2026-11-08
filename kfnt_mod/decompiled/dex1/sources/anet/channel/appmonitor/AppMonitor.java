package anet.channel.appmonitor;

import anet.channel.statist.AlarmObject;
import anet.channel.statist.CountObject;
import anet.channel.statist.StatObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AppMonitor {
    private static volatile IAppMonitor appMonitor = new a(null);
    private static volatile IAppMonitor apmMonitor = null;

    public static IAppMonitor getInstance() {
        return appMonitor;
    }

    public static void setInstance(IAppMonitor iAppMonitor) {
        appMonitor = new a(iAppMonitor);
    }

    public static void setApmMonitor(IAppMonitor iAppMonitor) {
        apmMonitor = iAppMonitor;
    }

    static class a implements IAppMonitor {

        /* renamed from: a, reason: collision with root package name */
        IAppMonitor f396a;

        @Override // anet.channel.appmonitor.IAppMonitor
        @Deprecated
        public void register() {
        }

        @Override // anet.channel.appmonitor.IAppMonitor
        @Deprecated
        public void register(Class<?> cls) {
        }

        a(IAppMonitor iAppMonitor) {
            this.f396a = null;
            this.f396a = iAppMonitor;
        }

        @Override // anet.channel.appmonitor.IAppMonitor
        public void commitStat(StatObject statObject) {
            if (AppMonitor.apmMonitor != null) {
                AppMonitor.apmMonitor.commitStat(statObject);
            }
            if (this.f396a != null) {
                this.f396a.commitStat(statObject);
            }
        }

        @Override // anet.channel.appmonitor.IAppMonitor
        public void commitAlarm(AlarmObject alarmObject) {
            if (this.f396a != null) {
                this.f396a.commitAlarm(alarmObject);
            }
        }

        @Override // anet.channel.appmonitor.IAppMonitor
        public void commitCount(CountObject countObject) {
            if (this.f396a != null) {
                this.f396a.commitCount(countObject);
            }
        }
    }
}
