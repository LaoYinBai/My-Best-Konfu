package com.bytedance.sdk.account.api.response;

import com.bytedance.sdk.account.api.call.BaseApiResponse;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AuthTicketResponse extends BaseApiResponse {
    public String mobile;
    public JSONObject rawData;
    public String ticket;
    public int ticket_age;

    public AuthTicketResponse(boolean z, int i) {
        super(z, i);
    }
}
