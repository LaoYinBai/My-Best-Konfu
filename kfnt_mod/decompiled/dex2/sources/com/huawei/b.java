package com.huawei;

import android.content.Context;
import com.bytedance.push.utils.e;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.hw.HWPushAdapter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    public static boolean a(Context context) {
        e.a(HWPushAdapter.TAG, "isHMSAvailableCode = " + b(context));
        return HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == 0;
    }

    public static int b(Context context) {
        return HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context);
    }
}
