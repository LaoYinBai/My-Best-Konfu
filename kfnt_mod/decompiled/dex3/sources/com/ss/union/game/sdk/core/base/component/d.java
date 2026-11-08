package com.ss.union.game.sdk.core.base.component;

import android.app.Application;
import android.util.Log;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.union.game.sdk.common.util.reflect.Reflect;
import com.ss.union.game.sdk.common.util.reflect.ReflectException;
import com.ss.union.game.sdk.core.base.utils.LogCoreUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d {
    private static final String a = "com.ss.union.game.sdk.push.LGPushManager";

    public static void a(Application application) {
        try {
            Reflect.onClass(a).call(PointCategory.INIT, application);
        } catch (ReflectException e) {
            LogCoreUtils.logInit("No Push component is connected... " + Log.getStackTraceString(e));
        }
    }
}
