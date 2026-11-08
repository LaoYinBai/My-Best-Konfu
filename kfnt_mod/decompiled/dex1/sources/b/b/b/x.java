package b.b.b;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import androidx.annotation.Nullable;
import java.util.concurrent.CountDownLatch;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class x<SERVICE, RESULT> {

    /* renamed from: a, reason: collision with root package name */
    public final CountDownLatch f1190a = new CountDownLatch(1);

    /* renamed from: b, reason: collision with root package name */
    public final Intent f1191b;

    /* renamed from: c, reason: collision with root package name */
    public final b<SERVICE, RESULT> f1192c;
    public final Context d;

    public class a implements ServiceConnection {

        /* renamed from: a, reason: collision with root package name */
        public final CountDownLatch f1193a;

        /* renamed from: b, reason: collision with root package name */
        public final b<SERVICE, RESULT> f1194b;

        /* renamed from: c, reason: collision with root package name */
        @Nullable
        public SERVICE f1195c;

        public a(x xVar, CountDownLatch countDownLatch, b<SERVICE, RESULT> bVar) {
            this.f1193a = countDownLatch;
            this.f1194b = bVar;
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
        }

        @Override // android.content.ServiceConnection
        public void onNullBinding(ComponentName componentName) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            g.b(o.f1173b, "ServiceBlockBinder#onServiceConnected " + componentName);
            try {
                this.f1195c = this.f1194b.a(iBinder);
                try {
                    this.f1193a.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    th.printStackTrace();
                    g.a(o.f1173b, "ServiceBlockBinder#onServiceConnected", th);
                    try {
                        this.f1193a.countDown();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } catch (Throwable th2) {
                    try {
                        this.f1193a.countDown();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    throw th2;
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            g.b(o.f1173b, "ServiceBlockBinder#onServiceDisconnected" + componentName);
            try {
                this.f1193a.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public interface b<T, RESULT> {
        T a(IBinder iBinder);

        RESULT a(T t);
    }

    public x(Context context, Intent intent, b<SERVICE, RESULT> bVar) {
        this.d = context;
        this.f1191b = intent;
        this.f1192c = bVar;
    }

    public RESULT a() {
        Throwable th;
        x<SERVICE, RESULT>.a aVar;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            return null;
        }
        try {
            aVar = new a(this, this.f1190a, this.f1192c);
            this.d.bindService(this.f1191b, aVar, 1);
            this.f1190a.await();
        } catch (Throwable th2) {
            th = th2;
            aVar = null;
        }
        try {
            return this.f1192c.a((b<SERVICE, RESULT>) aVar.f1195c);
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

    public final void a(x<SERVICE, RESULT>.a aVar) {
        if (aVar != null) {
            try {
                this.d.unbindService(aVar);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
