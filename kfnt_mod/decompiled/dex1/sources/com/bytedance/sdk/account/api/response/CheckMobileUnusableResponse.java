package com.bytedance.sdk.account.api.response;

import com.bytedance.sdk.account.api.call.BaseApiResponse;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CheckMobileUnusableResponse extends BaseApiResponse {
    private boolean mIsUnusable;
    private boolean mIsVerified;
    private boolean mMnoSupport;
    private String mTicket;

    public CheckMobileUnusableResponse(boolean z, int i) {
        super(z, i);
    }

    public boolean isUnusable() {
        return this.mIsUnusable;
    }

    public void setUnusable(boolean z) {
        this.mIsUnusable = z;
    }

    public boolean isVerified() {
        return this.mIsVerified;
    }

    public void setVerified(boolean z) {
        this.mIsVerified = z;
    }

    public boolean isMnoSupport() {
        return this.mMnoSupport;
    }

    public void setMnoSupport(boolean z) {
        this.mMnoSupport = z;
    }

    public String getTicket() {
        return this.mTicket;
    }

    public void setTicket(String str) {
        this.mTicket = str;
    }
}
