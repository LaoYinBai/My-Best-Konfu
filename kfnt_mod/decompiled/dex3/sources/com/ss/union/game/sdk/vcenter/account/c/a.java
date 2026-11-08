package com.ss.union.game.sdk.vcenter.account.c;

import android.os.RemoteException;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.vapp.VLog;
import com.ss.union.game.sdk.vcenter.g;
import com.ss.union.game.sdk.vcenter.h;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class a extends h.a {
    private com.ss.union.game.sdk.vcenter.account.a.a d;

    public a(com.ss.union.game.sdk.vcenter.account.a.a aVar) {
        this.d = aVar;
    }

    @Override // com.ss.union.game.sdk.vcenter.h
    public void a(String str, g gVar) throws RemoteException {
        a(false, str, gVar);
    }

    @Override // com.ss.union.game.sdk.vcenter.h
    public void a(int i, String str, String str2) throws RemoteException {
        VLog.Account.d("通知sdk，onAuthorizationFail: code" + i + ",message:" + str + ",ext:" + str2);
        this.d.a(2, i, str, str2);
    }

    @Override // com.ss.union.game.sdk.vcenter.h
    public void b(String str, g gVar) throws RemoteException {
        a(true, str, gVar);
    }

    private void a(boolean z, String str, final g gVar) {
        VLog.Account.d("通知sdk: 登录或者绑定，result" + str + ",toBind = " + z);
        this.d.a(com.ss.union.game.sdk.vcenter.b.b.a(str), z ? 2 : 1, new com.ss.union.game.sdk.vcenter.account.callback.c() { // from class: com.ss.union.game.sdk.vcenter.account.c.a.1
            @Override // com.ss.union.game.sdk.vcenter.account.callback.c
            public void a(int i, User user) {
                try {
                    gVar.a(true, 0, "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.ss.union.game.sdk.vcenter.account.callback.c
            public void a(int i, int i2, String str2, String str3) {
                try {
                    gVar.a(false, i2, str2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
