package com.ss.android.account;

import com.bytedance.sdk.account.user.IBDAccountEntityFactory;
import com.bytedance.sdk.account.user.IBDAccountUserEntity;
import com.ss.android.account.model2.BDAccountPlatformEntity;
import com.ss.android.token.TokenConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AbsAccountUserEntity extends IBDAccountUserEntity {
    public String avatarUrl;
    public final Map<String, BDAccountPlatformEntity> bindMap;
    public String description;
    public String userName;

    public String getDescription() {
        return this.description;
    }

    public AbsAccountUserEntity(JSONObject jSONObject) {
        super(jSONObject);
        this.bindMap = new HashMap();
    }

    public AbsAccountUserEntity() {
        this.bindMap = new HashMap();
    }

    public void extract() throws Exception {
        super.extract();
        extract(this, getRawData());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void extract(AbsAccountUserEntity absAccountUserEntity, JSONObject jSONObject) throws Exception {
        if (jSONObject.has("name")) {
            absAccountUserEntity.userName = jSONObject.optString("name");
        } else if (jSONObject.has("username")) {
            absAccountUserEntity.userName = jSONObject.optString("username");
        }
        absAccountUserEntity.avatarUrl = jSONObject.optString("avatar_url");
        absAccountUserEntity.description = jSONObject.optString(TokenConstants.ERROR_MSG);
    }

    public String getUserName() {
        return this.userName;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public Map<String, BDAccountPlatformEntity> getBindMap() {
        return this.bindMap;
    }

    public static class AbsUserInfoFactory implements IBDAccountEntityFactory {
        public IBDAccountUserEntity parseUserInfo(JSONObject jSONObject) throws Exception {
            AbsAccountUserEntity absAccountUserEntity = new AbsAccountUserEntity(jSONObject);
            AbsAccountUserEntity.extract(absAccountUserEntity, jSONObject);
            return absAccountUserEntity;
        }
    }
}
