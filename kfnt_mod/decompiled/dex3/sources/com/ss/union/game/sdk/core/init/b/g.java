package com.ss.union.game.sdk.core.init.b;

import com.ss.union.game.sdk.common.util.flow.FlowItem;
import com.ss.union.game.sdk.core.cgsdk.CGSDKManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class g extends FlowItem {
    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public void doIt() {
        CGSDKManager.init();
        finish();
    }
}
