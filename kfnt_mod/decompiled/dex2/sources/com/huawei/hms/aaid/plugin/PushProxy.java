package com.huawei.hms.aaid.plugin;

import android.content.Context;
import com.huawei.hmf.tasks.f;
import com.huawei.hms.common.ApiException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface PushProxy {
    void deleteToken(Context context) throws ApiException;

    void getToken(Context context);

    f<Void> subscribe(Context context, String str);

    f<Void> turnOff(Context context);

    f<Void> turnOn(Context context);

    f<Void> unsubscribe(Context context, String str);
}
