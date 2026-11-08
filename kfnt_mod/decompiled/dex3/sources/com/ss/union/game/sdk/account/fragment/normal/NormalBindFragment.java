package com.ss.union.game.sdk.account.fragment.normal;

import android.os.Bundle;
import com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.util.ResourceUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NormalBindFragment extends BaseNormalLoginFragment {
    @Override // com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment
    protected int e() {
        return 2;
    }

    public static void a(BaseFragment baseFragment, boolean z) {
        if (baseFragment != null) {
            baseFragment.navigation(a(z));
        } else {
            new OperationBuilder(a(z)).show();
        }
    }

    private static NormalBindFragment a(boolean z) {
        NormalBindFragment normalBindFragment = new NormalBindFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(a, z);
        normalBindFragment.setArguments(bundle);
        return normalBindFragment;
    }

    @Override // com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment
    protected String c() {
        return ResourceUtils.getString("lg_login_bind_account");
    }

    @Override // com.ss.union.game.sdk.account.fragment.base.BaseNormalLoginFragment
    protected String d() {
        return ResourceUtils.getString("lg_bind_otherway");
    }
}
