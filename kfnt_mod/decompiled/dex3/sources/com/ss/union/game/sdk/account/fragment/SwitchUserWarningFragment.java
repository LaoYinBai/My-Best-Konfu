package com.ss.union.game.sdk.account.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ss.union.game.sdk.account.b;
import com.ss.union.game.sdk.account.fragment.oneKey.OneKeySwitchFragment;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.core.base.event.PageStater;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SwitchUserWarningFragment extends BaseFragment {
    private Activity a;
    private ImageView b;
    private TextView c;
    private TextView d;
    private a e;

    public interface a {
        void a(int i, String str, int i2);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_tt_ss_switch_visitor_warning";
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

    public static void a(a aVar) {
        SwitchUserWarningFragment switchUserWarningFragment = new SwitchUserWarningFragment();
        switchUserWarningFragment.b(aVar);
        new OperationBuilder(switchUserWarningFragment).show();
    }

    public void b(a aVar) {
        this.e = aVar;
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) throws JSONException {
        super.onCreate(bundle);
        PageStater.V1.onEvent(com.ss.union.game.sdk.account.a.a.b, "show_guest_switch_window");
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.b = (ImageView) findViewById("btn_close");
        this.c = (TextView) findViewById("btn_switch");
        this.d = (TextView) findViewById("btn_un_switch");
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment, android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.a = getActivity();
        if (this.a == null) {
        }
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.account.fragment.SwitchUserWarningFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                PageStater.V1.onEvent("click_button", "close_guest_switch_window");
                SwitchUserWarningFragment.this.a();
                SwitchUserWarningFragment.this.close();
            }
        });
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.account.fragment.SwitchUserWarningFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                PageStater.V1.onEvent("click_button", "click_guest_confirm_switch");
                OneKeySwitchFragment.a((BaseFragment) SwitchUserWarningFragment.this, true);
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.account.fragment.SwitchUserWarningFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                PageStater.V1.onEvent("click_button", "click_guest_cancel_switch");
                SwitchUserWarningFragment.this.a();
                SwitchUserWarningFragment.this.close();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.e != null) {
            this.e.a(-1004, b.b, 3);
        }
    }
}
