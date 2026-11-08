package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.mobile.query.QuickLoginContinueQueryObj;
import com.bytedance.sdk.account.mobile.thread.call.QuickLoginContinueCallback;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.utility.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class QuickLoginContinueApiThread extends BaseAccountApi<MobileApiResponse<QuickLoginContinueQueryObj>> {
    private QuickLoginContinueQueryObj queryObj;

    private QuickLoginContinueApiThread(Context context, ApiRequest apiRequest, QuickLoginContinueQueryObj quickLoginContinueQueryObj, QuickLoginContinueCallback quickLoginContinueCallback) {
        super(context, apiRequest, quickLoginContinueCallback);
        this.queryObj = quickLoginContinueQueryObj;
    }

    public static QuickLoginContinueApiThread quickLoginContinue(Context context, String str, String str2, QuickLoginContinueCallback quickLoginContinueCallback) {
        QuickLoginContinueQueryObj quickLoginContinueQueryObj = new QuickLoginContinueQueryObj(str, str2);
        return new QuickLoginContinueApiThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getUserQuickContinueLogin()).parameters(getParams(quickLoginContinueQueryObj)).post(), quickLoginContinueQueryObj, quickLoginContinueCallback);
    }

    protected static Map<String, String> getParams(QuickLoginContinueQueryObj quickLoginContinueQueryObj) {
        HashMap map = new HashMap();
        map.put("mobile", StringUtils.encryptWithXor(quickLoginContinueQueryObj.mMobile));
        map.put("sms_code_key", StringUtils.encryptWithXor(String.valueOf(quickLoginContinueQueryObj.mSmsCodekey)));
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public MobileApiResponse<QuickLoginContinueQueryObj> transformResponse(boolean z, ApiResponse apiResponse) {
        return new MobileApiResponse<>(z, 1022, this.queryObj);
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
    public void onSendEvent(MobileApiResponse<QuickLoginContinueQueryObj> mobileApiResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventMobile.LOGIN_CONTINUE, "mobile", "login_continue", mobileApiResponse, this.mApiCall);
    }
}
