package com.bytedance.sdk.account.impl;

import android.content.Context;
import com.bytedance.sdk.account.api.IBDAccount;
import com.bytedance.sdk.account.api.IBDAccountAPI;
import com.bytedance.sdk.account.api.IBDAccountPlatformAPI;
import com.bytedance.sdk.account.platform.api.IAccountSettingsService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BDAccountDelegate {
    public static IBDAccount instance(Context context) {
        return BDAccountManager.instance(context);
    }

    public static IBDAccountAPI createBDAccountApi(Context context) {
        return new BDAccountApiImpl(context);
    }

    public static IAccountSettingsService getSettingsInstance(Context context) {
        return BDAccountSettingsManager.instance(context);
    }

    public static IBDAccountPlatformAPI createPlatformAPI(Context context) {
        return new BDAccountPlatformImpl(context);
    }
}
