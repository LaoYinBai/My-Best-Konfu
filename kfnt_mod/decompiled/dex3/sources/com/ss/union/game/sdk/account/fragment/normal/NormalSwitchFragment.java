package com.ss.union.game.sdk.account.fragment.normal;

import android.os.Bundle;
import com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NormalSwitchFragment extends BaseNormalLoginFragment {
    @Override // com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment
    protected int e() {
        return 3;
    }

    public static void a(BaseFragment baseFragment, boolean z) {
        if (baseFragment != null) {
            baseFragment.navigation(a(z));
        } else {
            new OperationBuilder(a(z)).show();
        }
    }

    private static NormalSwitchFragment a(boolean z) {
        NormalSwitchFragment normalSwitchFragment = new NormalSwitchFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(a, z);
        normalSwitchFragment.setArguments(bundle);
        return normalSwitchFragment;
    }

    @Override // com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment
    protected String c() {
        return ResourceUtils.getString(!LGAccountDataUtil.isLogined() ? "lg_login_switch_account_from_visitor_limit" : "lg_login_switch_account");
    }

    @Override // com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment
    protected String d() {
        return ResourceUtils.getString(!LGAccountDataUtil.isLogined() ? "lg_login_switch_other_account_from_visitor_limit" : "lg_switch_otherway");
    }
}
