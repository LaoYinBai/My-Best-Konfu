package com.bytedance.sdk.open.aweme.common.model;

import android.os.Bundle;
import androidx.annotation.CallSuper;
import com.bytedance.sdk.open.aweme.common.constants.ParamKeyConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class BaseReq {
    public String callerLocalEntry;
    public String callerPackage;
    public String callerVersion;
    public Bundle extras;

    @CallSuper
    public boolean checkArgs() {
        return true;
    }

    public abstract int getType();

    public String getCallerLocalEntry() {
        return this.callerLocalEntry;
    }

    public String getCallerPackage() {
        return this.callerPackage;
    }

    public String getCallerVersion() {
        return this.callerVersion;
    }

    @CallSuper
    public void toBundle(Bundle bundle) {
        bundle.putInt(ParamKeyConstants.BaseParams.TYPE, getType());
        bundle.putBundle(ParamKeyConstants.BaseParams.EXTRA, this.extras);
        bundle.putString(ParamKeyConstants.BaseParams.FROM_ENTRY, this.callerLocalEntry);
    }

    @CallSuper
    public void fromBundle(Bundle bundle) {
        this.callerPackage = bundle.getString(ParamKeyConstants.BaseParams.CALLER_PKG);
        this.callerVersion = bundle.getString(ParamKeyConstants.BaseParams.CALLER_BASE_OPEN_VERSION);
        this.extras = bundle.getBundle(ParamKeyConstants.BaseParams.EXTRA);
        this.callerLocalEntry = bundle.getString(ParamKeyConstants.BaseParams.FROM_ENTRY);
    }
}
