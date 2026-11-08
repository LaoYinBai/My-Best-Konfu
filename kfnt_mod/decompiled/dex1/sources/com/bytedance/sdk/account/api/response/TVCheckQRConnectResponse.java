package com.bytedance.sdk.account.api.response;

import com.bytedance.sdk.account.api.call.UserApiResponse;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TVCheckQRConnectResponse extends UserApiResponse {
    public String qrcode;
    public JSONObject rawData;
    public String status;
    public String token;

    public TVCheckQRConnectResponse(boolean z, int i) {
        super(z, i);
    }
}
