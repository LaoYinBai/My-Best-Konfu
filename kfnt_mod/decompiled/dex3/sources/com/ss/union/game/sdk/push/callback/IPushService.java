package com.ss.union.game.sdk.push.callback;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IPushService {
    void enableRedBadgeWithDefaultStrategy(Context context, boolean z);

    boolean notificationPermissionEnable(Context context);

    void setOnPushArriveListener(OnPushMessageArriveListener onPushMessageArriveListener);

    void setOnPushClickListener(OnNotificationClickListener onNotificationClickListener);

    void setRedBadgeNumber(Context context, int i);

    void startLaunchActivity(Context context);
}
