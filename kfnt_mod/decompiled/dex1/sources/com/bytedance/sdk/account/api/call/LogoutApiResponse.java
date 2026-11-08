package com.bytedance.sdk.account.api.call;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class LogoutApiResponse extends BaseApiResponse {
    public String mLogoutScene;
    private String mSessionKey;

    public LogoutApiResponse(boolean z, int i) {
        super(z, i);
    }

    public String getSessionKey() {
        return this.mSessionKey;
    }

    public void setSessionKey(String str) {
        this.mSessionKey = str;
    }
}
