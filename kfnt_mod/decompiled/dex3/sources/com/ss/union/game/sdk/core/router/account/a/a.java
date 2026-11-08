package com.ss.union.game.sdk.core.router.account.a;

import com.ss.union.game.sdk.core.base.dialog.callback.StandardDialogListenerAdapter;
import com.ss.union.game.sdk.core.router.account.RouterAccountManager;
import com.ss.union.game.sdk.core.router.account.in.IRouterAccount;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a extends com.ss.union.game.sdk.core.router.base.in.a<IRouterAccount> implements IRouterAccount {
    public static a a() {
        return C0089a.a;
    }

    /* renamed from: com.ss.union.game.sdk.core.router.account.a.a$a, reason: collision with other inner class name */
    private static class C0089a {
        static a a = new a();

        private C0089a() {
        }
    }

    private a() {
    }

    @Override // com.ss.union.game.sdk.core.router.account.in.IRouterAccount
    public void invokeLoginRealNameSelect(int i, StandardDialogListenerAdapter standardDialogListenerAdapter) {
        if (this.a != 0) {
            ((IRouterAccount) this.a).invokeLoginRealNameSelect(i, standardDialogListenerAdapter);
        } else {
            RouterAccountManager.logD("router == null ,please register router");
        }
    }

    @Override // com.ss.union.game.sdk.core.router.account.in.IRouterAccount
    public boolean isShowLoginRealNameSelectWindow(int i) {
        if (this.a != 0) {
            return ((IRouterAccount) this.a).isShowLoginRealNameSelectWindow(i);
        }
        RouterAccountManager.logD("router == null ,please register router");
        return false;
    }
}
