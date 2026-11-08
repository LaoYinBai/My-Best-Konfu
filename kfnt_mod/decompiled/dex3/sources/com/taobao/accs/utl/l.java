package com.taobao.accs.utl;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.taobao.accs.common.ThreadPoolExecutorFactory;
import com.taobao.accs.init.Launcher_InitAccs;
import com.taobao.agoo.TaobaoRegister;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class l implements Application.ActivityLifecycleCallbacks {
    public static final String ACTION_STATE_BACK = "com.taobao.accs.ACTION_STATE_BACK";
    public static final String ACTION_STATE_DEEPBACK = "com.taobao.accs.ACTION_STATE_DEEPBACK";
    public static final String ACTION_STATE_FORE = "com.taobao.accs.ACTION_STATE_FORE";
    public static final int STATE_BACK = 0;
    public static final int STATE_DEEPBACK = 2;
    public static final int STATE_FORE = 1;
    private static final String a = "l";
    private static volatile l b;
    private static ArrayList<a> c;
    private static Context d;
    private boolean f;
    private boolean g;
    private long h;
    private int e = 0;
    private int i = 0;
    private int j = 1;
    private Handler k = new Handler(Looper.getMainLooper());
    private Runnable l = new m(this);

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public static l a() {
        if (b == null) {
            synchronized (l.class) {
                if (b == null) {
                    b = new l();
                }
            }
        }
        return b;
    }

    private l() {
        c = new ArrayList<>();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (this.g) {
            if ((this.e & 1) != 1) {
                this.e |= 1;
                this.e |= 2;
            } else if ((this.e & 2) == 2) {
                this.e &= -3;
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        this.h = System.currentTimeMillis();
        long jA = t.a(d);
        int i = this.i;
        this.i = i + 1;
        if (i == 0) {
            ALog.i(a, "onActivityStarted back to force", new Object[0]);
            this.k.removeCallbacks(this.l);
            this.f = true;
            boolean z = this.j == 2;
            this.j = 1;
            Intent intent = new Intent(new Intent(ACTION_STATE_FORE));
            intent.putExtra("state", z);
            LocalBroadcastManager.getInstance(d).sendBroadcast(intent);
        }
        if (this.g) {
            int i2 = (this.f ? 4 : 0) | ((this.e & 2) != 2 ? 1 : 2);
            if (activity == null || activity.getIntent() == null) {
                return;
            }
            try {
                Intent intent2 = activity.getIntent();
                String stringExtra = intent2.getStringExtra("AliAgooMsgID");
                if (!TextUtils.isEmpty(stringExtra)) {
                    ALog.i(a, "onActivityStarted isFromAgoo", new Object[0]);
                    intent2.removeExtra("AliAgooMsgID");
                    a aVar = new a(stringExtra, i2, this.h, jA);
                    if (TaobaoRegister.isRegisterSuccess() || Launcher_InitAccs.mIsInited) {
                        ThreadPoolExecutorFactory.execute(aVar);
                    } else {
                        ThreadPoolExecutorFactory.getScheduledExecutor().execute(new n(this, aVar));
                    }
                }
            } catch (Exception e) {
                ALog.e(a, "onActivityStarted Error:", e, new Object[0]);
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        if (this.f) {
            t.a(d, this.h);
        }
        this.f = false;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        int i = this.i - 1;
        this.i = i;
        if (i == 0) {
            this.j = 0;
            this.k.postDelayed(this.l, DownloadConstants.DEFAULT_NEXT_INSTALL_MIN_INTERVAL);
            LocalBroadcastManager.getInstance(d).sendBroadcast(new Intent(ACTION_STATE_BACK));
        }
    }

    public void a(Application application) {
        if (d == null) {
            d = application;
            this.g = t.e();
            application.registerActivityLifecycleCallbacks(this);
        }
    }

    public void b() {
        if (c != null) {
            Iterator<a> it = c.iterator();
            while (it.hasNext()) {
                ThreadPoolExecutorFactory.getScheduledExecutor().execute(it.next());
            }
            c.clear();
        }
    }

    public int c() {
        return this.j;
    }

    public static class a implements Runnable {
        private String a;
        private int b;
        private long c;
        private long d;

        public a(String str, int i, long j, long j2) {
            this.a = str;
            this.b = i;
            this.c = j;
            this.d = j2;
        }

        @Override // java.lang.Runnable
        public void run() {
            ALog.e(l.a, "click report", "lastActiveTime", Long.valueOf(this.d), "currentActiveTime", Long.valueOf(this.c));
            if (this.d == 0 || UtilityImpl.a(this.d, this.c)) {
                this.b |= 8;
            }
            TaobaoRegister.clickMessage(l.d, this.a, null, this.b, this.d);
        }
    }
}
