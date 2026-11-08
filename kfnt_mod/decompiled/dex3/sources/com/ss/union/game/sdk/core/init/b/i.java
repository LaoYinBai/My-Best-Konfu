package com.ss.union.game.sdk.core.init.b;

import com.ss.union.game.sdk.common.util.flow.FlowItem;
import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.event.PageStater;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class i extends FlowItem {
    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public String toString() {
        return "GameProtectInit";
    }

    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public void doIt() {
        com.ss.union.game.sdk.core.b.b.a(AppIdManager.apAppID(), ConfigManager.AppConfig.isDebug(), ConfigManager.LoginConfig.isNoUserLogin());
        HashMap map = new HashMap();
        map.put("blackword_request", ConfigManager.AppConfig.appName());
        PageStater.onEvent("ohayoo_sdk_blackword", map);
        com.ss.union.game.sdk.core.b.b.a(AppLogManager.getInstance().getDid(), AppLogManager.getInstance().getIid());
        finish();
    }
}
