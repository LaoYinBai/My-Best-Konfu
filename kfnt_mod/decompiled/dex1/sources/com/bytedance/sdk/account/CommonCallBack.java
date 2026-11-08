package com.bytedance.sdk.account;

import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.ss.android.account.TTAccountInit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class CommonCallBack<T extends BaseApiResponse> extends AbsApiCall<T> {
    public String getCaptchaInfo(T t) {
        return null;
    }

    public boolean needShowCaptcha(T t) {
        return false;
    }

    public boolean needShowPicCaptcha(T t) {
        return false;
    }

    public boolean needShowSecureCaptcha(T t) {
        return false;
    }

    public abstract void onError(T t, int i);

    public void onNeedCaptcha(T t, String str) {
    }

    public void onNeedSecureCaptcha(T t) {
    }

    public abstract void onSuccess(T t);

    @Override // com.bytedance.sdk.account.api.call.AbsApiCall
    public void onResponse(T t) {
        if (t.success) {
            onSuccess(t);
            return;
        }
        if (needShowCaptcha(t)) {
            if (needShowPicCaptcha(t)) {
                onNeedCaptcha(t, getCaptchaInfo(t));
                return;
            } else if (TTAccountInit.getConfig().isSecureCaptchaEnabled() && needShowSecureCaptcha(t)) {
                onNeedSecureCaptcha(t);
                return;
            } else {
                onError(t, t.error);
                return;
            }
        }
        onError(t, t.error);
    }
}
