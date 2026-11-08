package com.ss.union.game.sdk.core.realName.fragment;

import android.os.Bundle;
import com.ss.union.game.sdk.common.util.KeyboardUtils;
import com.ss.union.game.sdk.common.util.UIUtils;
import com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NormalRealNameFragment extends RealNameFragment {
    private KeyboardUtils.KeyboardObserver q;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_fragment_real_name_authentication";
    }

    public static RealNameFragment a(int i, boolean z, LGRealNameCallback lGRealNameCallback) {
        Bundle bundle = new Bundle();
        NormalRealNameFragment normalRealNameFragment = new NormalRealNameFragment();
        bundle.putInt("key_bundle_type", i);
        bundle.putBoolean("key_show_close_btn", z);
        normalRealNameFragment.setArguments(bundle);
        normalRealNameFragment.setCallback(lGRealNameCallback);
        return normalRealNameFragment;
    }

    @Override // com.ss.union.game.sdk.core.realName.fragment.RealNameFragment, com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        super.initListener();
        this.q = KeyboardUtils.observeKeyboard(getContext(), getWindow(), new KeyboardUtils.OnKeyboardChangeListener() { // from class: com.ss.union.game.sdk.core.realName.fragment.NormalRealNameFragment.1
            @Override // com.ss.union.game.sdk.common.util.KeyboardUtils.OnKeyboardChangeListener
            public void keyboardShow(int i) {
                int iDip2Px = UIUtils.dip2Px(50.0f);
                if (NormalRealNameFragment.this.n.getScrollY() < iDip2Px / 2) {
                    NormalRealNameFragment.this.n.scrollBy(0, iDip2Px);
                }
            }

            @Override // com.ss.union.game.sdk.common.util.KeyboardUtils.OnKeyboardChangeListener
            public void keyboardHide(int i) {
                NormalRealNameFragment.this.n.scrollTo(0, 0);
            }
        });
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (this.q != null) {
            this.q.release();
        }
    }
}
