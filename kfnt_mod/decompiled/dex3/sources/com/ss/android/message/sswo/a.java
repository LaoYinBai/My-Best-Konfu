package com.ss.android.message.sswo;

import android.content.Context;
import android.content.IntentFilter;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.PowerManager;
import android.view.Display;
import com.bytedance.push.settings.AliveOnlineSettings;
import com.bytedance.push.settings.SettingsManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static volatile a c;
    private Context a;
    private SswoReceiver b = new SswoReceiver();

    private a(Context context) {
        this.a = context.getApplicationContext();
    }

    public static a a(Context context) {
        if (c == null) {
            synchronized (a.class) {
                if (c == null) {
                    c = new a(context);
                }
            }
        }
        return c;
    }

    public void a() {
        try {
            if (!f()) {
                b();
                return;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            this.a.registerReceiver(this.b, intentFilter);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private boolean f() {
        return ((AliveOnlineSettings) SettingsManager.obtain(com.ss.android.message.a.a(), AliveOnlineSettings.class)).a();
    }

    public void b() {
        try {
            this.a.unregisterReceiver(this.b);
        } catch (Throwable unused) {
        }
    }

    public void c() {
        try {
            if (!f()) {
                d();
            } else {
                SswoActivity.a(this.a);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void d() {
        try {
            SswoActivity.b(this.a);
        } catch (Throwable unused) {
        }
    }

    public boolean e() {
        try {
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (Build.VERSION.SDK_INT >= 20) {
            Display[] displays = ((DisplayManager) this.a.getSystemService("display")).getDisplays();
            return displays != null && displays.length > 0 && 1 == displays[0].getState();
        }
        return !((PowerManager) this.a.getSystemService("power")).isScreenOn();
    }
}
