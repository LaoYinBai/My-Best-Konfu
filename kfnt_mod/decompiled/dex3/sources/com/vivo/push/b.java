package com.vivo.push;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.vivo.push.util.n;
import com.vivo.push.util.r;
import com.vivo.push.util.y;
import com.vivo.vms.IPCInvoke;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class b implements ServiceConnection {
    private static final Object d = new Object();
    private static Map<String, b> e = new HashMap();
    public boolean a;
    public String b;
    public Context c;
    private volatile IPCInvoke g;
    private String i;
    private Handler j;
    private Object h = new Object();
    private AtomicInteger f = new AtomicInteger(1);

    private b(Context context, String str) {
        this.b = null;
        this.j = null;
        this.c = context;
        this.i = str;
        this.j = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.vivo.push.b.1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                if (message == null) {
                    n.a("AidlManager", "handleMessage error : msg is null");
                    return false;
                }
                switch (message.what) {
                    case 1:
                        n.a("AidlManager", "In connect, bind core service time out");
                        if (b.this.f.get() != 2) {
                            return true;
                        }
                        b.this.a(1);
                        return true;
                    case 2:
                        if (b.this.f.get() == 4) {
                            b.this.d();
                        }
                        b.this.a(1);
                        return true;
                    default:
                        n.b("AidlManager", "unknow msg what [" + message.what + "]");
                        return true;
                }
            }
        });
        this.b = r.b(context);
        if (TextUtils.isEmpty(this.b) || TextUtils.isEmpty(this.i)) {
            n.c(this.c, "init error : push pkgname is " + this.b + " ; action is " + this.i);
            this.a = false;
            return;
        }
        this.a = y.a(context, this.b) >= 1260;
        a();
    }

    public static b a(Context context, String str) {
        b bVar = e.get(str);
        if (bVar == null) {
            synchronized (d) {
                bVar = e.get(str);
                if (bVar == null) {
                    bVar = new b(context, str);
                    e.put(str, bVar);
                }
            }
        }
        return bVar;
    }

    private void a() {
        int i = this.f.get();
        n.d("AidlManager", "Enter connect, Connection Status: " + i);
        if (i == 4 || i == 2 || i == 3 || i == 5 || !this.a) {
            return;
        }
        a(2);
        if (!b()) {
            a(1);
            n.a("AidlManager", "bind core service fail");
        } else {
            this.j.removeMessages(1);
            this.j.sendEmptyMessageDelayed(1, 3000L);
        }
    }

    private boolean b() {
        Intent intent = new Intent(this.i);
        intent.setPackage(this.b);
        try {
            return this.c.bindService(intent, this, 1);
        } catch (Exception e2) {
            n.a("AidlManager", "bind core error", e2);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        this.f.set(i);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        c();
        this.g = IPCInvoke.Stub.asInterface(iBinder);
        if (this.g == null) {
            n.d("AidlManager", "onServiceConnected error : aidl must not be null.");
            d();
            this.f.set(1);
            return;
        }
        if (this.f.get() == 2) {
            a(4);
        } else if (this.f.get() != 4) {
            d();
        }
        synchronized (this.h) {
            this.h.notifyAll();
        }
    }

    private void c() {
        this.j.removeMessages(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        try {
            this.c.unbindService(this);
        } catch (Exception e2) {
            n.a("AidlManager", "On unBindServiceException:" + e2.getMessage());
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.g = null;
        a(1);
    }

    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName componentName) {
        n.b("AidlManager", "onBindingDied : " + componentName);
    }

    public final boolean a(Bundle bundle) {
        a();
        if (this.f.get() == 2) {
            synchronized (this.h) {
                try {
                    this.h.wait(2000L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }
        try {
            int i = this.f.get();
            if (i != 4) {
                n.d("AidlManager", "invoke error : connect status = " + i);
                return false;
            }
            this.j.removeMessages(2);
            this.j.sendEmptyMessageDelayed(2, com.umeng.commonsdk.proguard.b.d);
            this.g.asyncCall(bundle, null);
            return true;
        } catch (Exception e3) {
            n.a("AidlManager", "invoke error ", e3);
            int i2 = this.f.get();
            n.d("AidlManager", "Enter disconnect, Connection Status: " + i2);
            switch (i2) {
                case 1:
                default:
                    return false;
                case 2:
                    c();
                    a(1);
                    return false;
                case 3:
                    a(1);
                    return false;
                case 4:
                    a(1);
                    d();
                    return false;
            }
        }
    }
}
