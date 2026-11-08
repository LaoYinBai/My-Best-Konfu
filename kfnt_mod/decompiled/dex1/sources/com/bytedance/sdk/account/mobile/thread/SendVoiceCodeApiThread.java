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
import com.bytedance.sdk.account.utility.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SendVoiceCodeApiThread extends BaseAccountApi<MobileApiResponse<SendCodeQueryObj>> {
    private SendCodeQueryObj queryObj;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(MobileApiResponse<SendCodeQueryObj> mobileApiResponse) {
    }

    private SendVoiceCodeApiThread(Context context, ApiRequest apiRequest, SendCodeQueryObj sendCodeQueryObj, SendCodeCallback sendCodeCallback) {
        super(context, apiRequest, sendCodeCallback);
        this.queryObj = sendCodeQueryObj;
    }

    public static SendVoiceCodeApiThread sendVoiceCode(Context context, String str, String str2, int i, SendCodeCallback sendCodeCallback) {
        return sendVoiceCode(context, str, str2, i, 0, sendCodeCallback);
    }

    public static SendVoiceCodeApiThread sendVoiceCode(Context context, String str, String str2, String str3, int i, SendCodeCallback sendCodeCallback) {
        SendCodeQueryObj sendCodeQueryObj = new SendCodeQueryObj(str, str2, str3, i);
        return new SendVoiceCodeApiThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getVoiceCodePath()).parameters(getParams(sendCodeQueryObj)).post(), sendCodeQueryObj, sendCodeCallback);
    }

    public static SendVoiceCodeApiThread sendVoiceCode(Context context, String str, String str2, int i, int i2, SendCodeCallback sendCodeCallback) {
        SendCodeQueryObj sendCodeQueryObj = new SendCodeQueryObj(str, str2, i, i2, -1);
        return new SendVoiceCodeApiThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getVoiceCodePath()).parameters(getParams(sendCodeQueryObj)).post(), sendCodeQueryObj, sendCodeCallback);
    }

    protected static Map<String, String> getParams(SendCodeQueryObj sendCodeQueryObj) {
        HashMap map = new HashMap();
        map.put("mobile", StringUtils.encryptWithXor(sendCodeQueryObj.mMobile));
        if (!TextUtils.isEmpty(sendCodeQueryObj.mOldMobile)) {
            map.put("old_mobile", StringUtils.encryptWithXor(sendCodeQueryObj.mOldMobile));
        }
        map.put("captcha", sendCodeQueryObj.mCaptcha);
        map.put("type", StringUtils.encryptWithXor(String.valueOf(sendCodeQueryObj.mType)));
        map.put("unbind_exist", StringUtils.encryptWithXor(String.valueOf(sendCodeQueryObj.mUnbindExist)));
        map.put("mix_mode", "1");
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
}
