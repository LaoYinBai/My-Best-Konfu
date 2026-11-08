package com.bytedance.sdk.account;

import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.mobile.query.MobileQueryObj;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class NeedCaptchaCallBack<T extends MobileApiResponse<K>, K extends MobileQueryObj> extends CommonCallBack<T> {
    @Override // com.bytedance.sdk.account.CommonCallBack
    public abstract void onError(T t, int i);

    @Override // com.bytedance.sdk.account.CommonCallBack
    public abstract void onSuccess(T t);

    @Override // com.bytedance.sdk.account.CommonCallBack
    public final boolean needShowCaptcha(T t) {
        if (t == null || t.mobileObj == 0) {
            return false;
        }
        return t.mobileObj.isNeedShowCaptcha();
    }

    @Override // com.bytedance.sdk.account.CommonCallBack
    public final boolean needShowPicCaptcha(T t) {
        if (t == null || t.mobileObj == 0) {
            return false;
        }
        return t.mobileObj.isNeedShowPicCaptcha();
    }

    @Override // com.bytedance.sdk.account.CommonCallBack
    public final boolean needShowSecureCaptcha(T t) {
        if (t == null || t.mobileObj == 0) {
            return false;
        }
        return t.mobileObj.isNeedShowSecureCaptcha();
    }

    @Override // com.bytedance.sdk.account.CommonCallBack
    public final String getCaptchaInfo(T t) {
        if (t == null || t.mobileObj == 0) {
            return null;
        }
        return t.mobileObj.mErrorCaptcha;
    }
}
