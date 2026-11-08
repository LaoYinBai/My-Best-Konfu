package com.ss.android.newmedia.redbadge;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Pair;
import com.bytedance.common.push.utility.a.c;
import com.bytedance.common.push.utility.i;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.ss.android.pushmanager.IMessageContext;
import com.ss.android.pushmanager.MessageConstants;
import com.ss.android.pushmanager.PushCommonConstants;
import com.ss.android.pushmanager.setting.PushCommonSetting;
import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;
import com.taobao.accs.common.Constants;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c implements c.a {
    private static volatile c b;
    private boolean c;
    private IMessageContext d;
    private Context e;
    private boolean g;
    private int h;
    private int i;
    private String j;
    private String k;
    private String l;
    private long m;
    private long n;
    private long o;
    private long p;
    private int q;
    private final com.bytedance.common.push.utility.a.c f = new com.bytedance.common.push.utility.a.c(com.ss.android.message.d.a().b(), this);
    private boolean r = false;
    final BroadcastReceiver a = new BroadcastReceiver() { // from class: com.ss.android.newmedia.redbadge.c.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context == null || intent == null) {
                return;
            }
            try {
                if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()) && com.bytedance.common.push.utility.g.a(context) && c.this.c) {
                    c.this.f.sendEmptyMessage(0);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    };
    private AtomicBoolean s = new AtomicBoolean(false);
    private ContentObserver t = new ContentObserver(this.f) { // from class: com.ss.android.newmedia.redbadge.c.3
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            if (com.bytedance.common.push.utility.a.b()) {
                com.bytedance.common.push.utility.a.b("RedBadgeController", "KEY_IS_DESKTOP_RED_BADGE_SHOW");
            }
            c.this.a(c.this.e);
        }
    };
    private ContentObserver u = new ContentObserver(this.f) { // from class: com.ss.android.newmedia.redbadge.c.4
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            if (com.bytedance.common.push.utility.a.b()) {
                com.bytedance.common.push.utility.a.b("RedBadgeController", "KEY_DESKTOP_RED_BADGE_ARGS");
            }
            c.this.a(c.this.e);
        }
    };
    private ContentObserver v = new ContentObserver(this.f) { // from class: com.ss.android.newmedia.redbadge.c.5
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            if (com.bytedance.common.push.utility.a.b()) {
                com.bytedance.common.push.utility.a.b("RedBadgeController", "KEY_LAST_TIME_PARAS");
            }
            c.this.b(c.this.e);
        }
    };

    public static c a(IMessageContext iMessageContext) {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c(iMessageContext);
                }
            }
        }
        return b;
    }

    private c(IMessageContext iMessageContext) {
        this.c = false;
        this.d = iMessageContext;
        this.e = iMessageContext.getContext().getApplicationContext();
        a(this.e);
        c(this.e);
        if (this.g) {
            this.e.registerReceiver(this.a, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            b(this.e);
            this.f.sendEmptyMessageDelayed(0, DownloadConstants.DEFAULT_NEXT_INSTALL_MIN_INTERVAL);
        }
        this.c = true;
    }

    public void a(Context context) {
        if (context == null) {
            return;
        }
        try {
            this.g = com.ss.android.newmedia.redbadge.b.a.a(this.e).a();
            String strB = com.ss.android.newmedia.redbadge.b.a.a(this.e).b();
            if (i.a(strB)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(strB);
            this.h = jSONObject.optInt("max_show_times", 5);
            this.i = jSONObject.optInt("query_waiting_duration", 30);
            this.j = jSONObject.optString("strategy");
            if (this.g) {
                return;
            }
            this.f.removeMessages(0);
            this.f.removeMessages(1);
            this.f.removeMessages(2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void b(Context context) {
        if (context == null) {
            return;
        }
        try {
            this.p = com.ss.android.newmedia.redbadge.b.a.a(this.e).c();
            this.q = com.ss.android.newmedia.redbadge.b.a.a(this.e).d();
            this.k = com.ss.android.newmedia.redbadge.b.a.a(this.e).e();
            this.l = com.ss.android.newmedia.redbadge.b.a.a(this.e).f();
            if (i.a(this.k)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(this.k);
            this.m = jSONObject.optLong("launch");
            this.n = jSONObject.optLong("leave");
            this.o = jSONObject.optLong("badge");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a() {
        if (this.g) {
            this.f.sendEmptyMessage(1);
        }
    }

    public void b() {
        if (this.g) {
            this.f.sendEmptyMessage(2);
        }
    }

    public void handleMsg(Message message) {
        if (message == null) {
        }
        try {
            switch (message.what) {
                case 0:
                    d();
                    break;
                case 1:
                    e();
                    break;
                case 2:
                    f();
                    break;
                case 3:
                    long jLongValue = ((Long) message.obj).longValue();
                    long j = this.q * 1000;
                    if (com.bytedance.common.push.utility.a.b()) {
                        com.bytedance.common.push.utility.a.b("RedBadgeController", "next schedule time = " + DateFormat.getDateTimeInstance().format(new Date(jLongValue + j)));
                    }
                    this.f.sendEmptyMessageDelayed(0, j);
                    break;
                case 4:
                    long jLongValue2 = ((Long) message.obj).longValue();
                    long j2 = this.q * 1000;
                    long j3 = this.p + (this.q * 1000);
                    if (jLongValue2 <= j3) {
                        j2 = j3 - jLongValue2;
                    }
                    if (com.bytedance.common.push.utility.a.b()) {
                        com.bytedance.common.push.utility.a.b("RedBadgeController", "next schedule time = " + DateFormat.getDateTimeInstance().format(new Date(jLongValue2 + j2)));
                    }
                    this.f.sendEmptyMessageDelayed(0, j2);
                    break;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void d() {
        long j;
        if (com.bytedance.common.push.utility.a.b()) {
            com.bytedance.common.push.utility.a.b("RedBadgeController", "handleOnSchedule");
        }
        if (this.g) {
            try {
                this.f.removeMessages(0);
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis < this.p) {
                    this.p = jCurrentTimeMillis - (this.q * 1000);
                    com.ss.android.newmedia.redbadge.b.a.a(this.e).a(this.p);
                }
                if (PushCommonSetting.getInstance().isAppForeground() && com.ss.android.pushmanager.a.a.b().e()) {
                    if (com.bytedance.common.push.utility.a.b()) {
                        com.bytedance.common.push.utility.a.b("RedBadgeController", "isApplicationForeground = true now = " + jCurrentTimeMillis + " mIsForeground = " + this.r);
                    }
                    this.f.sendMessage(this.f.obtainMessage(4, Long.valueOf(jCurrentTimeMillis)));
                    return;
                }
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("RedBadgeController", "isApplicationForeground = false now = " + jCurrentTimeMillis + " mIsForeground = " + this.r);
                }
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("RedBadgeController", "mLastLeaveTime = " + this.n + " mLastLaunchTime = " + this.m);
                }
                if (this.n < this.m) {
                    j = (jCurrentTimeMillis - this.m) - 900000;
                } else {
                    j = jCurrentTimeMillis - this.n;
                }
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("RedBadgeController", "duration = " + (j / 1000) + " mQueryWaitingDuration = " + this.i + " mNextQueryInterval = " + this.q + " mLastRequestTime = " + this.p);
                }
                if (j >= this.i * 1000 && jCurrentTimeMillis - this.p >= this.q * 1000) {
                    a(jCurrentTimeMillis);
                } else {
                    this.f.sendMessage(this.f.obtainMessage(4, Long.valueOf(jCurrentTimeMillis)));
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private void e() {
        if (com.bytedance.common.push.utility.a.b()) {
            com.bytedance.common.push.utility.a.b("RedBadgeController", "handleOnAppEntrance");
        }
        if (this.g) {
            this.r = true;
            if (this.f.hasMessages(0)) {
                return;
            }
            this.f.sendEmptyMessageDelayed(0, this.q * 1000);
        }
    }

    private void f() {
        if (com.bytedance.common.push.utility.a.b()) {
            com.bytedance.common.push.utility.a.b("RedBadgeController", "handleOnAppExit");
        }
        if (this.g) {
            this.r = false;
            this.f.removeMessages(0);
            this.f.sendEmptyMessageDelayed(0, this.i * 1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject a(String str) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        if (i.a(str)) {
            return jSONObject2;
        }
        try {
            jSONObject = new JSONObject(str);
            try {
                a(jSONObject, "launch");
                a(jSONObject, "leave");
                a(jSONObject, "badge");
            } catch (Throwable th) {
                th = th;
                th.printStackTrace();
                return jSONObject;
            }
        } catch (Throwable th2) {
            th = th2;
            jSONObject = jSONObject2;
        }
        return jSONObject;
    }

    private void a(JSONObject jSONObject, String str) {
        if (jSONObject == null || i.a(str)) {
            return;
        }
        try {
            jSONObject.put(str, jSONObject.optLong(str) / 1000);
        } catch (Throwable unused) {
        }
    }

    private void a(final long j) {
        if (com.bytedance.common.push.utility.a.b()) {
            com.bytedance.common.push.utility.a.b("RedBadgeController", "doSendRequest");
        }
        if (this.s.get()) {
            return;
        }
        this.s.getAndSet(true);
        com.bytedance.common.push.utility.b.b.a(new Runnable() { // from class: com.ss.android.newmedia.redbadge.c.2
            @Override // java.lang.Runnable
            public void run() {
                boolean z;
                JSONObject jSONObject;
                try {
                    com.ss.android.message.log.c.a(c.this.e, "red_badge", "start_send_request", new JSONObject[0]);
                } catch (Throwable th) {
                    th.printStackTrace();
                    try {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("th", th.getMessage());
                        com.ss.android.message.log.c.a(c.this.e, "red_badge", "send_request_fail_with_exception", jSONObject2);
                        c.this.c();
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
                if (!com.bytedance.common.push.utility.g.a(c.this.e)) {
                    if (com.bytedance.common.push.utility.a.b()) {
                        com.bytedance.common.push.utility.a.b("RedBadgeController", "isNetworkAvailable = false");
                    }
                    com.ss.android.message.log.c.a(c.this.e, "red_badge", "network_not_available", new JSONObject[0]);
                    c.this.c();
                    c.this.f.sendMessage(c.this.f.obtainMessage(3, Long.valueOf(j)));
                    c.this.s.getAndSet(false);
                    return;
                }
                JSONObject jSONObject3 = new JSONObject();
                int iG = com.ss.android.newmedia.redbadge.b.a.a(c.this.e).g();
                if (!DateUtils.isToday(c.this.m) && iG > 0) {
                    iG = 0;
                }
                int iH = com.ss.android.newmedia.redbadge.b.a.a(c.this.e).h();
                if (!DateUtils.isToday(c.this.o) && iH > 0) {
                    iH = 0;
                }
                jSONObject3.put("launch_times", iG);
                jSONObject3.put("badge_show_times", iH);
                if (iH < c.this.h) {
                    jSONObject3.put("last_time_paras", c.this.a(c.this.k));
                    jSONObject3.put("last_last_time_paras", c.this.a(c.this.l));
                    jSONObject3.put("desktop_red_badge_strategy", c.this.j);
                    jSONObject3.put("client_current_time", System.currentTimeMillis() / 1000);
                    jSONObject3.put("session_key", com.ss.android.newmedia.redbadge.b.a.a(c.this.e).i());
                    jSONObject3.put("device_id", PushCommonSetting.getInstance().getDeviceId());
                    if (c.this.d != null) {
                        jSONObject3.put("app_id", c.this.d.getAid());
                    }
                    try {
                        jSONObject3.put("rom", com.ss.android.newmedia.redbadge.b.a.a(c.this.e).j());
                        jSONObject3.put("brand", Build.BRAND);
                        jSONObject3.put(Constants.KEY_MODEL, Build.MODEL);
                        jSONObject3.put("os_api", Build.VERSION.SDK_INT);
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                    if (c.this.d != null) {
                        jSONObject3.put("ver", c.this.d.getVersionCode());
                    }
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new Pair("data", jSONObject3.toString()));
                    String strA = com.ss.android.message.a.b.a(e.a, com.ss.android.pushmanager.a.a.b().f());
                    Bundle bundle = new Bundle();
                    bundle.putInt("red_badge_is_open", c.this.g() ? 1 : 0);
                    c.this.a("red_badge_request", bundle);
                    String strA2 = com.bytedance.common.push.utility.f.a().a(strA, arrayList);
                    if (i.a(strA2)) {
                        com.ss.android.message.log.c.a(c.this.e, "red_badge", "send_request_fail_no_response", new JSONObject[0]);
                        c.this.c();
                    } else {
                        try {
                            jSONObject = new JSONObject(strA2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if ("success".equals(jSONObject.getString("reason"))) {
                            com.ss.android.message.log.c.a(c.this.e, "red_badge", "send_request_success", new JSONObject[0]);
                            long jB = c.this.b(strA2);
                            Bundle bundle2 = new Bundle();
                            bundle2.putInt("red_badge_is_open", c.this.g() ? 1 : 0);
                            bundle2.putLong(PushCommonConstants.KEY_RULE_ID, jB);
                            bundle2.putString("resp", strA2);
                            c.this.a("red_badge_request_success", bundle2);
                            c.this.p = System.currentTimeMillis();
                            c.this.q = jSONObject.optInt("next_query_interval", 0) + 600;
                            if (jSONObject.optInt(com.umeng.commonsdk.framework.c.a, 0) > 0) {
                                com.ss.android.newmedia.redbadge.b.a.a(c.this.e).f(strA2);
                            }
                            try {
                                Intent intent = new Intent("com.ss.android.redbadge.message");
                                intent.putExtra(MessageConstants.MESSAGE_KEY_DATA, strA2);
                                intent.setPackage(c.this.e.getPackageName());
                                if (Build.VERSION.SDK_INT < 26 || com.ss.android.message.a.b() < 26) {
                                    c.this.e.startService(intent);
                                } else {
                                    c.this.e.bindService(intent, new g(intent, true, c.this.e), 1);
                                }
                            } catch (Throwable th4) {
                                th4.printStackTrace();
                            }
                            com.ss.android.newmedia.redbadge.b.a.a(c.this.e).a(c.this.p);
                            com.ss.android.newmedia.redbadge.b.a.a(c.this.e).a(c.this.q);
                            z = true;
                        } else {
                            z = false;
                        }
                        if (com.bytedance.common.push.utility.a.b()) {
                            com.bytedance.common.push.utility.a.b("RedBadgeController", "doSendRequest result = " + z);
                        }
                        if (!z) {
                            JSONObject jSONObject4 = new JSONObject();
                            jSONObject4.put("response", strA2);
                            com.ss.android.message.log.c.a(c.this.e, "red_badge", "send_request_fail", jSONObject4);
                            Bundle bundle3 = new Bundle();
                            bundle3.putString("response", strA2);
                            bundle3.putLong(PushCommonConstants.KEY_RULE_ID, c.this.b(strA2));
                            bundle3.putInt("red_badge_is_open", c.this.g() ? 1 : 0);
                            c.this.a("red_badge_request_fail", bundle3);
                            c.this.c();
                        }
                    }
                    c.this.f.sendMessage(c.this.f.obtainMessage(3, Long.valueOf(j)));
                    c.this.s.getAndSet(false);
                    return;
                }
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("RedBadgeController", "badge_show_times = " + iH);
                }
                com.ss.android.message.log.c.a(c.this.e, "event_v1", "red_badge", "outdo_max_show_times", iH, c.this.h);
                c.this.f.sendMessage(c.this.f.obtainMessage(3, Long.valueOf(j)));
                c.this.s.getAndSet(false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long b(String str) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        return this.g;
    }

    public void c() throws JSONException {
        String strK = com.ss.android.newmedia.redbadge.b.a.a(this.e).k();
        com.bytedance.common.push.utility.a.b("RedBadgeController", "tryUseLastValidResponse: lastRes = " + strK + "  isUseRedBadgeLastValidResponse() = " + com.ss.android.newmedia.redbadge.b.a.a(this.e).l());
        if (!com.ss.android.newmedia.redbadge.b.a.a(this.e).l() || TextUtils.isEmpty(strK)) {
            return;
        }
        try {
            int iH = com.ss.android.newmedia.redbadge.b.a.a(this.e).h();
            if (!DateUtils.isToday(this.o) && iH > 0) {
                iH = 0;
            }
            if (iH >= this.h) {
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("RedBadgeController", "badge_show_times = " + iH);
                }
                com.ss.android.message.log.c.a(this.e, "event_v1", "red_badge", "outdo_max_show_times", iH, this.h);
                return;
            }
            JSONObject jSONObject = new JSONObject(strK);
            if ("success".equals(jSONObject.getString("reason"))) {
                jSONObject.put(com.umeng.commonsdk.framework.c.a, jSONObject.optInt(com.umeng.commonsdk.framework.c.a, 0) + 1);
                com.bytedance.common.push.utility.a.b("RedBadgeController", "tryUseLastValidResponse: res = " + jSONObject);
                com.ss.android.message.log.c.a(this.e, "red_badge", "use_last_valid_response", jSONObject);
                this.p = System.currentTimeMillis();
                this.q = jSONObject.optInt("next_query_interval", 0) + 600;
                try {
                    com.ss.android.newmedia.redbadge.b.a.a(this.e).f(jSONObject.toString());
                    Intent intent = new Intent("com.ss.android.redbadge.message");
                    intent.putExtra(MessageConstants.MESSAGE_KEY_DATA, jSONObject.toString());
                    intent.setPackage(this.e.getPackageName());
                    if (Build.VERSION.SDK_INT >= 26 && com.ss.android.message.a.b() >= 26) {
                        this.e.bindService(intent, new g(intent, true, this.e), 1);
                    } else {
                        this.e.startService(intent);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                com.ss.android.newmedia.redbadge.b.a.a(this.e).a(this.p);
                com.ss.android.newmedia.redbadge.b.a.a(this.e).a(this.q);
            }
            com.ss.android.newmedia.redbadge.b.a.a(this.e).a(this.p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void a(String str, Bundle bundle) {
        if (i.a(str)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("_event_v3", 1);
            if (bundle != null) {
                for (String str2 : bundle.keySet()) {
                    jSONObject.put(str2, bundle.get(str2));
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        com.ss.android.message.log.c.a(this.e, "event_v3", str, null, 0L, 0L, jSONObject);
    }

    private void c(Context context) {
        if (context == null) {
            return;
        }
        try {
            context.getContentResolver().registerContentObserver(PushMultiProcessSharedProvider.getContentUri(context, "is_desktop_red_badge_show", PushMultiProcessSharedProvider.BOOLEAN_TYPE), true, this.t);
            context.getContentResolver().registerContentObserver(PushMultiProcessSharedProvider.getContentUri(context, "desktop_red_badge_args", PushMultiProcessSharedProvider.STRING_TYPE), true, this.u);
            context.getContentResolver().registerContentObserver(PushMultiProcessSharedProvider.getContentUri(context, "red_badge_last_time_paras", PushMultiProcessSharedProvider.STRING_TYPE), true, this.v);
        } catch (Throwable unused) {
        }
    }
}
