package com.ss.union.game.sdk.push;

import android.content.Context;
import com.ss.union.game.sdk.push.callback.IPushService;
import com.ss.union.game.sdk.push.callback.OnNotificationClickListener;
import com.ss.union.game.sdk.push.callback.OnPushMessageArriveListener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b implements IPushService {

    private static class a {
        static b a = new b();

        private a() {
        }
    }

    public static b a() {
        return a.a;
    }

    @Override // com.ss.union.game.sdk.push.callback.IPushService
    public void setOnPushClickListener(OnNotificationClickListener onNotificationClickListener) {
        com.ss.union.game.sdk.push.d.a.a("Push is not open");
    }

    @Override // com.ss.union.game.sdk.push.callback.IPushService
    public void setOnPushArriveListener(OnPushMessageArriveListener onPushMessageArriveListener) {
        com.ss.union.game.sdk.push.d.a.a("Push is not open");
    }

    @Override // com.ss.union.game.sdk.push.callback.IPushService
    public void enableRedBadgeWithDefaultStrategy(Context context, boolean z) {
        com.ss.union.game.sdk.push.d.a.a("Push is not open");
    }

    @Override // com.ss.union.game.sdk.push.callback.IPushService
    public void setRedBadgeNumber(Context context, int i) {
        com.ss.union.game.sdk.push.d.a.a("Push is not open");
    }

    @Override // com.ss.union.game.sdk.push.callback.IPushService
    public boolean notificationPermissionEnable(Context context) {
        com.ss.union.game.sdk.push.d.a.a("Push is not open");
        return false;
    }

    @Override // com.ss.union.game.sdk.push.callback.IPushService
    public void startLaunchActivity(Context context) {
        com.ss.union.game.sdk.push.d.a.a("Push is not open");
    }
}
