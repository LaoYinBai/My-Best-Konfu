package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import com.ss.android.downloadlib.applink.AppLinkMonitor;
import com.xiaomi.push.Cif;
import com.xiaomi.push.bg;
import com.xiaomi.push.bl;
import com.xiaomi.push.da;
import com.xiaomi.push.es;
import com.xiaomi.push.hj;
import com.xiaomi.push.hk;
import com.xiaomi.push.hn;
import com.xiaomi.push.ho;
import com.xiaomi.push.ht;
import com.xiaomi.push.hw;
import com.xiaomi.push.ii;
import com.xiaomi.push.ij;
import com.xiaomi.push.ip;
import com.xiaomi.push.it;
import com.xiaomi.push.iu;
import com.xiaomi.push.service.az;
import com.xiaomi.push.service.bc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class an {
    private static an a;

    /* renamed from: a, reason: collision with other field name */
    private static final ArrayList<a> f66a = new ArrayList<>();
    private static boolean b;

    /* renamed from: a, reason: collision with other field name */
    private Context f67a;

    /* renamed from: a, reason: collision with other field name */
    private Handler f69a;

    /* renamed from: a, reason: collision with other field name */
    private Messenger f70a;

    /* renamed from: a, reason: collision with other field name */
    private boolean f74a;

    /* renamed from: a, reason: collision with other field name */
    private List<Message> f73a = new ArrayList();
    private boolean c = false;

    /* renamed from: a, reason: collision with other field name */
    private Intent f68a = null;

    /* renamed from: a, reason: collision with other field name */
    private Integer f71a = null;

    /* renamed from: a, reason: collision with other field name */
    private String f72a = null;

    static class a<T extends iu<T, ?>> {
        hj a;

        /* renamed from: a, reason: collision with other field name */
        T f75a;

        /* renamed from: a, reason: collision with other field name */
        boolean f76a;

        a() {
        }
    }

    private an(Context context) {
        this.f74a = false;
        this.f69a = null;
        this.f67a = context.getApplicationContext();
        this.f74a = m51c();
        b = m52d();
        this.f69a = new ao(this, Looper.getMainLooper());
        if (com.xiaomi.push.l.m548a(context)) {
            com.xiaomi.push.service.d.a(new ap(this));
        }
        Intent intentB = b();
        if (intentB != null) {
            b(intentB);
        }
    }

    private synchronized int a() {
        return this.f67a.getSharedPreferences("mipush_extra", 0).getInt(Constants.EXTRA_KEY_BOOT_SERVICE_MODE, -1);
    }

    /* renamed from: a, reason: collision with other method in class */
    private Intent m46a() {
        return (!m56a() || "com.xiaomi.xmsf".equals(this.f67a.getPackageName())) ? e() : d();
    }

    private Message a(Intent intent) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 17;
        messageObtain.obj = intent;
        return messageObtain;
    }

    public static synchronized an a(Context context) {
        if (a == null) {
            a = new an(context);
        }
        return a;
    }

    /* renamed from: a, reason: collision with other method in class */
    private String m49a() {
        try {
            return this.f67a.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4).versionCode >= 106 ? "com.xiaomi.push.service.XMPushService" : "com.xiaomi.xmsf.push.service.XMPushService";
        } catch (Exception unused) {
            return "com.xiaomi.xmsf.push.service.XMPushService";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, at atVar, boolean z, HashMap<String, String> map) {
        ii iiVar;
        String str2;
        if (b.m63a(this.f67a).m70b() && bg.b(this.f67a)) {
            ii iiVar2 = new ii();
            iiVar2.a(true);
            Intent intentM46a = m46a();
            if (TextUtils.isEmpty(str)) {
                str = com.xiaomi.push.service.at.a();
                iiVar2.a(str);
                iiVar = z ? new ii(str, true) : null;
                synchronized (ae.class) {
                    ae.a(this.f67a).m42a(str);
                }
            } else {
                iiVar2.a(str);
                iiVar = z ? new ii(str, true) : null;
            }
            switch (as.a[atVar.ordinal()]) {
                case 1:
                    iiVar2.c(ht.DisablePushMessage.f509a);
                    iiVar.c(ht.DisablePushMessage.f509a);
                    if (map != null) {
                        iiVar2.a(map);
                        iiVar.a(map);
                    }
                    str2 = "com.xiaomi.mipush.DISABLE_PUSH_MESSAGE";
                    intentM46a.setAction(str2);
                    break;
                case 2:
                    iiVar2.c(ht.EnablePushMessage.f509a);
                    iiVar.c(ht.EnablePushMessage.f509a);
                    if (map != null) {
                        iiVar2.a(map);
                        iiVar.a(map);
                    }
                    str2 = "com.xiaomi.mipush.ENABLE_PUSH_MESSAGE";
                    intentM46a.setAction(str2);
                    break;
                case 3:
                case 4:
                case 5:
                case 6:
                    iiVar2.c(ht.ThirdPartyRegUpdate.f509a);
                    if (map != null) {
                        iiVar2.a(map);
                        break;
                    }
                    break;
            }
            com.xiaomi.channel.commonutils.logger.b.e("type:" + atVar + ", " + str);
            iiVar2.b(b.m63a(this.f67a).m64a());
            iiVar2.d(this.f67a.getPackageName());
            a((an) iiVar2, hj.Notification, false, (hw) null);
            if (z) {
                iiVar.b(b.m63a(this.f67a).m64a());
                iiVar.d(this.f67a.getPackageName());
                byte[] bArrA = it.a(ah.a(this.f67a, iiVar, hj.Notification, false, this.f67a.getPackageName(), b.m63a(this.f67a).m64a()));
                if (bArrA != null) {
                    da.a(this.f67a.getPackageName(), this.f67a, iiVar, hj.Notification, bArrA.length);
                    intentM46a.putExtra("mipush_payload", bArrA);
                    intentM46a.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
                    intentM46a.putExtra("mipush_app_id", b.m63a(this.f67a).m64a());
                    intentM46a.putExtra("mipush_app_token", b.m63a(this.f67a).b());
                    c(intentM46a);
                }
            }
            Message messageObtain = Message.obtain();
            messageObtain.what = 19;
            int iOrdinal = atVar.ordinal();
            messageObtain.obj = str;
            messageObtain.arg1 = iOrdinal;
            this.f69a.sendMessageDelayed(messageObtain, AppLinkMonitor.DELAY_TIME);
        }
    }

    private Intent b() {
        if (!"com.xiaomi.xmsf".equals(this.f67a.getPackageName())) {
            return c();
        }
        com.xiaomi.channel.commonutils.logger.b.c("pushChannel xmsf create own channel");
        return e();
    }

    private void b(Intent intent) {
        try {
            if (com.xiaomi.push.l.m547a() || Build.VERSION.SDK_INT < 26) {
                this.f67a.startService(intent);
            } else {
                d(intent);
            }
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
        }
    }

    private Intent c() {
        if (m56a()) {
            com.xiaomi.channel.commonutils.logger.b.c("pushChannel app start miui china channel");
            return d();
        }
        com.xiaomi.channel.commonutils.logger.b.c("pushChannel app start  own channel");
        return e();
    }

    private synchronized void c(int i) {
        this.f67a.getSharedPreferences("mipush_extra", 0).edit().putInt(Constants.EXTRA_KEY_BOOT_SERVICE_MODE, i).commit();
    }

    private void c(Intent intent) {
        int iA = com.xiaomi.push.service.aq.a(this.f67a).a(ho.ServiceBootMode.a(), hk.START.a());
        int iA2 = a();
        boolean z = iA == hk.BIND.a() && b;
        int iA3 = (z ? hk.BIND : hk.START).a();
        if (iA3 != iA2) {
            m57a(iA3);
        }
        if (z) {
            d(intent);
        } else {
            b(intent);
        }
    }

    /* renamed from: c, reason: collision with other method in class */
    private boolean m51c() {
        try {
            PackageInfo packageInfo = this.f67a.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4);
            if (packageInfo == null) {
                return false;
            }
            return packageInfo.versionCode >= 105;
        } catch (Throwable unused) {
            return false;
        }
    }

    private Intent d() {
        Intent intent = new Intent();
        String packageName = this.f67a.getPackageName();
        intent.setPackage("com.xiaomi.xmsf");
        intent.setClassName("com.xiaomi.xmsf", m49a());
        intent.putExtra("mipush_app_package", packageName);
        f();
        return intent;
    }

    private synchronized void d(Intent intent) {
        if (this.c) {
            Message messageA = a(intent);
            if (this.f73a.size() >= 50) {
                this.f73a.remove(0);
            }
            this.f73a.add(messageA);
            return;
        }
        if (this.f70a == null) {
            Context context = this.f67a;
            ar arVar = new ar(this);
            Context context2 = this.f67a;
            context.bindService(intent, arVar, 1);
            this.c = true;
            this.f73a.clear();
            this.f73a.add(a(intent));
        } else {
            try {
                this.f70a.send(a(intent));
            } catch (RemoteException unused) {
                this.f70a = null;
                this.c = false;
            }
        }
    }

    /* renamed from: d, reason: collision with other method in class */
    private boolean m52d() {
        if (m56a()) {
            try {
                return this.f67a.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4).versionCode >= 108;
            } catch (Exception unused) {
            }
        }
        return true;
    }

    private Intent e() {
        Intent intent = new Intent();
        String packageName = this.f67a.getPackageName();
        g();
        intent.setComponent(new ComponentName(this.f67a, "com.xiaomi.push.service.XMPushService"));
        intent.putExtra("mipush_app_package", packageName);
        return intent;
    }

    /* renamed from: e, reason: collision with other method in class */
    private boolean m53e() {
        String packageName = this.f67a.getPackageName();
        return packageName.contains("miui") || packageName.contains("xiaomi") || (this.f67a.getApplicationInfo().flags & 1) != 0;
    }

    private void f() {
        try {
            PackageManager packageManager = this.f67a.getPackageManager();
            ComponentName componentName = new ComponentName(this.f67a, "com.xiaomi.push.service.XMPushService");
            if (packageManager.getComponentEnabledSetting(componentName) == 2) {
                return;
            }
            packageManager.setComponentEnabledSetting(componentName, 2, 1);
        } catch (Throwable unused) {
        }
    }

    private void g() {
        try {
            PackageManager packageManager = this.f67a.getPackageManager();
            ComponentName componentName = new ComponentName(this.f67a, "com.xiaomi.push.service.XMPushService");
            if (packageManager.getComponentEnabledSetting(componentName) == 1) {
                return;
            }
            packageManager.setComponentEnabledSetting(componentName, 1, 1);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m54a() {
        b(m46a());
    }

    public void a(int i) {
        Intent intentM46a = m46a();
        intentM46a.setAction("com.xiaomi.mipush.CLEAR_NOTIFICATION");
        intentM46a.putExtra(az.z, this.f67a.getPackageName());
        intentM46a.putExtra(az.A, i);
        c(intentM46a);
    }

    void a(int i, String str) {
        Intent intentM46a = m46a();
        intentM46a.setAction("com.xiaomi.mipush.thirdparty");
        intentM46a.putExtra("com.xiaomi.mipush.thirdparty_LEVEL", i);
        intentM46a.putExtra("com.xiaomi.mipush.thirdparty_DESC", str);
        b(intentM46a);
    }

    /* renamed from: a, reason: collision with other method in class */
    void m55a(Intent intent) {
        intent.fillIn(m46a(), 24);
        c(intent);
    }

    public final void a(hn hnVar) {
        Intent intentM46a = m46a();
        byte[] bArrA = it.a(hnVar);
        if (bArrA == null) {
            com.xiaomi.channel.commonutils.logger.b.m22a("send TinyData failed, because tinyDataBytes is null.");
            return;
        }
        intentM46a.setAction("com.xiaomi.mipush.SEND_TINYDATA");
        intentM46a.putExtra("mipush_payload", bArrA);
        b(intentM46a);
    }

    public final void a(ij ijVar, boolean z) {
        es.a(this.f67a.getApplicationContext()).a(this.f67a.getPackageName(), "E100003", ijVar.a(), 6001, null);
        this.f68a = null;
        b.m63a(this.f67a).f81a = ijVar.a();
        Intent intentM46a = m46a();
        byte[] bArrA = it.a(ah.a(this.f67a, ijVar, hj.Registration));
        if (bArrA == null) {
            com.xiaomi.channel.commonutils.logger.b.m22a("register fail, because msgBytes is null.");
            return;
        }
        intentM46a.setAction("com.xiaomi.mipush.REGISTER_APP");
        intentM46a.putExtra("mipush_app_id", b.m63a(this.f67a).m64a());
        intentM46a.putExtra("mipush_payload", bArrA);
        intentM46a.putExtra("mipush_session", this.f72a);
        intentM46a.putExtra("mipush_env_chanage", z);
        intentM46a.putExtra("mipush_env_type", b.m63a(this.f67a).a());
        if (bg.b(this.f67a) && m59b()) {
            c(intentM46a);
        } else {
            this.f68a = intentM46a;
        }
    }

    public final void a(ip ipVar) {
        byte[] bArrA = it.a(ah.a(this.f67a, ipVar, hj.UnRegistration));
        if (bArrA == null) {
            com.xiaomi.channel.commonutils.logger.b.m22a("unregister fail, because msgBytes is null.");
            return;
        }
        Intent intentM46a = m46a();
        intentM46a.setAction("com.xiaomi.mipush.UNREGISTER_APP");
        intentM46a.putExtra("mipush_app_id", b.m63a(this.f67a).m64a());
        intentM46a.putExtra("mipush_payload", bArrA);
        c(intentM46a);
    }

    public final <T extends iu<T, ?>> void a(T t, hj hjVar, hw hwVar) {
        a((an) t, hjVar, !hjVar.equals(hj.Registration), hwVar);
    }

    public <T extends iu<T, ?>> void a(T t, hj hjVar, boolean z) {
        a aVar = new a();
        aVar.f75a = t;
        aVar.a = hjVar;
        aVar.f76a = z;
        synchronized (f66a) {
            f66a.add(aVar);
            if (f66a.size() > 10) {
                f66a.remove(0);
            }
        }
    }

    public final <T extends iu<T, ?>> void a(T t, hj hjVar, boolean z, hw hwVar) {
        a(t, hjVar, z, true, hwVar, true);
    }

    public final <T extends iu<T, ?>> void a(T t, hj hjVar, boolean z, hw hwVar, boolean z2) {
        a(t, hjVar, z, true, hwVar, z2);
    }

    public final <T extends iu<T, ?>> void a(T t, hj hjVar, boolean z, boolean z2, hw hwVar, boolean z3) {
        a(t, hjVar, z, z2, hwVar, z3, this.f67a.getPackageName(), b.m63a(this.f67a).m64a());
    }

    public final <T extends iu<T, ?>> void a(T t, hj hjVar, boolean z, boolean z2, hw hwVar, boolean z3, String str, String str2) {
        a(t, hjVar, z, z2, hwVar, z3, str, str2, true);
    }

    public final <T extends iu<T, ?>> void a(T t, hj hjVar, boolean z, boolean z2, hw hwVar, boolean z3, String str, String str2, boolean z4) {
        if (!b.m63a(this.f67a).m72c()) {
            if (z2) {
                a((an) t, hjVar, z);
                return;
            } else {
                com.xiaomi.channel.commonutils.logger.b.m22a("drop the message before initialization.");
                return;
            }
        }
        Cif cifA = z4 ? ah.a(this.f67a, t, hjVar, z, str, str2) : ah.b(this.f67a, t, hjVar, z, str, str2);
        if (hwVar != null) {
            cifA.a(hwVar);
        }
        byte[] bArrA = it.a(cifA);
        if (bArrA == null) {
            com.xiaomi.channel.commonutils.logger.b.m22a("send message fail, because msgBytes is null.");
            return;
        }
        da.a(this.f67a.getPackageName(), this.f67a, t, hjVar, bArrA.length);
        Intent intentM46a = m46a();
        intentM46a.setAction("com.xiaomi.mipush.SEND_MESSAGE");
        intentM46a.putExtra("mipush_payload", bArrA);
        intentM46a.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", z3);
        c(intentM46a);
    }

    public final void a(String str, at atVar, e eVar) {
        ae.a(this.f67a).a(atVar, "syncing");
        a(str, atVar, false, i.a(this.f67a, eVar));
    }

    public void a(String str, String str2) {
        Intent intentM46a = m46a();
        intentM46a.setAction("com.xiaomi.mipush.CLEAR_NOTIFICATION");
        intentM46a.putExtra(az.z, this.f67a.getPackageName());
        intentM46a.putExtra(az.E, str);
        intentM46a.putExtra(az.F, str2);
        c(intentM46a);
    }

    public final void a(boolean z) {
        a(z, (String) null);
    }

    public final void a(boolean z, String str) {
        at atVar;
        if (z) {
            ae.a(this.f67a).a(at.DISABLE_PUSH, "syncing");
            ae.a(this.f67a).a(at.ENABLE_PUSH, "");
            atVar = at.DISABLE_PUSH;
        } else {
            ae.a(this.f67a).a(at.ENABLE_PUSH, "syncing");
            ae.a(this.f67a).a(at.DISABLE_PUSH, "");
            atVar = at.ENABLE_PUSH;
        }
        a(str, atVar, true, (HashMap<String, String>) null);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m56a() {
        return this.f74a && 1 == b.m63a(this.f67a).a();
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m57a(int i) {
        if (!b.m63a(this.f67a).m70b()) {
            return false;
        }
        c(i);
        ii iiVar = new ii();
        iiVar.a(com.xiaomi.push.service.at.a());
        iiVar.b(b.m63a(this.f67a).m64a());
        iiVar.d(this.f67a.getPackageName());
        iiVar.c(ht.ClientABTest.f509a);
        iiVar.f650a = new HashMap();
        iiVar.f650a.put("boot_mode", i + "");
        a(this.f67a).a((an) iiVar, hj.Notification, false, (hw) null);
        return true;
    }

    /* renamed from: b, reason: collision with other method in class */
    public final void m58b() {
        Intent intentM46a = m46a();
        intentM46a.setAction("com.xiaomi.mipush.DISABLE_PUSH");
        c(intentM46a);
    }

    public void b(int i) {
        Intent intentM46a = m46a();
        intentM46a.setAction("com.xiaomi.mipush.SET_NOTIFICATION_TYPE");
        intentM46a.putExtra(az.z, this.f67a.getPackageName());
        intentM46a.putExtra(az.B, i);
        intentM46a.putExtra(az.D, bl.b(this.f67a.getPackageName() + i));
        c(intentM46a);
    }

    /* renamed from: b, reason: collision with other method in class */
    public boolean m59b() {
        if (!m56a() || !m53e()) {
            return true;
        }
        if (this.f71a == null) {
            this.f71a = Integer.valueOf(bc.a(this.f67a).a());
            if (this.f71a.intValue() == 0) {
                this.f67a.getContentResolver().registerContentObserver(bc.a(this.f67a).m612a(), false, new aq(this, new Handler(Looper.getMainLooper())));
            }
        }
        return this.f71a.intValue() != 0;
    }

    /* renamed from: c, reason: collision with other method in class */
    public void m60c() {
        if (this.f68a != null) {
            c(this.f68a);
            this.f68a = null;
        }
    }

    /* renamed from: d, reason: collision with other method in class */
    public void m61d() {
        synchronized (f66a) {
            boolean z = Thread.currentThread() == Looper.getMainLooper().getThread();
            Iterator<a> it = f66a.iterator();
            while (it.hasNext()) {
                a next = it.next();
                a(next.f75a, next.a, next.f76a, false, null, true);
                if (!z) {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException unused) {
                    }
                }
            }
            f66a.clear();
        }
    }

    /* renamed from: e, reason: collision with other method in class */
    public void m62e() {
        Intent intentM46a = m46a();
        intentM46a.setAction("com.xiaomi.mipush.SET_NOTIFICATION_TYPE");
        intentM46a.putExtra(az.z, this.f67a.getPackageName());
        intentM46a.putExtra(az.D, bl.b(this.f67a.getPackageName()));
        c(intentM46a);
    }
}
