package com.ss.union.game.sdk.account.fragment.oneKey;

import android.os.Bundle;
import android.text.TextUtils;
import com.ss.union.game.sdk.account.b.a;
import com.ss.union.game.sdk.account.callback.IAuthorizeCallBack;
import com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment;
import com.ss.union.game.sdk.account.fragment.gamecenter.VBridgeFragment;
import com.ss.union.game.sdk.account.fragment.normal.NormalSwitchFragment;
import com.ss.union.game.sdk.account.result.LGCarrierQueryResult;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.account.constant.LGAccountConstant;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.privacy.LGPrivacyPolicyManager;
import com.ss.union.game.sdk.vcenter.c;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class OneKeySwitchFragment extends BaseOneKeyFragment {
    @Override // com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment
    protected void e() {
    }

    @Override // com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment
    protected int j() {
        return 3;
    }

    public static void a(final BaseFragment baseFragment, final boolean z) {
        if (a.a != null) {
            a.a.onPreparePop();
        }
        LGPrivacyPolicyManager.setLoginPrivacyChecked(false);
        if (c.a().b()) {
            VBridgeFragment vBridgeFragment = new VBridgeFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean(VBridgeFragment.b, z);
            bundle.putInt(VBridgeFragment.a, 3);
            vBridgeFragment.setArguments(bundle);
            if (baseFragment != null) {
                baseFragment.navigation(vBridgeFragment);
                return;
            } else {
                new OperationBuilder(vBridgeFragment).show();
                return;
            }
        }
        a(new IAuthorizeCallBack() { // from class: com.ss.union.game.sdk.account.fragment.oneKey.OneKeySwitchFragment.1
            @Override // com.ss.union.game.sdk.account.callback.IAuthorizeCallBack
            public void onSuccess(String str, String str2) throws JSONException {
                User currentUserLocal = LGAccountDataUtil.getCurrentUserLocal();
                if (currentUserLocal != null && ((TextUtils.equals(currentUserLocal.login_type, LGAccountConstant.LoginType.LOGIN_TYPE_CLOUD_PHONE.getName()) || TextUtils.equals(currentUserLocal.login_type, LGAccountConstant.LoginType.LOGIN_TYPE_PHONE.getName())) && TextUtils.equals(str, currentUserLocal.mobile))) {
                    NormalSwitchFragment.a(baseFragment, z);
                } else {
                    com.ss.union.game.sdk.account.a.a.a("scene_switch_account_acquire_phone_result", true);
                    OneKeySwitchFragment.c(baseFragment, z);
                }
            }

            @Override // com.ss.union.game.sdk.account.callback.IAuthorizeCallBack
            public void onFail(String str, String str2) throws JSONException {
                com.ss.union.game.sdk.account.a.a.a("scene_switch_account_acquire_phone_result", false);
                NormalSwitchFragment.a(baseFragment, z);
            }

            @Override // com.ss.union.game.sdk.account.callback.IAuthorizeCallBack
            public void onTimeout() throws JSONException {
                com.ss.union.game.sdk.account.a.a.a("scene_switch_account_acquire_phone_result", false);
                NormalSwitchFragment.a(baseFragment, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(BaseFragment baseFragment, boolean z) {
        if (baseFragment != null) {
            baseFragment.navigation(a(z));
        } else {
            new OperationBuilder(a(z)).show();
        }
    }

    private static OneKeySwitchFragment a(boolean z) {
        OneKeySwitchFragment oneKeySwitchFragment = new OneKeySwitchFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(BaseOneKeyFragment.a, z);
        oneKeySwitchFragment.setArguments(bundle);
        return oneKeySwitchFragment;
    }

    @Override // com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment
    protected void a(LGCarrierQueryResult lGCarrierQueryResult) {
        super.a(lGCarrierQueryResult);
        com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.i);
    }

    @Override // com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment
    protected String f() {
        return !LGAccountDataUtil.isLogined() ? "lg_login_switch_account_from_visitor_limit" : "lg_login_switch_account";
    }

    @Override // com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment
    protected String g() {
        return !LGAccountDataUtil.isLogined() ? "lg_login_switch_current_phone_from_visitor_limit" : "lg_login_switch_current_phone";
    }

    @Override // com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment
    protected String h() {
        return !LGAccountDataUtil.isLogined() ? "lg_login_switch_other_account_from_visitor_limit" : "lg_login_switch_other_account";
    }

    @Override // com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment
    protected void i() {
        NormalSwitchFragment.a(this, d());
    }

    @Override // com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment
    protected void b(String str, String str2) {
        NormalSwitchFragment.a(this, d());
    }
}
