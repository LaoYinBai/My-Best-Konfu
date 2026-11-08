package com.ss.union.game.sdk.account.fragment.normal;

import android.os.Bundle;
import com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.util.ResourceUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NormalLoginFragment extends BaseNormalLoginFragment {
    @Override // com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment
    protected int e() {
        return 1;
    }

    public static void a(BaseFragment baseFragment, boolean z) {
        if (baseFragment != null) {
            baseFragment.navigation(a(z));
        } else {
            new OperationBuilder(a(z)).show();
        }
    }

    private static NormalLoginFragment a(boolean z) {
        NormalLoginFragment normalLoginFragment = new NormalLoginFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(a, z);
        normalLoginFragment.setArguments(bundle);
        return normalLoginFragment;
    }

    @Override // com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment
    protected String c() {
        return ResourceUtils.getString("lg_login_area_login_game");
    }

    @Override // com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment
    protected String d() {
        return ResourceUtils.getString("lg_login_otherway");
    }
}
