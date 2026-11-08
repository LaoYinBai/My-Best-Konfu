package com.ss.union.game.sdk.account.fragment.oneKey;

import android.os.Bundle;
import com.ss.union.game.sdk.account.b.a;
import com.ss.union.game.sdk.account.callback.IAuthorizeCallBack;
import com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment;
import com.ss.union.game.sdk.account.fragment.gamecenter.VBridgeFragment;
import com.ss.union.game.sdk.account.fragment.normal.NormalLoginFragment;
import com.ss.union.game.sdk.account.result.LGCarrierQueryResult;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.core.privacy.LGPrivacyPolicyManager;
import com.ss.union.game.sdk.vcenter.c;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class OneKeyLoginFragment extends BaseOneKeyFragment {
    @Override // com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment
    protected void e() {
    }

    @Override // com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment
    protected String f() {
        return "lg_login_area_login_game";
    }

    @Override // com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment
    protected String g() {
        return "lg_login_onekey_login_phone";
    }

    @Override // com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment
    protected String h() {
        return "lg_login_other_way";
    }

    @Override // com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment
    protected int j() {
        return 1;
    }

    public static void a(final BaseFragment baseFragment, final boolean z, boolean z2) {
        try {
            if (a.a != null) {
                a.a.onPreparePop();
            }
        } catch (Throwable unused) {
        }
        LGPrivacyPolicyManager.setLoginPrivacyChecked(false);
        if (c.a().b()) {
            VBridgeFragment vBridgeFragment = new VBridgeFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean(VBridgeFragment.b, z);
            bundle.putInt(VBridgeFragment.a, 1);
            bundle.putBoolean(VBridgeFragment.c, z2);
            vBridgeFragment.setArguments(bundle);
            if (baseFragment != null) {
                baseFragment.navigation(vBridgeFragment);
                return;
            } else {
                new OperationBuilder(vBridgeFragment).show();
                return;
            }
        }
        a(new IAuthorizeCallBack() { // from class: com.ss.union.game.sdk.account.fragment.oneKey.OneKeyLoginFragment.1
            @Override // com.ss.union.game.sdk.account.callback.IAuthorizeCallBack
            public void onSuccess(String str, String str2) throws JSONException {
                com.ss.union.game.sdk.account.a.a.a("login_box_acquire_phone_result", true);
                OneKeyLoginFragment.b(baseFragment, z);
            }

            @Override // com.ss.union.game.sdk.account.callback.IAuthorizeCallBack
            public void onFail(String str, String str2) throws JSONException {
                com.ss.union.game.sdk.account.a.a.a("login_box_acquire_phone_result", false);
                NormalLoginFragment.a(baseFragment, z);
            }

            @Override // com.ss.union.game.sdk.account.callback.IAuthorizeCallBack
            public void onTimeout() throws JSONException {
                com.ss.union.game.sdk.account.a.a.a("login_box_acquire_phone_result", false);
                NormalLoginFragment.a(baseFragment, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(BaseFragment baseFragment, boolean z) {
        if (baseFragment != null) {
            baseFragment.navigation(a(z));
        } else {
            new OperationBuilder(a(z)).show();
        }
    }

    private static OneKeyLoginFragment a(boolean z) {
        return new OneKeyLoginFragment();
    }

    @Override // com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment
    protected void a(LGCarrierQueryResult lGCarrierQueryResult) {
        super.a(lGCarrierQueryResult);
        com.ss.union.game.sdk.account.a.a.a(com.ss.union.game.sdk.account.a.a.g);
    }

    @Override // com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment
    protected void i() {
        NormalLoginFragment.a(this, d());
    }

    @Override // com.ss.union.game.sdk.account.fragment.base.BaseOneKeyFragment
    protected void b(String str, String str2) {
        NormalLoginFragment.a(this, d());
    }
}
