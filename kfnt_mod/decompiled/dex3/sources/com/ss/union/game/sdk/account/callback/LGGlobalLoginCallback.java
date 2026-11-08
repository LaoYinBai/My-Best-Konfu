package com.ss.union.game.sdk.account.callback;

import com.ss.union.game.sdk.common.result.LGSDKResult;
import com.ss.union.game.sdk.core.base.account.model.User;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface LGGlobalLoginCallback {
    public static final int AccountBind = 2;
    public static final int Login = 1;
    public static final int SwitchAccount = 3;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ApiLoginType {
    }

    void onFail(LGSDKResult lGSDKResult, int i);

    void onSuccess(User user, int i);
}
