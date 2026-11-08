package com.ss.android.account;

import com.bytedance.sdk.account.impl.BDAccountDelegate;
import com.bytedance.sdk.account.platform.api.IAccountSettingsService;
import com.bytedance.sdk.account.platform.base.AuthorizeFramework;
import com.bytedance.sdk.account.utils.IProjectMode;
import com.ss.android.TTAccountConfig;
import com.ss.android.TTAccountExtraConfig;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TTAccountInit {
    private static volatile TTAccountConfig sConfig;
    private static volatile TTAccountExtraConfig sExtraConfig;
    private static volatile IProjectMode sProjectMode;

    public static void init(TTAccountConfig tTAccountConfig) {
        if (tTAccountConfig == null) {
            return;
        }
        sConfig = tTAccountConfig;
        AuthorizeFramework.registerService(IAccountSettingsService.class, BDAccountDelegate.getSettingsInstance(sConfig.getApplicationContext()));
    }

    public static TTAccountConfig getConfig() {
        if (sConfig == null) {
            throw new IllegalStateException("not init TTAccount config");
        }
        return sConfig;
    }

    public static TTAccountExtraConfig getExtraConfig() {
        return sExtraConfig;
    }

    public static void setExtraConfig(TTAccountExtraConfig tTAccountExtraConfig) {
        sExtraConfig = tTAccountExtraConfig;
    }

    public static void initProjectMode(IProjectMode iProjectMode) {
        sProjectMode = iProjectMode;
    }

    public static IProjectMode getProjectMode() {
        return sProjectMode;
    }
}
