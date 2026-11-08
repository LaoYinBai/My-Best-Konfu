package com.bytedance.sdk.account.sso;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BindConflictUser {
    public String avatarUrl;
    public String conflictPlatformScreenName;
    public String currentPlatformScreenName;
    public String lastLoginTime;
    public String mobile;
    public String screenName;

    public static void extract(BindConflictUser bindConflictUser, JSONObject jSONObject) {
        if (jSONObject != null) {
            bindConflictUser.screenName = jSONObject.optString("screen_name");
            bindConflictUser.avatarUrl = jSONObject.optString("avatar_url");
            bindConflictUser.lastLoginTime = jSONObject.optString("last_login_time");
            bindConflictUser.mobile = jSONObject.optString("mobile");
            bindConflictUser.currentPlatformScreenName = jSONObject.optString("platform_screen_name_current");
            bindConflictUser.conflictPlatformScreenName = jSONObject.optString("platform_screen_name_conflict");
        }
    }
}
