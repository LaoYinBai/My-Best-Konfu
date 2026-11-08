package anet.channel.util;

import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.status.NetworkStatusHelper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class e implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ d f610a;

    e(d dVar) {
        this.f610a = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        f fVarJ;
        try {
            if (this.f610a.f608a.equals(c.b(NetworkStatusHelper.getStatus()))) {
                ALog.e("awcn.Inet64Util", "startIpStackDetect double check", null, new Object[0]);
                int i = c.i();
                if (this.f610a.f609b.ipStackType != i) {
                    c.e.put(this.f610a.f608a, Integer.valueOf(i));
                    this.f610a.f609b.lastIpStackType = this.f610a.f609b.ipStackType;
                    this.f610a.f609b.ipStackType = i;
                }
                if ((i == 2 || i == 3) && (fVarJ = c.j()) != null) {
                    c.d.put(this.f610a.f608a, fVarJ);
                    this.f610a.f609b.nat64Prefix = fVarJ.toString();
                }
                if (GlobalAppRuntimeInfo.isTargetProcess()) {
                    AppMonitor.getInstance().commitStat(this.f610a.f609b);
                }
            }
        } catch (Exception unused) {
        }
    }
}
