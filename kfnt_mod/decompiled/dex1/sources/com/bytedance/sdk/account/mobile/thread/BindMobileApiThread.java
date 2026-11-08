package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.mobile.query.BindMobileQueryObj;
import com.bytedance.sdk.account.mobile.thread.call.BindMobileCallback;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.utility.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BindMobileApiThread extends BaseAccountApi<MobileApiResponse<BindMobileQueryObj>> {
    private BindMobileQueryObj queryObj;

    private BindMobileApiThread(Context context, ApiRequest apiRequest, BindMobileQueryObj bindMobileQueryObj, BindMobileCallback bindMobileCallback) {
        super(context, apiRequest, bindMobileCallback);
        this.queryObj = bindMobileQueryObj;
    }

    public static BindMobileApiThread bindMobile(Context context, String str, String str2, String str3, String str4, int i, BindMobileCallback bindMobileCallback) {
        BindMobileQueryObj bindMobileQueryObj = new BindMobileQueryObj(str, str2, str3, str4, i);
        return new BindMobileApiThread(context, new ApiRequest.Builder().url(TextUtils.isEmpty(str3) ? BDAccountNetApi.Account.getUserBindMobileV1() : BDAccountNetApi.Account.getUserBindMobileV2()).parameters(getParams(bindMobileQueryObj)).post(), bindMobileQueryObj, bindMobileCallback);
    }

    protected static Map<String, String> getParams(BindMobileQueryObj bindMobileQueryObj) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(bindMobileQueryObj.mCaptcha)) {
            map.put("captcha", bindMobileQueryObj.mCaptcha);
        }
        map.put("code", StringUtils.encryptWithXor(bindMobileQueryObj.mCode));
        map.put("mobile", StringUtils.encryptWithXor(bindMobileQueryObj.mMobile));
        map.put("password", StringUtils.encryptWithXor(bindMobileQueryObj.mPassword));
        map.put("unbind_exist", StringUtils.encryptWithXor(String.valueOf(bindMobileQueryObj.mUnbindExist)));
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public MobileApiResponse<BindMobileQueryObj> transformResponse(boolean z, ApiResponse apiResponse) {
        return new MobileApiResponse<>(z, 1013, this.queryObj);
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
    public void onSendEvent(MobileApiResponse<BindMobileQueryObj> mobileApiResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventMobile.BIND, "mobile", null, mobileApiResponse, this.mApiCall);
    }
}
