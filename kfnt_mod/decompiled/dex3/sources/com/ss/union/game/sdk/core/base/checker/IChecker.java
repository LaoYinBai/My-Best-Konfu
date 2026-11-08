package com.ss.union.game.sdk.core.base.checker;

import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.utils.LogCoreUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class IChecker {
    protected abstract String check();

    public String start(boolean z) {
        if (!z && !ConfigManager.AppConfig.isDebug()) {
            return "";
        }
        String strCheck = check();
        LogCoreUtils.logInit(strCheck);
        return strCheck;
    }
}
