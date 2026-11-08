package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.ValidateCodeResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.mobile.thread.call.ValidateCodeCallBack;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import com.bytedance.sdk.account.sso.ApiObj;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ValidateCodeThread extends BaseAccountApi<ValidateCodeResponse> {
    private ValidateCodeObj queryObj;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(ValidateCodeResponse validateCodeResponse) {
    }

    private ValidateCodeThread(Context context, ApiRequest apiRequest, ValidateCodeObj validateCodeObj, ValidateCodeCallBack validateCodeCallBack) {
        super(context, apiRequest, validateCodeCallBack);
        this.queryObj = validateCodeObj;
    }

    public static ValidateCodeThread validateCode(Context context, String str, int i, boolean z, ValidateCodeCallBack validateCodeCallBack) {
        ValidateCodeObj validateCodeObj = new ValidateCodeObj(str, z, i);
        return new ValidateCodeThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getValidateCodePath()).parameters(getParams(validateCodeObj)).post(), validateCodeObj, validateCodeCallBack);
    }

    public static ValidateCodeThread validateCode(Context context, String str, int i, boolean z, int i2, String str2, ValidateCodeCallBack validateCodeCallBack) {
        ValidateCodeObj validateCodeObj = new ValidateCodeObj(str, z, i, i2, str2);
        return new ValidateCodeThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getValidateCodePath()).parameters(getParams(validateCodeObj)).post(), validateCodeObj, validateCodeCallBack);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public ValidateCodeResponse transformResponse(boolean z, ApiResponse apiResponse) {
        ValidateCodeResponse validateCodeResponse = new ValidateCodeResponse(z, 1015);
        if (z) {
            validateCodeResponse.setTicket(this.queryObj.ticket);
        } else {
            validateCodeResponse.error = this.queryObj.mError;
            validateCodeResponse.errorMsg = this.queryObj.mErrorMsg;
        }
        return validateCodeResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        ApiHelper.apiError(this.queryObj, jSONObject, jSONObject2);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        if (jSONObject2 != null) {
            this.queryObj.ticket = jSONObject2.optString("ticket", "");
        }
    }

    protected static Map<String, String> getParams(ValidateCodeObj validateCodeObj) {
        HashMap map = new HashMap();
        map.put("code", Uri.encode(validateCodeObj.code));
        map.put("type", String.valueOf(validateCodeObj.scenario));
        map.put("need_ticket", validateCodeObj.needTicket ? "1" : OnekeyLoginConstants.CU_RESULT_SUCCESS);
        if (validateCodeObj.scene > 0) {
            map.put("scene", String.valueOf(validateCodeObj.scene));
        }
        if (!TextUtils.isEmpty(validateCodeObj.shark_ticket)) {
            map.put("shark_ticket", validateCodeObj.shark_ticket);
        }
        return map;
    }

    public static class ValidateCodeObj extends ApiObj {
        private String code;
        private boolean needTicket;
        private int scenario;
        private int scene;
        private String shark_ticket;
        String ticket;

        public ValidateCodeObj(String str, boolean z, int i) {
            this.code = str;
            this.needTicket = z;
            this.scenario = i;
        }

        public ValidateCodeObj(String str, boolean z, int i, int i2, String str2) {
            this.code = str;
            this.needTicket = z;
            this.scenario = i;
            this.scene = i2;
            this.shark_ticket = str2;
        }
    }
}
