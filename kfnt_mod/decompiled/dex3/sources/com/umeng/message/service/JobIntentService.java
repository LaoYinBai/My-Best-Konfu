package com.umeng.message.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import com.ss.android.downloadlib.constants.DownloadConstants;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class JobIntentService extends Service {
    static final String d = "JobIntentService";
    static final boolean e = false;
    static final Object m = new Object();
    static final HashMap<ComponentName, f> n = new HashMap<>();
    CompatJobEngine f;
    f g;
    a h;
    boolean i = false;
    boolean j = false;
    boolean k = false;
    final ArrayList<c> l;

    interface CompatJobEngine {
        IBinder compatGetBinder();

        GenericWorkItem dequeueWork();
    }

    interface GenericWorkItem {
        void complete();

        Intent getIntent();
    }

    protected abstract void a(Intent intent);

    public boolean onStopCurrentWork() {
        return true;
    }

    public JobIntentService() {
        if (Build.VERSION.SDK_INT >= 26) {
            this.l = null;
        } else {
            this.l = new ArrayList<>();
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        d();
    }

    private void d() {
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                this.f = new d(this);
                this.g = null;
            } else {
                this.f = null;
                this.g = a(this, new ComponentName(this, getClass()), false, 0);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Service
    @SuppressLint({"WrongConstant"})
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            try {
                if (this.l == null) {
                    return 2;
                }
                if (this.g == null) {
                    d();
                }
                this.g.a();
                ArrayList<c> arrayList = this.l;
                synchronized (this.l) {
                    ArrayList<c> arrayList2 = this.l;
                    if (intent == null) {
                        intent = new Intent();
                    }
                    arrayList2.add(new c(intent, i2));
                    a(true);
                }
                return 3;
            } catch (Throwable unused) {
            }
        }
        return 2;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (intent != null) {
            try {
                if (this.f != null) {
                    return this.f.compatGetBinder();
                }
                return null;
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        try {
            super.onDestroy();
            if (this.l != null) {
                ArrayList<c> arrayList = this.l;
                synchronized (this.l) {
                    this.k = true;
                    this.g.c();
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static void enqueueWork(Context context, Class cls, int i, Intent intent) {
        if (context == null || cls == null || intent == null) {
            return;
        }
        try {
            enqueueWork(context, new ComponentName(context, (Class<?>) cls), i, intent);
        } catch (Throwable unused) {
        }
    }

    public static void enqueueWork(Context context, ComponentName componentName, int i, Intent intent) {
        if (intent == null) {
            throw new IllegalArgumentException("work must not be null");
        }
        if (context == null || componentName == null) {
            return;
        }
        try {
            Object obj = m;
            synchronized (m) {
                f fVarA = a(context, componentName, true, i);
                fVarA.a(i);
                fVarA.a(intent);
            }
        } catch (Throwable unused) {
        }
    }

    static f a(Context context, ComponentName componentName, boolean z, int i) {
        f bVar;
        f fVar = n.get(componentName);
        if (fVar != null) {
            return fVar;
        }
        if (Build.VERSION.SDK_INT < 26) {
            bVar = new b(context, componentName);
        } else {
            if (!z) {
                throw new IllegalArgumentException("Can't be here without a job id");
            }
            bVar = new e(context, componentName, i);
        }
        f fVar2 = bVar;
        n.put(componentName, fVar2);
        return fVar2;
    }

    public void setInterruptIfStopped(boolean z) {
        this.i = z;
    }

    public boolean isStopped() {
        return this.j;
    }

    boolean a() {
        if (this.h != null) {
            this.h.cancel(this.i);
        }
        this.j = true;
        return onStopCurrentWork();
    }

    void a(boolean z) {
        try {
            if (this.h == null) {
                this.h = new a();
                if (this.g != null && z) {
                    this.g.b();
                }
                this.h.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            }
        } catch (Throwable unused) {
        }
    }

    void b() {
        try {
            if (this.l != null) {
                ArrayList<c> arrayList = this.l;
                synchronized (this.l) {
                    this.h = null;
                    if (this.l != null && this.l.size() > 0) {
                        a(false);
                    } else if (!this.k) {
                        this.g.c();
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    GenericWorkItem c() {
        c cVarRemove;
        if (this.f != null) {
            return this.f.dequeueWork();
        }
        ArrayList<c> arrayList = this.l;
        synchronized (this.l) {
            cVarRemove = this.l.size() > 0 ? this.l.remove(0) : null;
        }
        return cVarRemove;
    }

    final class a extends AsyncTask<Void, Void, Void> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            while (true) {
                try {
                    GenericWorkItem genericWorkItemC = JobIntentService.this.c();
                    if (genericWorkItemC == null) {
                        return null;
                    }
                    JobIntentService.this.a(genericWorkItemC.getIntent());
                    genericWorkItemC.complete();
                } catch (Throwable unused) {
                    return null;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onCancelled(Void r1) {
            try {
                JobIntentService.this.b();
            } catch (Throwable unused) {
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r1) {
            try {
                JobIntentService.this.b();
            } catch (Throwable unused) {
            }
        }
    }

    final class c implements GenericWorkItem {
        final Intent a;
        final int b;

        c(Intent intent, int i) {
            this.a = intent;
            this.b = i;
        }

        @Override // com.umeng.message.service.JobIntentService.GenericWorkItem
        public Intent getIntent() {
            return this.a;
        }

        @Override // com.umeng.message.service.JobIntentService.GenericWorkItem
        public void complete() {
            try {
                JobIntentService.this.stopSelf(this.b);
            } catch (Throwable unused) {
            }
        }
    }

    static final class e extends f {
        private final JobInfo a;
        private final JobScheduler b;

        e(Context context, ComponentName componentName, int i) {
            super(context, componentName);
            a(i);
            this.a = new JobInfo.Builder(i, this.c).setOverrideDeadline(0L).build();
            this.b = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
        }

        @Override // com.umeng.message.service.JobIntentService.f
        void a(Intent intent) {
            try {
                this.b.enqueue(this.a, new JobWorkItem(intent));
            } catch (Throwable unused) {
            }
        }
    }

    static final class d extends JobServiceEngine implements CompatJobEngine {
        static final String a = "JobServiceEngineImpl";
        static final boolean b = false;
        final JobIntentService c;
        final Object d;
        JobParameters e;

        d(JobIntentService jobIntentService) {
            super(jobIntentService);
            this.d = new Object();
            this.c = jobIntentService;
        }

        @Override // com.umeng.message.service.JobIntentService.CompatJobEngine
        public IBinder compatGetBinder() {
            return getBinder();
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStartJob(JobParameters jobParameters) {
            this.e = jobParameters;
            this.c.a(false);
            return true;
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStopJob(JobParameters jobParameters) {
            boolean zA = this.c.a();
            Object obj = this.d;
            synchronized (this.d) {
                this.e = null;
            }
            return zA;
        }

        @Override // com.umeng.message.service.JobIntentService.CompatJobEngine
        public GenericWorkItem dequeueWork() {
            try {
                Object obj = this.d;
                synchronized (this.d) {
                    if (this.e == null) {
                        return null;
                    }
                    JobWorkItem jobWorkItemDequeueWork = this.e.dequeueWork();
                    if (jobWorkItemDequeueWork == null) {
                        return null;
                    }
                    jobWorkItemDequeueWork.getIntent().setExtrasClassLoader(this.c.getClassLoader());
                    return new a(jobWorkItemDequeueWork);
                }
            } catch (Throwable unused) {
                return null;
            }
        }

        final class a implements GenericWorkItem {
            final JobWorkItem a;

            a(JobWorkItem jobWorkItem) {
                this.a = jobWorkItem;
            }

            @Override // com.umeng.message.service.JobIntentService.GenericWorkItem
            public Intent getIntent() {
                return this.a.getIntent();
            }

            @Override // com.umeng.message.service.JobIntentService.GenericWorkItem
            public void complete() {
                try {
                    Object obj = d.this.d;
                    synchronized (d.this.d) {
                        if (d.this.e != null) {
                            try {
                                d.this.e.completeWork(this.a);
                            } catch (Throwable unused) {
                            }
                        }
                    }
                } catch (Throwable unused2) {
                }
            }
        }
    }

    static final class b extends f {
        boolean a;
        boolean b;
        private final Context f;
        private final PowerManager.WakeLock g;
        private final PowerManager.WakeLock h;

        b(Context context, ComponentName componentName) {
            super(context, componentName);
            this.f = context.getApplicationContext();
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            this.g = powerManager.newWakeLock(1, componentName.getClassName() + ":launch");
            this.g.setReferenceCounted(false);
            this.h = powerManager.newWakeLock(1, componentName.getClassName() + ":run");
            this.h.setReferenceCounted(false);
        }

        @Override // com.umeng.message.service.JobIntentService.f
        void a(Intent intent) {
            try {
                Intent intent2 = new Intent(intent);
                intent2.setComponent(this.c);
                if (this.f.startService(intent2) != null) {
                    synchronized (this) {
                        if (!this.a) {
                            this.a = true;
                            if (!this.b) {
                                this.g.acquire(60000L);
                            }
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }

        @Override // com.umeng.message.service.JobIntentService.f
        public void a() {
            synchronized (this) {
                this.a = false;
            }
        }

        @Override // com.umeng.message.service.JobIntentService.f
        public void b() {
            try {
                synchronized (this) {
                    if (!this.b) {
                        this.b = true;
                        this.h.acquire(DownloadConstants.DOWNLOAD_OPTIMIZATION_SPACE_INTERNAL);
                        this.g.release();
                    }
                }
            } catch (Throwable unused) {
            }
        }

        @Override // com.umeng.message.service.JobIntentService.f
        public void c() {
            try {
                synchronized (this) {
                    if (this.b) {
                        if (this.a) {
                            this.g.acquire(60000L);
                        }
                        this.b = false;
                        this.h.release();
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    static abstract class f {
        final ComponentName c;
        boolean d;
        int e;

        public void a() {
        }

        abstract void a(Intent intent);

        public void b() {
        }

        public void c() {
        }

        f(Context context, ComponentName componentName) {
            this.c = componentName;
        }

        void a(int i) {
            if (!this.d) {
                this.d = true;
                this.e = i;
            } else {
                if (this.e == i) {
                    return;
                }
                throw new IllegalArgumentException("Given job ID " + i + " is different than previous " + this.e);
            }
        }
    }
}
