package com.bytedance.sdk.account.platform.base;

import com.bytedance.sdk.account.platform.api.IAuthorizeMonitorService;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AuthorizeMonitorUtil {
    public static final String EVENT = "passport_third_party_oauth_result";
    public static final String SCENE_AUTH = "auth";
    public static final String SPECIAL = "uc_login";

    public interface Param {
        public static final String AUTH_ERROR_CANCEL = "errorCancel";
        public static final String ERROR_CODE = "errCode";
        public static final String ERROR_DESC = "errDesc";
        public static final String PARAMS_FOR_SPECIAL = "params_for_special";
        public static final String PLATFORM = "platform";
        public static final String RESULT = "result";
        public static final String SCENE = "scene";
    }

    public interface Result {
        public static final int FAILURE = 0;
        public static final int SUCCESS = 1;
    }

    public static void onPlatformAuthEvent(String str, int i, String str2, String str3, boolean z, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        jSONObject.put("platform", str);
        jSONObject.put("scene", "auth");
        jSONObject.put("result", i);
        if (str2 != null) {
            jSONObject.put("errCode", str2);
        }
        if (str3 != null) {
            jSONObject.put("errDesc", str3);
        }
        if (i == 0 && z) {
            jSONObject.put("errorCancel", z);
        }
        jSONObject.put(Param.PARAMS_FOR_SPECIAL, SPECIAL);
        innerOnEvent(EVENT, jSONObject);
    }

    public static void checkMonitorService() {
        if (((IAuthorizeMonitorService) AuthorizeFramework.getService(IAuthorizeMonitorService.class)) == null) {
            throw new IllegalStateException("need IAuthorizeMonitorService to upload SDK events, please call AuthorizeFramework.registerService(IAuthorizeMonitorService instacne) first");
        }
    }

    private static void innerOnEvent(String str, JSONObject jSONObject) {
        IAuthorizeMonitorService iAuthorizeMonitorService = (IAuthorizeMonitorService) AuthorizeFramework.getService(IAuthorizeMonitorService.class);
        if (iAuthorizeMonitorService != null) {
            iAuthorizeMonitorService.onEvent(str, jSONObject);
        }
    }
}
