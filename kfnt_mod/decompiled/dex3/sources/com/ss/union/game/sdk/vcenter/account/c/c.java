package com.ss.union.game.sdk.vcenter.account.c;

import android.os.RemoteException;
import com.ss.union.game.sdk.core.base.account.model.User;
import com.ss.union.game.sdk.core.vapp.VLog;
import com.ss.union.game.sdk.vcenter.g;
import com.ss.union.game.sdk.vcenter.l;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class c extends l.a {
    private com.ss.union.game.sdk.vcenter.account.a.a c;

    public c(com.ss.union.game.sdk.vcenter.account.a.a aVar) {
        this.c = aVar;
    }

    @Override // com.ss.union.game.sdk.vcenter.l
    public void a(String str, final g gVar) throws RemoteException {
        VLog.Account.d("通知sdk，onAuthorizationSuccess: result" + str);
        this.c.a(com.ss.union.game.sdk.vcenter.b.b.a(str), 3, new com.ss.union.game.sdk.vcenter.account.callback.c() { // from class: com.ss.union.game.sdk.vcenter.account.c.c.1
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

    @Override // com.ss.union.game.sdk.vcenter.l
    public void a(int i, String str, String str2) throws RemoteException {
        VLog.Account.d("通知sdk，onAuthorizationFail: code" + i + ",message:" + str + ",ext:" + str2);
        this.c.a(3, i, str, str2);
    }
}
