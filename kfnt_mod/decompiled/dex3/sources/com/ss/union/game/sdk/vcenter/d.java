package com.ss.union.game.sdk.vcenter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.core.browser.JSInterface;
import com.ss.union.game.sdk.core.splashEffect.util.TimeCollector;
import com.ss.union.game.sdk.core.vapp.VLog;
import com.ss.union.game.sdk.vcenter.i;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d {
    private static final String a = "game_pn";
    private static d b = new d();
    private i e;
    private boolean c = false;
    private List<Runnable> d = new CopyOnWriteArrayList();
    private volatile boolean f = false;
    private volatile boolean g = false;

    private static String d() {
        return "com.bd.ad.v.game.center.gamesdk.SdkIPCBridgeService";
    }

    private d() {
    }

    private static String c() {
        try {
            return Entry.sHostContext != null ? Entry.sHostContext.getPackageName() : JSInterface.a.c;
        } catch (Exception e) {
            e.printStackTrace();
            return JSInterface.a.c;
        }
    }

    public static d a() {
        return b;
    }

    public boolean b() {
        if (this.f) {
            return this.g;
        }
        this.f = true;
        this.g = b(GlobalApplicationUtils.getContext());
        return this.g;
    }

    public void a(final String str, k kVar) {
        if (!e()) {
            b(GlobalApplicationUtils.getContext());
            this.d.add(new b<k>(kVar) { // from class: com.ss.union.game.sdk.vcenter.d.1
                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.ss.union.game.sdk.vcenter.d.b
                public void a(k kVar2) {
                    d.this.a(str, kVar2);
                }
            });
            return;
        }
        try {
            this.e.a(str, kVar);
        } catch (RemoteException e) {
            e.printStackTrace();
            a(com.ss.union.game.sdk.account.a.a.ae, str, e);
            try {
                kVar.a(com.ss.union.game.sdk.vcenter.b.a, e.getMessage(), "ipc 异常了");
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(final String str, l lVar) {
        if (!e()) {
            b(GlobalApplicationUtils.getContext());
            this.d.add(new b<l>(lVar) { // from class: com.ss.union.game.sdk.vcenter.d.2
                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.ss.union.game.sdk.vcenter.d.b
                public void a(l lVar2) {
                    d.this.a(str, lVar2);
                }
            });
            return;
        }
        try {
            this.e.a(str, lVar);
        } catch (RemoteException e) {
            e.printStackTrace();
            a("switchAccount", str, e);
            try {
                lVar.a(com.ss.union.game.sdk.vcenter.b.a, e.getMessage(), "ipc 异常了");
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(final String str, h hVar) {
        if (!e()) {
            b(GlobalApplicationUtils.getContext());
            this.d.add(new b<h>(hVar) { // from class: com.ss.union.game.sdk.vcenter.d.3
                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.ss.union.game.sdk.vcenter.d.b
                public void a(h hVar2) {
                    d.this.a(str, hVar2);
                }
            });
            return;
        }
        try {
            this.e.a(str, hVar);
        } catch (RemoteException e) {
            e.printStackTrace();
            a("bindAccount", str, e);
            try {
                hVar.a(com.ss.union.game.sdk.vcenter.b.a, e.getMessage(), "ipc 异常了");
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(j jVar) {
        if (!e()) {
            b(GlobalApplicationUtils.getContext());
            this.d.add(new b<j>(jVar) { // from class: com.ss.union.game.sdk.vcenter.d.4
                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.ss.union.game.sdk.vcenter.d.b
                public void a(j jVar2) {
                    d.this.a(jVar2);
                }
            });
            return;
        }
        try {
            this.e.a(jVar);
        } catch (RemoteException e) {
            e.printStackTrace();
            a("fetchVAccountInfo", "", e);
            try {
                jVar.a(com.ss.union.game.sdk.vcenter.account.a.a(-1, "ipc失败" + e.getMessage(), e.getMessage()));
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void a(String str, String str2, RemoteException remoteException) {
        VLog.Account.d("ipc 执行方法" + str + ",params = " + str2 + ",异常信息=" + remoteException.getMessage());
    }

    private boolean e() {
        return this.c;
    }

    private boolean b(Context context) {
        if (this.c) {
            return true;
        }
        TimeCollector.tag("IPC耗时").start();
        boolean zA = a(context);
        VLog.Account.d("绑定IPC service结果：" + zA);
        TimeCollector.tag("IPC耗时").endAll("绑定耗时是：");
        return zA;
    }

    public boolean a(Context context) {
        try {
            ComponentName componentName = new ComponentName(c(), d());
            Intent intent = new Intent();
            a(intent);
            intent.setComponent(componentName);
            return context.bindService(intent, new a(), 1);
        } catch (Throwable unused) {
            return false;
        }
    }

    private void a(Intent intent) {
        intent.putExtra(a, GlobalApplicationUtils.getContext().getPackageName());
        intent.setType(GlobalApplicationUtils.getContext().getPackageName() + "_" + String.valueOf(System.currentTimeMillis()).substring(r0.length() - 4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.c = false;
        this.e = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(IBinder iBinder) throws RemoteException {
        try {
            iBinder.linkToDeath(new IBinder.DeathRecipient() { // from class: com.ss.union.game.sdk.vcenter.d.5
                @Override // android.os.IBinder.DeathRecipient
                public void binderDied() {
                    VLog.Account.d("监听摸摸鱼app 挂了，binderDied = true");
                    d.this.f();
                }
            }, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
            VLog.Account.d("监听摸摸鱼app=binder 失败：" + e.getMessage());
        }
    }

    abstract class b<T> implements Runnable {
        private T a;

        abstract void a(T t);

        public b(T t) {
            this.a = t;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.a == null) {
                VLog.Account.d("action没有被执行，activityRef == null || callbackRef == null，return");
            } else {
                a(this.a);
            }
        }
    }

    private class a implements ServiceConnection {
        private a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) throws RemoteException {
            d.this.c = true;
            d.this.e = i.a.a(iBinder);
            d.this.a(iBinder);
            try {
                d.this.e.a(com.ss.union.game.sdk.vcenter.c.a.c.a());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Iterator it = d.this.d.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
            d.this.d.clear();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            d.this.f();
        }
    }
}
