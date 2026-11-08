package com.ss.union.game.sdk.a;

import com.ss.android.pushmanager.PushCommonConstants;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.core.antiAddiction.abs.AbsAntiAddictionStrategy;
import com.ss.union.game.sdk.core.antiAddiction.bean.AntiAddictionInfo;
import com.ss.union.game.sdk.core.antiAddiction.config.AntiAddictionConfig;
import com.ss.union.game.sdk.core.antiAddiction.fragment.AntiAddictionFragment;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.constant.CoreUrls;
import com.ss.union.game.sdk.core.base.dialog.callback.StandardDialogListenerAdapter;
import com.ss.union.game.sdk.core.realName.RealNameManager;
import com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback;
import com.ss.union.game.sdk.core.realName.entity.LGRealNameRewardInfo;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a extends AbsAntiAddictionStrategy {
    private static final String a = "AntiAddictionAccount";

    @Override // com.ss.union.game.sdk.core.antiAddiction.abs.AbsAntiAddictionStrategy
    public void antiAddictionStart() {
        if (a()) {
            com.ss.union.game.sdk.c.a.b("antiAddictionStart hasVerifiedAndAdult");
        } else {
            e();
        }
    }

    @Override // com.ss.union.game.sdk.core.antiAddiction.abs.AbsAntiAddictionStrategy
    protected void antiAddictionFinished() {
        AntiAddictionFragment.show();
        AntiAddictionConfig.Account.triggerAnti();
    }

    public void a(LGRealNameCallback lGRealNameCallback) {
        a(103, lGRealNameCallback);
    }

    private boolean a() {
        return c() && b();
    }

    private boolean b() {
        return LGAccountDataUtil.isAdult();
    }

    private boolean c() {
        return LGAccountDataUtil.isIdentifyValidated();
    }

    private boolean d() {
        return LGAccountDataUtil.isVisitor();
    }

    private void e() {
        com.ss.union.game.sdk.c.a.b("queryAntiAdditionInfo");
        CoreNetClient.post(CoreUrls.RealName.URL_GET_ANTI_ADDICTION_INFO).param("type", "ACCOUNT").param("app_id", AppIdManager.lgAppID()).param(PushCommonConstants.KEY_USER_ID, LGAccountDataUtil.getCurrentUserOpenId()).param("token", LGAccountDataUtil.getLoginToken()).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.a.a.1
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                com.ss.union.game.sdk.c.a.b("updateAntiAddictionTime error code = " + coreNetResponse.code() + " msg = " + coreNetResponse.message());
                AntiAddictionInfo antiAddictionInfoInfo = AntiAddictionConfig.Account.info();
                antiAddictionInfoInfo.serverTime = System.currentTimeMillis() / 1000;
                antiAddictionInfoInfo.id = LGAccountDataUtil.getCurrentUserOpenId();
                AntiAddictionConfig.Account.update(antiAddictionInfoInfo);
                a.this.a(antiAddictionInfoInfo);
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                AntiAddictionInfo antiAddictionInfo = new AntiAddictionInfo();
                antiAddictionInfo.parse(coreNetResponse.data, coreNetResponse.serverTimestampS, LGAccountDataUtil.getCurrentUserOpenId());
                AntiAddictionConfig.Account.update(antiAddictionInfo);
                com.ss.union.game.sdk.c.a.b("updateAntiAddictionTime success remainingTime = " + antiAddictionInfo.remainingTime);
                a.this.a(antiAddictionInfo);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(AntiAddictionInfo antiAddictionInfo) {
        if (antiAddictionInfo.isInMinorLimit) {
            com.ss.union.game.sdk.c.a.b("isInMinorLimit = true, show AntiAddictionFragment");
            AntiAddictionFragment.show();
        } else {
            b(antiAddictionInfo);
        }
    }

    private void b(AntiAddictionInfo antiAddictionInfo) {
        com.ss.union.game.sdk.c.a.b("checkAntiAddictionCountdown");
        if (c() && b()) {
            com.ss.union.game.sdk.c.a.b("checkAntiAddictionCountdown has real name and adult");
        } else {
            c(antiAddictionInfo);
        }
    }

    private void c(AntiAddictionInfo antiAddictionInfo) {
        int iRemainTime = AntiAddictionConfig.Account.remainTime() * 60;
        com.ss.union.game.sdk.c.a.b("checkRemindingCountdown，remainingTime = " + iRemainTime);
        if (iRemainTime == 0) {
            if (!c() && !b.a().b()) {
                com.ss.union.game.sdk.c.a.b("checkRemindingCountdown unRealName and hasShowRealNameWindow = false -- start show RealName window");
                a(105, antiAddictionInfo.isHoliday);
                return;
            } else {
                AntiAddictionFragment.show();
                return;
            }
        }
        startAntiAddiction(iRemainTime);
    }

    private void a(final int i, final boolean z) {
        a(i, new LGRealNameCallback() { // from class: com.ss.union.game.sdk.a.a.2
            @Override // com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback
            public void onSuccess(boolean z2, boolean z3, LGRealNameRewardInfo lGRealNameRewardInfo) {
                a.this.a(i, z2, z3);
            }

            @Override // com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback
            public void onFail(int i2, String str) {
                a.this.a(i, z, i2, str);
            }
        });
    }

    private void a(final int i, final LGRealNameCallback lGRealNameCallback) {
        if (c.a(i)) {
            c.a(i, new StandardDialogListenerAdapter() { // from class: com.ss.union.game.sdk.a.a.3
                @Override // com.ss.union.game.sdk.core.base.dialog.callback.StandardDialogListenerAdapter, com.ss.union.game.sdk.core.base.dialog.callback.a
                public boolean onWeaknessBtnClick(BaseFragment baseFragment) {
                    baseFragment.navigation(RealNameManager.createRealNameFragment(i, false, lGRealNameCallback));
                    return true;
                }
            });
        } else {
            RealNameManager.showRealNameWindow(i, lGRealNameCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, boolean z, boolean z2) {
        com.ss.union.game.sdk.c.a.b("showRealNameWindow onSuccess，isRealNameValid = " + z + "--isAdult = " + z2);
        antiAddictionStart();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, boolean z, int i2, String str) {
        com.ss.union.game.sdk.c.a.b("showRealNameWindow onFail，errorCode = " + i2 + "--errorMsg = " + str);
        if (i2 != -1004) {
            antiAddictionStart();
        } else {
            AntiAddictionFragment.show();
        }
    }
}
