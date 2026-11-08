package com.xiaomi.push;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.SystemClock;
import com.xiaomi.push.ex;
import com.xiaomi.push.service.XMJobService;

@TargetApi(21)
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ez implements ex.a {
    JobScheduler a;

    /* renamed from: a, reason: collision with other field name */
    Context f344a;

    /* renamed from: a, reason: collision with other field name */
    private boolean f345a = false;

    ez(Context context) {
        this.f344a = context;
        this.a = (JobScheduler) context.getSystemService("jobscheduler");
    }

    @Override // com.xiaomi.push.ex.a
    /* renamed from: a */
    public void mo307a() {
        this.f345a = false;
        this.a.cancel(1);
    }

    void a(long j) {
        JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(this.f344a.getPackageName(), XMJobService.class.getName()));
        builder.setMinimumLatency(j);
        builder.setOverrideDeadline(j);
        builder.setRequiredNetworkType(1);
        builder.setPersisted(false);
        com.xiaomi.channel.commonutils.logger.b.c("schedule Job = " + builder.build().getId() + " in " + j);
        this.a.schedule(builder.build());
    }

    @Override // com.xiaomi.push.ex.a
    public void a(boolean z) {
        if (z || this.f345a) {
            long jB = fv.b();
            if (z) {
                mo307a();
                jB -= SystemClock.elapsedRealtime() % jB;
            }
            this.f345a = true;
            a(jB);
        }
    }

    @Override // com.xiaomi.push.ex.a
    /* renamed from: a */
    public boolean mo306a() {
        return this.f345a;
    }
}
