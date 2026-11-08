package com.ss.union.game.sdk.account;

import android.app.Activity;
import com.ss.union.game.sdk.account.callback.ICloseAccountCallback;
import com.ss.union.game.sdk.account.callback.ILoginBoxPopCallBack;
import com.ss.union.game.sdk.account.callback.INickNameUpdate;
import com.ss.union.game.sdk.account.callback.IQueryAccountInfoCallback;
import com.ss.union.game.sdk.account.callback.IUnbindAccountCallback;
import com.ss.union.game.sdk.account.callback.LGGlobalLoginCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface LGSDKAccountService {
    void closeAccount(ICloseAccountCallback iCloseAccountCallback);

    boolean isVisitor();

    void loginNormal(Activity activity);

    void queryAccountInfo(IQueryAccountInfoCallback iQueryAccountInfoCallback);

    void registerLoginBoxPopCallBack(ILoginBoxPopCallBack iLoginBoxPopCallBack);

    void setGlobalLoginCallback(LGGlobalLoginCallback lGGlobalLoginCallback);

    void switchAccount(Activity activity);

    void unBind(IUnbindAccountCallback iUnbindAccountCallback);

    void updateNickName(INickNameUpdate iNickNameUpdate);

    void visitorBindAccount(Activity activity);
}
