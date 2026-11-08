package com.bytedance.sdk.account.api.call;

import com.bytedance.sdk.account.sso.BindConflictUser;
import com.bytedance.sdk.account.user.IBDAccountUserEntity;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class UserApiResponse extends BaseApiResponse {
    public String authToken;
    public String confirmTip;
    public String errorTip;
    public long mCancelApplyTime;
    public String mCancelAvatarUrl;
    public String mCancelNickName;
    public long mCancelTime;
    public String mCancelToken;
    public BindConflictUser mConflictUser;
    private String mProfileKey;
    public String mSharkTicket;
    public IBDAccountUserEntity userInfo;

    public UserApiResponse(boolean z, int i) {
        super(z, i);
    }

    public String getProfileKey() {
        return this.mProfileKey;
    }

    public void setProfileKey(String str) {
        this.mProfileKey = str;
    }
}
