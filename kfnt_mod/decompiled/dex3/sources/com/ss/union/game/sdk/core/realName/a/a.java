package com.ss.union.game.sdk.core.realName.a;

import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.event.EventJSONHeaders;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static void a(User user) {
        AppLogManager.getInstance().updateUserInfo(user.open_id, user.login_type, user.is_adult, AppIdManager.lgAppID());
        EventJSONHeaders.getInstance().updateUserInfo(user.open_id, user.login_type, user.is_adult);
    }

    public static void a(boolean z) {
        AppLogManager.getInstance().updateDeviceAdultInfo(z, AppIdManager.lgAppID());
        EventJSONHeaders.getInstance().updateDeviceAdultInfo(z);
    }
}
