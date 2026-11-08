package com.ss.union.game.sdk.core.vapp.buoy;

import com.ss.union.game.sdk.common.ui.floatview.IFloatViewState;
import com.ss.union.game.sdk.common.util.SPUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class e implements IFloatViewState {
    public static final int a = -1;
    private static final String b = "key_float_ball_x";
    private static final String c = "key_float_ball_y";

    e() {
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.IFloatViewState
    public void saveX(int i) {
        SPUtils.getInstance().put(b, i);
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.IFloatViewState
    public void saveY(int i) {
        SPUtils.getInstance().put(c, i);
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.IFloatViewState
    public int getX() {
        return SPUtils.getInstance().getInt(b, -1);
    }

    @Override // com.ss.union.game.sdk.common.ui.floatview.IFloatViewState
    public int getY() {
        return SPUtils.getInstance().getInt(c, -1);
    }
}
