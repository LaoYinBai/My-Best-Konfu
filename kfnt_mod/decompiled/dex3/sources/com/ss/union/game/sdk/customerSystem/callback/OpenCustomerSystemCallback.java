package com.ss.union.game.sdk.customerSystem.callback;

import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface OpenCustomerSystemCallback {
    public static final int CODE_NOT_LOGIN = 1;

    void onFail(int i, String str);

    void onSuc();
}
