package com.ss.android.message;

import android.annotation.TargetApi;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.Message;
import com.bytedance.common.push.utility.a.c;
import com.bytedance.push.alive.AlvService;
import com.bytedance.push.i;
import com.ss.android.pushmanager.MessageConstants;
import com.ss.android.pushmanager.a.a;
import com.ss.android.pushmanager.setting.PushSetting;
import com.taobao.accs.utl.j;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PushJobService extends AlvService {
    private IBinder a = null;

    @TargetApi(21)
    public static void a(Context context) {
        if (context == null || ((a.b) com.ss.android.b.a.b.a(a.b.class)).g()) {
            return;
        }
        try {
            JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
            jobScheduler.cancel(1);
            JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(context.getPackageName(), PushJobService.class.getName()));
            builder.setPeriodic(PushSetting.getInstance().getJobScheduleWakeUpIntervalSecond() * 1000);
            builder.setPersisted(true);
            builder.setRequiredNetworkType(0);
            int iSchedule = jobScheduler.schedule(builder.build());
            if (iSchedule > 0 || !com.bytedance.common.push.utility.a.b()) {
                return;
            }
            com.bytedance.common.push.utility.a.b("PushJobService", "schedule err errCode = " + iSchedule);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @TargetApi(21)
    public static void b(Context context) {
        if (context == null) {
            return;
        }
        try {
            ((JobScheduler) context.getSystemService("jobscheduler")).cancel(1);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                this.a = new a(this).a;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if (com.bytedance.common.push.utility.a.b()) {
            com.bytedance.common.push.utility.a.b("PushJobService", "onCreate mBinder = " + this.a.toString());
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        int iOnStartCommand = super.onStartCommand(intent, i, i2);
        if (((a.b) com.ss.android.b.a.b.a(a.b.class)).g()) {
            return 2;
        }
        return iOnStartCommand;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public IBinder onBind(Intent intent) {
        if (com.bytedance.common.push.utility.a.b()) {
            com.bytedance.common.push.utility.a.b("PushJobService", "onBind mBinder = " + this.a.toString());
        }
        if (this.a != null) {
            return this.a;
        }
        return new Binder();
    }

    @TargetApi(21)
    static class a extends JobService implements c.a {
        private IBinder a;
        private com.bytedance.common.push.utility.a.c b;

        public a(Service service) {
            this.a = null;
            com.bytedance.common.push.utility.d.a.a(this).a("attachBaseContext", new Class[]{Context.class}, new Object[]{service});
            this.a = onBind(null);
            i.j().e().a(this);
        }

        @Override // android.app.job.JobService
        public boolean onStartJob(JobParameters jobParameters) {
            if (com.bytedance.common.push.utility.a.b()) {
                com.bytedance.common.push.utility.a.b("InnerJobService", "Job start " + jobParameters.getJobId());
            }
            try {
                Intent intent = new Intent(this, (Class<?>) NotifyService.class);
                intent.setAction(MessageConstants.NOTIFY_ACTION);
                intent.setPackage(getPackageName());
                startService(intent);
                Intent intent2 = new Intent();
                intent2.setPackage(getPackageName());
                intent2.setClassName(getPackageName(), j.channelService);
                startService(intent2);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (this.b == null) {
                this.b = new com.bytedance.common.push.utility.a.c(this);
            }
            this.b.sendMessage(Message.obtain(this.b, 1, jobParameters));
            return true;
        }

        @Override // android.app.job.JobService
        public boolean onStopJob(JobParameters jobParameters) {
            if (!com.bytedance.common.push.utility.a.b()) {
                return false;
            }
            com.bytedance.common.push.utility.a.b("InnerJobService", "Job stop " + jobParameters.getJobId());
            return false;
        }

        public void handleMsg(Message message) {
            try {
                if (message.what != 1) {
                    return;
                }
                JobParameters jobParameters = (JobParameters) message.obj;
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("InnerJobService", "Job finished " + jobParameters.getJobId());
                }
                jobFinished(jobParameters, false);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
