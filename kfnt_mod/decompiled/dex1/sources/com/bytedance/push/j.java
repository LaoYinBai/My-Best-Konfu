package com.bytedance.push;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.push.settings.LocalFrequencySettings;
import com.bytedance.push.settings.LocalSettings;
import com.bytedance.push.settings.SettingsManager;
import com.bytedance.push.third.PushManager;
import com.ss.android.message.NotifyService;
import com.ss.android.pushmanager.a.a;
import com.ss.android.pushmanager.setting.PushSetting;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class j implements com.bytedance.push.c.i {

    /* renamed from: a, reason: collision with root package name */
    private Map<Integer, Boolean> f4850a = new ConcurrentHashMap();

    /* renamed from: b, reason: collision with root package name */
    private com.bytedance.push.h.c f4851b = new com.bytedance.push.h.c(this);

    j() {
    }

    @Override // com.bytedance.push.c.i
    public boolean a(Context context) {
        boolean zF = f(context);
        if (PushSetting.getInstance().isPushNotifyEnable()) {
            Iterator it = com.bytedance.push.third.e.a(context).b().iterator();
            while (it.hasNext()) {
                zF |= a(context, ((Integer) it.next()).intValue());
            }
            g(context.getApplicationContext());
        } else {
            b(context);
        }
        return zF;
    }

    @Override // com.bytedance.push.c.i
    public void a(final boolean z) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            com.bytedance.common.push.utility.b.b.a(new Runnable() { // from class: com.bytedance.push.j.1
                @Override // java.lang.Runnable
                public void run() {
                    j.this.f4851b.a(z);
                }
            });
        } else {
            this.f4851b.a(z);
        }
    }

    @Override // com.bytedance.push.c.i
    public void a() {
        Application applicationA = com.ss.android.message.a.a();
        e(applicationA);
        d(applicationA);
    }

    private void d(final Context context) {
        try {
            ((LocalSettings) SettingsManager.obtain(context, LocalSettings.class)).registerValChanged(context, "ali_push_type", "integer", new com.bytedance.push.settings.a() { // from class: com.bytedance.push.j.2
                @Override // com.bytedance.push.settings.a
                public void a() {
                    j.this.e(context);
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Context context) {
        int aliPushType = PushSetting.getInstance().getAliPushType();
        if (aliPushType > -1) {
            com.bytedance.push.utils.e.c("registerAliPush: aliPushType = " + aliPushType);
            d(context, aliPushType);
        }
    }

    private boolean f(Context context) {
        int iE;
        boolean zA = false;
        try {
            if (com.bytedance.push.third.e.c(com.bytedance.push.third.e.a(context).e())) {
                if (com.bytedance.push.utils.e.a()) {
                    com.bytedance.push.utils.e.a("PushStart", "registerUmPush process = " + com.ss.android.message.a.b.a(context));
                }
                iE = com.bytedance.push.third.e.a(context).e();
                zA = a(context, com.bytedance.push.third.e.a(context).e());
            } else {
                iE = -1;
            }
            PushSetting.getInstance().setAliPushType(iE);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return zA;
    }

    private boolean a(Context context, int i) {
        if (context == null || PushSetting.getInstance().isShutPushNotifyEnable()) {
            return false;
        }
        Context applicationContext = context.getApplicationContext();
        if (!com.bytedance.push.third.e.c(i) || !b(applicationContext, i)) {
            return false;
        }
        synchronized (this) {
            Boolean bool = this.f4850a.get(Integer.valueOf(i));
            if (bool != null && bool.booleanValue()) {
                return true;
            }
            this.f4850a.put(Integer.valueOf(i), true);
            return d(applicationContext, i);
        }
    }

    private boolean b(Context context, int i) {
        if (context == null) {
            return false;
        }
        return PushManager.inst().isPushAvailable(context, i);
    }

    private void c(Context context, int i) {
        if (context == null) {
            return;
        }
        PushManager.inst().unregisterPush(context.getApplicationContext(), i);
    }

    private boolean d(Context context, int i) {
        if (!com.bytedance.push.third.e.c(i) || context == null) {
            return false;
        }
        PushManager.inst().registerPush(context, i);
        return true;
    }

    @Override // com.bytedance.push.c.i
    public void b(Context context) {
        Iterator it = com.bytedance.push.third.e.a(context).b().iterator();
        while (it.hasNext()) {
            c(context, ((Integer) it.next()).intValue());
        }
        this.f4850a.clear();
    }

    @Override // com.bytedance.push.c.i
    public void a(Context context, String str, int i) {
        PushManager.inst().setAlias(context, str, i);
    }

    @Override // com.bytedance.push.c.i
    public boolean c(Context context) {
        return !TextUtils.equals(com.bytedance.push.third.e.a(context).c().toString(), ((LocalFrequencySettings) SettingsManager.obtain(context, LocalFrequencySettings.class)).f());
    }

    private void g(final Context context) {
        Intent intent = new Intent(context, (Class<?>) NotifyService.class);
        try {
            if (com.ss.android.b.a.b.a(a.b.class).g()) {
                context.startService(intent);
            }
            context.bindService(intent, new ServiceConnection() { // from class: com.bytedance.push.j.3
                @Override // android.content.ServiceConnection
                public void onServiceDisconnected(ComponentName componentName) {
                }

                @Override // android.content.ServiceConnection
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    try {
                        context.unbindService(this);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }, 1);
        } catch (Throwable unused) {
            Log.e("SenderService", "start NotifyService失败");
        }
    }
}
