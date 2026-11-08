package com.ss.union.game.sdk.push;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import androidx.core.app.NotificationManagerCompat;
import com.bytedance.push.BDPush;
import com.bytedance.push.OnPushArriveListener;
import com.bytedance.push.OnPushClickListener;
import com.bytedance.push.PushBody;
import com.ss.union.game.sdk.common.util.ProcessUtils;
import com.ss.union.game.sdk.core.applog.AppLogIdUpdateListener;
import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.applog.AppLogOaidListener;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.constant.LGUris;
import com.ss.union.game.sdk.core.base.event.EventJSONHeaders;
import com.ss.union.game.sdk.push.callback.IPushService;
import com.ss.union.game.sdk.push.callback.OnNotificationClickListener;
import com.ss.union.game.sdk.push.callback.OnPushMessageArriveListener;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c implements IPushService {
    private static AtomicBoolean a = new AtomicBoolean(false);
    private com.ss.union.game.sdk.push.a.a b;

    private static class a {
        static c a = new c();

        private a() {
        }
    }

    public static c a() {
        return a.a;
    }

    private c() {
        this.b = new com.ss.union.game.sdk.push.a.a();
    }

    public void a(Application application) {
        if (a.compareAndSet(false, true)) {
            b(application);
        }
    }

    private void b(Application application) {
        a(application, AppIdManager.apAppID(), ConfigManager.AppConfig.appChannel(), ConfigManager.PushConfig.isDebug());
        if (!ProcessUtils.isMainProcess(application)) {
            AppLogManager.getInstance().init(application, AppIdManager.apAppID(), ConfigManager.AppConfig.appChannel(), true);
            AppLogManager.getInstance().registerIdUpdateListener(new AppLogIdUpdateListener() { // from class: com.ss.union.game.sdk.push.c.1
                @Override // com.ss.union.game.sdk.core.applog.AppLogIdUpdateListener
                public void onIdChanged(String str, String str2) throws JSONException {
                    EventJSONHeaders.getInstance().updateDid(str);
                    EventJSONHeaders.getInstance().updateDid(str2);
                }
            });
            AppLogManager.getInstance().registerOaidListener(new AppLogOaidListener() { // from class: com.ss.union.game.sdk.push.c.2
                @Override // com.ss.union.game.sdk.core.applog.AppLogOaidListener
                public void onOaidLoaded(String str) throws JSONException {
                    EventJSONHeaders.getInstance().updateOaid(str);
                }
            });
            return;
        }
        com.ss.union.game.sdk.push.c.a.a();
    }

    @Override // com.ss.union.game.sdk.push.callback.IPushService
    public void setOnPushClickListener(OnNotificationClickListener onNotificationClickListener) {
        this.b.a(onNotificationClickListener);
    }

    @Override // com.ss.union.game.sdk.push.callback.IPushService
    public void setOnPushArriveListener(OnPushMessageArriveListener onPushMessageArriveListener) {
        this.b.a(onPushMessageArriveListener);
    }

    @Override // com.ss.union.game.sdk.push.callback.IPushService
    public void enableRedBadgeWithDefaultStrategy(Context context, boolean z) {
        BDPush.getService().enableRedBadgeWithDefaultStrategy(context, z);
    }

    @Override // com.ss.union.game.sdk.push.callback.IPushService
    public void setRedBadgeNumber(Context context, int i) {
        BDPush.getService().setRedBadgeNumber(context, i);
    }

    @Override // com.ss.union.game.sdk.push.callback.IPushService
    public boolean notificationPermissionEnable(Context context) {
        return NotificationManagerCompat.from(context).areNotificationsEnabled();
    }

    @Override // com.ss.union.game.sdk.push.callback.IPushService
    public void startLaunchActivity(Context context) {
        this.b.a(context);
    }

    private void a(Application application, String str, String str2, boolean z) {
        try {
            BDPush.initOnApplication(application, Integer.parseInt(str), str2, z, LGUris.onlineHost());
            b();
            com.ss.union.game.sdk.push.d.a.a("init Push Sdk finish...");
        } catch (Exception e) {
            com.ss.union.game.sdk.push.d.a.a("Init Push Sdk Error..." + e.getMessage());
        }
    }

    private void b() {
        BDPush.getService().setOnPushClickListener(new OnPushClickListener() { // from class: com.ss.union.game.sdk.push.c.3
            public void onPushClick(Context context, int i, PushBody pushBody) {
                com.ss.union.game.sdk.push.d.a.a("current process : " + ProcessUtils.getCurrentProcessName());
                if (pushBody != null) {
                    c.this.b.a(context, i, pushBody.title, pushBody.text, pushBody.imageUrl, Uri.parse(pushBody.open_url == null ? "" : pushBody.open_url), pushBody.id);
                }
            }
        });
        BDPush.getService().setOnPushArriveListener(new OnPushArriveListener() { // from class: com.ss.union.game.sdk.push.c.4
            public void onPushArrive(Context context, int i, PushBody pushBody) {
                com.ss.union.game.sdk.push.d.a.a("current process : " + ProcessUtils.getCurrentProcessName());
                if (pushBody != null) {
                    c.this.b.a(context, i, pushBody.title, pushBody.text, pushBody.imageUrl, pushBody.open_url, pushBody.id);
                }
            }
        });
    }
}
