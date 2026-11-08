package com.tencent.bugly.crashreport.crash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.tencent.bugly.proguard.u;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;

/* compiled from: BUGLY */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BuglyBroadcastRecevier extends BroadcastReceiver {
    private static BuglyBroadcastRecevier d;
    private Context b;
    private String c;
    private boolean e = true;
    private IntentFilter a = new IntentFilter();

    public static synchronized BuglyBroadcastRecevier getInstance() {
        if (d == null) {
            d = new BuglyBroadcastRecevier();
        }
        return d;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (this.b != null) {
            this.b.unregisterReceiver(this);
        }
    }

    public synchronized void addFilter(String str) {
        if (!this.a.hasAction(str)) {
            this.a.addAction(str);
        }
        x.c("add action %s", str);
    }

    public synchronized void register(Context context) {
        this.b = context;
        z.a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.BuglyBroadcastRecevier.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    x.a(BuglyBroadcastRecevier.d.getClass(), "Register broadcast receiver of Bugly.", new Object[0]);
                    synchronized (this) {
                        BuglyBroadcastRecevier.this.b.registerReceiver(BuglyBroadcastRecevier.d, BuglyBroadcastRecevier.this.a);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public synchronized void unregister(Context context) {
        try {
            x.a(getClass(), "Unregister broadcast receiver of Bugly.", new Object[0]);
            context.unregisterReceiver(this);
            this.b = context;
        } catch (Throwable th) {
            if (x.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        try {
            a(context, intent);
        } catch (Throwable th) {
            if (x.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    private synchronized boolean a(Context context, Intent intent) {
        if (context != null && intent != null) {
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                if (this.e) {
                    this.e = false;
                    return true;
                }
                String strE = com.tencent.bugly.crashreport.common.info.b.e(this.b);
                x.c("is Connect BC " + strE, new Object[0]);
                x.a("network %s changed to %s", this.c, strE);
                if (strE == null) {
                    this.c = null;
                    return true;
                }
                String str = this.c;
                this.c = strE;
                long jCurrentTimeMillis = System.currentTimeMillis();
                com.tencent.bugly.crashreport.common.strategy.a aVarA = com.tencent.bugly.crashreport.common.strategy.a.a();
                u uVarA = u.a();
                com.tencent.bugly.crashreport.common.info.a aVarA2 = com.tencent.bugly.crashreport.common.info.a.a(context);
                if (aVarA != null && uVarA != null && aVarA2 != null) {
                    if (!strE.equals(str)) {
                        if (jCurrentTimeMillis - uVarA.a(c.a) > com.umeng.commonsdk.proguard.b.d) {
                            x.a("try to upload crash on network changed.", new Object[0]);
                            c cVarA = c.a();
                            if (cVarA != null) {
                                cVarA.a(0L);
                            }
                        }
                        if (jCurrentTimeMillis - uVarA.a(1001) > com.umeng.commonsdk.proguard.b.d) {
                            x.a("try to upload userinfo on network changed.", new Object[0]);
                            com.tencent.bugly.crashreport.biz.b.a.b();
                        }
                    }
                    return true;
                }
                x.d("not inited BC not work", new Object[0]);
                return true;
            }
        }
        return false;
    }
}
