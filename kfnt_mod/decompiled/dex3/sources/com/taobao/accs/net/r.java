package com.taobao.accs.net;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import com.taobao.accs.internal.AccsJobService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class r extends f {
    public static final int DEAMON_JOB_ID = 2051;
    public static final int HB_JOB_ID = 2050;

    protected r(Context context) {
        super(context);
    }

    @Override // com.taobao.accs.net.f
    protected void a(int i) {
        long j = i * 1000;
        ((JobScheduler) this.a.getSystemService("jobscheduler")).schedule(new JobInfo.Builder(HB_JOB_ID, new ComponentName(this.a.getPackageName(), AccsJobService.class.getName())).setMinimumLatency(j).setOverrideDeadline(j).setRequiredNetworkType(1).build());
    }
}
