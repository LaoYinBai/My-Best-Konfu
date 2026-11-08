package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import android.text.TextUtils;
import androidx.core.provider.FontsContractCompat;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.mobile.query.OneForceBindMobileQueryObj;
import com.bytedance.sdk.account.mobile.thread.call.OneForceBindMobileCallback;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.utility.StringUtils;
import com.bytedance.sdk.open.aweme.common.constants.ParamKeyConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class OneForceBindLoginApiThread extends BaseAccountApi<MobileApiResponse<OneForceBindMobileQueryObj>> {
    private OneForceBindMobileQueryObj queryObj;

    public OneForceBindLoginApiThread(Context context, ApiRequest apiRequest, OneForceBindMobileQueryObj oneForceBindMobileQueryObj, OneForceBindMobileCallback oneForceBindMobileCallback) {
        super(context, apiRequest, oneForceBindMobileCallback);
        this.queryObj = oneForceBindMobileQueryObj;
    }

    public static OneForceBindLoginApiThread oneForceBindLogin(Context context, String str, String str2, String str3, int i, OneForceBindMobileCallback oneForceBindMobileCallback) {
        OneForceBindMobileQueryObj oneForceBindMobileQueryObj = new OneForceBindMobileQueryObj(str, str2, str3, i);
        return new OneForceBindLoginApiThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getBindLogin()).parameters(getParams(oneForceBindMobileQueryObj)).post(), oneForceBindMobileQueryObj, oneForceBindMobileCallback);
    }

    private static Map<String, String> getParams(OneForceBindMobileQueryObj oneForceBindMobileQueryObj) {
        HashMap map = new HashMap();
        map.put("token", StringUtils.encryptWithXor(oneForceBindMobileQueryObj.mToken));
        map.put(ParamKeyConstants.WebViewConstants.QUERY_FROM, StringUtils.encryptWithXor(oneForceBindMobileQueryObj.mFrom));
        if (!TextUtils.isEmpty(oneForceBindMobileQueryObj.mProfileKey)) {
            map.put("profile_key", oneForceBindMobileQueryObj.mProfileKey);
        }
        map.put("bind_logic_type", String.valueOf(oneForceBindMobileQueryObj.mBindLogicType));
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public MobileApiResponse<OneForceBindMobileQueryObj> transformResponse(boolean z, ApiResponse apiResponse) {
        return new MobileApiResponse<>(z, 10005, this.queryObj);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        ApiHelper.mobileError(this.queryObj, jSONObject);
        this.queryObj.jsonResult = jSONObject2;
        this.queryObj.mResultCode = jSONObject.optString(FontsContractCompat.Columns.RESULT_CODE);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.queryObj.mUserInfo = ApiHelper.UserApiHelper.parseUser(jSONObject, jSONObject2);
        this.queryObj.jsonResult = jSONObject;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(MobileApiResponse<OneForceBindMobileQueryObj> mobileApiResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventMobile.ONE_BIND_MOBILE, "mobile", AccountMonitorConstants.EventPlatform.SCENE_FORCE_BIND_MOBILE, mobileApiResponse, this.mApiCall);
    }
}
