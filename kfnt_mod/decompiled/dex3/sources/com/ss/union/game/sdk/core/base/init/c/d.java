package com.ss.union.game.sdk.core.base.init.c;

import com.ss.union.game.sdk.common.util.flow.FlowItem;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.AutomaticDetectionMonitor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d extends FlowItem {
    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public String toString() {
        return "AutomaticDetectionInit";
    }

    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public void doIt() {
        if (ConfigManager.AppConfig.isDebug() & ConfigManager.AppConfig.isOpenEnvCheck()) {
            AutomaticDetectionMonitor.init();
        }
        finish();
    }
}
