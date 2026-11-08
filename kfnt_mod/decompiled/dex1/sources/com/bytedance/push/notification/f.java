package com.bytedance.push.notification;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import anet.channel.entity.ConnType;
import com.bytedance.push.c.j;
import com.bytedance.push.d;
import com.bytedance.push.settings.LocalFrequencySettings;
import com.bytedance.push.settings.PushOnlineSettings;
import com.bytedance.push.settings.SettingsManager;
import com.ss.android.pushmanager.setting.PushSetting;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class f implements com.bytedance.push.c.f {

    /* renamed from: a, reason: collision with root package name */
    private final j f4892a;

    public f(j jVar) {
        this.f4892a = jVar;
    }

    public void b(final Context context) {
        com.bytedance.common.push.utility.b.b.a(new Runnable() { // from class: com.bytedance.push.notification.f.1
            @Override // java.lang.Runnable
            public void run() {
                boolean zIsPushNotifyEnable = PushSetting.getInstance().isPushNotifyEnable();
                LocalFrequencySettings localFrequencySettings = (LocalFrequencySettings) SettingsManager.obtain(context, LocalFrequencySettings.class);
                if ((Math.abs(System.currentTimeMillis() - localFrequencySettings.k()) > ((PushOnlineSettings) SettingsManager.obtain(context, PushOnlineSettings.class)).h()) || !localFrequencySettings.g() || f.this.c(context)) {
                    f.this.a(context, zIsPushNotifyEnable);
                }
                f.this.b(context, zIsPushNotifyEnable);
            }
        });
    }

    @Override // com.bytedance.push.c.f
    public void a(Context context, List<com.bytedance.push.e.a> list) {
        if (com.bytedance.common.push.utility.a.b.a(list)) {
            return;
        }
        for (com.bytedance.push.e.a aVar : list) {
            if (aVar != null) {
                try {
                    if (aVar.j()) {
                        e.a().a(context, aVar);
                    } else if (!TextUtils.equals(aVar.b(), "push")) {
                        e.a().b(context, aVar);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override // com.bytedance.push.c.f
    public void a(Context context, boolean z) {
        LocalFrequencySettings localFrequencySettings = (LocalFrequencySettings) SettingsManager.obtain(context, LocalFrequencySettings.class);
        if (!com.bytedance.common.push.utility.g.a(context)) {
            localFrequencySettings.a(false);
            return;
        }
        i iVar = new i(context, this.f4892a, z, null, null);
        if (Looper.getMainLooper() == Looper.myLooper()) {
            com.bytedance.common.push.utility.b.b.a(iVar);
        } else {
            iVar.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(Context context) {
        return e.a().a(context, ((LocalFrequencySettings) SettingsManager.obtain(context, LocalFrequencySettings.class)).h());
    }

    static boolean a(Context context, String str) {
        try {
            if (Build.VERSION.SDK_INT < 26) {
                return true;
            }
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null && !TextUtils.isEmpty(str)) {
                return notificationManager.getNotificationChannel(str) != null;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.bytedance.push.c.f
    public void a(Context context) {
        if (PushSetting.getInstance().isAllowNetwork()) {
            b(context);
        }
    }

    @Override // com.bytedance.push.c.f
    public void a(final Context context, final d.b bVar) {
        if (context != null && Build.VERSION.SDK_INT >= 26) {
            com.bytedance.common.push.utility.b.b.a(new Runnable() { // from class: com.bytedance.push.notification.f.2
                @Override // java.lang.Runnable
                @TargetApi(26)
                public void run() {
                    d.b bVar2 = bVar;
                    String strA = com.ss.android.message.a.a.a(context, "push_notification_channel_name");
                    if (bVar2 == null) {
                        bVar2 = new d.b("push", strA);
                    } else if (!bVar2.a()) {
                        if (TextUtils.isEmpty(bVar2.f4777b)) {
                            bVar2.f4777b = "push";
                        }
                        if (TextUtils.isEmpty(bVar2.f4776a)) {
                            bVar2.f4776a = strA;
                        }
                    }
                    String str = bVar2.f4777b;
                    String str2 = bVar2.f4776a;
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                    if (notificationManager != null && notificationManager.getNotificationChannel(str) == null) {
                        NotificationChannel notificationChannel = new NotificationChannel(str, str2, 4);
                        notificationChannel.setShowBadge(true);
                        notificationChannel.enableVibration(true);
                        notificationChannel.enableLights(true);
                        notificationManager.createNotificationChannel(notificationChannel);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("in_status", z ? ConnType.PK_OPEN : "close");
            jSONObject.put("out_status", 1 == com.ss.android.message.a.b.d(context) ? ConnType.PK_OPEN : "close");
        } catch (Throwable unused) {
        }
        this.f4892a.h().a("ttpush_push_notification_status", jSONObject);
    }
}
