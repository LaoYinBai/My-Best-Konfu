package com.ss.union.game.sdk.core.init.b;

import com.ss.union.game.sdk.common.util.flow.FlowItem;
import com.ss.union.game.sdk.core.antiAddiction.AntiAddictionManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a extends FlowItem {
    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public String toString() {
        return "AntiAddictionInit";
    }

    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public void doIt() {
        AntiAddictionManager.getInstance().antiAddictionStart();
        finish();
    }
}
