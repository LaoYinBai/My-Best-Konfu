package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class bh {
    private static bh a;

    /* renamed from: a, reason: collision with other field name */
    private static String f921a;

    /* renamed from: a, reason: collision with other field name */
    private Context f922a;

    /* renamed from: a, reason: collision with other field name */
    private boolean f925a;
    private Messenger b;

    /* renamed from: a, reason: collision with other field name */
    private List<Message> f924a = new ArrayList();

    /* renamed from: b, reason: collision with other field name */
    private boolean f926b = false;

    /* renamed from: a, reason: collision with other field name */
    private Messenger f923a = new Messenger(new bi(this, Looper.getMainLooper()));

    private bh(Context context) {
        this.f925a = false;
        this.f922a = context.getApplicationContext();
        if (a()) {
            com.xiaomi.channel.commonutils.logger.b.c("use miui push service");
            this.f925a = true;
        }
    }

    private Message a(Intent intent) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 17;
        messageObtain.obj = intent;
        return messageObtain;
    }

    public static bh a(Context context) {
        if (a == null) {
            a = new bh(context);
        }
        return a;
    }

    /* renamed from: a, reason: collision with other method in class */
    private synchronized void m617a(Intent intent) {
        if (this.f926b) {
            Message messageA = a(intent);
            if (this.f924a.size() >= 50) {
                this.f924a.remove(0);
            }
            this.f924a.add(messageA);
            return;
        }
        if (this.b == null) {
            Context context = this.f922a;
            bj bjVar = new bj(this);
            Context context2 = this.f922a;
            context.bindService(intent, bjVar, 1);
            this.f926b = true;
            this.f924a.clear();
            this.f924a.add(a(intent));
        } else {
            try {
                this.b.send(a(intent));
            } catch (RemoteException unused) {
                this.b = null;
                this.f926b = false;
            }
        }
    }

    private boolean a() throws PackageManager.NameNotFoundException {
        if (com.xiaomi.push.ab.e) {
            return false;
        }
        try {
            PackageInfo packageInfo = this.f922a.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4);
            if (packageInfo == null) {
                return false;
            }
            return packageInfo.versionCode >= 104;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m618a(Intent intent) {
        try {
            if (com.xiaomi.push.l.m547a() || Build.VERSION.SDK_INT < 26) {
                this.f922a.startService(intent);
                return true;
            }
            m617a(intent);
            return true;
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            return false;
        }
    }
}
