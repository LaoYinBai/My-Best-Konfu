package com.bytedance.sdk.account.user;

import com.bytedance.sdk.account.user.IBDAccountUserEntity;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IBDAccountEntityFactory<T extends IBDAccountUserEntity> {
    T parseUserInfo(JSONObject jSONObject) throws Exception;
}
