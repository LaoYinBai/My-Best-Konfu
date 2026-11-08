package com.ss.union.game.sdk.core.base.init.c;

import com.ss.union.game.sdk.common.util.flow.FlowItem;
import com.ss.union.game.sdk.core.base.component.TTAdComponent;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b extends FlowItem {
    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public String toString() {
        return "AdInit";
    }

    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public void doIt() {
        TTAdComponent.init();
        finish();
    }
}
