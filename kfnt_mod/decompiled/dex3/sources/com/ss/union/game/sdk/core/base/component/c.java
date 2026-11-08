package com.ss.union.game.sdk.core.base.component;

import android.util.Log;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.union.game.sdk.common.util.reflect.Reflect;
import com.ss.union.game.sdk.common.util.reflect.ReflectException;
import com.ss.union.game.sdk.core.base.init.callback.LGRequestAppLogCallback;
import com.ss.union.game.sdk.core.base.init.callback.LGSplashInitCallback;
import com.ss.union.game.sdk.core.base.utils.LogCoreUtils;
import com.ss.union.game.sdk.core.init.callback.LGSdkInitCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    private static final String a = "com.ss.union.game.sdk.core.LGSDKDevKit";

    public static void a(LGSplashInitCallback lGSplashInitCallback) {
        try {
            Reflect.onClass(a).call("initSplash", lGSplashInitCallback);
        } catch (ReflectException e) {
            LogCoreUtils.logInit("No Devkit component is connected，splash init failed... " + Log.getStackTraceString(e));
            if (lGSplashInitCallback != null) {
                lGSplashInitCallback.onFinish();
            }
        }
    }

    public static boolean a(LGSdkInitCallback lGSdkInitCallback, LGRequestAppLogCallback lGRequestAppLogCallback) {
        try {
            Reflect.onClass(a).call(PointCategory.INIT, lGSdkInitCallback, lGRequestAppLogCallback);
            return true;
        } catch (ReflectException e) {
            LogCoreUtils.logInit("No Devkit component is connected... " + Log.getStackTraceString(e));
            return false;
        }
    }
}
