package com.ss.android.push.daemon.a;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.ss.android.push.daemon.nativ.NativeDaemonAPI;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c extends a {
    private IBinder c;
    private Parcel d;
    private d e = new d();

    @Override // com.ss.android.push.daemon.a.a, com.ss.android.push.daemon.e
    public void a(Context context, com.ss.android.push.daemon.b bVar) {
        super.a(context, bVar);
        try {
            b();
            if (this.c == null) {
                return;
            }
            a(context, bVar.b.c);
            c();
            try {
                File dir = context.getDir("indicators", 0);
                new NativeDaemonAPI(context).doDaemon(new File(dir, "indicator_p").getAbsolutePath(), new File(dir, "indicator_d").getAbsolutePath(), new File(dir, "observer_p").getAbsolutePath(), new File(dir, "observer_d").getAbsolutePath());
            } catch (Throwable th) {
                th.printStackTrace();
            }
            ComponentName componentName = new ComponentName(context.getPackageName(), bVar.a.b);
            Intent intent = new Intent();
            intent.setComponent(componentName);
            context.startService(intent);
            if (bVar == null || bVar.c == null) {
                return;
            }
            this.b = bVar;
            bVar.c.b(context);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    @Override // com.ss.android.push.daemon.a.a, com.ss.android.push.daemon.e
    public void b(Context context, com.ss.android.push.daemon.b bVar) {
        super.b(context, bVar);
        try {
            b();
            if (this.c == null) {
                return;
            }
            a(context, bVar.a.c);
            c();
            try {
                File dir = context.getDir("indicators", 0);
                new NativeDaemonAPI(context).doDaemon(new File(dir, "indicator_d").getAbsolutePath(), new File(dir, "indicator_p").getAbsolutePath(), new File(dir, "observer_d").getAbsolutePath(), new File(dir, "observer_p").getAbsolutePath());
            } catch (Throwable unused) {
            }
            ComponentName componentName = new ComponentName(context.getPackageName(), bVar.b.b);
            Intent intent = new Intent();
            intent.setComponent(componentName);
            context.startService(intent);
            if (bVar == null || bVar.c == null) {
                return;
            }
            this.b = bVar;
            bVar.c.c(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.ss.android.push.daemon.a.a, com.ss.android.push.daemon.e
    public void a() {
        if (this.e.a()) {
            return;
        }
        try {
            if (!c() || this.b == null || this.b.c == null) {
                return;
            }
            this.b.c.a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void b() throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        try {
            Class<?> cls = Class.forName("android.app.ActivityManagerNative");
            Object objInvoke = cls.getMethod("getDefault", new Class[0]).invoke(cls, new Object[0]);
            Field declaredField = objInvoke.getClass().getDeclaredField("mRemote");
            declaredField.setAccessible(true);
            this.c = (IBinder) declaredField.get(objInvoke);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
        } catch (NoSuchFieldException e4) {
            e4.printStackTrace();
        } catch (NoSuchMethodException e5) {
            e5.printStackTrace();
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
        } catch (Exception e7) {
            e7.printStackTrace();
        }
    }

    @SuppressLint({"Recycle"})
    private synchronized void a(Context context, String str) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context.getPackageName(), str));
        intent.setAction("start_by_daemon_action");
        intent.setFlags(32);
        this.d = Parcel.obtain();
        this.d.writeInterfaceToken("android.app.IActivityManager");
        this.d.writeStrongBinder(null);
        intent.writeToParcel(this.d, 0);
        this.d.writeString(intent.resolveTypeIfNeeded(context.getContentResolver()));
        this.d.writeStrongBinder(null);
        this.d.writeInt(-1);
        this.d.writeString(null);
        this.d.writeBundle(null);
        this.d.writeString(null);
        this.d.writeInt(-1);
        this.d.writeInt(0);
        this.d.writeInt(0);
        this.d.writeInt(0);
    }

    private boolean c() throws RemoteException {
        try {
            if (this.c != null && this.d != null) {
                this.c.transact(14, this.d, null, 0);
                return true;
            }
            Log.e("Daemon", "REMOTE IS NULL or PARCEL IS NULL !!!");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
