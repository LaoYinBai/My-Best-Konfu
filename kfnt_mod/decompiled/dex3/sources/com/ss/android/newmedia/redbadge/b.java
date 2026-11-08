package com.ss.android.newmedia.redbadge;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Message;
import android.text.format.DateUtils;
import com.bytedance.common.push.utility.a.c;
import com.bytedance.common.push.utility.i;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.android.pushmanager.PushCommonConstants;
import com.ss.android.pushmanager.setting.PushCommonSetting;
import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;
import com.umeng.message.entity.UMessage;
import java.util.Random;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b implements c.a {
    private static volatile b a;
    private Context b;
    private boolean d;
    private int e;
    private int f;
    private a g;
    private a h;
    private volatile boolean i = false;
    private boolean j = false;
    private Runnable k = new Runnable() { // from class: com.ss.android.newmedia.redbadge.b.2
        @Override // java.lang.Runnable
        public void run() {
            if (b.this.b == null) {
                return;
            }
            if (com.bytedance.common.push.utility.a.b()) {
                com.bytedance.common.push.utility.a.b("RedBadgeControlClient", "mRunnable AppAlive = " + b.this.j);
            }
            if (b.this.j) {
                b.this.j = false;
                b.this.c.sendEmptyMessage(1);
            }
        }
    };
    private com.bytedance.common.push.utility.a.c c = new com.bytedance.common.push.utility.a.c(com.ss.android.message.d.a().b(), this);
    private ContentObserver l = new ContentObserver(this.c) { // from class: com.ss.android.newmedia.redbadge.b.3
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            if (com.bytedance.common.push.utility.a.b()) {
                com.bytedance.common.push.utility.a.b("RedBadgeControlClient", "KEY_IS_DESKTOP_RED_BADGE_SHOW");
            }
            b.this.c();
        }
    };
    private ContentObserver m = new ContentObserver(this.c) { // from class: com.ss.android.newmedia.redbadge.b.4
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            if (com.bytedance.common.push.utility.a.b()) {
                com.bytedance.common.push.utility.a.b("RedBadgeControlClient", "KEY_DESKTOP_RED_BADGE_ARGS");
            }
            b.this.c();
        }
    };

    public static b a(Context context) {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b(context);
                }
            }
        }
        return a;
    }

    private b(Context context) {
        this.b = context.getApplicationContext();
        e();
        b(context);
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.d = com.ss.android.newmedia.redbadge.b.a.a(this.b).a();
    }

    private boolean d() {
        return this.d;
    }

    private void e() {
        boolean z;
        try {
            this.e = com.ss.android.newmedia.redbadge.b.a.a(this.b).g();
            this.f = com.ss.android.newmedia.redbadge.b.a.a(this.b).h();
            String strE = com.ss.android.newmedia.redbadge.b.a.a(this.b).e();
            if (!i.a(strE)) {
                this.g = a.a(strE);
            }
            String strF = com.ss.android.newmedia.redbadge.b.a.a(this.b).f();
            if (!i.a(strF)) {
                this.h = a.a(strF);
            }
            if (this.g != null) {
                if (DateUtils.isToday(this.g.a)) {
                    z = false;
                } else {
                    this.e = 0;
                    z = true;
                }
                if (!DateUtils.isToday(this.g.c)) {
                    this.f = 0;
                    z = true;
                }
                if (z) {
                    f();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void f() {
        try {
            com.ss.android.newmedia.redbadge.b.a.a(this.b).b(this.e);
            com.ss.android.newmedia.redbadge.b.a.a(this.b).c(this.f);
            com.ss.android.newmedia.redbadge.b.a.a(this.b).b(this.g == null ? "" : this.g.a().toString());
            com.ss.android.newmedia.redbadge.b.a.a(this.b).c(this.h == null ? "" : this.h.a().toString());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(int i) {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (this.g == null) {
                this.g = new a();
            }
            if (this.h == null) {
                this.h = new a();
            }
            if (!DateUtils.isToday(this.g.a)) {
                this.e = 0;
            }
            if (!DateUtils.isToday(this.g.c)) {
                this.f = 0;
            }
            switch (i) {
                case 0:
                    this.h.a = this.g.a;
                    this.h.b = this.g.b;
                    this.g.a = jCurrentTimeMillis;
                    this.g.b = jCurrentTimeMillis + 900000;
                    this.e++;
                    break;
                case 1:
                    this.g.b = jCurrentTimeMillis;
                    break;
                case 2:
                    this.h.c = this.g.c;
                    this.g.c = jCurrentTimeMillis;
                    this.f++;
                    break;
            }
            f();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void c(String str) {
        try {
            com.ss.android.newmedia.redbadge.b.a.a(this.b).d(str);
            com.bytedance.common.push.utility.b.b.a(new Runnable() { // from class: com.ss.android.newmedia.redbadge.b.1
                @Override // java.lang.Runnable
                public void run() {
                    com.ss.android.newmedia.redbadge.b.a.a(b.this.b).e(com.ss.android.pushmanager.a.a.b().d());
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private long d(String str) {
        if (str == null) {
            return 0L;
        }
        try {
            return new JSONObject(str).optLong(PushCommonConstants.KEY_RULE_ID);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public void a(String str) {
        int iNextInt;
        if (str == null || this.b == null || !d()) {
            return;
        }
        try {
            if (com.bytedance.common.push.utility.a.b()) {
                com.bytedance.common.push.utility.a.b("RedBadgeControlClient", "handleMessage = " + str);
            }
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt(PointCategory.SHOW) <= 0) {
                return;
            }
            String strOptString = jSONObject.optString("content_type");
            String strOptString2 = jSONObject.optString(com.umeng.commonsdk.framework.c.a);
            long jD = d(str);
            if (!i.a(strOptString) && !i.a(strOptString2) && !PushCommonSetting.getInstance().isAppForeground()) {
                if ("desktop_red_badge".equals(strOptString)) {
                    try {
                        iNextInt = Integer.parseInt(strOptString2);
                    } catch (Throwable unused) {
                        iNextInt = new Random().nextInt(5) + 1;
                    }
                    if (iNextInt > 0) {
                        RedBadgerManager.inst().applyCount(this.b, iNextInt);
                        this.i = true;
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put(com.umeng.commonsdk.framework.c.a, strOptString2);
                        a("desktop_red_badge", iNextInt, jSONObject2);
                        Bundle bundle = new Bundle();
                        bundle.putInt("badge_number", iNextInt);
                        bundle.putInt("red_badge_is_open", d() ? 1 : 0);
                        bundle.putString("show_type", PointCategory.REQUEST);
                        bundle.putLong(PushCommonConstants.KEY_RULE_ID, jD);
                        a("red_badge_show", bundle);
                    } else {
                        RedBadgerManager.inst().removeCount(this.b);
                        this.i = false;
                    }
                    a(2);
                    return;
                }
                if (UMessage.DISPLAY_TYPE_NOTIFICATION.equals(strOptString)) {
                    Intent intent = new Intent(com.ss.android.pushmanager.a.a.b().c());
                    intent.putExtra(com.ss.android.pushmanager.a.a.b().b(), strOptString2);
                    intent.setPackage(this.b.getPackageName());
                    this.b.startService(intent);
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put(com.umeng.commonsdk.framework.c.a, strOptString2);
                    a(UMessage.DISPLAY_TYPE_NOTIFICATION, 0L, jSONObject3);
                }
            }
        } catch (Throwable unused2) {
        }
    }

    public void a() {
        if (this.b == null) {
            return;
        }
        if (!this.j) {
            this.j = true;
            this.c.sendEmptyMessage(0);
        } else if (this.i) {
            this.i = false;
            this.c.sendEmptyMessage(3);
        }
        if (com.bytedance.common.push.utility.a.b()) {
            com.bytedance.common.push.utility.a.b("RedBadgeControlClient", "onResume mAppAlive = " + this.j);
        }
        this.c.removeCallbacks(this.k);
    }

    public void b() {
        if (this.b == null) {
            return;
        }
        if (this.j) {
            this.c.postDelayed(this.k, com.umeng.commonsdk.proguard.b.d);
            this.c.sendEmptyMessage(2);
        }
        if (com.bytedance.common.push.utility.a.b()) {
            com.bytedance.common.push.utility.a.b("RedBadgeControlClient", "onPause AppAlive = " + this.j);
        }
    }

    public void b(String str) {
        c(str);
    }

    public void handleMsg(Message message) {
        if (message == null) {
        }
        try {
            switch (message.what) {
                case 0:
                    a(0);
                    RedBadgerManager.inst().removeCount(this.b);
                    this.i = false;
                    if (com.bytedance.common.push.utility.a.b()) {
                        com.bytedance.common.push.utility.a.b("RedBadgeControlClient", "isAllowRedBadgeShow = " + d());
                    }
                    if (d()) {
                        Intent intent = new Intent(this.b, (Class<?>) RedBadgePushProcessService.class);
                        intent.putExtra("app_entrance", true);
                        this.b.startService(intent);
                        break;
                    }
                    break;
                case 1:
                    a(1);
                    if (com.bytedance.common.push.utility.a.b()) {
                        com.bytedance.common.push.utility.a.b("RedBadgeControlClient", "isAllowRedBadgeShow = " + d());
                    }
                    if (d()) {
                        Intent intent2 = new Intent(this.b, (Class<?>) RedBadgePushProcessService.class);
                        intent2.putExtra("app_exit", true);
                        this.b.startService(intent2);
                        break;
                    }
                    break;
                case 2:
                    a(1);
                    break;
                case 3:
                    RedBadgerManager.inst().removeCount(this.b);
                    this.i = false;
                    if (com.bytedance.common.push.utility.a.b()) {
                        com.bytedance.common.push.utility.a.b("RedBadgeControlClient", "强制消除红点");
                        break;
                    }
                    break;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static class a {
        long a;
        long b;
        long c;

        private a() {
        }

        JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("launch", this.a);
                jSONObject.put("leave", this.b);
                jSONObject.put("badge", this.c);
            } catch (Throwable unused) {
            }
            return jSONObject;
        }

        static a a(String str) {
            a aVar = new a();
            try {
                JSONObject jSONObject = new JSONObject(str);
                aVar.a = jSONObject.optLong("launch", 0L);
                aVar.b = jSONObject.optLong("leave", 0L);
                aVar.c = jSONObject.optLong("badge", 0L);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return aVar;
        }
    }

    void a(String str, long j, JSONObject jSONObject) {
        com.ss.android.pushmanager.a.a.b().a(this.b, "event_v1", "red_badge", str, j, 0L, jSONObject);
    }

    void a(String str, Bundle bundle) {
        com.ss.android.pushmanager.a.a.b().a(str, bundle);
    }

    private void b(Context context) {
        if (context == null) {
            return;
        }
        try {
            context.getContentResolver().registerContentObserver(PushMultiProcessSharedProvider.getContentUri(context, "is_desktop_red_badge_show", PushMultiProcessSharedProvider.BOOLEAN_TYPE), true, this.l);
            context.getContentResolver().registerContentObserver(PushMultiProcessSharedProvider.getContentUri(context, "desktop_red_badge_args", PushMultiProcessSharedProvider.STRING_TYPE), true, this.m);
        } catch (Throwable unused) {
        }
    }
}
