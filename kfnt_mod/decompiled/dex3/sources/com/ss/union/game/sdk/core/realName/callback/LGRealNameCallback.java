package com.ss.union.game.sdk.core.realName.callback;

import com.ss.union.game.sdk.core.realName.entity.LGRealNameRewardInfo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface LGRealNameCallback {
    void onFail(int i, String str);

    void onSuccess(boolean z, boolean z2, LGRealNameRewardInfo lGRealNameRewardInfo);
}
