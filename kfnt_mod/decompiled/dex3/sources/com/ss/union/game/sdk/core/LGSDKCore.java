package com.ss.union.game.sdk.core;

import android.content.Context;
import com.ss.union.game.sdk.core.applog.AppLogAbConfigGetListener;
import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.debug.IDebugService;
import com.ss.union.game.sdk.core.init.callback.LGSdkInitCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGSDKCore {
    public static void init(Context context, LGSdkInitCallback lGSdkInitCallback) {
        com.ss.union.game.sdk.core.base.init.provider.a.a(context, lGSdkInitCallback);
    }

    public static void registerAbConfigGetListener(AppLogAbConfigGetListener appLogAbConfigGetListener) {
        AppLogManager.getInstance().registerAbConfigGetListener(appLogAbConfigGetListener);
    }

    @Deprecated
    public static void addSdkInitCallback(LGSdkInitCallback lGSdkInitCallback) {
        com.ss.union.game.sdk.core.base.init.provider.a.a(lGSdkInitCallback);
    }

    public static boolean isSdkInitSuccess() {
        return com.ss.union.game.sdk.core.base.init.provider.a.a();
    }

    public static IDebugService getDebugService() {
        return com.ss.union.game.sdk.core.base.debug.a.a();
    }
}
