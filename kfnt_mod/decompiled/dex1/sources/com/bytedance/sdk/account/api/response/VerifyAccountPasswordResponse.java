package com.bytedance.sdk.account.api.response;

import com.bytedance.sdk.account.api.call.BaseApiResponse;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class VerifyAccountPasswordResponse extends BaseApiResponse {
    private String captcha;
    private String ticket;

    public VerifyAccountPasswordResponse(boolean z, int i) {
        super(z, i);
    }

    public String getTicket() {
        return this.ticket;
    }

    public void setTicket(String str) {
        this.ticket = str;
    }

    public String getCaptcha() {
        return this.captcha;
    }

    public void setCaptcha(String str) {
        this.captcha = str;
    }
}
