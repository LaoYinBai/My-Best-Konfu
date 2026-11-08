package com.ss.union.game.sdk.account.callback;

import com.ss.union.game.sdk.common.callback.IFailCallback;
import com.ss.union.game.sdk.core.base.account.model.User;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IUnbindAccountCallback extends IFailCallback {
    void onUnbindSuccess(User user);
}
