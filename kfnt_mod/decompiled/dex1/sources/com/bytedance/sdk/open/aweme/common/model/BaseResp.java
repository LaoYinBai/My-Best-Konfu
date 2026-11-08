package com.bytedance.sdk.open.aweme.common.model;

import android.os.Bundle;
import androidx.annotation.CallSuper;
import com.bytedance.sdk.open.aweme.common.constants.ParamKeyConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class BaseResp {
    public int errorCode;
    public String errorMsg;
    public Bundle extras;

    @CallSuper
    public boolean checkArgs() {
        return true;
    }

    public abstract int getType();

    public boolean isCancel() {
        return this.errorCode == -2;
    }

    public boolean isSuccess() {
        return this.errorCode == 0;
    }

    @CallSuper
    public void toBundle(Bundle bundle) {
        bundle.putInt(ParamKeyConstants.BaseParams.ERROR_CODE, this.errorCode);
        bundle.putString(ParamKeyConstants.BaseParams.ERROR_MSG, this.errorMsg);
        bundle.putInt(ParamKeyConstants.BaseParams.TYPE, getType());
        bundle.putBundle(ParamKeyConstants.BaseParams.EXTRA, this.extras);
    }

    @CallSuper
    public void fromBundle(Bundle bundle) {
        this.errorCode = bundle.getInt(ParamKeyConstants.BaseParams.ERROR_CODE);
        this.errorMsg = bundle.getString(ParamKeyConstants.BaseParams.ERROR_MSG);
        this.extras = bundle.getBundle(ParamKeyConstants.BaseParams.EXTRA);
    }
}
