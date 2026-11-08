package com.a.b;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import com.bytedance.push.alive.c;
import com.ss.android.b.a.b;
import com.ss.android.message.MessageReceiver;
import com.ss.android.message.sswo.SswoActivity;
import com.ss.android.pushmanager.a.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AKeeperImpl implements c {

    /* renamed from: a, reason: collision with root package name */
    private static volatile AKeeperImpl f2306a;

    public static c a() {
        if (f2306a == null) {
            synchronized (AKeeperImpl.class) {
                if (f2306a == null) {
                    f2306a = new AKeeperImpl();
                }
            }
        }
        return f2306a;
    }

    @Override // com.bytedance.push.alive.c
    public void a(Context context, Handler handler) {
        com.bytedance.push.alive.a.a(context).a();
    }

    @Override // com.bytedance.push.alive.c
    public boolean b() {
        return SswoActivity.a();
    }

    @Override // com.bytedance.push.alive.c
    public boolean a(Context context, String str) throws PackageManager.NameNotFoundException {
        return a.a(context, str);
    }

    @Override // com.bytedance.push.alive.c
    public void a(Context context) {
        try {
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, (Class<?>) MessageReceiver.class), b.a(a.b.class).g() ? 2 : 1, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
