package com.ss.android.newmedia.redbadge;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.text.TextUtils;
import com.ss.android.newmedia.redbadge.a.h;
import com.ss.android.newmedia.redbadge.a.i;
import com.ss.android.newmedia.redbadge.a.j;
import com.ss.android.newmedia.redbadge.a.k;
import com.ss.android.newmedia.redbadge.a.l;
import com.ss.android.newmedia.redbadge.a.m;
import com.ss.android.newmedia.redbadge.a.n;
import com.ss.android.newmedia.redbadge.a.o;
import com.ss.android.newmedia.redbadge.a.p;
import com.ss.android.newmedia.redbadge.a.q;
import com.ss.android.newmedia.redbadge.a.r;
import com.ss.android.socialbase.appdownloader.util.RomUtils;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RedBadgerManager {
    private static final List<Class<? extends a>> BADGERS = new LinkedList();
    private static volatile RedBadgerManager sInstance;
    private ComponentName mComponentName;
    private a mRedBadger;

    static {
        BADGERS.add(com.ss.android.newmedia.redbadge.a.a.class);
        BADGERS.add(com.ss.android.newmedia.redbadge.a.b.class);
        BADGERS.add(h.class);
        BADGERS.add(i.class);
        BADGERS.add(m.class);
        BADGERS.add(p.class);
        BADGERS.add(com.ss.android.newmedia.redbadge.a.c.class);
        BADGERS.add(com.ss.android.newmedia.redbadge.a.f.class);
        BADGERS.add(j.class);
        BADGERS.add(n.class);
        BADGERS.add(q.class);
        BADGERS.add(r.class);
        BADGERS.add(k.class);
        BADGERS.add(com.ss.android.newmedia.redbadge.a.e.class);
        BADGERS.add(l.class);
        BADGERS.add(com.ss.android.newmedia.redbadge.a.g.class);
        BADGERS.add(o.class);
    }

    private RedBadgerManager() {
    }

    public static synchronized RedBadgerManager inst() {
        if (sInstance == null) {
            synchronized (RedBadgerManager.class) {
                if (sInstance == null) {
                    sInstance = new RedBadgerManager();
                }
            }
        }
        return sInstance;
    }

    public boolean applyCount(Context context, int i) {
        try {
            applyCountOrThrow(context, i);
            return true;
        } catch (d e) {
            if (!com.bytedance.common.push.utility.a.b()) {
                return false;
            }
            com.bytedance.common.push.utility.a.b("RedBadgerManager", "Unable to execute badge", e);
            return false;
        }
    }

    public void applyCountOrThrow(Context context, int i) throws d {
        if (this.mRedBadger == null && !initBadger(context)) {
            throw new d("No default launcher available");
        }
        try {
            this.mRedBadger.a(context, this.mComponentName, i);
        } catch (Exception e) {
            throw new d("Unable to execute badge", e);
        }
    }

    public boolean removeCount(Context context) {
        return applyCount(context, 0);
    }

    public void removeCountOrThrow(Context context) throws d {
        applyCountOrThrow(context, 0);
    }

    private boolean initBadger(Context context) {
        ResolveInfo resolveInfoResolveActivity;
        a aVarNewInstance;
        Intent launchIntentForPackage;
        boolean z = false;
        if (context == null) {
            return false;
        }
        try {
            try {
                launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            } catch (Throwable unused) {
                resolveInfoResolveActivity = null;
            }
            if (launchIntentForPackage == null) {
                com.bytedance.common.push.utility.a.b("RedBadgerManager", "Unable to find launch intent for package " + context.getPackageName());
                return false;
            }
            this.mComponentName = launchIntentForPackage.getComponent();
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            resolveInfoResolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
            if (resolveInfoResolveActivity != null && resolveInfoResolveActivity.activityInfo != null && !TextUtils.isEmpty(resolveInfoResolveActivity.activityInfo.name) && !resolveInfoResolveActivity.activityInfo.name.toLowerCase().contains("resolver")) {
                String str = resolveInfoResolveActivity.activityInfo.packageName;
                Iterator<Class<? extends a>> it = BADGERS.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    try {
                        aVarNewInstance = it.next().newInstance();
                    } catch (Throwable th) {
                        th.printStackTrace();
                        aVarNewInstance = null;
                    }
                    if (aVarNewInstance != null && aVarNewInstance.a().contains(str)) {
                        this.mRedBadger = aVarNewInstance;
                        z = true;
                        break;
                    }
                }
                if (this.mRedBadger != null) {
                    return z;
                }
                if (Build.MANUFACTURER.equalsIgnoreCase("OPPO")) {
                    this.mRedBadger = new j();
                } else if (Build.MANUFACTURER.equalsIgnoreCase(RomUtils.ROM_VIVO)) {
                    this.mRedBadger = new n();
                } else if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
                    this.mRedBadger = new p();
                } else if (Build.MANUFACTURER.equalsIgnoreCase("ZUK")) {
                    this.mRedBadger = new r();
                } else if (Build.MANUFACTURER.equalsIgnoreCase(RomUtils.ROM_ZTE)) {
                    this.mRedBadger = new q();
                } else if (Build.MANUFACTURER.equalsIgnoreCase("SONY")) {
                    this.mRedBadger = new m();
                } else if (Build.MANUFACTURER.equalsIgnoreCase("Samsung")) {
                    this.mRedBadger = new k();
                } else if (Build.MANUFACTURER.equalsIgnoreCase("HUAWEI") || Build.MANUFACTURER.equalsIgnoreCase("HONOR")) {
                    this.mRedBadger = new com.ss.android.newmedia.redbadge.a.f();
                } else {
                    this.mRedBadger = new com.ss.android.newmedia.redbadge.a.d();
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
