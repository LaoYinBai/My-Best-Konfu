package com.bytedance.sdk.account.job;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.callback.TicketResetPasswordCallback;
import com.bytedance.sdk.account.api.response.TicketResetPasswordResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.sso.ApiObj;
import com.bytedance.sdk.account.user.IBDAccountUserEntity;
import com.bytedance.sdk.account.utility.StringUtils;
import com.bytedance.sdk.account.utils.Utils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TicketResetPasswordJob extends BaseAccountApi<TicketResetPasswordResponse> {
    private String eventType;
    private ApiObj mQueryObj;
    private JSONObject rawResponse;
    IBDAccountUserEntity userEntity;

    private TicketResetPasswordJob(Context context, ApiRequest apiRequest, String str, TicketResetPasswordCallback ticketResetPasswordCallback) {
        super(context, apiRequest, ticketResetPasswordCallback);
        this.eventType = "";
        this.eventType = str;
        this.mQueryObj = new ApiObj();
    }

    public static TicketResetPasswordJob resetPassword(Context context, String str, String str2, TicketResetPasswordCallback ticketResetPasswordCallback) {
        return new TicketResetPasswordJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getTicketResetPassowrd()).parameters(getParams(str, str2)).post(), "mobile", ticketResetPasswordCallback);
    }

    public static TicketResetPasswordJob resetEmailPassword(Context context, String str, String str2, Map map, String str3, TicketResetPasswordCallback ticketResetPasswordCallback) {
        return new TicketResetPasswordJob(context, new ApiRequest.Builder().url(Utils.getUrl(BDAccountNetApi.Account.getEmailTicketResetPassword(), str3)).parameters(getEmailParams(str, str2), map).post(), "email", ticketResetPasswordCallback);
    }

    protected static Map<String, String> getParams(String str, String str2) {
        HashMap map = new HashMap();
        map.put("password", StringUtils.encryptWithXor(str));
        map.put("ticket", StringUtils.encryptWithXor(str2));
        map.put("mix_mode", "1");
        return map;
    }

    protected static Map<String, String> getEmailParams(String str, String str2) {
        HashMap map = new HashMap();
        map.put("password", StringUtils.encryptWithXor(str));
        map.put("ticket", str2);
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public TicketResetPasswordResponse transformResponse(boolean z, ApiResponse apiResponse) {
        TicketResetPasswordResponse ticketResetPasswordResponse = new TicketResetPasswordResponse(z, 1018);
        if (z) {
            ticketResetPasswordResponse.userInfo = this.userEntity;
        } else {
            ticketResetPasswordResponse.error = apiResponse.mError;
            ticketResetPasswordResponse.errorMsg = apiResponse.mErrorMsg;
            if (this.mQueryObj.mError == 1075) {
                ticketResetPasswordResponse.mCancelApplyTime = this.mQueryObj.mCancelApplyTime;
                ticketResetPasswordResponse.mCancelAvatarUrl = this.mQueryObj.mCancelAvatarUrl;
                ticketResetPasswordResponse.mCancelNickName = this.mQueryObj.mCancelNickName;
                ticketResetPasswordResponse.mCancelTime = this.mQueryObj.mCancelTime;
                ticketResetPasswordResponse.mCancelToken = this.mQueryObj.mCancelToken;
            }
        }
        ticketResetPasswordResponse.result = this.rawResponse;
        return ticketResetPasswordResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        this.rawResponse = jSONObject2;
        ApiHelper.apiError(this.mQueryObj, jSONObject, jSONObject2);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.userEntity = ApiHelper.UserApiHelper.parseUser(jSONObject, jSONObject2);
        this.rawResponse = jSONObject;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(TicketResetPasswordResponse ticketResetPasswordResponse) throws JSONException {
        if (TextUtils.isEmpty(this.eventType)) {
            return;
        }
        if (this.eventType.equals("mobile")) {
            AccountMonitorUtil.onEvent(AccountMonitorConstants.EventMobile.RESET_PASSWORD, "mobile", "ticket", ticketResetPasswordResponse, this.mApiCall);
        } else if (this.eventType.equals("email")) {
            AccountMonitorUtil.onEvent(AccountMonitorConstants.EventMobile.EMAIL_RESET_PASSWORD, "email", "ticket", ticketResetPasswordResponse, this.mApiCall);
        }
    }
}
