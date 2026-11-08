package com.ss.union.game.sdk.pay.fragment;

import android.os.Bundle;
import android.view.View;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.mvp.IPresenter;
import com.ss.union.game.sdk.core.realName.RealNameManager;
import com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback;
import com.ss.union.game.sdk.core.realName.entity.LGRealNameRewardInfo;
import com.ss.union.game.sdk.pay.b.a;
import com.ss.union.game.sdk.pay.callback.PayTipsForUnnamedCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PayTipsForUnnamedFragment extends BaseFragment<PayTipsForUnnamedCallback, IPresenter> {
    View a;
    View b;
    View c;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_pay_tips_for_unnamed";
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    public boolean onBackPressed() {
        return true;
    }

    public static void a(PayTipsForUnnamedCallback payTipsForUnnamedCallback) {
        new OperationBuilder(b(payTipsForUnnamedCallback)).cancelable(false).show();
    }

    public static PayTipsForUnnamedFragment b(PayTipsForUnnamedCallback payTipsForUnnamedCallback) {
        PayTipsForUnnamedFragment payTipsForUnnamedFragment = new PayTipsForUnnamedFragment();
        payTipsForUnnamedFragment.setCallback(payTipsForUnnamedCallback);
        return payTipsForUnnamedFragment;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.a = findViewById("lg_pay_tips_for_unnamed_cancel");
        this.b = findViewById("lg_pay_tips_for_unnamed_close");
        this.c = findViewById("lg_pay_tips_for_unnamed_go");
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.pay.fragment.PayTipsForUnnamedFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PayTipsForUnnamedFragment.this.a();
                a.j();
            }
        });
        this.a.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.pay.fragment.PayTipsForUnnamedFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PayTipsForUnnamedFragment.this.a();
                a.h();
            }
        });
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.pay.fragment.PayTipsForUnnamedFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PayTipsForUnnamedFragment.this.b();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (getCallback() != null) {
            getCallback().onCancel();
        }
        close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        a.i();
        navigation(RealNameManager.createRealNameFragment(109, true, new LGRealNameCallback() { // from class: com.ss.union.game.sdk.pay.fragment.PayTipsForUnnamedFragment.4
            @Override // com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback
            public void onSuccess(boolean z, boolean z2, LGRealNameRewardInfo lGRealNameRewardInfo) {
                if (PayTipsForUnnamedFragment.this.getCallback() != null) {
                    ((PayTipsForUnnamedCallback) PayTipsForUnnamedFragment.this.getCallback()).onSuccess(z, z2);
                }
            }

            @Override // com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback
            public void onFail(int i, String str) {
                if (PayTipsForUnnamedFragment.this.getCallback() != null) {
                    ((PayTipsForUnnamedCallback) PayTipsForUnnamedFragment.this.getCallback()).onCancel();
                }
            }
        }));
        a.f();
    }
}
