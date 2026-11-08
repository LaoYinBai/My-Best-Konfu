package com.bytedance.sdk.open.aweme.common.impl;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bytedance.sdk.open.aweme.base.BaseCheckHelperImpl;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AwemeCheckHelperImpl extends BaseCheckHelperImpl {
    @Override // com.bytedance.sdk.open.aweme.base.BaseCheckHelperImpl
    protected int getAuthRequestApi() {
        return 1;
    }

    @Override // com.bytedance.sdk.open.aweme.base.IAPPCheckHelper
    @NonNull
    public String getPackageName() {
        return "com.ss.android.ugc.aweme";
    }

    @Override // com.bytedance.sdk.open.aweme.base.BaseCheckHelperImpl
    @NonNull
    public String getSignature() {
        return "aea615ab910015038f73c47e45d21466";
    }

    public AwemeCheckHelperImpl(Context context) {
        super(context);
    }
}
