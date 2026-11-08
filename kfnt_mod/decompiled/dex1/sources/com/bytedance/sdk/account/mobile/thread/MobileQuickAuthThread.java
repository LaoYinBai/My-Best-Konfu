package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.mobile.query.MobileQuickAuthObj;
import com.bytedance.sdk.account.mobile.thread.call.MobileQuickAuthCallback;
import com.bytedance.sdk.account.utility.StringUtils;
import com.bytedance.sdk.account.utils.NetUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MobileQuickAuthThread extends BaseAccountApi<MobileApiResponse<MobileQuickAuthObj>> {
    private MobileQuickAuthObj queryObj;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(MobileApiResponse<MobileQuickAuthObj> mobileApiResponse) {
    }

    private MobileQuickAuthThread(Context context, ApiRequest apiRequest, MobileQuickAuthObj mobileQuickAuthObj, MobileQuickAuthCallback mobileQuickAuthCallback) {
        super(context, apiRequest, mobileQuickAuthCallback);
        this.queryObj = mobileQuickAuthObj;
    }

    public static MobileQuickAuthThread quickAuth(Context context, String str, String str2, String str3, MobileQuickAuthCallback mobileQuickAuthCallback) {
        MobileQuickAuthObj mobileQuickAuthObj = new MobileQuickAuthObj(str, str2, str3);
        return new MobileQuickAuthThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getSMSAuthPath()).parameters(getParams(mobileQuickAuthObj)).post(), mobileQuickAuthObj, mobileQuickAuthCallback);
    }

    protected static Map<String, String> getParams(MobileQuickAuthObj mobileQuickAuthObj) {
        HashMap map = new HashMap();
        map.put("mobile", StringUtils.encryptWithXor(mobileQuickAuthObj.mMobile));
        if (!TextUtils.isEmpty(mobileQuickAuthObj.mCaptcha)) {
            map.put("captcha", mobileQuickAuthObj.mCaptcha);
        }
        map.put("code", StringUtils.encryptWithXor(String.valueOf(mobileQuickAuthObj.mCode)));
        map.put("mix_mode", "1");
        map.put("type", "26");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public MobileApiResponse<MobileQuickAuthObj> transformResponse(boolean z, ApiResponse apiResponse) {
        return new MobileApiResponse<>(z, 10003, this.queryObj);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        ApiHelper.mobileError(this.queryObj, jSONObject);
        this.queryObj.jsonResult = jSONObject2;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        try {
            this.queryObj.mToken = jSONObject2.optString("token");
        } catch (Exception e) {
            this.queryObj.mError = NetUtils.checkApiException(this.mContextRef.get(), e);
        }
        this.queryObj.jsonResult = jSONObject;
    }
}
