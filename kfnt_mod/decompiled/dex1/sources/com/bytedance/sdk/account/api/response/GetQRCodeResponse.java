package com.bytedance.sdk.account.api.response;

import com.bytedance.sdk.account.api.call.BaseApiResponse;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GetQRCodeResponse extends BaseApiResponse {
    public String appName;
    public String qrCode;
    public String qrCodeUrl;
    public String token;
    public String webName;

    public GetQRCodeResponse(boolean z, int i) {
        super(z, i);
    }
}
