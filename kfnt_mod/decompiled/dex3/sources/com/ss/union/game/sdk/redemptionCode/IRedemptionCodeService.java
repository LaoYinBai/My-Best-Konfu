package com.ss.union.game.sdk.redemptionCode;

import androidx.annotation.Keep;
import com.ss.union.game.sdk.redemptionCode.callback.LGRedemptionCodeConfirmCallback;
import com.ss.union.game.sdk.redemptionCode.callback.LGRedemptionCodeInquireCallback;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IRedemptionCodeService {
    void confirmRedeemCode(String str, LGRedemptionCodeConfirmCallback lGRedemptionCodeConfirmCallback);

    void inquireRedeemCode(String str, LGRedemptionCodeInquireCallback lGRedemptionCodeInquireCallback);
}
