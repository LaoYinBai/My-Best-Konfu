package com.ss.union.game.sdk.core.base.debug.sdkVersion;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.dialog.OperationBuilder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SdkVersionFragment extends BaseFragment {
    View a;
    TextView b;

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected String getLayoutId() {
        return "lg_fragment_sdk_version";
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected boolean initArgument(Bundle bundle) {
        return true;
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void loadData() {
    }

    public static void a() {
        new OperationBuilder(new SdkVersionFragment()).show();
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initView() {
        this.a = findViewById("id_lg_fragment_sdk_version_close");
        this.b = (TextView) findViewById("id_lg_fragment_sdk_version_content");
        this.b.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initData() {
        this.b.setText("LightGameSDK：2.3.3.3\n穿山甲M：3.1.0.1\n穿山甲：4.0.2.1\n深度转化：6.3.0\n安全GPSDK：1.6.1\n安全CPSDK：0.0.7\n游戏保护CGSDK：1.0.0\nPush SDK：3.2.0-honor\nAPM SDK：1.4.0\n信通院 SDK：1.0.13\n手机一键登录 SDK：0.0.1.4\n抖音登录 SDK：0.0.1.3\n滑块验证 SDK：1.1.1-alpha.0");
    }

    @Override // com.ss.union.game.sdk.common.dialog.BaseFragment
    protected void initListener() {
        this.a.setOnClickListener(new View.OnClickListener() { // from class: com.ss.union.game.sdk.core.base.debug.sdkVersion.SdkVersionFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SdkVersionFragment.this.back();
            }
        });
    }
}
