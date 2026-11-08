package com.ss.union.game.sdk.core.realName.d;

import com.ss.android.pushmanager.PushCommonConstants;
import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.ss.union.game.sdk.common.util.DeviceUtils;
import com.ss.union.game.sdk.common.util.MD5Utils;
import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.base.account.LGAccountDataUtil;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.constant.CoreUrls;
import com.ss.union.game.sdk.core.init.config.GameOptionConfig;
import com.ss.union.game.sdk.core.realName.callback.CheckDeviceRealNameCallback;
import com.ss.union.game.sdk.core.realName.callback.LGRealNameCallback;
import com.ss.union.game.sdk.core.realName.entity.CheckDeviceRealNameInfo;
import com.ss.union.game.sdk.core.realName.entity.LGRealNameRewardInfo;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static void a(final LGRealNameCallback lGRealNameCallback) {
        a(new CheckDeviceRealNameCallback() { // from class: com.ss.union.game.sdk.core.realName.d.a.1
            @Override // com.ss.union.game.sdk.core.realName.callback.CheckDeviceRealNameCallback
            public void onSuccess(CheckDeviceRealNameInfo checkDeviceRealNameInfo) {
                lGRealNameCallback.onSuccess(checkDeviceRealNameInfo.is_identify_validated, checkDeviceRealNameInfo.is_adult, null);
            }

            @Override // com.ss.union.game.sdk.core.realName.callback.CheckDeviceRealNameCallback
            public void onFail(int i, String str) {
                if (lGRealNameCallback != null) {
                    lGRealNameCallback.onFail(i, str);
                }
            }
        });
    }

    public static void a(final CheckDeviceRealNameCallback checkDeviceRealNameCallback) {
        CoreNetClient.post(CoreUrls.RealName.URL_CHECK_DEVICE_REAL_NAME).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.core.realName.d.a.2
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                if (checkDeviceRealNameCallback != null) {
                    checkDeviceRealNameCallback.onFail(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                if (checkDeviceRealNameCallback != null) {
                    JSONObject jSONObjectOptJSONObject = coreNetResponse.data.optJSONObject("data");
                    if (jSONObjectOptJSONObject != null) {
                        CheckDeviceRealNameInfo checkDeviceRealNameInfo = new CheckDeviceRealNameInfo();
                        checkDeviceRealNameInfo.parse(jSONObjectOptJSONObject);
                        checkDeviceRealNameCallback.onSuccess(checkDeviceRealNameInfo);
                        return;
                    }
                    checkDeviceRealNameCallback.onFail(ICoreNetCallback.ERROR_CODE_SERVER_ERROR, ICoreNetCallback.ERROR_CODE_SERVER_ERROR_DESC);
                }
            }
        });
    }

    public static void a(String str, String str2, final LGRealNameCallback lGRealNameCallback) {
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        CoreNetClient.post(CoreUrls.RealName.URL_DEVICE_REAL_NAME).param("app_id", AppIdManager.lgAppID()).param("identify_code", str).param("identify_name", str2).param(com.ss.union.game.sdk.redemptionCode.a.t, jCurrentTimeMillis + "").param("token", a(str, str2, jCurrentTimeMillis)).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.core.realName.d.a.3
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                if (lGRealNameCallback != null) {
                    lGRealNameCallback.onFail(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                if (lGRealNameCallback != null) {
                    JSONObject jSONObjectOptJSONObject = coreNetResponse.data.optJSONObject("data");
                    if (jSONObjectOptJSONObject != null) {
                        lGRealNameCallback.onSuccess(jSONObjectOptJSONObject.optBoolean(User.KEY_IS_IDENTIFY_VALIDATED), jSONObjectOptJSONObject.optBoolean(User.KEY_IS_ADULT), a.b());
                    } else {
                        lGRealNameCallback.onFail(ICoreNetCallback.ERROR_CODE_SERVER_ERROR, ICoreNetCallback.ERROR_CODE_SERVER_ERROR_DESC);
                    }
                }
            }
        });
    }

    public static void b(String str, String str2, final LGRealNameCallback lGRealNameCallback) {
        CoreNetClient.post(CoreUrls.RealName.URL_IDENTIFY_VALIDATE).param("app_id", AppIdManager.lgAppID()).param("identify_code", str).param("identify_name", str2).param(PushCommonConstants.KEY_USER_ID, LGAccountDataUtil.getCurrentUserOpenId()).param("token", LGAccountDataUtil.getLoginToken()).api(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.core.realName.d.a.4
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                if (lGRealNameCallback != null) {
                    lGRealNameCallback.onFail(coreNetResponse.code(), coreNetResponse.message());
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                JSONObject jSONObjectOptJSONObject = coreNetResponse.data.optJSONObject("data");
                if (jSONObjectOptJSONObject != null) {
                    boolean zOptBoolean = jSONObjectOptJSONObject.optBoolean(User.KEY_IS_IDENTIFY_VALIDATED);
                    boolean zOptBoolean2 = jSONObjectOptJSONObject.optBoolean(User.KEY_IS_ADULT);
                    LGAccountDataUtil.refreshRealNameInfoAndSave(zOptBoolean, zOptBoolean2);
                    if (lGRealNameCallback != null) {
                        lGRealNameCallback.onSuccess(zOptBoolean, zOptBoolean2, a.b());
                        return;
                    }
                    return;
                }
                if (lGRealNameCallback != null) {
                    lGRealNameCallback.onFail(ICoreNetCallback.ERROR_CODE_SERVER_ERROR, ICoreNetCallback.ERROR_CODE_SERVER_ERROR_DESC);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static LGRealNameRewardInfo b() {
        if (!GameOptionConfig.GameOption.IdentifyAward.awardEnable()) {
            return null;
        }
        LGRealNameRewardInfo lGRealNameRewardInfo = new LGRealNameRewardInfo();
        lGRealNameRewardInfo.awardEnable = true;
        lGRealNameRewardInfo.awardName = GameOptionConfig.GameOption.IdentifyAward.awardName();
        lGRealNameRewardInfo.awardIcon = GameOptionConfig.GameOption.IdentifyAward.awardIcon();
        lGRealNameRewardInfo.awardCustomInfo = GameOptionConfig.GameOption.IdentifyAward.awardCustomInfo();
        return lGRealNameRewardInfo;
    }

    private static String a(String str, String str2, long j) {
        return MD5Utils.getMD5Str("light_game_sdk" + str2 + str + AppIdManager.lgAppID() + AppLogManager.getInstance().getDid() + DeviceUtils.getAppPackageName() + j + "light_game_sdk");
    }
}
