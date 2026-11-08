package com.meizu.cloud.pushsdk.platform.message;

import android.text.TextUtils;
import com.heytap.mcssdk.mode.CommandMessage;
import com.meizu.cloud.pushinternal.DebugLogger;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class BasicPushStatus implements Serializable {
    public static final String SUCCESS_CODE = "200";
    public static final String TAG = "BasicPushStatus";
    public String code;
    public String message;

    public BasicPushStatus() {
    }

    public BasicPushStatus(String str) {
        JSONObject jSONObject = parse(str);
        if (jSONObject == null || !SUCCESS_CODE.equals(this.code) || jSONObject.isNull("value")) {
            return;
        }
        try {
            parseValueData(jSONObject.getJSONObject("value"));
        } catch (JSONException e) {
            DebugLogger.e(TAG, "parse value data error " + e.getMessage() + " json " + str);
        }
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    protected JSONObject parse(String str) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            e = e;
        }
        try {
            if (!jSONObject.isNull(CommandMessage.CODE)) {
                setCode(jSONObject.getString(CommandMessage.CODE));
            }
            if (!jSONObject.isNull("message")) {
                setMessage(jSONObject.getString("message"));
            }
            return jSONObject;
        } catch (JSONException e2) {
            e = e2;
            jSONObject2 = jSONObject;
            DebugLogger.e(TAG, "covert json error " + e.getMessage());
            return jSONObject2;
        }
    }

    public abstract void parseValueData(JSONObject jSONObject) throws JSONException;

    public void setCode(String str) {
        this.code = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String toString() {
        return "BasicPushStatus{code='" + this.code + "', message='" + this.message + "'}";
    }
}
