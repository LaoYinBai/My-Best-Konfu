package com.bytedance.push;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.bytedance.applog.AppLog;
import com.bytedance.push.c.m;
import com.bytedance.push.d;
import com.ss.android.newmedia.redbadge.RedBadgerManager;
import com.ss.android.pushmanager.client.PushSettingManager;
import com.ss.android.pushmanager.setting.PushSetting;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class c implements IBDPushService, com.bytedance.push.c.l {
    private static com.bytedance.push.c.h f = new g();

    /* renamed from: c, reason: collision with root package name */
    private OnPushClickListener f4761c;
    private OnPushArriveListener d;

    /* renamed from: a, reason: collision with root package name */
    private h f4759a = new h();

    /* renamed from: b, reason: collision with root package name */
    private f f4760b = new f();
    private volatile AtomicBoolean e = new AtomicBoolean(false);
    private AppLogObserver g = new AppLogObserver() { // from class: com.bytedance.push.c.1
        @Override // com.bytedance.push.AppLogObserver, com.bytedance.applog.IDataObserver
        public void onIdLoaded(String str, String str2, String str3) {
            c.this.handleAppLogUpdate(com.ss.android.message.a.a(), AppLog.getDid(), AppLog.getIid(), AppLog.getClientUdid(), AppLog.getOpenUdid(), AppLog.getUserUniqueID());
        }

        @Override // com.bytedance.push.AppLogObserver
        public void onConfigChanged(JSONObject jSONObject) {
            c.this.handleAppLogUpdate(com.ss.android.message.a.a(), AppLog.getDid(), AppLog.getIid(), AppLog.getClientUdid(), AppLog.getOpenUdid(), AppLog.getUserUniqueID());
        }

        @Override // com.bytedance.push.AppLogObserver, com.bytedance.applog.IDataObserver
        public void onRemoteConfigGet(boolean z, JSONObject jSONObject) {
            c.this.handleAppLogUpdate(com.ss.android.message.a.a(), AppLog.getDid(), AppLog.getIid(), AppLog.getClientUdid(), AppLog.getOpenUdid(), AppLog.getUserUniqueID());
        }
    };

    c() {
    }

    static {
        com.bytedance.common.push.utility.f.a(new com.bytedance.push.g.b());
    }

    @Override // com.bytedance.push.IBDPushService
    public synchronized void handleAppLogUpdate(Context context, String str, String str2, String str3, String str4, String str5) {
        if (com.ss.android.message.a.b.c(context)) {
            com.bytedance.common.push.utility.a.b("BDPush", "handleAppLogUpdate: deviceId = " + str + ", installId = " + str2 + ", clientUdid = " + str3 + ", openUdid = " + str4 + ", userId = " + str5);
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                HashMap map = new HashMap();
                map.put("clientudid", str3);
                map.put(com.bytedance.b.a.c.h.x, str);
                map.put("install_id", str2);
                map.put("openudid", str4);
                map.put("user_id", str5);
                handleAppLogUpdate(context, map);
            }
        }
    }

    @Override // com.bytedance.push.IBDPushService
    public synchronized void handleAppLogUpdate(Context context, Map<String, String> map) {
        if (com.ss.android.message.a.b.c(context) && map != null) {
            String str = map.get(com.bytedance.b.a.c.h.x);
            com.bytedance.common.push.utility.a.b("BDPush", "handleAppLogUpdate: deviceId = " + str + ", installId = " + map.get("install_id") + ", clientUdid = " + map.get("clientudid") + ", openUdid = " + map.get("openudid") + ", userId = " + map.get("user_id"));
            PushSettingManager.getInstance().notifyAllowOffAlive(context, false);
            f.a(map, false);
            if (this.e.compareAndSet(false, true)) {
                com.ss.android.newmedia.redbadge.b.a(context).b("");
            }
            this.f4760b.a(str);
        }
    }

    @Override // com.bytedance.push.IBDPushService
    public void setRedBadgeNumber(Context context, int i) {
        RedBadgerManager.inst().applyCount(context, i);
    }

    @Override // com.bytedance.push.IBDPushService
    public void enableRedBadgeWithDefaultStrategy(Context context, boolean z) throws JSONException {
        enableRedBadgeWithStrategy(context, z, 3, "strategy_default");
    }

    @Override // com.bytedance.push.IBDPushService
    public void enableRedBadgeWithStrategy(Context context, boolean z, int i, String str) throws JSONException {
        com.ss.android.message.a.a((Application) context.getApplicationContext());
        com.ss.android.newmedia.redbadge.b.a.a(context).a(z);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("strategy", str);
            jSONObject.put("max_show_times", i);
            jSONObject.put("query_waiting_duration", 30);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        com.ss.android.newmedia.redbadge.b.a.a(context).a(jSONObject.toString());
    }

    @Override // com.bytedance.push.IBDPushService
    public void setOnTrackCallback(ITracker iTracker) {
        this.f4759a.a(iTracker);
    }

    @Override // com.bytedance.push.IBDPushService
    public void setPushEnable(Context context, boolean z) {
        com.ss.android.message.a.a((Application) context.getApplicationContext());
        PushSettingManager.getInstance().notifyPushEnableChange(context.getApplicationContext(), z);
    }

    @Override // com.bytedance.push.IBDPushService
    public void setOnPushClickListener(OnPushClickListener onPushClickListener) {
        this.f4761c = onPushClickListener;
    }

    @Override // com.bytedance.push.IBDPushService
    public void setOnPushArriveListener(OnPushArriveListener onPushArriveListener) {
        this.d = onPushArriveListener;
    }

    @Override // com.bytedance.push.IBDPushService
    public void init(final Application application, PushContext pushContext, boolean z, String str) {
        if (z) {
            com.bytedance.common.push.utility.a.a(2);
        } else {
            com.bytedance.common.push.utility.a.a(4);
        }
        b bVar = new b();
        bVar.a(pushContext.getVersion());
        bVar.b(pushContext.getVersionCode());
        bVar.c(pushContext.getUpdateVersionCode());
        bVar.b(pushContext.getTweakedChannel());
        bVar.a(pushContext.getAid());
        bVar.c(pushContext.getAppName());
        f.a(new d.a(application, bVar).b(str).a(com.ss.android.message.a.b.a(application)).a(this).a((com.ss.android.message.b) new com.ss.android.newmedia.redbadge.f()).a(z).a(new m() { // from class: com.bytedance.push.c.4
            @Override // com.bytedance.push.c.m
            public Notification a(Context context, int i, PushBody pushBody, Bitmap bitmap, Notification notification) {
                return notification;
            }

            @Override // com.bytedance.push.c.m
            public boolean a(Context context, int i, PushBody pushBody) {
                if (c.this.d == null) {
                    return false;
                }
                c.this.d.onPushArrive(context, i, pushBody);
                return false;
            }
        }).a(new com.bytedance.push.c.c() { // from class: com.bytedance.push.c.3
            @Override // com.bytedance.push.c.c
            public Map<String, String> a() {
                HashMap map = new HashMap();
                map.put("tob_version", "3.2.0-honor");
                return map;
            }
        }).a(new com.bytedance.push.c.d() { // from class: com.bytedance.push.c.2
            @Override // com.bytedance.push.c.d
            public void a(Context context, String str2, String str3, String str4, long j, long j2, JSONObject jSONObject) {
            }

            @Override // com.bytedance.push.c.d
            public void a(String str2, JSONObject jSONObject) {
                c.this.f4759a.a(str2, jSONObject);
            }
        }).b(false).a(new l(application, pushContext)).a());
        if (com.ss.android.message.a.b.c(application)) {
            this.f4760b.a(new com.bytedance.push.i.b(f));
            try {
                AppLog.addDataObserver(this.g);
                com.ss.android.message.d.a().a(new Runnable() { // from class: com.bytedance.push.c.5
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            c.this.handleAppLogUpdate(application, AppLog.getDid(), AppLog.getIid(), AppLog.getClientUdid(), AppLog.getOpenUdid(), AppLog.getUserUniqueID());
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                }, TimeUnit.SECONDS.toMillis(20L));
                handleAppLogUpdate(application, AppLog.getDid(), AppLog.getIid(), AppLog.getClientUdid(), AppLog.getOpenUdid(), AppLog.getUserUniqueID());
            } catch (Throwable th) {
                th.printStackTrace();
            }
            application.registerActivityLifecycleCallbacks(new a() { // from class: com.bytedance.push.c.6
                @Override // com.bytedance.push.a, android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(Activity activity) {
                    com.ss.android.newmedia.redbadge.b.a(activity.getApplicationContext()).a();
                }

                @Override // com.bytedance.push.a, android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(Activity activity) {
                    com.ss.android.newmedia.redbadge.b.a(activity.getApplicationContext()).b();
                }
            });
        }
    }

    @Override // com.bytedance.push.IBDPushService
    public void setAlias(Context context, String str, ICallback iCallback) {
        com.ss.android.message.a.a((Application) context.getApplicationContext());
        if (com.ss.android.message.a.b.c(context)) {
            if (TextUtils.equals(str, PushSetting.getInstance().getAlias())) {
                iCallback.onMessage(0, "");
            } else {
                this.f4760b.a(new com.bytedance.push.i.a(str, iCallback));
            }
        }
    }

    @Override // com.bytedance.push.c.l
    public JSONObject a(Context context, int i, PushBody pushBody) {
        this.f4761c.onPushClick(context, i, pushBody);
        return null;
    }
}
