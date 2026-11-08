package com.bytedance.sdk.account.api.call;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CheckMobileRegisterResponse extends BaseApiResponse {
    public boolean isRegistered;

    public CheckMobileRegisterResponse(boolean z, int i) {
        super(z, i);
    }

    public boolean isRegistered() {
        return this.isRegistered;
    }
}
