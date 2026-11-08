package com.ss.union.game.sdk.account.callback;

import com.bytedance.sdk.account.api.response.LoginByTicketResponse;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IOneKeyLoginCallBack {
    void onFail(String str, String str2);

    void onSuccess(LoginByTicketResponse loginByTicketResponse);
}
