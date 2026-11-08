package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.mobile.query.MobileQueryObj;
import com.bytedance.sdk.account.mobile.thread.call.SetPassCall;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.utility.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SetpassThread extends BaseAccountApi<MobileApiResponse<SetPassObj>> {
    private SetPassObj queryObj;

    public SetpassThread(Context context, ApiRequest apiRequest, SetPassObj setPassObj, SetPassCall setPassCall) {
        super(context, apiRequest, setPassCall);
        this.queryObj = setPassObj;
    }

    public static SetpassThread setPassword(Context context, String str, String str2, SetPassCall setPassCall) {
        SetPassObj setPassObj = new SetPassObj(str, str2);
        return new SetpassThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getSetPass()).parameters(getParams(setPassObj)).post(), setPassObj, setPassCall);
    }

    protected static Map<String, String> getParams(SetPassObj setPassObj) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(setPassObj.captcha)) {
            map.put("captcha", setPassObj.captcha);
        }
        map.put("password", StringUtils.encryptWithXor(setPassObj.password));
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public MobileApiResponse<SetPassObj> transformResponse(boolean z, ApiResponse apiResponse) {
        return new MobileApiResponse<>(z, 10002, this.queryObj);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        ApiHelper.mobileError(this.queryObj, jSONObject);
        this.queryObj.jsonResult = jSONObject2;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.queryObj.jsonResult = jSONObject;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(MobileApiResponse<SetPassObj> mobileApiResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventMobile.SET_PASSWORD, "mobile", null, mobileApiResponse, this.mApiCall);
    }

    public static class SetPassObj extends MobileQueryObj {
        String captcha;
        String password;

        public SetPassObj(String str, String str2) {
            super(13);
            this.password = str;
            this.captcha = str2;
        }
    }
}
