package com.bytedance.sdk.account.api.response;

import com.bytedance.sdk.account.api.call.BaseApiResponse;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CheckEnvResponse extends BaseApiResponse {
    public String mErrorCaptcha;
    public JSONObject rawData;
    public String safe;

    public CheckEnvResponse(boolean z, int i) {
        super(z, i);
    }
}
