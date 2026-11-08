package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.mobile.query.BindLoginObj;
import com.bytedance.sdk.account.mobile.thread.call.BindLoginCallback;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.utility.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BindLoginThread extends BaseAccountApi<MobileApiResponse<BindLoginObj>> {
    private BindLoginObj queryObj;

    private BindLoginThread(Context context, ApiRequest apiRequest, BindLoginObj bindLoginObj, BindLoginCallback bindLoginCallback) {
        super(context, apiRequest, bindLoginCallback);
        this.queryObj = bindLoginObj;
    }

    public static BindLoginThread bindLogin(Context context, String str, String str2, String str3, String str4, BindLoginCallback bindLoginCallback) {
        BindLoginObj bindLoginObj = new BindLoginObj(str, str2, str3, str4);
        return new BindLoginThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getBindLogin()).parameters(getParams(bindLoginObj)).post(), bindLoginObj, bindLoginCallback);
    }

    public static BindLoginThread bindLogin(Context context, String str, String str2, String str3, String str4, Map map, BindLoginCallback bindLoginCallback) {
        BindLoginObj bindLoginObj = new BindLoginObj(str, str2, str3, str4, map);
        return new BindLoginThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getBindLogin()).parameters(getParams(bindLoginObj), bindLoginObj.mExtendInfo).post(), bindLoginObj, bindLoginCallback);
    }

    private static Map<String, String> getParams(BindLoginObj bindLoginObj) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(bindLoginObj.mCaptcha)) {
            map.put("captcha", bindLoginObj.mCaptcha);
        }
        map.put("code", StringUtils.encryptWithXor(bindLoginObj.mCode));
        map.put("mobile", StringUtils.encryptWithXor(bindLoginObj.mMobile));
        map.put("profile_key", bindLoginObj.profile_key);
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public MobileApiResponse<BindLoginObj> transformResponse(boolean z, ApiResponse apiResponse) {
        return new MobileApiResponse<>(z, 10005, this.queryObj);
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
    public void onSendEvent(MobileApiResponse<BindLoginObj> mobileApiResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventMobile.BIND_LOGIN, "mobile", null, mobileApiResponse, this.mApiCall);
    }
}
