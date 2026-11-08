package com.xiaomi.push;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.ss.android.socialbase.appdownloader.constants.Constants;
import com.xiaomi.push.ai;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class dt {
    private static volatile dt a;

    /* renamed from: a, reason: collision with other field name */
    private Context f248a;

    private dt(Context context) {
        this.f248a = context;
    }

    private int a(int i) {
        return Math.max(60, i);
    }

    public static dt a(Context context) {
        if (a == null) {
            synchronized (dt.class) {
                if (a == null) {
                    a = new dt(context);
                }
            }
        }
        return a;
    }

    private void a(com.xiaomi.push.service.aq aqVar, ai aiVar, boolean z) {
        if (aqVar.a(ho.UploadSwitch.a(), true)) {
            dz dzVar = new dz(this.f248a);
            if (z) {
                aiVar.a((ai.a) dzVar, a(aqVar.a(ho.UploadFrequency.a(), Constants.MAX_RETRY_AFTER)));
            } else {
                aiVar.m93a((ai.a) dzVar);
            }
        }
    }

    private boolean a() {
        if (Build.VERSION.SDK_INT >= 14) {
            try {
                ((Application) (this.f248a instanceof Application ? this.f248a : this.f248a.getApplicationContext())).registerActivityLifecycleCallbacks(new dj(this.f248a, String.valueOf(System.currentTimeMillis() / 1000)));
                return true;
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.a(e);
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        ai aiVarA = ai.a(this.f248a);
        com.xiaomi.push.service.aq aqVarA = com.xiaomi.push.service.aq.a(this.f248a);
        SharedPreferences sharedPreferences = this.f248a.getSharedPreferences("mipush_extra", 0);
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = sharedPreferences.getLong("first_try_ts", jCurrentTimeMillis);
        if (j == jCurrentTimeMillis) {
            sharedPreferences.edit().putLong("first_try_ts", jCurrentTimeMillis).commit();
        }
        if (Math.abs(jCurrentTimeMillis - j) < 172800000) {
            return;
        }
        a(aqVarA, aiVarA, false);
        if (aqVarA.a(ho.StorageCollectionSwitch.a(), true)) {
            int iA = a(aqVarA.a(ho.StorageCollectionFrequency.a(), Constants.MAX_RETRY_AFTER));
            aiVarA.a(new dy(this.f248a, iA), iA, 0);
        }
        boolean zA = aqVarA.a(ho.AppIsInstalledCollectionSwitch.a(), false);
        String strA = aqVarA.a(ho.AppIsInstalledList.a(), (String) null);
        if (zA && !TextUtils.isEmpty(strA)) {
            int iA2 = a(aqVarA.a(ho.AppIsInstalledCollectionFrequency.a(), Constants.MAX_RETRY_AFTER));
            aiVarA.a(new dv(this.f248a, iA2, strA), iA2, 0);
        }
        if (aqVarA.a(ho.BroadcastActionCollectionSwitch.a(), true)) {
            int iA3 = a(aqVarA.a(ho.BroadcastActionCollectionFrequency.a(), 900));
            aiVarA.a(new dw(this.f248a, iA3), iA3, 0);
        }
        if (aqVarA.a(ho.ActivityTSSwitch.a(), false)) {
            a();
        }
        a(aqVarA, aiVarA, true);
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m204a() {
        ai.a(this.f248a).a(new du(this));
    }
}
