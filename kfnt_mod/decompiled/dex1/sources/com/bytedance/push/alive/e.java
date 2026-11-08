package com.bytedance.push.alive;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import com.bytedance.push.settings.AliveOnlineSettings;
import com.bytedance.push.settings.SettingsManager;
import com.ss.android.message.PushJobService;
import com.ss.android.pushmanager.setting.PushSetting;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e implements d {

    /* renamed from: a, reason: collision with root package name */
    private final Handler f4737a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f4738b;

    /* renamed from: c, reason: collision with root package name */
    private com.bytedance.push.settings.a f4739c;

    public String toString() {
        return "JobSchedule";
    }

    e(Handler handler) {
        this.f4737a = handler;
    }

    @Override // com.bytedance.push.alive.d
    public void a(final Context context) {
        this.f4739c = new com.bytedance.push.settings.a() { // from class: com.bytedance.push.alive.e.1
            @Override // com.bytedance.push.settings.a
            public void a() {
                e.this.f4737a.post(new Runnable() { // from class: com.bytedance.push.alive.e.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (com.bytedance.push.utils.e.a()) {
                            com.bytedance.push.utils.e.a("PushService", "BUNDLE_ALLOW_PUSH_JOB_SERVICE");
                        }
                        if (PushSetting.getInstance().isAllowNetwork()) {
                            e.this.c(context);
                        }
                    }
                });
            }
        };
        ((AliveOnlineSettings) SettingsManager.obtain(context, AliveOnlineSettings.class)).registerValChanged(context, "allow_push_job_service", "boolean", this.f4739c);
        c(context);
    }

    @Override // com.bytedance.push.alive.d
    public boolean b(Context context) {
        return Build.VERSION.SDK_INT >= 21 && com.bytedance.common.push.b.a.a.a.a.a(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context) {
        boolean zC = ((AliveOnlineSettings) SettingsManager.obtain(com.ss.android.message.a.a(), AliveOnlineSettings.class)).c();
        if (zC != this.f4738b) {
            this.f4738b = zC;
        }
        if (this.f4738b) {
            if (Build.VERSION.SDK_INT >= 21) {
                PushJobService.a(context);
                com.bytedance.push.utils.e.a("PushAlive", "register JobSchedule success");
                return;
            }
            return;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            PushJobService.b(context);
            com.bytedance.push.utils.e.a("PushAlive", "unregister JobSchedule success");
        }
    }
}
