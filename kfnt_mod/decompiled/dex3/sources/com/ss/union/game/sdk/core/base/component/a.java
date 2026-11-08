package com.ss.union.game.sdk.core.base.component;

import android.util.Log;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.union.game.sdk.common.util.reflect.Reflect;
import com.ss.union.game.sdk.common.util.reflect.ReflectException;
import com.ss.union.game.sdk.core.base.utils.LogCoreUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static final String a = "com.ss.union.game.sdk.LGAccountManager";
    private static boolean b;

    public static void a() {
        try {
            Reflect.onClass(a).call(PointCategory.INIT);
        } catch (ReflectException e) {
            LogCoreUtils.logInit("No Account component is connected... " + Log.getStackTraceString(e));
        }
    }

    public static boolean b() {
        if (b) {
            return true;
        }
        try {
            Reflect.onClass(a);
            b = true;
            return true;
        } catch (ReflectException unused) {
            return false;
        }
    }
}
