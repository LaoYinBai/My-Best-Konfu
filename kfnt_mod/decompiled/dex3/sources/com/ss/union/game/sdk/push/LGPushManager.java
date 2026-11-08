package com.ss.union.game.sdk.push;

import android.app.Application;
import com.ss.union.game.sdk.common.util.logger.LogUtils;
import com.ss.union.game.sdk.core.base.cps.CPSHelper;
import com.ss.union.game.sdk.core.base.init.GameSdkCoreInit;
import com.ss.union.game.sdk.push.callback.IPushService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGPushManager {
    public static void init(Application application) {
        GameSdkCoreInit.init(application);
        if (CPSHelper.isEnable()) {
            if (CPSHelper.isPushEnable()) {
                c.a().a(application);
                return;
            }
            LogUtils.log("CPSHelper.isPushEnable : " + CPSHelper.isPushEnable());
            return;
        }
        c.a().a(application);
    }

    public static IPushService getPushService() {
        if (CPSHelper.isEnable()) {
            if (!CPSHelper.isPushEnable()) {
                return b.a();
            }
            return c.a();
        }
        return c.a();
    }
}
