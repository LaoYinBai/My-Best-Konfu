package com.vivo.push.util;

import android.content.Context;
import com.vivo.push.NoPorGuard;
import com.vivo.push.model.InsideNotificationItem;

@NoPorGuard
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface BaseNotifyDataAdapter {
    int getDefaultNotifyIcon();

    int getDefaultSmallIconId();

    int getNotifyMode(InsideNotificationItem insideNotificationItem);

    void init(Context context);
}
