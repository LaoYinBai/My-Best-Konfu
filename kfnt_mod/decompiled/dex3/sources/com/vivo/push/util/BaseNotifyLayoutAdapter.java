package com.vivo.push.util;

import android.content.Context;
import com.vivo.push.NoPorGuard;

@NoPorGuard
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface BaseNotifyLayoutAdapter {
    int getNotificationLayout();

    int getSuitIconId();

    int getTitleColor();

    void init(Context context);
}
