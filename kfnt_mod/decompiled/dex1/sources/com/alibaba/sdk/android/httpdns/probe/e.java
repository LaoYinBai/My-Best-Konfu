package com.alibaba.sdk.android.httpdns.probe;

import com.alibaba.sdk.android.httpdns.i;
import com.alibaba.sdk.android.httpdns.probe.IPProbeService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e implements IPProbeService {

    /* renamed from: a, reason: collision with other field name */
    private AtomicLong f38a = new AtomicLong(0);

    /* renamed from: c, reason: collision with root package name */
    private ConcurrentHashMap<String, Long> f2396c = new ConcurrentHashMap<>();

    /* renamed from: a, reason: collision with root package name */
    private b f2394a = null;

    /* renamed from: b, reason: collision with root package name */
    private f f2395b = new f() { // from class: com.alibaba.sdk.android.httpdns.probe.e.1
        @Override // com.alibaba.sdk.android.httpdns.probe.f
        public void a(long j, c cVar) {
            if (cVar != null) {
                try {
                    if (!e.this.f2396c.containsKey(cVar.getHostName()) || ((Long) e.this.f2396c.get(cVar.getHostName())).longValue() != j) {
                        i.d("corresponding tasknumber not exists, drop the result");
                    } else if (cVar != null && cVar.a() != null && cVar.i() != null && cVar.j() != null && cVar.getHostName() != null) {
                        i.e("defultId:" + cVar.i() + ", selectedIp:" + cVar.j() + ", promote:" + (cVar.c() - cVar.d()));
                        e.this.a(cVar.getHostName(), cVar.i(), cVar.j(), cVar.c(), cVar.d(), cVar.a().length);
                        e.this.f2394a.a(cVar.getHostName(), cVar.a());
                        e.this.f2396c.remove(cVar.getHostName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, String str3, long j, long j2, int i) {
        com.alibaba.sdk.android.httpdns.d.b bVarA = com.alibaba.sdk.android.httpdns.d.b.a();
        if (bVarA != null) {
            bVarA.a(str, str2, str3, j, j2, i);
        }
    }

    @Override // com.alibaba.sdk.android.httpdns.probe.IPProbeService
    public IPProbeService.a getProbeStatus(String str) {
        return this.f2396c.containsKey(str) ? IPProbeService.a.PROBING : IPProbeService.a.NO_PROBING;
    }

    @Override // com.alibaba.sdk.android.httpdns.probe.IPProbeService
    public void launchIPProbeTask(String str, int i, String[] strArr) {
        if (!com.alibaba.sdk.android.httpdns.a.a.a().f()) {
            i.f("ip probe is forbidden");
        } else {
            if (getProbeStatus(str) != IPProbeService.a.NO_PROBING) {
                i.f("already launch the same task, drop the task");
                return;
            }
            long jAddAndGet = this.f38a.addAndGet(1L);
            this.f2396c.put(str, Long.valueOf(jAddAndGet));
            com.alibaba.sdk.android.httpdns.c.a().execute(new a(jAddAndGet, str, strArr, i, this.f2395b));
        }
    }

    @Override // com.alibaba.sdk.android.httpdns.probe.IPProbeService
    public void setIPListUpdateCallback(b bVar) {
        this.f2394a = bVar;
    }

    @Override // com.alibaba.sdk.android.httpdns.probe.IPProbeService
    public boolean stopIPProbeTask(String str) {
        if (!this.f2396c.containsKey(str)) {
            return false;
        }
        i.d("stop ip probe task for host:" + str);
        this.f2396c.remove(str);
        return true;
    }
}
