package com.bytedance.sdk.account.api.response;

import com.bytedance.sdk.account.api.call.BaseApiResponse;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GenerateUserInfoTicketResponse extends BaseApiResponse {
    private String ticket;

    public GenerateUserInfoTicketResponse(boolean z, int i) {
        super(z, i);
    }

    public String getTicket() {
        return this.ticket;
    }

    public void setTicket(String str) {
        this.ticket = str;
    }
}
