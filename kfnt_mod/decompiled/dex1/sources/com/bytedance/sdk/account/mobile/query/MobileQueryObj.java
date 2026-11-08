package com.bytedance.sdk.account.mobile.query;

import android.text.TextUtils;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class MobileQueryObj {
    public JSONObject jsonResult;
    public long mCancelApplyTime;
    public String mCancelAvatarUrl;
    public String mCancelNickName;
    public long mCancelTime;
    public String mCancelToken;
    public int mError;
    public String mErrorAlert;
    public String mErrorCaptcha;
    public String mErrorMsg;
    public int mScenario;

    public MobileQueryObj() {
    }

    public MobileQueryObj(int i) {
        this.mScenario = i;
    }

    public boolean isNeedShowCaptcha() {
        return this.mError > 1100 && this.mError < 1199;
    }

    public boolean isNeedShowPicCaptcha() {
        return (this.mError == 1101 || this.mError == 1102 || this.mError == 1103) && !TextUtils.isEmpty(this.mErrorCaptcha);
    }

    public boolean isNeedShowSecureCaptcha() {
        return this.mError == 1104 || this.mError == 1105;
    }
}
