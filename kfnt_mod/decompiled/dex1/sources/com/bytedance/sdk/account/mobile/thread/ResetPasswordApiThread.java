package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.mobile.query.ResetPasswordQueryObj;
import com.bytedance.sdk.account.mobile.thread.call.ResetPasswordCallback;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.utility.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ResetPasswordApiThread extends BaseAccountApi<MobileApiResponse<ResetPasswordQueryObj>> {
    private ResetPasswordQueryObj queryObj;

    private ResetPasswordApiThread(Context context, ApiRequest apiRequest, ResetPasswordQueryObj resetPasswordQueryObj, ResetPasswordCallback resetPasswordCallback) {
        super(context, apiRequest, resetPasswordCallback);
        this.queryObj = resetPasswordQueryObj;
    }

    public static ResetPasswordApiThread resetPassword(Context context, String str, String str2, String str3, String str4, ResetPasswordCallback resetPasswordCallback) {
        ResetPasswordQueryObj resetPasswordQueryObj = new ResetPasswordQueryObj(str, str2, str3, str4);
        return new ResetPasswordApiThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getUserResetPassword()).parameters(getParams(resetPasswordQueryObj)).post(), resetPasswordQueryObj, resetPasswordCallback);
    }

    protected static Map<String, String> getParams(ResetPasswordQueryObj resetPasswordQueryObj) {
        HashMap map = new HashMap();
        map.put("mobile", StringUtils.encryptWithXor(resetPasswordQueryObj.mMobile));
        if (!TextUtils.isEmpty(resetPasswordQueryObj.mCaptcha)) {
            map.put("captcha", resetPasswordQueryObj.mCaptcha);
        }
        map.put("code", StringUtils.encryptWithXor(resetPasswordQueryObj.mCode));
        map.put("password", StringUtils.encryptWithXor(resetPasswordQueryObj.mPassword));
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public MobileApiResponse<ResetPasswordQueryObj> transformResponse(boolean z, ApiResponse apiResponse) {
        return new MobileApiResponse<>(z, 1003, this.queryObj);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        ApiHelper.mobileError(this.queryObj, jSONObject);
        this.queryObj.jsonResult = jSONObject2;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.queryObj.mUserInfo = ApiHelper.UserApiHelper.parseUser(jSONObject, jSONObject2);
        this.queryObj.jsonResult = jSONObject;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(MobileApiResponse<ResetPasswordQueryObj> mobileApiResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventMobile.RESET_PASSWORD, "mobile", null, mobileApiResponse, this.mApiCall);
    }
}
