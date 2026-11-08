package com.mbridge.msdk.out;

import android.text.TextUtils;
import com.huawei.updatesdk.service.d.a.b;
import com.mbridge.msdk.foundation.same.net.Aa;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ChannelManager {
    public static void setChannel(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            Aa aa = new Aa();
            Method declaredMethod = aa.getClass().getDeclaredMethod(b.a, String.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(aa, str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
