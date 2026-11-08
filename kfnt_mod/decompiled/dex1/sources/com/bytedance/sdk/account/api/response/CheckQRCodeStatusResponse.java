package com.bytedance.sdk.account.api.response;

import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.mobile.query.IUserQueryObj;
import com.bytedance.sdk.account.user.IBDAccountUserEntity;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CheckQRCodeStatusResponse extends BaseApiResponse implements IUserQueryObj {
    public String appName;
    public IBDAccountUserEntity mUserInfo;
    public String qrCode;
    public String qrCodeUrl;
    public String redirectUrl;
    public String status;
    public String token;
    public String webName;

    public CheckQRCodeStatusResponse(boolean z, int i) {
        super(z, i);
    }

    @Override // com.bytedance.sdk.account.mobile.query.IUserQueryObj
    public IBDAccountUserEntity getUserInfo() {
        return this.mUserInfo;
    }
}
