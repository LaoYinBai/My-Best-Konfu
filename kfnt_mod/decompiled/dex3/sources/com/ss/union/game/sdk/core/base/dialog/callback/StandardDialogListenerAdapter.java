package com.ss.union.game.sdk.core.base.dialog.callback;

import com.ss.union.game.sdk.common.dialog.BaseFragment;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class StandardDialogListenerAdapter implements a {
    @Override // com.ss.union.game.sdk.core.base.dialog.callback.a
    public boolean onBackClick(BaseFragment baseFragment) {
        return false;
    }

    @Override // com.ss.union.game.sdk.core.base.dialog.callback.a
    public boolean onCloseClick(BaseFragment baseFragment) {
        return false;
    }

    @Override // com.ss.union.game.sdk.core.base.dialog.callback.a
    public boolean onEnhanceBtnClick(BaseFragment baseFragment) {
        return false;
    }

    @Override // com.ss.union.game.sdk.core.base.dialog.callback.a
    public boolean onWeaknessBtnClick(BaseFragment baseFragment) {
        return false;
    }

    @Override // com.ss.union.game.sdk.core.base.dialog.callback.a
    public void show(BaseFragment baseFragment) {
    }
}
