package com.ss.union.game.sdk.core.antiAddiction.fragment;

import android.os.Bundle;
import android.view.View;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;
import com.ss.union.game.sdk.core.antiAddiction.a.a;
import com.ss.union.game.sdk.core.antiAddiction.bean.LGAntiAddictionGlobalResult;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.realName.LGRealNameManagerImpl;
import com.ss.union.game.sdk.core.realName.event.RealNameLogEvent;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AntiAddictionFragment extends BaseFragment {
    public static final int KEY_TYPE_22_POINT_REAL_NAME_TEENAGER = 201;
    private View a;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_fragment_anti_addiction";
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean onBackPressed() {
        return true;
    }

    public static void show() {
        new OperationBuilder(new AntiAddictionFragment()).show();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.a = findViewById("confirm_btn");
        if (ConfigManager.LoginConfig.isNoUserLogin()) {
            return;
        }
        RealNameLogEvent.reportShow(RealNameLogEvent.REAL_NAME_EVENT_VALUE_MINOR_WNDOW);
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() throws JSONException {
        a.a();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.a.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.antiAddiction.fragment.AntiAddictionFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) throws JSONException {
                a.b();
                LGAntiAddictionGlobalResult lGAntiAddictionGlobalResult = new LGAntiAddictionGlobalResult();
                lGAntiAddictionGlobalResult.autoPopup = true;
                lGAntiAddictionGlobalResult.setErrno(-5001);
                LGRealNameManagerImpl.getInstance().globalAntiAddiction(lGAntiAddictionGlobalResult);
                AntiAddictionFragment.this.back();
            }
        });
    }
}
