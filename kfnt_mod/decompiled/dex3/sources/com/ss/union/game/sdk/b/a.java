package com.ss.union.game.sdk.b;

import com.ss.union.game.sdk.a.c;
import com.ss.union.game.sdk.core.base.dialog.callback.StandardDialogListenerAdapter;
import com.ss.union.game.sdk.core.router.account.in.IRouterAccount;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a implements IRouterAccount {
    @Override // com.ss.union.game.sdk.core.router.account.in.IRouterAccount
    public void invokeLoginRealNameSelect(int i, StandardDialogListenerAdapter standardDialogListenerAdapter) {
        c.a(i, standardDialogListenerAdapter);
    }

    @Override // com.ss.union.game.sdk.core.router.account.in.IRouterAccount
    public boolean isShowLoginRealNameSelectWindow(int i) {
        return c.a(i);
    }
}
