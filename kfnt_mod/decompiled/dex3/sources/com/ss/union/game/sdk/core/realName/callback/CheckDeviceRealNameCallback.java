package com.ss.union.game.sdk.core.realName.callback;

import com.ss.union.game.sdk.core.realName.entity.CheckDeviceRealNameInfo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface CheckDeviceRealNameCallback {
    void onFail(int i, String str);

    void onSuccess(CheckDeviceRealNameInfo checkDeviceRealNameInfo);
}
