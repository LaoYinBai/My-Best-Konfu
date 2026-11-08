package com.bytedance.sdk.account.api.call;

import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class LogoutOthersApiResponse extends BaseApiResponse {
    private List<String> mLogoutUserIds;

    public LogoutOthersApiResponse(boolean z, int i) {
        super(z, i);
    }

    public List<String> getLogoutUserIds() {
        return this.mLogoutUserIds;
    }

    public void setLogoutUserIds(List<String> list) {
        this.mLogoutUserIds = list;
    }
}
