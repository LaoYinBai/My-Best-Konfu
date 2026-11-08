package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.mobile.query.OneBindMobileQueryObj;
import com.bytedance.sdk.account.mobile.thread.call.OneBindMobileCallback;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.utility.StringUtils;
import com.bytedance.sdk.open.aweme.common.constants.ParamKeyConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class OneBindMobileApiThread extends BaseAccountApi<MobileApiResponse<OneBindMobileQueryObj>> {
    private OneBindMobileQueryObj queryObj;

    private OneBindMobileApiThread(Context context, ApiRequest apiRequest, OneBindMobileQueryObj oneBindMobileQueryObj, OneBindMobileCallback oneBindMobileCallback) {
        super(context, apiRequest, oneBindMobileCallback);
        this.queryObj = oneBindMobileQueryObj;
    }

    public static OneBindMobileApiThread oneBindMobile(Context context, String str, String str2, String str3, int i, Map map, OneBindMobileCallback oneBindMobileCallback) {
        OneBindMobileQueryObj oneBindMobileQueryObj = new OneBindMobileQueryObj(str, str2, str3, i);
        return new OneBindMobileApiThread(context, new ApiRequest.Builder().url(TextUtils.isEmpty(str3) ? BDAccountNetApi.Account.getOneBindMobileV1() : BDAccountNetApi.Account.getOneBindMobileV2()).parameters(getParams(oneBindMobileQueryObj), map).post(), oneBindMobileQueryObj, oneBindMobileCallback);
    }

    protected static Map<String, String> getParams(OneBindMobileQueryObj oneBindMobileQueryObj) {
        HashMap map = new HashMap();
        map.put("token", StringUtils.encryptWithXor(oneBindMobileQueryObj.mToken));
        map.put(ParamKeyConstants.WebViewConstants.QUERY_FROM, StringUtils.encryptWithXor(oneBindMobileQueryObj.mFrom));
        if (!TextUtils.isEmpty(oneBindMobileQueryObj.mPassword)) {
            map.put("password", StringUtils.encryptWithXor(oneBindMobileQueryObj.mPassword));
        }
        map.put("unbind_exist", StringUtils.encryptWithXor(String.valueOf(oneBindMobileQueryObj.mUnbindExist)));
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public MobileApiResponse<OneBindMobileQueryObj> transformResponse(boolean z, ApiResponse apiResponse) {
        return new MobileApiResponse<>(z, 1027, this.queryObj);
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
    public void onSendEvent(MobileApiResponse<OneBindMobileQueryObj> mobileApiResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventMobile.ONE_BIND_MOBILE, "mobile", null, mobileApiResponse, this.mApiCall);
    }
}
