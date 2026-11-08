package com.ss.union.game.sdk.core.base.init.c;

import android.content.Context;
import com.ss.union.game.sdk.common.util.flow.FlowItem;
import com.ss.union.game.sdk.core.base.init.GameSdkCoreInit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class f extends FlowItem {
    private Context a;

    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public String toString() {
        return "CoreBaseInit";
    }

    public f(Context context) {
        this.a = context;
    }

    @Override // com.ss.union.game.sdk.common.util.flow.FlowItem
    public void doIt() {
        GameSdkCoreInit.init(this.a);
        finish();
    }
}
