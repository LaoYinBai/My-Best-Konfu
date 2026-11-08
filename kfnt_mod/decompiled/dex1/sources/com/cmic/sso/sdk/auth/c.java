package com.cmic.sso.sdk.auth;

import android.text.TextUtils;
import android.util.Log;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {
    public static JSONObject a(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("resultCode", str);
            jSONObject.put("desc", str2);
        } catch (Exception e) {
            Log.e("AuthnResult", "Exception", e);
        }
        return jSONObject;
    }

    static JSONObject a(String str, com.cmic.sso.sdk.a aVar, JSONObject jSONObject) throws JSONException {
        String[] strArr = {"未知", OnekeyLoginConstants.CM, OnekeyLoginConstants.CU, OnekeyLoginConstants.CT};
        try {
            String strB = aVar.b(OnekeyLoginConstants.OPERATOR_TYPE, OnekeyLoginConstants.CU_RESULT_SUCCESS);
            if (OnekeyLoginConstants.CU_RESULT_SUCCESS.equals(strB) || TextUtils.isEmpty(strB)) {
                if (OnekeyLoginConstants.CM_RESULT_SUCCESS.equals(str)) {
                    jSONObject.put("operatorType", strArr[1]);
                } else {
                    jSONObject.put("operatorType", strArr[0]);
                }
            } else {
                jSONObject.put("operatorType", strArr[Integer.parseInt(strB)]);
            }
        } catch (Exception e) {
            Log.e("AuthnResult", "JSONException", e);
        }
        return jSONObject;
    }

    static JSONObject a(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("resultCode", OnekeyLoginConstants.CM_RESULT_SUCCESS);
            jSONObject.put("desc", "true");
            jSONObject.put(OnekeyLoginConstants.CM_KEY_PRE_PHONE_SCRIP, str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    static JSONObject a(String str, String str2, com.cmic.sso.sdk.a aVar, JSONObject jSONObject) throws JSONException {
        String str3;
        String str4;
        JSONObject jSONObject2 = new JSONObject();
        try {
            int iIntValue = Integer.valueOf(aVar.b("authtype", OnekeyLoginConstants.CU_RESULT_SUCCESS)).intValue();
            int iC = aVar.c("networktype");
            switch (iIntValue) {
                case 2:
                    str3 = "7";
                    str4 = "短信验证码";
                    break;
                case 3:
                    if (iC != 3) {
                        str4 = "网关鉴权";
                        str3 = OnekeyLoginConstants.UNICOM_TYPE;
                        break;
                    } else {
                        str4 = "WIFI下网关鉴权";
                        str3 = "1";
                        break;
                    }
                case 4:
                    str3 = OnekeyLoginConstants.TELECOM_TYPE;
                    str4 = "短信上行";
                    break;
                default:
                    str3 = OnekeyLoginConstants.CU_RESULT_SUCCESS;
                    str4 = "其他";
                    break;
            }
            jSONObject2.put("resultCode", str);
            jSONObject2.put("authType", str3 + "");
            jSONObject2.put("authTypeDes", str4);
            if (OnekeyLoginConstants.CM_RESULT_SUCCESS.equals(str)) {
                if (1 == aVar.b("logintype", 0)) {
                    jSONObject2.put("openId", aVar.b("openId"));
                    jSONObject2.put(OnekeyLoginConstants.CM_KEY_PRE_PHONE_SCRIP, aVar.b(OnekeyLoginConstants.CM_KEY_PRE_PHONE_SCRIP));
                }
                jSONObject2.put("token", jSONObject.optString("token"));
            } else {
                jSONObject2.put("desc", str2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        com.cmic.sso.sdk.d.c.b("AuthnResult", "返回参数:" + jSONObject2.toString());
        return jSONObject2;
    }
}
