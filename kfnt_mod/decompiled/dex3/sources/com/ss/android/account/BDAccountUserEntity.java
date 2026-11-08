package com.ss.android.account;

import com.bytedance.sdk.account.user.IBDAccountEntityFactory;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BDAccountUserEntity extends AbsAccountUserEntity {
    public String area;
    public String birthday;
    public int canFoundByPhone;
    public int canSyncShare;
    public int gender;
    public String industry;
    public int isBlocked;
    public int isBlocking;
    public boolean isGenerated;
    public boolean isRecommendAllowed;
    public boolean isToutiao;
    public String mBgImgUrl;
    public int mDisplayOcrEntrance;
    public int mFollowersCount;
    public int mFollowingCount;
    public long mMediaId;
    public int mVisitorsCount;
    public String pgcAvatarUrl;
    public long pgcMediaId;
    public String pgcName;
    public String recommendHintMessage;
    public String screenName;
    public int shareShowIcon;
    public int userPrivacyExtend;
    public String user_auth_info;
    public String user_decoration;
    public boolean user_verified;
    public String verifiedContent;

    public BDAccountUserEntity(JSONObject jSONObject) {
        super(jSONObject);
        this.mDisplayOcrEntrance = 0;
    }

    public BDAccountUserEntity() {
        this.mDisplayOcrEntrance = 0;
    }

    @Override // com.ss.android.account.AbsAccountUserEntity
    public void extract() throws Exception {
        super.extract();
        innerParseUserInfo(this, getRawData());
    }

    public static void innerParseUserInfo(BDAccountUserEntity bDAccountUserEntity, JSONObject jSONObject) throws Exception {
        bDAccountUserEntity.canFoundByPhone = jSONObject.optInt("can_be_found_by_phone");
        bDAccountUserEntity.canSyncShare = jSONObject.optInt("share_to_repost", -1);
        bDAccountUserEntity.shareShowIcon = jSONObject.optInt("user_privacy_extend") & 1;
        bDAccountUserEntity.userPrivacyExtend = jSONObject.optInt("user_privacy_extend");
        bDAccountUserEntity.gender = jSONObject.optInt("gender");
        bDAccountUserEntity.screenName = jSONObject.optString("screen_name");
        bDAccountUserEntity.verifiedContent = jSONObject.optString("verified_content");
        bDAccountUserEntity.isGenerated = jSONObject.optBoolean("is_generated");
        bDAccountUserEntity.user_verified = jSONObject.optBoolean("user_verified");
        bDAccountUserEntity.isRecommendAllowed = jSONObject.optInt("is_recommend_allowed") != 0;
        bDAccountUserEntity.recommendHintMessage = jSONObject.optString("recommend_hint_message");
        bDAccountUserEntity.user_decoration = jSONObject.optString("user_decoration");
        bDAccountUserEntity.user_auth_info = jSONObject.optString("user_auth_info");
        bDAccountUserEntity.birthday = jSONObject.optString("birthday");
        bDAccountUserEntity.area = jSONObject.optString("area");
        bDAccountUserEntity.industry = jSONObject.optString("industry");
        bDAccountUserEntity.isBlocked = jSONObject.optInt("is_blocked");
        bDAccountUserEntity.isBlocking = jSONObject.optInt("is_blocking");
        bDAccountUserEntity.isToutiao = jSONObject.optBoolean("is_toutiao");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("media");
        if (jSONObjectOptJSONObject != null) {
            bDAccountUserEntity.pgcAvatarUrl = jSONObjectOptJSONObject.optString("avatar_url");
            bDAccountUserEntity.pgcMediaId = jSONObjectOptJSONObject.optLong(AdDownloadModel.JsonKey.ID);
            bDAccountUserEntity.pgcName = jSONObjectOptJSONObject.optString("name");
            bDAccountUserEntity.mDisplayOcrEntrance = jSONObjectOptJSONObject.optInt("display_app_ocr_entrance", 0);
        }
        bDAccountUserEntity.mFollowingCount = jSONObject.optInt("followings_count");
        bDAccountUserEntity.mFollowersCount = jSONObject.optInt("followers_count");
        bDAccountUserEntity.mVisitorsCount = jSONObject.optInt("visit_count_recent");
        bDAccountUserEntity.mMediaId = jSONObject.optLong("media_id");
        bDAccountUserEntity.mBgImgUrl = jSONObject.optString("bg_img_url");
    }

    public static class UserInfoFactory implements IBDAccountEntityFactory<BDAccountUserEntity> {
        public BDAccountUserEntity parseUserInfo(JSONObject jSONObject) throws Exception {
            BDAccountUserEntity bDAccountUserEntity = new BDAccountUserEntity(jSONObject);
            bDAccountUserEntity.extract();
            return bDAccountUserEntity;
        }
    }
}
