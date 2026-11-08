package com.ss.union.game.sdk.core.init.b;

import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.common.util.flow.FlowItem;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.splashEffect.callback.LGSplashBrandEffectListener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class l extends FlowItem {
    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public String toString() {
        return "SplashInit";
    }

    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public void doIt() {
        if (ConfigManager.AppConfig.isOpenSplashEffect()) {
            com.ss.union.game.sdk.core.splashEffect.a.a(GlobalApplicationUtils.getApplication());
            com.ss.union.game.sdk.core.splashEffect.a.a(GlobalApplicationUtils.getContext(), new LGSplashBrandEffectListener() { // from class: com.ss.union.game.sdk.core.init.b.l.1
                @Override // com.ss.union.game.sdk.core.splashEffect.callback.LGSplashBrandEffectListener
                public void onFinish() {
                    l.this.finish();
                }
            });
        } else {
            finish();
        }
    }
}
