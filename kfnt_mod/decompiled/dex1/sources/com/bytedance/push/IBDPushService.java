package com.bytedance.push;

import android.app.Application;
import android.content.Context;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IBDPushService {
    void enableRedBadgeWithDefaultStrategy(Context context, boolean z);

    void enableRedBadgeWithStrategy(Context context, boolean z, int i, String str);

    void handleAppLogUpdate(Context context, String str, String str2, String str3, String str4, String str5);

    void handleAppLogUpdate(Context context, Map<String, String> map);

    void init(Application application, PushContext pushContext, boolean z, String str);

    void setAlias(Context context, String str, ICallback iCallback);

    void setOnPushArriveListener(OnPushArriveListener onPushArriveListener);

    void setOnPushClickListener(OnPushClickListener onPushClickListener);

    void setOnTrackCallback(ITracker iTracker);

    void setPushEnable(Context context, boolean z);

    void setRedBadgeNumber(Context context, int i);
}
