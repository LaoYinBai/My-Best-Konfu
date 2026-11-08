package com.ss.union.game.sdk.core.base.init.c;

import com.ss.union.game.sdk.common.util.flow.FlowItem;
import com.ss.union.game.sdk.core.base.init.callback.LGSplashInitCallback;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class h extends FlowItem {
    private LGSplashInitCallback a;

    public h(LGSplashInitCallback lGSplashInitCallback) {
        this.a = lGSplashInitCallback;
    }

    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public void doIt() {
        com.ss.union.game.sdk.core.base.component.c.a(this.a);
        finish();
    }
}
