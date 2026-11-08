package com.bytedance.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import androidx.annotation.Nullable;
import java.util.concurrent.CountDownLatch;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class da<SERVICE, RESULT> {

    /* renamed from: a, reason: collision with root package name */
    public final CountDownLatch f3485a = new CountDownLatch(1);

    /* renamed from: b, reason: collision with root package name */
    public final Intent f3486b;

    /* renamed from: c, reason: collision with root package name */
    public final b<SERVICE, RESULT> f3487c;
    public final Context d;

    public class a implements ServiceConnection {

        /* renamed from: a, reason: collision with root package name */
        public final CountDownLatch f3488a;

        /* renamed from: b, reason: collision with root package name */
        public final b<SERVICE, RESULT> f3489b;

        /* renamed from: c, reason: collision with root package name */
        @Nullable
        public SERVICE f3490c;

        public a(da daVar, CountDownLatch countDownLatch, b<SERVICE, RESULT> bVar) {
            this.f3488a = countDownLatch;
            this.f3489b = bVar;
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
        }

        @Override // android.content.ServiceConnection
        public void onNullBinding(ComponentName componentName) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            CountDownLatch countDownLatch;
            t.a(bh.f3405b, "ServiceBlockBinder#onServiceConnected " + componentName, null);
            try {
                try {
                    this.f3490c = this.f3489b.a(iBinder);
                    countDownLatch = this.f3488a;
                } catch (Throwable th) {
                    try {
                        de.a("", th);
                        t.a(bh.f3405b, "ServiceBlockBinder#onServiceConnected", th);
                        countDownLatch = this.f3488a;
                    } catch (Throwable th2) {
                        try {
                            this.f3488a.countDown();
                        } catch (Exception e) {
                            de.a("", e);
                        }
                        throw th2;
                    }
                }
                countDownLatch.countDown();
            } catch (Exception e2) {
                de.a("", e2);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            t.a(bh.f3405b, "ServiceBlockBinder#onServiceDisconnected" + componentName, null);
            try {
                this.f3488a.countDown();
            } catch (Exception e) {
                de.a("", e);
            }
        }
    }

    public interface b<T, RESULT> {
        T a(IBinder iBinder);

        RESULT a(T t);
    }

    public da(Context context, Intent intent, b<SERVICE, RESULT> bVar) {
        this.d = context;
        this.f3486b = intent;
        this.f3487c = bVar;
    }

    public RESULT a() {
        Throwable th;
        da<SERVICE, RESULT>.a aVar;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            return null;
        }
        try {
            aVar = new a(this, this.f3485a, this.f3487c);
            this.d.bindService(this.f3486b, aVar, 1);
            this.f3485a.await();
        } catch (Throwable th2) {
            th = th2;
            aVar = null;
        }
        try {
            return this.f3487c.a((b<SERVICE, RESULT>) aVar.f3490c);
        } catch (Throwable th3) {
            th = th3;
            try {
                de.a("", th);
                return null;
            } finally {
                a(aVar);
            }
        }
    }

    public final void a(da<SERVICE, RESULT>.a aVar) {
        if (aVar != null) {
            try {
                this.d.unbindService(aVar);
            } catch (Throwable th) {
                de.a("", th);
            }
        }
    }
}
