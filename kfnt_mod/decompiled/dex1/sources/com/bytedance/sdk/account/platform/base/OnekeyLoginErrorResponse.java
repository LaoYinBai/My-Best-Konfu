package com.bytedance.sdk.account.platform.base;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class OnekeyLoginErrorResponse extends AuthorizeErrorResponse {
    public int error;
    public String errorMsg;
    public int errorType;
    public long mCancelApplyTime;
    public String mCancelAvatarUrl;
    public String mCancelNickName;
    public long mCancelTime;
    public String mCancelToken;
    public String mConfirmSwitchBindTips;
    public String mConfirmSwitchBindUrl;
    public int mDetailErrorCode;
    public String mDetailErrorMsg;
    public JSONObject rawResult;
    public String resultCode;
    public String smsCodeKey;
    public String netType = "";
    public int netStatus = -1;
}
