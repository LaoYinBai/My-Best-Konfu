package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import androidx.annotation.Nullable;
import java.util.concurrent.CountDownLatch;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class i1<SERVICE, RESULT> {

    /* renamed from: a, reason: collision with root package name */
    private final CountDownLatch f1341a = new CountDownLatch(1);

    /* renamed from: b, reason: collision with root package name */
    private final Intent f1342b;

    /* renamed from: c, reason: collision with root package name */
    private final b<SERVICE, RESULT> f1343c;
    private final Context d;

    class a implements ServiceConnection {

        /* renamed from: a, reason: collision with root package name */
        private final CountDownLatch f1344a;

        /* renamed from: b, reason: collision with root package name */
        private final b<SERVICE, RESULT> f1345b;

        /* renamed from: c, reason: collision with root package name */
        @Nullable
        SERVICE f1346c;

        a(i1 i1Var, CountDownLatch countDownLatch, b<SERVICE, RESULT> bVar) {
            this.f1344a = countDownLatch;
            this.f1345b = bVar;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            t0.b(z0.j, "ServiceBlockBinder#onServiceConnected " + componentName);
            try {
                this.f1346c = this.f1345b.a(iBinder);
                try {
                    this.f1344a.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    th.printStackTrace();
                    t0.b(z0.j, "ServiceBlockBinder#onServiceConnected", th);
                    try {
                        this.f1344a.countDown();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } catch (Throwable th2) {
                    try {
                        this.f1344a.countDown();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    throw th2;
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            t0.b(z0.j, "ServiceBlockBinder#onServiceDisconnected" + componentName);
            try {
                this.f1344a.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    interface b<T, RESULT> {
        T a(IBinder iBinder);

        RESULT a(T t);
    }

    i1(Context context, Intent intent, b<SERVICE, RESULT> bVar) {
        this.d = context;
        this.f1342b = intent;
        this.f1343c = bVar;
    }

    RESULT a() {
        Throwable th;
        i1<SERVICE, RESULT>.a aVar;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            return null;
        }
        try {
            aVar = new a(this, this.f1341a, this.f1343c);
            this.d.bindService(this.f1342b, aVar, 1);
            this.f1341a.await();
        } catch (Throwable th2) {
            th = th2;
            aVar = null;
        }
        try {
            return this.f1343c.a((b<SERVICE, RESULT>) aVar.f1346c);
        } catch (Throwable th3) {
            th = th3;
            try {
                th.printStackTrace();
                return null;
            } finally {
                a(aVar);
            }
        }
    }

    private void a(i1<SERVICE, RESULT>.a aVar) {
        if (aVar != null) {
            try {
                this.d.unbindService(aVar);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
