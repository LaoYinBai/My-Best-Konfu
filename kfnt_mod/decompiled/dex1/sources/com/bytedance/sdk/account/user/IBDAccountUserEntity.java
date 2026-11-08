package com.bytedance.sdk.account.user;

import android.text.TextUtils;
import androidx.annotation.CallSuper;
import com.ss.android.account.model2.BDAccountPlatformEntity;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class IBDAccountUserEntity {
    public final Map<String, BDAccountPlatformEntity> bindMap = new HashMap();
    public int countryCode;
    public String email;
    public boolean hasPassword;
    public boolean isNewUser;
    public String mobile;
    protected JSONObject rawData;
    protected JSONObject rawJson;
    public String sessionKey;
    public long userId;

    public IBDAccountUserEntity(JSONObject jSONObject) {
        this.rawJson = jSONObject;
        this.rawData = jSONObject.optJSONObject("data");
    }

    public IBDAccountUserEntity() {
    }

    public JSONObject getRawJson() {
        return this.rawJson;
    }

    public JSONObject getRawData() {
        return this.rawData;
    }

    @CallSuper
    public void extract() throws Exception {
        extract(this, this.rawJson, this.rawData);
    }

    public static void extract(IBDAccountUserEntity iBDAccountUserEntity, JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        iBDAccountUserEntity.userId = jSONObject2.optLong("user_id", 0L);
        iBDAccountUserEntity.sessionKey = jSONObject2.optString("session_key", "");
        iBDAccountUserEntity.isNewUser = jSONObject2.optInt("new_user") != 0;
        iBDAccountUserEntity.mobile = jSONObject2.optString("mobile", "");
        iBDAccountUserEntity.mobile = jSONObject2.optString("mobile", "");
        iBDAccountUserEntity.hasPassword = jSONObject2.optInt("has_password") != 0;
        iBDAccountUserEntity.email = jSONObject2.optString("email", "");
        BDAccountPlatformEntity bDAccountPlatformEntityCreate = BDAccountPlatformEntity.create("mobile");
        BDAccountPlatformEntity bDAccountPlatformEntityCreate2 = BDAccountPlatformEntity.create("email");
        bDAccountPlatformEntityCreate2.mNickname = iBDAccountUserEntity.email;
        if (!TextUtils.isEmpty(iBDAccountUserEntity.email)) {
            iBDAccountUserEntity.getBindMap().put(bDAccountPlatformEntityCreate2.mName, bDAccountPlatformEntityCreate2);
        }
        bDAccountPlatformEntityCreate.mNickname = iBDAccountUserEntity.mobile;
        if (!TextUtils.isEmpty(iBDAccountUserEntity.mobile)) {
            iBDAccountUserEntity.getBindMap().put(bDAccountPlatformEntityCreate.mName, bDAccountPlatformEntityCreate);
        }
        JSONArray jSONArrayOptJSONArray = jSONObject2.optJSONArray("connects");
        if (jSONArrayOptJSONArray != null) {
            int length = jSONArrayOptJSONArray.length();
            long jCurrentTimeMillis = System.currentTimeMillis();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject3 = jSONArrayOptJSONArray.getJSONObject(i);
                String string = jSONObject3.getString("platform");
                if (string != null && string.length() != 0) {
                    BDAccountPlatformEntity bDAccountPlatformEntityCreate3 = BDAccountPlatformEntity.create(string);
                    if (jSONObject3.has("screen_name")) {
                        bDAccountPlatformEntityCreate3.mNickname = jSONObject3.optString("screen_name");
                    } else if (jSONObject3.has("platform_screen_name")) {
                        bDAccountPlatformEntityCreate3.mNickname = jSONObject3.optString("platform_screen_name");
                    }
                    bDAccountPlatformEntityCreate3.mAvatar = jSONObject3.optString("profile_image_url");
                    bDAccountPlatformEntityCreate3.mPlatformUid = jSONObject3.optString("platform_uid");
                    bDAccountPlatformEntityCreate3.mModifyTime = jSONObject3.optLong("modify_time");
                    bDAccountPlatformEntityCreate3.mCreateTIme = jSONObject3.optString("create_time");
                    bDAccountPlatformEntityCreate3.mUserId = jSONObject2.optLong("user_id", 0L);
                    long jOptLong = jSONObject3.optLong("expires_in");
                    if (jOptLong > 0) {
                        bDAccountPlatformEntityCreate3.mExpire = (1000 * jOptLong) + jCurrentTimeMillis;
                    }
                    bDAccountPlatformEntityCreate3.mExpireIn = jOptLong;
                    BDAccountPlatformEntity bDAccountPlatformEntity = iBDAccountUserEntity.getBindMap().get(string);
                    if (bDAccountPlatformEntity == null || bDAccountPlatformEntity.mModifyTime <= 0 || bDAccountPlatformEntity.mModifyTime <= bDAccountPlatformEntityCreate3.mModifyTime) {
                        iBDAccountUserEntity.getBindMap().put(string, bDAccountPlatformEntityCreate3);
                    }
                }
            }
        }
        iBDAccountUserEntity.countryCode = jSONObject2.optInt("country_code", -1);
    }

    public long getUserId() {
        return this.userId;
    }

    public Map<String, BDAccountPlatformEntity> getBindMap() {
        return this.bindMap;
    }

    public int getCountryCode() {
        return this.countryCode;
    }

    public boolean isNewUser() {
        return this.isNewUser;
    }

    public String getSessionKey() {
        return this.sessionKey;
    }

    public String getMobile() {
        return this.mobile;
    }

    public String getEmail() {
        return this.email;
    }
}
