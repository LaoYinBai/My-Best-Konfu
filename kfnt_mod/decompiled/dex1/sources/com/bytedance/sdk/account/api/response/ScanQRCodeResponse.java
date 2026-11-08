package com.bytedance.sdk.account.api.response;

import com.bytedance.sdk.account.api.call.BaseApiResponse;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ScanQRCodeResponse extends BaseApiResponse {
    public String csrfToken;
    public String desc;
    public String query;
    public String sourceIcon;
    public String title;

    public ScanQRCodeResponse(boolean z, int i) {
        super(z, i);
    }
}
