package com.ss.union.game.sdk.core.init.b;

import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.util.flow.FlowItem;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.dialog.callback.StandardDialogListenerAdapter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class h extends FlowItem {
    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public String toString() {
        return "EnvTipInit";
    }

    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public void doIt() {
        if (!ConfigManager.AppConfig.isDebug()) {
            finish();
        } else {
            com.ss.union.game.sdk.core.debug.a.a.a(new StandardDialogListenerAdapter() { // from class: com.ss.union.game.sdk.core.init.b.h.1
                @Override // com.ss.union.game.sdk.core.base.dialog.callback.StandardDialogListenerAdapter, com.ss.union.game.sdk.core.base.dialog.callback.a
                public boolean onEnhanceBtnClick(BaseFragment baseFragment) {
                    h.this.finish();
                    return super.onEnhanceBtnClick(baseFragment);
                }
            });
        }
    }
}
