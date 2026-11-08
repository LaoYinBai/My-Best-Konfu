package com.huawei.hms.common.internal;

import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.hms.adapter.internal.CommonCode;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;
import com.mbridge.msdk.rover.RoverCampaignUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ResponseHeader implements ResponseErrorCode, IMessageEntity {
    private static final String TAG = "ResponseHeader";

    @Packed
    private String api_name;

    @Packed
    private String app_id = "";

    @Packed
    private int error_code;

    @Packed
    private String error_reason;
    private Parcelable parcelable;

    @Packed
    private String pkg_name;

    @Packed
    private String resolution;

    @Packed
    private String session_id;

    @Packed
    private String srv_name;

    @Packed
    private int status_code;

    @Packed
    private String transaction_id;

    public ResponseHeader() {
    }

    public ResponseHeader(int i, int i2, String str) {
        this.status_code = i;
        this.error_code = i2;
        this.error_reason = str;
    }

    public boolean isSuccess() {
        return this.status_code == 0;
    }

    @Override // com.huawei.hms.common.internal.ResponseErrorCode
    public int getStatusCode() {
        return this.status_code;
    }

    public void setStatusCode(int i) {
        this.status_code = i;
    }

    @Override // com.huawei.hms.common.internal.ResponseErrorCode
    public int getErrorCode() {
        return this.error_code;
    }

    public void setErrorCode(int i) {
        this.error_code = i;
    }

    @Override // com.huawei.hms.common.internal.ResponseErrorCode
    public String getErrorReason() {
        return this.error_reason;
    }

    public void setErrorReason(String str) {
        this.error_reason = str;
    }

    public String getSrvName() {
        return this.srv_name;
    }

    public void setSrvName(String str) {
        this.srv_name = str;
    }

    public String getApiName() {
        return this.api_name;
    }

    public void setApiName(String str) {
        this.api_name = str;
    }

    public String getAppID() {
        return this.app_id;
    }

    public void setAppID(String str) {
        this.app_id = str;
    }

    public String getActualAppID() {
        if (TextUtils.isEmpty(this.app_id)) {
            return "";
        }
        String[] strArrSplit = this.app_id.split("\\|");
        if (strArrSplit.length == 0) {
            return "";
        }
        if (strArrSplit.length == 1) {
            return strArrSplit[0];
        }
        return strArrSplit[1];
    }

    public String getPkgName() {
        return this.pkg_name;
    }

    public void setPkgName(String str) {
        this.pkg_name = str;
    }

    public String getSessionId() {
        return this.session_id;
    }

    public void setSessionId(String str) {
        this.session_id = str;
    }

    @Override // com.huawei.hms.common.internal.ResponseErrorCode
    public String getTransactionId() {
        return this.transaction_id;
    }

    public void setTransactionId(String str) {
        this.transaction_id = str;
    }

    @Override // com.huawei.hms.common.internal.ResponseErrorCode
    public String getResolution() {
        return this.resolution;
    }

    @Override // com.huawei.hms.common.internal.ResponseErrorCode
    public Parcelable getParcelable() {
        return this.parcelable;
    }

    public void setParcelable(Parcelable parcelable) {
        this.parcelable = parcelable;
    }

    public void setResolution(String str) {
        this.resolution = str;
    }

    public boolean fromJson(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.status_code = JsonUtil.getIntValue(jSONObject, "status_code");
            this.error_code = JsonUtil.getIntValue(jSONObject, "error_code");
            this.error_reason = JsonUtil.getStringValue(jSONObject, "error_reason");
            this.srv_name = JsonUtil.getStringValue(jSONObject, "srv_name");
            this.api_name = JsonUtil.getStringValue(jSONObject, "api_name");
            this.app_id = JsonUtil.getStringValue(jSONObject, "app_id");
            this.pkg_name = JsonUtil.getStringValue(jSONObject, "pkg_name");
            this.session_id = JsonUtil.getStringValue(jSONObject, RoverCampaignUnit.JSON_KEY_SESSION_ID);
            this.transaction_id = JsonUtil.getStringValue(jSONObject, "transaction_id");
            this.resolution = JsonUtil.getStringValue(jSONObject, CommonCode.MapKey.HAS_RESOLUTION);
            return true;
        } catch (JSONException e) {
            HMSLog.e(TAG, "fromJson failed: " + e.getMessage());
            return false;
        }
    }

    public String toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status_code", this.status_code);
            jSONObject.put("error_code", this.error_code);
            jSONObject.put("error_reason", this.error_reason);
            jSONObject.put("srv_name", this.srv_name);
            jSONObject.put("api_name", this.api_name);
            jSONObject.put("app_id", this.app_id);
            jSONObject.put("pkg_name", this.pkg_name);
            if (!TextUtils.isEmpty(this.session_id)) {
                jSONObject.put(RoverCampaignUnit.JSON_KEY_SESSION_ID, this.session_id);
            }
            jSONObject.put("transaction_id", this.transaction_id);
            jSONObject.put(CommonCode.MapKey.HAS_RESOLUTION, this.resolution);
        } catch (JSONException e) {
            HMSLog.e(TAG, "toJson failed: " + e.getMessage());
        }
        return jSONObject.toString();
    }

    public String toString() {
        return "status_code:" + this.status_code + ", error_code" + this.error_code + ", api_name:" + this.api_name + ", app_id:" + this.app_id + ", pkg_name:" + this.pkg_name + ", session_id:*, transaction_id:" + this.transaction_id + ", resolution:" + this.resolution;
    }
}
