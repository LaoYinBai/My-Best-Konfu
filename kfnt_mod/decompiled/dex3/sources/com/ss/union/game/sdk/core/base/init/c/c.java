package com.ss.union.game.sdk.core.base.init.c;

import com.ss.union.game.sdk.common.util.flow.FlowItem;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c extends FlowItem {
    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public String toString() {
        return "AppLogInit";
    }

    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public void doIt() {
        com.ss.union.game.sdk.core.base.component.b.a();
        finish();
    }
}
