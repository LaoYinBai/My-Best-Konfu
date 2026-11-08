package com.ss.union.game.sdk.core.base.dialog.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.core.base.dialog.callback.a;
import com.ss.union.game.sdk.core.base.dialog.model.StandardDialogParams;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class StandardFragmentDialog extends BaseFragment implements View.OnClickListener {
    public static final String KEY_PARAMS = "key_params";
    private ImageView a;
    private ImageView b;
    private TextView c;
    private TextView d;
    private Button e;
    private Button f;
    private a g;
    private StandardDialogParams h;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_fragment_sdk_common_dialog";
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
    }

    public static void show(StandardDialogParams standardDialogParams, a aVar) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_PARAMS, standardDialogParams);
        StandardFragmentDialog standardFragmentDialogA = a(bundle);
        standardFragmentDialogA.setStandardDialogListener(aVar);
        new OperationBuilder(standardFragmentDialogA).show();
    }

    private static StandardFragmentDialog a(Bundle bundle) {
        StandardFragmentDialog standardFragmentDialog = new StandardFragmentDialog();
        standardFragmentDialog.setArguments(bundle);
        return standardFragmentDialog;
    }

    public void setStandardDialogListener(a aVar) {
        this.g = aVar;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        if (bundle == null) {
            return true;
        }
        this.h = (StandardDialogParams) getArguments().getSerializable(KEY_PARAMS);
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.a = (ImageView) findViewById("lg_sdk_common_dialog_iv_back");
        this.b = (ImageView) findViewById("lg_sdk_common_dialog_iv_close");
        this.c = (TextView) findViewById("lg_sdk_common_dialog_tv_title");
        this.d = (TextView) findViewById("lg_sdk_common_dialog_tv_content");
        this.e = (Button) findViewById("lg_sdk_common_weakness_btn");
        this.f = (Button) findViewById("lg_sdk_common_enhance_btn");
        if (this.g != null) {
            this.g.show(this);
        }
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
        if (this.h != null) {
            this.c.setText(this.h.getTitle());
            this.d.setText(this.h.getDescription());
            this.f.setText(this.h.getEnhanceBtnText());
            this.e.setText(this.h.getWeaknessBtnText());
            this.a.setVisibility(this.h.backBtnEnable() ? 0 : 8);
            this.b.setVisibility(this.h.closeBtnEnable() ? 0 : 8);
            this.e.setVisibility(this.h.weaknessBtnEnable() ? 0 : 8);
        }
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.e.setOnClickListener(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0030  */
    @Override // android.view.View.OnClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onClick(android.view.View r2) {
        /*
            r1 = this;
            com.ss.union.game.sdk.core.base.dialog.callback.a r0 = r1.g
            if (r0 == 0) goto L30
            android.widget.ImageView r0 = r1.a
            if (r2 != r0) goto Lf
            com.ss.union.game.sdk.core.base.dialog.callback.a r2 = r1.g
            boolean r2 = r2.onBackClick(r1)
            goto L31
        Lf:
            android.widget.ImageView r0 = r1.b
            if (r2 != r0) goto L1a
            com.ss.union.game.sdk.core.base.dialog.callback.a r2 = r1.g
            boolean r2 = r2.onCloseClick(r1)
            goto L31
        L1a:
            android.widget.Button r0 = r1.e
            if (r2 != r0) goto L25
            com.ss.union.game.sdk.core.base.dialog.callback.a r2 = r1.g
            boolean r2 = r2.onWeaknessBtnClick(r1)
            goto L31
        L25:
            android.widget.Button r0 = r1.f
            if (r2 != r0) goto L30
            com.ss.union.game.sdk.core.base.dialog.callback.a r2 = r1.g
            boolean r2 = r2.onEnhanceBtnClick(r1)
            goto L31
        L30:
            r2 = 0
        L31:
            if (r2 != 0) goto L36
            r1.back()
        L36:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.union.game.sdk.core.base.dialog.fragment.StandardFragmentDialog.onClick(android.view.View):void");
    }
}
