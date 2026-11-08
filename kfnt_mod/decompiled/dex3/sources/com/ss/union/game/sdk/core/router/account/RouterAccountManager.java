package com.ss.union.game.sdk.core.router.account;

import com.ss.union.game.sdk.common.util.logger.LogUtils;
import com.ss.union.game.sdk.core.router.account.a.a;
import com.ss.union.game.sdk.core.router.account.in.IRouterAccount;
import com.ss.union.game.sdk.core.router.base.in.IBaseRouter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RouterAccountManager {
    private static final String a = "DevKit-AccountRouterManager";

    public static IBaseRouter<IRouterAccount> getBaseRouter() {
        return a.a();
    }

    public static IRouterAccount getAccountRouter() {
        return a.a();
    }

    public static void logD(String str) {
        LogUtils.log(a, str);
    }
}
