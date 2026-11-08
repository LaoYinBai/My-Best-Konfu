package com.bytedance.sdk.open.aweme.common.impl;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bytedance.sdk.open.aweme.base.BaseCheckHelperImpl;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MusicallyCheckHelperImpl extends BaseCheckHelperImpl {
    @Override // com.bytedance.sdk.open.aweme.base.BaseCheckHelperImpl
    protected int getAuthRequestApi() {
        return 1;
    }

    @Override // com.bytedance.sdk.open.aweme.base.IAPPCheckHelper
    @NonNull
    public String getPackageName() {
        return "com.zhiliaoapp.musically";
    }

    @Override // com.bytedance.sdk.open.aweme.base.BaseCheckHelperImpl
    @NonNull
    public String getSignature() {
        return "194326e82c84a639a52e5c023116f12a";
    }

    public MusicallyCheckHelperImpl(Context context) {
        super(context);
    }
}
