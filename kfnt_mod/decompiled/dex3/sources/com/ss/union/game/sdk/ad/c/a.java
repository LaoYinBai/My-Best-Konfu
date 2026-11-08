package com.ss.union.game.sdk.ad.c;

import android.text.TextUtils;
import android.util.Log;
import com.ss.union.game.sdk.core.base.cps.CpsLog;
import java.lang.reflect.Field;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class a {
    a() {
    }

    static void a(Object obj, String str, String str2) throws IllegalAccessException, IllegalArgumentException {
        if (obj == null || TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object obj2 = field.get(obj);
                if ((obj2 instanceof String) && TextUtils.equals(str, (String) obj2)) {
                    field.set(obj, str2);
                    CpsLog.d("替换广告位id成功", "旧的广告位id:" + str + "=====>新的广告位id:" + str2);
                    return;
                }
            }
        } catch (Exception e) {
            CpsLog.d("替换广告位id失败", e.getMessage());
            e.printStackTrace();
        }
        CpsLog.d(Log.getStackTraceString(new Exception("替换广告位id失败")));
    }
}
