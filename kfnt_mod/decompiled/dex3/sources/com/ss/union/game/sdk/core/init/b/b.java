package com.ss.union.game.sdk.core.init.b;

import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.common.util.flow.FlowItem;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.utils.DanJuanUtils;
import com.ss.union.game.sdk.core.base.utils.LGSDKParam;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b extends FlowItem {
    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public String toString() {
        return "ApmInit";
    }

    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public void doIt() {
        String strAppChannel = ConfigManager.AppConfig.appChannel();
        HashMap map = new HashMap();
        try {
            Map<String, String> mapCommonParam = LGSDKParam.commonParam();
            if (mapCommonParam != null && mapCommonParam.size() > 0) {
                for (Map.Entry<String, String> entry : mapCommonParam.entrySet()) {
                    if (entry != null) {
                        map.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        } catch (Throwable unused) {
        }
        if (DanJuanUtils.isRunningDanJuanVirtual()) {
            map.put("GameEnv", "vapp");
            map.put("GameRealChannel", strAppChannel);
            strAppChannel = "vapp";
        }
        com.ss.union.game.sdk.core.a.a.a(GlobalApplicationUtils.getContext(), AppIdManager.apAppID(), AppIdManager.sdkAid(), 2333, "2.3.3.3", strAppChannel, map);
        finish();
    }
}
