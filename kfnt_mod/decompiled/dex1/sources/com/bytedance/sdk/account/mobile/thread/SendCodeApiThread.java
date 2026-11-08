package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.b.a.a.a;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.mobile.query.SendCodeQueryObj;
import com.bytedance.sdk.account.mobile.thread.call.SendCodeCallback;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import com.bytedance.sdk.account.utility.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SendCodeApiThread extends BaseAccountApi<MobileApiResponse<SendCodeQueryObj>> {
    private SendCodeQueryObj queryObj;

    private SendCodeApiThread(Context context, ApiRequest apiRequest, SendCodeQueryObj sendCodeQueryObj, SendCodeCallback sendCodeCallback) {
        super(context, apiRequest, sendCodeCallback);
        this.queryObj = sendCodeQueryObj;
    }

    public static SendCodeApiThread sendCode(Context context, String str, String str2, int i, SendCodeCallback sendCodeCallback) {
        return sendCode(context, str, str2, i, 0, -1, sendCodeCallback);
    }

    public static SendCodeApiThread sendCode(Context context, String str, String str2, int i, int i2, int i3, SendCodeCallback sendCodeCallback) {
        SendCodeQueryObj sendCodeQueryObj = new SendCodeQueryObj(str, str2, i, i2, i3);
        return new SendCodeApiThread(context, sendCodeRequest(sendCodeQueryObj).url(BDAccountNetApi.Account.getUserSendCode()).post(), sendCodeQueryObj, sendCodeCallback);
    }

    public static SendCodeApiThread sendCode(Context context, String str, String str2, String str3, int i, SendCodeCallback sendCodeCallback) {
        SendCodeQueryObj sendCodeQueryObj = new SendCodeQueryObj(str, str2, str3, i);
        return new SendCodeApiThread(context, sendCodeRequest(sendCodeQueryObj).url(BDAccountNetApi.Account.getUserSendCode()).post(), sendCodeQueryObj, sendCodeCallback);
    }

    public static SendCodeApiThread sendCode(Context context, String str, String str2, int i, int i2, String str3, int i3, int i4, SendCodeCallback sendCodeCallback) {
        SendCodeQueryObj sendCodeQueryObj = new SendCodeQueryObj(str, str2, i, i2, str3, i3, i4);
        return new SendCodeApiThread(context, sendCodeRequest(sendCodeQueryObj).url(BDAccountNetApi.Account.getUserSendCode()).post(), sendCodeQueryObj, sendCodeCallback);
    }

    public static SendCodeApiThread sendCode(Context context, String str, String str2, int i, int i2, String str3, int i3, int i4, String str4, String str5, SendCodeCallback sendCodeCallback) {
        SendCodeQueryObj sendCodeQueryObj = new SendCodeQueryObj(str, str2, i, i2, str3, i3, i4, str4, str5);
        return new SendCodeApiThread(context, sendCodeRequest(sendCodeQueryObj).url(BDAccountNetApi.Account.getUserSendCode()).post(), sendCodeQueryObj, sendCodeCallback);
    }

    public static SendCodeApiThread sendCode(Context context, String str, String str2, int i, int i2, String str3, int i3, int i4, String str4, String str5, Map map, SendCodeCallback sendCodeCallback) {
        SendCodeQueryObj sendCodeQueryObj = new SendCodeQueryObj(str, str2, i, i2, str3, i3, i4, str4, str5, map);
        return new SendCodeApiThread(context, sendCodeRequest(sendCodeQueryObj).url(BDAccountNetApi.Account.getUserSendCode()).post(), sendCodeQueryObj, sendCodeCallback);
    }

    public static SendCodeApiThread sendCode(Context context, int i, String str, String str2, int i2, int i3, String str3, String str4, String str5, SendCodeCallback sendCodeCallback) {
        SendCodeQueryObj sendCodeQueryObj = new SendCodeQueryObj(i, str, str2, i2, i3, str3, str4, str5);
        return new SendCodeApiThread(context, sendCodeRequest(sendCodeQueryObj).url(BDAccountNetApi.Account.getUserSendCode()).post(), sendCodeQueryObj, sendCodeCallback);
    }

    private static ApiRequest.Builder sendCodeRequest(SendCodeQueryObj sendCodeQueryObj) {
        return new ApiRequest.Builder().parameters(getParams(sendCodeQueryObj), sendCodeQueryObj.mExtendInfo);
    }

    protected static Map<String, String> getParams(SendCodeQueryObj sendCodeQueryObj) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(sendCodeQueryObj.mMobile)) {
            map.put("mobile", StringUtils.encryptWithXor(sendCodeQueryObj.mMobile));
        }
        if (!TextUtils.isEmpty(sendCodeQueryObj.mOldMobile)) {
            map.put("old_mobile", StringUtils.encryptWithXor(sendCodeQueryObj.mOldMobile));
        }
        if (!TextUtils.isEmpty(sendCodeQueryObj.mCaptcha)) {
            map.put("captcha", sendCodeQueryObj.mCaptcha);
        }
        map.put("type", StringUtils.encryptWithXor(String.valueOf(sendCodeQueryObj.mType)));
        map.put("unbind_exist", StringUtils.encryptWithXor(String.valueOf(sendCodeQueryObj.mUnbindExist)));
        map.put("mix_mode", "1");
        if (sendCodeQueryObj.checkRegister == 1) {
            map.put("check_register", "1");
        } else if (sendCodeQueryObj.checkRegister == 0) {
            map.put("check_register", OnekeyLoginConstants.CU_RESULT_SUCCESS);
        }
        if (!TextUtils.isEmpty(sendCodeQueryObj.mTicket)) {
            map.put("ticket", sendCodeQueryObj.mTicket);
        }
        map.put("auto_read", String.valueOf(sendCodeQueryObj.mAutoread));
        if (!TextUtils.isEmpty(sendCodeQueryObj.mSharkTicket)) {
            map.put("shark_ticket", sendCodeQueryObj.mSharkTicket);
        }
        if (!TextUtils.isEmpty(sendCodeQueryObj.mAuthToken)) {
            map.put("auth_token", sendCodeQueryObj.mAuthToken);
        }
        if (!TextUtils.isEmpty(sendCodeQueryObj.mUnusableMobileTicket)) {
            map.put("unusable_mobile_ticket", sendCodeQueryObj.mUnusableMobileTicket);
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public MobileApiResponse<SendCodeQueryObj> transformResponse(boolean z, ApiResponse apiResponse) {
        return new MobileApiResponse<>(z, 1002, this.queryObj);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        ApiHelper.mobileError(this.queryObj, jSONObject);
        this.queryObj.jsonResult = jSONObject2;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.queryObj.mResendTime = jSONObject2.optInt(a.q, 30);
        this.queryObj.jsonResult = jSONObject;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(MobileApiResponse<SendCodeQueryObj> mobileApiResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventMobile.SEND_CODE, "mobile", this.apiRequest.parameter("type"), mobileApiResponse, this.mApiCall);
    }
}
