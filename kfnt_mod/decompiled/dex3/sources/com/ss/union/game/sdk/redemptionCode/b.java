package com.ss.union.game.sdk.redemptionCode;

import android.content.Context;
import android.text.TextUtils;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.core.LGSDKCore;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.redemptionCode.callback.LGRedemptionCodeConfirmCallback;
import com.ss.union.game.sdk.redemptionCode.callback.LGRedemptionCodeInquireCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b implements IRedemptionCodeService {
    private static volatile IRedemptionCodeService a = null;
    private static final String b = "RedemptionCodeServiceImpl";

    private b() {
    }

    public static IRedemptionCodeService a() {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b();
                }
            }
        }
        return a;
    }

    @Override // com.ss.union.game.sdk.redemptionCode.IRedemptionCodeService
    public void inquireRedeemCode(String str, final LGRedemptionCodeInquireCallback lGRedemptionCodeInquireCallback) {
        if (lGRedemptionCodeInquireCallback == null) {
            com.ss.union.game.sdk.c.a.g("LGRedemptionCodeInquireCallback 为空");
            return;
        }
        Context context = GlobalApplicationUtils.getContext();
        if (!LGSDKCore.isSdkInitSuccess() || context == null) {
            com.ss.union.game.sdk.redemptionCode.a.a.a(-204);
            lGRedemptionCodeInquireCallback.onFail(-204, "SDK还未初始化完成");
        } else if (TextUtils.isEmpty(str)) {
            com.ss.union.game.sdk.redemptionCode.a.a.a(3);
            lGRedemptionCodeInquireCallback.onFail(3, a.h);
        } else if (!LGAccountDataUtil.isLogined()) {
            com.ss.union.game.sdk.redemptionCode.a.a.a(2);
            lGRedemptionCodeInquireCallback.onFail(2, a.f);
        } else {
            com.ss.union.game.sdk.redemptionCode.b.a.a(str, new LGRedemptionCodeInquireCallback() { // from class: com.ss.union.game.sdk.redemptionCode.b.1
                @Override // com.ss.union.game.sdk.redemptionCode.callback.LGRedemptionCodeInquireCallback
                public void onSuccess(String str2) {
                    com.ss.union.game.sdk.c.a.g("inquireRedeemCode success: " + str2);
                    lGRedemptionCodeInquireCallback.onSuccess(str2);
                }

                @Override // com.ss.union.game.sdk.redemptionCode.callback.LGRedemptionCodeInquireCallback
                public void onFail(int i, String str2) {
                    com.ss.union.game.sdk.c.a.g("inquireRedeemCode fail: code= " + i + " msg= " + str2);
                    com.ss.union.game.sdk.redemptionCode.a.a.a(i);
                    lGRedemptionCodeInquireCallback.onFail(i, str2);
                }
            });
            com.ss.union.game.sdk.redemptionCode.a.a.a();
        }
    }

    @Override // com.ss.union.game.sdk.redemptionCode.IRedemptionCodeService
    public void confirmRedeemCode(String str, final LGRedemptionCodeConfirmCallback lGRedemptionCodeConfirmCallback) {
        if (lGRedemptionCodeConfirmCallback == null) {
            com.ss.union.game.sdk.c.a.g("LGRedemptionCodeConfirmCallback 为空");
            return;
        }
        Context context = GlobalApplicationUtils.getContext();
        if (!LGSDKCore.isSdkInitSuccess() || context == null) {
            com.ss.union.game.sdk.redemptionCode.a.a.b(-204);
            lGRedemptionCodeConfirmCallback.onFail(-204, "SDK还未初始化完成");
        } else if (TextUtils.isEmpty(str)) {
            com.ss.union.game.sdk.redemptionCode.a.a.b(3);
            lGRedemptionCodeConfirmCallback.onFail(3, a.h);
        } else if (!LGAccountDataUtil.isLogined()) {
            com.ss.union.game.sdk.redemptionCode.a.a.b(2);
            lGRedemptionCodeConfirmCallback.onFail(2, a.f);
        } else {
            com.ss.union.game.sdk.redemptionCode.b.a.a(str, new LGRedemptionCodeConfirmCallback() { // from class: com.ss.union.game.sdk.redemptionCode.b.2
                @Override // com.ss.union.game.sdk.redemptionCode.callback.LGRedemptionCodeConfirmCallback
                public void onSuccess(String str2) {
                    com.ss.union.game.sdk.c.a.g("confirmRedeemCode success");
                    com.ss.union.game.sdk.redemptionCode.a.a.b();
                    lGRedemptionCodeConfirmCallback.onSuccess(str2);
                }

                @Override // com.ss.union.game.sdk.redemptionCode.callback.LGRedemptionCodeConfirmCallback
                public void onFail(int i, String str2) {
                    com.ss.union.game.sdk.c.a.g("confirmRedeemCode fail: code= " + i + " msg= " + str2);
                    com.ss.union.game.sdk.redemptionCode.a.a.b(i);
                    lGRedemptionCodeConfirmCallback.onFail(i, str2);
                }
            });
        }
    }
}
