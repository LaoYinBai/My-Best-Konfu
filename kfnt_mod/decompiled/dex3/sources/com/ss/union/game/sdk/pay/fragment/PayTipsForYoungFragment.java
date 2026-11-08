package com.ss.union.game.sdk.pay.fragment;

import android.os.Bundle;
import android.view.View;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.common.mvp.IPresenter;
import com.ss.union.game.sdk.pay.callback.PayTipsForYoungCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class PayTipsForYoungFragment extends BaseFragment<PayTipsForYoungCallback, IPresenter> {
    private View a;
    private View b;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_pay_failed_tips_for_young";
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

    public static void a(PayTipsForYoungCallback payTipsForYoungCallback) {
        new OperationBuilder(b(payTipsForYoungCallback)).cancelable(false).show();
    }

    private static PayTipsForYoungFragment b(PayTipsForYoungCallback payTipsForYoungCallback) {
        PayTipsForYoungFragment payTipsForYoungFragment = new PayTipsForYoungFragment();
        payTipsForYoungFragment.setCallback(payTipsForYoungCallback);
        return payTipsForYoungFragment;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.a = findViewById("lg_pay_failed_tips_for_young_close");
        this.b = findViewById("lg_pay_failed_tips_for_young_go");
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.a.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.pay.fragment.PayTipsForYoungFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PayTipsForYoungFragment.this.a();
            }
        });
        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.pay.fragment.PayTipsForYoungFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PayTipsForYoungFragment.this.a();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        back();
        if (getCallback() != null) {
            getCallback().onClose();
        }
    }
}
