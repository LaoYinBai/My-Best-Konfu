package com.huawei.hms.support.api.entity.auth;

import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class AuthInfoResp extends AbstractResp {

    @Packed
    private AuthorizationInfo authInfo;

    @Override // com.huawei.hms.support.api.entity.auth.AbstractResp
    public int getRtnCode() {
        return super.getRtnCode();
    }

    public AuthorizationInfo getAuthInfo() {
        return this.authInfo;
    }

    public void setAuthInfo(AuthorizationInfo authorizationInfo) {
        this.authInfo = authorizationInfo;
    }
}
