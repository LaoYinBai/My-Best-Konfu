package com.ss.union.game.sdk;

import com.ss.union.game.sdk.account.LGSDKAccountService;
import com.ss.union.game.sdk.account.b.a;
import com.ss.union.game.sdk.core.router.account.RouterAccountManager;
import com.ss.union.game.sdk.customerSystem.ICustomerSystemService;
import com.ss.union.game.sdk.feedback.LGSDKFeedbackService;
import com.ss.union.game.sdk.mail.ILGMailService;
import com.ss.union.game.sdk.mail.b;
import com.ss.union.game.sdk.redemptionCode.IRedemptionCodeService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGAccountManager {
    private static void init() {
        a.a().b();
        RouterAccountManager.getBaseRouter().register(new com.ss.union.game.sdk.b.a());
    }

    public static LGSDKAccountService getAccountService() {
        return a.a();
    }

    public static ICustomerSystemService getCustomerSystemService() {
        return com.ss.union.game.sdk.customerSystem.a.a();
    }

    public static LGSDKFeedbackService getFeedbackService() {
        return com.ss.union.game.sdk.feedback.b.a.a();
    }

    public static ILGMailService getMailService() {
        return b.a();
    }

    public static IRedemptionCodeService getRedemptionService() {
        return com.ss.union.game.sdk.redemptionCode.b.a();
    }
}
